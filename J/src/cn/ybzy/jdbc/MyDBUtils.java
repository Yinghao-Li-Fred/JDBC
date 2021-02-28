package cn.ybzy.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.ybzy.model.User;

public class MyDBUtils {
	
	/**
	 * start the transaction
	 */
	public static void startTransaction(Connection conn) {
		if(conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * commit the transaction
	 */
	public static void commitTransaction(Connection conn) {
		if(conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  transaction rollback
	 */
	public static void rollbackTransaction(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	public static Connection getConnection()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		/*
		 * Properties pop = new Properties(); InputStream in =
		 * DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		 * pop.load(in); String driverClass = pop.getProperty("driverClass"); String url
		 * = pop.getProperty("url"); String user = pop.getProperty("user"); String
		 * password = pop.getProperty("password"); Class.forName(driverClass);
		 * Connection conn = DriverManager.getConnection(url, user, password); return
		 * conn;
		 */
		return dataSource.getConnection();
	}

	/**
	 * The general method of insert, update and delete
	 * 
	 * @param sql
	 * @return
	 */
	public static int IUD(String sql, Object... args) {

		Connection conn = null;
		PreparedStatement stat = null;
		int count = 0;
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql);
			
			for(int i=0; i<args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			
			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stat, null);
		}
		return count;
	}
	
	/**
	 * Close all the sql connection
	 */
	public static void close(Connection conn, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		if(stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Can be used only for the User class
	 * @param sql
	 * @param args
	 * @return
	 */
	public static User getOneUser(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		User user = null;
		
		try{
			conn = MyDBUtils.getConnection();
			//String sql = "select id, username, password, phone_no, address, reg_date from users where id=" + userid;
			stat = conn.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			
			rs = stat.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNo(rs.getString("phone_no"));
				user.setAddress(rs.getString("address"));
				user.setRegDate(rs.getDate("reg_date"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MyDBUtils.close(conn, stat, rs);
		}
		
		return user;
	}
	
	/**
	 * The general method for querying 
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	public static <T> T getOneData(Class clazz, String sql, Object... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			
			rs = stat.executeQuery();
			
			if(rs.next()) {
				// get this class's object through reflection
				entity = (T) clazz.newInstance(); 
				
				// get entity's attribute
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				Map<String, Object> map = new HashMap<>();
				
				// put the values in a map
				for(int i = 1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object val = rs.getObject(key);
					map.put(key, val);
				}
				
				// encapsulate the values in the map into entity. Java fields are variables within Java classes.
				for(Entry<String, Object> entry:map.entrySet()) {
					Field field = clazz.getDeclaredField(entry.getKey()); // getField() can only get the public field, so we use the getDeclaredField() here
					field.setAccessible(true);
					field.set(entity, entry.getValue());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return entity;
	}
}

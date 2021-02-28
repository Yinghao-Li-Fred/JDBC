package cn.ybzy.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.mysql.cj.jdbc.Driver;

import org.apache.commons.beanutils.BeanUtils;

import cn.ybzy.jdbc.MyDBUtils;
import cn.ybzy.model.User;

public class BaseDao {
	
	private PreparedStatement insteadHolder(PreparedStatement stat, Object...args) throws SQLException {
		for(int i=0; i<args.length; i++) {
			stat.setObject(i+1, args[i]);
		}
		return stat;
	}
	
	@SuppressWarnings("unchecked")
	private<T> T getEntity(ResultSet rs, Class<?> clazz) throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
		T entity = (T) clazz.newInstance(); 
		// get entity's attribute
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		// put the values in a map
		
		for(int i = 1; i <= columnCount; i++) {
			String key = rsmd.getColumnLabel(i);
			Object val = rs.getObject(key);
//			Field field = clazz.getDeclaredField(key); // getField() can only get the public field, so we use the getDeclaredField() here
//			field.setAccessible(true);
//			field.set(entity, val);
			BeanUtils.setProperty(entity, key, val); 
		}
		return entity;
	}

	public int IUD(String sql, Object...args) {
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
			MyDBUtils.close(conn, stat, null);
		}
		return count;
	}
	
	/**
	 * The IUD method support transaction
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 */
	public int IUD(Connection conn, String sql, Object...args) {
		PreparedStatement stat = null;
		int count = 0;
		try {
			stat = conn.prepareStatement(sql);
			
			for(int i=0; i<args.length; i++) {
				stat.setObject(i+1, args[i]);
			}
			
			count = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(null, stat, null);
		}
		return count;
	}
	
	/**
	 * return the primary key id
	 * @param sql
	 * @param args
	 * @return
	 */
	public int insertReturnId(String sql, Object...args) {
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int id = 0;
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insteadHolder(stat, args);
			stat.executeUpdate();
			rs = stat.getGeneratedKeys();
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
		return id;
	}
	
	public <T> T getOneData(Class<T> clazz, String sql, Object ... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		
		try {
			conn = MyDBUtils.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				entity = getEntity(rs, clazz);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return entity;
	}
	
	public <T> List<T> getListData(Class<T> clazz, String sql, Object ...args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		T entity = null;
		List<T> list = new ArrayList<>();
		
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);
			rs = stat.executeQuery();
			
			while (rs.next()) {
				entity = getEntity(rs, clazz);
				list.add(entity);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		return list;
	}
	
	public Object getOneColumn(String sql, Object ... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				return rs.getObject(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		
		return null;
	}
	
	/**
	 *\ special method for getting the blob data
	 * @param sql
	 * @param args
	 * @return
	 */
	public Blob getOneColumnBlob(String sql, Object ... args) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.prepareStatement(sql);
			insteadHolder(stat, args);
			rs = stat.executeQuery();
			
			if(rs.next()) {
				return rs.getBlob(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, stat, rs);
		}
		
		return null;
	}
}

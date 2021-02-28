package cn.ybzy.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.jupiter.api.Test;

import cn.ybzy.jdbc.MyDBUtils;
import cn.ybzy.model.User;

public class DBUtilsTest {

	@Test
	void dbuTest1() {
		// Get DBUtils QueryRunner class object
		QueryRunner qr = new QueryRunner();
		
		Connection conn = null;
		try {
			conn = MyDBUtils.getConnection();
			String sql = "insert into users (username, password, phone_no, address, reg_date) values (?, ?, ?, ?, ?)";
			qr.update(conn, sql, "white" , "123", "342", "Mars", "2020-1-2");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MyDBUtils.close(conn, null, null);
		}
	}
	
	@Test
	void dbuTest2() {
		QueryRunner qr = new QueryRunner();
		Connection conn = null;
		try {
			conn = MyDBUtils.getConnection();
			String sql = "select id, username, password, phone_no, address, reg_date from users";
			Object obj = qr.query(conn, sql, new rsHandler<User>());
			System.out.println(obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	class rsHandler<T> implements ResultSetHandler<T>{

		@Override
		public T handle(ResultSet rs) throws SQLException {
			if(rs.next()) {
				System.out.println(rs.getString("username"));
			}
			return (T) "something";
		}
		
	}
}

package cn.ybzy.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import cn.ybzy.jdbc.MyDBUtils;

public class BatchTest {

	/**
	 * Test the Statement to insert 100000 data
	 */
	@Test
	public void insertStatement() {
		Connection conn = null;
		Statement stat = null;
		String sql = null;
		
		try {
			conn = MyDBUtils.getConnection();
			stat = conn.createStatement();
			MyDBUtils.startTransaction(conn);
			
			long start = System.currentTimeMillis();
			for(int i = 1; i <= 100000; i++) {
				sql = "insert into students (sname, age_id) values('name" + i +"', '" + i + "')";
				stat.executeUpdate(sql);
			}
			long end = System.currentTimeMillis();
			MyDBUtils.commitTransaction(conn);
			System.out.println("time spending is: " + (end - start) + " milliseconds ");
		} catch(Exception e) {
			e.printStackTrace();
			MyDBUtils.rollbackTransaction(conn);
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
		
	}
	
	@Test
	public void preparedStatementTest() {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = null;
		
		try {
			conn = MyDBUtils.getConnection();
			
			MyDBUtils.startTransaction(conn);
			
			long start = System.currentTimeMillis();
			for(int i = 1; i <= 100000; i++) {
				sql = "insert into students (sname, age_id) values(?, ?)";
				stat = conn.prepareStatement(sql);
				stat.setString(1, "name" + i);
				stat.setInt(2, i);
				stat.executeUpdate();
			}
			long end = System.currentTimeMillis();
			MyDBUtils.commitTransaction(conn);
			System.out.println("time spending is: " + (end - start) + " milliseconds ");
		} catch(Exception e) {
			e.printStackTrace();
			MyDBUtils.rollbackTransaction(conn);
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
	}
	
	@Test
	public void batchTest() {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = null;
		
		try {
			conn = MyDBUtils.getConnection();
			
			MyDBUtils.startTransaction(conn);
			
			long start = System.currentTimeMillis();
			for(int i = 1; i <= 100000; i++) {
				sql = "insert into students (sname, age_id) values(?, ?)";
				stat = conn.prepareStatement(sql);
				stat.setString(1, "name" + i);
				stat.setInt(2, i);
				//stat.executeUpdate();
				if(i%300 == 0) {
					stat.executeBatch(); // execute the sql query every 300 queries.
					stat.clearBatch();
				}
			}
			if(100000%300 != 0) {
				stat.executeBatch();
				stat.clearBatch();
			}
			long end = System.currentTimeMillis();
			MyDBUtils.commitTransaction(conn);
			System.out.println("time spending is: " + (end - start) + " milliseconds ");
		} catch(Exception e) {
			e.printStackTrace();
			MyDBUtils.rollbackTransaction(conn);
		} finally {
			MyDBUtils.close(conn, stat, null);
		}
	}
}

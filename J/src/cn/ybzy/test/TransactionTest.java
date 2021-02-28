package cn.ybzy.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import cn.ybzy.dao.BaseDao;
import cn.ybzy.jdbc.MyDBUtils;

public class TransactionTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		BaseDao baseDao = new BaseDao();
		Connection conn = MyDBUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			String sql1 = "update account set balance = 500 where id =1";
			baseDao.IUD(conn, sql1);
			
			String sql2 = "update account set balance = 1500 where id =2";
			baseDao.IUD(conn, sql2);
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			MyDBUtils.close(conn, null, null);
		}
	}
}

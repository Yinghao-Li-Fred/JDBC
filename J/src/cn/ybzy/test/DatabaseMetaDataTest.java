package cn.ybzy.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import cn.ybzy.jdbc.MyDBUtils;

public class DatabaseMetaDataTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		Connection connection = MyDBUtils.getConnection();
		DatabaseMetaData dmd = connection.getMetaData();
		
	}
}

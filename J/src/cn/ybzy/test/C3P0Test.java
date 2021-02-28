package cn.ybzy.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Test {

	@Test
	public void C3p0Test() throws PropertyVetoException, SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/demo");
		cpds.setUser("root");
		cpds.setPassword("root");
		
		Connection conn = cpds.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void C3p0XmlTest() throws SQLException {
		DataSource dataSource = new ComboPooledDataSource("mysql");
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
}

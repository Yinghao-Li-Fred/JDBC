package cn.ybzy.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

public class DBCPTest {

	@Test
	public void dbcpTest() throws SQLException {
		//create DataSource implementation class: BasicDataSource
		BasicDataSource dataSource = new BasicDataSource();
		
		//connecting information
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/demo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//optional attributes: database connection management
		dataSource.setInitialSize(10);
		dataSource.setMaxTotal(50);
		dataSource.setMinIdle(5); // the minimum free connection
		dataSource.setMaxWaitMillis(1000*5); // the maximum waiting time for distribution connection
		
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
	
	/**
	 * BasicDataSourceFactory test
	 * @throws Exception 
	 */
	@Test
	public void dpcpFactoryTest() throws Exception {
		Properties pop = new Properties();
		InputStream in = DBCPTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		pop.load(in);
		DataSource dataSource = BasicDataSourceFactory.createDataSource(pop);
		
		Connection conn = dataSource.getConnection();
		System.out.println(conn);	
	}
}

package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {

	public static void main(String[] args) {
		try {
			//1. Get connection to db
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
			//2. Create a statement
			Statement statement = myConn.createStatement();
			//3.Execute 
			ResultSet myRs = statement.executeQuery("select * from user");
			//4 Process
			while(myRs.next()) {
				System.out.println(myRs.getString("User"));
			}
		
	} catch (Exception exception){
		exception.printStackTrace();
	}

	}

}

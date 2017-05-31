package com.natv.myservlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection connectDb(){
		Connection conn = null;
		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","username","password");
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test" + "?user=root&password=");
		}
		catch(Exception e){
			System.err.println(e);
		}
		return conn;
	}

}

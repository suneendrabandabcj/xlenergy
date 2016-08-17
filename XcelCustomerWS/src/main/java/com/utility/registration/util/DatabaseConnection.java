package com.utility.registration.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Suneendra Banda
 *
 *        
 *  This class contain method that connect to database.
 */
public class DatabaseConnection {
 
	/**
	 * setConnection method establish connection with MySQL and return
	 * Connection object.
	 * 
	 * @return
	 */
	public Connection setConnection() {
		
		String driver = "com.mysql.jdbc.Driver";
		
		Connection connection = null;
		try {
			try {
				Class.forName(driver).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerutility?useSSL=false", "root",
					"root");
			System.out.println("connected.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;

	}
	
	public static void main(String[] args) {
		DatabaseConnection con = new DatabaseConnection();
		Connection connn = con.setConnection();
	}

}

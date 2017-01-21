package com.codefries.console;

import java.sql.Connection;

import com.codefries.util.DatabaseUtil;

public class DatabaseConnectionTester {
	
	public static void main(String[] args) {
		Connection connection = DatabaseUtil.getConnection();
		if(connection != null) {
			System.out.println("Connection created successfully.");
			DatabaseUtil.closeConnection(connection);
			System.out.println("Connection closed successfully.");
		} else {
			System.out.println("Failed to make connection.");
		}
	}
}

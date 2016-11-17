package com.lexim.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		if(connection != null) {
			return connection;
		}
		InputStream inputStream = DatabaseUtil.class.getClassLoader().getResourceAsStream("/database.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			throw new RuntimeException("Problem loading the properties file.", e);
		} catch (SQLException e) {
			throw new RuntimeException("Connection cannot be established.", e);
		} 
		return connection;
	}
	
	public static void closeConnection(Connection toBeClosed) {
		if(toBeClosed == null) {
			return;
		}
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			throw new RuntimeException("Problem closing the connection.", e);
		}
	}
}

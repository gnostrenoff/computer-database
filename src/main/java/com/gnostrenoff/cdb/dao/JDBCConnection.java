package com.gnostrenoff.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {

	private static JDBCConnection jdbcConn;
	private static String url;
	private static String username;
	private static String password;
	
	private JDBCConnection(){
		try {
	        InputStream file = JDBCConnection.class.getResourceAsStream("db_properties");
	        Properties props = new Properties();
			props.load(file);
			Class.forName(props.getProperty("DRIVER_CLASS"));
			url = props.getProperty("URL");
			username = props.getProperty("USERNAME");
			password = props.getProperty("PASSWORD");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCConnection getInstance(){	
		if(jdbcConn == null){
			jdbcConn = new JDBCConnection();
		}
		return jdbcConn;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

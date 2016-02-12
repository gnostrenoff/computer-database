package com.gnostrenoff.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.gnostrenoff.cdb.exceptions.ConnectionPropertiesFileNotFoundException;

/**
 * this class provides configured connection instances. It can have only one instance. 
 * @author excilys
 */
public class JDBCConnection {

	private static JDBCConnection jdbcConn;
	private String driver;
	private String url;
	private String username;
	private String password;
	
	/**
	 * constructor makes sure there is only one instance at a time, and prepares properties for connections.
	 */
	private JDBCConnection(){
		try {
	        InputStream file = JDBCConnection.class.getClassLoader().getResourceAsStream("db.properties");
	        if(file == null){
	        	throw new ConnectionPropertiesFileNotFoundException("properties file not found");
	        }
	        Properties props = new Properties();
			props.load(file);
			driver = props.getProperty("db.DRIVER_CLASS");
			Class.forName(driver);
			url = props.getProperty("db.URL");
			username = props.getProperty("db.USERNAME");
			password = props.getProperty("db.PASSWORD");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this methode statically provides a instance of this class 
	 * @return an instance of this class
	 */
	public static JDBCConnection getInstance(){	
		if(jdbcConn == null){
			jdbcConn = new JDBCConnection();
		}
		return jdbcConn;
	}
	
	/**
	 * this method provides a configured connection, ready to handle transaction.
	 * @return a configured connection
	 */
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}
}

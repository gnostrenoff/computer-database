package com.gnostrenoff.cdb.dao.util;

import com.gnostrenoff.cdb.controller.exception.ConnectionException;
import com.gnostrenoff.cdb.dao.exception.PropertiesFileNotFoundException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * this class provides configured connection instances. It can have only one instance.
 * 
 * @author excilys
 */
public class ConnectionManager {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
  
  /** The jdbc conn. */
  private static ConnectionManager jdbcConn = new ConnectionManager();
  
  /** The connection pool. */
  BoneCP connectionPool = null;
  
  /** The driver. */
  private static String driver;
  
  /** The url. */
  private static String url;
  
  /** The username. */
  private static String username;
  
  /** The password. */
  private static String password;

  /**
   * constructor makes sure there is only one instance at a time, and prepares properties for
   * connections.
   */
  private ConnectionManager() {
    try {
      // load properties file
      LOGGER.info("loading properties file ...");
      InputStream file = getClass().getClassLoader().getResourceAsStream("db.properties");
      if (file == null) {
        throw new PropertiesFileNotFoundException("properties file not found");
      }
      Properties props = new Properties();
      props.load(file);

      // retrieve data from file
      driver = props.getProperty("db.DRIVER_CLASS");
      url = props.getProperty("db.URL");
      username = props.getProperty("db.USERNAME");
      password = props.getProperty("db.PASSWORD");

      LOGGER.info("loading database driver ...");
      // load the database driver
      Class.forName(driver);

      LOGGER.info("configuring connection pooling ...");
      // configure and create a connection pool
      BoneCPConfig config = new BoneCPConfig();
      config.setJdbcUrl(url);
      config.setUsername(username);
      config.setPassword(password);
      config.setMinConnectionsPerPartition(5);
      config.setMaxConnectionsPerPartition(10);
      config.setPartitionCount(2);
      connectionPool = new BoneCP(config);

    } catch (ClassNotFoundException e1) {
      throw new ConnectionException("failed to load database driver");
    } catch (IOException e) {
      throw new ConnectionException("failed to read properties file");
    } catch (SQLException e) {
      throw new ConnectionException("failed to set up connection pool");
    }
  }

  /**
   * this methode provides an instance of JDBCConnection.
   *
   * @return an instance of this class
   */
  public static ConnectionManager getInstance() {
    return jdbcConn;
  }

  /**
   * this method provides a configured connection through the connection pool.
   *
   * @return a configured connection
   */
  public Connection getConnection() {
    try {
      return connectionPool.getConnection();
    } catch (SQLException e) {
      throw new ConnectionException(
          "connection provider failed while attempting to give a connection");
    }
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the driver.
   *
   * @return the driver
   */
  public String getDriver() {
    return driver;
  }
}

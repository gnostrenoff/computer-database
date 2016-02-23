package com.gnostrenoff.cdb.service.util;

import com.gnostrenoff.cdb.dao.util.ConnectionManager;
import com.gnostrenoff.cdb.service.exception.TransactionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionManager.
 */
public class TransactionManager {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);
  
  /** The instance. */
  public static TransactionManager instance = new TransactionManager();
  
  /** The context. */
  public final ThreadLocal<Connection> context = new ThreadLocal<Connection>();

  /**
   * Instantiates a new transaction manager.
   */
  private TransactionManager() {
  }

  /**
   * Gets the single instance of TransactionManager.
   *
   * @return single instance of TransactionManager
   */
  public static TransactionManager getInstance() {
    return instance;
  }

  /**
   * Gets the connection.
   *
   * @return the connection
   */
  public Connection getConnection() {
    Connection conn = context.get();
    if (conn == null) {
      conn = ConnectionManager.getInstance().getConnection();
      context.set(conn);
    }
    return conn;
  }

  /**
   * Close connection.
   */
  public void closeConnection() {
    Connection conn = context.get();
    try {
      if (conn != null && conn.getAutoCommit()) {
        conn.close();
        context.remove();
      }
    } catch (SQLException e) {
      LOGGER.error("failed to close connection");
      throw new TransactionException("failed to close connection");
    }

  }

  /**
   * Start transaction.
   */
  public void startTransaction() {
    try {
      getConnection().setAutoCommit(false);
      LOGGER.info("transaction has started");
    } catch (SQLException e) {
      LOGGER.error("failed to start transaction");
      throw new TransactionException("failed to start transaction");
    }
  }

  /**
   * Commit transaction.
   */
  public void commitTransaction() {
    try {
      context.get().commit();
      LOGGER.info("successfully commited");
    } catch (SQLException e) {
      LOGGER.error("failed to commit transaction");
      throw new TransactionException("failed to commit transaction");
    }
  }

  /**
   * Rollback transaction.
   */
  public void rollbackTransaction() {
    try {
      context.get().rollback();
      LOGGER.info("transaction rolled back");
    } catch (SQLException e) {
      LOGGER.error("failed to rollback transaction");
      throw new TransactionException("failed to rollback transaction");
    }
  }

  /**
   * End transaction.
   */
  public void endTransaction() {
    try {
      context.get().close();
      LOGGER.info("transaction has ended");
    } catch (SQLException e) {
      LOGGER.error("failed to end transaction");
      throw new TransactionException("failed to end transaction");
    }
    context.remove();
  }

}

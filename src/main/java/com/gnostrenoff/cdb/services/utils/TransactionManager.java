package com.gnostrenoff.cdb.services.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.utils.ConnectionManager;
import com.gnostrenoff.cdb.services.exceptions.TransactionException;

public class TransactionManager {

	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionManager.class);
	public static TransactionManager instance = new TransactionManager();
	public final ThreadLocal<Connection> CONTEXT = new ThreadLocal<Connection>();
	
	private TransactionManager(){
	}
	
	public static TransactionManager getInstance(){
		return instance;
	}

	public Connection getConnection() {
		Connection conn = CONTEXT.get();
		if (conn == null) {
			conn = ConnectionManager.getInstance().getConnection();
			CONTEXT.set(conn);
		}
		return conn;
	}

	public void closeConnection() {
		Connection conn = CONTEXT.get();
		try {
			if (conn != null && conn.getAutoCommit()) {
				conn.close();
				CONTEXT.remove();
			}
		} catch (SQLException e) {
			LOGGER.error("failed to close connection");
			throw new TransactionException("failed to close connection");
		}

	}

	public void startTransaction() {
		try {
			getConnection().setAutoCommit(false);
			LOGGER.info("transaction has started");
		} catch (SQLException e) {
			LOGGER.error("failed to start transaction");
			throw new TransactionException("failed to start transaction");
		}
	}

	public void commitTransaction() {
		try {
			CONTEXT.get().commit();
			LOGGER.info("successfully commited");
		} catch (SQLException e) {
			LOGGER.error("failed to commit transaction");
			throw new TransactionException("failed to commit transaction");
		}
	}

	public void rollbackTransaction() {
		try {
			CONTEXT.get().rollback();
			LOGGER.info("transaction rolled back");
		} catch (SQLException e) {
			LOGGER.error("failed to rollback transaction");
			throw new TransactionException("failed to rollback transaction");
		}
	}

	public  void endTransaction() {
		try {
			CONTEXT.get().close();
			LOGGER.info("transaction has ended");
		} catch (SQLException e) {
			LOGGER.error("failed to end transaction");
			throw new TransactionException("failed to end transaction");
		}
		CONTEXT.remove();
	}

}

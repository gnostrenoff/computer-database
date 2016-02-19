package com.gnostrenoff.cdb.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.exceptions.DaoException;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;

/**
 * class providing static methods to close objects
 * @author excilys
 *
 */
public class ObjectCloser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectCloser.class);
	
	private ObjectCloser(){}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			close(conn, ps);
			rs.close();
		} catch (SQLException e) {
			LOGGER.error("failed to close object");
			throw new DaoException("failed to close object");
		}
	}
	public static void close(Connection conn, PreparedStatement ps){
		try {
			conn.close();
			ps.close();
		} catch (SQLException e) {
			LOGGER.error("failed to close object");
			throw new DaoException("failed to close object");
		}
	}

}

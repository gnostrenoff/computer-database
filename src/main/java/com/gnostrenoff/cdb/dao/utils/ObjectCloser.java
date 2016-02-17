package com.gnostrenoff.cdb.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gnostrenoff.cdb.exceptions.DaoException;

public class ObjectCloser {
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			conn.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new DaoException("failed to close connection");
		}
	}
	public static void close(Connection conn, PreparedStatement ps){
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DaoException("failed to close connection");
		}
	}

}

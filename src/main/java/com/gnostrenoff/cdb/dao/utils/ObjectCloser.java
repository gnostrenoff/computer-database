package com.gnostrenoff.cdb.dao.utils;

import com.gnostrenoff.cdb.dao.exceptions.DaoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * class providing static methods to close objects.
 *
 * @author excilys
 */
public class ObjectCloser {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ObjectCloser.class);

  /**
   * Instantiates a new object closer.
   */
  private ObjectCloser() {
  }

  /**
   * Close.
   *
   * @param ps the ps
   * @param rs the rs
   */
  public static void close(PreparedStatement ps, ResultSet rs) {
    try {
      close(ps);
      if (rs != null) {
        rs.close();
      }        
    } catch (SQLException e) {
      LOGGER.error("failed to close object");
      throw new DaoException("failed to close object");
    }
  }

  /**
   * Close.
   *
   * @param ps the ps
   */
  public static void close(PreparedStatement ps) {
    try {
      if (ps != null) {
        ps.close();
      }       
    } catch (SQLException e) {
      LOGGER.error("failed to close object");
      throw new DaoException("failed to close object");
    }
  }

}

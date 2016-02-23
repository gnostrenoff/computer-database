package com.gnostrenoff.cdb.dao.util;

import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.model.QueryParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * class providing sql PreparedStatement.
 *
 * @author excilys
 */
public class StatementCreator {

  /** The Constant SQL_GET_MANY. */
  private static final String SQL_GET_MANY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY %s %s LIMIT ? OFFSET ?";
  
  /** The Constant SQL_GET_MANY_WITH_SEARCH. */
  private static final String SQL_GET_MANY_WITH_SEARCH = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY %s %s LIMIT ? OFFSET ?";
  
  /** The Constant SQL_GET_MANY_BY_ID. */
  private static final String SQL_GET_MANY_BY_ID = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE company_id=? ORDER BY %s %s";
  
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(StatementCreator.class);

  /**
   * method creates prepared statement, depending on given parameters.
   *
   * @param params          parameters
   * @param conn          connection to use
   * @return built prepared statement
   */
  public static PreparedStatement create(QueryParams params, Connection conn) {

    PreparedStatement ps = null;
    String query = null;

    long companyId = params.getCompanyId();
    String search = params.getSearch();
    String orderBy = params.getOrderBy();
    String order = params.getOrder();

    if (companyId != 0) {
      query = SQL_GET_MANY_BY_ID;
    } else if (search != null && !search.isEmpty()) {
      query = SQL_GET_MANY_WITH_SEARCH;
    } else {
      query = SQL_GET_MANY;
    }

    if (orderBy != null && !orderBy.isEmpty() && order != null && !order.isEmpty()) {
      if (orderBy.equals(OrderBy.NAME) || orderBy.equals(OrderBy.COMPANY)
          || orderBy.equals(OrderBy.INTRODUCED) || orderBy.equals(OrderBy.DISCONTINUED)) {
        query = String.format(query, orderBy, order);
      } else {
        LOGGER.error("Order by parameter not valid");
        throw new DaoException("Order by parameter not valid");
      }

    } else {
      query = String.format(query, OrderBy.NAME, Order.ASC);
    }

    try {
      ps = conn.prepareStatement(query);
      if (companyId != 0) {
        ps.setLong(1, companyId);
      } else if (search != null && !search.isEmpty()) {
        ps.setString(1, "%" + search + "%");
        ps.setString(2, "%" + search + "%");
        ps.setInt(3, 10);
        ps.setInt(4, params.getOffset());
      } else {
        ps.setInt(1, params.getNbElements());
        ps.setInt(2, params.getOffset());
      }

    } catch (SQLException e) {
      LOGGER.error("failed to get a statement");
      throw new DaoException("failed to get a statement");
    }

    return ps;
  }

}

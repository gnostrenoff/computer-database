package com.gnostrenoff.cdb.dao.mapper;

import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is providing a static method to convert a resultset to a company object.
 *
 * @author excilys
 */
public class CompanyDaoMapper {
  
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoMapper.class);

  /**
   * converts a resulset to a company object.
   *
   * @param rs          resultset to convert
   * @return company obtained
   */
  public static Company map(ResultSet rs) {
    Company company = new Company();
    try {
      company.setId(rs.getLong("id"));
      company.setName(rs.getString("name"));
    } catch (SQLException e) {
      LOGGER.error("failed to convert into company");
      throw new DaoException("failed to convert into company", e);
    }
    return company;
  }
}

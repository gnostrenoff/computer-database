package com.gnostrenoff.cdb.dao.mapper;

import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is providing a static method to convert a resultset to a company object.
 *
 * @author excilys
 */
public class CompanyDaoMapper implements RowMapper<Company> {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoMapper.class);

  @Override
  public Company mapRow(ResultSet rs, int arg1) throws SQLException {
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

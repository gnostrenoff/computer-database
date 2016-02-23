package com.gnostrenoff.cdb.dao.mapper;

import com.gnostrenoff.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * This class is providing a static method to convert a resultset to a company object.
 *
 * @author excilys
 */
public class CompanyDaoMapper {

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
      e.printStackTrace();
    }
    return company;
  }
}

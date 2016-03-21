package com.gnostrenoff.cdb.dao.mapper;

import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * This class is providing a static method to convert a resultset to a computer object.
 *
 * @author excilys
 */
public class ComputerDaoMapper implements RowMapper<Computer> {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoMapper.class);

  public ComputerDaoMapper() {
    super();
  }

  @Override
  public Computer mapRow(ResultSet rs, int arg1) throws SQLException {
    Computer computer = new Computer();
    try {
      computer.setId(rs.getLong("computer.id"));
      computer.setName(rs.getString("computer.name"));
      Timestamp ts = rs.getTimestamp("introduced");
      if (ts != null) {
        computer.setIntroduced(ts.toLocalDateTime().toLocalDate());
      }
      ts = rs.getTimestamp("discontinued");
      if (ts != null) {
        computer.setDiscontinued(ts.toLocalDateTime().toLocalDate());
      }
      Company company = new Company();
      company.setId(rs.getLong("company.id"));
      company.setName(rs.getString("company.name"));
      computer.setCompany(company);

    } catch (SQLException e) {
      LOGGER.error("failed to convert into computer");
      throw new DaoException("failed to convert into computer", e);
    }
    return computer;
  }

  /**
   * Gets the attributes of the given computer.
   *
   * @param computer
   *          the computer
   * @return the attributes of the computer in an object array
   */
  public static Object[] getRequiredArgs(Computer computer) {

    Object[] args = new Object[4];
    args[0] = computer.getName();

    LocalDate localDate = computer.getIntroduced();
    if (localDate != null) {
      args[1] = computer.getIntroduced();
    }
    localDate = computer.getDiscontinued();
    if (localDate != null) {
      args[2] = computer.getDiscontinued();
    }

    Company company = computer.getCompany();
    if (company != null && company.getId() >= 0) {
      args[3] = computer.getCompany().getId();
    }

    return args;
  }

}

package com.gnostrenoff.cdb.dao.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.dao.mapper.CompanyDaoMapper;
import com.gnostrenoff.cdb.dao.util.ObjectCloser;
import com.gnostrenoff.cdb.model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * implementation of CompanyDao interface.
 *
 * @author excilys
 */
@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {
  
  @Autowired
  private DataSource dataSource;

  /** The Constant SQL_GET_ONE. */
  private static final String SQL_GET_ONE = "SELECT * FROM company WHERE id=?";
  
  /** The Constant SQL_DELETE. */
  private static final String SQL_DELETE = "delete from company where id=?";
  
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);

  /**
   * Instantiates a new company dao impl.
   */
  private CompanyDaoImpl() {
  }

  @Override
  public List<Company> getList() throws DaoException {

    List<Company> companyList = new ArrayList<>();
    String query = "select * from company";
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      conn = dataSource.getConnection();
      ps = conn.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        companyList.add(CompanyDaoMapper.map(rs));
      }
    } catch (SQLException e) {
      LOGGER.error("failed to get company list");
      throw new DaoException("failed to get company list");
    } finally {
      ObjectCloser.close(ps, rs);
    }

    return companyList;
  }

  @Override
  public Company get(long companyId) throws DaoException {
    String query = SQL_GET_ONE;
    Connection conn = null;
    PreparedStatement ps = null;
    Company company = null;
    ResultSet rs = null;

    try {
      conn = dataSource.getConnection();
      ps = conn.prepareStatement(query);
      ps.setLong(1, companyId);
      rs = ps.executeQuery();
      if (rs.next()) {
        company = CompanyDaoMapper.map(rs);
      } else {
        company = null;
      }

    } catch (SQLException e) {
      LOGGER.error("failed to get company");
      throw new DaoException("failed to get company");
    } finally {
      ObjectCloser.close(ps, rs);
    }

    return company;
  }

  @Override
  public void delete(long id) {
    String query = SQL_DELETE;
    Connection conn = null;
    PreparedStatement ps = null;

    try {
      conn = dataSource.getConnection();
      ps = conn.prepareStatement(query);
      ps.setLong(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("failed to delete company");
      throw new DaoException("failed to delete company");
    } finally {
      ObjectCloser.close(ps);
    }
  }

}

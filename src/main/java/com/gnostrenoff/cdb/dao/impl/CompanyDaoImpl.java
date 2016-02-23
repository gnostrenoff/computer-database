package com.gnostrenoff.cdb.dao.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.dao.mapper.CompanyDaoMapper;
import com.gnostrenoff.cdb.dao.util.ObjectCloser;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.service.util.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * implementation of CompanyDao interface.
 *
 * @author excilys
 */
public class CompanyDaoImpl implements CompanyDao {

  /** The Constant SQL_GET_ONE. */
  private static final String SQL_GET_ONE = "SELECT * FROM company WHERE id=?";
  
  /** The Constant SQL_DELETE. */
  private static final String SQL_DELETE = "delete from company where id=?";
  
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
  
  /** The company dao imp. */
  private static CompanyDaoImpl companyDaoImp = new CompanyDaoImpl();

  /**
   * Instantiates a new company dao impl.
   */
  private CompanyDaoImpl() {
  }

  /**
   * Gets the single instance of CompanyDaoImpl.
   *
   * @return single instance of CompanyDaoImpl
   */
  public static CompanyDaoImpl getInstance() {
    return companyDaoImp;
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.dao.CompanyDao#getList()
   */
  @Override
  public List<Company> getList() throws DaoException {

    List<Company> companyList = new ArrayList<>();
    String query = "select * from company";
    TransactionManager tm = TransactionManager.getInstance();
    Connection conn = tm.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
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
      tm.closeConnection();
    }

    return companyList;
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.dao.CompanyDao#get(long)
   */
  @Override
  public Company get(long companyId) throws DaoException {
    String query = SQL_GET_ONE;
    TransactionManager tm = TransactionManager.getInstance();
    Connection conn = tm.getConnection();
    PreparedStatement ps = null;
    Company company = null;
    ResultSet rs = null;

    try {
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
      tm.closeConnection();
    }

    return company;
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.dao.CompanyDao#delete(long)
   */
  @Override
  public void delete(long id) {
    String query = SQL_DELETE;
    TransactionManager tm = TransactionManager.getInstance();
    Connection conn = tm.getConnection();
    PreparedStatement ps = null;

    try {
      ps = conn.prepareStatement(query);
      ps.setLong(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("failed to delete company");
      throw new DaoException("failed to delete company");
    } finally {
      ObjectCloser.close(ps);
      tm.closeConnection();
    }
  }

}

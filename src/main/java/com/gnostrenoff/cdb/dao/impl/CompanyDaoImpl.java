package com.gnostrenoff.cdb.dao.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.dao.mapper.CompanyDaoMapper;
import com.gnostrenoff.cdb.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.sql.DataSource;

/**
 * implementation of CompanyDao interface.
 *
 * @author excilys
 */
@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {

  /** The Constant SQL_GET_ALL. */
  private static final String SQL_GET_ALL = "select * from company";

  /** The Constant SQL_GET_ONE. */
  private static final String SQL_GET_ONE = "SELECT * FROM company WHERE id=?";

  /** The Constant SQL_DELETE. */
  private static final String SQL_DELETE = "delete from company where id=?";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Company> getList() throws DaoException {
    List<Company> companyList = jdbcTemplate.query(SQL_GET_ALL, new CompanyDaoMapper());
    return companyList;
  }

  @Override
  public Company get(long companyId) throws DaoException {
    Company company = jdbcTemplate.queryForObject(SQL_GET_ONE, new CompanyDaoMapper(), companyId );
    return company;
  }

  @Override
  public void delete(long id) {
    jdbcTemplate.update(SQL_DELETE, id);
  }

}

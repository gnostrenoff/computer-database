package com.gnostrenoff.cdb.dao.impl;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.mapper.ComputerDaoMapper;
import com.gnostrenoff.cdb.dao.util.PreparedStatementComputerCreator;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.sql.DataSource;

/**
 * The Class ComputerDaoImpl.
 */
@Repository("computerDao")
public class ComputerDaoImpl implements ComputerDao {

  /** The Constant SQL_CREATE. */
  private static final String SQL_CREATE = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";

  /** The Constant SQL_GET_ONE. */
  private static final String SQL_GET_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";

  /** The Constant SQL_DELETE. */
  private static final String SQL_DELETE = "delete from computer where id=?";

  /** The Constant SQL_DELETE_BY_ID. */
  private static final String SQL_DELETE_BY_ID = "delete from computer where company_id=?";

  /** The Constant SQL_UPDATE. */
  private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";

  /** The Constant SQL_GET_ROWCOUNT. */
  private static final String SQL_GET_ROWCOUNT = "SELECT COUNT(*) FROM computer;";

  /** The Constant SQL_GET_ROWCOUNT_SEARCH. */
  private static final String SQL_GET_ROWCOUNT_SEARCH = "SELECT COUNT(*) FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public void create(Computer computer) {
    System.out.println(computer);
    jdbcTemplate.update(SQL_CREATE, ComputerDaoMapper.getRequiredArgs(computer));
  }

  @Override
  public Computer get(long computerId) {
    Computer computer = jdbcTemplate.queryForObject(SQL_GET_ONE, new ComputerDaoMapper(),
        computerId);
    return computer;
  }

  @Override
  public void update(Computer computer) {
    Object[] args = new Object[5];
    ComputerDaoMapper.getRequiredArgs(computer);
    System.arraycopy(ComputerDaoMapper.getRequiredArgs(computer), 0, args, 0, 4);
    args[4] = computer.getId();
    jdbcTemplate.update(SQL_UPDATE, args);
  }

  @Override
  public void delete(long computerId) {
    jdbcTemplate.update(SQL_DELETE, computerId);
  }

  @Override
  public List<Computer> getList(QueryParams params) {

    List<Computer> computerList = jdbcTemplate.query(new PreparedStatementComputerCreator(params),
        new ComputerDaoMapper());
    return computerList;
  }

  @Override
  public int count(String search) {

    int count = 0;

    if (search != null && !search.isEmpty()) {
      count = jdbcTemplate.queryForObject(SQL_GET_ROWCOUNT_SEARCH, Integer.class,
          "%" + search + "%", "%" + search + "%");
    } else {
      count = jdbcTemplate.queryForObject(SQL_GET_ROWCOUNT, Integer.class);
    }
    return count;
  }

  @Override
  public void deleteByCompanyId(long companyId) {
    jdbcTemplate.update(SQL_DELETE_BY_ID, companyId);
  }

}

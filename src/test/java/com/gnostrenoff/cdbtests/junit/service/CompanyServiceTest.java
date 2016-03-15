package com.gnostrenoff.cdbtests.junit.service;

import static org.junit.Assert.assertNotNull;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.exception.DaoException;
import com.gnostrenoff.cdb.service.CompanyService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * The Class CompanyServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context-test.xml" })
@Ignore
public class CompanyServiceTest {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceTest.class);

  /** The dataSource. */
  @Autowired
  private DataSource dataSource;

  /** The service. */
  @Autowired
  private CompanyService companyService;

  /** The company dao mock. */
  @Autowired
  private CompanyDao companyDaoMock;

  /** The company dao mock. */
  @Autowired
  private ComputerDao computerDao;

  /**
   * Inits the.
   */
  @Before
  public void init() {

    Connection conn = null;
    Statement statement = null;
    try {
      conn = dataSource.getConnection();
      statement = conn.createStatement();

      statement.execute("SET FOREIGN_KEY_CHECKS=0");
      statement.executeUpdate("TRUNCATE computer");
      statement.executeUpdate("TRUNCATE company");
      statement.execute("SET FOREIGN_KEY_CHECKS=1");
      statement.executeUpdate("insert into company (id,name) values (1,'company of computerInDb')");
      statement
          .executeUpdate("insert into computer (id,name, company_id) values (1,'computerInDb', 1)");

    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error("test database initialisation failed");
    }

    // init company dao mock
    Mockito.doThrow(new DaoException("")).when(companyDaoMock).delete(1L);
  }

  /**
   * Tests transaction while deleting a company by id.
   *
   * @return the all computers
   */
  @Test
  public void deleteById() {
    // expected : roolback between the two delete calls.
    companyService.delete(1);
    assertNotNull(computerDao.findOne(1L));
  }

}

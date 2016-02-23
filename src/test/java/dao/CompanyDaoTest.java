package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.utils.ConnectionManager;
import com.gnostrenoff.cdb.model.Company;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDaoTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionManager.class)
@Ignore
public class CompanyDaoTest {

  /** The company dao. */
  private static CompanyDao companyDao;
  
  /** The data source. */
  private static JdbcDataSource dataSource;
  
  /** The database tester. */
  private static IDatabaseTester databaseTester;

  /**
   * Inits the.
   */
  @BeforeClass
  public static void init() {

    ConnectionManager jdbcConnection = ConnectionManager.getInstance();
    try {
      RunScript.execute(jdbcConnection.getUrl(), jdbcConnection.getUsername(),
          jdbcConnection.getPassword(), "src/test/java/db-test/SCHEMA_TEST.sql",
          Charset.forName("UTF8"), false);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    dataSource = new JdbcDataSource();
    dataSource.setURL(jdbcConnection.getUrl());

    dataSource.setUser(jdbcConnection.getUsername());
    dataSource.setPassword(jdbcConnection.getPassword());

    companyDao = CompanyDaoImpl.getInstance();
  }

  /**
   * Import data set.
   *
   * @throws Exception the exception
   */
  @Before
  public void importDataSet() throws Exception {
    IDataSet dataSet = readDataSet();
    cleanlyInsert(dataSet);
  }

  /**
   * Read data set.
   *
   * @return the i data set
   * @throws Exception the exception
   */
  private IDataSet readDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File("src/test/java/db-test/dataset.xml"));
  }

  /**
   * Cleanly insert.
   *
   * @param dataSet the data set
   * @throws Exception the exception
   */
  private void cleanlyInsert(IDataSet dataSet) throws Exception {
    ConnectionManager jdbcConnection = ConnectionManager.getInstance();
    databaseTester = new JdbcDatabaseTester(jdbcConnection.getDriver(), jdbcConnection.getUrl(),
        jdbcConnection.getUsername(), jdbcConnection.getPassword());
    databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();
  }

  /**
   * Gets the all companies.
   *
   * @return the all companies
   */
  @Test
  public void getAllCompanies() {

    List<Company> list = companyDao.getList();
    assertNotNull(list);
    assertTrue(list.size() == 2);
    Company company0 = new Company(1, "Apple Inc.");
    Company company1 = new Company(2, "Thinking Machines");

    assertTrue(company0.equals(list.get(0)));
    assertTrue(company1.equals(list.get(1)));

  }
}

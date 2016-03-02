package com.gnostrenoff.cdbtests.junit.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.dao.util.ConnectionManager;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.spring.ApplicationContextProvider;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.AfterClass;
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
import java.time.LocalDate;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDaoTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionManager.class)
@Ignore
public class ComputerDaoTest {

  /** The computer dao. */
  private static ComputerDao computerDao;

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

    computerDao = ApplicationContextProvider.getApplicationContext().getBean("computerDao",
        ComputerDaoImpl.class);
    ;
  }

  /**
   * Import data set.
   *
   * @throws Exception
   *           the exception
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
   * @throws Exception
   *           the exception
   */
  private IDataSet readDataSet() throws Exception {
    return new FlatXmlDataSetBuilder().build(new File("src/test/java/db-test/dataset.xml"));
  }

  /**
   * Cleanly insert.
   *
   * @param dataSet
   *          the data set
   * @throws Exception
   *           the exception
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
   * Gets the all computers.
   *
   * @return the all computers
   */
  @Test
  public void getAllComputers() {

    List<Computer> list = computerDao.getList(null);
    assertNotNull(list);
    assertTrue(list.size() == 2);
    Computer computer0 = new Computer(3, "macbook", LocalDate.of(2015, 03, 12),
        LocalDate.of(2015, 8, 12), new Company(1, "Apple Inc."));
    Computer computer1 = new Computer(4, "CM-200", LocalDate.of(2014, 03, 12),
        LocalDate.of(2015, 9, 12), new Company(2, "Thinking Machines"));
    assertTrue(computer0.equals(list.get(0)));
    assertTrue(computer1.equals(list.get(1)));

  }

  /**
   * Gets the one computers.
   *
   * @return the one computers
   */
  @Test
  public void getOneComputers() {

    Computer computerFromDatabase = computerDao.get(3);
    assertNotNull(computerFromDatabase);
    Computer computer = new Computer(3, "macbook", LocalDate.of(2015, 03, 12),
        LocalDate.of(2015, 8, 12), new Company(1, "Apple Inc."));
    assertTrue(computer.equals(computerFromDatabase));
  }

  /**
   * Delete computer.
   */
  @Test
  public void deleteComputer() {

    computerDao.delete(4);

    try {
      IDataSet dataSet = databaseTester.getConnection().createDataSet();
      ITable cTable = dataSet.getTable("computer");
      assertTrue(cTable.getRowCount() == 1);
      assertTrue(cTable.getValue(0, "id").toString().equals("3"));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Creates the computer.
   */
  @Test
  public void createComputer() {

    Computer computer = new Computer(5, "macbookpro", LocalDate.of(2011, 03, 12),
        LocalDate.of(2010, 8, 12), new Company(1, "Apple Inc."));
    computerDao.create(computer);

    try {
      IDataSet dataSet = databaseTester.getConnection().createDataSet();
      ITable cTable = dataSet.getTable("computer");
      assertTrue(cTable.getRowCount() == 3);
      int index = 0;
      while (!cTable.getValue(index, "id").toString().equals("5"))
        index++;
      assertTrue(cTable.getValue(index, "name").equals("macbookpro"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Update computer.
   */
  @Test
  public void updateComputer() {

    Computer computer = new Computer(3, "macbook++", LocalDate.of(2014, 03, 12),
        LocalDate.of(2015, 8, 12), new Company(1, "Apple Inc."));
    computerDao.update(computer);

    try {
      IDataSet dataSet = databaseTester.getConnection().createDataSet();
      ITable cTable = dataSet.getTable("computer");
      assertTrue(cTable.getRowCount() == 2);
      int index = 0;
      while (!cTable.getValue(index, "id").toString().equals("3")) {
        index++;
      }
      assertTrue(cTable.getValue(index, "name").equals("macbook++"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * End.
   */
  @AfterClass
  public static void end() {
    computerDao = null;
    computerDao = null;
    dataSource = null;
  }

}

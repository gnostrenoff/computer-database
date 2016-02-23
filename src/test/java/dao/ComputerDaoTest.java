package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.dao.utils.ConnectionManager;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionManager.class)
@Ignore
public class ComputerDaoTest {

	private static ComputerDao computerDao;
	private static JdbcDataSource dataSource;
	private static IDatabaseTester databaseTester;

	@BeforeClass
	public static void init() {

		ConnectionManager jdbcConnection = ConnectionManager.getInstance();
		try {
			RunScript.execute(jdbcConnection.getUrl(), jdbcConnection.getUsername(), jdbcConnection.getPassword(),
					"src/test/java/db-test/SCHEMA_TEST.sql", Charset.forName("UTF8"), false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		dataSource = new JdbcDataSource();
		dataSource.setURL(jdbcConnection.getUrl());

		dataSource.setUser(jdbcConnection.getUsername());
		dataSource.setPassword(jdbcConnection.getPassword());

		computerDao = ComputerDaoImpl.getInstance();
	}

	@Before
	public void importDataSet() throws Exception {
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/test/java/db-test/dataset.xml"));
	}

	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		ConnectionManager jdbcConnection = ConnectionManager.getInstance();
		databaseTester = new JdbcDatabaseTester(jdbcConnection.getDriver(), jdbcConnection.getUrl(),
				jdbcConnection.getUsername(), jdbcConnection.getPassword());
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void getAllComputers() {

		List<Computer> list = computerDao.getList(null);
		assertNotNull(list);
		assertTrue(list.size() == 2);
		Computer computer0 = new Computer(3, "macbook", LocalDate.of(2015, 03, 12), LocalDate.of(2015, 8, 12),
				new Company(1, "Apple Inc."));
		Computer computer1 = new Computer(4, "CM-200", LocalDate.of(2014, 03, 12), LocalDate.of(2015, 9, 12),
				new Company(2, "Thinking Machines"));
		assertTrue(computer0.equals(list.get(0)));
		assertTrue(computer1.equals(list.get(1)));

	}

	@Test
	public void getOneComputers() {

		Computer computerFromDatabase = computerDao.get(3);
		assertNotNull(computerFromDatabase);
		Computer computer = new Computer(3, "macbook", LocalDate.of(2015, 03, 12), LocalDate.of(2015, 8, 12),
				new Company(1, "Apple Inc."));
		assertTrue(computer.equals(computerFromDatabase));
	}

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

	@Test
	public void createComputer() {

		Computer computer = new Computer(5, "macbookpro", LocalDate.of(2011, 03, 12), LocalDate.of(2010, 8, 12),
				new Company(1, "Apple Inc."));
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

	@Test
	public void updateComputer() {

		Computer computer = new Computer(3, "macbook++", LocalDate.of(2014, 03, 12), LocalDate.of(2015, 8, 12),
				new Company(1, "Apple Inc."));
		computerDao.update(computer);

		try {
			IDataSet dataSet = databaseTester.getConnection().createDataSet();
			ITable cTable = dataSet.getTable("computer");
			assertTrue(cTable.getRowCount() == 2);
			int index = 0;
			while (!cTable.getValue(index, "id").toString().equals("3"))
				index++;
			assertTrue(cTable.getValue(index, "name").equals("macbook++"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void end() {
		computerDao = null;
		computerDao = null;
		dataSource = null;
	}

}

package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JDBCConnection.class)
public class ComputerDaoTest {

	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	private static Connection connection;
	private static ComputerDao computerDao;
	private static JdbcDataSource dataSource;
	private static JDBCConnection jdbcConnection;
	private static IDatabaseTester databaseTester;

	@BeforeClass
	public static void init() {

		// create connection
		try {
			RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "", "src/test/java/db-test/SCHEMA_TEST.sql",
					Charset.forName("UTF8"), false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		dataSource = new JdbcDataSource();
		dataSource.setURL(JDBC_URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASSWORD);

		jdbcConnection = Mockito.mock(JDBCConnection.class);
		PowerMockito.mockStatic(JDBCConnection.class);
		BDDMockito.given(JDBCConnection.getInstance()).willReturn(jdbcConnection);

		computerDao = ComputerDaoImpl.getInstance();
	}

	@Before
	public void importDataSet() throws Exception {
		getTestConnection();
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}

	public void getTestConnection() {
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Mockito.when(jdbcConnection.getConnection()).thenReturn(connection);
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/test/java/db-test/dataset.xml"));
	}

	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void getAllComputers() {

		List<Computer> list = computerDao.getComputers();
		assertNotNull(list);
		assertTrue(list.size() == 2);
		Computer computer0 = new Computer(3, "macbook", LocalDateTime.of(2015, 03, 12, 12, 35, 00),
				LocalDateTime.of(2015, 8, 12, 12, 36, 00), new Company(1, "Apple Inc."));
		Computer computer1 = new Computer(4, "CM-200", LocalDateTime.of(2014, 03, 12, 12, 35, 00),
				LocalDateTime.of(2015, 9, 12, 12, 36, 00), new Company(2, "Thinking Machines"));
		assertTrue(computer0.equals(list.get(0)));
		assertTrue(computer1.equals(list.get(1)));

	}

	@Test
	public void getOneComputers() {

		Computer computerFromDatabase = computerDao.getComputer(3);
		assertNotNull(computerFromDatabase);
		Computer computer = new Computer(3, "macbook", LocalDateTime.of(2015, 03, 12, 12, 35, 00),
				LocalDateTime.of(2015, 8, 12, 12, 36, 00), new Company(1, "Apple Inc."));
		assertTrue(computer.equals(computerFromDatabase));
	}

	@Test
	public void deleteComputer() {

		computerDao.deleteComputer(4);

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
    public void createComputer(){

    	Computer computer = new Computer(5, "macbookpro", LocalDateTime.of(2011, 03, 12, 12, 35, 00), LocalDateTime.of(2010, 8, 12, 12, 36, 00), new Company(1, "Apple Inc."));
    	computerDao.createComputer(computer); 	
    	
    	try {
			IDataSet dataSet = databaseTester.getConnection().createDataSet();
			ITable cTable = dataSet.getTable("computer");
			assertTrue(cTable.getRowCount() == 3);
			int index = 0; 
			while(!cTable.getValue(index, "id").toString().equals("5")) index++;
			assertTrue(cTable.getValue(index, "name").equals("macbookpro"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Test
	public void updateComputer() {

		Computer computer = new Computer(3, "macbook++", LocalDateTime.of(2014, 03, 12, 12, 35, 00),
				LocalDateTime.of(2015, 8, 12, 12, 36, 00), new Company(1, "Apple Inc."));
		computerDao.updateComputer(computer);

		try {
			IDataSet dataSet = databaseTester.getConnection().createDataSet();
			ITable cTable = dataSet.getTable("computer");
			assertTrue(cTable.getRowCount() == 2);
			int index = 0; 
			while(!cTable.getValue(index, "id").toString().equals("3")) index++;
			assertTrue(cTable.getValue(index, "name").equals("macbook++"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void end() {
		computerDao = null;
		connection = null;
		computerDao = null;
		dataSource = null;
		jdbcConnection = null;
	}

}

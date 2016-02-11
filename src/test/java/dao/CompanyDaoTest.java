package dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.model.Company;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JDBCConnection.class)
public class CompanyDaoTest {

	private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	private static Connection connection;
	private static CompanyDao companyDao;
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

		companyDao = CompanyDaoImpl.getInstance();
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
	public void getAllCompanies() {

		List<Company> list = companyDao.getCompanies();
		assertNotNull(list);
		assertTrue(list.size() == 2);
		Company company0 = new Company(1, "Apple Inc.");
		Company company1 = new Company(2, "Thinking Machines");
		
		assertTrue(company0.equals(list.get(0)));
		assertTrue(company1.equals(list.get(1)));

	}
}

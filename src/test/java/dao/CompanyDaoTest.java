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
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Company;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JDBCConnection.class)
public class CompanyDaoTest {
	
	private static CompanyDao companyDao;
	private static JdbcDataSource dataSource;
	private static IDatabaseTester databaseTester;

	@BeforeClass
	public static void init() {

		JDBCConnection jdbcConnection = JDBCConnection.getInstance();
		try {
			RunScript.execute(jdbcConnection.getUrl(), jdbcConnection.getUsername(), jdbcConnection.getPassword(), "src/test/java/db-test/SCHEMA_TEST.sql",
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

	@Before
	public void importDataSet() throws Exception {
		IDataSet dataSet = readDataSet();
		cleanlyInsert(dataSet);
	}

	private IDataSet readDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/test/java/db-test/dataset.xml"));
	}

	private void cleanlyInsert(IDataSet dataSet) throws Exception {
		JDBCConnection jdbcConnection = JDBCConnection.getInstance();
		databaseTester = new JdbcDatabaseTester(jdbcConnection.getDriver(), jdbcConnection.getUrl(), jdbcConnection.getUsername(), jdbcConnection.getPassword());
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

package com.gnostrenoff.cdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.mappers.CompanyMapper;
import com.gnostrenoff.cdb.mappers.ComputerMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

/**
 * implementation of CompanyDao interface
 * 
 * @author excilys
 *
 */
public class CompanyDaoImpl implements CompanyDao {

	private static final String SQL_GET_ONE = "SELECT * FROM company WHERE id=?";
	private static CompanyDaoImpl companyDaoImp;
	private JDBCConnection jdbcConnection;

	private CompanyDaoImpl() {
		this.jdbcConnection = JDBCConnection.getInstance();
	}

	public static CompanyDaoImpl getInstance() {
		if (companyDaoImp == null) {
			companyDaoImp = new CompanyDaoImpl();
		}
		return companyDaoImp;
	}

	@Override
	public List<Company> getCompanies() {

		List<Company> companyList = new ArrayList<>();
		String query = "select * from company";
		ResultSet rs = null;
		Connection conn = jdbcConnection.getConnection();

		try {
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				companyList.add(CompanyMapper.map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return companyList;
	}

	@Override
	public Company getCompany(long companyId) {
		String query = SQL_GET_ONE;
		Connection conn = jdbcConnection.getConnection();
		Company company = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(true);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, companyId);
			rs = ps.executeQuery();
			if (rs.next()) {
				company = CompanyMapper.map(rs);
			} else {
				company = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return company;
	}

}

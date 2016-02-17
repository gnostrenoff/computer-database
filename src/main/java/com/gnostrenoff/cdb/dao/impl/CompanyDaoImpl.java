package com.gnostrenoff.cdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.mappers.CompanyDaoMapper;
import com.gnostrenoff.cdb.dao.utils.JDBCConnection;
import com.gnostrenoff.cdb.dao.utils.ObjectCloser;
import com.gnostrenoff.cdb.exceptions.DaoException;
import com.gnostrenoff.cdb.model.Company;

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
	public List<Company> getList() throws DaoException {

		List<Company> companyList = new ArrayList<>();
		String query = "select * from company";
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				companyList.add(CompanyDaoMapper.map(rs));
			}
		} catch (SQLException e) {
			throw new DaoException("failed to get company list");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

		return companyList;
	}

	@Override
	public Company get(long companyId) throws DaoException {
		String query = SQL_GET_ONE;
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		Company company = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(query);
			ps.setLong(1, companyId);
			rs = ps.executeQuery();
			if (rs.next()) {
				company = CompanyDaoMapper.map(rs);
			} else {
				company = null;
			}

		} catch (SQLException e) {
			throw new DaoException("failed to get company");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

		return company;
	}

}

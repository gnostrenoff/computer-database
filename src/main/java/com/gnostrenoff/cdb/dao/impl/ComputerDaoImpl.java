package com.gnostrenoff.cdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.exceptions.DaoException;
import com.gnostrenoff.cdb.dao.mappers.ComputerDaoMapper;
import com.gnostrenoff.cdb.dao.utils.ObjectCloser;
import com.gnostrenoff.cdb.dao.utils.StatementCreator;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.utils.TransactionManager;

public class ComputerDaoImpl implements ComputerDao {

	private static final String SQL_CREATE = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";
	private static final String SQL_GET_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
	private static final String SQL_DELETE = "delete from computer where id=?";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private static final String SQL_GET_ROWCOUNT = "SELECT COUNT(*) FROM computer;";
	private static final String SQL_GET_ROWCOUNT_SEARCH = "SELECT COUNT(*) FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

	private static ComputerDaoImpl computerDaoImpl = new ComputerDaoImpl();

	private ComputerDaoImpl() {
	}

	public static ComputerDaoImpl getInstance() {
		return computerDaoImpl;
	}

	@Override
	public void create(Computer computer) throws DaoException {

		String query = SQL_CREATE;
		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, computer.getName());

			LocalDate localDate = computer.getIntroduced();
			if (localDate != null) {
				ps.setTimestamp(2, Timestamp.valueOf(localDate.atStartOfDay()));
			} else {
				ps.setNull(2, Types.TIMESTAMP);
			}
			localDate = computer.getDiscontinued();
			if (localDate != null) {
				ps.setTimestamp(3, Timestamp.valueOf(localDate.atStartOfDay()));
			} else {
				ps.setNull(3, Types.TIMESTAMP);
			}

			Company company = computer.getCompany();
			if (company != null) {
				ps.setLong(4, company.getId());
			} else {
				ps.setNull(4, Types.DOUBLE);
			}

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			computer.setId(rs.getLong(1));
		} catch (Exception e) {
			LOGGER.error("failed to create computer");
			throw new DaoException("failed to create computer");
		} finally {
			ObjectCloser.close(ps, rs);
			tm.closeConnection();
		}

	}

	@Override
	public Computer get(long computerId) throws DaoException {

		String query = SQL_GET_ONE;
		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		PreparedStatement ps = null;
		Computer computer = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			rs = ps.executeQuery();
			rs.next();
			computer = ComputerDaoMapper.map(rs);
		} catch (SQLException e) {
			LOGGER.error("failed to get computer");
			throw new DaoException("failed to get computer");
		} finally {
			ObjectCloser.close(ps, rs);
			tm.closeConnection();
		}

		return computer;
	}

	@Override
	public void update(Computer computer) throws DaoException {

		String query = SQL_UPDATE;
		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, computer.getName());

			LocalDate localDate = computer.getIntroduced();
			if (localDate != null) {
				ps.setTimestamp(2, Timestamp.valueOf(localDate.atStartOfDay()));
			} else {
				ps.setNull(2, Types.TIMESTAMP);
			}

			localDate = computer.getDiscontinued();
			if (localDate != null) {
				ps.setTimestamp(3, Timestamp.valueOf(localDate.atStartOfDay()));
			} else {
				ps.setNull(3, Types.TIMESTAMP);
			}

			Company company = computer.getCompany();
			if (company != null) {
				ps.setLong(4, company.getId());
			} else {
				ps.setNull(4, Types.TIMESTAMP);
			}

			ps.setLong(5, computer.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("failed to update computer");
			throw new DaoException("failed to update computer");
		} finally {
			ObjectCloser.close(ps);
			tm.closeConnection();
		}
	}

	@Override
	public void delete(long computerId) throws DaoException {

		String query = SQL_DELETE;
		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("failed to delete computer");
			throw new DaoException("failed to delete computer");
		} finally {
			ObjectCloser.close(ps);
			tm.closeConnection();
		}

	}

	@Override
	public List<Computer> getList(QueryParams params) throws DaoException {

		List<Computer> computerList = new ArrayList<>();
		ResultSet rs = null;
		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		PreparedStatement ps = StatementCreator.create(params, conn);

		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				computerList.add(ComputerDaoMapper.map(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("failed to get computer list");
			throw new DaoException("failed to get computer list");
		} finally {
			ObjectCloser.close(ps, rs);
			tm.closeConnection();
		}

		return computerList;
	}

	@Override
	public int count(QueryParams params) throws DaoException {

		TransactionManager tm = TransactionManager.getInstance();
		Connection conn = tm.getConnection();
		String query = null, search = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowCount = 0;

		if (params != null) {
			search = params.getSearch();
			if (search != null && !search.isEmpty()) {
				query = SQL_GET_ROWCOUNT_SEARCH;
			} else {
				query = SQL_GET_ROWCOUNT;
			}
		} else {
			query = SQL_GET_ROWCOUNT;
		}

		try {
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(query);

			if (search != null && !search.isEmpty()) {
				ps.setString(1, "%" + search + "%");
				ps.setString(2, "%" + search + "%");
			}

			rs = ps.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);

		} catch (SQLException e) {
			LOGGER.error("failed to get row count");
			throw new DaoException("failed to get row count");
		} finally {
			ObjectCloser.close(ps, rs);
			tm.closeConnection();
		}

		return rowCount;
	}

}

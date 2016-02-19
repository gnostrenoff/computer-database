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
import com.gnostrenoff.cdb.dao.utils.JDBCConnection;
import com.gnostrenoff.cdb.dao.utils.ObjectCloser;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;

public class ComputerDaoImpl implements ComputerDao {

	private static final String SQL_CREATE = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";
	private static final String SQL_GET_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
	private static final String SQL_DELETE = "delete from computer where id=?";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private static final String SQL_GET_MANY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT %d OFFSET %d";
	private static final String SQL_GET_ROWCOUNT = "SELECT COUNT(*) FROM computer;";
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

	private static ComputerDaoImpl computerDaoImpl = new ComputerDaoImpl();
	private JDBCConnection jdbcConnection;

	private ComputerDaoImpl() {
		this.jdbcConnection = JDBCConnection.getInstance();
	}

	public static ComputerDaoImpl getInstance() {
		return computerDaoImpl;
	}

	@Override
	public void create(Computer computer) throws DaoException {

		String query = SQL_CREATE;
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);
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
				ps.setNull(4, Types.TIMESTAMP);
			}

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			computer.setId(rs.getLong(1));
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			LOGGER.error("failed to create computer");
			throw new DaoException("failed to create computer");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

	}

	@Override
	public Computer get(long computerId) throws DaoException {

		String query = SQL_GET_ONE;
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		Computer computer = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			rs = ps.executeQuery();
			rs.next();
			computer = ComputerDaoMapper.map(rs);
		} catch (SQLException e) {
			LOGGER.error("failed to get computer");
			throw new DaoException("failed to get computer");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

		return computer;
	}

	@Override
	public void update(Computer computer) throws DaoException {

		String query = SQL_UPDATE;
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
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
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			LOGGER.error("failed to update computer");
			throw new DaoException("failed to update computer");
		} finally {
			ObjectCloser.close(conn, ps);
		}
	}

	@Override
	public void delete(long computerId) throws DaoException {

		String query = SQL_DELETE;
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			LOGGER.error("failed to delete computer");
			throw new DaoException("failed to delete computer");
		} finally {
			ObjectCloser.close(conn, ps);
		}

	}

	@Override
	public List<Computer> getList(QueryParams params) throws DaoException {

		List<Computer> computerList = new ArrayList<>();
		String query = String.format(SQL_GET_MANY, params.getNbElements(), params.getOffset());
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				computerList.add(ComputerDaoMapper.map(rs));
			}
		} catch (SQLException e) {
			LOGGER.error("failed to get computer list");
			throw new DaoException("failed to get computer list");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

		return computerList;
	}

	@Override
	public int count() throws DaoException {

		Connection conn = jdbcConnection.getConnection();
		String query = SQL_GET_ROWCOUNT;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowCount = 0;

		try {
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);

		} catch (SQLException e) {
			LOGGER.error("failed to get row count");
			throw new DaoException("failed to get row count");
		} finally {
			ObjectCloser.close(conn, ps, rs);
		}

		return rowCount;
	}

}

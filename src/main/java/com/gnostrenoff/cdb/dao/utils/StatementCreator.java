package com.gnostrenoff.cdb.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.exceptions.DaoException;
import com.gnostrenoff.cdb.model.QueryParams;

public class StatementCreator {

	private static final String SQL_GET_MANY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
	private static final String SQL_GET_MANY_WITH_SEARCH = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? LIMIT ? OFFSET ?";
	private static final String SQL_GET_MANY_BY_ID = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE company_id=?";
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectCloser.class);

	public static PreparedStatement create(QueryParams params, Connection conn) {

		PreparedStatement ps = null;
		String query = null;

		long companyId = params.getCompanyId();
		String search = params.getSearch();

		if (companyId != 0) {
			query = SQL_GET_MANY_BY_ID;
		} else if (search != null && !search.isEmpty()) {
			query = SQL_GET_MANY_WITH_SEARCH;
		} else {
			query = SQL_GET_MANY;
		}

		try {
			ps = conn.prepareStatement(query);
			if (companyId != 0) {
				ps.setLong(1, companyId);
			} else if (search != null && !search.isEmpty()) {
				ps.setString(1, "%" + search + "%");
				ps.setString(2, "%" + search + "%");
				ps.setInt(3, 10);
				ps.setInt(4, params.getOffset());
			} else {
				ps.setInt(1, params.getNbElements());
				ps.setInt(2, params.getOffset());
			}

		} catch (SQLException e) {
			LOGGER.error("failed to get a statement");
			throw new DaoException("failed to get a statement");
		}

		return ps;
	}

}

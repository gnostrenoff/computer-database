package com.gnostrenoff.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.gnostrenoff.cdb.model.Computer;

/**
 * This class is providing a static method to convert a resultset to a computer object
 * @author excilys
 */
public class ComputerMapper {

	/**
	 * converts a resulset to a computer object
	 * @param rs resultset to convert
	 * @return computer obtained
	 */
	public static Computer map(ResultSet rs){
		Computer computer = new Computer();
		try {
			computer.setId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
			computer.setIntroduced(rs.getTimestamp("introduced"));
			computer.setDiscontinued(rs.getTimestamp("discontinued"));
			computer.setCompanyId(rs.getLong("company_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

}

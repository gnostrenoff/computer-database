package com.gnostrenoff.cdb.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

/**
 * This class is providing a static method to convert a resultset to a computer object
 * @author excilys
 */
public class ComputerDaoMapper {

	/**
	 * converts a resulset to a computer object
	 * @param rs resultset to convert
	 * @return computer obtained
	 */
	public static Computer map(ResultSet rs){
		Computer computer = new Computer();
		try {
			computer.setId(rs.getLong("computer.id"));
			computer.setName(rs.getString("computer.name"));
			Timestamp ts = rs.getTimestamp("introduced");
			if(ts != null){
				computer.setIntroduced(ts.toLocalDateTime().toLocalDate());
			}
			ts = rs.getTimestamp("discontinued");
			if(ts != null){
				computer.setDiscontinued(ts.toLocalDateTime().toLocalDate());
			}	
			Company company = new Company();
			company.setId(rs.getLong("company.id"));
			company.setName(rs.getString("company.name"));
			computer.setCompany(company);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

}

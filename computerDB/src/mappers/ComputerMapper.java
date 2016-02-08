package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Computer;

public class ComputerMapper {

	public static Computer map(ResultSet rs){
		Computer computer = new Computer();
		try {
			computer.setId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
			//computer.setIntroduced(rs.getDate("introduced"));
			//computer.setDiscontinued(rs.getDate("discontinued"));
			computer.setCompanyId(rs.getLong("company_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

}

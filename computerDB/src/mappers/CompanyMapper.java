package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

public class CompanyMapper {
	
	public static Company map(ResultSet rs){
		Company company = new Company();
		try {
			company.setId(rs.getLong("id"));
			company.setName(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}

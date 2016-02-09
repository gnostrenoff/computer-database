package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

/**
 * This class is providing a static method to convert a resultset to a company object
 * @author excilys
 */
public class CompanyMapper {
	
	/**
	 * converts a resulset to a company object
	 * @param rs resultset to convert
	 * @return company obtained
	 */
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

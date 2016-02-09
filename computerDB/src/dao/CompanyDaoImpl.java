package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.DaoException;
import mappers.CompanyMapper;
import model.Company;

/**
 * implementation of CompanyDao interface
 * @author excilys
 *
 */
public class CompanyDaoImpl implements CompanyDao{
	
	/**
	 * dao factory to get a connection
	 */
	private DaoFactory daoFactory;
	/**
	 * connection got from dao factory
	 */
	private Connection conn;
	
	protected CompanyDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Company> getCompanies(){
		
		List<Company> companyList = new ArrayList<>();	
		String query = "select * from company";
		ResultSet rs = null;
		conn = daoFactory.getConnection();
		
		try{
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			while(rs.next()){
				companyList.add(CompanyMapper.map(rs));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return companyList;
	}

}

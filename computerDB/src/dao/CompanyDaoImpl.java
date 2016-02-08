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

public class CompanyDaoImpl implements CompanyDao{
	
	private DaoFactory daoFactory;
	private Connection conn;
	
	protected CompanyDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		this.conn = daoFactory.getConnection();
	}

	@Override
	public List<Company> getCompanies(){
		List<Company> companyList = new ArrayList<>();
		
		String query = "select * from company";
		ResultSet rs = null;
		
		try{
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
		}catch(SQLException e){
			
		}
		
		try {
			while(rs.next()){
				companyList.add(CompanyMapper.map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return companyList;
	}

}

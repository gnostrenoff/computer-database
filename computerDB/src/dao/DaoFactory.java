package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";
	private static Connection conn;
	private static DaoFactory daoFactory;
	
	private DaoFactory(){
	}
	
	public static DaoFactory getInstance(){
		
		if(daoFactory == null){
	
			daoFactory = new DaoFactory();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		return daoFactory;
	}
	
	protected Connection getConnection(){
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public CompanyDao getCompanyDao(){
		return new CompanyDaoImpl(this);
	}
	
	public ComputerDao getComputerDao(){
		return new ComputerDaoImpl(this);
	}

}

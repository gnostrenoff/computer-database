package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn;
	private static DaoFactory daoFactory;
	
	private DaoFactory(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DaoFactory getInstance(){
		
		if(daoFactory == null){
	
			daoFactory = new DaoFactory("jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull", "root", "root");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return daoFactory;
	}
	
	protected Connection getConnection(){
		return conn;
	}
	
	public CompanyDao getCompanyDao(){
		return new CompanyDaoImpl(this);
	}
	
	public ComputerDao getComputerDao(){
		return new ComputerDaoImpl(this);
	}

}

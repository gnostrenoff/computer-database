package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mappers.ComputerMapper;
import model.Computer;

public class ComputerDaoImpl implements ComputerDao{
	
	private DaoFactory daoFactory;
	private Connection conn;
	
	protected ComputerDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		this.conn = daoFactory.getConnection();
	}

	@Override
	public void createComputer(Computer computer) {
		
		String query = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";
		PreparedStatement ps;
		ResultSet rs;
		
		try{
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, computer.getName());
			ps.setTimestamp(2, computer.getIntroduced());
			ps.setTimestamp(3, computer.getDiscontinued());
			ps.setLong(4, computer.getCompanyId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			computer.setId(rs.getLong(1));
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public Computer getComputer(long computerId) {
		
		String query = "select * from computer where id=?";
		ResultSet rs = null;
		
		try{
			conn.setAutoCommit(true);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			rs = ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ComputerMapper.map(rs);
	}

	@Override
	public void updateComputer(Computer computer) {
		
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, computer.getName());
			ps.setTimestamp(2, computer.getIntroduced());
			ps.setTimestamp(3, computer.getDiscontinued());
			ps.setLong(4, computer.getCompanyId());
			ps.setLong(5, computer.getId());
			ps.executeUpdate();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComputer(long computerId) {
		
		String query = "delete from computer where id=?";
		
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			ps.executeUpdate();
			conn.commit();
		}catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Computer> getComputers() {
		
		List<Computer> computerList = new ArrayList<>();
		
		String query = "select * from computer";
		ResultSet rs = null;
		
		try{
			conn.setAutoCommit(true);
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			while(rs.next()){
				computerList.add(ComputerMapper.map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return computerList;
	}
}
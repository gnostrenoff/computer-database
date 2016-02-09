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
	
	/**
	 * dao factory to get a connection
	 */
	private DaoFactory daoFactory;
	/**
	 * connection got from dao factory
	 */
	private Connection conn;
	
	protected ComputerDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		this.conn = daoFactory.getConnection();
	}

	@Override
	public void createComputer(Computer computer) {
		
		String query = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";
		conn = daoFactory.getConnection();
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
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Computer getComputer(long computerId) {
		
		String query = "select * from computer where id=?";
		conn = daoFactory.getConnection();
		Computer computer = null;
		ResultSet rs = null;
		
		try{
			conn.setAutoCommit(true);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, computerId);
			rs = ps.executeQuery();
			rs.next();
			computer = ComputerMapper.map(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return computer;
	}

	@Override
	public void updateComputer(Computer computer) {
		
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		conn = daoFactory.getConnection();
		
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
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteComputer(long computerId) {
		
		String query = "delete from computer where id=?";
		conn = daoFactory.getConnection();
		
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
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Computer> getComputers() {
		
		List<Computer> computerList = new ArrayList<>();		
		String query = "select * from computer";
		conn = daoFactory.getConnection();
		ResultSet rs = null;
		
		try{
			conn.setAutoCommit(true);
			Statement s = conn.createStatement();
			rs = s.executeQuery(query);
			while(rs.next()){
				computerList.add(ComputerMapper.map(rs));
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
		
		return computerList;
	}
}

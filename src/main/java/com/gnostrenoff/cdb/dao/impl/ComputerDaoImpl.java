package com.gnostrenoff.cdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.JDBCConnection;
import com.gnostrenoff.cdb.mappers.ComputerMapper;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

public class ComputerDaoImpl implements ComputerDao{
	
	private static ComputerDaoImpl computerDaoImpl;
	private JDBCConnection jdbcConnection;
	
	
	private ComputerDaoImpl() {
		this.jdbcConnection = JDBCConnection.getInstance();
	}
	
	public static ComputerDaoImpl getInstance(){
		if(computerDaoImpl == null){
			computerDaoImpl = new ComputerDaoImpl();
		}
		return computerDaoImpl;
	}

	@Override
	public void createComputer(Computer computer) {
		
		String query = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?);";
		Connection conn = jdbcConnection.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try{
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, computer.getName());
			
			LocalDateTime localDateTime = computer.getIntroduced();
			if(localDateTime != null){
				ps.setTimestamp(2, Timestamp.valueOf(localDateTime));
			}
			localDateTime = computer.getDiscontinued();
			if(localDateTime != null){
				ps.setTimestamp(3, Timestamp.valueOf(localDateTime));
			}
			
			Company company = computer.getCompany();
			if(company != null){
				ps.setLong(4, company.getId());
			}
			
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
		
		String query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
		Connection conn = jdbcConnection.getConnection();
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
		Connection conn = jdbcConnection.getConnection();
		
		try{
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, computer.getName());
			
			LocalDateTime localDateTime = computer.getIntroduced();
			if(localDateTime != null){
				ps.setTimestamp(2, Timestamp.valueOf(localDateTime));
			}
			localDateTime = computer.getDiscontinued();
			if(localDateTime != null){
				ps.setTimestamp(3, Timestamp.valueOf(localDateTime));
			}
			
			Company company = computer.getCompany();
			if(company != null){
				ps.setLong(4, company.getId());
			}
			
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
		Connection conn = jdbcConnection.getConnection();
		
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
		String query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
		Connection conn = jdbcConnection.getConnection();
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
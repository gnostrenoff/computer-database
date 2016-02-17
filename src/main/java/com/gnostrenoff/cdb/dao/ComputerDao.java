package com.gnostrenoff.cdb.dao;

import java.util.List;

import com.gnostrenoff.cdb.model.Computer;

/**
 * interface for computer entity DAO
 * @author excilys
 */
public interface ComputerDao {
	
	/**
	 * save the given computer in database
	 * @param computer to save
	 */
	public void create(Computer computer);
	
	/**
	 * retrieves a computer by id form database
	 * @param computerId id of computer to retrieve
	 * @return computer computer retrieved
	 */
	public Computer get(long computerId);
	
	/**
	 * load the complete list of existing computers in database
	 * @return list of computers
	 */
	public List<Computer> getList(int rowCount, int offset);
	
	/**
	 * updates the given computer in database
	 * @param computer computer to update
	 */
	public void update(Computer computer);
	
	/**
	 * deletes the computer with the given id
	 * @param computerId id of the computer to delete
	 */
	public void delete(long computerId);
	
	/**
	 * get row count of computer table
	 * @return row count
	 */
	public int count();

}

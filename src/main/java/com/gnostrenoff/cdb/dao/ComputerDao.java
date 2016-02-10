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
	public void createComputer(Computer computer);
	
	/**
	 * retrieves a computer by id form database
	 * @param computerId id of computer to retrieve
	 * @return computer computer retrieved
	 */
	public Computer getComputer(long computerId);
	
	/**
	 * load the complete list of existing computers in database
	 * @return list of computers
	 */
	public List<Computer> getComputers();
	
	/**
	 * updates the given computer in database
	 * @param computer computer to update
	 */
	public void updateComputer(Computer computer);
	
	/**
	 * deletes the computer with the given id
	 * @param computerId id of the computer to delete
	 */
	public void deleteComputer(long computerId);

}

package dao;

import java.util.List;

import model.Company;
import model.Computer;

public interface ComputerDao {
	
	public void createComputer(Computer computer);
	public Computer getComputer(long computerId);
	public List<Computer> getComputers();
	public void updateComputer(Computer computer);
	public void deleteComputer(long computerId);

}

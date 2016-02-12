package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.exceptions.DatesNotCorrectException;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.ComputerService;

/**
 * implementation of a computer service
 * @author excilys
 */
public class ComputerServiceImpl implements ComputerService{
	
	private static ComputerServiceImpl computerService;
	private static ComputerDao computerDao;
	
	private ComputerServiceImpl(){
		computerDao = ComputerDaoImpl.getInstance();
	}
	
	public static ComputerServiceImpl getInstance(){
		if(computerService == null){
			computerService = new ComputerServiceImpl();
		}
		return computerService;
	}

	@Override
	public void createComputer(Computer computer) {
		if(computer.getDiscontinued().isAfter(computer.getIntroduced())){
			computerDao.createComputer(computer);
		}
		else{
			throw new DatesNotCorrectException("computer cannot be discontinued before being introduced!");
		}
	}

	@Override
	public Computer getComputer(long computerId) {
		return computerDao.getComputer(computerId);
	}

	@Override
	public List<Computer> getComputers(int rowCount, int offset) {
		return computerDao.getComputers(rowCount,0);
	}

	@Override
	public void updateComputer(Computer computer) {
		if(computer.getDiscontinued().isAfter(computer.getIntroduced())){
			computerDao.updateComputer(computer);
		}
		else{
			throw new DatesNotCorrectException("computer cannot be discontinued before being introduced!");
		}
	}

	@Override
	public void deleteComputer(long computerId) {
		computerDao.deleteComputer(computerId);
	}

	@Override
	public int getRowCount() {
		return computerDao.getRowCount();
	}

}

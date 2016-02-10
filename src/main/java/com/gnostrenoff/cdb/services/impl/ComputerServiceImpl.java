package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.ComputerService;

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
		computerDao.createComputer(computer);
	}

	@Override
	public Computer getComputer(long computerId) {
		return computerDao.getComputer(computerId);
	}

	@Override
	public List<Computer> getComputers() {
		return computerDao.getComputers();
	}

	@Override
	public void updateComputer(Computer computer) {
		computerDao.updateComputer(computer);
	}

	@Override
	public void deleteComputer(long computerId) {
		computerDao.deleteComputer(computerId);
	}

}

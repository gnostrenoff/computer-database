package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.validator.ComputerValidator;

/**
 * implementation of a computer service
 * 
 * @author excilys
 */
public class ComputerServiceImpl implements ComputerService {

	private static ComputerServiceImpl computerService;
	private static ComputerDao computerDao;

	private ComputerServiceImpl() {
		computerDao = ComputerDaoImpl.getInstance();
	}

	public static ComputerServiceImpl getInstance() {
		if (computerService == null) {
			computerService = new ComputerServiceImpl();
		}
		return computerService;
	}

	@Override
	public void create(Computer computer) {

		ComputerValidator.validate(computer);
		computerDao.create(computer);

	}

	@Override
	public Computer get(long computerId) {
		return computerDao.get(computerId);
	}

	@Override
	public List<Computer> getList(QueryParams params) {
		return computerDao.getList(params);
	}

	@Override
	public void update(Computer computer) {
		ComputerValidator.validate(computer);
		computerDao.update(computer);
	}

	@Override
	public void delete(long computerId) {
		computerDao.delete(computerId);
	}

	@Override
	public int count() {
		return computerDao.count();
	}

}

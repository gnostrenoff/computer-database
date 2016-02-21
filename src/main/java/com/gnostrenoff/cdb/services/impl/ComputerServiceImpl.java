package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.exceptions.ComputerValidatorException;
import com.gnostrenoff.cdb.services.validator.ComputerValidator;

/**
 * implementation of a computer service
 * 
 * @author excilys
 */
public class ComputerServiceImpl implements ComputerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
	private static ComputerServiceImpl computerService = new ComputerServiceImpl();
	private static ComputerDao computerDao;

	private ComputerServiceImpl() {
		computerDao = ComputerDaoImpl.getInstance();
	}

	public static ComputerServiceImpl getInstance() {
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
		return computerDao.getList(params, null);
	}

	@Override
	public void update(Computer computer) {
		ComputerValidator.validate(computer);
		computerDao.update(computer);
	}

	@Override
	public void delete(long computerId) {
		if (computerId != 0)
			computerDao.delete(computerId, null);
		else{
			LOGGER.error("delete computer failed : invalid id");
			throw new ComputerValidatorException("delete computer failed : invalid id");
		}
	}

	@Override
	public int count(QueryParams params) {
		return computerDao.count(params);
	}

}

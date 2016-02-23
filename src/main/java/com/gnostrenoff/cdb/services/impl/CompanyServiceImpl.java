package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.utils.TransactionManager;

/**
 * implementation of a company service
 * 
 * @author excilys
 */
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
	private static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
	private static CompanyDao companyDao;

	private CompanyServiceImpl() {
		companyDao = CompanyDaoImpl.getInstance();
	}

	public static CompanyServiceImpl getInstance() {
		return companyServiceImpl;
	}

	@Override
	public List<Company> getList() {
		return companyDao.getList();
	}

	@Override
	public Company get(long companyId) {
		return companyDao.get(companyId);
	}

	@Override
	public void delete(long id) {

		TransactionManager tm = TransactionManager.getInstance();
		tm.startTransaction();
		
		//first delete the related computers
		ComputerDao computerDao = ComputerDaoImpl.getInstance();
		QueryParams params = new QueryParams(id);
		List<Computer> list = computerDao.getList(params);
		for(Computer computer : list){
			LOGGER.info("deleting related computer " + computer.getName() + "(" + computer.getId() + ")");
			computerDao.delete(computer.getId());
		}
		
		//then delete company
		LOGGER.info("deleting company " + id);
		companyDao.delete(id);
		
		tm.commitTransaction();
		tm.endTransaction();
	
	}

}

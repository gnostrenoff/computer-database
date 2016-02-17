package com.gnostrenoff.cdb.services.impl;

import java.util.List;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.services.CompanyService;

/**
 * implementation of a company service
 * 
 * @author excilys
 */
public class CompanyServiceImpl implements CompanyService {

	private static CompanyServiceImpl companyServiceImpl;
	private static CompanyDao companyDao;

	private CompanyServiceImpl() {
		companyDao = CompanyDaoImpl.getInstance();
	}

	public static CompanyServiceImpl getInstance() {
		if (companyServiceImpl == null) {
			companyServiceImpl = new CompanyServiceImpl();
		}
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

}

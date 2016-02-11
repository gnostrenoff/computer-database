package com.gnostrenoff.cdb.services;

import java.util.List;

import com.gnostrenoff.cdb.model.Company;

/**
 * service for companies
 * @author excilys
 */
public interface CompanyService {
	
	/**
	 * load the complete list of existing company in database
	 * @return list of computers
	 */
	public List<Company> getCompanies();

}

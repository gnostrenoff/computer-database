package com.gnostrenoff.cdb.dao;

import java.util.List;

import com.gnostrenoff.cdb.model.Company;

/**
 * interface for company entity DAO
 * @author excilys
 */
public interface CompanyDao {
	
	/**
	 * load the complete list of existing company in database
	 * @return list of computers
	 */
	public List<Company> getCompanies();

}

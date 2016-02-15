package com.gnostrenoff.cdb.dao;

import java.util.List;

import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;

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
	
	/**
	 * retrieves a company by id form database
	 * @param companyId id of company to retrieve
	 * @return company companyretrieved
	 */
	public Company getCompany(long companyId);

}

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
	public List<Company> getList();
	
	/**
	 * retrieves a company by id form database
	 * @param companyId id of company to retrieve
	 * @return company companyretrieved
	 */
	public Company get(long companyId);
	
	/**
	 * deletes a company
	 * @param id id of company to delete
	 * @param conn connection to use in case of a transaction is needed
	 */
	public void delete(long id);

}

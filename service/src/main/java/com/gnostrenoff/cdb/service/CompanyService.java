package com.gnostrenoff.cdb.service;

import com.gnostrenoff.cdb.model.Company;

import java.util.List;

/**
 * service for companies.
 *
 * @author excilys
 */
public interface CompanyService {

  /**
   * load the complete list of existing company in database.
   *
   * @return list of computers
   */
  public List<Company> getList();

  /**
   * retrieves a company by id form database.
   *
   * @param companyId          id of company to retrieve
   * @return company companyretrieved
   */
  public Company get(long companyId);

  /**
   * deletes a company, and all the computers related.
   *
   * @param id          id of company to delete
   */
  public void delete(long id);

}

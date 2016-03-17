package com.gnostrenoff.cdb.dao;

import com.gnostrenoff.cdb.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * interface for company entity DAO.
 *
 * @author excilys
 */
public interface CompanyDao extends JpaRepository<Company, Long> {

}

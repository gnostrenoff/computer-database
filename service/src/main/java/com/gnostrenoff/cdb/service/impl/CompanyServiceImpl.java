package com.gnostrenoff.cdb.service.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.service.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * implementation of a company service.
 *
 * @author excilys
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

  /** The company dao. */
  @Autowired
  private CompanyDao companyDao;

  /** The company dao. */
  @Autowired
  private ComputerDao computerDao;

  /**
   * Instantiates a new company service impl.
   */
  private CompanyServiceImpl() {
  }

  @Override
  public List<Company> getList() {
    return companyDao.findAll();
  }

  @Override
  public Company get(long companyId) {
    return companyDao.findOne(companyId);
  }

  @Override
  @Transactional
  public void delete(long id) {

    // first delete the related computers
    LOGGER.info("deleting the related computers");
    computerDao.deleteByCompany_Id(id);

    // then delete company
    LOGGER.info("deleting company " + id);
    companyDao.delete(id);

  }

}

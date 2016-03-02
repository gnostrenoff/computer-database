package com.gnostrenoff.cdb.service.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.util.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * implementation of a company service.
 *
 * @author excilys
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
  
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

    // first delete the related computers
    LOGGER.info("deleting the related computers");
    computerDao.deleteByCompanyId(id);

    // then delete company
    LOGGER.info("deleting company " + id);
    companyDao.delete(id);

    tm.commitTransaction();
    tm.endTransaction();

  }

}

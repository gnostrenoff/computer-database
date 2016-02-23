package com.gnostrenoff.cdb.services.impl;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.utils.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * implementation of a company service.
 *
 * @author excilys
 */
public class CompanyServiceImpl implements CompanyService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
  
  /** The company service impl. */
  private static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
  
  /** The company dao. */
  private static CompanyDao companyDao;

  /**
   * Instantiates a new company service impl.
   */
  private CompanyServiceImpl() {
    companyDao = CompanyDaoImpl.getInstance();
  }

  /**
   * Gets the single instance of CompanyServiceImpl.
   *
   * @return single instance of CompanyServiceImpl
   */
  public static CompanyServiceImpl getInstance() {
    return companyServiceImpl;
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.services.CompanyService#getList()
   */
  @Override
  public List<Company> getList() {
    return companyDao.getList();
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.services.CompanyService#get(long)
   */
  @Override
  public Company get(long companyId) {
    return companyDao.get(companyId);
  }

  /* (non-Javadoc)
   * @see com.gnostrenoff.cdb.services.CompanyService#delete(long)
   */
  @Override
  public void delete(long id) {

    TransactionManager tm = TransactionManager.getInstance();
    tm.startTransaction();

    // first delete the related computers
    ComputerDao computerDao = ComputerDaoImpl.getInstance();
    QueryParams params = new QueryParams(id);
    List<Computer> list = computerDao.getList(params);
    for (Computer computer : list) {
      LOGGER.info("deleting related computer " + computer.getName() + "(" + computer.getId() + ")");
      computerDao.delete(computer.getId());
    }

    // then delete company
    LOGGER.info("deleting company " + id);
    companyDao.delete(id);

    tm.commitTransaction();
    tm.endTransaction();

  }

}

package com.gnostrenoff.cdb.services.impl;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.exceptions.ComputerValidatorException;
import com.gnostrenoff.cdb.services.utils.ComputerValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * implementation of a computer service.
 *
 * @author excilys
 */
public class ComputerServiceImpl implements ComputerService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

  /** The computer service. */
  private static ComputerServiceImpl computerService = new ComputerServiceImpl();

  /** The computer dao. */
  private static ComputerDao computerDao;

  /**
   * Instantiates a new computer service impl.
   */
  private ComputerServiceImpl() {
    computerDao = ComputerDaoImpl.getInstance();
  }

  /**
   * Gets the single instance of ComputerServiceImpl.
   *
   * @return single instance of ComputerServiceImpl
   */
  public static ComputerServiceImpl getInstance() {
    return computerService;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.gnostrenoff.cdb.services.ComputerService#create(com.gnostrenoff.cdb.model.Computer)
   */
  @Override
  public void create(Computer computer) {

    ComputerValidator.validate(computer);
    computerDao.create(computer);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.gnostrenoff.cdb.services.ComputerService#get(long)
   */
  @Override
  public Computer get(long computerId) {
    return computerDao.get(computerId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.gnostrenoff.cdb.services.ComputerService#getList(com.gnostrenoff.cdb.model.QueryParams)
   */
  @Override
  public List<Computer> getList(QueryParams params) {
    return computerDao.getList(params);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.gnostrenoff.cdb.services.ComputerService#update(com.gnostrenoff.cdb.model.Computer)
   */
  @Override
  public void update(Computer computer) {
    ComputerValidator.validate(computer);
    computerDao.update(computer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.gnostrenoff.cdb.services.ComputerService#delete(long)
   */
  @Override
  public void delete(long computerId) {
    if (computerId != 0) {
      computerDao.delete(computerId);
    } else {
      LOGGER.error("delete computer failed : invalid id");
      throw new ComputerValidatorException("delete computer failed : invalid id");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.gnostrenoff.cdb.services.ComputerService#count(com.gnostrenoff.cdb.model.QueryParams)
   */
  @Override
  public int count(QueryParams params) {
    return computerDao.count(params);
  }

}

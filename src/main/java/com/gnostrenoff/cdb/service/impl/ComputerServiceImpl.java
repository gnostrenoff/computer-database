package com.gnostrenoff.cdb.service.impl;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ComputerValidatorException;
import com.gnostrenoff.cdb.service.util.ComputerValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * implementation of a computer service.
 *
 * @author excilys
 */
@Service("computerService")
public class ComputerServiceImpl implements ComputerService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);

  /** The computer dao. */
  @Autowired
  private ComputerDao computerDao;

  /**
   * Instantiates a new computer service impl.
   */
  private ComputerServiceImpl() {
  }

  @Override
  public void create(Computer computer) {

    ComputerValidator.validate(computer);
    computerDao.create(computer);

  }

  @Override
  public Computer get(long computerId) {
    return computerDao.get(computerId);
  }

  @Override
  public List<Computer> getList(QueryParams params) {
    return computerDao.getList(params);
  }

  @Override
  public void update(Computer computer) {
    ComputerValidator.validate(computer);
    computerDao.update(computer);
  }

  @Override
  public void delete(long computerId) {
    if (computerId != 0) {
      computerDao.delete(computerId);
    } else {
      LOGGER.error("delete computer failed : invalid id");
      throw new ComputerValidatorException("delete computer failed : invalid id");
    }
  }

  @Override
  public int count(String search) {
    return computerDao.count(search);
  }

}

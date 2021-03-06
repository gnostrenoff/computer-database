package com.gnostrenoff.cdb.service.impl;

import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.exception.ServiceValidatorException;
import com.gnostrenoff.cdb.service.util.ComputerValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

  @Autowired
  private ComputerValidator computerValidator;

  /**
   * Instantiates a new computer service impl.
   */
  private ComputerServiceImpl() {
  }

  @Override
  public void create(Computer computer) {

    computerValidator.validate(computer);
    computerDao.save(computer);

  }

  @Override
  public Computer get(long computerId) {
    return computerDao.findOne(computerId);
  }

  @Override
  public Page<Computer> getList(Pageable params, String search) {

    if (search != null && !search.isEmpty()) {
      return computerDao.findByNameAndCompanyLike(search, params);
    }
    return computerDao.findAll(params);
  }

  @Override
  public void update(Computer computer) {
    computerValidator.validate(computer);
    computerDao.save(computer);
  }

  @Override
  public void delete(long computerId) {
    if (computerId > 0) {
      computerDao.delete(computerId);
    } else {
      LOGGER.error("delete computer failed : invalid id");
      throw new ServiceValidatorException("delete computer failed : invalid id");
    }
  }

  @Override
  public long count() {
    return computerDao.count();
  }

}

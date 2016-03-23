package com.gnostrenoff.cdb.dao;

import com.gnostrenoff.cdb.model.Computer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * interface for computer entity DAO.
 *
 * @author excilys
 */
public interface ComputerDao extends JpaRepository<Computer, Long> {

  
  /**
   * custom methods to handle search parameter.
   *
   * @param searchParam the search param
   * @param pageable the pageable
   * @return the page
   */
  @Query("SELECT c FROM Computer c LEFT JOIN c.company cpy WHERE "
      + "(c.name LIKE %:searchParam% OR cpy.name LIKE %:searchParam% )")
  Page<Computer> findByNameAndCompanyLike(@Param("searchParam") String searchParam,
      Pageable pageable);

  void deleteByCompany_Id(@Param("id") long id);
}

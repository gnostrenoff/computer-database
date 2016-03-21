package com.gnostrenoff.cdb.dao;

import com.gnostrenoff.cdb.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface UserDao.
 */
public interface UserDao extends JpaRepository<User, Long> {
  
  User findByUsername(String username);
  
}

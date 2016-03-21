package com.gnostrenoff.cdb.service;

import com.gnostrenoff.cdb.model.User;

/**
 * service for users.
 *
 * @author excilys
 */
public interface UserService {

  /**
   * save the given user in database.
   *
   * @param computer          to save
   */
  public void create(User user);

  /**
   * retrieves a user by id form database.
   *
   * @param computerId          id of computer to retrieve
   * @return computer computer retrieved
   */
  public User get(long userId);

  /**
   * updates the given user in database.
   *
   * @param computer          computer to update
   */
  public void update(User user);

  /**
   * deletes the user with the given id.
   *
   * @param computerId          id of the computer to delete
   */
  public void delete(long userId);

}

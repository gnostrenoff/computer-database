package com.gnostrenoff.cdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Company.
 */
@Entity
@Table(name = "company")
public class Company {

  /** company id. */
  @Id
  private long id;

  /** company name. */
  @Column(name = "name")
  private String name;

  /**
   * Instantiates a new company.
   */
  public Company() {
    super();
  }

  /**
   * Instantiates a new company.
   *
   * @param id
   *          the id
   * @param name
   *          the name
   */
  public Company(long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name
   *          the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder compString = new StringBuilder(Long.toString(id));
    compString.append("\t - \t").append(name);

    return compString.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Company other = (Company) obj;
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}

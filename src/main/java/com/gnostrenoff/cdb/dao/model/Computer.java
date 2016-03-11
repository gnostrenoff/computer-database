package com.gnostrenoff.cdb.dao.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Class Computer.
 */
public class Computer implements Serializable {

  private static final long serialVersionUID = 4027018618232051841L;

  /** id of computer. */
  private long id;

  /** name of computer. */
  private String name;

  /** date computer was introduced. */
  private LocalDate introduced;

  /** date computer was discontinued. */
  private LocalDate discontinued;

  /** manufacturer. */
  private Company company;

  /**
   * Instantiates a new computer.
   */
  public Computer() {
  }

  public Computer(String name) {
    super();
    this.name = name;
  }

  /**
   * Instantiates a new computer.
   *
   * @param name
   *          the name
   * @param introduced
   *          the introduced
   * @param discontinued
   *          the discontinued
   * @param company
   *          the company
   */
  public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
  }

  /**
   * Instantiates a new computer.
   *
   * @param id
   *          the id
   * @param name
   *          the name
   * @param introduced
   *          the introduced
   * @param discontinued
   *          the discontinued
   * @param company
   *          the company
   */
  public Computer(long id, String name, LocalDate introduced, LocalDate discontinued,
      Company company) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
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
   * Gets the introduced.
   *
   * @return the introduced
   */
  public LocalDate getIntroduced() {
    return introduced;
  }

  /**
   * Sets the introduced.
   *
   * @param introduced
   *          the new introduced
   */
  public void setIntroduced(LocalDate introduced) {
    this.introduced = introduced;
  }

  /**
   * Gets the discontinued.
   *
   * @return the discontinued
   */
  public LocalDate getDiscontinued() {
    return discontinued;
  }

  /**
   * Sets the discontinued.
   *
   * @param discontinued
   *          the new discontinued
   */
  public void setDiscontinued(LocalDate discontinued) {
    this.discontinued = discontinued;
  }

  /**
   * Gets the company.
   *
   * @return the company
   */
  public Company getCompany() {
    return company;
  }

  /**
   * Sets the company.
   *
   * @param company
   *          the new company
   */
  public void setCompany(Company company) {
    this.company = company;
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced
        + ", discontinued=" + discontinued + ", company name="
        + ((company == null) ? "" : company.getName()) + "]";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Computer other = (Computer) obj;
    if (company == null) {
      if (other.company != null)
        return false;
    } else if (!company.equals(other.company))
      return false;
    if (discontinued == null) {
      if (other.discontinued != null)
        return false;
    } else if (!discontinued.equals(other.discontinued))
      return false;
    if (id != other.id)
      return false;
    if (introduced == null) {
      if (other.introduced != null)
        return false;
    } else if (!introduced.equals(other.introduced))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}

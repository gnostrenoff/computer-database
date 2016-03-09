package com.gnostrenoff.cdb.dto;

import com.gnostrenoff.cdb.controller.validator.ComputerDate;
import com.gnostrenoff.cdb.controller.validator.DateCoherence;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * The Class ComputerDto.
 */
@DateCoherence
public class ComputerDto {

  /** The id. */
  private long id;

  /** The name. */
  @NotNull
  @NotEmpty
  private String name;

  /** The introduced. */
  @ComputerDate
  private String introduced;

  /** The discontinued. */
  @ComputerDate
  private String discontinued;

  /** The company name. */
  private String companyName;

  /** The company id. */
  private long companyId;

  /**
   * Instantiates a new computer dto.
   */
  public ComputerDto() {
  }

  /**
   * Instantiates a new computer dto.
   *
   * @param name
   *          the name
   * @param introduced
   *          the introduced
   * @param discontinued
   *          the discontinued
   * @param companyName
   *          the company name
   * @param companyId
   *          the company id
   */
  public ComputerDto(String name, String introduced, String discontinued, String companyName,
      long companyId) {
    super();
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.companyName = companyName;
    this.companyId = companyId;
  }

  /**
   * Gets the company id.
   *
   * @return the company id
   */
  public long getCompanyId() {
    return companyId;
  }

  /**
   * Sets the company id.
   *
   * @param companyId
   *          the new company id
   */
  public void setCompanyId(long companyId) {
    this.companyId = companyId;
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
  public String getIntroduced() {
    return introduced;
  }

  /**
   * Sets the introduced.
   *
   * @param introduced
   *          the new introduced
   */
  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  /**
   * Gets the discontinued.
   *
   * @return the discontinued
   */
  public String getDiscontinued() {
    return discontinued;
  }

  /**
   * Sets the discontinued.
   *
   * @param discontinued
   *          the new discontinued
   */
  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  /**
   * Gets the company name.
   *
   * @return the company name
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Sets the company name.
   *
   * @param companyName
   *          the new company name
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
}

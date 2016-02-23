package com.gnostrenoff.cdb.model;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryParams.
 */
public class QueryParams {

  /**
   * index of page to get.
   */
  private int index;

  /**
   * number of elements per page.
   */
  private int nbElements;

  /**
   * offset.
   */
  private int offset;

  /**
   * company id.
   */
  private long companyId;

  /** search parameter. */
  private String search;

  /**
   * order by parameter.
   */
  private String orderBy;

  /**
   * order parameter.
   */
  private String order;

  /**
   * Instantiates a new query params.
   */
  public QueryParams() {
  }

  /**
   * Instantiates a new query params.
   *
   * @param companyId
   *          the company id
   */
  public QueryParams(long companyId) {
    this.companyId = companyId;
  }

  /**
   * Instantiates a new query params.
   *
   * @param index
   *          the index
   * @param nbElements
   *          the nb elements
   */
  public QueryParams(int index, int nbElements) {
    this.index = index;
    this.nbElements = nbElements;
  }

  /**
   * Instantiates a new query params.
   *
   * @param nbElements
   *          the nb elements
   * @param offset
   *          the offset
   * @param orderBy
   *          the order by
   * @param order
   *          the order
   */
  public QueryParams(int nbElements, int offset, String orderBy, String order) {
    this.nbElements = nbElements;
    this.offset = offset;
    this.orderBy = orderBy;
    this.order = order;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getNbElements() {
    return nbElements;
  }

  public void setNbElements(int nbElements) {
    this.nbElements = nbElements;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String name) {
    this.orderBy = name;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }
}

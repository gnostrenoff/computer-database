package com.gnostrenoff.cdb.model;

/**
 * The Class QueryParams.
 */
public class PageParams {

  private int index;

  /**
   * number of elements per page.
   */
  private int nbElements;

  /** total nulber of computers. */
  private int nbTotalComputers;

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
   * @param order
   *          the order.
   */
  public PageParams(int index, int nbElements, int offset, String orderBy, String order) {
    this.index = index;
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

  public int getNbTotalComputers() {
    return nbTotalComputers;
  }

  public void setNbTotalComputers(int nbTotalComputers) {
    this.nbTotalComputers = nbTotalComputers;
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

  @Override
  public String toString() {
    return "QueryParams [index=" + index + ", nbElements=" + nbElements + ", offset=" + offset
        + ", companyId=" + companyId + ", search=" + search + ", orderBy=" + orderBy + ", order="
        + order + "]";
  }
}

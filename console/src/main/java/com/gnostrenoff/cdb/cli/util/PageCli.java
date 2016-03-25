package com.gnostrenoff.cdb.cli.util;

import java.util.List;

/**
 * class model for Page.
 *
 * @author excilys
 */
public class PageCli {

  /** index of page. */
  private int index;

  /** number of elements in this page. */
  private int nbElements;

  /** number total of page. */
  private int nbTotalPages;
  
  /**  total nulber of computers. */
  private long nbTotalComputers;

  /** search parameter. */
  private String search;

  /** orderby parameter. */
  private String orderBy;

  /** order parameter. */
  private String order;

  /** start page. */
  private int pageStart;

  /** end page. */
  private int pageEnd;

  /** list of computers present in this page. */
  private List<ComputerCli> computerList;

  /**
   * Instantiates a new page dto.
   */
  public PageCli() {
  }

  /**
   * Instantiates a new page dto.
   *
   * @param index the index
   * @param nbElements the nb elements
   */
  public PageCli(int index, int nbElements) {
    super();
    this.index = index;
    this.nbElements = nbElements;
  }

  /**
   * Gets the index.
   *
   * @return the index
   */
  public int getIndex() {
    return index;
  }

  /**
   * Sets the index.
   *
   * @param index the new index
   */
  public void setIndex(int index) {
    this.index = index;
  }

  /**
   * Gets the nb elements.
   *
   * @return the nb elements
   */
  public int getNbElements() {
    return nbElements;
  }

  /**
   * Sets the nb elements.
   *
   * @param nbElements the new nb elements
   */
  public void setNbElements(int nbElements) {
    this.nbElements = nbElements;
  }

  /**
   * Gets the computer list.
   *
   * @return the computer list
   */
  public List<ComputerCli> getComputerList() {
    return computerList;
  }

  /**
   * Sets the computer list.
   *
   * @param comuterList the new computer list
   */
  public void setComputerList(List<ComputerCli> comuterList) {
    this.computerList = comuterList;
  }

  /**
   * Gets the nb total pages.
   *
   * @return the nb total pages
   */
  public int getNbTotalPages() {
    return nbTotalPages;
  }

  /**
   * Gets the nb total computers.
   *
   * @return the nb total computers
   */
  public long getNbTotalComputers() {
    return nbTotalComputers;
  }

  /**
   * Sets the nb total computers.
   *
   * @param nbTotalComputers the new nb total computers
   */
  public void setNbTotalComputers(long nbTotalComputers) {
    this.nbTotalComputers = nbTotalComputers;
  }

  /**
   * Sets the nb total pages.
   *
   * @param nbTotalPages the new nb total pages
   */
  public void setNbTotalPages(int nbTotalPages) {
    this.nbTotalPages = nbTotalPages;
  }

  /**
   * Gets the page start.
   *
   * @return the page start
   */
  public int getPageStart() {
    return pageStart;
  }

  /**
   * Sets the page start.
   *
   * @param pageStart the new page start
   */
  public void setPageStart(int pageStart) {
    this.pageStart = pageStart;
  }

  /**
   * Gets the page end.
   *
   * @return the page end
   */
  public int getPageEnd() {
    return pageEnd;
  }

  /**
   * Sets the page end.
   *
   * @param pageEnd the new page end
   */
  public void setPageEnd(int pageEnd) {
    this.pageEnd = pageEnd;
  }

  /**
   * Gets the search.
   *
   * @return the search
   */
  public String getSearch() {
    return search;
  }

  /**
   * Sets the search.
   *
   * @param search the new search
   */
  public void setSearch(String search) {
    this.search = search;
  }

  /**
   * Gets the order by.
   *
   * @return the order by
   */
  public String getOrderBy() {
    return orderBy;
  }

  /**
   * Sets the order by.
   *
   * @param orderBy the new order by
   */
  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  /**
   * Gets the order.
   *
   * @return the order
   */
  public String getOrder() {
    return order;
  }

  /**
   * Sets the order.
   *
   * @param order the new order
   */
  public void setOrder(String order) {
    this.order = order;
  }
}

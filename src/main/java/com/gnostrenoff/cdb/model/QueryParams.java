package com.gnostrenoff.cdb.model;

import org.springframework.data.domain.Pageable;

public class QueryParams {

  /** The page request. */
  private Pageable pageRequest;
  
  /** The search param. */
  private String searchParam;

  /**
   * Instantiates a new page params.
   *
   * @param pageRequest the page request
   * @param searchParam the search param
   */
  public QueryParams(Pageable pageRequest, String searchParam) {
    super();
    this.pageRequest = pageRequest;
    this.searchParam = searchParam;
  }

  /**
   * Gets the page request.
   *
   * @return the page request
   */
  public Pageable getPageRequest() {
    return pageRequest;
  }

  /**
   * Sets the page request.
   *
   * @param pageRequest the new page request
   */
  public void setPageRequest(Pageable pageRequest) {
    this.pageRequest = pageRequest;
  }

  /**
   * Gets the search param.
   *
   * @return the search param
   */
  public String getSearchParam() {
    return searchParam;
  }

  /**
   * Sets the search param.
   *
   * @param searchParam the new search param
   */
  public void setSearchParam(String searchParam) {
    this.searchParam = searchParam;
  }
  
}

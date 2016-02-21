package com.gnostrenoff.cdb.model;

public class QueryParams {
	
	/**
	 * index of page to get
	 */
	private int index;
	
	/**
	 * number of elements per page
	 */
	private int nbElements;
	
	/**
	 * offset
	 */
	private int offset;
	
	/**
	 * company id
	 */
	private long companyId;
	
	/**
	 * search parameter
	 */
	private String search;
	
	public QueryParams() {
	}
	
	public QueryParams(long companyId) {
		this.companyId = companyId;
	}
	
	public QueryParams(int index, int nbElements) {
		this.index = index;
		this.nbElements = nbElements;
	}
	
	public QueryParams(int index, int nbElements, String search) {
		this.index = index;
		this.nbElements = nbElements;
		this.search = search;
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
}

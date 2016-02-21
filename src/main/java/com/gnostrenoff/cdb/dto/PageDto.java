package com.gnostrenoff.cdb.dto;

import java.util.List;

/**
 * class model for Page
 * 
 * @author excilys
 *
 */
public class PageDto {

	/**
	 * index of page
	 */
	private int index;

	/**
	 * number of elements in this page
	 */
	private int nbElements;
	
	/**
	 * number total of page
	 */
	private int nbTotalPages;
	
	/**
	 * search parameter
	 */
	private String search;
	
	/**
	 * start page
	 */
	private int pageStart;
	
	/**
	 * end page
	 */
	private int pageEnd;
	
	/**
	 * list of computers present in this page
	 */
	private List<ComputerDto> computerList;

	public PageDto() {
	}

	public PageDto(int index, int nbElements) {
		super();
		this.index = index;
		this.nbElements = nbElements;
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

	public List<ComputerDto> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<ComputerDto> comuterList) {
		this.computerList = comuterList;
	}

	public int getNbTotalPages() {
		return nbTotalPages;
	}

	public void setNbTotalPages(int nbTotalPages) {
		this.nbTotalPages = nbTotalPages;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

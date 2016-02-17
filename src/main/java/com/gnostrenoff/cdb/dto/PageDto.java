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
}

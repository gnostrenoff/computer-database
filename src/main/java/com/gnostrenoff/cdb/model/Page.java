package com.gnostrenoff.cdb.model;

import java.util.List;

public class Page {

	private int index;
	private int nbElements;
	private List<Computer> comuterList;

	public Page() {
	}

	public Page(int index, int nbElements) {
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

	public List<Computer> getComuterList() {
		return comuterList;
	}

	public void setComuterList(List<Computer> comuterList) {
		this.comuterList = comuterList;
	}
}

package com.gnostrenoff.cdb.model;

public class QueryParams {
	
	private int index;
	private int nbElements;
	private int offset;
	
	public QueryParams(int index, int nbElements) {
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
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
}

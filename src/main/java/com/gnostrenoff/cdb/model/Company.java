package com.gnostrenoff.cdb.model;

public class Company {
	
	/**
	 * company id
	 */
	private long id;
	
	/**
	 * company name
	 */
	private String name;
	
	public Company() {
		super();
	}
	
	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String toString(){
		StringBuilder compString = new StringBuilder(Long.toString(id));
		compString.append("\t - \t").append(name);
		
		return compString.toString();
	}
}
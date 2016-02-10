package com.gnostrenoff.cdb.model;

import java.time.LocalDateTime;
import com.gnostrenoff.cdb.model.Company;

public class Computer {
	
	/**
	 * id of computer
	 */
	private long id;
	
	/**
	 * name of computer
	 */
	private String name;
	
	/**
	 * date computer was introduced
	 */
	private LocalDateTime introduced;
	
	/**
	 * date computer was discontinued
	 */
	private LocalDateTime discontinued;
	
	/**
	 * manufacturer
	 */
	private Company company;
	
	public Computer() {
	}
	
	public Computer(String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public Computer(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company name=" + company.getName() + "]";
	}

	
	

}

package com.gnostrenoff.cdb.dto;

public class ComputerDto {
	
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private String companyName;
	private long companyId;
	
	public ComputerDto() {
	}
	
	public ComputerDto(String name, String introduced, String discontinued, String companyName, long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
		this.companyId = companyId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIntroduced() {
		return introduced;
	}
	
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	
	public String getDiscontinued() {
		return discontinued;
	}
	
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}

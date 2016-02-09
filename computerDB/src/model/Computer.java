package model;

import java.sql.Timestamp;

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
	private Timestamp introduced;
	
	/**
	 * date computer was discontinued
	 */
	private Timestamp discontinued;
	
	/**
	 * id of manufacturer
	 */
	private long companyId;
	
	public Computer() {
		super();
	}
	
	public Computer(String name, Timestamp introduced, Timestamp discontinued, long companyId) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public Computer(long id, String name, Timestamp introduced, Timestamp discontinued, long companyId) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
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

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}

	
	

}

package com.cs.sparklesApp.model;

public class Employee {
	
	private int employeeNumber;
	private String lastName;
	private String firstName;
	private String extension;
	private String email;
	private String jobCode;
	private Office officeCode;
	private Employee reportsTo;
	
	public Employee(int employeeNumber) {
		super();
		this.employeeNumber = employeeNumber;
	}
	
	/** 
	 * All parameter cinstructor
	 * @param employeeNumber
	 * @param lastName
	 * @param firstName
	 * @param extension
	 * @param email
	 * @param jobCode
	 * @param officeCode
	 * @param reportsTo
	 */
		public Employee(int employeeNumber, String lastName, String firstName, String extension, String email,
			String jobCode, Office officeCode, Employee reportsTo) {
		super();
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.jobCode = jobCode;
		this.officeCode = officeCode;
		this.reportsTo = reportsTo;
	}
		/** 
		 * getter and setter methods
		 * 
		 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public Office getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(Office officeCode) {
		this.officeCode = officeCode;
	}
	public Employee getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	

}

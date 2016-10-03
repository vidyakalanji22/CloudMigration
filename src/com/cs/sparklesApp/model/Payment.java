package com.cs.sparklesApp.model;

import java.util.Date;

public class Payment {
	
	private Customer customerNumber;
	private String checkNumber;
	private Date paymentDate;
	private double amount;
	
	/**
	 * All parametr constructor
	 * @param customerNumber
	 * @param checkNumber
	 * @param paymentDate
	 * @param amount
	 */
	
	public Payment(Customer customerNumber, String checkNumber, Date paymentDate, double amount) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}
	
	/**
	 * getter and setter methods
	 * @return
	 */
	public Customer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}

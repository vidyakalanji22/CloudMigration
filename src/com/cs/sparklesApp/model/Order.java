package com.cs.sparklesApp.model;

import java.util.Date;

public class Order {
	private int orderNumber;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	private String comment;
	private Customer customerNumber;
	
	public Order(int orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}
	
	/**
	 * All parameter constructor
	 * @param orderNumber
	 * @param orderDate
	 * @param requiredDate
	 * @param shippedDate
	 * @param status
	 * @param comment
	 * @param customerNumber
	 */
	public Order(int orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status, String comment,
			Customer customerNumber) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comment = comment;
		this.customerNumber = customerNumber;
	}
	
	/**
	 * getter and setter methods
	 *
	 */
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Customer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	

}

package com.cs.sparklesApp.model;

public class OrderDetails {
	
	private int quantityOrdered;
	private double priceEach;
	private Short orderLineNumber;
	private Order orderNumber;
	private Product productCode;
	
	
	/**
	 * All parameter constructor
	 */
		
	public OrderDetails(int quantityOrdered, double priceEach, Short orderLineNumber, Order orderNumber,
			Product productCode) {
		super();
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
		this.orderNumber = orderNumber;
		this.productCode = productCode;
	}
	/**
	 * Getter and setter methods
	 * @return
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public double getPriceEach() {
		return priceEach;
	}
	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}
	public Short getOrderLineNumber() {
		return orderLineNumber;
	}
	public void setOrderLineNumber(Short orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	public Order getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Order orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Product getProductCode() {
		return productCode;
	}
	public void setProductCode(Product productCode) {
		this.productCode = productCode;
	}
	

}

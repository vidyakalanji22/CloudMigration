package com.cs.sparklesApp.model;

public class Product {
	
	private String productCode;
	private String productName;
	private ProductLine productLine;
	private String productScale;
	private String productVendor;
	private String productDescription;
	private Short quantityInStock;
	private double buyPrice;
	private double msrp;
	
	
	/**
	 * all parameter constructor
	 * @param productCode
	 * @param productName
	 * @param productLine
	 * @param productScale
	 * @param productVendor
	 * @param productDescription
	 * @param quantityInStock
	 * @param buyPrice
	 * @param msrp
	 */
	
	public Product(String productCode, String productName, ProductLine productLine, String productScale,
			String productVendor, String productDescription, Short quantityInStock, double buyPrice, double msrp) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
	}
	/**
	 * Getter and setter methods
	 * @param productCode
	 */
	public Product(String productCode) {
		super();
		this.productCode = productCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductLine getProductLine() {
		return productLine;
	}
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}
	public String getProductScale() {
		return productScale;
	}
	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Short getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(Short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getMsrp() {
		return msrp;
	}
	public void setMsrp(double msrp) {
		this.msrp = msrp;
	}

}

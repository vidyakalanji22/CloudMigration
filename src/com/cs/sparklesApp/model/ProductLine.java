package com.cs.sparklesApp.model;

import java.sql.Blob;

public class ProductLine {
	private String productLine;
	private String textDescription;
	private String htmlDescription;
	private Blob image;
	
	public ProductLine(String productLine) {
		super();
		this.productLine = productLine;
	}
	/**
	 * All parameter constructor
	 * @param productLine
	 * @param textDescription
	 * @param htmlDescription
	 * @param image
	 */
	public ProductLine(String productLine, String textDescription, String htmlDescription, Blob image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}
	/**
	 * Getter and setter methods
	 * 
	 */
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	public String getHtmlDescription() {
		return htmlDescription;
	}
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	
	
	
}

package com.innovation.daolife.model;

/**
 * DlProduct entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlProduct implements java.io.Serializable {

	// Fields

	private Short productId;
	private String productName;
	private String productUrl;
	private String productAuthor;
	private String productAuthorurl;
	private Short productSum;

	// Constructors

	/** default constructor */
	public DlProduct() {
	}

	/** full constructor */
	public DlProduct(Short productId, String productName, String productUrl,
			String productAuthor, String productAuthorurl, Short productSum) {
		this.productId = productId;
		this.productName = productName;
		this.productUrl = productUrl;
		this.productAuthor = productAuthor;
		this.productAuthorurl = productAuthorurl;
		this.productSum = productSum;
	}

	// Property accessors

	public Short getProductId() {
		return this.productId;
	}

	public void setProductId(Short productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return this.productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public String getProductAuthor() {
		return this.productAuthor;
	}

	public void setProductAuthor(String productAuthor) {
		this.productAuthor = productAuthor;
	}

	public String getProductAuthorurl() {
		return this.productAuthorurl;
	}

	public void setProductAuthorurl(String productAuthorurl) {
		this.productAuthorurl = productAuthorurl;
	}

	public Short getProductSum() {
		return this.productSum;
	}

	public void setProductSum(Short productSum) {
		this.productSum = productSum;
	}

}
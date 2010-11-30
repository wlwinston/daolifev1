package com.innovation.daolife.model;

import java.util.Date;
import java.util.Set;

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
	private String productPic;
	private String productTshirtPic;
	private Short productDaonum;
	private Integer productFollowsum;
	private Short productContentId;
	private Date productPosttime;
	private Short productSum;
	private DlContent dlContent;

	// Constructors

	/** default constructor */
	public DlProduct() {
	}

	/** full constructor */
	public DlProduct(Short productId, String productName, String productUrl,
			String productAuthor, String productAuthorurl, String productPic,
			String productTshirtPic,Short productDaonum, Date productPosttime, Short productSum) {
		this.productId = productId;
		this.productName = productName;
		this.productUrl = productUrl;
		this.productAuthor = productAuthor;
		this.productAuthorurl = productAuthorurl;
		this.productPic = productPic;
		this.productTshirtPic = productTshirtPic;
		this.productDaonum = productDaonum;
		this.productPosttime = productPosttime;
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

	public String getProductPic() {
		return this.productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public Short getProductDaonum() {
		return this.productDaonum;
	}

	public void setProductDaonum(Short productDaonum) {
		this.productDaonum = productDaonum;
	}

	public Date getProductPosttime() {
		return this.productPosttime;
	}

	public void setProductPosttime(Date productPosttime) {
		this.productPosttime = productPosttime;
	}

	public Short getProductSum() {
		return this.productSum;
	}

	public void setProductSum(Short productSum) {
		this.productSum = productSum;
	}

	public Integer getProductFollowsum() {
		return productFollowsum;
	}

	public void setProductFollowsum(Integer productFollowsum) {
		this.productFollowsum = productFollowsum;
	}

	public Short getProductContentId() {
		return productContentId;
	}

	public void setProductContentId(Short productContentId) {
		this.productContentId = productContentId;
	}

	public DlContent getDlContent() {
		return dlContent;
	}

	public void setDlContent(DlContent dlContent) {
		this.dlContent = dlContent;
	}

	public String getProductTshirtPic() {
		return productTshirtPic;
	}

	public void setProductTshirtPic(String productTshirtPic) {
		this.productTshirtPic = productTshirtPic;
	}

}
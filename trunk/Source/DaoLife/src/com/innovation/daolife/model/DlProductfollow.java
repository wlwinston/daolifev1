package com.innovation.daolife.model;

/**
 * DlProductfollow entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlProductfollow implements java.io.Serializable {

	// Fields

	private Short productfollowId;
	private Short productfollowProductid;
	private Short productfollowUserid;

	// Constructors

	/** default constructor */
	public DlProductfollow() {
	}

	/** full constructor */
	public DlProductfollow(Short productfollowProductid,
			Short productfollowUserid) {
		this.productfollowProductid = productfollowProductid;
		this.productfollowUserid = productfollowUserid;
	}

	// Property accessors

	public Short getProductfollowId() {
		return this.productfollowId;
	}

	public void setProductfollowId(Short productfollowId) {
		this.productfollowId = productfollowId;
	}

	public Short getProductfollowProductid() {
		return this.productfollowProductid;
	}

	public void setProductfollowProductid(Short productfollowProductid) {
		this.productfollowProductid = productfollowProductid;
	}

	public Short getProductfollowUserid() {
		return this.productfollowUserid;
	}

	public void setProductfollowUserid(Short productfollowUserid) {
		this.productfollowUserid = productfollowUserid;
	}

}
package com.innovation.daolife.model;

/**
 * DlUserroles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlUserroles implements java.io.Serializable {

	// Fields

	private Short userrolesId;
	private Short userId;
	private String rolesName;

	// Constructors

	/** default constructor */
	public DlUserroles() {
	}

	/** full constructor */
	public DlUserroles(Short userrolesId, Short userId, String rolesName) {
		this.userrolesId = userrolesId;
		this.userId = userId;
		this.rolesName = rolesName;
	}

	// Property accessors

	public Short getUserrolesId() {
		return this.userrolesId;
	}

	public void setUserrolesId(Short userrolesId) {
		this.userrolesId = userrolesId;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getRolesName() {
		return this.rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

}
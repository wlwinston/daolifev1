package com.innovation.daolife.model;

/**
 * DlArea entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlArea implements java.io.Serializable {

	// Fields

	private String areaId;
	private String areaName;
	private String parentId;
	private String ifSepecial;
	private String remark;

	// Constructors

	/** default constructor */
	public DlArea() {
	}

	/** minimal constructor */
	public DlArea(String areaId, String areaName, String parentId,
			String ifSepecial) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.parentId = parentId;
		this.ifSepecial = ifSepecial;
	}

	/** full constructor */
	public DlArea(String areaId, String areaName, String parentId,
			String ifSepecial, String remark) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.parentId = parentId;
		this.ifSepecial = ifSepecial;
		this.remark = remark;
	}

	// Property accessors

	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIfSepecial() {
		return this.ifSepecial;
	}

	public void setIfSepecial(String ifSepecial) {
		this.ifSepecial = ifSepecial;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
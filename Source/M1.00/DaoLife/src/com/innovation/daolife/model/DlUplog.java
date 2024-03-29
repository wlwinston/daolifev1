package com.innovation.daolife.model;

import java.util.Date;

/**
 * DlUplog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlUplog implements java.io.Serializable {

	// Fields

	private Short uplogId;
	private Short hotdaoId;
	private Short userId;
	private String upIp;
	private Date uptime;

	// Constructors

	/** default constructor */
	public DlUplog() {
	}

	/** full constructor */
	public DlUplog(Short uplogId, Short hotdaoId, Short userId, String upIp,
			Date uptime) {
		this.uplogId = uplogId;
		this.hotdaoId = hotdaoId;
		this.userId = userId;
		this.upIp = upIp;
		this.uptime = uptime;
	}

	// Property accessors

	public Short getUplogId() {
		return this.uplogId;
	}

	public void setUplogId(Short uplogId) {
		this.uplogId = uplogId;
	}

	public Short getHotdaoId() {
		return this.hotdaoId;
	}

	public void setHotdaoId(Short hotdaoId) {
		this.hotdaoId = hotdaoId;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getUpIp() {
		return this.upIp;
	}

	public void setUpIp(String upIp) {
		this.upIp = upIp;
	}

	public Date getUptime() {
		return this.uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

}
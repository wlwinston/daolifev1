package com.innovation.daolife.model;

import java.util.Set;

/**
 * DlContentat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlContentat implements java.io.Serializable {

	// Fields

	private Short contentatId;
	private Short contentId;
	private Short statusId;
	private Short statusUid;
	private String statusUname;
	private String statusType;
	private DlUsers dlUsers;
	// Constructors

	/** default constructor */
	public DlContentat() {
	}

	/** minimal constructor */
	public DlContentat(Short contentatId, Short contentId, Short statusId,
			Short statusUid, String statusUname) {
		this.contentatId = contentatId;
		this.contentId = contentId;
		this.statusId = statusId;
		this.statusUid = statusUid;
		this.statusUname = statusUname;
	}

	/** full constructor */
	public DlContentat(Short contentatId, Short contentId, Short statusId,
			Short statusUid, String statusUname, String statusType) {
		this.contentatId = contentatId;
		this.contentId = contentId;
		this.statusId = statusId;
		this.statusUid = statusUid;
		this.statusUname = statusUname;
		this.statusType = statusType;
	}

	// Property accessors

	public Short getContentatId() {
		return this.contentatId;
	}

	public void setContentatId(Short contentatId) {
		this.contentatId = contentatId;
	}

	public Short getContentId() {
		return this.contentId;
	}

	public void setContentId(Short contentId) {
		this.contentId = contentId;
	}

	public Short getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Short statusId) {
		this.statusId = statusId;
	}

	public Short getStatusUid() {
		return this.statusUid;
	}

	public void setStatusUid(Short statusUid) {
		this.statusUid = statusUid;
	}

	public String getStatusUname() {
		return this.statusUname;
	}

	public void setStatusUname(String statusUname) {
		this.statusUname = statusUname;
	}

	public String getStatusType() {
		return this.statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public DlUsers getDlUsers() {
		return dlUsers;
	}

	public void setDlUsers(DlUsers dlUsers) {
		this.dlUsers = dlUsers;
	}

}
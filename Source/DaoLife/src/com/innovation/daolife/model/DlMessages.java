package com.innovation.daolife.model;

import java.util.Date;

/**
 * DlMessages entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlMessages implements java.io.Serializable {

	// Fields

	private Short messageId;
	private Short userId;
	private String messageBody;
	private Date MTime;
	private Short isread;

	// Constructors

	/** default constructor */
	public DlMessages() {
	}

	/** full constructor */
	public DlMessages(Short messageId, Short userId, String messageBody,
			Date MTime, Short isread) {
		this.messageId = messageId;
		this.userId = userId;
		this.messageBody = messageBody;
		this.MTime = MTime;
		this.isread = isread;
	}

	// Property accessors

	public Short getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Short messageId) {
		this.messageId = messageId;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getMessageBody() {
		return this.messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public Date getMTime() {
		return this.MTime;
	}

	public void setMTime(Date MTime) {
		this.MTime = MTime;
	}

	public Short getIsread() {
		return this.isread;
	}

	public void setIsread(Short isread) {
		this.isread = isread;
	}

}
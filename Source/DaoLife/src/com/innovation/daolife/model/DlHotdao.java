package com.innovation.daolife.model;

import java.util.Date;

/**
 * DlHotdao entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlHotdao implements java.io.Serializable {

	// Fields

	private Short hotdaoId;
	private Short daonum;
	private String userName;
	private String contentBody;
	private Short retwittNum;
	private Date posttime;
	private Short upSum;
	private Short userId;
	private DlUsers dlUsers;

	// Constructors

	/** default constructor */
	public DlHotdao() {
	}

	/** full constructor */
	public DlHotdao(Short hotdaoId, Short daonum, String userName,
			String contentBody, Short retwittNum, Date posttime, Short upSum,Short userId) {
		this.hotdaoId = hotdaoId;
		this.daonum = daonum;
		this.userName = userName;
		this.contentBody = contentBody;
		this.retwittNum = retwittNum;
		this.posttime = posttime;
		this.upSum = upSum;
		this.userId = userId;
	}

	// Property accessors

	public Short getHotdaoId() {
		return this.hotdaoId;
	}

	public void setHotdaoId(Short hotdaoId) {
		this.hotdaoId = hotdaoId;
	}

	public Short getDaonum() {
		return this.daonum;
	}

	public void setDaonum(Short daonum) {
		this.daonum = daonum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContentBody() {
		return this.contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public Short getRetwittNum() {
		return this.retwittNum;
	}

	public void setRetwittNum(Short retwittNum) {
		this.retwittNum = retwittNum;
	}

	public Date getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public Short getUpSum() {
		return this.upSum;
	}

	public void setUpSum(Short upSum) {
		this.upSum = upSum;
	}

	public Short getUserId() {
		return userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public DlUsers getDlUsers() {
		return dlUsers;
	}

	public void setDlUsers(DlUsers dlUsers) {
		this.dlUsers = dlUsers;
	}

}
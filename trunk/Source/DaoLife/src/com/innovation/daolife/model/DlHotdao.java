package com.innovation.daolife.model;

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
	private Integer posttime;
	private Short upSum;

	// Constructors

	/** default constructor */
	public DlHotdao() {
	}

	/** full constructor */
	public DlHotdao(Short hotdaoId, Short daonum, String userName,
			String contentBody, Short retwittNum, Integer posttime, Short upSum) {
		this.hotdaoId = hotdaoId;
		this.daonum = daonum;
		this.userName = userName;
		this.contentBody = contentBody;
		this.retwittNum = retwittNum;
		this.posttime = posttime;
		this.upSum = upSum;
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

	public Integer getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Integer posttime) {
		this.posttime = posttime;
	}

	public Short getUpSum() {
		return this.upSum;
	}

	public void setUpSum(Short upSum) {
		this.upSum = upSum;
	}

}
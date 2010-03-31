package com.innovation.daolife.model;

/**
 * DlContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlContent implements java.io.Serializable {

	// Fields

	private Short contentId;
	private Short userId;
	private Short topicid;
	private String contentBody;
	private Integer posttime;
	private Short originId;
	private Short retwittNum;
	private String status;
	private String type;

	// Constructors

	/** default constructor */
	public DlContent() {
	}

	/** minimal constructor */
	public DlContent(Short contentId, Short userId, Short topicid,
			String contentBody, Integer posttime, Short retwittNum,
			String status, String type) {
		this.contentId = contentId;
		this.userId = userId;
		this.topicid = topicid;
		this.contentBody = contentBody;
		this.posttime = posttime;
		this.retwittNum = retwittNum;
		this.status = status;
		this.type = type;
	}

	/** full constructor */
	public DlContent(Short contentId, Short userId, Short topicid,
			String contentBody, Integer posttime, Short originId,
			Short retwittNum, String status, String type) {
		this.contentId = contentId;
		this.userId = userId;
		this.topicid = topicid;
		this.contentBody = contentBody;
		this.posttime = posttime;
		this.originId = originId;
		this.retwittNum = retwittNum;
		this.status = status;
		this.type = type;
	}

	// Property accessors

	public Short getContentId() {
		return this.contentId;
	}

	public void setContentId(Short contentId) {
		this.contentId = contentId;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public Short getTopicid() {
		return this.topicid;
	}

	public void setTopicid(Short topicid) {
		this.topicid = topicid;
	}

	public String getContentBody() {
		return this.contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public Integer getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Integer posttime) {
		this.posttime = posttime;
	}

	public Short getOriginId() {
		return this.originId;
	}

	public void setOriginId(Short originId) {
		this.originId = originId;
	}

	public Short getRetwittNum() {
		return this.retwittNum;
	}

	public void setRetwittNum(Short retwittNum) {
		this.retwittNum = retwittNum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
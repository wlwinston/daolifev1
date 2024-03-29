package com.innovation.daolife.model;

import java.util.Date;
import java.util.Set;

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
	//߶����
	private String contentBody;
	//߶�ύʱ��
	private Date posttime;
	//ԭ��ID
	private Short originId;
	private String originAllid;
	//ת����
	private Short retwittNum;
	//����
	private Short upNum;
	private Short replyNum;
	private String status;
	private String type;
	private String sinaTextId;
	public String getSinaTextId() {
		return sinaTextId;
	}

	public void setSinaTextId(String sinaTextId) {
		this.sinaTextId = sinaTextId;
	}

	//��߶����Ϣ
	private DlUsers dlUsers;
	private Set atUsers;
	private Set contentTopic;
	private Set dlComments;

	// Constructors

	/** default constructor */
	public DlContent() {
	}

	/** minimal constructor */
	public DlContent(Short contentId, Short userId, Short topicid,
			String contentBody, Date posttime, Short retwittNum,Short upNum,
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
			String contentBody, Date posttime, Short originId, String originAllid,
			Short retwittNum,Short upNum, String status, String type) {
		this.contentId = contentId;
		this.userId = userId;
		this.topicid = topicid;
		this.contentBody = contentBody;
		this.posttime = posttime;
		this.originId = originId;
		this.originAllid = originAllid;
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

	public Date getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Date posttime) {
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

	public String getOriginAllid() {
		return originAllid;
	}

	public void setOriginAllid(String originAllid) {
		this.originAllid = originAllid;
	}

	public Short getUpNum() {
		return upNum;
	}

	public void setUpNum(Short upNum) {
		this.upNum = upNum;
	}

	public DlUsers getDlUsers() {
		return dlUsers;
	}

	public void setDlUsers(DlUsers dlUsers) {
		this.dlUsers = dlUsers;
	}

	public Set getAtUsers() {
		return atUsers;
	}

	public void setAtUsers(Set atUsers) {
		this.atUsers = atUsers;
	}

	public Set getContentTopic() {
		return contentTopic;
	}

	public void setContentTopic(Set contentTopic) {
		this.contentTopic = contentTopic;
	}

	public Set getDlComments() {
		return dlComments;
	}

	public void setDlComments(Set dlComments) {
		this.dlComments = dlComments;
	}

	public Short getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Short replyNum) {
		this.replyNum = replyNum;
	}

}
package com.innovation.daolife.model;

import java.util.Date;
import java.util.Set;

/**
 * DlComment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlComment implements java.io.Serializable {

	// Fields

	private Short commentId;
	private Short contentId;
	private String commentBody;
	private Short relaCommentid;
	private Short relaUserid;
	private Short userId;
	private String status;
	private Date posttime;
	private DlUsers dlUsers;
	private DlContent dlContents;
	private DlComment reComment;
	private String originBody;

	// Constructors

	/** default constructor */
	public DlComment() {
	}

	/** minimal constructor */
	public DlComment(Short contentId, String commentBody, Short relaCommentid,
			Short userId, String status, Date posttime) {
		this.contentId = contentId;
		this.commentBody = commentBody;
		this.relaCommentid = relaCommentid;
		this.userId = userId;
		this.status = status;
		this.posttime = posttime;
	}

	/** full constructor */
	public DlComment(Short contentId, String commentBody, Short relaCommentid,
			Short relaUserid, Short userId, String status, Date posttime) {
		this.contentId = contentId;
		this.commentBody = commentBody;
		this.relaCommentid = relaCommentid;
		this.relaUserid = relaUserid;
		this.userId = userId;
		this.status = status;
		this.posttime = posttime;
	}

	// Property accessors

	public Short getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Short commentId) {
		this.commentId = commentId;
	}

	public Short getContentId() {
		return this.contentId;
	}

	public void setContentId(Short contentId) {
		this.contentId = contentId;
	}

	public String getCommentBody() {
		return this.commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public Short getRelaCommentid() {
		return this.relaCommentid;
	}

	public void setRelaCommentid(Short relaCommentid) {
		this.relaCommentid = relaCommentid;
	}

	public Short getRelaUserid() {
		return this.relaUserid;
	}

	public void setRelaUserid(Short relaUserid) {
		this.relaUserid = relaUserid;
	}

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPosttime() {
		return this.posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public DlContent getDlContents() {
		return dlContents;
	}

	public void setDlContents(DlContent dlContents) {
		this.dlContents = dlContents;
	}

	public DlUsers getDlUsers() {
		return dlUsers;
	}

	public void setDlUsers(DlUsers dlUsers) {
		this.dlUsers = dlUsers;
	}

	public DlComment getReComment() {
		return reComment;
	}

	public void setReComment(DlComment reComment) {
		this.reComment = reComment;
	}

	public String getOriginBody() {
		return originBody;
	}

	public void setOriginBody(String originBody) {
		this.originBody = originBody;
	}

	

}
package com.innovation.daolife.model;

/**
 * DlContenttopic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlContenttopic implements java.io.Serializable {

	// Fields

	private Short contenttopicId;
	private Short contentId;
	private Short topicId;

	// Constructors

	/** default constructor */
	public DlContenttopic() {
	}

	/** full constructor */
	public DlContenttopic(Short contenttopicId, Short contentId, Short topicId) {
		this.contenttopicId = contenttopicId;
		this.contentId = contentId;
		this.topicId = topicId;
	}

	// Property accessors

	public Short getContenttopicId() {
		return this.contenttopicId;
	}

	public void setContenttopicId(Short contenttopicId) {
		this.contenttopicId = contenttopicId;
	}

	public Short getContentId() {
		return this.contentId;
	}

	public void setContentId(Short contentId) {
		this.contentId = contentId;
	}

	public Short getTopicId() {
		return topicId;
	}

	public void setTopicId(Short topicId) {
		this.topicId = topicId;
	}

}
package com.innovation.daolife.model;

import java.util.Set;

/**
 * DlTopic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlTopic implements java.io.Serializable {

	// Fields

	private Short topicId;
	private String topicBody;
	private Short open;
	private Set topicContent;

	// Constructors

	/** default constructor */
	public DlTopic() {
	}

	/** full constructor */
	public DlTopic(Short topicId, String topicBody, Short open) {
		this.topicId = topicId;
		this.topicBody = topicBody;
		this.open = open;
	}

	// Property accessors

	public Short getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Short topicId) {
		this.topicId = topicId;
	}

	public String getTopicBody() {
		return this.topicBody;
	}

	public void setTopicBody(String topicBody) {
		this.topicBody = topicBody;
	}

	public Short getOpen() {
		return this.open;
	}

	public void setOpen(Short open) {
		this.open = open;
	}

	public Set getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(Set topicContent) {
		this.topicContent = topicContent;
	}

}
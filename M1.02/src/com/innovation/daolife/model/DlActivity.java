package com.innovation.daolife.model;

/**
 * DlActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlActivity implements java.io.Serializable {

	// Fields

	private Short activityId;
	private String activityName;
	private String activityDescription;
	private String activitySponsor;
	private String topicId;
	private Integer activityStartdate;
	private Integer activityEnddate;

	// Constructors

	/** default constructor */
	public DlActivity() {
	}

	/** minimal constructor */
	public DlActivity(Short activityId, String activityName,
			String activityDescription, String activitySponsor, String topicId,
			Integer activityStartdate) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activitySponsor = activitySponsor;
		this.topicId = topicId;
		this.activityStartdate = activityStartdate;
	}

	/** full constructor */
	public DlActivity(Short activityId, String activityName,
			String activityDescription, String activitySponsor, String topicId,
			Integer activityStartdate, Integer activityEnddate) {
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activitySponsor = activitySponsor;
		this.topicId = topicId;
		this.activityStartdate = activityStartdate;
		this.activityEnddate = activityEnddate;
	}

	// Property accessors

	public Short getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Short activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return this.activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getActivitySponsor() {
		return this.activitySponsor;
	}

	public void setActivitySponsor(String activitySponsor) {
		this.activitySponsor = activitySponsor;
	}

	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public Integer getActivityStartdate() {
		return this.activityStartdate;
	}

	public void setActivityStartdate(Integer activityStartdate) {
		this.activityStartdate = activityStartdate;
	}

	public Integer getActivityEnddate() {
		return this.activityEnddate;
	}

	public void setActivityEnddate(Integer activityEnddate) {
		this.activityEnddate = activityEnddate;
	}

}
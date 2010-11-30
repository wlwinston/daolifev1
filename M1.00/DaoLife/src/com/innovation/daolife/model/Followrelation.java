package com.innovation.daolife.model;

/**
 * Followrelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Followrelation implements java.io.Serializable {

	// Fields

	private Integer relationId;
	private Integer follower;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Followrelation() {
	}

	/** full constructor */
	public Followrelation(Integer follower, Integer userId) {
		this.follower = follower;
		this.userId = userId;
	}

	// Property accessors

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getFollower() {
		return this.follower;
	}

	public void setFollower(Integer follower) {
		this.follower = follower;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
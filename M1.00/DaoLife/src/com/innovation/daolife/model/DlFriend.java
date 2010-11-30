package com.innovation.daolife.model;

/**
 * DlFriend entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlFriend implements java.io.Serializable {

	// Fields

	private Short friId;
	private Short fidFollow;
	private Short fidFans;

	// Constructors

	/** default constructor */
	public DlFriend() {
	}

	/** full constructor */
	public DlFriend(Short friId, Short fidFollow, Short fidFans) {
		this.friId = friId;
		this.fidFollow = fidFollow;
		this.fidFans = fidFans;
	}

	// Property accessors

	public Short getFriId() {
		return this.friId;
	}

	public void setFriId(Short friId) {
		this.friId = friId;
	}

	public Short getFidFollow() {
		return this.fidFollow;
	}

	public void setFidFollow(Short fidFollow) {
		this.fidFollow = fidFollow;
	}

	public Short getFidFans() {
		return this.fidFans;
	}

	public void setFidFans(Short fidFans) {
		this.fidFans = fidFans;
	}

}
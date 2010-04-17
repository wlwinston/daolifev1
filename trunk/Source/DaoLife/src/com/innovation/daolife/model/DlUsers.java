package com.innovation.daolife.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * DlUsers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DlUsers implements java.io.Serializable {

	// Fields

	private Short userId;
	private String userName;
	private String userNickName;
	private String userUrl;
	private String password;
	private String salt;
	private String userHead;
	private String mailadres;
	private String homeCity;
	private String liveCity;
	private String birthday;
	private Date signupdate;
	private String userGender;
	private String userInfo;
	private Byte isclose;
	private Date lastLogin;
	private String musicaddr;
	private Short followNum;
	private Short fansNum;
	private Short photoNum;
	private Short atWeekNum;
	private Short atMonthNum;
	private Short atSumNum;
	private String recommendInd;
	private String userAddress;
	private String userPostcode;
	private Integer qq;
	private String msn;
	private String gtalk;
	private String authEmail;
	private Byte userlock;
	private Set dlContents;
	private Set dlFollowers;
	private Set dlFancers;
	//用户的角色列表
	private List<DlUserroles> userRolesList = null ;

	// Constructors

	/** default constructor */
	public DlUsers() {
	}

	/** minimal constructor */
	public DlUsers(Short userId, String userName,String userNickName, String password,
			String userHead, String mailadres, Date signupdate,
			String userInfo, Byte isclose, Date lastLogin, Short followNum,
			Short photoNum, String recommendInd, String authEmail, Byte userlock) {
		this.userId = userId;
		this.userName = userName;
		this.userNickName = userNickName;
		this.password = password;
		this.userHead = userHead;
		this.mailadres = mailadres;
		this.signupdate = signupdate;
		this.userInfo = userInfo;
		this.isclose = isclose;
		this.lastLogin = lastLogin;
		this.followNum = followNum;
		this.photoNum = photoNum;
		this.recommendInd = recommendInd;
		this.authEmail = authEmail;
		this.userlock = userlock;
	}

	/** full constructor */
	public DlUsers(Short userId, String userName, String userNickName, String userUrl,
			String password, String salt, String userHead, String mailadres,
			String homeCity, String liveCity, String birthday,
			Date signupdate, String userGender, String userInfo,
			Byte isclose, Date lastLogin, String musicaddr, Short followNum,
			Short fansNum, Short photoNum, Short atWeekNum, Short atMonthNum,
			Short atSumNum, String recommendInd,String userAddress,String userPostCode, Integer qq, String msn,
			String gtalk, String authEmail, Byte userlock) {
		this.userId = userId;
		this.userName = userName;
		this.userNickName = userNickName;
		this.userUrl = userUrl;
		this.password = password;
		this.salt = salt;
		this.userHead = userHead;
		this.mailadres = mailadres;
		this.homeCity = homeCity;
		this.liveCity = liveCity;
		this.birthday = birthday;
		this.signupdate = signupdate;
		this.userGender = userGender;
		this.userInfo = userInfo;
		this.isclose = isclose;
		this.lastLogin = lastLogin;
		this.musicaddr = musicaddr;
		this.followNum = followNum;
		this.fansNum = fansNum;
		this.photoNum = photoNum;
		this.atWeekNum = atWeekNum;
		this.atMonthNum = atMonthNum;
		this.atSumNum = atSumNum;
		this.recommendInd = recommendInd;
		this.userAddress = userAddress;
		this.userPostcode = userPostCode;
		this.qq = qq;
		this.msn = msn;
		this.gtalk = gtalk;
		this.authEmail = authEmail;
		this.userlock = userlock;
	}

	// Property accessors

	public Short getUserId() {
		return this.userId;
	}

	public void setUserId(Short userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserUrl() {
		return this.userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserHead() {
		return this.userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	public String getMailadres() {
		return this.mailadres;
	}

	public void setMailadres(String mailadres) {
		this.mailadres = mailadres;
	}

	public String getHomeCity() {
		return this.homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getLiveCity() {
		return this.liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Date getSignupdate() {
		return this.signupdate;
	}

	public void setSignupdate(Date signupdate) {
		this.signupdate = signupdate;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Byte getIsclose() {
		return this.isclose;
	}

	public void setIsclose(Byte isclose) {
		this.isclose = isclose;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getMusicaddr() {
		return this.musicaddr;
	}

	public void setMusicaddr(String musicaddr) {
		this.musicaddr = musicaddr;
	}

	public Short getFollowNum() {
		return this.followNum;
	}

	public void setFollowNum(Short followNum) {
		this.followNum = followNum;
	}

	public Short getFansNum() {
		return this.fansNum;
	}

	public void setFansNum(Short fansNum) {
		this.fansNum = fansNum;
	}

	public Short getPhotoNum() {
		return this.photoNum;
	}

	public void setPhotoNum(Short photoNum) {
		this.photoNum = photoNum;
	}

	public Short getAtWeekNum() {
		return this.atWeekNum;
	}

	public void setAtWeekNum(Short atWeekNum) {
		this.atWeekNum = atWeekNum;
	}

	public Short getAtMonthNum() {
		return this.atMonthNum;
	}

	public void setAtMonthNum(Short atMonthNum) {
		this.atMonthNum = atMonthNum;
	}

	public Short getAtSumNum() {
		return this.atSumNum;
	}

	public void setAtSumNum(Short atSumNum) {
		this.atSumNum = atSumNum;
	}

	public String getRecommendInd() {
		return this.recommendInd;
	}

	public void setRecommendInd(String recommendInd) {
		this.recommendInd = recommendInd;
	}

	public Integer getQq() {
		return this.qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getGtalk() {
		return this.gtalk;
	}

	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	public String getAuthEmail() {
		return this.authEmail;
	}

	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}

	public Byte getUserlock() {
		return this.userlock;
	}

	public void setUserlock(Byte userlock) {
		this.userlock = userlock;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPostcode() {
		return userPostcode;
	}

	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public List<DlUserroles> getUserRolesList() {
		return userRolesList;
	}

	public void setUserRolesList(List<DlUserroles> userRolesList) {
		this.userRolesList = userRolesList;
	}

	public Set getDlContents() {
		return dlContents;
	}

	public void setDlContents(Set dlContents) {
		this.dlContents = dlContents;
	}

	public Set getDlFollowers() {
		return dlFollowers;
	}

	public void setDlFollowers(Set dlFollowers) {
		this.dlFollowers = dlFollowers;
	}

	public Set getDlFancers() {
		return dlFancers;
	}

	public void setDlFancers(Set dlFancers) {
		this.dlFancers = dlFancers;
	}

}
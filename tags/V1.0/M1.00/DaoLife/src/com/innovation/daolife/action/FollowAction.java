package com.innovation.daolife.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.innovation.common.util.Constant;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IFollowrelationService;
import com.opensymphony.xwork2.ActionSupport;

public class FollowAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private static String FOLLOWSUCCESS = "followSuccess";
	private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
	private Short followId ;
	private IFollowrelationService followrelationService;
	//private IDlDaoService dlDaoService;

	public String follow(){
		DlUsers nowuser = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		//String followId = request
		DlFriend friend = new DlFriend();
		friend.setFidFollow(this.followId);
		friend.setFidFans(nowuser.getUserId());
		followrelationService.addFollow(friend);
		
		return FOLLOWSUCCESS;
	}
	
	public String unFollow(){
		DlUsers nowuser = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		//String followId = request
		DlFriend friend = new DlFriend();
		friend.setFidFollow(this.followId);
		friend.setFidFans(nowuser.getUserId());
		followrelationService.deleteFollow(friend);
		
		return FOLLOWSUCCESS;
	}

	public void setSession(Map att) {
        this.att = att;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

	public IFollowrelationService getFollowrelationService() {
		return followrelationService;
	}

	public void setFollowrelationService(
			IFollowrelationService followrelationService) {
		this.followrelationService = followrelationService;
	}

	public Short getFollowId() {
		return followId;
	}

	public void setFollowId(Short followId) {
		this.followId = followId;
	}
	
//	public void setDlDaoService(IDlDaoService dlDaoService) {
//		this.dlDaoService = dlDaoService;
//	}
//
//	public IDlDaoService getDlDaoService() {
//		return dlDaoService;
//	}

}

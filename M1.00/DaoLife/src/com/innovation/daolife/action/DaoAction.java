/** 
 *
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.mail.EmailException;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.dao.DataAccessException;

import com.innovation.common.util.Constant;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlFriendDao;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IFollowrelationService;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class DaoAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private static String DAOPOSTSUCCESS = "daoPostSuccess";
	private static String FOLLOWSUCCESS = "followSuccess";
	private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
	private String contextBody ;
	private Short followId ;
	//private IDlDaoService dlDaoService;
	private IFollowrelationService followrelationService;
	private IDlDaoService dlDaoService;
	private Short orgDaoId;
	
	
	public String post() throws Exception {
		DlUsers user = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		dlDaoService.addDao(user,contextBody);
		return DAOPOSTSUCCESS;
	}
	

	public String retwitte() throws Exception {
		DlUsers user = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		dlDaoService.addRetwitteDao(user,contextBody,orgDaoId);
		return DAOPOSTSUCCESS;
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
	




	public void setDlDaoService(IDlDaoService dlDaoService) {
		this.dlDaoService = dlDaoService;
	}




	public String getContextBody() {
		return contextBody;
	}

	


	public void setContextBody(String contextBody) {
		this.contextBody = contextBody;
	}

	public Short getOrgDaoId() {
		return orgDaoId;
	}

	public void setOrgDaoId(Short orgDaoId) {
		this.orgDaoId = orgDaoId;
	}

	public IFollowrelationService getFollowrelationService() {
		return followrelationService;
	}

	public void setFollowrelationService(
			IFollowrelationService followrelationService) {
		this.followrelationService = followrelationService;
	}

}

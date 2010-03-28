/** 
 *
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private static String REGISTERSUCCESS = "registerSuccess";
	private static String USERLIST = "list";
	private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
	private DlUsers user;
	private PaginationSupport paginationSupport ;
	private UserSearch userSearch;
	private IUserService userService;
	private String userName;
	private String password;
	private static String LOGINSUCCESS = "loginSuccess";
	private static String LOGINFAILURE = "loginFailure";

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String regist() throws Exception{
		userService.regist(user);
		att.put("user", user);
		return REGISTERSUCCESS;
	}
	
	
	public String userList() throws Exception{
		if(paginationSupport == null)
		{
			paginationSupport = new PaginationSupport(1, 1);
		}
		if(userSearch == null)
		{
			userSearch = new UserSearch();
			userSearch.setPassword("123456");
			
		}
		HttpSession sess = request.getSession();
		//sess.getMaxInactiveInterval()
		paginationSupport = userService.getListBySearch(paginationSupport,userSearch);
		request.setAttribute("paginationSupport", paginationSupport);
		request.setAttribute("userSearch", userSearch);
		return USERLIST;
	}
	
	/**
	 * @ fengsn
	 * 用户登录校验
	 * */
	public String login() throws Exception{
		DlUsers dlUser = userService.getUserByNameOrEmail(userName);
		if(dlUser==null){
			request.setAttribute("ErrorInfo", "用户不存在，请重新输入!");
			return LOGINFAILURE;
		}
		String salt = dlUser.getSalt();
		String oldpasswd = dlUser.getPassword();
		boolean flag = Md5Util.getInstance().checkpassword(password, salt, oldpasswd);
		if(!flag){
			request.setAttribute("ErrorInfo", "密码错误，请重新输入!");
			return LOGINFAILURE;
		}else{
			att.put("user", dlUser);
			return LOGINSUCCESS;
		}
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
	public PaginationSupport getPaginationSupport() {
		return paginationSupport;
	}
	public void setPaginationSupport(PaginationSupport paginationSupport) {
		this.paginationSupport = paginationSupport;
	}
	public UserSearch getUserSearch() {
		return userSearch;
	}
	public void setUserSearch(UserSearch userSearch) {
		this.userSearch = userSearch;
	}
	public DlUsers getUser() {
		return user;
	}
	public void setUser(DlUsers user) {
		this.user = user;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

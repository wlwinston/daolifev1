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

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private static String REGISTERSUCCESS = "registerSuccess";
	private static String USERLIST = "list";
	private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
	private User user;
	private PaginationSupport paginationSupport ;
	private UserSearch userSearch;
	private IUserService userService;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String register() throws Exception{
		userService.addUser(user);
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
}

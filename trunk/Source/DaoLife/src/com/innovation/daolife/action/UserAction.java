/** 
 *
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.innovation.common.util.Constant;
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
	private Short userId;
	private String authCode;
	private String newPassword;
	private String newPasswordConfirm;
	private static String LOGINSUCCESS = "loginSuccess";
	private static String LOGINFAILURE = "loginFailure";
	private static String FINDPASSWORDSUCCESS = "findPasswordSuccess";
	private static String RESETCHECKSUCCESS = "resetCheckSuccess";
	private static String RESETCHECKFAILUE ="resetCheckFailue";
	private static String SETNEWPASSWORDSUCCESS = "setNewPasswordSuccess";
	private static String SETNEWPASSWORDFAILUE ="setNewPasswordFailue";

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String regist() throws Exception{
		userService.regist(user);
		att.put("user", user);
		return REGISTERSUCCESS;
	}
	
	public String resetPassword(){
		if( userId == null || authCode == null)
		{
			return RESETCHECKFAILUE;
		}
		boolean flag = userService.checkAuthCode(userId,authCode);
		if(flag){
			request.setAttribute("userId", userId);
			request.setAttribute("newPassword", "newPassword");
			return RESETCHECKSUCCESS;
		}
		else{
			return RESETCHECKFAILUE;
		}
	}
	
	public String resetPasswordSave() throws NoSuchAlgorithmException, UnsupportedEncodingException, EmailException{
		String forward = SETNEWPASSWORDFAILUE;
		if(newPassword != null && newPasswordConfirm != null)
		{
			if(newPassword.equals(newPasswordConfirm))
			{
				if(userId != null)
				{
					userService.resetPassword(userId,newPassword);
					forward = SETNEWPASSWORDSUCCESS;
				}
			}
			else{
				request.setAttribute("ErrorInfo", Constant.ERROR_RETPASSWORD_PASSWORDCONFIRMERROR.getStrValue());
			}
		}
		else{
			request.setAttribute("ErrorInfo", Constant.ERROR_RETPASSWORD_NOPASSWORD.getStrValue());
		}
		return forward;
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
	
	public String findPassword() throws EmailException {
		userService.resetPasswordEmail(userName);
		return FINDPASSWORDSUCCESS;
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
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public Short getUserId() {
		return userId;
	}
	public void setUserId(Short userId) {
		this.userId = userId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

}

/** 
 *
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.innovation.common.util.Constant;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.WebConfig;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlArea;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlAreaService;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private static String REGISTERSUCCESS = "registerSuccess";
	private static String REGISTERFAILURE="registerFailure";
	private static String USERLIST = "list";
	private Map att;
	private String perUrl ;
    private HttpServletRequest request;
    private HttpServletResponse response; 
	private DlUsers user;
	private DlUsers personalInfo;
	private DlUsers updateUser;
	private PaginationSupport paginationSupport ;
	private UserSearch userSearch;
	private IUserService userService;
	private IDlAreaService dlAreaService;
	private String userName;
	private String findPwUserName;
	private String password;
	private String newpassword;
	private Short userId;
	private List areaInfo = new ArrayList();
	private String province;
	private String city;
	private String province_select;
	private String city_select;
	private List provinceList = new ArrayList();
	private List cityList = new ArrayList();
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
	private static String MEMBERINFO ="memberInfo";
	private static String MYPAGE = "myPage";
	private static String NOPERSON = "noPerson";
	private static String PERSONPAGE = "personPage";
	private static String NOLOGIN = "unLogin";
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String regist() throws Exception{
		try{
		userService.regist(user);
		att.put(Constant.SESSION_USER_KEY.getStrValue(), user);
		}
		catch(Exception e){
			return REGISTERFAILURE;
		}
		return REGISTERSUCCESS;
	}
	
	public String logout() throws Exception{
		request.getSession().setAttribute(Constant.SESSION_USER_KEY.getStrValue(), null);
		request.getSession().removeAttribute(Constant.SESSION_USER_KEY.getStrValue());
		//清除cookie
		Cookie   userNameCookie   =   new   Cookie( "daolife_userName", null);
		  Cookie   authCodeCookie   =   new   Cookie( "daolife_authCode", null);
		  Cookie   loginTimeCookie   =   new   Cookie( "daolife_loginTime", null);
		  userNameCookie.setPath("/");
		  userNameCookie.setDomain(WebConfig.cookieDomain);
		  userNameCookie.setMaxAge(0);
		  authCodeCookie.setPath("/");
		  authCodeCookie.setDomain(WebConfig.cookieDomain);
		  authCodeCookie.setMaxAge(0);
		  loginTimeCookie.setDomain(WebConfig.cookieDomain);
		  loginTimeCookie.setPath("/");
		  loginTimeCookie.setMaxAge(0);
		  response.addCookie(userNameCookie);
		  response.addCookie(authCodeCookie);
		  response.addCookie(loginTimeCookie);
		return "clearSuccess";
	}
	
	/**
	 * @author fsn
	 * 修改个人信息
	 * */
	public String update() throws Exception{
		DlUsers oldUserInfo = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		updateUser.setHomeCity(city);
		userService.update(updateUser,oldUserInfo);
		att.put("user", updateUser);
		return "updateSuccess";
	}
	
	public String initUpdate() throws Exception{
		DlUsers oldUserInfo = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		updateUser = userService.getUsersById(oldUserInfo.getUserId());
		String cityId = updateUser.getHomeCity();
		
		if(cityId!=null&&!cityId.equals("")){
			DlArea tmp_province = dlAreaService.getProvinceByCity(cityId);
			province=tmp_province.getParentId();
			city=cityId;
			provinceList = dlAreaService.getDlAreaListInfo("000001");
//			provinceList.add(Province);
			cityList = dlAreaService.getDlAreaListInfo(province);
//			cityList.add(City);
		}else{
			province="000002";//默认北京市
			city="000010";//默认海淀区
			provinceList = dlAreaService.getDlAreaListInfo("000001");
//			provinceList.add(Province);
			cityList = dlAreaService.getDlAreaListInfo("000002");
//			cityList.add(City);
		}
		return "initUpdateSuccess";
	}
	
	/**
	 * @author fsn
	 * 修改密码
	 * */
	public String updatePsw() throws Exception{
		DlUsers oldUserInfo = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		userService.updatePsw(oldUserInfo,newpassword);
		return "updatePswSuccess";
	}
	
	public String updatePrepare() throws Exception{
		DlUsers UserInfo = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		updateUser = UserInfo;
		return "updatePrepareSuccess";
	}
	
	public String attentionMe(){
		return "attentionMeSuccess";
	}
	
	public String daoHot(){
		return "daoHotSuccess";
	}
	
	public String friendsHot(){
		return "friendsHotSuccess";
	}
	
	public String followMe(){
		return "followMeSuccess";
	}
	
	public String myPage() throws Exception{
		DlUsers UserInfo = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		//updateUser = UserInfo;
		if(UserInfo!=null){
			int size = userService.getUserDao(UserInfo.getUserId()).size();
			UserInfo.setContentsSize(size);
//			att.put(Constant.SESSION_USER_KEY.getStrValue(), UserInfo);
//			att.put(Constant.SESSION_USERDAOS_KEY.getStrValue(), size);
			return "myPageSuccess";
		}else{
			return "forbidden";
		}
		//return "myPageSuccess";
	}

	public String resetPassword(){
		//判断用户和验证码是否为空
		if( userId == null || authCode == null)
		{
			return RESETCHECKFAILUE;
		}
		//对验证码进行校验
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
		userService.resetPasswordEmail(findPwUserName);
		return FINDPASSWORDSUCCESS;
	}
	
	public String memberInfo() throws Exception{
		if(paginationSupport == null)
		{
			paginationSupport = new PaginationSupport(1, 1);
		}
		DlUsers user = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		Short uid = user.getUserId();
		//sess.getMaxInactiveInterval()
		paginationSupport = userService.getFollowerContentListByUser(paginationSupport, uid);
		return MEMBERINFO;
	}
	
	public String myDaoList() throws Exception{
		if(paginationSupport == null)
		{
			paginationSupport = new PaginationSupport(1, 1);
		}
		DlUsers user = (DlUsers) att.get(Constant.SESSION_USER_KEY.getStrValue());
		Short uid = user.getUserId();
		//sess.getMaxInactiveInterval()
		paginationSupport = userService.getContentListByUser(paginationSupport, uid);
		return MEMBERINFO;
	}
	/**
	 * 个人页面
	 * @return
	 * @throws Exception
	 */
	public String personalPage() throws Exception{
		ActionContext context = ActionContext.getContext();   
	    HttpServletRequest myRequest = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);   
	    Map session = context.getSession();   
	    if(session.get(Constant.SESSION_USER_KEY.getStrValue()) == null)
	    {
	    	if(!this.checkCookie(myRequest,session))
	    	{
	    		return NOLOGIN;
	    	}
	    }
		DlUsers myUser = (DlUsers) session.get(Constant.SESSION_USER_KEY.getStrValue());
		
		Short uid = myUser.getUserId();
		if(userId == null)
		{
			//String actionUrl = myRequest.getServletPath();
			//if(actionUrl.length() > 1)
			//{
				//actionUrl = actionUrl.substring(1, actionUrl.length());
				//if(actionUrl.indexOf("//")<0)
				//{
					//System.out.println("HHHHHHHHH=========================================="+request.getContextPath());
					String actionUrl = URLDecoder.decode(perUrl,"UTF-8");
					personalInfo = userService.getUserByUrl(actionUrl);
					if(personalInfo != null)
					{
						userId = personalInfo.getUserId();
					}
					else{
						return NOPERSON;
					}
				//}
				//else{
				//	return NOPERSON;
				//}
			//}
			//else{
				//return NOPERSON;
			//}
		}
		else{
			personalInfo = userService.getUsersById(userId);
		}
			if(userId.equals(uid))
			{
				return MYPAGE;
			}
			else{
				if(personalInfo == null)
				{
					return NOPERSON;
				}
			}
		
		//sess.getMaxInactiveInterval()
		
		return PERSONPAGE;
	}
	private boolean checkCookie(HttpServletRequest myRequest, Map session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Cookie   cookies[]=request.getCookies(); 
		Cookie   sCookie=null; 
		String   svalue=null; 
		String   sname=null; 
		String   userName = null;
		String   authCode = null;
		if(cookies != null)
		{
			for(int   i=0;i <cookies.length;i++) 
			{ 
				sCookie=cookies[i]; 
				svalue=sCookie.getValue(); 
				sname=sCookie.getName(); 
				if(sname.equals("daolife_userName"))
				{
					userName = svalue;
				}
				if(sname.equals("daolife_authCode"))
				{
					authCode = svalue;
				}
			}
			if(userName != null && authCode != null)
			{
				Short uid = Short.valueOf(userName);
				DlUsers dlUser = userService.getUsersById(uid);
				if(dlUser != null){
					String salt = dlUser.getSalt();
					String oldpasswd = dlUser.getPassword();
					String oldAuthCode = Md5Util.getInstance().EncoderByMd5(userName+oldpasswd,salt);
					if(authCode.equals(oldAuthCode))
					{
						List<DlUserroles> userRolesList = userService.getRolesListByUserId(dlUser.getUserId());
						dlUser.setUserRolesList(userRolesList);
						int size = userService.getUserDao(dlUser.getUserId()).size();
						dlUser.setContentsSize(size);
						session.put(Constant.SESSION_USER_KEY.getStrValue(), dlUser);
						return true;
					}
					
				}
			}
		} 
		return false;
	}
	/**
	 * @ fengsn
	 * 登录
	 * */
	public String login() throws Exception{
		DlUsers dlUser = userService.getUserByNameOrEmail(userName);
//		PaginationSupport rePaginationSupport = new PaginationSupport();
//		rePaginationSupport.setPageSize(10);
//		rePaginationSupport.setStartIndex(1);
//		userService.getFollowerContentListByUser(rePaginationSupport,dlUser.getUserId());
		if(dlUser==null){
			request.setAttribute("ErrorInfo", "用户不存在!");
			return LOGINFAILURE;
		}
		
		String salt = dlUser.getSalt();
		String oldpasswd = dlUser.getPassword();
		boolean flag = Md5Util.getInstance().checkpassword(password, salt, oldpasswd);
		if(!flag){
			request.setAttribute("ErrorInfo", "密码错误!");
			return LOGINFAILURE;
		}else{
			//获取用户角色
			List<DlUserroles> userRolesList = userService.getRolesListByUserId(dlUser.getUserId());
			dlUser.setUserRolesList(userRolesList);
			int size = userService.getUserDao(dlUser.getUserId()).size();
			dlUser.setContentsSize(size);
			att.put(Constant.SESSION_USER_KEY.getStrValue(), dlUser);
			return LOGINSUCCESS;
		}
	}
	
	/**
	 * @ fengsn
	 * 登录
	 * */
	public String uploadImage() throws Exception{
		
			System.out.println("====================进来了=========================");
			return LOGINFAILURE;
		
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
	public DlUsers getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(DlUsers updateUser) {
		this.updateUser = updateUser;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public DlUsers getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(DlUsers personalInfo) {
		this.personalInfo = personalInfo;
	}
	public String getFindPwUserName() {
		return findPwUserName;
	}
	public void setFindPwUserName(String findPwUserName) {
		this.findPwUserName = findPwUserName;
	}
	public List getAreaInfo() {
		return areaInfo;
	}
	public void setAreaInfo(List areaInfo) {
		this.areaInfo = areaInfo;
	}
	public IDlAreaService getDlAreaService() {
		return dlAreaService;
	}
	public void setDlAreaService(IDlAreaService dlAreaService) {
		this.dlAreaService = dlAreaService;
	}
	public List getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List provinceList) {
		this.provinceList = provinceList;
	}
	public List getCityList() {
		return cityList;
	}
	public void setCityList(List cityList) {
		this.cityList = cityList;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince_select() {
		return province_select;
	}
	public void setProvince_select(String province_select) {
		this.province_select = province_select;
	}
	public String getCity_select() {
		return city_select;
	}
	public void setCity_select(String city_select) {
		this.city_select = city_select;
	}
	public String getPerUrl() {
		return perUrl;
	}
	public void setPerUrl(String perUrl) {
		this.perUrl = perUrl;
	}

}

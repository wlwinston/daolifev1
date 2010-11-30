package com.innovation.common.web.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovation.common.util.Constant;
import com.innovation.common.util.Md5Util;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Ȩ�޿记住登录Cookie拦截器
 * @author winston
 *
 */
public class WebCookieInterceptor extends AbstractInterceptor {
	public static final String FORBIDDEN = "forbidden"; 
	private IUserService userService ;
	private static Logger logger = Logger.getLogger(WebCookieInterceptor.class);
	

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();  
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession(); 
		Cookie   cookies[]=request.getCookies(); 
		Cookie   sCookie=null; 
		String   svalue=null; 
		String   sname=null; 
		String   userName = null;
		String   authCode = null;
		//logger.info("Cookie loading start!");
		if(cookies != null)
		{
			//logger.info(cookies.length+"================cookies.length");
			for(int   i=0;i <cookies.length;i++) 
			{ 
				sCookie=cookies[i]; 
				svalue=sCookie.getValue(); 
				sname=sCookie.getName(); 
				//logger.info(svalue+"================svalue");
				//logger.info(sname+"================sname");
				if(sname.equals("daolife_userName"))
				{
					userName = svalue;
				}
				if(sname.equals("daolife_authCode"))
				{
					authCode = svalue;
				}
			}
			DlUsers user = (DlUsers) session.get(Constant.SESSION_USER_KEY.getStrValue()); 
			if(userName != null && authCode != null && user == null )
			{
				//logger.info(userName+"'s Cookie load Success!");
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
					}
					
				}
			}
			//logger.info("Cookie loading end!");
		} 
		      return actionInvocation.invoke(); 
	}

	

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}

package com.innovation.common.web.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.innovation.common.util.Constant;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * È¨ÏÞ¿ØÖÆÀ¹½ØÆ÷
 * @author winston
 *
 */
public class AuthorizationInterceptor extends AbstractInterceptor {
	public static final String FORBIDDEN = "forbidden"; 
	private List<String> allowedRoles = new ArrayList<String>(); 
	private List<String> disallowedRoles = new ArrayList<String>();
	
	public void setAllowedRoles(String roles) { 
	    if (roles != null) 
	      allowedRoles = Arrays.asList(roles.split("[ ]*,[ ]*")); 
	  } 

	public void setDisallowedRoles(String roles) { 
	    if (roles != null) 
	      disallowedRoles = Arrays.asList(roles.split("[ ]*,[ ]*")); 
	 } 

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		 HttpServletRequest request = ServletActionContext.getRequest(); 
		 
		 Map<String, Object> session = actionInvocation.getInvocationContext().getSession(); 
		  // Get current user id 
		 DlUsers user = (DlUsers) session.get(Constant.SESSION_USER_KEY.getStrValue()); 
		 if (!isAllowed(user)) { 
		      // forbid invoking the action 
		      return FORBIDDEN; 
		 } else { 
		      // allow invoking the action 
		      return actionInvocation.invoke(); 
		 } 
	}

	private boolean isAllowed(DlUsers user) {
		List<DlUserroles> userRolesList = null;
		if(user != null)
		{
			userRolesList = user.getUserRolesList();
		}
		if(allowedRoles.size() > 0)
		{
			if (userRolesList == null || userRolesList.size() == 0) 
			 {
				return false; 
			 }
			else{
				for(Iterator<DlUserroles> it = userRolesList.iterator();it.hasNext();)
				{
					DlUserroles userRoles = it.next();
					if(allowedRoles.contains(userRoles.getRolesName()))
					{
						return true;
					}
				}
			}
		}
		else if(disallowedRoles.size() > 0){
			if (userRolesList == null || userRolesList.size() == 0) 
			 {
				return true; 
			 }
			else{
				for(Iterator<DlUserroles> it = userRolesList.iterator();it.hasNext();)
				{
					DlUserroles userRoles = it.next();
					if(disallowedRoles.contains(userRoles.getRolesName()))
					{
						return false;
					}
				}
			}
		}
		else{
			return true;
		}
		return false;
	}
	
}

package com.innovation.common.util;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;

/**
 * »ñÈ¡Ip
 * @author winston
 */
public class IPUtils {
	
	public String getIpAddr(HttpServletRequest request) {  
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		}  
		if(ip.indexOf(",")>0)
		{
			ip = (ip.split(","))[0];
		}
		return ip;  
	}
	public String getIpAddr(WebContext webContext) {  
		HttpServletRequest request = webContext.getHttpServletRequest();
		return getIpAddr(request);
	}  
}


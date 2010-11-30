package com.innovation.daolife.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.innovation.daolife.service.IProductService;
import com.innovation.daolife.service.impl.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{
	private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
    private IProductService productService;
    
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public Map getAtt() {
		return att;
	}

	public void setAtt(Map att) {
		this.att = att;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}

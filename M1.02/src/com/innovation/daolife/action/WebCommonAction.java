/** 
 *
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.innovation.common.util.Constant;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlProduct;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IProductService;
import com.innovation.daolife.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WebCommonAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware{

	private Map att;
	private Short productId;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private List productList ;
    private List productfollowUserList ;
    private DlProduct product;
    private List hotDaoList;
    private List hotUserList;
    private List productDaoList;
	
	private IUserService userService;
	private IProductService productService;
	private IDlDaoService daoService;
	
	private static String INDEX = "index";
	private static String PRODUCT = "product";
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String index(){
		
		
		PaginationSupport paginationSupport = new PaginationSupport(5,0);
		paginationSupport =  productService.getHotProduct(paginationSupport);
		productList = paginationSupport.getItems();
		paginationSupport = new PaginationSupport(9,0);
		paginationSupport =  userService.getHotUser(paginationSupport,null);
		hotUserList =  paginationSupport.getItems();
		paginationSupport = new PaginationSupport(3,0);
		paginationSupport =  daoService.getHotDao(paginationSupport);
		hotDaoList =  paginationSupport.getItems();
		return INDEX;
	} 
	
	public String product(){
		PaginationSupport paginationSupport = new PaginationSupport(5,0);
		paginationSupport =  productService.getHotProduct(paginationSupport);
		productList = paginationSupport.getItems();
		productDaoList = productService.getProductDao();
		return PRODUCT;
	}
	
	public String getproduct(){
		if (att != null
				&& att
						.get(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers nowuser = (DlUsers) att
			.get(Constant.SESSION_USER_KEY.getStrValue());
			productService.addProductFollow(productId,nowuser.getUserId());
		}
		PaginationSupport paginationSupport = new PaginationSupport(5,0);
		paginationSupport =  productService.getHotProduct(paginationSupport);
		productList = paginationSupport.getItems();
		paginationSupport =  productService.getProductById(paginationSupport,productId);
		product = (DlProduct) paginationSupport.getItems().get(0);
		paginationSupport = new PaginationSupport(9,0);
		paginationSupport =  userService.getFollowProductUser(paginationSupport,productId);
		productfollowUserList = paginationSupport.getItems();
		return PRODUCT;
	}
	
	public String hotWidget(){
		PaginationSupport paginationSupport = new PaginationSupport(9,0);
		paginationSupport =  userService.getHotUser(paginationSupport,null);
		hotUserList =  paginationSupport.getItems();
		paginationSupport = new PaginationSupport(3,0);
		paginationSupport =  daoService.getHotDao(paginationSupport);
		hotDaoList =  paginationSupport.getItems();
		return INDEX;
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
	public List getProductList() {
		return productList;
	}
	public void setProductList(List productList) {
		this.productList = productList;
	}
	public List getHotDaoList() {
		return hotDaoList;
	}
	public void setHotDaoList(List hotDaoList) {
		this.hotDaoList = hotDaoList;
	}
	public List getHotUserList() {
		return hotUserList;
	}
	public void setHotUserList(List hotUserList) {
		this.hotUserList = hotUserList;
	}
	public IDlDaoService getDaoService() {
		return daoService;
	}
	public void setDaoService(IDlDaoService daoService) {
		this.daoService = daoService;
	}
	public IProductService getProductService() {
		return productService;
	}
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	public List getProductDaoList() {
		return productDaoList;
	}
	public void setProductDaoList(List productDaoList) {
		this.productDaoList = productDaoList;
	}
	public List getProductfollowUserList() {
		return productfollowUserList;
	}
	public void setProductfollowUserList(List productfollowUserList) {
		this.productfollowUserList = productfollowUserList;
	}

	public Short getProductId() {
		return productId;
	}
	public void setProductId(Short productId) {
		this.productId = productId;
	}
	public DlProduct getProduct() {
		return product;
	}
	public void setProduct(DlProduct product) {
		this.product = product;
	}
	
}

package com.innovation.common.Ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.FastHashMap;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.innovation.common.util.Constant;
import com.innovation.common.util.EmailUtils;
import com.innovation.common.util.IPUtils;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.ProjectException;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.impl.DlFriendDao;
import com.innovation.daolife.dao.impl.DlMessagesDao;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IFollowrelationService;
import com.innovation.daolife.service.IUserService;


/**
 * 此类为当在页面上用dwr调用方法时，可以将所有的这样的方法写入此类中
 * @author Winston
 *
 */
public class CommonAjax {
	
	private static Logger logger = Logger.getLogger(CommonAjax.class);
	
	private IUserService userService;
	
	private IDlUsersDao dlUsersDao;
	
	private IDlDaoService dlDaoService;
	
	private IFollowrelationService followrelationService;
	
	private static String FOLLOWSUCCESS = "followSuccess";
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @author fsn
	 * 检查用户名是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserName(String username){
		boolean flag = userService.checkUserByName(username);
		return flag;
	}
	
	/**
	 * @author fsn
	 * 检查邮箱是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserEmail(String email){
		boolean flag = userService.checkUserByEmail(email);
		return flag;
	}
	
	/**
	 * @author fsn
	 * 检查邮箱是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserAddress(String adress){
		boolean flag = userService.checkUserByAdress(adress);
		return flag;
	}
	
	/**
	 * @author fsn
	 * 检查昵称是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserNickName(String check){
		boolean flag = userService.checkUserByNickName(check);
		return flag;
	}
	
	/**
	 * 获取我的叨
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public PaginationSupport getMyDao(int pages)
	{
		if(pages<0)
		{
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize*(pages-1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize, startIndex);
		WebContext request = WebContextFactory.get();
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
		{
			DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getContentListByUser(paginationSupport, userId);
		}
		else{
			return  null;
		}
		return paginationSupport;
		
	}
	/**
	 * 获取最热dao
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public PaginationSupport getHotDao(int pages){
		if(pages<0)
		{
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize*(pages-1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize, startIndex);
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		paginationSupport = dlDaoService.getHotDao(paginationSupport);
		return paginationSupport;
	}
	
	/**
	 * 获取我的叨
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public PaginationSupport getPesonalDao(int pages,short userId)
	{
		if(pages<0)
		{
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize*(pages-1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize, startIndex);
		WebContext request = WebContextFactory.get();
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
		{
			DlUsers user = userService.getUsersById(userId) ;
			//Short userId = user.getUserId();
			if(user!=null)
			{
				paginationSupport = userService.getContentListByUser(paginationSupport, userId);
			}
			else{
				return null;
			}
		}
		else{
			return  null;
		}
		return paginationSupport;
		
	}
	/**
	 * 获取我好友的叨
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public PaginationSupport getAllDao(int pages)
	{
		if(pages<0)
		{
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize*(pages-1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize, startIndex);
		WebContext request = WebContextFactory.get();
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
		{
			DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getFollowerContentListByUser(paginationSupport, userId);
		}
		else{
			return  null;
		}
		return paginationSupport;
		
	}
	/**
	 * 顶叨
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public String upDao(String daoId)
	{
		String upResult = "";
		try{
			WebContext request = WebContextFactory.get();
			
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
			{
				DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
				String ip = new IPUtils().getIpAddr(request);
				dlDaoService.addUpDao(daoId,user,ip);
			}
			else{
				upResult = Constant.UPDAO_ERRORMESSAGE_NOUSER.getStrValue();
			}
		}
		catch(Exception e){
			if(e instanceof ProjectException)
			{
				upResult = e.getMessage();
			}
		}
		return upResult;
	}
	
	/**
	 * 发叨
	 * @param contentBody 叨内容
	 * @return 叨的实体对象
	 * @throws Exception 
	 * @author winston
	 */
	public DlCustomerDaoEntry addDao(String contentBody) throws Exception
	{
		WebContext request = WebContextFactory.get();
		DlCustomerDaoEntry customerDao = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
		{
			DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			try{
				customerDao = dlDaoService.addDao(user, contentBody);
			}
			catch (Exception e) {
				logger.info(e.getStackTrace());
				customerDao = null;
			}
		}
		return customerDao;
	}
	/**
	 * 转叨
	 * @param contentBody
	 * @param orgDaoId
	 * @return 叨的实体对象
	 * @author winston
	 */
	public DlCustomerDaoEntry addRetwitteDao (String contentBody, Short orgDaoId)  {
		WebContext request = WebContextFactory.get();
		DlCustomerDaoEntry customerDao = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
		{
			DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			try{
				customerDao = dlDaoService.addRetwitteDao(user, contentBody, orgDaoId);
			}
			catch (Exception e) {
				logger.info(e.getStackTrace());
				customerDao = null;
			}
		}
		return customerDao;
	}
	
	/**
	 * 添加关注
	 * @author fsn
	 * */
	public boolean follow(Short followId){
		DlUsers nowuser = new DlUsers();
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null){
		nowuser = (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
		}
		//String followId = request
		DlFriend friend = new DlFriend();
		friend.setFidFollow(followId);
		friend.setFidFans(nowuser.getUserId());
		followrelationService.addFollow(friend);
		
		return true;
	}
	
	public boolean unFollow(Short followId){
		DlUsers nowuser = new DlUsers();
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null){
		nowuser = (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
		}
		//String followId = request
		DlFriend friend = new DlFriend();
		friend.setFidFollow(followId);
		friend.setFidFans(nowuser.getUserId());
		followrelationService.deleteFollow(friend);
		
		return true;
	}
	
	
	
	
	
	
	public IDlDaoService getDlDaoService() {
		return dlDaoService;
	}
	public void setDlDaoService(IDlDaoService dlDaoService) {
		this.dlDaoService = dlDaoService;
	}
	public IFollowrelationService getFollowrelationService() {
		return followrelationService;
	}
	public void setFollowrelationService(
			IFollowrelationService followrelationService) {
		this.followrelationService = followrelationService;
	}
	
	
	
	
}		

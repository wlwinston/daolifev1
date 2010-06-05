package com.innovation.common.Ajax;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.FastHashMap;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.innovation.common.util.Constant;
import com.innovation.common.util.EmailUtils;
import com.innovation.common.util.IPUtils;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.ProjectException;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.impl.DlFriendDao;
import com.innovation.daolife.dao.impl.DlMessagesDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlAreaService;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IDlMessagesService;
import com.innovation.daolife.service.IFollowrelationService;
import com.innovation.daolife.service.IUserService;

/**
 * ����Ϊ����ҳ������dwr���÷���ʱ�����Խ����е�����ķ���д�������
 * 
 * @author Winston
 * 
 */
public class CommonAjax {

	private static Logger logger = Logger.getLogger(CommonAjax.class);

	private IUserService userService;

	private IDlUsersDao dlUsersDao;

	private IDlDaoService dlDaoService;
	
	private IDlMessagesService dlMessagesService;
	
	private IDlAreaService dlAreaService;

	private IFollowrelationService followrelationService;

	private static String FOLLOWSUCCESS = "followSuccess";

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @author fsn ����û����Ƿ�Ψһ ����true Ψһ
	 */
	public boolean checkUserName(String username) {
		boolean flag = userService.checkUserByName(username);
		return flag;
	}

	/**
	 * @author fsn ��������Ƿ�Ψһ ����true Ψһ
	 */
	public boolean checkUserEmail(String email) {
		boolean flag = userService.checkUserByEmail(email);
		return flag;
	}

	/**
	 * @author fsn ��������Ƿ�Ψһ ����true Ψһ
	 */
	public boolean checkUserAddress(String adress) {
		boolean flag = userService.checkUserByAdress(adress);
		return flag;
	}

	/**
	 * @author fsn ����ǳ��Ƿ�Ψһ ����true Ψһ
	 */
	public boolean checkUserNickName(String check) {
		boolean flag = userService.checkUserByNickName(check);
		return flag;
	}
	
	/**
	 * @author fengsn
	 * return @我的内容
	 * */
	public PaginationSupport getAtContentDao(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers nowuser = (DlUsers) session
			.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			paginationSupport = dlDaoService.getAtContentListByUser(paginationSupport, nowuser.getUserId());
			dlMessagesService.dealReadState(nowuser.getUserId(),"0");
		} else {
			return null;
		}
		return paginationSupport;

	}

	/**
	 * @author fengsn
	 * 统计新信息提醒
	 * */
	public List getMessageStatistics(){
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers nowuser = (DlUsers) session
			.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			List result = dlMessagesService.getMessages(nowuser.getUserId());
			return result;
		} else {
			return null;
		}
	}
	
	
	/**
	 * @author fsn ���userId����û���Ϣ
	 */
	public DlUsers getPesonalUserById(Short id) {

		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers nowuser = (DlUsers) session
			.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			DlUsers user = userService.getPesonalUserById(id,nowuser);
			return user;
		} else {
			return null;
		}
		
	}
	
	/**
	 * @author fsn 
	 * 一、返回 session 里面的用户id 二、传入的userId(如果没有 返回session 里面的用户id)  三、用户信息
	 */
	public List getUserInfo(Short userId) {
		List result = new ArrayList();
		//判断入参id 是否为空
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		Short sessionId ;
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers nowuser = (DlUsers) session
			.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			sessionId = nowuser.getUserId();
			if(userId==null){
				result.add(sessionId);
				result.add(sessionId);
				DlUsers tmp_user = userService.getPesonalUserById(sessionId,nowuser);
				result.add(tmp_user);
			}else{
				DlUsers tmp_user = userService.getPesonalUserById(userId,nowuser);
				result.add(sessionId);
				result.add(userId);
				result.add(tmp_user);
			}
		} else {
			return null;
		}
		return result;
	}
	
	/**
	 * @author fengsn
	 * 返回List 存放省级下拉菜单数据
	 * */
	public List getProvinces(){
		List Province = new ArrayList();
		Province = dlAreaService.getAreaInfo("000001");
		return Province;
	}
	
	/**
	 * @author fengsn
	 * 返回 List 存放地区级下拉菜单数据
	 * */
	public List getCitys(String Id){
		List City = new ArrayList();
		City = dlAreaService.getAreaInfo(Id);
		return City;
	}
	
	/**
	 * ��ȡת��dao����Ϣ
	 * 
	 * @param contentId
	 *            ��ת��ID
	 * @author fsn
	 */
	public PaginationSupport getRewriteContentDao(int pages,Short contentId) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			paginationSupport = dlDaoService.getRewriteInfoList(
					paginationSupport, contentId);
		} else {
			return null;
		}
		return paginationSupport;

	}

	/**
	 * ��ȡ�ҵ�߶
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public PaginationSupport getMyDao(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getContentListByUser(
					paginationSupport, userId);
		} else {
			return null;
		}
		return paginationSupport;

	}

	/**
	 * ��ȡ����dao
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public PaginationSupport getHotDao(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		// WebContext request = WebContextFactory.get();
		// HttpSession session = request.getSession(false);
		paginationSupport = dlDaoService.getHotDao(paginationSupport);
		return paginationSupport;
	}

	/**
	 * ��ȡ����dao
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public PaginationSupport getHotUser(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			// Short userId = user.getUserId();
			paginationSupport = userService.getHotUser(paginationSupport, user);
			paginationSupport.setNowUid(user.getUserId());
		} else {
			// DlUsers user = new DlUsers();
			paginationSupport = userService.getHotUser(paginationSupport, null);
			paginationSupport.setNowUid((short)0);
		}
		return paginationSupport;
	}

	/**
	 * ��ȡ�ҵ�߶
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public PaginationSupport getPesonalDao(int pages, short userId) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		DlUsers user = userService.getUsersById(userId);
		// Short userId = user.getUserId();
		if (user != null) {
			paginationSupport = userService.getContentListByUser(
					paginationSupport, userId);
		} else {
			return null;
		}

		return paginationSupport;

	}

	/**
	 * ��ȡ�Һ��ѵ�߶
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public PaginationSupport getAllDao(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();

		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getFollowerContentListByUser(
					paginationSupport, userId);
		} else {
			return null;
		}
		return paginationSupport;

	}
	
	/**
	 * ��㿴��
	 * @author fsn
	 */
	public PaginationSupport getContentsByTime(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		paginationSupport = dlDaoService.getContentListByTime(paginationSupport);
		return paginationSupport;
	}

	/**
	 * ��߶
	 * 
	 * @param daoId
	 *            ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
	 * @author winston
	 */
	public String upDao(String daoId) {
		String upResult = "";
		try {
			WebContext request = WebContextFactory.get();

			HttpSession session = request.getSession(false);
			if (session != null
					&& session.getAttribute(Constant.SESSION_USER_KEY
							.getStrValue()) != null) {
				DlUsers user = (DlUsers) session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
				String ip = new IPUtils().getIpAddr(request);
				dlDaoService.addUpDao(daoId, user, ip);
			} else {
				upResult = Constant.UPDAO_ERRORMESSAGE_NOUSER.getStrValue();
			}
		} catch (Exception e) {
			if (e instanceof ProjectException) {
				upResult = e.getMessage();
			}
		}
		return upResult;
	}

	/**
	 * ��߶
	 * 
	 * @param contentBody
	 *            ߶����
	 * @return ߶��ʵ�����
	 * @throws Exception
	 * @author winston
	 */
	public DlCustomerDaoEntry addDao(String contentBody) throws Exception {
		WebContext request = WebContextFactory.get();
		DlCustomerDaoEntry customerDao = null;
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			try {
				customerDao = dlDaoService.addDao(user, contentBody);
			} catch (Exception e) {
				logger.info(e.getStackTrace());
				customerDao = null;
			}
		}
		return customerDao;
	}

	/**
	 * ת߶
	 * 
	 * @param contentBody
	 * @param orgDaoId
	 * @return ߶��ʵ�����
	 * @author winston
	 */
	public DlCustomerDaoEntry addRetwitteDao(String contentBody, Short orgDaoId) {
		WebContext request = WebContextFactory.get();
		DlCustomerDaoEntry customerDao = null;
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			try {
				customerDao = dlDaoService.addRetwitteDao(user, contentBody,
						orgDaoId);
			} catch (Exception e) {
				logger.info(e.getStackTrace());
				customerDao = null;
			}
		}
		return customerDao;
	}

	/**
	 * ��ӹ�ע
	 * 
	 * @author fsn
	 */
	public boolean follow(Short followId) {
		DlUsers nowuser = new DlUsers();
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			nowuser = (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY
					.getStrValue());
			DlFriend friend = new DlFriend();
			friend.setFidFollow(followId);
			friend.setFidFans(nowuser.getUserId());
			followrelationService.addFollow(friend);
			return true;
		} else {
			return false;
		}
		// String followId = request

	}
	
	/**
	 * @author fengsn
	 * �ҹ�ע��dao��
	 * */
	public PaginationSupport getMyFollowFriend(int pages){
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getFollowListByUser(
					paginationSupport, userId);
		} else {
			return null;
		}
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * ��ע�ҵ�dao��
	 * */
	public PaginationSupport getMyFanFriend(int pages) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			DlUsers user = (DlUsers) session
					.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
			Short userId = user.getUserId();
			paginationSupport = userService.getFanListByUser(
					paginationSupport, userId);
			dlMessagesService.dealReadState(userId,"2");
		} else {
			return null;
		}
		return paginationSupport;
	}

	/**
	 * @author fengsn
	 * �huode获得其他用户的关注dao友信息
	 * */
	public PaginationSupport getOtherFollowFriend(int pages,Short userId){
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		if (userId!=null) {
			paginationSupport = userService.getFollowListByUser(
					paginationSupport, userId);
		} else {
			return null;
		}
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * ��获得其他用户的dao友列表
	 * */
	public PaginationSupport getOtherFanFriend(int pages,Short userId) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		if (userId!=null) {
			paginationSupport = userService.getFanListByUser(
					paginationSupport, userId);
			//dlMessagesService.dealReadState(userId,"2");
		} else {
			return null;
		}
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * ��获得话题相关的dao内容
	 * */
	public PaginationSupport getTopicContent(int pages,Short topicId) {
		if (pages < 0) {
			pages = 1;
		}
		int pageSize = Constant.PAGESIZE_MYDAO.getIntValue();
		int startIndex = pageSize * (pages - 1);
		PaginationSupport paginationSupport = new PaginationSupport(pageSize,
				startIndex);
		if (topicId!=null) {
			paginationSupport = dlDaoService.getTopicListContent(
					paginationSupport, topicId);
			//dlMessagesService.dealReadState(userId,"2");
		} else {
			return null;
		}
		return paginationSupport;
	}
	
	
	public boolean unFollow(Short followId) {
		DlUsers nowuser = new DlUsers();
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session
						.getAttribute(Constant.SESSION_USER_KEY.getStrValue()) != null) {
			nowuser = (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY
					.getStrValue());
			// String followId = request
			DlFriend friend = new DlFriend();
			friend.setFidFollow(followId);
			friend.setFidFans(nowuser.getUserId());
			followrelationService.deleteFollow(friend);

			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * 比对验证码
	 * @param authCode 验证码
	 * @author winston
	 * @return
	 */
	public boolean checkAuthCode(String authCode)
	{
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		if (session != null
				&& session.getAttribute("rand") != null) {
			if(authCode!=null && authCode.trim().length()>0)
			{
				String authRand = (String) session.getAttribute("rand");
				if(authCode.equalsIgnoreCase(authRand))
				{
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public String login(String userName ,String password,boolean ifCookie) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String loginInfo = "";
		DlUsers dlUser = userService.getUserByNameOrEmail(userName);
		if(dlUser==null){
			loginInfo =  "您输入用户不存在";
			return loginInfo;
		}
		WebContext request = WebContextFactory.get();
		HttpSession session = request.getSession(false);
		String salt = dlUser.getSalt();
		String oldpasswd = dlUser.getPassword();
		boolean flag = false;
		try {
			flag = Md5Util.getInstance().checkpassword(password, salt, oldpasswd);
		} catch (Exception e) {
			loginInfo = "系统错误!";
		}
		if(!flag){
			loginInfo = "密码错误";
		}else{
			//获取用户角色
			List<DlUserroles> userRolesList = userService.getRolesListByUserId(dlUser.getUserId());
			dlUser.setUserRolesList(userRolesList);
			int size = userService.getUserDao(dlUser.getUserId()).size();
			dlUser.setContentsSize(size);
			session.setAttribute(Constant.SESSION_USER_KEY.getStrValue(), dlUser);
			//记住登录状态
			if(ifCookie)
			{
				  Cookie   userNameCookie   =   new   Cookie( "daolife_userName", userName);
				  String authCode = Md5Util.getInstance().EncoderByMd5(userName+oldpasswd,dlUser.getSalt());
				  Cookie   authCodeCookie   =   new   Cookie( "daolife_authCode", authCode);
				  Date now = new Date();
				  String nowDateString  = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime()); 
				  Cookie   loginTimeCookie   =   new   Cookie( "daolife_loginTime", nowDateString);
				  HttpServletResponse response = request.getHttpServletResponse();
				  userNameCookie.setPath("/");
				  userNameCookie.setMaxAge(7*24*60*60);
				  authCodeCookie.setPath("/");
				  authCodeCookie.setMaxAge(7*24*60*60);
				  loginTimeCookie.setPath("/");
				  loginTimeCookie.setMaxAge(7*24*60*60);
				  response.addCookie(userNameCookie);
				  response.addCookie(authCodeCookie);
				  response.addCookie(loginTimeCookie);
			}
		}
		return loginInfo;
	}
	
	
	public DlContent getDlContentById(Short contentId) throws Exception
	{
		DlContent content = dlDaoService.getDao(contentId);
		return content;
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

	public IDlMessagesService getDlMessagesService() {
		return dlMessagesService;
	}

	public void setDlMessagesService(IDlMessagesService dlMessagesService) {
		this.dlMessagesService = dlMessagesService;
	}

	public IDlAreaService getDlAreaService() {
		return dlAreaService;
	}

	public void setDlAreaService(IDlAreaService dlAreaService) {
		this.dlAreaService = dlAreaService;
	}

}

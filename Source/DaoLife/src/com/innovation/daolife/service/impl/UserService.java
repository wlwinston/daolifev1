/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;

import com.innovation.common.Ajax.CommonAjax;
import com.innovation.common.util.Constant;
import com.innovation.common.util.DaoLifeEmail;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.RandomString;
import com.innovation.common.util.WebConfig;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlAreaDao;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlFriendDao;
import com.innovation.daolife.dao.IDlHotdaoDao;
import com.innovation.daolife.dao.IDlProductDao;
import com.innovation.daolife.dao.IDlUserrolesDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.dao.impl.DlHotdaoDao;
import com.innovation.daolife.dao.impl.DlProductDao;
import com.innovation.daolife.dao.impl.UserDao;
import com.innovation.daolife.model.DlArea;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlHotdao;
import com.innovation.daolife.model.DlProduct;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;

public class UserService implements IUserService {
	
	private static Logger logger = Logger.getLogger(UserService.class);

	private IDlUsersDao dlUsersDao;

	private IDlContentatDao dlContentatDao;
	
	private IDlContentDao dlContentDao;

	private IDlHotdaoDao dlHotdaoDao;
	
	private IDlAreaDao dlAreaDao;

	private IDlProductDao dlProductDao;
	
	private IDlUserrolesDao dlUserrolesDao;
	
	private IDlFriendDao dlFriendDao;
	
	private DaoLifeEmail daoLifeEmail;

	public User getUserById(String id) {
		User user = null;
		/*
		 * try{ int userId = Integer.parseInt(id); user = userDao.get(userId); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		return user;
	}

	public DlUsers getUsersById(Short id) {
		DlUsers user = null;
		try {
			// int userId = Integer.parseInt(id);
			user = dlUsersDao.get(id);
			if(user != null)
			{
				String sql = " From DlContent u where u.userId=? and u.status='0'";
				List<DlContent> contentList = dlContentDao.find(sql, id);
				user.setContentsSize(contentList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public DlUsers getPesonalUserById(Short id,DlUsers nowuser) {
		DlUsers user = null;
		try {
			// int userId = Integer.parseInt(id);
			user = dlUsersDao.get(id);
			if(user != null)
			{
				String sql = " From DlContent u where u.userId=? and u.status='0'";
				List<DlContent> contentList = dlContentDao.find(sql, id);
				user.setContentsSize(contentList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Short fanId = nowuser.getUserId();
		if(checkRela(id,fanId)){
			user.setFollowFlag(true);
		}
		user.setCityName(getCityName(user));
		return user;
	}
	
	public List<DlContent> getUserDao(Short id) {
		String sql = " From DlContent u where u.userId=?";
		List<DlContent> contentList = dlContentDao.find(sql, id);
//		List<DlContent> dlContentList = new ArrayList();
//		DlUsers user = userList.get(0);
//		Set ul = user.getDlContents(); 
//        Iterator<DlContent> it = ul.iterator();
//        while(it.hasNext()){
//        	dlContentList.add(it.next());
//        }
		return contentList;
	}
	
	/**
	 * @author fengsn
	 * ��获得关注我的用户���
	 * */
	public PaginationSupport getFollowListByUser(PaginationSupport paginationSupport,Short userId) {
		
		DlUsers user = dlUsersDao.get(userId);
		List<DlUsers> itemList = new ArrayList();
		int start = 0;
		int pagesize = paginationSupport.getPageSize();
		int startIndex = paginationSupport.getStartIndex();
		int tmp_Strat = pagesize*startIndex+1;
		int tmp_End = pagesize*(startIndex+1);
	    for(Iterator<DlUsers> it = user.getDlFollowers().iterator();it.hasNext(); )
	    {
	    	DlUsers newUser = it.next();
	    	Short newUId = newUser.getUserId();
	    	start+=1;
	    	if(start<=tmp_End&&start>=tmp_Strat&&newUId!=userId){
	    		if(checkRela(newUId,userId)){
	    			newUser.setFollowFlag(true);
				}else{
					newUser.setFollowFlag(false);
				}
	    		newUser.setCityName(getCityName(newUser));
	    		itemList.add(newUser);
	    	}else if(newUId==userId){
	    		start = start-1;
	    	}else if(start>tmp_End){
	    		break;
	    	}else if(start<tmp_Strat){
	    		continue;
	    	}
	    }
	    paginationSupport.setItems(itemList);
		
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 获得我的dao友
	 * */
	public PaginationSupport getFanListByUser(PaginationSupport paginationSupport,Short userId) {
		
		DlUsers user = dlUsersDao.get(userId);
		List<DlUsers> itemList = new ArrayList();
		int start = 0;
		int pagesize = paginationSupport.getPageSize();
		int startIndex = paginationSupport.getStartIndex();
		int tmp_Strat = pagesize*startIndex+1;
		int tmp_End = pagesize*(startIndex+1);
	    for(Iterator<DlUsers> it = user.getDlFancers().iterator();it.hasNext(); )
	    {
	    	DlUsers newUser = it.next();
	    	Short newUId = newUser.getUserId();
	    	start+=1;
	    	if(start<=tmp_End&&start>=tmp_Strat&&newUId!=userId){
	    		if(checkRela(newUId,userId)){
	    			newUser.setFollowFlag(true);
				}else{
					newUser.setFollowFlag(false);
				}
	    		newUser.setCityName(getCityName(newUser));
	    		itemList.add(newUser);
	    	}else if(newUId==userId){
	    		start = start-1;
	    	}else if(start>tmp_End){
	    		break;
	    	}else if(start<tmp_Strat){
	    		continue;
	    	}
	    }
	    paginationSupport.setItems(itemList);

		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * ��获得关注ta的用户���
	 * */
	public PaginationSupport getOtherFollowListByUser(PaginationSupport paginationSupport,Short userId,Short sessionId) {
		
		DlUsers user = dlUsersDao.get(userId);
		List<DlUsers> itemList = new ArrayList();
		int start = 0;
		int pagesize = paginationSupport.getPageSize();
		int startIndex = paginationSupport.getStartIndex();
		int tmp_Strat = pagesize*startIndex+1;
		int tmp_End = pagesize*(startIndex+1);
	    for(Iterator<DlUsers> it = user.getDlFollowers().iterator();it.hasNext(); )
	    {
	    	DlUsers newUser = it.next();
	    	Short newUId = newUser.getUserId();
	    	start+=1;
	    	if(start<=tmp_End&&start>=tmp_Strat&&newUId!=userId){
	    		if(checkRela(newUId,sessionId)){
	    			newUser.setFollowFlag(true);
				}else{
					newUser.setFollowFlag(false);
				}
	    		newUser.setCityName(getCityName(newUser));
	    		itemList.add(newUser);
	    	}else if(newUId==userId){
	    		start = start-1;
	    	}else if(start>tmp_End){
	    		break;
	    	}else if(start<tmp_Strat){
	    		continue;
	    	}
	    }
	    paginationSupport.setItems(itemList);
	    
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 获得ta的dao友
	 * */
	public PaginationSupport getOtherFanListByUser(PaginationSupport paginationSupport,Short userId,Short sessionId) {
		DlUsers user = dlUsersDao.get(userId);
		List<DlUsers> itemList = new ArrayList();
		int start = 0;
		int pagesize = paginationSupport.getPageSize();
		int startIndex = paginationSupport.getStartIndex();
		int tmp_Strat = pagesize*startIndex+1;
		int tmp_End = pagesize*(startIndex+1);
	    for(Iterator<DlUsers> it = user.getDlFancers().iterator();it.hasNext(); )
	    {
	    	DlUsers newUser = it.next();
	    	Short newUId = newUser.getUserId();
	    	start+=1;
	    	if(start<=tmp_End&&start>=tmp_Strat&&newUId!=userId){
	    		if(checkRela(newUId,sessionId)){
	    			newUser.setFollowFlag(true);
				}else{
					newUser.setFollowFlag(false);
				}
	    		newUser.setCityName(getCityName(newUser));
	    		itemList.add(newUser);
	    	}else if(newUId==userId){
	    		start = start-1;
	    	}else if(start>tmp_End){
	    		break;
	    	}else if(start<tmp_Strat){
	    		continue;
	    	}
	    }
	    paginationSupport.setItems(itemList);
	    
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 关注产品用户user
	 * */
	public PaginationSupport getFollowProductUser(PaginationSupport paginationSupport,Short Id) {
		String querysql = " Select u From DlUsers u INNER JOIN u.dlProductfollow f  where  f.productfollowProductid = "+Id+"";
		String countsql =" Select count(u.userId) From DlUsers u INNER JOIN u.dlProductfollow f  where  f.productfollowProductid = "+Id+" ";
		paginationSupport = dlUsersDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * ��ѯ�ͻ��Լ���dao
	 * */
	public PaginationSupport getContentListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select c From DlContent c INNER JOIN c.dlUsers u where  u.userId = "+userId+"  and c.status='0' order by c.posttime desc";
		String countsql =" Select count(c.contentId) From DlContent c INNER JOIN c.dlUsers u where u.userId = "+userId+" and c.status='0' order by c.posttime desc";
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		List<DlContent> itemList = paginationSupport.getItems();
		for(Iterator<DlContent> it = itemList.iterator();it.hasNext();)
		{
			DlContent dlContent = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlContent.getDlUsers(), user);
			dlContent.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);
		return paginationSupport;
	}
	
	/**
	 * @author fengsn 
	 * ��ѯ����dao��
	 */
	public PaginationSupport getHotUser(PaginationSupport paginationSupport,DlUsers user) {
		//Short daoNum = this.getdaoNum();
			String querysql = " Select c From DlUsers c order by recommendInd desc,at_sum_num desc";
			String countsql = " Select count(c.userId) From DlUsers c order by recommendInd desc,at_sum_num desc";
			paginationSupport = dlUsersDao.findPageByQuery(querysql, countsql,
					paginationSupport.getPageSize(), paginationSupport
							.getStartIndex());
		if(user!=null){
			List<DlUsers> itemList = paginationSupport.getItems();
			Short fanId = user.getUserId();
			for(Iterator<DlUsers> it = itemList.iterator();it.hasNext();)
			{
				DlUsers dlUsers = it.next();
				Short followId = dlUsers.getUserId();
				if(checkRela(followId,fanId)){
					dlUsers.setFollowFlag(true);
				}
				dlUsers.setCityName(getCityName(dlUsers));
				//dlUsers.setFollowFlag(true);
			}
			paginationSupport.setItems(itemList);
		}
		return paginationSupport;
	}
	
	private boolean checkRela(Short followId,Short fanId){
		boolean result = false;
		String sql = " From DlFriend u where u.fidFollow=? and u.fidFans=?";
		List<DlFriend> friendList = dlFriendDao.find(sql,
				new Short[] { followId, fanId });
		if(friendList.size()>0){
			result = true;
		}
		return result;
	}
	
	//城市名称
	private String getCityName(DlUsers user){
		String sql = " From DlArea u where u.areaId=?";
		List<DlArea> areaList =dlAreaDao.find(sql, user.getHomeCity());
		if(areaList.size()>0){
			return areaList.get(0).getAreaName();
		}else{
			return "保密";
		}
	}
	
	/**
	 * @author fengsn
	 * ��ѯ���ѵ�dao
	 * */
	public PaginationSupport getFollowerContentListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select c From DlContent c INNER JOIN c.dlUsers u  INNER JOIN u.dlFancers f where  f.userId = "+userId+" and c.status='0' order by c.posttime desc";
		String countsql =" Select count(c.contentId) From DlContent c INNER JOIN c.dlUsers u INNER JOIN u.dlFancers f where  f.userId = "+userId+" and c.status='0' order by c.posttime desc";
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		List<DlContent> itemList = paginationSupport.getItems();
		for(Iterator<DlContent> it = itemList.iterator();it.hasNext();)
		{
			DlContent dlContent = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlContent.getDlUsers(), user);
			dlContent.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);
		return paginationSupport;
	}
	
	public PaginationSupport getListBySearch(
			PaginationSupport paginationSupport, UserSearch userSearch) {
		/*
		 * DetachedCriteria detachedCriteria = userSearch.getDetachedCriteria();
		 * paginationSupport = userDao.findPageByCriteria(detachedCriteria,
		 * paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		 * String sql = " From User u where u.username=?"; List<User> userList =
		 * userDao.find(sql,"wanglei");
		 * 
		 * String querysql =" Select u.username,u.password,u.email From User u
		 * INNER JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		 * List<User> newlist = userDao.findByNamedQuery(querysql); List<Followrelation>
		 * fList = followrelationDao.find("From Followrelation f where
		 * f.follower= 1"); String countsql =" Select count(*) From User u INNER
		 * JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		 * PaginationSupport a = userDao.findPageByQuery(querysql, countsql, 30,
		 * 0);
		 */
		return paginationSupport;
	}

	/**
	 * @fsn ͨ��name����email����û���Ϣ
	 */
	public DlUsers getUserByNameOrEmail(String name) {
		DlUsers user = null;
		String sql = " From DlUsers u where u.userName=? or u.mailadres = ? ";
		List<DlUsers> userList = dlUsersDao.find(sql,
				new String[] { name, name });
		if (userList.size() > 0) {
			user = userList.get(0);
		}
		return user;

	}

	/**
	 * @fsn ����û����Ψһ��
	 */
	public boolean checkUserByName(String name) {
		boolean flag = true;
		String sql = " From DlUsers u where u.userName=?";
		List<DlUsers> userList = dlUsersDao.find(sql, name);
		if (userList.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @fsn ��������Ψһ��
	 */
	public boolean checkUserByEmail(String name) {
		boolean flag = true;
		String sql = " From DlUsers u where u.mailadres=?";
		List<DlUsers> userList = dlUsersDao.find(sql, name);
		if (userList.size() > 0) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @fsn ��������Ψһ��
	 */
	public boolean checkUserByAdress(String name) {
		boolean flag = true;
		String sql = " From DlUsers u where u.userUrl=?";
		List<DlUsers> userList = dlUsersDao.find(sql, name);
		if (userList.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @fsn ����ǳƵ�Ψһ��
	 */
	public boolean checkUserByNickName(String name) {
		boolean flag = true;
		String sql = " From DlUsers u where u.userNickName=?";
		List<DlUsers> userList = dlUsersDao.find(sql, name);
		if (userList.size() > 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @fsn ���������޸�
	 */
	public void update(DlUsers newuser, DlUsers olduser) throws Exception {
		Short userId = olduser.getUserId();
		newuser.setUserId(userId);
		newuser.setPassword(olduser.getPassword());
		newuser.setSalt(olduser.getSalt());
		String newNickName = newuser.getUserNickName();
		String oldNickName = olduser.getUserNickName();
		if (!newNickName.equals(oldNickName)) {
			modifyNickName(newNickName, oldNickName);
		}
		
		dlUsersDao.update(newuser);
	}
	
	public void updatePsw(DlUsers user, String newpsw) throws Exception{
		String salt = user.getSalt();
		String inputpsw = Md5Util.getInstance().EncoderByMd5(newpsw, salt);
		user.setPassword(inputpsw);
		dlUsersDao.update(user);
	}

	private void modifyNickName(String newNickName, String oldNickName) throws Exception  {
		String updateContentSql = "update dl_contentat set status_uname='" +newNickName+"' where  status_uname='"
				+oldNickName +"'";
		String updateHotSql = "update dl_hotdao set user_name ='" + newNickName + "' where  user_name= '"
				+ oldNickName + "'";
		String updateProductSql = "update dl_product set product_author='" + newNickName + "' where  product_author= '"
				+ oldNickName + "'";
		
		dlContentatDao.update(updateContentSql);
		dlHotdaoDao.update(updateHotSql);
		dlProductDao.update(updateProductSql);
	}

	/**
	 * 注册用户
	 * @param  用户信息
	 * @author winston
	 */
	public void regist(DlUsers user) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		user.setAtMonthNum((short) 0);
		user.setAtSumNum((short) 0);
		user.setAtWeekNum((short) 0);
		user.setFansNum((short) 0);
		user.setFollowNum((short) 0);
		user.setUserInfo("");
		user.setUserHead("default");
		user.setRecommendInd(Constant.USER_RECOMMENDIND_NO.getStrValue());
		user.setIsclose(new Byte(Constant.USER_ISCLOSED_NO.getStrValue()));
		Date now = new Date();
		user.setLastLogin(now);
		String password = user.getPassword();
		String salt = RandomString.getInstance().getRandomString(6);
		user.setSalt(salt);
		password = Md5Util.getInstance().EncoderByMd5(password, salt);
		user.setPassword(password);
		user.setPhotoNum((short) 0);
		String nowString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(now.getTime());
		user.setSignupdate(now);
		user.setUserlock(new Byte(Constant.USER_USERLOCK_NO.getStrValue()));
		user.setAuthEmail(Constant.USER_AUTHMAIL_NOMAIL.getStrValue());
		dlUsersDao.save(user);
		//增加默认网站用户
		DlUserroles userRoles = new DlUserroles();
		userRoles.setRolesName(Constant.WEBSITE_ROLES_DEFAULT.getStrValue());
		userRoles.setUserId(user.getUserId());
		dlUserrolesDao.save(userRoles);
		List<DlUserroles> userRolesList = new ArrayList<DlUserroles>();
		userRolesList.add(userRoles);
		user.setUserRolesList(userRolesList);
		//增加自己为自己的好友，方便大家都在叨时查询出自己的叨句
		DlFriend dlFriend = new DlFriend();
		dlFriend.setFidFans(user.getUserId());
		dlFriend.setFidFollow(user.getUserId());
		dlFriendDao.save(dlFriend);
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(DlUsers user) {
		dlUsersDao.update(user);
	}

	public IDlUsersDao getDlUsersDao() {
		return dlUsersDao;
	}

	public void setDlUsersDao(IDlUsersDao dlUsersDao) {
		this.dlUsersDao = dlUsersDao;
	}

	/**
	 * �����һ�����email
	 */
	public void resetPasswordEmail(String userName) throws EmailException {
		String authCode = RandomString.getInstance().getRandomString(25);
		DlUsers user = this.getUserByNameOrEmail(userName);
		if (user != null) {
			user.setAuthEmail(authCode);
			this.updateUser(user);
			//验证
			String authUrl = "<a href=\""+WebConfig.linkWebPrefix+"/ResetPassword.action?userId="+ user.getUserId() +"&authCode="+authCode+"\" >"+WebConfig.linkWebPrefix+"/ResetPassword.action?userId="
					+ user.getUserId() + "&authCode=" + authCode + "</a>";
			daoLifeEmail.sendFindPasswordEmail(user.getUserName(), authUrl, user
					.getMailadres());
		}
	}

	/**
	 * У���һ�������Կ�Ƿ���ȷ
	 */
	public boolean checkAuthCode(Short userId, String authCode) {
		DlUsers user = dlUsersDao.get(userId);
		if (user != null) {
			if (authCode.equals(user.getAuthEmail())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��������
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws EmailException
	 */
	public void resetPassword(Short userId, String newPassword)
			throws NoSuchAlgorithmException, UnsupportedEncodingException,
			EmailException {
		DlUsers user = dlUsersDao.get(userId);
		if (user != null) {
			String salt = RandomString.getInstance().getRandomString(6);
			String md5Password = Md5Util.getInstance().EncoderByMd5(
					newPassword, salt);
			user.setPassword(md5Password);
			user.setSalt(salt);
			user.setAuthEmail(RandomString.getInstance().getRandomString(25));
			this.updateUser(user);
			daoLifeEmail.sendSetPasswordSuccessEmail(user.getUserName(),
					newPassword, user.getMailadres());

		}

	}

	public IDlContentatDao getDlContentatDao() {
		return dlContentatDao;
	}

	public void setDlContentatDao(IDlContentatDao dlContentatDao) {
		this.dlContentatDao = dlContentatDao;
	}

	public IDlHotdaoDao getDlHotdaoDao() {
		return dlHotdaoDao;
	}

	public void setDlHotdaoDao(IDlHotdaoDao dlHotdaoDao) {
		this.dlHotdaoDao = dlHotdaoDao;
	}

	public IDlProductDao getDlProductDao() {
		return dlProductDao;
	}

	public void setDlProductDao(IDlProductDao dlProductDao) {
		this.dlProductDao = dlProductDao;
	}

	public IDlUserrolesDao getDlUserrolesDao() {
		return dlUserrolesDao;
	}

	public void setDlUserrolesDao(IDlUserrolesDao dlUserrolesDao) {
		this.dlUserrolesDao = dlUserrolesDao;
	}

	public List<DlUserroles> getRolesListByUserId(Short userId) {
		String searchSql = " From DlUserroles Where userId = ?";
		List<DlUserroles> rolesList = dlUserrolesDao.find(searchSql,userId);
		return rolesList;
	}

	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}

	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}

	public IDlFriendDao getDlFriendDao() {
		return dlFriendDao;
	}

	public void setDlFriendDao(IDlFriendDao dlFriendDao) {
		this.dlFriendDao = dlFriendDao;
	}
	
	public DlUsers getUserByUrl(String userUrl){
		DlUsers user = null;
		String searchSql = " From DlUsers u where u.userUrl=?";
		List<DlUsers> userList = dlUsersDao.find(searchSql,userUrl);
		if(userList != null && userList.size()>0)
		{
			user = userList.iterator().next();
		}
		return user;
	}

	public DaoLifeEmail getDaoLifeEmail() {
		return daoLifeEmail;
	}

	public void setDaoLifeEmail(DaoLifeEmail daoLifeEmail) {
		this.daoLifeEmail = daoLifeEmail;
	}

	public IDlAreaDao getDlAreaDao() {
		return dlAreaDao;
	}

	public void setDlAreaDao(IDlAreaDao dlAreaDao) {
		this.dlAreaDao = dlAreaDao;
	}

}

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

import javax.transaction.Transaction;

import org.apache.commons.mail.EmailException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.BeanUtils;

import com.innovation.common.util.Constant;
import com.innovation.common.util.DaoLifeEmail;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.RandomString;
import com.innovation.daolife.action.search.UserSearch;
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

	private IDlUsersDao dlUsersDao;

	private IDlContentatDao dlContentatDao;
	
	private IDlContentDao dlContentDao;

	private IDlHotdaoDao dlHotdaoDao;

	private IDlProductDao dlProductDao;
	
	private IDlUserrolesDao dlUserrolesDao;
	
	private IDlFriendDao dlFriendDao;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * 查询关注的好友
	 * */
	public PaginationSupport getFollowListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select f From DlUsers u INNER JOIN u.dlFollowers f  where  u.userId = "+userId+" and f.userId<>"+userId+"";
		String countsql =" Select count(f.userId) From DlUsers u INNER JOIN u.dlFancers f  where  u.userId = "+userId+" and f.userId<>"+userId+"";
		paginationSupport = dlUsersDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 查询关注我的好友
	 * */
	public PaginationSupport getFanListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select f From DlUsers u INNER JOIN u.dlFancers f  where  u.userId = "+userId+" and f.userId<>"+userId+"";
		String countsql =" Select count(f.userId) From DlUsers u INNER JOIN u.dlFancers f  where  u.userId = "+userId+" and f.userId<>"+userId+"";
		paginationSupport = dlUsersDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 查询客户自己的dao
	 * */
	public PaginationSupport getContentListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select c From DlContent c INNER JOIN c.dlUsers u where  u.userId = "+userId+"";
		String countsql =" Select count(c.contentId) From DlContent c INNER JOIN c.dlUsers u where u.userId = "+userId+"";
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
	 * 查询最热dao友
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
	
	/**
	 * @author fengsn
	 * 查询好友的dao
	 * */
	public PaginationSupport getFollowerContentListByUser(PaginationSupport paginationSupport,Short userId) {
		String querysql = " Select c From DlContent c INNER JOIN c.dlUsers u  INNER JOIN u.dlFancers f where  f.userId = "+userId+" order by c.posttime desc";
		String countsql =" Select count(c.contentId) From DlContent c INNER JOIN c.dlUsers u INNER JOIN u.dlFancers f where  f.userId = "+userId+" order by c.posttime desc";
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
	 * @fsn 通过name或者email获得用户信息
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
	 * @fsn 检查用户名的唯一性
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
	 * @fsn 检查邮箱的唯一性
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
	 * @fsn 检查邮箱的唯一性
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
	 * @fsn 检查昵称的唯一性
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
	 * @fsn 个人资料修改
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

	public void regist(DlUsers user) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		user.setAtMonthNum((short) 0);
		user.setAtSumNum((short) 0);
		user.setAtWeekNum((short) 0);
		user.setFansNum((short) 0);
		user.setFollowNum((short) 0);
		user.setUserInfo("这个人很懒,什么都没留下！");
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
		//保存用户默认角色
		DlUserroles userRoles = new DlUserroles();
		userRoles.setRolesName(Constant.WEBSITE_ROLES_DEFAULT.getStrValue());
		userRoles.setUserId(user.getUserId());
		dlUserrolesDao.save(userRoles);
		//保存自己是自己的好友
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
	 * 发送找回密码email
	 */
	public void resetPasswordEmail(String userName) throws EmailException {
		String authCode = RandomString.getInstance().getRandomString(25);
		DlUsers user = this.getUserByNameOrEmail(userName);
		if (user != null) {
			user.setAuthEmail(authCode);
			this.updateUser(user);
			DaoLifeEmail daoEmail = new DaoLifeEmail();
			// 地址需改成可配置
			String authUrl = "http://www.daolife.com/ResetPassword.action?userId="
					+ user.getUserId() + "&authCode=" + authCode + "";
			daoEmail.sendFindPasswordEmail(user.getUserName(), authUrl, user
					.getMailadres());
		}
	}

	/**
	 * 校验找回密码密钥是否正确
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
	 * 重置密码
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
			DaoLifeEmail daoEmail = new DaoLifeEmail();
			daoEmail.sendSetPasswordSuccessEmail(user.getUserName(),
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

}

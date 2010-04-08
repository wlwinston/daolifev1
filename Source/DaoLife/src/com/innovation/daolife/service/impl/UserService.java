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
import java.util.Date;
import java.util.List;

import javax.transaction.Transaction;

import org.apache.commons.mail.EmailException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.innovation.common.util.Constant;
import com.innovation.common.util.DaoLifeEmail;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.RandomString;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlHotdaoDao;
import com.innovation.daolife.dao.IDlProductDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.dao.impl.DlHotdaoDao;
import com.innovation.daolife.dao.impl.DlProductDao;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlHotdao;
import com.innovation.daolife.model.DlProduct;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;

public class UserService implements IUserService {

	private IDlUsersDao dlUsersDao;

	private IDlContentatDao dlContentatDao;

	private IDlHotdaoDao dlHotdaoDao;

	private IDlProductDao dlProductDao;

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
	 * @fsn ����û�����Ψһ��
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

	public void regist(DlUsers user) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		user.setAtMonthNum((short) 0);
		user.setAtSumNum((short) 0);
		user.setAtWeekNum((short) 0);
		user.setFansNum((short) 0);
		user.setFollowNum((short) 0);
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
			DaoLifeEmail daoEmail = new DaoLifeEmail();
			// ��ַ��ĳɿ�����
			String authUrl = "http://www.daolife.com/ResetPassword.action?userId="
					+ user.getUserId() + "&authCode=" + authCode + "";
			daoEmail.sendFindPasswordEmail(user.getUserName(), authUrl, user
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

}
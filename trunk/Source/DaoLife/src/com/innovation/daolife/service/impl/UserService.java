/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.hibernate.criterion.DetachedCriteria;

import com.innovation.common.util.Constant;
import com.innovation.common.util.DaoLifeEmail;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.RandomString;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;

public class UserService implements IUserService {
	private IDlUsersDao dlUsersDao;
	
	public User getUserById(String id) {
		User user = null ;
		/*try{
			int userId = Integer.parseInt(id);
			user = userDao.get(userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}*/
		return user;
	}

	public PaginationSupport getListBySearch(
			PaginationSupport paginationSupport, UserSearch userSearch) {
		/*DetachedCriteria detachedCriteria = userSearch.getDetachedCriteria();
		paginationSupport = userDao.findPageByCriteria(detachedCriteria, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		String sql = " From User u where u.username=?";
		List<User> userList = userDao.find(sql,"wanglei");
		
		String querysql =" Select u.username,u.password,u.email From User u INNER JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		List<User> newlist = userDao.findByNamedQuery(querysql);
	    List<Followrelation> fList = followrelationDao.find("From Followrelation f where f.follower= 1");
		String countsql =" Select count(*) From User u INNER JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		PaginationSupport a = userDao.findPageByQuery(querysql, countsql, 30, 0);*/
		return paginationSupport;
	}

	
	/**
	 * @fsn
	 * 通过name或者email获得用户信息
	 * */
	public DlUsers getUserByNameOrEmail(String name) {
		DlUsers user = null ;
		String sql =" From DlUsers u where u.userName=? or u.mailadres = ? ";
		List<DlUsers> userList = dlUsersDao.find(sql,new String[]{name, name});
		if(userList.size()>0){
			user = userList.get(0);
		}
		return user;
		
	}
	
	/**
	 * @fsn
	 * 检查用户名的唯一性
	 * */
	public boolean checkUserByName(String name) {
		boolean flag = true;
		String sql =" From DlUsers u where u.userName=?";
		List<DlUsers> userList = dlUsersDao.find(sql,name);
		if(userList.size()>0){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @fsn
	 * 检查邮箱的唯一性
	 * */
	public boolean checkUserByEmail(String name) {
		boolean flag = true;
		String sql =" From DlUsers u where u.mailadres=?";
		List<DlUsers> userList = dlUsersDao.find(sql,name);
		if(userList.size()>0){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @fsn
	 * 检查昵称的唯一性
	 * */
	public boolean checkUserByNickName(String name) {
		boolean flag = true;
		String sql =" From DlUsers u where u.userNickName=?";
		List<DlUsers> userList = dlUsersDao.find(sql,name);
		if(userList.size()>0){
			flag = false;
		}
		return flag;
	}
	
	public void regist(DlUsers user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.setAtMonthNum((short)0);
		user.setAtSumNum((short)0);
		user.setAtWeekNum((short)0);
		user.setFansNum((short)0);
		user.setFollowNum((short)0);
		user.setUserHead("default");
		user.setRecommendInd(Constant.USER_RECOMMENDIND_NO.getStrValue());
		user.setIsclose(new Byte(Constant.USER_ISCLOSED_NO.getStrValue()));
		 Date now  = new Date();
		user.setLastLogin(now);
		String password = user.getPassword();
		String salt = RandomString.getInstance().getRandomString(6);
		user.setSalt(salt);
		password = Md5Util.getInstance().EncoderByMd5(password, salt);
		user.setPassword(password);
		user.setPhotoNum((short)0);
		String nowString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now.getTime()); 
		user.setSignupdate(now);
		user.setUserlock(new Byte(Constant.USER_USERLOCK_NO.getStrValue()));
		user.setAuthEmail(Constant.USER_AUTHMAIL_NOMAIL.getStrValue());
		dlUsersDao.save(user);
		
	}

	

	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateUser(DlUsers user){
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
		if(user !=  null)
		{
			user.setAuthEmail(authCode);
			this.updateUser(user);
			DaoLifeEmail daoEmail = new DaoLifeEmail();
			//地址需改成可配置
			String authUrl = "http://www.daolife.com/ResetPassword.action?userId="+user.getUserId()+"&authCode="+authCode+"";
			daoEmail.sendFindPasswordEmail(user.getUserName(), authUrl, user.getMailadres());
		}
	}
	/**
	 * 校验找回密码密钥是否正确
	 */
	public boolean checkAuthCode(Short userId, String authCode) {
		DlUsers user = dlUsersDao.get(userId);
		if(user !=  null)
		{
			if(authCode.equals(user.getAuthEmail()))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 重置密码
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws EmailException 
	 */
	public void resetPassword(Short userId, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException, EmailException {
		DlUsers user = dlUsersDao.get(userId);
		if(user !=  null)
		{
			String salt = RandomString.getInstance().getRandomString(6);
			String md5Password = Md5Util.getInstance().EncoderByMd5(newPassword, salt);
			user.setPassword(md5Password);
			user.setSalt(salt);
			user.setAuthEmail(RandomString.getInstance().getRandomString(25));
			this.updateUser(user);
			DaoLifeEmail daoEmail = new DaoLifeEmail();
			daoEmail.sendSetPasswordSuccessEmail(user.getUserName(), newPassword, user.getMailadres());
			
		}
		
		
	}
	

	

}

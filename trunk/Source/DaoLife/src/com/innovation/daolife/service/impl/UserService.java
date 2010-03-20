/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;

public class UserService implements IUserService {
	private IUserDao userDao;
	private IFollowrelationDao followrelationDao;
	public User addUser(User user) {
		userDao.save(user);
		return user;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserById(String id) {
		User user = null ;
		try{
			int userId = Integer.parseInt(id);
			user = userDao.get(userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public PaginationSupport getListBySearch(
			PaginationSupport paginationSupport, UserSearch userSearch) {
		DetachedCriteria detachedCriteria = userSearch.getDetachedCriteria();
		paginationSupport = userDao.findPageByCriteria(detachedCriteria, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		String sql = " From User u where u.username=?";
		List<User> userList = userDao.find(sql,"wanglei");
		
		String querysql =" Select u.username,u.password,u.email From User u INNER JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		/*List<User> newlist = userDao.findByNamedQuery(querysql);*/
	    List<Followrelation> fList = followrelationDao.find("From Followrelation f where f.follower= 1");
		String countsql =" Select count(*) From User u INNER JOIN Followrelation f ON u.id = f.userId where f.follower = 1";
		PaginationSupport a = userDao.findPageByQuery(querysql, countsql, 30, 0);
		return paginationSupport;
	}

	public IFollowrelationDao getFollowrelationDao() {
		return followrelationDao;
	}

	public void setFollowrelationDao(IFollowrelationDao followrelationDao) {
		this.followrelationDao = followrelationDao;
	}

}

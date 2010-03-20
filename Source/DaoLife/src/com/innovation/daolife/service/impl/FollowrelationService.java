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
import com.innovation.daolife.dao.impl.FollowrelationDao;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IFollowrelationService;

public class FollowrelationService implements IFollowrelationService {
	private FollowrelationDao followrelationDao;

	public FollowrelationDao getFollowrelationDao() {
		return followrelationDao;
	}

	public void setFollowrelationDao(FollowrelationDao followrelationDao) {
		this.followrelationDao = followrelationDao;
	}
}

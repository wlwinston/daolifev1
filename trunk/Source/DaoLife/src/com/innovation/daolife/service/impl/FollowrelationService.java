/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.dao.impl.DlFriendDao;
import com.innovation.daolife.dao.impl.DlMessagesDao;
import com.innovation.daolife.dao.impl.FollowrelationDao;
import com.innovation.daolife.model.DlFriend;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.common.util.Constant;
import com.innovation.daolife.service.IFollowrelationService;

public class FollowrelationService implements IFollowrelationService {
	private FollowrelationDao followrelationDao;
	private DlFriendDao dlFriendDao;
	private DlMessagesDao dlMessagesDao;
	private IDlUsersDao dlUsersDao;
	
	public FollowrelationDao getFollowrelationDao() {
		return followrelationDao;
	}

	public void setFollowrelationDao(FollowrelationDao followrelationDao) {
		this.followrelationDao = followrelationDao;
	}
	
	/**
	 * 添加关注
	 * @author fsn
	 * */
	public void addFollow(DlFriend friend) {
		dlFriendDao.save(friend);
		DlMessages dlMessages = new DlMessages();
		Short fan = friend.getFidFans();
		Short followId = friend.getFidFollow();
		//user表添加fans记录 follow fans
		DlUsers user = dlUsersDao.get(fan);
		int folloeNum = user.getFollowNum()+1;
		user.setFollowNum((short)folloeNum);
		dlUsersDao.update(user);
		
		DlUsers followuser = dlUsersDao.get(followId);
		int fanNum = followuser.getFansNum()+1;
		followuser.setFansNum((short)fanNum);
		dlUsersDao.update(followuser);
		
		Date now = new Date();
		dlMessages.setMTime(now);
		dlMessages.setUserId(followId);
		dlMessages.setIsread((short)Constant.MESSAGE_ISREAD_NO.getIntValue());
		dlMessages.setMessageBody(Constant.MESSAGE_MESSAGEBODY_ATTENTION.getStrValue());
		dlMessages.setType(Constant.MESSAGE_TYPE_FOLLOW.getStrValue());
		dlMessagesDao.save(dlMessages);
	}
	
	
	public void deleteFollow(DlFriend friend){
		//dlFriendDao
		String sql = " From DlFriend u where u.fidFollow=? or u.fidFans = ? ";
		List<DlFriend> userList = dlFriendDao.find(sql,
				new Short[] { friend.getFidFollow(), friend.getFidFans() });
		friend = userList.get(0);
		dlFriendDao.delete(friend);
		Short fan = friend.getFidFans();
		Short followId = friend.getFidFollow();
		//user表添加fans记录 follow fans
		DlUsers user = dlUsersDao.get(fan);
		int folloeNum = user.getFollowNum()-1;
		user.setFollowNum((short)folloeNum);
		dlUsersDao.update(user);
		
		DlUsers followuser = dlUsersDao.get(followId);
		int fanNum = followuser.getFansNum()-1;
		followuser.setFansNum((short)fanNum);
		dlUsersDao.update(followuser);
	}

	public DlFriendDao getDlFriendDao() {
		return dlFriendDao;
	}

	public void setDlFriendDao(DlFriendDao dlFriendDao) {
		this.dlFriendDao = dlFriendDao;
	}

	public DlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}

	public void setDlMessagesDao(DlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}

	public IDlUsersDao getDlUsersDao() {
		return dlUsersDao;
	}

	public void setDlUsersDao(IDlUsersDao dlUsersDao) {
		this.dlUsersDao = dlUsersDao;
	}
	
}

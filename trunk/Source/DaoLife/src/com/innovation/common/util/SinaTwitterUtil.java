package com.innovation.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import weibo4j.Status;
import weibo4j.Weibo;

import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlContenttopicDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlContenttopic;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDaoContentBodyConvertService;


public class SinaTwitterUtil {
	
	private IDlContentDao dlContentDao;
	private IDlUsersDao dlUsersDao;
	private IDlContentatDao dlContentatDao;
	public IDlContentatDao getDlContentatDao() {
		return dlContentatDao;
	}

	public void setDlContentatDao(IDlContentatDao dlContentatDao) {
		this.dlContentatDao = dlContentatDao;
	}

	public IDlContenttopicDao getDlContenttopicDao() {
		return dlContenttopicDao;
	}

	public void setDlContenttopicDao(IDlContenttopicDao dlContenttopicDao) {
		this.dlContenttopicDao = dlContenttopicDao;
	}

	public IDlTopicDao getDlTopicDao() {
		return dlTopicDao;
	}

	public void setDlTopicDao(IDlTopicDao dlTopicDao) {
		this.dlTopicDao = dlTopicDao;
	}

	public IDlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}

	public void setDlMessagesDao(IDlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}


	private IDlContenttopicDao dlContenttopicDao;
	private IDlTopicDao dlTopicDao;
	private IDlMessagesDao dlMessagesDao;
	public IDlUsersDao getDlUsersDao() {
		return dlUsersDao;
	}

	public void setDlUsersDao(IDlUsersDao dlUsersDao) {
		this.dlUsersDao = dlUsersDao;
	}


	private IDaoContentBodyConvertService daoContentBodyConvert;

	public IDaoContentBodyConvertService getDaoContentBodyConvert() {
		return daoContentBodyConvert;
	}

	public void setDaoContentBodyConvert(
			IDaoContentBodyConvertService daoContentBodyConvert) {
		this.daoContentBodyConvert = daoContentBodyConvert;
	}

	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}

	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}
	
	public void getAtTwitter(){
		try {
        	Weibo weibo = getWeibo(false,"1656783420","88589871604");
        	List<Status> list = weibo.getMentions();
			DlUsers user = (DlUsers)dlUsersDao.get((short)1);
        	for(Status status : list) {
        		String userName  = status.getUser().getScreenName();
        		String id= String.valueOf(status.getId());
        		System.out.println( userName + "  : "+status.getText());
        		//System.out.println( status.getRetweetDetails().toString());
        		List contentList =  dlContentDao.find(" From DlContent c where c.sinaTextId=?", id);
        		if(contentList.size() == 0)
        		{
        			DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
    				.covertContent(userName + "  : "+status.getText());
        			customerDaoDao.getDlContent().setSinaTextId(id);
        			this.saveDao(customerDaoDao, user);
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param customerDaoDao
	 * @param user
	 * @throws Exception
	 * @author winston
	 */
	private DlCustomerDaoEntry saveDao(DlCustomerDaoEntry customerDaoDao,
			DlUsers user) throws Exception {
		DlContent content = customerDaoDao.getDlContent();
		content.setPosttime(new Date());
		//默认被转数量和被顶数量
		content.setRetwittNum((short) 0);
		content.setUpNum((short) 0);
		content.setReplyNum((short)0);
		content.setStatus(Constant.CONTENT_STATUS_INIT.getStrValue());
		content.setUserId(user.getUserId());
		// 叨发布形式类型
		content.setType("网页");
		
		dlContentDao.save(content);

		// 获取被at人数目
		List<DlContentat> atList = customerDaoDao.getDlContentatList();
		String uidString = "WHERE 1<>1 ";
		for (int i = 0; i < atList.size(); i++) {
			DlContentat atContentat = atList.get(i);
			atContentat.setContentId(content.getContentId());
			dlContentatDao.save(atContentat);
			short atUserID = atContentat.getStatusUid();
			uidString += " OR user_id = " + atUserID;
		}
		List<DlContenttopic> topicList = customerDaoDao.getDlContenttopicList();
		for (int i = 0; i < topicList.size(); i++) {
			DlContenttopic dlContenttopic = topicList.get(i);
			dlContenttopic.setContentId(content.getContentId());
			dlContenttopicDao.save(dlContenttopic);
		}
		// 修改用户被@数目
		uidString = "UPDATE dl_users SET at_week_num = at_week_num + 1,at_month_num = at_month_num + 1,at_sum_num = at_sum_num + 1 "
				+ uidString;
		dlUsersDao.update(uidString);
		// 消息LIST
		List<DlMessages> messageList = customerDaoDao.getDlMessageList();
		for (int i = 0; i < atList.size(); i++) {

			dlMessagesDao.save(messageList.get(i));

		}
		return customerDaoDao;
	}
	
	private static Weibo getWeibo(boolean isOauth,String userId,String password) {
		Weibo weibo = new Weibo();
		if(isOauth) {//oauth验证方式 args[0]:访问的token；args[1]:访问的密匙
			//weibo.setToken(args[0], args[1]);
		}else {//用户登录方式
    		weibo.setUserId(userId);//用户名/ID
    		weibo.setPassword(password);//密码
		}
		return weibo;
	}
}

package com.innovation.daolife.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.innovation.common.util.Constant;
import com.innovation.common.util.WebConfig;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlActivity;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlTopic;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDaoContentBodyConvertService;

public class DaoContentBodyConvertService implements IDaoContentBodyConvertService{
	private IDlUsersDao dlUsersDao;
	private IDlContentDao dlContentDao;
	private IDlContentatDao dlContentatDao;
	private IDlTopicDao dlTopicDao;
	public DlCustomerDaoEntry covertContent(String contextBody) {
		if(contextBody == null)
		{
			return null;
		}
		DlCustomerDaoEntry dlCustomerDaoEntry = new DlCustomerDaoEntry();
		List<DlUsers> userList = null;
		String[] contextAfterAt = contextBody.split("@");
		int contextSize = contextAfterAt.length;
		if(contextSize > 1)
		{
			String atSql = " From DlUsers Where 1 <> 1";
			for(int i =1 ;i < contextSize ; i++)
			{
				if(contextAfterAt[i].indexOf(" ")>0)
				{
					String atName = contextAfterAt[i].substring(0, contextAfterAt[i].indexOf(" "));
					atSql += " or userNickName = '"+atName+"'";
				}
				//System.out.println(atName);
			}
			
			userList = dlUsersDao.find(atSql);
			DlUsers user = null;
			for(int i =0 ;i<userList.size();i++)
			{
				DlContentat dlContentat = new DlContentat();
				user = userList.get(i);
				dlContentat.setStatusUid(user.getUserId());
				dlContentat.setStatusUname(user.getUserNickName());
				dlContentat.setStatusType("网页");
				dlContentat.setStatusId(user.getUserId());
				dlCustomerDaoEntry.getDlContentatList().add(dlContentat);
				DlMessages message = new DlMessages();
				message.setIsread((short)Constant.MESSAGE_ISREAD_NO.getIntValue());
				message.setMessageBody(Constant.MESSAGE_MESSAGEBODY_ATMESSAGE.getStrValue());
				message.setType(Constant.MESSAGE_TYPE_AT.getStrValue());
				message.setMTime(new Date());
				message.setUserId(user.getUserId());
				dlCustomerDaoEntry.getDlMessageList().add(message);
			}
			
		}
		DlContent  content = new DlContent();
		String topicName = "";
		Short topicId = 0;
		if( contextBody.indexOf("#") >= 0)
		{
			String contextAfer = contextBody.substring(contextBody.indexOf("#")+1);
			if(contextAfer.indexOf(" ") > 0)
			{
				topicName = contextAfer.substring(0, contextAfer.indexOf(" "));
				List<DlTopic> topicList = dlTopicDao.find(" From DlTopic WHERE topicBody = '"+topicName+"' and open = 1");
				if(topicList.size()>0)
				{
					topicId = topicList.get(0).getTopicId();
					
				}
			}
		}
		content.setTopicid(topicId);
		if( userList != null && userList.size() >0){
			for(int i =0 ;i<userList.size();i++)
			{
				DlUsers newUser = userList.get(i);
				String newUserLinkString =  "<a href ='"+WebConfig.linkUserPrefix+newUser.getUserId()+"' target='_blank'>@"+newUser.getUserNickName()+" </a>";
				contextBody = contextBody.replaceAll("@"+newUser.getUserNickName()+" ",newUserLinkString);
			}
		}
		if(topicId != 0 )
		{
			String newTopicLinkString =  "<a href ='"+WebConfig.linkTopicPrefix+topicId+"' target='_blank'>#"+topicName+" </a>";
			contextBody = contextBody.replaceAll("#"+topicName+" ",newTopicLinkString);
		}
		content.setContentBody(contextBody);
		dlCustomerDaoEntry.setDlContent(content);
		
		
		
		
		return dlCustomerDaoEntry;
	}
	public IDlUsersDao getDlUsersDao() {
		return dlUsersDao;
	}
	public void setDlUsersDao(IDlUsersDao dlUsersDao) {
		this.dlUsersDao = dlUsersDao;
	}
	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}
	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}
	public IDlContentatDao getDlContentatDao() {
		return dlContentatDao;
	}
	public void setDlContentatDao(IDlContentatDao dlContentatDao) {
		this.dlContentatDao = dlContentatDao;
	}
	
	public IDlTopicDao getDlTopicDao() {
		return dlTopicDao;
	}
	public void setDlTopicDao(IDlTopicDao dlTopicDao) {
		this.dlTopicDao = dlTopicDao;
	}

}

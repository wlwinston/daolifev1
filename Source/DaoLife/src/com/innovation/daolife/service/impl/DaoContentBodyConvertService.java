package com.innovation.daolife.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.FastHashMap;
import org.apache.log4j.Logger;

import com.innovation.common.util.Constant;
import com.innovation.common.util.WebConfig;
import com.innovation.daolife.dao.IDlCommentDao;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlActivity;
import com.innovation.daolife.model.DlComment;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlContenttopic;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlTopic;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDaoContentBodyConvertService;

public class DaoContentBodyConvertService implements IDaoContentBodyConvertService{
	private static Logger logger = Logger.getLogger(DaoContentBodyConvertService.class);
	private IDlUsersDao dlUsersDao;
	private IDlContentDao dlContentDao;
	private IDlCommentDao dlCommentDao;
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
				else{
					String atName = contextAfterAt[i];
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
		String[] contextAfterSharp = contextBody.split("#");
		
		List<DlTopic> topicList = null;
		int contextAfterSharpLength = contextAfterSharp.length;
		if(contextBody.lastIndexOf("#") == contextBody.length()-1)
		{
			contextAfterSharpLength++;
		}
		FastHashMap topicMap = new FastHashMap();
		List<String> topicNameList = new ArrayList<String>();
		if(contextAfterSharpLength > 1 ){
			int topicLength = (int)((contextAfterSharpLength-1)/2);
			String topicSql = " From DlTopic Where 1 <> 1 ";
			for(int i = 0; i<topicLength; i++)
			{
				topicName = contextAfterSharp[2*i+1];
				logger.info("topicName ===="+topicName);
				topicSql += " or topicBody = '"+topicName+"'";
				topicNameList.add(contextAfterSharp[2*i+1]);
			}
			topicList = dlTopicDao.find(topicSql);
			for(Iterator<DlTopic> it = topicList.iterator();it.hasNext();)
			{
				DlTopic topic = it.next();
				topicMap.put(topic.getTopicBody(), topic);
			}
			for(int i = 0 ;i<topicNameList.size();i++)
			{
				String topicNameStr = topicNameList.get(i);
				if(topicMap.get(topicNameStr) != null)
				{
					DlTopic topic = (DlTopic)(topicMap.get(topicNameStr));
					if(topic.getOpen() == 1 )
					{
						DlContenttopic dlContenttopic = new DlContenttopic();
						dlContenttopic.setTopicId(topic.getTopicId());
						dlCustomerDaoEntry.getDlContenttopicList().add(dlContenttopic);
						String newTopicLinkString =  "<a href ='"+WebConfig.linkTopicPrefix+topic.getTopicId()+"' target='_blank'>#"+topicNameStr+"#</a>";
						contextBody = contextBody.replaceAll("#"+topicNameStr+"#",newTopicLinkString);
					}
				}
				else{
					DlTopic dlTopic = new DlTopic();
					dlTopic.setOpen((short)1);
					dlTopic.setTopicBody(topicNameStr);
					dlTopicDao.saveOrUpdate(dlTopic);
					DlContenttopic dlContenttopic = new DlContenttopic();
					dlContenttopic.setTopicId(dlTopic.getTopicId());
					dlCustomerDaoEntry.getDlContenttopicList().add(dlContenttopic);
					String newTopicLinkString =  "<a href ='"+WebConfig.linkTopicPrefix+dlTopic.getTopicId()+"' target='_blank'>#"+topicNameStr+"#</a>";
					contextBody = contextBody.replaceAll("#"+topicNameStr+"#",newTopicLinkString);
					
				}
			}
		}
		
		content.setTopicid(topicId);
		if( userList != null && userList.size() >0){
			for(int i =0 ;i<userList.size();i++)
			{
				DlUsers newUser = userList.get(i);
				String newUserLinkString =  "<a href ='"+WebConfig.linkUserPrefix+newUser.getUserId()+"' target='_blank'>@"+newUser.getUserNickName()+"</a>";
				contextBody = contextBody.replaceAll("@"+newUser.getUserNickName()+"",newUserLinkString);
			}
		}
		
		content.setContentBody(contextBody);
		dlCustomerDaoEntry.setDlContent(content);

		
		return dlCustomerDaoEntry;
	}
	
	/**
	 * @author fengsn
	 * @param contextBody
	 * 转化回复中的body
	 * 
	 * */
	public DlCustomerDaoEntry covertComment(String contextBody,Short contentId,Short relaId){
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
				else{
					String atName = contextAfterAt[i];
					atSql += " or userNickName = '"+atName+"'";
				}
			}
			
			userList = dlUsersDao.find(atSql);
		}
		
		//在message表中添加提示信息
		DlContent dlContent = dlContentDao.get(contentId);
		DlMessages message = new DlMessages();
		message.setIsread((short) Constant.MESSAGE_ISREAD_NO.getIntValue());
		message.setMessageBody(Constant.MESSAGE_MESSAGEBODY_COMMENT
				.getStrValue());
		message.setType(Constant.MESSAGE_TYPE_COMMENT.getStrValue());
		message.setMTime(new Date());
		message.setUserId(dlContent.getUserId());
		dlCustomerDaoEntry.getDlMessageList().add(message);
		
		//处理#***# 为活动
		DlComment  dlComment = new DlComment();
		String topicName = "";
		String[] contextAfterSharp = contextBody.split("#");
		
		List<DlTopic> topicList = null;
		int contextAfterSharpLength = contextAfterSharp.length;
		if(contextBody.lastIndexOf("#") == contextBody.length()-1)
		{
			contextAfterSharpLength++;
		}
		FastHashMap topicMap = new FastHashMap();
		List<String> topicNameList = new ArrayList<String>();
		if(contextAfterSharpLength > 1 ){
			int topicLength = (int)((contextAfterSharpLength-1)/2);
			String topicSql = " From DlTopic Where 1 <> 1 ";
			for(int i = 0; i<topicLength; i++)
			{
				topicName = contextAfterSharp[2*i+1];
				logger.info("topicName ===="+topicName);
				topicSql += " or topicBody = '"+topicName+"'";
				topicNameList.add(contextAfterSharp[2*i+1]);
			}
			topicList = dlTopicDao.find(topicSql);
			for(Iterator<DlTopic> it = topicList.iterator();it.hasNext();)
			{
				DlTopic topic = it.next();
				topicMap.put(topic.getTopicBody(), topic);
			}
			for(int i = 0 ;i<topicNameList.size();i++)
			{
				String topicNameStr = topicNameList.get(i);
				if(topicMap.get(topicNameStr) != null)
				{
					DlTopic topic = (DlTopic)(topicMap.get(topicNameStr));
					if(topic.getOpen() == 1 )
					{
						DlContenttopic dlContenttopic = new DlContenttopic();
						dlContenttopic.setTopicId(topic.getTopicId());
						dlCustomerDaoEntry.getDlContenttopicList().add(dlContenttopic);
						String newTopicLinkString =  "<a href ='"+WebConfig.linkTopicPrefix+topic.getTopicId()+"' target='_blank'>#"+topicNameStr+"#</a>";
						contextBody = contextBody.replaceAll("#"+topicNameStr+"#",newTopicLinkString);
					}
				}
				else{
					DlTopic dlTopic = new DlTopic();
					dlTopic.setOpen((short)1);
					dlTopic.setTopicBody(topicNameStr);
					dlTopicDao.saveOrUpdate(dlTopic);
					DlContenttopic dlContenttopic = new DlContenttopic();
					dlContenttopic.setTopicId(dlTopic.getTopicId());
					dlCustomerDaoEntry.getDlContenttopicList().add(dlContenttopic);
					String newTopicLinkString =  "<a href ='"+WebConfig.linkTopicPrefix+dlTopic.getTopicId()+"' target='_blank'>#"+topicNameStr+"#</a>";
					contextBody = contextBody.replaceAll("#"+topicNameStr+"#",newTopicLinkString);
				}
			}
		}
		//处理@** 
		if( userList != null && userList.size() >0){
			for(int i =0 ;i<userList.size();i++)
			{
				DlUsers newUser = userList.get(i);
				String newUserLinkString =  "<a href ='"+WebConfig.linkUserPrefix+newUser.getUserId()+"' target='_blank'>@"+newUser.getUserNickName()+"</a>";
				contextBody = contextBody.replaceAll("@"+newUser.getUserNickName()+"",newUserLinkString);
			}
		}
		
		dlComment.setCommentBody(contextBody);
		dlComment.setContentId(contentId);
		//处理reply 如果直接回复的dao replyUid存放叨的userId
		if(relaId==null){
			dlComment.setRelaUserid(dlContent.getUserId());
		}else{
			DlComment comment =dlCommentDao.get(relaId);
			dlComment.setRelaCommentid(relaId);
			dlComment.setRelaUserid(comment.getUserId());
		}
		dlCustomerDaoEntry.setDlComment(dlComment);
		
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

	public IDlCommentDao getDlCommentDao() {
		return dlCommentDao;
	}

	public void setDlCommentDao(IDlCommentDao dlCommentDao) {
		this.dlCommentDao = dlCommentDao;
	}

}

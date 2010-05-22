package com.innovation.daolife.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.dao.DataAccessException;

import com.innovation.common.util.Constant;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.service.IDlMessagesService;

public class DlMessagesService implements IDlMessagesService {

	private IDlMessagesDao dlMessagesDao;
	
	/**
	 * @author fengsn
	 * 统计message
	 * 站内信息统计
	 * */
	public List getMessages(Short userId){
		String sql = "select count(i.userId),i.type from DlMessages as i where userId="+userId+" and isread=0 group  by type";
		List messageList = dlMessagesDao.findWithoutT(sql);
		return messageList;
	}
	
	/**
	 * @author fengsn
	 * 调用接口 将message里面的未读置为已读
	 * */
	public void dealReadState(Short userId,String type){
		String updateSql = "update dl_messages set isread=1 where  isread=0 and type ='"+type+"' and user_id="+userId+"";
		try {
			dlMessagesDao.update(updateSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IDlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}
	public void setDlMessagesDao(IDlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}
	
	
}

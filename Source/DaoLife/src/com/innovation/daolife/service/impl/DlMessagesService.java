package com.innovation.daolife.service.impl;

import java.util.List;

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
	
	public IDlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}
	public void setDlMessagesDao(IDlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}
	
	
}

package com.innovation.daolife.service;

import java.util.List;

import com.innovation.daolife.model.DlMessages;

public interface IDlMessagesService {
	
	public List getMessages(Short userId);
	
	public void dealReadState(Short userId,String type);

}

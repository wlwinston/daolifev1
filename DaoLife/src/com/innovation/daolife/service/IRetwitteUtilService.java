package com.innovation.daolife.service;

import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlCustomerDaoEntry;

public interface IRetwitteUtilService {
	
	public void retwitte(DlCustomerDaoEntry customerDaoEntry,Short originId);
}

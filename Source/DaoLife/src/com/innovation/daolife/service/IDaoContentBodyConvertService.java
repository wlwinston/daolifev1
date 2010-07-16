package com.innovation.daolife.service;

import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlCustomerDaoEntry;

public interface IDaoContentBodyConvertService {
	
	public DlCustomerDaoEntry covertContent(String contextBody);
	public DlCustomerDaoEntry covertComment(String contextBody,Short contentId,Short relaId);
}

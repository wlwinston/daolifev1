package com.innovation.daolife.service.impl;

import java.util.Date;
import java.util.List;

import com.innovation.common.util.Constant;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlHotdaoDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlActivity;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlHotdao;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlTopic;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDaoContentBodyConvertService;
import com.innovation.daolife.service.IRetwitteUtilService;

public class RetwitteUtilService implements IRetwitteUtilService{

	private IDlContentDao dlContentDao;
	private IDlHotdaoDao dlHotdaoDao;
	
	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}
	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}
	public void retwitte(DlCustomerDaoEntry customerDaoEntry, Short originId) {
		
		DlContent originContent = dlContentDao.get(originId);
		if( originContent != null )
		{
			String originSource = originContent.getOriginAllid();
			if(originSource != null && originSource.trim().length()>0)
			{
				String[] originIdArray = originSource.split(",");
				for(int i =0;i < originIdArray.length;i++)
				{
					DlContent originOld = dlContentDao.get(Short.parseShort(originIdArray[i]));
					if(originOld != null)
					{
						originOld.setRetwittNum((short)(originOld.getRetwittNum()+1));
						dlContentDao.saveOrUpdate(originOld);
						DlHotdao hotdaoOld = dlHotdaoDao.get(originOld.getContentId());
						if(hotdaoOld != null && hotdaoOld.getHotdaoId() != null)
						{
							hotdaoOld.setRetwittNum(originOld.getRetwittNum());
							dlHotdaoDao.saveOrUpdate(hotdaoOld);
						}
					}
					
				}
				originSource += ","+originId;
			}
			else{
				originSource = String.valueOf(originId);
			}
			originContent.setRetwittNum((short)(originContent.getRetwittNum()+1));
			customerDaoEntry.getDlContent().setOriginId(originId);
			customerDaoEntry.getDlContent().setOriginAllid(originSource);
			
		}
		
	}
	public IDlHotdaoDao getDlHotdaoDao() {
		return dlHotdaoDao;
	}
	public void setDlHotdaoDao(IDlHotdaoDao dlHotdaoDao) {
		this.dlHotdaoDao = dlHotdaoDao;
	}
	

}

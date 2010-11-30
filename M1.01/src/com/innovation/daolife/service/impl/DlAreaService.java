package com.innovation.daolife.service.impl;

import java.util.List;

import com.innovation.daolife.dao.IDlAreaDao;
import com.innovation.daolife.service.IDlAreaService;

public class DlAreaService implements IDlAreaService {

	private IDlAreaDao dlAreaDao;
	
	public List getAreaInfo(String parentId){
		String sql = "select i.areaId,i.areaName from DlArea as i where parentId='"+parentId+"'";
		List areaList = dlAreaDao.findWithoutT(sql);
		return areaList;
	}

	public IDlAreaDao getDlAreaDao() {
		return dlAreaDao;
	}

	public void setDlAreaDao(IDlAreaDao dlAreaDao) {
		this.dlAreaDao = dlAreaDao;
	}
}

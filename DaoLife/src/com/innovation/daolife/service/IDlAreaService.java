package com.innovation.daolife.service;

import java.util.List;

import com.innovation.daolife.model.DlArea;

public interface IDlAreaService {

	public List getAreaInfo(String parentId);
	
	public DlArea getProvinceByCity(String cityId);
	
	public List getDlAreaListInfo(String parentId);
}

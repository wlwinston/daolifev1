package com.innovation.daolife.model;

import java.util.ArrayList;
import java.util.List;

public class DlCustomerDaoEntry implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4572019710227042512L;
	
	
	private DlContent dlContent = new DlContent();
	private List<DlContentat> dlContentatList = new ArrayList<DlContentat>();
	private List<DlMessages> dlMessageList = new ArrayList<DlMessages>();
	public DlContent getDlContent() {
		return dlContent;
	}
	public void setDlContent(DlContent dlContent) {
		this.dlContent = dlContent;
	}
	public List<DlContentat> getDlContentatList() {
		return dlContentatList;
	}
	public void setDlContentatList(List<DlContentat> dlContentatList) {
		this.dlContentatList = dlContentatList;
	}
	public List<DlMessages> getDlMessageList() {
		return dlMessageList;
	}
	public void setDlMessageList(List<DlMessages> dlMessageList) {
		this.dlMessageList = dlMessageList;
	}
	
	
}

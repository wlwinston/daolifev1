package com.innovation.common.util;

public class WebConfig {
	//网站地址
	public static String linkWebPrefix="";
	//网站地址
	public static String linkUserPrefix="";
	//网站话题ID
	public static String linkTopicPrefix ="";
	public  String getLinkWebPrefix() {
		return linkWebPrefix;
	}
	public  void setLinkWebPrefix(String linkWebPrefix) {
		WebConfig.linkWebPrefix = linkWebPrefix;
	}
	public  String getLinkUserPrefix() {
		return linkUserPrefix;
	}
	public  void setLinkUserPrefix(String linkUserPrefix) {
		WebConfig.linkUserPrefix = linkUserPrefix;
	}
	public  String getLinkTopicPrefix() {
		return linkTopicPrefix;
	}
	public  void setLinkTopicPrefix(String linkTopicPrefix) {
		WebConfig.linkTopicPrefix = linkTopicPrefix;
	}
	
	
	
	
	
}

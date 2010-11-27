package com.innovation.common.util;

public class WebConfig {
	//cookie domain
	public static String cookieDomain = "";
	//网站地址
	public static String linkWebPrefix="";
	//网站地址
	public static String linkUserPrefix="";
	//网站话题ID
	public static String linkTopicPrefix ="";
	//新浪登录回写URL
	public static String sinaRewriteUrl  ="";
	//新浪发叨登录回写URL
	public static String sinaAddDaoLoginRewriteUrl = "";
	
	public  String getSinaAddDaoLoginRewriteUrl() {
		return sinaAddDaoLoginRewriteUrl;
	}
	public  void setSinaAddDaoLoginRewriteUrl(String sinaAddDaoLoginRewriteUrl) {
		WebConfig.sinaAddDaoLoginRewriteUrl = sinaAddDaoLoginRewriteUrl;
	}
	public  String getSinaRewriteUrl() {
		return sinaRewriteUrl;
	}
	public  void setSinaRewriteUrl(String sinaRewriteUrl) {
		WebConfig.sinaRewriteUrl = sinaRewriteUrl;
	}
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
	public  String getCookieDomain() {
		return cookieDomain;
	}
	public  void setCookieDomain(String cookieDomain) {
		WebConfig.cookieDomain = cookieDomain;
	}
	
	
	
	
	
}

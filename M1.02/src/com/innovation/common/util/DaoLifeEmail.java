package com.innovation.common.util;

import org.apache.commons.mail.EmailException;

public class DaoLifeEmail {
	private String hostName = "";//这里必须是SMTP地址,填写之前查询该mail网的smtp
	private String userName = "";//邮箱名
	private String userPass = "";//邮箱密码
	private String fromAd = "";//发送人地址
	private String fromName = "";//发送人姓名
	
	public void sendRegistSuccessEmail(String recevierName,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("感谢您注册Daolife");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",您已经成功注册Daolife。");
		emailTools.sendSimpleEmail();
	}
	
	public  static  void main(String args[]) throws EmailException{
		DaoLifeEmail daoEmail = new DaoLifeEmail();
		//daoEmail.sendRegistSuccessEmail("高健", "liamgao2009@gmail.com");
		String authCode = RandomString.getInstance().getRandomString(25);
		daoEmail.sendFindPasswordEmail("高健", "<a href=\""+WebConfig.linkWebPrefix+"/resetPassword.action?uid=1&authCode="+authCode+"\" >http://www.daolife.com/resetPassword.action?uid=1&authCode="+authCode+"</a>", "liamgao2009@gmail.com");
	}
	
	public void sendFindPasswordEmail(String recevierName,String authUrl,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("Daolife找回密码");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",您的找回密码地址为："+authUrl+"。");
		emailTools.sendSimpleEmail();
	}
	
	
	
	
	public void sendSetPasswordSuccessEmail(String recevierName,String newPassword,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("Daolife找回密码成功");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",您的密码已经成功找回，新密码为："+newPassword);
		emailTools.sendSimpleEmail();
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getFromAd() {
		return fromAd;
	}

	public void setFromAd(String fromAd) {
		this.fromAd = fromAd;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	

}

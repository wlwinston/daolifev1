package com.innovation.common.util;

import org.apache.commons.mail.EmailException;

public class DaoLifeEmail {
	private String hostName = "smtp.163.com";//���������SMTP��ַ,��д֮ǰ��ѯ��mail����smtp
	private String userName = "wangleimsh@163.com";//������
	private String userPass = "WANGLEI";//��������
	private String fromAd = "wangleimsh@163.com";//�����˵�ַ
	private String fromName = "Daolife";//����������
	
	public void sendRegistSuccessEmail(String recevierName,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("��л��ע��Daolife");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",���Ѿ��ɹ�ע��Daolife��");
		emailTools.sendSimpleEmail();
	}
	
	public  static  void main(String args[]) throws EmailException{
		DaoLifeEmail daoEmail = new DaoLifeEmail();
		//daoEmail.sendRegistSuccessEmail("�߽�", "liamgao2009@gmail.com");
		String authCode = RandomString.getInstance().getRandomString(25);
		daoEmail.sendFindPasswordEmail("�߽�", "<a href=\"http://www.daolife.com/resetPassword.action?uid=1&authCode="+authCode+"\" >http://www.daolife.com/resetPassword.action?uid=1&authCode="+authCode+"</a>", "liamgao2009@gmail.com");
	}
	
	public void sendFindPasswordEmail(String recevierName,String authUrl,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("Daolife�һ�����");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",�����һ������ַΪ��"+authUrl+"��");
		emailTools.sendSimpleEmail();
	}
	
	
	
	
	public void sendSetPasswordSuccessEmail(String recevierName,String newPassword,String recevierEmail) throws EmailException{
		EmailUtils emailTools = new EmailUtils();
		emailTools.setFromAd(fromAd);
		emailTools.setFromName(fromName);
		emailTools.setHostName(hostName);
		emailTools.setUserName(userName);
		emailTools.setUserPass(userPass);
		emailTools.setSubject("Daolife�һ�����ɹ�");
		emailTools.setToAd(recevierEmail);
		emailTools.setToName(recevierName);
		emailTools.setMsg(recevierName+",���������Ѿ��ɹ��һأ�������Ϊ��"+newPassword);
		emailTools.sendSimpleEmail();
	}
	
	

}

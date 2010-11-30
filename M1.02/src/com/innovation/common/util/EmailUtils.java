package com.innovation.common.util;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 * 邮件发送工具类
 * @author winston
 */
public class EmailUtils {
    private static Logger logger = Logger.getLogger(EmailUtils.class);
	private String hostName = null;//SMTP服务器
	private String userName = null;//用户名
	private String userPass = null;//密码
	private String toAd = null;//收信的地址
	private String toName = null;//收信人名称
	private String fromAd = null;//发信人地址
	private String fromName = null;//发信人名称
	private String subject = null;//主题
	private String msg = null;//邮件内容
	private String url = null;//
	private String name = null;//
	private ArrayList pathArrayList = null;//附件ArrayList

	/**
	 * 发送简单邮件
	 * @throws EmailException
	 */
	public void sendSimpleEmail() throws EmailException{
		HtmlEmail email = new HtmlEmail();
		//设置邮件字符集
		email.setCharset("GBK");
		//设置SMTP服务器 
		email.setHostName(hostName);
		//设置用户名和密码
		email.setAuthentication(userName,userPass);
		//设置收信人信息
		email.addTo(toAd,toName);
		//设置发信人信息
		email.setFrom(fromAd,fromName);
		//设置主题
		email.setSubject(subject);
		//设置邮件内容
		email.setHtmlMsg(msg);
		//发送邮件
		email.send();
		logger.info(subject+"发送成功");
  }
	/**
	 * 发送带附件邮件
	 * @throws EmailException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMultiPartEmail() throws EmailException, UnsupportedEncodingException{
		MultiPartEmail email = new MultiPartEmail();
		email.setCharset("GBK");
		email.setHostName(hostName);
		email.setAuthentication(userName,userPass);	
		email.addTo(toAd,toName);
		email.setFrom(fromAd,fromName);
		email.setSubject(subject);
		email.setMsg(msg);
		EmailAttachment attachment = null;
		Iterator pal = pathArrayList.iterator();
		while (pal.hasNext()) {
			String path = (String) pal.next();
			attachment = new EmailAttachment();
			attachment.setPath(path);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("");
	        String[] s = StringUtils.split(path,"/");
			attachment.setName(MimeUtility.encodeText(s[s.length-1]));
			email.attach(attachment);
		}
		email.send();
		logger.info(subject+"发送成功");
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
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getToAd() {
		return toAd;
	}
	public void setToAd(String toAd) {
		this.toAd = toAd;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList getPathArrayList() {
		return pathArrayList;
	}
	public void setPathArrayList(ArrayList pathArrayList) {
		this.pathArrayList = pathArrayList;
	}
}


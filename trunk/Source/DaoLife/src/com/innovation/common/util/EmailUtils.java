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
 * 发送邮件
 * @author winston
 */
public class EmailUtils {
    private static Logger logger = Logger.getLogger(EmailUtils.class);
	private String hostName = null;//这里必须是SMTP地址,填写之前查询该mail网的smtp
	private String userName = null;//邮箱名
	private String userPass = null;//邮箱密码
	private String toAd = null;//接收人地址
	private String toName = null;//接收人姓名
	private String fromAd = null;//发送人地址
	private String fromName = null;//发送人姓名
	private String subject = null;//邮件标题
	private String msg = null;//邮件内容
	private String url = null;//附件远程地址
	private String name = null;//附件名
	private ArrayList pathArrayList = null;//多附件名组成的ArrayList

	public void sendSimpleEmail() throws EmailException{
		HtmlEmail email = new HtmlEmail();
		//设置邮件文字编码
		email.setCharset("GBK");
		//邮箱主页 
		email.setHostName(hostName);
		//登陆邮件服务器的用户名和密码
		email.setAuthentication(userName,userPass);
		//接收人
		email.addTo(toAd,toName);
		//发送人
		email.setFrom(fromAd,fromName);
		//标题
		email.setSubject(subject);
		//邮件内容
		email.setHtmlMsg(msg);
		//发送
		email.send();
		logger.info(subject+" 发送成功");
  }
	
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
			String path = (String) pal.next();//获取附件地址
			attachment = new EmailAttachment();
			attachment.setPath(path);// 加载附件地址如："h:/图片/小屁孩.gif"
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("中文描述");//附件描述
	        String[] s = StringUtils.split(path,"/");//将附件地址按"/",目的是为了获取文件名
			attachment.setName(MimeUtility.encodeText(s[s.length-1]));//附件名称中文处理,s[s.length-1])为文件名
			email.attach(attachment);//添加附件
		}
		email.send();
		logger.info(subject+" 发送成功");
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


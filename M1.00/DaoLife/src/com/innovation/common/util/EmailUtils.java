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
 * �����ʼ�
 * @author winston
 */
public class EmailUtils {
    private static Logger logger = Logger.getLogger(EmailUtils.class);
	private String hostName = null;//���������SMTP��ַ,��д֮ǰ��ѯ��mail���smtp
	private String userName = null;//������
	private String userPass = null;//��������
	private String toAd = null;//�����˵�ַ
	private String toName = null;//����������
	private String fromAd = null;//�����˵�ַ
	private String fromName = null;//����������
	private String subject = null;//�ʼ�����
	private String msg = null;//�ʼ�����
	private String url = null;//����Զ�̵�ַ
	private String name = null;//������
	private ArrayList pathArrayList = null;//�฽������ɵ�ArrayList

	public void sendSimpleEmail() throws EmailException{
		HtmlEmail email = new HtmlEmail();
		//�����ʼ����ֱ���
		email.setCharset("GBK");
		//������ҳ 
		email.setHostName(hostName);
		//��½�ʼ���������û��������
		email.setAuthentication(userName,userPass);
		//������
		email.addTo(toAd,toName);
		//������
		email.setFrom(fromAd,fromName);
		//����
		email.setSubject(subject);
		//�ʼ�����
		email.setHtmlMsg(msg);
		//����
		email.send();
		logger.info(subject+"发送成功");
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
			String path = (String) pal.next();//��ȡ������ַ
			attachment = new EmailAttachment();
			attachment.setPath(path);// ���ظ�����ַ�磺"h:/ͼƬ/Сƨ��.gif"
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("");//��������
	        String[] s = StringUtils.split(path,"/");//��������ַ��"/",Ŀ����Ϊ�˻�ȡ�ļ���
			attachment.setName(MimeUtility.encodeText(s[s.length-1]));//����������Ĵ���,s[s.length-1])Ϊ�ļ���
			email.attach(attachment);//��Ӹ���
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


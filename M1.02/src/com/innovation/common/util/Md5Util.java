package com.innovation.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	private static Md5Util md5 = null;

	private Md5Util() {
	}

	public static synchronized Md5Util getInstance() {
		if (md5 == null) {
			md5 = new Md5Util();
		}
		return md5;
	}

	/**
	 * ����MD5���м���
	 * 
	 * @param str
	 *            �����ܵ��ַ���
	 * @return ���ܺ���ַ���
	 * @throws NoSuchAlgorithmException
	 *             û�����ֲ�����ϢժҪ���㷨
	 * @throws UnsupportedEncodingException
	 */
	public String EncoderByMd5(String str,String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String md5Value = "";
		if( salt != null ){
			md5Value =  firstEncoderByMd5(firstEncoderByMd5(str)+salt);
		}
		return md5Value;
	}

	private static String firstEncoderByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		// ȷ�����㷽��
		// MessageDigest md5 = MessageDigest.getInstance("MD5");
		// BASE64Encoder base64en = new BASE64Encoder();
		// ���ܺ���ַ���
		// String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		// return newstr;
		StringBuffer buf = new StringBuffer(""); 
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(str.getBytes()); 
		byte b[] = md.digest(); 
		int i; 
		for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0){
				i+= 256; 
			}
			if(i<16){
				buf.append("0"); 
			}
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();		
	}
	/**
	 * �ж��û������Ƿ���ȷ
	 * 
	 * @param newpasswd
	 *            �û����������
	 * @param oldpasswd
	 *            ���ݿ��д洢�����룭���û������ժҪ
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean checkpassword(String newpasswd, String salt, String oldpasswd)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (firstEncoderByMd5(firstEncoderByMd5(newpasswd)+salt).equals(oldpasswd))
			return true;
		else
			return false;
	}
}

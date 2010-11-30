package com.innovation.common.util;

import java.util.Random;
/**
 * 获得n位随机字符串
 * int length为想要得到字符串的长度
 *@author wuyin
 */
public class RandomString {
	
	private static RandomString randomString = null;

	private RandomString() {
	}

	public static synchronized RandomString getInstance() {
		if (randomString == null) {
			randomString = new RandomString();
		}
		return randomString;
	}
	public String getRandomString(int length){ 
	    StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
	    StringBuffer sb = new StringBuffer(); 
	    Random r = new Random(); 
	    int range = buffer.length(); 
	    for (int i = 0; i < length; i ++){ 
	        sb.append(buffer.charAt(r.nextInt(range))); 
	    } 
	    return sb.toString(); 
	}
}

package com.innovation.common.util;

/**
 * 用户自定义异常
 */
public class ProjectException  extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5677045215647280874L;

	public ProjectException(){
	     super();
	}
	    
	private String message;
	
	 public ProjectException(String message){
	     this.message = message;
	 }
	    
	 public String getMessage() {
	  return message;
	 }
}

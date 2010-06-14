package com.innovation.common.util;

/**
 * �û��Զ����쳣
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

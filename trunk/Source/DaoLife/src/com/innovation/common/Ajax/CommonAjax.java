package com.innovation.common.Ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.collections.FastHashMap;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IUserService;


/**
 * ����Ϊ����ҳ������dwr���÷���ʱ�����Խ����е������ķ���д�������
 * @author Winston
 *
 */
public class CommonAjax {
	
	private IUserService userService;
	
	private IDlUsersDao dlUsersDao;
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public User getUserById(String id){
		User user = userService.getUserById(id);
		return user;
	}
	/**
	 * @author fsn
	 * ����û�����email�����Ƿ�Ψһ
	 * ����true Ψһ
	 * */
	public boolean checkUserNameorEmail(String check){
		boolean flag = true;
		DlUsers users = userService.getUserByNameOrEmail(check);
		if(users!=null){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @author fsn
	 * ����ǳ��Ƿ�Ψһ
	 * ����true Ψһ
	 * */
	public boolean checkUserNickName(String checke){
		boolean flag = true;
		String sql =" From DlUsers u where u.userNickName=? ";
		List<DlUsers> userList = dlUsersDao.find(sql,checke);
		if(userList.size()==0){
			flag = false;
		}
		return flag;
	}
	
	
}		

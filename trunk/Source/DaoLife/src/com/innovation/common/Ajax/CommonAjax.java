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
 * 此类为当在页面上用dwr调用方法时，可以将所有的这样的方法写入此类中
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
	 * 检查用户名和email邮箱是否唯一
	 * 返回true 唯一
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
	 * 检查昵称是否唯一
	 * 返回true 唯一
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

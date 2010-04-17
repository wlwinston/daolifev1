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
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.FastHashMap;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.innovation.common.util.Constant;
import com.innovation.common.util.IPUtils;
import com.innovation.common.util.ProjectException;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IUserService;


/**
 * 此类为当在页面上用dwr调用方法时，可以将所有的这样的方法写入此类中
 * @author Winston
 *
 */
public class CommonAjax {
	
	private IUserService userService;
	
	private IDlUsersDao dlUsersDao;
	
	private IDlDaoService dlDaoService;
	
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public User getUserById(String id){
		User user = userService.getUserById(id);
		return user;
	}
	/**
	 * @author fsn
	 * 检查用户名是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserName(String username){
		boolean flag = userService.checkUserByName(username);
		return flag;
	}
	
	/**
	 * @author fsn
	 * 检查邮箱是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserEmail(String email){
		boolean flag = userService.checkUserByEmail(email);
		return flag;
	}
	
	/**
	 * @author fsn
	 * 检查昵称是否唯一
	 * 返回true 唯一
	 * */
	public boolean checkUserNickName(String check){
		boolean flag = userService.checkUserByNickName(check);
		return flag;
	}
	
	
	/**
	 * 顶叨
	 * @param daoId 被顶叨ID
	 * @return 返回错误信息，如果为空则顶叨成功
	 * @author winston
	 */
	public String upDao(String daoId)
	{
		String upResult = "";
		try{
			WebContext request = WebContextFactory.get();
			
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute(Constant.SESSION_USER_KEY.getStrValue())!=null)
			{
				DlUsers user =  (DlUsers) session.getAttribute(Constant.SESSION_USER_KEY.getStrValue());
				String ip = new IPUtils().getIpAddr(request);
				dlDaoService.addUpDao(daoId,user,ip);
			}
			else{
				upResult = Constant.UPDAO_ERRORMESSAGE_NOUSER.getStrValue();
			}
		}
		catch(Exception e){
			if(e instanceof ProjectException)
			{
				upResult = e.getMessage();
			}
		}
		return upResult;
	}
	
	public IDlDaoService getDlDaoService() {
		return dlDaoService;
	}
	public void setDlDaoService(IDlDaoService dlDaoService) {
		this.dlDaoService = dlDaoService;
	}
	
	
	
	
}		

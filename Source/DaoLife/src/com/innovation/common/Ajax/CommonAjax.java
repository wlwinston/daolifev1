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
 * ����Ϊ����ҳ������dwr���÷���ʱ�����Խ����е������ķ���д�������
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
	 * ����û����Ƿ�Ψһ
	 * ����true Ψһ
	 * */
	public boolean checkUserName(String username){
		boolean flag = userService.checkUserByName(username);
		return flag;
	}
	
	/**
	 * @author fsn
	 * ��������Ƿ�Ψһ
	 * ����true Ψһ
	 * */
	public boolean checkUserEmail(String email){
		boolean flag = userService.checkUserByEmail(email);
		return flag;
	}
	
	/**
	 * @author fsn
	 * ����ǳ��Ƿ�Ψһ
	 * ����true Ψһ
	 * */
	public boolean checkUserNickName(String check){
		boolean flag = userService.checkUserByNickName(check);
		return flag;
	}
	
	
	/**
	 * ��߶
	 * @param daoId ����߶ID
	 * @return ���ش�����Ϣ�����Ϊ����߶�ɹ�
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

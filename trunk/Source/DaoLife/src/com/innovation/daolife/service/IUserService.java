/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.mail.EmailException;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlUserroles;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;

public interface IUserService {
	public User addUser(User user);
	public User getUserById(String id);
	public PaginationSupport getListBySearch(
			PaginationSupport paginationSupport, UserSearch userSearch);
	public void regist(DlUsers user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	public DlUsers getUserByNameOrEmail(String name);
	public boolean checkUserByName(String name);
	public boolean checkUserByEmail(String name);
	public boolean checkUserByNickName(String name);
	public void update(DlUsers newUser,DlUsers oldUser) throws Exception;
	public void resetPasswordEmail(String userName) throws EmailException;
	public boolean checkAuthCode(Short userId, String authCode);
	public void updatePsw(DlUsers user, String newpsw) throws Exception;
	public void resetPassword(Short userId, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException, EmailException;
	public List<DlUserroles> getRolesListByUserId(Short userId);
	public PaginationSupport getContentListByUser(PaginationSupport paginationSupport,Short userId);
	public PaginationSupport getFollowerContentListByUser(PaginationSupport paginationSupport,Short userId);
}

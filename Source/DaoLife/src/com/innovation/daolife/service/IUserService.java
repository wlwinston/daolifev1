/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;

public interface IUserService {
	public User addUser(User user);
	public User getUserById(String id);
	public PaginationSupport getListBySearch(
			PaginationSupport paginationSupport, UserSearch userSearch);
	public void regist(DlUsers user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	public DlUsers getUserByNameOrEmail(String name);
}

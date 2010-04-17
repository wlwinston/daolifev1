/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.mail.EmailException;
import org.springframework.dao.DataAccessException;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;

public interface IDlDaoService {
	public void addDao(DlUsers user ,String contextBody) throws Exception ;

	public void addRetwitteDao(DlUsers user, String contextBody, Short orgDaoId) throws Exception ;

	public void addUpDao(String daoId, DlUsers user,String userIp) throws Exception;
	
}

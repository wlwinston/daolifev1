/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.mail.EmailException;
import org.springframework.dao.DataAccessException;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.User;

public interface IDlDaoService {
	public DlCustomerDaoEntry addDao(DlUsers user ,String contextBody) throws Exception ;

	public DlCustomerDaoEntry addRetwitteDao(DlUsers user, String contextBody, Short orgDaoId) throws Exception ;

	public void addUpDao(String daoId, DlUsers user,String userIp) throws Exception;
	
	public PaginationSupport getAtContentListByUser(PaginationSupport paginationSupport,Short userId);
	
	public PaginationSupport getHotDao(PaginationSupport paginationSupport);
	
	public List<DlContent> getTopicContent(Short topicId);
	
	public PaginationSupport getContentListByTime(PaginationSupport paginationSupport);

}

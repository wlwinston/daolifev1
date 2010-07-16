/** 
 * @author fengsn
 */
package com.innovation.daolife.service;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlUsers;

public interface IDlCommentService {
	
	public DlCustomerDaoEntry addComment(DlUsers user, String contextBody,Short contentId,Short relaId)throws Exception ;

	public boolean deleteDao(Short id);
	
	public PaginationSupport getCommentListByContentId(PaginationSupport paginationSupport, Short contentId);
	
	public PaginationSupport getCommentMeList(PaginationSupport paginationSupport, Short userId);
	
	public PaginationSupport getMyCommentList(PaginationSupport paginationSupport, Short userId);
}

package com.innovation.daolife.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.innovation.common.util.Constant;
import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.dao.IDlCommentDao;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.model.DlComment;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlHotdao;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IDaoContentBodyConvertService;
import com.innovation.daolife.service.IDlCommentService;

public class DlCommentService implements IDlCommentService {
	
	private IDlTopicDao dlTopicDao;
	private IDlCommentDao dlCommentDao;
	private IDlContentDao dlContentDao;
	private IDlMessagesDao dlMessagesDao;
	private IDaoContentBodyConvertService daoContentBodyConvert;
	
	/**
	 * 添加评论 回复
	 * 
	 * @param user
	 *          回复人
	 * @param contentId
	 *          回复的叨的Id
	 * @param contextBody
	 * @author fengsn
	 */
	public DlCustomerDaoEntry addComment(DlUsers user, String contextBody,Short contentId,Short relaId)
			throws Exception {
		DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
				.covertComment(contextBody,contentId,relaId);
		this.saveComment(customerDaoDao, user);
		return customerDaoDao;
	}
	
	/**
	 * @param user
	 * @throws Exception
	 * @author fengsn
	 */
	private DlCustomerDaoEntry saveComment(DlCustomerDaoEntry customerDaoDao,
			DlUsers user) throws Exception {
		DlComment  dlComment = customerDaoDao.getDlComment();
		dlComment.setPosttime(new Date());
		dlComment.setUserId(user.getUserId());
		dlComment.setStatus(Constant.COMMENT_STATUS_INIT.getStrValue());
		dlCommentDao.save(dlComment);
		
		List<DlMessages> messageList = customerDaoDao.getDlMessageList();
		for (int i = 0; i < messageList.size(); i++) {
			dlMessagesDao.save(messageList.get(i));
		}
		
		return customerDaoDao;
	}
	
	/**删除评论 回复 */
	public boolean deleteComment(Short id){
		DlComment  dlComment = dlCommentDao.get(id);
		dlComment.setStatus("1");
		dlCommentDao.update(dlComment);
		DlContent content = dlContentDao.get(dlComment.getContentId());
		content.setReplyNum((short)(content.getReplyNum()- 1));
		dlContentDao.save(content);
		return true;
	}
	
	/**
	 * @author fengsn 
	 * 查询dao的回复 返回分页
	 */
	public PaginationSupport getCommentListByContentId(
			PaginationSupport paginationSupport, Short contentId) {
		String querysql = " Select c From DlComment c where  c.status='0' and c.contentId = "
				+ contentId + "order by c.posttime desc";
		String countsql = " Select count(c.contentId) From DlComment c where  c.status='0' and c.contentId = "
				+ contentId + "order by c.posttime desc";
		paginationSupport = dlCommentDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex()); 
		List<DlComment> itemList = paginationSupport.getItems();
		for(Iterator<DlComment> it = itemList.iterator();it.hasNext();)
		{
			DlComment comment = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(comment.getDlUsers(), user);
			comment.setDlUsers(user);
		}
		return paginationSupport;
	}
	
	/**回复 评论我的*/
	public PaginationSupport getCommentMeList(
			PaginationSupport paginationSupport, Short userId) {
		String querysql = " Select c From DlComment c where  c.status='0' and c.relaUserid = "
				+ userId + " order by c.posttime desc";
		String countsql = " Select count(c.contentId) From DlComment c where  c.status='0' and c.relaUserid = "
				+ userId + " order by c.posttime desc";
		paginationSupport = dlCommentDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		List<DlComment> itemList = paginationSupport.getItems();
		for(Iterator<DlComment> it = itemList.iterator();it.hasNext();)
		{
			DlComment comment = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(comment.getDlUsers(), user);
			comment.setDlUsers(user);
		}
		return paginationSupport;
	}
	
	/**我的回复 评论*/
	public PaginationSupport getMyCommentList(
			PaginationSupport paginationSupport, Short userId) {
		String querysql = " Select c From DlComment c where  c.status='0' and c.userId = "
				+ userId + "order by c.posttime desc";
		String countsql = " Select count(c.contentId) From DlComment c where  c.status='0' and c.userId = "
				+ userId + "order by c.posttime desc";
		paginationSupport = dlCommentDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		List<DlComment> itemList = paginationSupport.getItems();
		for(Iterator<DlComment> it = itemList.iterator();it.hasNext();)
		{
			DlComment comment = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(comment.getDlUsers(), user);
			comment.setDlUsers(user);
		}
		return paginationSupport;
	}
	
	public IDlTopicDao getDlTopicDao() {
		return dlTopicDao;
	}
	public void setDlTopicDao(IDlTopicDao dlTopicDao) {
		this.dlTopicDao = dlTopicDao;
	}
	public IDlCommentDao getDlCommentDao() {
		return dlCommentDao;
	}
	public void setDlCommentDao(IDlCommentDao dlCommentDao) {
		this.dlCommentDao = dlCommentDao;
	}
	public IDlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}
	public void setDlMessagesDao(IDlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}
	public IDaoContentBodyConvertService getDaoContentBodyConvert() {
		return daoContentBodyConvert;
	}
	public void setDaoContentBodyConvert(
			IDaoContentBodyConvertService daoContentBodyConvert) {
		this.daoContentBodyConvert = daoContentBodyConvert;
	}

	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}

	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}
	
}

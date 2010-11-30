/** 
 * @author Winston
 * @version 1.0 
 * Creation date: MAR 8, 2010 22:46:49 
 */
package com.innovation.daolife.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.mail.EmailException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.omg.CORBA.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;

import com.innovation.common.util.Constant;
import com.innovation.common.util.DaoLifeEmail;
import com.innovation.common.util.Md5Util;
import com.innovation.common.util.PaginationSupport;
import com.innovation.common.util.ProjectException;
import com.innovation.common.util.RandomString;
import com.innovation.daolife.action.search.UserSearch;
import com.innovation.daolife.dao.IDlContentDao;
import com.innovation.daolife.dao.IDlContentatDao;
import com.innovation.daolife.dao.IDlContenttopicDao;
import com.innovation.daolife.dao.IDlHotdaoDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUplogDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.dao.impl.DlContentDao;
import com.innovation.daolife.dao.impl.DlHotdaoDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
import com.innovation.daolife.model.DlContenttopic;
import com.innovation.daolife.model.DlCustomerDaoEntry;
import com.innovation.daolife.model.DlHotdao;
import com.innovation.daolife.model.DlMessages;
import com.innovation.daolife.model.DlUplog;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.model.Followrelation;
import com.innovation.daolife.model.User;
import com.innovation.daolife.service.IDaoContentBodyConvertService;
import com.innovation.daolife.service.IDlDaoService;
import com.innovation.daolife.service.IRetwitteUtilService;
import com.innovation.daolife.service.IUserService;

public class DlDaoService implements IDlDaoService {
	private IDlUplogDao dlUplogDao;
	private IDlUsersDao dlUsersDao;
	private IDlContentDao dlContentDao;
	private IDlContentatDao dlContentatDao;
	private IDlContenttopicDao dlContenttopicDao;
	private IDlTopicDao dlTopicDao;
	private IDlMessagesDao dlMessagesDao;
	private IDaoContentBodyConvertService daoContentBodyConvert;
	private IRetwitteUtilService retwitteUtil;
	private IDlHotdaoDao dlHotdaoDao;

	/**
	 * 新发一条叨
	 * 
	 * @param user
	 *          发叨人
	 * @param contextBody
	 * @author winston
	 */
	public DlCustomerDaoEntry addDao(DlUsers user, String contextBody)
			throws Exception {
		DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
				.covertContent(contextBody);
		this.saveDao(customerDaoDao, user);
		return customerDaoDao;
	}
	/**
	 * @author fengsn
	 * 删除一条dao
	 * @param user发叨人 id dao的id
	 * */
	public boolean deleteDao(DlUsers user,Short id){
		boolean flag = true;
		//删除dao句数据库记录
		DlContent contentDao = dlContentDao.get(id);
		contentDao.setStatus("1");
		dlContentDao.update(contentDao);
		String sql = " From DlContentat u where u.contentId=?";
		List<DlContentat> contentatList = dlContentatDao.find(sql, id);
		if(contentatList.size()>0){
			for(int i=0;i<contentatList.size();i++){
				DlContentat temp = contentatList.get(i);
				dlContentatDao.delete(temp);
			}
		}
		return flag;
	}
	

	/**
	 * @param customerDaoDao
	 * @param user
	 * @throws Exception
	 * @author winston
	 */
	private DlCustomerDaoEntry saveDao(DlCustomerDaoEntry customerDaoDao,
			DlUsers user) throws Exception {
		DlContent content = customerDaoDao.getDlContent();
		content.setPosttime(new Date());
		//默认被转数量和被顶数量
		content.setRetwittNum((short) 0);
		content.setUpNum((short) 0);
		content.setReplyNum((short)0);
		content.setStatus(Constant.CONTENT_STATUS_INIT.getStrValue());
		content.setUserId(user.getUserId());
		// 叨发布形式类型
		content.setType("网页");

		dlContentDao.save(content);

		// 获取被at人数目
		List<DlContentat> atList = customerDaoDao.getDlContentatList();
		String uidString = "WHERE 1<>1 ";
		for (int i = 0; i < atList.size(); i++) {
			DlContentat atContentat = atList.get(i);
			atContentat.setContentId(content.getContentId());
			dlContentatDao.save(atContentat);
			short atUserID = atContentat.getStatusUid();
			uidString += " OR user_id = " + atUserID;
		}
		List<DlContenttopic> topicList = customerDaoDao.getDlContenttopicList();
		for (int i = 0; i < topicList.size(); i++) {
			DlContenttopic dlContenttopic = topicList.get(i);
			dlContenttopic.setContentId(content.getContentId());
			dlContenttopicDao.save(dlContenttopic);
		}
		// 修改用户被@数目
		uidString = "UPDATE dl_users SET at_week_num = at_week_num + 1,at_month_num = at_month_num + 1,at_sum_num = at_sum_num + 1 "
				+ uidString;
		dlUsersDao.update(uidString);
		// 消息LIST
		List<DlMessages> messageList = customerDaoDao.getDlMessageList();
		for (int i = 0; i < atList.size(); i++) {

			dlMessagesDao.save(messageList.get(i));

		}
		return customerDaoDao;
	}

	/**
	 * 
	 * @param user
	 * @param contextBody
	 *            转叨内容
	 * @param orgDaoId
	 *            ԭ߶ID
	 * @author winston
	 */
	public DlCustomerDaoEntry addRetwitteDao(DlUsers user, String contextBody,
			Short orgDaoId) throws Exception {

		//发叨工具
		DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
				.covertContent(contextBody);
		//转到工具类
		retwitteUtil.retwitte(customerDaoDao, orgDaoId);

		// 保存叨߶
		this.saveDao(customerDaoDao, user);
		return customerDaoDao;
	}

	/**
	 * @author fengsn 
	 */
	public PaginationSupport getAtContentListByUser(
			PaginationSupport paginationSupport, Short userId) {
		String querysql = " Select c From DlUsers u INNER JOIN u.contentatmes c where  u.userId = "
				+ userId + "order by c.posttime desc";
		String countsql = " Select count(c.userId) From DlUsers u INNER JOIN u.contentatmes c where  u.userId = "
				+ userId + "order by c.posttime desc";
		// paginationSupport = dlContentatDao.findPageByQuery(querysql,
		// countsql, paginationSupport.getPageSize(),
		// paginationSupport.getStartIndex());
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		return paginationSupport;
	}
	
	/**
	 * @author fengsn 
	 */
	public PaginationSupport getRewriteInfoList(
			PaginationSupport paginationSupport, Short contentId) {
		String querysql = " Select c From DlContent c  where  c.originAllid = '"+contentId+"' or c.originAllid like '%,"+contentId+",%' or c.originAllid like '"+contentId+",%' or c.originAllid like '%,"+contentId+"'";
		String countsql =" Select count(c.contentId) From DlContent c  where  c.originAllid = '"+contentId+"' or c.originAllid like '%,"+contentId+",%' or c.originAllid like '"+contentId+",%' or c.originAllid like '%,"+contentId+"'";
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql, paginationSupport.getPageSize(), paginationSupport.getStartIndex());
		List<DlContent> itemList = paginationSupport.getItems();
		for(Iterator<DlContent> it = itemList.iterator();it.hasNext();)
		{
			DlContent dlContent = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlContent.getDlUsers(), user);
			dlContent.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * */
	public PaginationSupport getContentListByTime(
			PaginationSupport paginationSupport){
		String querysql = " Select c From DlContent c where c.status='0' order by posttime desc";
		String countsql = " Select count(c.contentId) From DlContent c where c.status='0'order by posttime desc";
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		List<DlContent> itemList = paginationSupport.getItems();
		for(Iterator<DlContent> it = itemList.iterator();it.hasNext();)
		{
			DlContent dlContent = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlContent.getDlUsers(), user);
			dlContent.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);

		return paginationSupport;
	}
	/**
	 * @author fengsn �������dao
	 * return 获取最热叨
	 */
	public PaginationSupport getHotDao(PaginationSupport paginationSupport) {
		Short daoNum = this.getdaoNum();
		String querysql = " Select c From DlHotdao c where  c.daonum = "
				+ daoNum + " order by upSum desc";
		String countsql = " Select count(c.hotdaoId) From DlHotdao c where  c.daonum = "
				+ daoNum + " order by upSum desc";
		paginationSupport = dlHotdaoDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		
		List<DlHotdao> itemList = paginationSupport.getItems();
		for(Iterator<DlHotdao> it = itemList.iterator();it.hasNext();)
		{
			DlHotdao dlHotdao = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlHotdao.getDlUsers(), user);
			dlHotdao.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);
		
		return paginationSupport;
	}

	/**
	 * ��@author fengsnֵ
	 * 获取最热叨句的期数
	 */
	private Short getdaoNum() {
		String sql = " select (i.daonum) from DlHotdao as i";
		// dlHotdaoDao
		List<Short> daoList = dlHotdaoDao.findWithoutT(sql);
		Short result = 1;
		if (daoList.size() > 0) {
			for (int i = 0; i < daoList.size(); i++) {
				Short tmp_dao = daoList.get(i);
				if (tmp_dao > result) {
					result = tmp_dao;
				}
			}
		}
		return result;
	}
	
	/**��߶����
	 * @param daoId
	 * @param user
	 * @param userIp
	 * @author winston
	 */
	public void addUpDao(String daoId, DlUsers user, String userIp)
			throws Exception {
		short userId = user.getUserId();
		String hql = " From DlUplog Where userId = ? And hotdaoId = ? ";
		Object[] sqlVlaue = { userId, Short.valueOf(daoId) };
		List<DlUplog> uplogList = dlUplogDao.find(hql, sqlVlaue);
		if (uplogList != null && uplogList.size() > 0) {
			ProjectException moreThanOne = new ProjectException(
					Constant.UPDAO_ERRORMESSAGE_ONLYONE.getStrValue());
			throw moreThanOne;
		}

		//保存顶叨记录

		DlUplog uplog = new DlUplog();
		uplog.setHotdaoId(Short.valueOf(daoId));
		uplog.setUpIp(userIp);
		uplog.setUserId(userId);
		uplog.setUptime(new Date());
		dlUplogDao.save(uplog);

		//将叨被顶数目加1

		DlContent content = dlContentDao.get(uplog.getHotdaoId());
		content.setUpNum((short) (content.getUpNum() + 1));
		dlContentDao.saveOrUpdate(content);
		DlHotdao hotdao = dlHotdaoDao.get(uplog.getHotdaoId());
		if(hotdao != null && hotdao.getHotdaoId() != null)
		{
			hotdao.setUpSum(content.getUpNum());
			dlHotdaoDao.saveOrUpdate(hotdao);
		}
	}
	
	/**
	 * 获取主题dao
	 * */
	public List<DlContent> getTopicContent(Short topicId){
		String sql = "From DlContent u where u.topicid=?";
		List<DlContent> result = dlContentDao.find(sql, topicId);
		return result;
	}

	/**
	 * @author fengsn
	 * 获得话题相关dao
	 * */
	public PaginationSupport getTopicListContent(
			PaginationSupport paginationSupport,Short topicId){
		String querysql = " Select c From DlTopic t INNER JOIN t.topicContent c where  t.topicId = "
			+ topicId + " and c.status='0' order by c.upNum  desc";
		String countsql = " Select count(c.contentId) From DlTopic t INNER JOIN t.topicContent c where  t.topicId = "
			+ topicId + " and c.status='0' order by c.upNum desc";
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		List<DlContent> itemList = paginationSupport.getItems();
		for(Iterator<DlContent> it = itemList.iterator();it.hasNext();)
		{
			DlContent dlContent = it.next();
			DlUsers user = new DlUsers();
			BeanUtils.copyProperties(dlContent.getDlUsers(), user);
			dlContent.setDlUsers(user);
		}
		paginationSupport.setItems(itemList);

		return paginationSupport;
	}
	
	/******************************查询返回List*******************************/
//	public List getMyAndFriendsDao(int size){
//		String sql = " select top "+size+" * from DlContent order by c.posttime  desc";
//		List<Short> daoList = dlContentDao.findWithoutT(sql);
//		return daoList
//	}
//	
//	public List getMoreDaos(int size,Short daoId){
//		String sql = " select top "+size+" * from DlContent where contentId<"+daoId+" order by c.posttime  desc";
//		List<Short> daoList = dlContentDao.findWithoutT(sql);
//		return daoList
//	}
//	
//	public List getNewDaos(Short daoId){
//		String sql = " select * from DlContent where contentId>="+daoId+" order by c.posttime  desc";
//		List<Short> daoList = dlContentDao.findWithoutT(sql);
//		return daoList
//	}
//	
//	private boolean checkIsProduct(Short daoId){
//		
//	}
	/************************************************************************/
	public IDaoContentBodyConvertService getDaoContentBodyConvert() {
		return daoContentBodyConvert;
	}

	public void setDaoContentBodyConvert(
			IDaoContentBodyConvertService daoContentBodyConvert) {
		this.daoContentBodyConvert = daoContentBodyConvert;
	}

	public IDlUsersDao getDlUsersDao() {
		return dlUsersDao;
	}

	public void setDlUsersDao(IDlUsersDao dlUsersDao) {
		this.dlUsersDao = dlUsersDao;
	}

	public IDlContentDao getDlContentDao() {
		return dlContentDao;
	}

	public void setDlContentDao(IDlContentDao dlContentDao) {
		this.dlContentDao = dlContentDao;
	}

	public IDlContentatDao getDlContentatDao() {
		return dlContentatDao;
	}

	public void setDlContentatDao(IDlContentatDao dlContentatDao) {
		this.dlContentatDao = dlContentatDao;
	}

	public IDlTopicDao getDlTopicDao() {
		return dlTopicDao;
	}

	public void setDlTopicDao(IDlTopicDao dlTopicDao) {
		this.dlTopicDao = dlTopicDao;
	}

	public IDlMessagesDao getDlMessagesDao() {
		return dlMessagesDao;
	}

	public void setDlMessagesDao(IDlMessagesDao dlMessagesDao) {
		this.dlMessagesDao = dlMessagesDao;
	}

	public IRetwitteUtilService getRetwitteUtil() {
		return retwitteUtil;
	}

	public void setRetwitteUtil(IRetwitteUtilService retwitteUtil) {
		this.retwitteUtil = retwitteUtil;
	}

	public IDlUplogDao getDlUplogDao() {
		return dlUplogDao;
	}

	public void setDlUplogDao(IDlUplogDao dlUplogDao) {
		this.dlUplogDao = dlUplogDao;
	}

	public IDlHotdaoDao getDlHotdaoDao() {
		return dlHotdaoDao;
	}

	public void setDlHotdaoDao(IDlHotdaoDao dlHotdaoDao) {
		this.dlHotdaoDao = dlHotdaoDao;
	}

	public DlContent getDao(Short daoId) throws Exception {
		DlContent content = dlContentDao.get(daoId);
		DlUsers user = new DlUsers();
		BeanUtils.copyProperties(content.getDlUsers(), user);
		content.setDlUsers(user);
		return content;
	}

	public IDlContenttopicDao getDlContenttopicDao() {
		return dlContenttopicDao;
	}

	public void setDlContenttopicDao(IDlContenttopicDao dlContenttopicDao) {
		this.dlContenttopicDao = dlContenttopicDao;
	}

}

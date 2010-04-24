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
import java.util.Date;
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
import com.innovation.daolife.dao.IDlHotdaoDao;
import com.innovation.daolife.dao.IDlMessagesDao;
import com.innovation.daolife.dao.IDlTopicDao;
import com.innovation.daolife.dao.IDlUplogDao;
import com.innovation.daolife.dao.IDlUsersDao;
import com.innovation.daolife.dao.IFollowrelationDao;
import com.innovation.daolife.dao.IUserDao;
import com.innovation.daolife.dao.impl.DlHotdaoDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlContentat;
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
	private IDlTopicDao dlTopicDao;
	private IDlMessagesDao dlMessagesDao;
	private IDaoContentBodyConvertService daoContentBodyConvert;
	private IRetwitteUtilService retwitteUtil;
	private IDlHotdaoDao dlHotdaoDao;

	/**
	 * 发新叨处理
	 * 
	 * @param user
	 *            当前操作人对象
	 * @param contextBody
	 *            叨内容
	 * @author winston
	 */
	public DlCustomerDaoEntry addDao(DlUsers user, String contextBody)
			throws Exception {
		// 叨内容处理工具处理叨内容 如@和#
		DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
				.covertContent(contextBody);
		// 保存新叨
		this.saveDao(customerDaoDao, user);
		return customerDaoDao;
	}

	/**
	 * 保存新叨
	 * 
	 * @param customerDaoDao
	 *            新叨逻辑对象
	 * @param user
	 *            操作人
	 * @throws Exception
	 * @author winston
	 */
	private DlCustomerDaoEntry saveDao(DlCustomerDaoEntry customerDaoDao,
			DlUsers user) throws Exception {
		DlContent content = customerDaoDao.getDlContent();
		// 设置叨发布时间为当前时间
		content.setPosttime(new Date());
		// 设置初始转发次数和被顶次数为0
		content.setRetwittNum((short) 0);
		content.setUpNum((short) 0);
		// 设置叨的状态为初始状态
		content.setStatus(Constant.CONTENT_STATUS_INIT.getStrValue());
		// 设置发布人
		content.setUserId(user.getUserId());
		// 设置发布手段……此处待扩展（会包括WAP,手机客户端）
		content.setType("网页");
		dlContentDao.save(content);
		// 保存被@记录
		List<DlContentat> atList = customerDaoDao.getDlContentatList();
		String uidString = "WHERE 1<>1 ";
		for (int i = 0; i < atList.size(); i++) {
			DlContentat atContentat = atList.get(i);
			atContentat.setContentId(content.getContentId());
			dlContentatDao.save(atContentat);
			short atUserID = atContentat.getStatusUid();
			uidString += " OR user_id = " + atUserID;
		}
		// 被@人的被@次数加一
		uidString = "UPDATE dl_users SET at_week_num = at_week_num + 1,at_month_num = at_month_num + 1,at_sum_num = at_sum_num + 1 "
				+ uidString;
		dlUsersDao.update(uidString);
		// 给被@人保存被@新消息
		List<DlMessages> messageList = customerDaoDao.getDlMessageList();
		for (int i = 0; i < atList.size(); i++) {

			dlMessagesDao.save(messageList.get(i));

		}
		return customerDaoDao;
	}

	/**
	 * 转叨处理
	 * 
	 * @param user
	 *            当前操作人对象
	 * @param contextBody
	 *            转叨内容
	 * @param orgDaoId
	 *            原叨ID
	 * @author winston
	 */
	public DlCustomerDaoEntry addRetwitteDao(DlUsers user, String contextBody,
			Short orgDaoId) throws Exception {
		// 叨内容处理工具处理叨内容 如@和#
		DlCustomerDaoEntry customerDaoDao = daoContentBodyConvert
				.covertContent(contextBody);
		// 转发工具处理转发ID 如被转发叨转发次数加一
		retwitteUtil.retwitte(customerDaoDao, orgDaoId);
		// 保存新叨
		this.saveDao(customerDaoDao, user);
		return customerDaoDao;
	}

	/**
	 * @author fengsn 查询@用户的dao内容
	 */
	public PaginationSupport getAtContentListByUser(
			PaginationSupport paginationSupport, Short userId) {
		String querysql = " Select c From DlUsers u INNER JOIN u.contentatmes c where  u.userId = "
				+ userId + "";
		String countsql = " Select count(c.userId) From DlUsers u INNER JOIN u.contentatmes c where  u.userId = "
				+ userId + "";
		// paginationSupport = dlContentatDao.findPageByQuery(querysql,
		// countsql, paginationSupport.getPageSize(),
		// paginationSupport.getStartIndex());
		paginationSupport = dlContentatDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		return paginationSupport;
	}

	/**
	 * @author fengsn 查询@用户的dao内容
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
		return paginationSupport;
	}

	/**
	 * 查询hotdao中的daonum最大值
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

	/**
	 * 顶叨处理
	 * 
	 * @param daoId
	 *            被顶叨ID
	 * @param user
	 *            当前操作人对象
	 * @param userIp
	 *            操作人IP
	 * @author winston
	 */
	public void addUpDao(String daoId, DlUsers user, String userIp)
			throws Exception {
		short userId = user.getUserId();
		String hql = " From DlUplog Where userId = ? And hotdaoId = ? ";
		Object[] sqlVlaue = { userId, daoId };
		List<DlUplog> uplogList = dlUplogDao.find(hql, sqlVlaue);
		if (uplogList != null && uplogList.size() > 0) {
			ProjectException moreThanOne = new ProjectException(
					Constant.UPDAO_ERRORMESSAGE_ONLYONE.getStrValue());
			throw moreThanOne;
		}
		// 插入顶历史
		DlUplog uplog = new DlUplog();
		uplog.setHotdaoId(Short.valueOf(daoId));
		uplog.setUpIp(userIp);
		uplog.setUserId(userId);
		uplog.setUptime(new Date());
		dlUplogDao.save(uplog);
		// 将叨的被顶次数加一
		DlContent content = dlContentDao.get(uplog.getHotdaoId());
		content.setUpNum((short) (content.getUpNum() + 1));
		dlContentDao.saveOrUpdate(content);

	}
	
	/**
	 * 获得话题中的dao
	 * */
	public List<DlContent> getTopicContent(Short topicId){
		String sql = "From DlContent u where u.topicid=?";
		List<DlContent> result = dlContentDao.find(sql, topicId);
		return result;
	}

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

}

package com.innovation.daolife.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.dao.IDlProductDao;
import com.innovation.daolife.dao.IDlProductFollowDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlProduct;
import com.innovation.daolife.model.DlProductfollow;
import com.innovation.daolife.model.DlUsers;
import com.innovation.daolife.service.IProductService;

public class ProductService implements IProductService {
	private IDlProductDao dlProductDao;
	private IDlProductFollowDao dlProductFollowDao;
	
	/**
	 * @author fengsn ���Ʒ
	 */
	public PaginationSupport getHotProduct(PaginationSupport paginationSupport) {
		//Short daoNum = this.getdaoNum();
		String querysql = " Select c From DlProduct c order by product_id desc ";
		String countsql = " Select count(c.productId) From DlProduct c order by product_id desc";
		paginationSupport = dlProductDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		return paginationSupport;
	}
	
	/**
	 * @author fengsn
	 * 添加产品关注
	 * */
	public void addProductFollow(Short productId,Short userId){
		
		
		String sql = " From DlProductfollow u where u.productfollowProductid=? and u.productfollowUserid = ? ";
		List<DlProductfollow> ProductfollowList = dlProductFollowDao.find(sql,
				new Short[] { productId, userId });
		
		if(ProductfollowList.size()==0){
			DlProduct dlProduct = dlProductDao.get(productId);
			dlProduct.setProductFollowsum(dlProduct.getProductFollowsum()+1);
			dlProductDao.update(dlProduct);
			
			DlProductfollow dlProductfollow = new DlProductfollow();
			dlProductfollow.setProductfollowProductid(productId);
			dlProductfollow.setProductfollowUserid(userId);
			dlProductFollowDao.save(dlProductfollow);
		}
		
	}
	
	
	/**
	 * @author fengsn ���Ʒ
	 */
	public PaginationSupport getProductById(PaginationSupport paginationSupport,Short id) {
		//Short daoNum = this.getdaoNum();
		String querysql = " Select c From DlProduct c where productId ="+id+" order by product_id desc ";
		String countsql = " Select count(c.productId) From DlProduct c  where productId ="+id+" order by product_id desc";
		paginationSupport = dlProductDao.findPageByQuery(querysql, countsql,
				paginationSupport.getPageSize(), paginationSupport
						.getStartIndex());
		return paginationSupport;
	}

	/**
	 * @author fengsn
	 * 获得产品的dao相关内容
	 * 返回product列表
	 * */
	public List<DlProduct> getProductDao(){
		String querysql = " Select c From DlProduct c order by product_id desc ";
		List<DlProduct> result = dlProductDao.findWithoutT(querysql);
		DlProduct tmp_dlProduct = new DlProduct();
		tmp_dlProduct.setProductPic("images/unknow.gif");
		tmp_dlProduct.setProductAuthor("?");
		tmp_dlProduct.setProductName("?");
		return result;
	}
	
	public IDlProductDao getDlProductDao() {
		return dlProductDao;
	}

	public void setDlProductDao(IDlProductDao dlProductDao) {
		this.dlProductDao = dlProductDao;
	}

	public IDlProductFollowDao getDlProductFollowDao() {
		return dlProductFollowDao;
	}

	public void setDlProductFollowDao(IDlProductFollowDao dlProductFollowDao) {
		this.dlProductFollowDao = dlProductFollowDao;
	}
}

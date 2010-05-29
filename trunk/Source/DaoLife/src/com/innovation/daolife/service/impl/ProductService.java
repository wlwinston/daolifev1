package com.innovation.daolife.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.dao.IDlProductDao;
import com.innovation.daolife.model.DlContent;
import com.innovation.daolife.model.DlProduct;
import com.innovation.daolife.service.IProductService;

public class ProductService implements IProductService {
	private IDlProductDao dlProductDao;
	
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
}

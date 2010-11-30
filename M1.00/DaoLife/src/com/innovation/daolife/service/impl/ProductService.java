package com.innovation.daolife.service.impl;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.dao.IDlProductDao;
import com.innovation.daolife.service.IProductService;

public class ProductService implements IProductService {
	private IDlProductDao dlProductDao;
	
	/**
	 * @author fengsn 查询最新产品
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

	public IDlProductDao getDlProductDao() {
		return dlProductDao;
	}

	public void setDlProductDao(IDlProductDao dlProductDao) {
		this.dlProductDao = dlProductDao;
	}
}

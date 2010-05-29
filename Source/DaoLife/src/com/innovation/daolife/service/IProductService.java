package com.innovation.daolife.service;

import java.util.List;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.model.DlProduct;

public interface IProductService {
	public PaginationSupport getHotProduct(PaginationSupport paginationSupport);
	public List<DlProduct> getProductDao();
}

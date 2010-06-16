package com.innovation.daolife.service;

import java.util.List;

import com.innovation.common.util.PaginationSupport;
import com.innovation.daolife.model.DlProduct;

public interface IProductService {
	public PaginationSupport getHotProduct(PaginationSupport paginationSupport);
	public DlProduct getProductById(Short id);
	public void addProductFollow(Short productId,Short userId);
	public List<DlProduct> getProductDao();
}

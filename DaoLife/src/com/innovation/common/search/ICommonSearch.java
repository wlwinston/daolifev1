package com.innovation.common.search;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

public interface ICommonSearch {
	public DetachedCriteria getDetachedCriteria();
	
}

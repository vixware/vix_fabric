package com.vix.mdm.item.service;

import java.util.Date;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;


public interface IPriceConditionService extends IBaseHibernateService{
	
	/** 物料id、数量区间、价格、折扣、开始-结束时间 */
	public boolean addPriceCondition(String itemId,Double minCount,Double maxCount,Double price,Double discount,Date startDate,Date endDate) throws Exception;
	
}

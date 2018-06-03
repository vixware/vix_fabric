package com.vix.drp.storesalesrecord.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.storesalesrecord.service.IStoreSalesRecordService;
import com.vix.ebusiness.order.orderProcess.dao.IOrderProcessDao;

@Service("storeSalesRecordService")
public class StoreSalesRecordServiceImpl extends BaseHibernateServiceImpl implements IStoreSalesRecordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "orderProcessDao")
	private IOrderProcessDao orderProcessDao;

	@Override
	public <T> T findOrderByHql(String hql, Map<String, Object> params) throws Exception {
		return orderProcessDao.findOrderByHql(hql, params);
	}
}

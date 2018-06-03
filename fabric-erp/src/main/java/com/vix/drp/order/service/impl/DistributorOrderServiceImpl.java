package com.vix.drp.order.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.drp.order.service.IDistributorOrderService;
import com.vix.ebusiness.order.orderProcess.dao.IOrderProcessDao;

@Service("distributorOrderService")
public class DistributorOrderServiceImpl extends BaseHibernateServiceImpl implements IDistributorOrderService {

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

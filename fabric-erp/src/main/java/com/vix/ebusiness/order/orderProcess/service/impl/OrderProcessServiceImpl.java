/**
 * 
 */
package com.vix.ebusiness.order.orderProcess.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.ebusiness.order.orderProcess.dao.IOrderProcessDao;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("orderProcessService")
public class OrderProcessServiceImpl extends BaseHibernateServiceImpl implements IOrderProcessService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "orderProcessDao")
	private IOrderProcessDao orderProcessDao;

	@Override
	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception {
		return orderProcessDao.findObjectByHqlNoTenantId(hql, params);
	}

	@Override
	public BaseEntity findBeforeAndAfterEntityByHql(String hql, Map<String, Object> params) throws Exception {
		return orderProcessDao.findOrderByHql(hql, params);
	}
}

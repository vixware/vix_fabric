/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.procurementSet.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.OrderType;

/**
 * @Description: 采购单据类型
 * @author ivan
 * @date 2014-01-18
 */
@Component("orderTypeDomain")
@Transactional
public class OrderTypeDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, OrderType.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, OrderType.class, params);
		return p;
	}

	public OrderType findOrderTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(OrderType.class, id);
	}

	public OrderType merge(OrderType orderType) throws Exception {
		return baseHibernateService.merge(orderType);
	}

	public void deleteByOrderType(OrderType orderType) throws Exception {
		baseHibernateService.deleteByEntity(orderType);
	}


	/** 索引对象列表 */
	public List<OrderType> findOrderTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(OrderType.class, null);
	}

}

package com.vix.ebusiness.customer.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.BusinessCustomer;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("customerDomain")
@Transactional
public class CustomerDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCustomerPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BusinessCustomer.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<BusinessCustomer> findCustomerList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(BusinessCustomer.class, params);
	}

}

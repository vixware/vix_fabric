package com.vix.drp.customerFeedbackmanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.customerFeedbackmanagement.entity.CustomerFeedback;

@Component("customerFeedbackDomain")
@Transactional
public class CustomerFeedbackDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerFeedback.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<CustomerFeedback> findCustomerFeedbackList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CustomerFeedback.class, params);
	}

	public CustomerFeedback findCustomerFeedbackById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerFeedback.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CustomerFeedback.class, ids);
	}

	public CustomerFeedback saveOrUpdateCustomerFeedback(CustomerFeedback customerFeedback) throws Exception {
		return baseHibernateService.merge(customerFeedback);
	}

	public void deleteByEntity(CustomerFeedback customerFeedback) throws Exception {
		baseHibernateService.deleteByEntity(customerFeedback);
	}

}

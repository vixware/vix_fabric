package com.vix.drp.CustomerSurveymanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.CustomerSurveymanagement.entity.CustomerSurvey;

@Component("customerSurveyDomain")
@Transactional
public class CustomerSurveyDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerSurvey.class, params);
		return p;
	}

	public CustomerSurvey findCustomerSurveyById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerSurvey.class, id);
	}

	/** 索引对象列表 */
	public List<CustomerSurvey> findCustomerSurveyist(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CustomerSurvey.class, params);
	}

	public CustomerSurvey saveOrUpdateCustomerSurvey(CustomerSurvey customerSurvey) throws Exception {
		return baseHibernateService.merge(customerSurvey);
	}

	public void deleteByEntity(CustomerSurvey customerSurvey) throws Exception {
		baseHibernateService.deleteByEntity(customerSurvey);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CustomerSurvey.class, ids);
	}
}

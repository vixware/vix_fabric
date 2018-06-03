/**
 * 
 */
package com.vix.drp.integralRulesSet.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.drp.integralRulesSet.service.IIntegralRulesSetService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("integralRulesSetDomain")
@Transactional
public class IntegralRulesSetDomain extends BaseDomain{

	@Autowired
	private IIntegralRulesSetService integralRulesSetService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = integralRulesSetService.findBillsTypePager(pager, params);
		return p;
	}

	public Organization findOrganizationById(String id) throws Exception {
		return integralRulesSetService.findEntityById(Organization.class, id);
	}

	public IntegralRules saveOrUpdatePrecisionControl(IntegralRules integralRules) throws Exception {
		return integralRulesSetService.merge(integralRules);
	}

	public List<Organization> findOrganization(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return integralRulesSetService.findAllSubEntity(Organization.class, attributeName, parentId, params);
	}
}

/**
 * 
 */
package com.vix.system.processMonitoring.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.e6soft.form.model.BusinessFormTemplate;
import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.system.formBinding.entity.TemplateAndOrganizationUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("processMonitoringDomain")
@Transactional
public class ProcessMonitoringDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findBusinessFormTemplatePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BusinessFormTemplate.class, params);
		return p;
	}

	public List<BusinessFormTemplate> findBusinessFormTemplateList() throws Exception {
		return baseHibernateService.findAllByConditions(BusinessFormTemplate.class, null);
	}

	public TemplateAndOrganizationUnit saveTemplateAndOrganizationUnit(TemplateAndOrganizationUnit templateAndOrganizationUnit) throws Exception {
		return baseHibernateService.merge(templateAndOrganizationUnit);
	}

	public TemplateAndOrganizationUnit findTemplateAndOrganizationUnit(Long organizationUnitId) throws Exception {
		return baseHibernateService.findEntityByAttribute(TemplateAndOrganizationUnit.class, "organizationUnitId", organizationUnitId);
	}
}

/**
 * 
 */
package com.vix.system.formBinding.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.e6soft.form.model.BusinessFormTemplate;
import com.vix.common.base.domain.BaseDomain;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.system.formBinding.entity.TemplateAndOrganizationUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("formBindingDomain")
@Transactional
public class FormBindingDomain extends BaseDomain {

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

	public TemplateAndOrganizationUnit findTemplateAndOrganizationUnit(String attribute, String value) throws Exception {
		return baseHibernateService.findEntityByAttribute(TemplateAndOrganizationUnit.class, attribute, value);
	}

	public UserAccount findUserAccountById(String id) throws Exception {
		return baseHibernateService.findEntityById(UserAccount.class, id);
	}

	public TemplateAndOrganizationUnit findAllTemplateAndOrganizationUnit(String hql, Map<String, Object> params) throws Exception {
		return baseHibernateService.findObjectByHql(hql, params);
	}
}

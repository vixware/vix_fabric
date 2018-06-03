package com.vix.system.industrymanagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;
import com.vix.system.industrymanagement.service.IIndustryManagementModuleService;

/**
 * 行业模块业务接口 实现
 * 
 * @author shadow
 *
 */
@Service("industryManagementModuleService")
@Transactional
public class IndustryManagementModuleServiceImpl extends BaseHibernateServiceImpl implements IIndustryManagementModuleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.system.industrymanagement.service.IIndustryManagementModuleService#
	 * findIndustryManagementModuleByIndustryMgtId(java.lang.String)
	 */
	@Override
	public List<IndustryManagementModule> findIndustryManagementModuleByIndustryMgtId(String industryMgtId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append("select industrymanagementModule from ").append(IndustryManagementModule.class.getName()).append(" industrymanagementModule ");// .append(ename);
		hql.append(" where 1=1 ");

		hql.append(" and industrymanagementModule.industryManagement.id = :industryMgtId ");
		param.put("industryMgtId", industryMgtId);

		hql.append(" order by industrymanagementModule.id ");

		HqlTenantIdUtil.handleParamMap4TenantId(param);// 不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);// 不处理CompanyInnerCode;

		List<IndustryManagementModule> industrymanagementModuleList = baseHibernateDao.findAllByHql2(hql.toString(), param);
		return industrymanagementModuleList;

	}
}

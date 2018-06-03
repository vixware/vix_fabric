package com.vix.drp.installation.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.installation.entity.InstallationOrder;

@Component("installationDomain")
@Transactional
public class InstallationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InstallationOrder.class, params);
		return p;
	}

	public InstallationOrder findInstallationOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(InstallationOrder.class, id);
	}

	public InstallationOrder saveOrUpdateInstallationOrder(InstallationOrder installationOrder) throws Exception {
		return baseHibernateService.merge(installationOrder);
	}

	public void deleteByEntity(InstallationOrder installationOrder) throws Exception {
		baseHibernateService.deleteByEntity(installationOrder);
	}

}

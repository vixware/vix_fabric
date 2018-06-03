package com.vix.common.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IOrgJobDao;
import com.vix.common.org.dao.IOrganizationUnitDao;
import com.vix.common.org.entity.OrgJob;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.hql.OrgJobHqlProvider;
import com.vix.common.org.service.IOrgJobService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

@Service("orgJobService")
@Transactional
public class OrgJobServiceImpl extends BaseHibernateServiceImpl implements IOrgJobService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "orgJobDao")
	private IOrgJobDao orgJobDao;

	@Resource(name = "orgJobHqlProvider")
	private OrgJobHqlProvider orgJobHqlProvider;

	@Resource(name = "organizationUnitDao")
	private IOrganizationUnitDao organizationUnitDao;

	@Override
	public List<OrgJob> findOrgJobByOrgId(String orgId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		StringBuilder hql = orgJobHqlProvider.findOrgJobByOrgId(params);
		List<OrgJob> jobList = orgJobDao.findAllByHql2(hql.toString(), params);
		return jobList;
	}

	@Override
	public List<OrgJob> findOrgJobByOrgUnitId(String orgUnitId) throws Exception {
		Organization org = organizationUnitDao.getTopOrganizationUnitByUnitId(orgUnitId);
		List<OrgJob> jobList = findOrgJobByOrgId(org.getId());
		return jobList;
	}
}

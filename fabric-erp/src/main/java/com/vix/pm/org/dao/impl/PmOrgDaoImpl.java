package com.vix.pm.org.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.pm.org.dao.IPmOrgDao;
import com.vix.pm.org.entity.PmOrg;

@Repository("pmOrgDao")
public class PmOrgDaoImpl extends BaseHibernateDaoImpl implements IPmOrgDao {

	@Override
	public Set<PmOrg> findBusinessOrgByOrgUnitId(String orgUnitId)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql.append("select bo from PmOrg bo inner join bo.organizationUnit orgUnit where orgUnit.id = :orgUnitId");
		params.put("orgUnitId", orgUnitId);
		
		List<PmOrg> resList = findAllByHql2(hql.toString(), params);
		Set<PmOrg> resSet = new HashSet<PmOrg>(resList);
		return resSet;
	}
}

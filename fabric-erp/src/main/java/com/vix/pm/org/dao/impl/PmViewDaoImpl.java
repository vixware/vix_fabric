package com.vix.pm.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.pm.org.dao.IPmViewDao;
import com.vix.pm.org.entity.PmOrgView;
import com.vix.pm.org.hql.PmViewHqlProvider;

@Repository("pmViewDao")
public class PmViewDaoImpl extends BaseHibernateDaoImpl implements IPmViewDao {

	@Resource(name = "pmViewHqlProvider")
	private PmViewHqlProvider pmViewHqlProvider;

	/**
	 * 查询业务组织视图和业务组织的 联合视图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PmOrgView> findOrgViewList(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", id);
		StringBuilder hql = pmViewHqlProvider.findBusinessOrgViewList(params);
		List<PmOrgView> orgViewList = findAllByHql2(hql.toString(),params);
		return orgViewList;
	}
}

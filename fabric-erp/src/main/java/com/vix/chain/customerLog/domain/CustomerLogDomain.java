package com.vix.chain.customerLog.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.hr.organization.entity.Employee;

@Component("customerLogDomain")
@Transactional
public class CustomerLogDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findZoVipCardLogPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ZoVipCardLog.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<ZoVipCardLog> findZoVipCardLog() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return baseHibernateService.findAllByConditions(ZoVipCardLog.class, params);
	}

	public Employee findEmployee(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}
}

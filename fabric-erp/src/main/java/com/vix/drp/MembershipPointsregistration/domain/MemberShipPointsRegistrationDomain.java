package com.vix.drp.MembershipPointsregistration.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.MembershipCardmanagement.entity.ZoVipCardLog;
import com.vix.hr.organization.entity.Employee;

/**
 * 竞争者信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-9
 */
@Component("memberShipPointsRegistrationDomain")
@Transactional
public class MemberShipPointsRegistrationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ZoVipCardLog.class, params);
		return p;
	}

	public Pager findMemberShipCardPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MemberShipCard.class, params);
		return p;
	}

	public ZoVipCardLog findZoVipCardLogById(String id) throws Exception {
		return baseHibernateService.findEntityById(ZoVipCardLog.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public ZoVipCardLog mergeZoVipCardLog(ZoVipCardLog zoVipCardLog) throws Exception {
		return baseHibernateService.merge(zoVipCardLog);
	}

	public void deleteZoVipCardLog(ZoVipCardLog zoVipCardLog) throws Exception {
		baseHibernateService.deleteByEntity(zoVipCardLog);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ZoVipCardLog.class, ids);
	}

	/** 索引对象列表 */
	public List<ZoVipCardLog> findZoVipCardLogList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ZoVipCardLog.class, params);
	}
}

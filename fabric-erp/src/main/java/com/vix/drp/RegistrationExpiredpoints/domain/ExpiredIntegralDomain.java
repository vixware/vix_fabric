package com.vix.drp.RegistrationExpiredpoints.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.RegistrationExpiredpoints.entity.ExpiredIntegral;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-9
 */
@Component("expiredIntegralDomain")
@Transactional
public class ExpiredIntegralDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ExpiredIntegral.class, params);
		return p;
	}

	public Pager findMemberShipCardPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MemberShipCard.class, params);
		return p;
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ExpiredIntegral.class, ids);
	}

	public ExpiredIntegral findExpiredIntegralById(String id) throws Exception {
		return baseHibernateService.findEntityById(ExpiredIntegral.class, id);
	}

	public ExpiredIntegral mergeExpiredIntegral(ExpiredIntegral expiredIntegral) throws Exception {
		return baseHibernateService.merge(expiredIntegral);
	}

	public void deleteExpiredIntegral(ExpiredIntegral expiredIntegral) throws Exception {
		baseHibernateService.deleteByEntity(expiredIntegral);
	}

	/** 索引对象列表 */
	public List<ExpiredIntegral> findExpiredIntegralList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ExpiredIntegral.class, params);
	}
}

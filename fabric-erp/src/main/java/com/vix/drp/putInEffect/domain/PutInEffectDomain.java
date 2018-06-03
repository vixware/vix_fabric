package com.vix.drp.putInEffect.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.putInEffect.entity.PutInEffect;

@Component("putInEffectDomain")
@Transactional
public class PutInEffectDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PutInEffect.class, params);
		return p;
	}

	public PutInEffect findPutInEffectById(String id) throws Exception {
		return baseHibernateService.findEntityById(PutInEffect.class, id);
	}

	public PutInEffect saveOrUpdatePutInEffect(PutInEffect putInEffect) throws Exception {
		return baseHibernateService.merge(putInEffect);
	}

	public void deleteByEntity(PutInEffect putInEffect) throws Exception {
		baseHibernateService.deleteByEntity(putInEffect);
	}

}

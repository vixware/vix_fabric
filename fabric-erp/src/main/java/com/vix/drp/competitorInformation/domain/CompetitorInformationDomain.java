package com.vix.drp.competitorInformation.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.competitorInformation.entity.CompetingChannelInfo;

/**
 * 竞争者信息
 * 
 * @author niuguobin
 * 
 */
@Component("competitorInformationDomain")
@Transactional
public class CompetitorInformationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CompetingChannelInfo.class, params);
		return p;
	}

	public CompetingChannelInfo findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(CompetingChannelInfo.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CompetingChannelInfo.class, ids);
	}

	public CompetingChannelInfo merge(CompetingChannelInfo competingChannelInfo) throws Exception {
		return baseHibernateService.merge(competingChannelInfo);
	}

	public void saveOrUpdate(CompetingChannelInfo competingChannelInfo) throws Exception {
		baseHibernateService.saveOrUpdate(competingChannelInfo);
	}

	public void deleteByEntity(CompetingChannelInfo competingChannelInfo) throws Exception {
		baseHibernateService.deleteByEntity(competingChannelInfo);
	}

	/** 索引对象列表 */
	public List<CompetingChannelInfo> findCompetingChannelInfoList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CompetingChannelInfo.class, params);
	}
}

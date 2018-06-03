package com.vix.chain.storeInformation.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;

@Component("storeDomain")
@Transactional
public class StoreDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return baseHibernateService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return baseHibernateService.merge(channelDistributor);
	}

	public void saveOrUpdate(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.saveOrUpdate(channelDistributor);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ChannelDistributor.class, ids);
	}

	/** 索引对象列表 */
	public List<ChannelDistributor> findChannelDistributorIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤渠道
		params.put("type," + SearchCondition.ANYLIKE, "4");
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}
}

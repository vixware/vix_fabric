/**
 * 
 */
package com.vix.drp.channel.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.service.IChannelService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("channelDomain")
@Transactional
public class ChannelDomain extends BaseDomain{

	@Autowired
	private IChannelService channelService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = channelService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findEntityById(String id) throws Exception {
		return channelService.findEntityById(ChannelDistributor.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return channelService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return channelService.merge(channelDistributor);
	}

	public ChannelDistributor saveOrUpdate(ChannelDistributor channelDistributor) throws Exception {
		return channelService.merge(channelDistributor);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		channelService.deleteByEntity(channelDistributor);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		channelService.batchDelete(ChannelDistributor.class, ids);
	}

	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return channelService.findAllByConditions(CurrencyType.class, params);
	}

	/** 索引对象列表 */
	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return channelService.findAllByConditions(ChannelDistributor.class, params);
	}
}

/**
 * 
 */
package com.vix.drp.channelprice.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channelprice.service.IChannelPriceService;
import com.vix.mdm.item.entity.PriceCondition;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("channelPriceDomain")
@Transactional
public class ChannelPriceDomain extends BaseDomain{

	@Autowired
	private IChannelPriceService channelPriceService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = channelPriceService.findOrganizationPager(pager, params);
		return p;
	}

	public PriceCondition findEntityById(String id) throws Exception {
		return channelPriceService.findEntityById(PriceCondition.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return channelPriceService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return channelPriceService.merge(channelDistributor);
	}

	public void saveOrUpdate(ChannelDistributor channelDistributor) throws Exception {
		channelPriceService.saveOrUpdate(channelDistributor);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		channelPriceService.deleteByEntity(channelDistributor);
	}

	/** 索引对象列表 */
	public List<PriceCondition> findPriceConditionIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤渠道
		/* params.put("type," + SearchCondition.ANYLIKE, "2"); */
		return channelPriceService.findAllByConditions(PriceCondition.class, params);
	}
}

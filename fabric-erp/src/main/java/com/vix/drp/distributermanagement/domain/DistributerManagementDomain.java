/**
 * 
 */
package com.vix.drp.distributermanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("distributerManagementDomain")
@Transactional
public class DistributerManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, params);
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

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		baseHibernateService.deleteByEntity(channelDistributor);
	}

	/** 索引对象列表 */
	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ChannelDistributor.class, ids);
	}

}

package com.vix.drp.tvMediaManagement.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.tvMediaManagement.entity.TvMedia;

@Component("tvMediaManagementDomain")
@Transactional
public class TvMediaManagementDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, TvMedia.class, params);
		return p;
	}

	public TvMedia findTvMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(TvMedia.class, id);
	}

	public TvMedia saveOrUpdateTvMedia(TvMedia tvMedia) throws Exception {
		return baseHibernateService.merge(tvMedia);
	}

	public void deleteByEntity(TvMedia tvMedia) throws Exception {
		baseHibernateService.deleteByEntity(tvMedia);
	}

}

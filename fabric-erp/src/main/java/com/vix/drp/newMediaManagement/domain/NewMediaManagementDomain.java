package com.vix.drp.newMediaManagement.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.newMediaManagement.entity.NewMedia;

@Component("newMediaManagementDomain")
@Transactional
public class NewMediaManagementDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, NewMedia.class, params);
		return p;
	}

	public NewMedia findNewMediaById(String id) throws Exception {
		return baseHibernateService.findEntityById(NewMedia.class, id);
	}

	public NewMedia saveOrUpdateNewMedia(NewMedia newMedia) throws Exception {
		return baseHibernateService.merge(newMedia);
	}

	public void deleteByEntity(NewMedia newMedia) throws Exception {
		baseHibernateService.deleteByEntity(newMedia);
	}

}

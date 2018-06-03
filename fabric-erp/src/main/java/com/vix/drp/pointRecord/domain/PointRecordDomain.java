package com.vix.drp.pointRecord.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.pointRecord.entity.PointRecord;

@Component("pointRecordDomain")
@Transactional
public class PointRecordDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPointRecordPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PointRecord.class, params);
		return p;
	}

	public PointRecord findPointRecordById(String id) throws Exception {
		return baseHibernateService.findEntityById(PointRecord.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(PointRecord.class, ids);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public PointRecord saveOrUpdatePointRecord(PointRecord pointRecord) throws Exception {
		return baseHibernateService.merge(pointRecord);
	}

	public void deleteByEntity(PointRecord pointRecord) throws Exception {
		baseHibernateService.deleteByEntity(pointRecord);
	}

}

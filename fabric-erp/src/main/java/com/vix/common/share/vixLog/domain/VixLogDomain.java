/**
 * 
 */
package com.vix.common.share.vixLog.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("vixLogDomain")
@Transactional
public class VixLogDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findLatestOperateEntityPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, LatestOperateEntity.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<LatestOperateEntity> findLatestOperateEntity() throws Exception {
		return baseHibernateService.findAllByConditions(LatestOperateEntity.class, null);
	}

}

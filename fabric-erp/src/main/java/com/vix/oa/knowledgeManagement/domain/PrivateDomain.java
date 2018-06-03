package com.vix.oa.knowledgeManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.knowledgeManagement.entity.PrivateCabinet;

/**
 * 
 * @ClassName: PrivateDomain
 * @Description: 公共文件柜
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-10-15 下午3:27:28
 */
@Component("privateDomain")
@Transactional
public class PrivateDomain extends BaseDomain {

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PrivateCabinet.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PrivateCabinet.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<PrivateCabinet> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PrivateCabinet.class, null);
	}

	public PrivateCabinet merge(PrivateCabinet privateCabinet) throws Exception {
		return baseHibernateService.merge(privateCabinet);
	}

}

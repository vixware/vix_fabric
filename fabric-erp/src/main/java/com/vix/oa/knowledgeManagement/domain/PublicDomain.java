package com.vix.oa.knowledgeManagement.domain;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.knowledgeManagement.entity.PublicCabinet;

/**
 * 
 * @ClassName: PublicDomain
 * @Description: 公共文件柜
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-10-15 下午3:27:28
 */
@Component("publicDomain")
@Transactional
public class PublicDomain extends BaseDomain{

	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PublicCabinet.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PublicCabinet.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<PublicCabinet> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PublicCabinet.class, null);
	}
	
	public PublicCabinet merge(PublicCabinet publicCabinet) throws Exception {
		return baseHibernateService.merge(publicCabinet);
	}
	
}

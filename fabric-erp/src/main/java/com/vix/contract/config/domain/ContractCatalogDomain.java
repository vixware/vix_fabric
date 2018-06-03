package com.vix.contract.config.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.config.entity.ContractCatalog;
import com.vix.core.web.Pager;

/**
 * @ClassName: ContractCatalogDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-7-29 上午9:08:00
 */
@Component("contractCatalogDomain")
@Transactional
public class ContractCatalogDomain extends BaseDomain{
	

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ContractCatalog.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractCatalog.class, params);
		return p;
	}
}

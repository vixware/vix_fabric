
package com.vix.contract.config.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.config.entity.ContractClause;
import com.vix.core.web.Pager;

/**
 * @ClassName: 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-24 下午4:18:31
 */
@Component("contractClauseDomain")
@Transactional
public class ContractClauseDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ContractClause.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractClause.class, params);
		return p;
	}

	public ContractClause findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractClause.class, id);
	}

	public ContractClause merge(ContractClause contractClause) throws Exception {
		return baseHibernateService.merge(contractClause);
	}

	public void deleteByEntity(ContractClause contractClause) throws Exception {
		baseHibernateService.deleteByEntity(contractClause);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ContractClause.class, ids);
	}

	/** 索引对象列表 */
	public List<ContractClause> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ContractClause.class, null);
	}
}

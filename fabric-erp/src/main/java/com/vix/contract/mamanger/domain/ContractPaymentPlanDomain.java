
package com.vix.contract.mamanger.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.mamanger.entity.ContractLine;
import com.vix.contract.mamanger.entity.ContractPaymentPlan;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.core.web.Pager;

/**
 * @ClassName: 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-24 下午4:18:31
 */
@Component("contractPaymentPlanDomain")
@Transactional
public class ContractPaymentPlanDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ContractPaymentPlan.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractPaymentPlan.class, params);
		return p;
	}

	public ContractPaymentPlan findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractPaymentPlan.class, id);
	}

	public ContractPaymentPlan merge(ContractPaymentPlan contractPaymentPlan) throws Exception {
		return baseHibernateService.merge(contractPaymentPlan);
	}

	public ContractLine merge(ContractLine contractLine) throws Exception {
		return baseHibernateService.merge(contractLine);
	}

	public ContractWarning merge(ContractWarning contractWarning)
			throws Exception {
		return baseHibernateService.merge(contractWarning);
	}

	public void deleteByEntity(ContractPaymentPlan contractPaymentPlan) throws Exception {
		baseHibernateService.deleteByEntity(contractPaymentPlan);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ContractPaymentPlan.class, ids);
	}

	/** 索引对象列表 */
	public List<ContractPaymentPlan> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ContractPaymentPlan.class, null);
	}
}

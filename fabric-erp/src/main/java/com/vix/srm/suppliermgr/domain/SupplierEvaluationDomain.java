/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.srm.suppliermgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierEvaluation;
import com.vix.mdm.srm.share.entity.SupplierEvaluationItem;

/**
 * @Description: 供应商预选与评估
 * @author ivan 
 * @date 2013-08-13
 */
@Component("SupplierEvaluationDomain")
@Transactional
public class SupplierEvaluationDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				SupplierEvaluation.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				SupplierEvaluation.class, params);
		return p;
	}

	public SupplierEvaluation findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierEvaluation.class, id);
	}

	public SupplierEvaluation merge(SupplierEvaluation supplierEvaluation) throws Exception {
		return baseHibernateService.merge(supplierEvaluation);
	}
	
	public SupplierEvaluationItem merge(SupplierEvaluationItem supplierEvaluationItem) throws Exception {
		return baseHibernateService.merge(supplierEvaluationItem);
	}

	public void deleteByEntity(SupplierEvaluation supplierEvaluation) throws Exception {
		baseHibernateService.deleteByEntity(supplierEvaluation);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SupplierEvaluation.class, ids);
	}


	/** 索引对象列表 */
	public List<SupplierEvaluation> findSupplierEvaluationIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SupplierEvaluation.class, null);
	}
}

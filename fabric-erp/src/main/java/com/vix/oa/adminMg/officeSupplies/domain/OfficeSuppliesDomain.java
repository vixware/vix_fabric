package com.vix.oa.adminMg.officeSupplies.domain;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSupplies;
import com.vix.oa.adminMg.officeSupplies.entity.OfficeSuppliesRegister;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;

/**
 * 
 * @ClassName: OfficeSuppliesDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-12 下午3:11:55
 */
@Component("officeSuppliesDomain")
@Transactional
public class OfficeSuppliesDomain extends BaseDomain{

	
	/** 获取列表数据 */
	public Pager findWimStockrecordsPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, OfficeSuppliesRegister.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,OfficeSupplies.class, params);
		return p;
	}
	
	/** 获取办公用品库存搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, OfficeSupplies.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ExecutionFeedback.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,EvaluationReview.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<OfficeSupplies> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(OfficeSupplies.class, null);
	}
	
	public OfficeSupplies findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(OfficeSupplies.class, id);
	}
	
	public ExecutionFeedback findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(ExecutionFeedback.class, id);
	}
	
	public EvaluationReview findEntityById2(String id) throws Exception {
		return baseHibernateService.findEntityById(EvaluationReview.class, id);
	}
	
	public void deleteByEntity(OfficeSupplies officeSupplies) throws Exception {
		baseHibernateService.deleteByEntity(officeSupplies);
	}
	
	public OfficeSupplies merge(OfficeSupplies officeSupplies) throws Exception {
		return baseHibernateService.merge(officeSupplies);
	}
	
	public EvaluationReview merge(EvaluationReview evaluationReview) throws Exception {
		return baseHibernateService.merge(evaluationReview);
	}
	
	public ExecutionFeedback merge(ExecutionFeedback executionFeedback) throws Exception {
		return baseHibernateService.merge(executionFeedback);
	}
	
	/** 索引对象列表 */
	public List<OfficeSuppliesRegister> findWimStockrecordsIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤归还单
		/*params.put("flag," + SearchCondition.ANYLIKE, "2");
		params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
		return baseHibernateService.findAllByConditions(OfficeSuppliesRegister.class, params);
	}

}

/**
 * 
 */
package com.vix.oa.currentexpense.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.currentexpense.entity.ExpenseAccountSummary;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("expensesSummaryDepartmentDomain")
@Transactional
public class ExpensesSummaryDepartmentDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findExpenseAccountSummaryPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ExpenseAccountSummary.class, params);
		return p;
	}

	public ExpenseAccountSummary findExpenseAccountSummaryById(String id) throws Exception {
		return baseHibernateService.findEntityById(ExpenseAccountSummary.class, id);
	}

	/**
	 * 保存
	 */
	public ExpenseAccountSummary mergeExpenseAccountSummary(ExpenseAccountSummary expenseAccountSummary) throws Exception {
		return baseHibernateService.merge(expenseAccountSummary);
	}

	public void deleteByEntity(ExpenseAccountSummary expenseAccountSummary) throws Exception {
		baseHibernateService.deleteByEntity(expenseAccountSummary);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ExpenseAccountSummary.class, ids);
	}

	/** 索引对象列表 */
	public List<ExpenseAccountSummary> findExpenseAccountSummaryList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ExpenseAccountSummary.class, params);
	}

}

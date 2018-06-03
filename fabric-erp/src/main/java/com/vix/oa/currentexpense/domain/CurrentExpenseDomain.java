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
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards;
import com.vix.oa.currentexpense.entity.CurrentExpense;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("currentExpenseDomain")
@Transactional
public class CurrentExpenseDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCurrentExpensePager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CurrentExpense.class, params);
		return p;
	}

	public CurrentExpense findCurrentExpenseById(String id) throws Exception {
		return baseHibernateService.findEntityById(CurrentExpense.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	/**
	 * 保存
	 */
	public CurrentExpense mergeCurrentExpense(CurrentExpense currentExpense) throws Exception {
		return baseHibernateService.merge(currentExpense);
	}

	public void deleteByEntity(CurrentExpense currentExpense) throws Exception {
		baseHibernateService.deleteByEntity(currentExpense);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CurrentExpense.class, ids);
	}

	/** 索引对象列表 */
	public List<CurrentExpense> findCurrentExpenseList() throws Exception {
		return baseHibernateService.findAllByConditions(CurrentExpense.class, null);
	}

	public List<AreaExpensesStandards> findAreaExpensesStandardsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(AreaExpensesStandards.class, params);
	}

}

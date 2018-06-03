package com.vix.drp.returnrecordstores.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.delivery.entity.SaleReturnForm;

@Component("storeReturnRecordsDomain")
@Transactional
public class StoreReturnRecordsDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SaleReturnForm.class, params);
		return p;
	}

	public SaleReturnForm findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleReturnForm.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SaleReturnForm.class, ids);
	}

	public void saveOrUpdate(SaleReturnForm saleReturnForm) throws Exception {
		baseHibernateService.saveOrUpdate(saleReturnForm);
	}

	public void deleteByEntity(SaleReturnForm saleReturnForm) throws Exception {
		baseHibernateService.deleteByEntity(saleReturnForm);
	}

	/** 索引对象列表 */
	public List<SaleReturnForm> findSaleReturnFormList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SaleReturnForm.class, params);
	}
}

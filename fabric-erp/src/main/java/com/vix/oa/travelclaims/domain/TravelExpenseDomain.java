/**
 * 
 */
package com.vix.oa.travelclaims.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.travelclaims.entity.AccommodationFee;
import com.vix.oa.travelclaims.entity.TransportCosts;
import com.vix.oa.travelclaims.entity.TravelExpense;
import com.vix.system.billTypeManagement.entity.BillsProperty;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("travelExpenseDomain")
@Transactional
public class TravelExpenseDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findTravelExpensePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, TravelExpense.class, params);
		return p;
	}

	public TravelExpense findTravelExpenseById(String id) throws Exception {
		return baseHibernateService.findEntityById(TravelExpense.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public TransportCosts findTransportCostsById(String id) throws Exception {
		return baseHibernateService.findEntityById(TransportCosts.class, id);
	}

	public AccommodationFee findAccommodationFeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(AccommodationFee.class, id);
	}

	/**
	 * 保存
	 */
	public TravelExpense mergeTravelExpense(TravelExpense travelExpense) throws Exception {
		return baseHibernateService.merge(travelExpense);
	}

	/**
	 */
	public TransportCosts mergeTransportCosts(TransportCosts transportCosts) throws Exception {
		return baseHibernateService.merge(transportCosts);
	}

	public AccommodationFee mergeAccommodationFee(AccommodationFee accommodationFee) throws Exception {
		return baseHibernateService.merge(accommodationFee);
	}

	public void deleteByEntity(TravelExpense travelExpense) throws Exception {
		baseHibernateService.deleteByEntity(travelExpense);
	}

	public void deleteTransportCostsByEntity(TransportCosts transportCosts) throws Exception {
		baseHibernateService.deleteByEntity(transportCosts);
	}

	public void deleteAccommodationFeeByEntity(AccommodationFee accommodationFee) throws Exception {
		baseHibernateService.deleteByEntity(accommodationFee);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(TravelExpense.class, ids);
	}

	/** 索引对象列表 */
	public List<TravelExpense> findTravelExpenseIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(TravelExpense.class, params);
	}
	/** 索引对象列表 */
	public List<BillsProperty> findBillsPropertyList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(BillsProperty.class, params);
	}

}

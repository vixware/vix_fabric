/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.vehiclemanagement.entity.InsuranceRegister;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("vehicleDomain")
@Transactional
public class VehicleDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findVehiclePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Vehicle.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findTakeDeliveryPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public Vehicle findVehicleById(String id) throws Exception {
		return baseHibernateService.findEntityById(Vehicle.class, id);
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Pager findItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public Vehicle mergeVehicle(Vehicle vehicle) throws Exception {
		return baseHibernateService.merge(vehicle);
	}

	public InsuranceRegister mergeInsuranceRegister(InsuranceRegister insuranceRegister) throws Exception {
		return baseHibernateService.merge(insuranceRegister);
	}

	public void deleteDriverByEntity(Vehicle driver) throws Exception {
		baseHibernateService.deleteByEntity(driver);
	}

	/** 索引对象列表 */
	public List<Vehicle> findVehicleList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(Vehicle.class, params);
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		baseHibernateService.evict(obj);
	}
}

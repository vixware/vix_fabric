/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.oa.adminMg.requisitionCar.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.requisitionCar.entity.CarUse;
import com.vix.oa.adminMg.requisitionCar.entity.VehicleRequest;

/**
 * 
 * @author Thinkpad
 *  车辆使用申请管理
 */
@Component("vehicleApplicationsDomain")
@Transactional
public class VehicleApplicationsDomain extends BaseDomain{

	
	/** 获取车辆使用列表数据 */
	public Pager findPagerByVehicleHqlCarUse(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CarUse.class, params);
		return p;
	}
	
	public CarUse merge(CarUse carUse) throws Exception {
		return baseHibernateService.merge(carUse);
	}
	
	public CarUse findCarUseById(String id) throws Exception {
		return baseHibernateService.findEntityById(CarUse.class, id);
	}
	
	public void deleteByCarUse(CarUse carUse) throws Exception {
		baseHibernateService.deleteByEntity(carUse);
	}
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CarUse.class, params);
		return p;
	}
	/** 获取汽车列表数据 */
	public Pager findPagerByHqlConditionsCar(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, VehicleRequest.class, params);
		return p;
	}
	

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, CarUse.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<CarUse> findCarUseIndex() throws Exception {
		return baseHibernateService.findAllByConditions(CarUse.class, null);
	}
}

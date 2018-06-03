/**
 * 
 */
package com.vix.dtbcenter.deliveryplan.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlan;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDeliveryPerson;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToDispatchRoute;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToSalesOrder;
import com.vix.dtbcenter.deliveryplan.entity.DeliveryPlanToVehicle;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("distributionManagementDomain")
@Transactional
public class DistributionManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDeliveryPlanPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DeliveryPlan.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findDeliveryPlanPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, DeliveryPlan.class, params);
		return p;
	}

	public DeliveryPlan findDeliveryPlanById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryPlan.class, id);
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public Vehicle findVehicleById(String id) throws Exception {
		return baseHibernateService.findEntityById(Vehicle.class, id);
	}

	public DeliveryPerson findDeliveryPersonById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryPerson.class, id);
	}

	public DispatchRoute findDispatchRouteById(String id) throws Exception {
		return baseHibernateService.findEntityById(DispatchRoute.class, id);
	}

	public DeliveryPlan mergeDeliveryPlan(DeliveryPlan deliveryPlan) throws Exception {
		return baseHibernateService.merge(deliveryPlan);
	}

	public DeliveryPlanToSalesOrder mergeDeliveryPlanToSalesOrder(DeliveryPlanToSalesOrder deliveryPlanToSalesOrder) throws Exception {
		return baseHibernateService.merge(deliveryPlanToSalesOrder);
	}

	public DeliveryPlanToVehicle mergeDeliveryPlanToVehicle(DeliveryPlanToVehicle deliveryPlanToVehicle) throws Exception {
		return baseHibernateService.merge(deliveryPlanToVehicle);
	}

	public DeliveryPlanToDeliveryPerson mergeDeliveryPlanToDeliveryPerson(DeliveryPlanToDeliveryPerson deliveryPlanToDeliveryPerson) throws Exception {
		return baseHibernateService.merge(deliveryPlanToDeliveryPerson);
	}

	public DeliveryPlanToDispatchRoute mergeDeliveryPlanToDispatchRoute(DeliveryPlanToDispatchRoute deliveryPlanToDispatchRoute) throws Exception {
		return baseHibernateService.merge(deliveryPlanToDispatchRoute);
	}

	public void deleteByEntity(DeliveryPlan deliveryPlan) throws Exception {
		baseHibernateService.deleteByEntity(deliveryPlan);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(DeliveryPlan.class, ids);
	}

	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<DeliveryPlan> findDeliveryPlanList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPlan.class, params);
	}

	public List<SalesOrder> findSalesOrder() throws Exception {
		return baseHibernateService.findAllByConditions(SalesOrder.class, null);
	}

	public List<Vehicle> findVehicle() throws Exception {
		return baseHibernateService.findAllByConditions(Vehicle.class, null);
	}

	public List<DeliveryPerson> findDeliveryPerson() throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPerson.class, null);
	}

	public List<DispatchRoute> findDispatchRoute() throws Exception {
		return baseHibernateService.findAllByConditions(DispatchRoute.class, null);
	}

	public Pager findDispatchRoutePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DispatchRoute.class, params);
		return p;
	}

	public Pager findDeliveryPersonPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DeliveryPerson.class, params);
		return p;
	}

	public Pager findVehiclePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Vehicle.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<DeliveryPlanToSalesOrder> findDeliveryPlanToSalesOrderByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPlanToSalesOrder.class, params);
	}

	/** 索引对象列表 */
	public List<DeliveryPlanToVehicle> findDeliveryPlanToVehicleByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPlanToVehicle.class, params);
	}

	/** 索引对象列表 */
	public List<DeliveryPlanToDeliveryPerson> findDeliveryPlanToDeliveryPersonByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPlanToDeliveryPerson.class, params);
	}

	/** 索引对象列表 */
	public List<DeliveryPlanToDispatchRoute> findDeliveryPlanToDispatchRouteByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryPlanToDispatchRoute.class, params);
	}
}

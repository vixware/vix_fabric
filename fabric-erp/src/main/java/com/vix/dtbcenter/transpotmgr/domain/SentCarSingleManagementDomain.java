/**
 * 
 */
package com.vix.dtbcenter.transpotmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceiptToRoute;
import com.vix.dtbcenter.transpotmgr.entity.DelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToDelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("sentCarSingleManagementDomain")
@Transactional
public class SentCarSingleManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDeliveryReceiptPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DeliveryReceipt.class, params);
		return p;
	}

	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public Pager findDispatchRoutePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DispatchRoute.class, params);
		return p;
	}

	public Pager findVehiclePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Vehicle.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findDeliveryReceiptPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, DeliveryReceipt.class, params);
		return p;
	}

	public DeliveryReceipt findDeliveryReceiptById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryReceipt.class, id);
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public DelieryNotification findDelieryNotificationById(String id) throws Exception {
		return baseHibernateService.findEntityById(DelieryNotification.class, id);
	}

	public DispatchRoute findDispatchRouteById(String id) throws Exception {
		return baseHibernateService.findEntityById(DispatchRoute.class, id);
	}

	public DeliveryReceipt mergeDeliveryReceipt(DeliveryReceipt deliveryReceipt) throws Exception {
		return baseHibernateService.merge(deliveryReceipt);
	}

	public DeliveryReceiptToSalesOrder mergeDeliveryReceiptToSalesOrder(DeliveryReceiptToSalesOrder deliveryReceiptToSalesOrder) throws Exception {
		return baseHibernateService.merge(deliveryReceiptToSalesOrder);
	}

	public DelieryNotification mergeDelieryNotification(DelieryNotification delieryNotification) throws Exception {
		return baseHibernateService.merge(delieryNotification);
	}

	public DeliveryReceiptToRoute mergeDeliveryReceiptToRoute(DeliveryReceiptToRoute deliveryReceiptToRoute) throws Exception {
		return baseHibernateService.merge(deliveryReceiptToRoute);
	}

	public List<DeliveryReceiptToDelieryNotification> findDeliveryReceiptToDelieryNotificationByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceiptToDelieryNotification.class, params);
	}

	public List<DeliveryReceiptToRoute> findDeliveryReceiptToRouteByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceiptToRoute.class, params);
	}

	public List<DeliveryReceiptToSalesOrder> findDeliveryReceiptToSalesOrderByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceiptToSalesOrder.class, params);
	}

	public void deleteByEntity(DeliveryReceipt truckingOrder) throws Exception {
		baseHibernateService.deleteByEntity(truckingOrder);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(DeliveryReceipt.class, ids);
	}

	public List<DeliveryReceipt> findDeliveryReceiptList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceipt.class, params);
	}

}

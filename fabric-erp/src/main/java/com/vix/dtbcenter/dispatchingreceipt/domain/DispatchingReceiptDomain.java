/**
 * 
 */
package com.vix.dtbcenter.dispatchingreceipt.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceipt;
import com.vix.dtbcenter.dispatchingreceipt.entity.DispatchingReceiptToSalesOrder;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.transpotmgr.entity.DelieryNotification;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToSalesOrder;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("dispatchingReceiptDomain")
@Transactional
public class DispatchingReceiptDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDeliveryReceiptPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DispatchingReceipt.class, params);
		return p;
	}

	public Pager findDelieryNotificationPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DelieryNotification.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findDeliveryReceiptPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, DispatchingReceipt.class, params);
		return p;
	}

	public DispatchingReceipt findDispatchingReceiptById(String id) throws Exception {
		return baseHibernateService.findEntityById(DispatchingReceipt.class, id);
	}

	public DeliveryReceipt findDeliveryReceiptById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryReceipt.class, id);
	}

	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public List<DispatchingReceiptToSalesOrder> findDeliveryReceiptToDelieryNotificationByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DispatchingReceiptToSalesOrder.class, params);
	}

	public Pager findSalesOrderPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public List<DispatchingReceiptToSalesOrder> findDispatchingReceiptToSalesOrderByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DispatchingReceiptToSalesOrder.class, params);
	}

	public List<DeliveryReceiptToSalesOrder> findDeliveryReceiptToSalesOrderByParams(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceiptToSalesOrder.class, params);
	}

	public DispatchingReceipt mergeDeliveryReceipt(DispatchingReceipt dispatchingReceipt) throws Exception {
		return baseHibernateService.merge(dispatchingReceipt);
	}

	public void mergeDispatchingReceiptToSalesOrder(DispatchingReceiptToSalesOrder dispatchingReceiptToSalesOrder) throws Exception {
		baseHibernateService.merge(dispatchingReceiptToSalesOrder);
	}

	public void deleteByEntity(DispatchingReceipt dispatchingReceipt) throws Exception {
		baseHibernateService.deleteByEntity(dispatchingReceipt);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(DispatchingReceipt.class, ids);
	}

	public Pager findVehiclePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DeliveryReceipt.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<DispatchingReceipt> findDispatchingReceiptList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DispatchingReceipt.class, params);
	}

	/**
	 * @param delieryNotificationId
	 * @return
	 * @throws Exception
	 */
	public DelieryNotification findDelieryNotificationById(String delieryNotificationId) throws Exception {
		return baseHibernateService.findEntityById(DelieryNotification.class, delieryNotificationId);
	}

	/**
	 * @param dispatchingReceiptToDelieryNotification
	 * @throws Exception
	 */
	public void mergeDispatchingReceiptToDelieryNotification(DispatchingReceiptToSalesOrder dispatchingReceiptToDelieryNotification) throws Exception {
		baseHibernateService.merge(dispatchingReceiptToDelieryNotification);
	}
}

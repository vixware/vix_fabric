/**
 * 
 */
package com.vix.dtbcenter.pickupds.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToDelieryNotification;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("pickupDispatchSendDomain")
@Transactional
public class PickupDispatchSendDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findDeliveryReceiptPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, DeliveryReceipt.class, params);
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

	public DeliveryReceiptToDelieryNotification finddeliveryReceiptToDelieryNotificationById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryReceiptToDelieryNotification.class, id);
	}

	public void deleteDeliveryReceiptToDelieryNotificationEntity(DeliveryReceiptToDelieryNotification deliveryReceiptToDelieryNotification) throws Exception {
		baseHibernateService.deleteByEntity(deliveryReceiptToDelieryNotification);
	}

	public DeliveryReceipt mergeDeliveryReceipt(DeliveryReceipt deliveryReceipt) throws Exception {
		return baseHibernateService.merge(deliveryReceipt);
	}

	public void deleteByEntity(DeliveryReceipt deliveryReceipt) throws Exception {
		baseHibernateService.deleteByEntity(deliveryReceipt);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(DeliveryReceipt.class, ids);
	}

	/** 索引对象列表 */
	public List<DeliveryReceipt> findDeliveryReceiptList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(DeliveryReceipt.class, params);
	}

}

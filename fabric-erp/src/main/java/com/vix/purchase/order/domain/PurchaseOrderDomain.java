/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.order.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoice;
import com.vix.mdm.purchase.order.entity.ApprovalOpinion;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.Currency;
import com.vix.mdm.purchase.order.entity.DeliveryPlan;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.mdm.purchase.order.entity.PriceConditions;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-15
 */
@Component("purchaseOrderDomain")
@Transactional
public class PurchaseOrderDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
		return p;
	}

	public Pager findSupplierPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions3(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions4(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Project.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseOrder.class, params);
		return p;
	}

	public Pager findPurchasePlan(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
		return p;
	}

	public Pager findPurchaseApply(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseApply.class, params);
		return p;
	}

	public Pager findPurchaseArrivalPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseArrival.class, params);
		return p;
	}

	public Pager findPurchaseInquire(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
		return p;
	}

	public PurchaseOrder findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseOrder.class, id);
	}

	public PurchasePlan findPurchasePlanById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchasePlan.class, id);
	}

	public PurchaseApply findPurchaseApplyById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseApply.class, id);
	}

	public PurchaseInquire findPurchaseInquireById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseInquire.class, id);
	}

	public PurchaseOrderLineItem findPurchaseOrderLineItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseOrderLineItem.class, id);
	}

	public Supplier findSupplierById(String id) throws Exception {
		return baseHibernateService.findEntityById(Supplier.class, id);
	}

	public PurchaseOrder merge(PurchaseOrder purchaseOrder) throws Exception {
		return baseHibernateService.merge(purchaseOrder);
	}

	public PurchaseOrderLineItem merge(PurchaseOrderLineItem purchaseOrderLineItem) throws Exception {
		return baseHibernateService.merge(purchaseOrderLineItem);
	}

	public ReceivedAddress merge(ReceivedAddress receivedAddress) throws Exception {
		return baseHibernateService.merge(receivedAddress);
	}

	public DeliveryPlan merge(DeliveryPlan deliveryPlan) throws Exception {
		return baseHibernateService.merge(deliveryPlan);
	}

	public PurchaseInvoice merge(PurchaseInvoice purchaseInvoice) throws Exception {
		return baseHibernateService.merge(purchaseInvoice);
	}

	public PriceConditions merge(PriceConditions priceConditions) throws Exception {
		return baseHibernateService.merge(priceConditions);
	}

	public ApprovalOpinion merge(ApprovalOpinion approvalOpinion) throws Exception {
		return baseHibernateService.merge(approvalOpinion);
	}

	public OrderType merge(OrderType orderType) throws Exception {
		return baseHibernateService.merge(orderType);
	}

	public BizType merge(BizType bizType) throws Exception {
		return baseHibernateService.merge(bizType);
	}

	public Currency merge(Currency currency) throws Exception {
		return baseHibernateService.merge(currency);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		return baseHibernateService.merge(supplier);
	}

	public void deleteByEntity(PurchaseOrder purchaseOrder) throws Exception {
		baseHibernateService.deleteByEntity(purchaseOrder);
	}

	/** 索引对象列表 */
	public List<PurchaseOrder> findPurchaseOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseOrder.class, null);
	}

	/** 索引对象列表 */
	public List<ReceivedAddress> findReceivedAddressIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ReceivedAddress.class, null);
	}

	/** 索引对象列表 */
	public List<OrderType> findOrderTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(OrderType.class, null);
	}

	/** 索引对象列表 */
	public List<BizType> findBizTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(BizType.class, null);
	}

	/** 索引对象列表 */
	public List<CurrencyType> findCurrencyTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, null);
	}

	/** 附件 */
	public Attachments merge(Attachments attachments) throws Exception {
		return baseHibernateService.merge(attachments);
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Attachments.class, id);
	}

	public void deleteAttachmentsEntity(Attachments attachments) throws Exception {
		baseHibernateService.deleteByEntity(attachments);
	}
}

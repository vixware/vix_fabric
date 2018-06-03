/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.invoice.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoice;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoiceItem;
import com.vix.mdm.purchase.order.entity.ReceivedAddress;

/**
 * @Description: 采购发票
 * @author ivan
 * @date 2013-08-15
 */
@Component("purchaseInvoiceDomain")
@Transactional
public class PurchaseInvoiceDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInvoice.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseInvoice.class, params);
		return p;
	}

	public PurchaseInvoice findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseInvoice.class, id);
	}

	public PurchaseInvoice merge(PurchaseInvoice purchaseInvoice) throws Exception {
		return baseHibernateService.merge(purchaseInvoice);
	}

	public PurchaseInvoiceItem merge(PurchaseInvoiceItem purchaseInvoiceItem) throws Exception {
		return baseHibernateService.merge(purchaseInvoiceItem);
	}

	public void deleteByEntity(PurchaseInvoice purchaseInvoice) throws Exception {
		baseHibernateService.deleteByEntity(purchaseInvoice);
	}

	/** 索引对象列表 */
	public List<PurchaseInvoice> findPurchaseInvoiceIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseInvoice.class, null);
	}

	/** 索引对象列表 */
	public List<ReceivedAddress> findReceivedAddressIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ReceivedAddress.class, null);
	}

}

/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.inquire.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquiryDetail;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-17
 */
@Component("purchaseInquireDomain")
@Transactional
public class PurchaseInquireDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseInquire.class, params);
		return p;
	}

	public PurchaseInquire findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseInquire.class, id);
	}

	public PurchaseInquire merge(PurchaseInquire purchaseInquire) throws Exception {
		return baseHibernateService.merge(purchaseInquire);
	}

	public PurchaseInquiryDetail merge(PurchaseInquiryDetail purchaseInquiryDetail) throws Exception {
		return baseHibernateService.merge(purchaseInquiryDetail);
	}

	public void deleteByEntity(PurchaseInquire purchaseInquire) throws Exception {
		baseHibernateService.deleteByEntity(purchaseInquire);
	}

	/** 索引对象列表 */
	public List<PurchaseInquire> findPurchaseInquireIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseInquire.class, params);
	}

	/** 币种列表 */
	public List<CurrencyType> findCurrencyTypeIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, params);
	}
}

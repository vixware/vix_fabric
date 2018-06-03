/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.apply.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.apply.entity.PurchaseApplyDetails;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-24
 */
@Component("purchaseApplyDomain")
@Transactional
public class PurchaseApplyDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseApply.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseApply.class, params);
		return p;
	}

	public PurchaseApply findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseApply.class, id);
	}

	public PurchaseApply merge(PurchaseApply purchaseApply) throws Exception {
		return baseHibernateService.merge(purchaseApply);
	}

	public PurchaseApplyDetails merge(PurchaseApplyDetails purchaseApplyDetails) throws Exception {
		return baseHibernateService.merge(purchaseApplyDetails);
	}

	public void deleteByEntity(PurchaseApply purchaseApply) throws Exception {
		baseHibernateService.deleteByEntity(purchaseApply);
	}

	/** 索引对象列表 */
	public List<PurchaseApply> findPurchaseApplyIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseApply.class, null);
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

	/** 币种列表 */
	public List<CurrencyType> findCurrencyTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, null);
	}
}

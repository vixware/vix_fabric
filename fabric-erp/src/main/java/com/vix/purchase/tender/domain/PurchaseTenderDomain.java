/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.tender.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.purchase.tender.entity.PurchaseTenderItem;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-31
 */
@Component("purchaseTenderDomain")
@Transactional
public class PurchaseTenderDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseTender.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	// public Pager findPagerByOrHqlConditions(Map<String, Object> params,
	// Pager pager) throws Exception {
	// Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
	// PurchaseTender.class, params);
	// return p;
	// }

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(String hql, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerJustByHql(pager, hql);
		return p;
	}

	public Pager findPagerByHqlConditions2(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	public PurchaseTender findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseTender.class, id);
	}

	public Supplier findSupplierById(String id) throws Exception {
		return baseHibernateService.findEntityById(Supplier.class, id);
	}

	public PurchaseTender merge(PurchaseTender purchaseTender) throws Exception {
		return baseHibernateService.merge(purchaseTender);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		return baseHibernateService.merge(supplier);
	}

	public PurchaseTenderItem merge(PurchaseTenderItem purchaseTenderItem) throws Exception {
		return baseHibernateService.merge(purchaseTenderItem);
	}

	public void deleteByEntity(PurchaseTender purchaseTender) throws Exception {
		baseHibernateService.deleteByEntity(purchaseTender);
	}

	/** 索引对象列表 */
	public List<PurchaseTender> findPurchaseTenderIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isParent," + SearchCondition.NOEQUAL, "null");
		return baseHibernateService.findAllByConditions(PurchaseTender.class, params);
	}

	/** 索引对象列表 */
	public List<PurchaseTender> findPurchaseTenderList(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId," + SearchCondition.EQUAL, id);
		return baseHibernateService.findAllByConditions(PurchaseTender.class, params);
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

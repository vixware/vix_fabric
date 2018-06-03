/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.srm.bidmgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.tender.entity.PurchaseTender;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierTender;
import com.vix.mdm.srm.share.entity.SupplierTenderItems;

/**
 * @Description: 供应商投标
 * @author ivan 
 * @date 2013-09-25
 */
@Component("supplierTenderDomain")
@Transactional
public class SupplierTenderDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				Supplier.class, params);
		return p;
	}

	public Pager findPagerByHqlConditions2(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				Supplier.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				SupplierTender.class, params);
		return p;
	}

	public SupplierTender findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierTender.class, id);
	}
	
	public Supplier findSupplierById(String id) throws Exception {
		return baseHibernateService.findEntityById(Supplier.class, id);
	}
	
	public PurchaseTender findPurchaseTenderById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseTender.class, id);
	}

	public SupplierTender merge(SupplierTender supplierTender) throws Exception {
		return baseHibernateService.merge(supplierTender);
	}
	
	public SupplierTenderItems merge(SupplierTenderItems supplierTenderItems) throws Exception {
		return baseHibernateService.merge(supplierTenderItems);
	}

	public void deleteByEntity(SupplierTender supplierTender) throws Exception {
		baseHibernateService.deleteByEntity(supplierTender);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SupplierTender.class, ids);
	}

	/** 索引对象列表 */
	public List<SupplierTender> findSupplierTenderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SupplierTender.class, null);
	}
	
	/** 附件 */
	public Attachments merge(Attachments attachments) throws Exception {
		return baseHibernateService.merge(attachments);
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Attachments.class, id);
	}
	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		baseHibernateService.deleteByEntity(attachments);
	}
}

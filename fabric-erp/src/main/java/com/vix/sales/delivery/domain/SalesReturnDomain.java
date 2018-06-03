/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.sales.delivery.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.ApprovalOpinion;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-26
 */
@Component("salesReturnDomain")
@Transactional
public class SalesReturnDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				SaleReturnForm.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				SaleReturnForm.class, params);
		return p;
	}

	public SaleReturnForm findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SaleReturnForm.class, id);
	}

	public SaleReturnForm merge(SaleReturnForm saleReturnForm)
			throws Exception {
		return baseHibernateService.merge(saleReturnForm);
	}
	
	public SaleReturnFormItem merge(SaleReturnFormItem saleReturnFormItem)
			throws Exception {
		return baseHibernateService.merge(saleReturnFormItem);
	}
	
	public ApprovalOpinion merge(ApprovalOpinion approvalOpinion)
			throws Exception {
		return baseHibernateService.merge(approvalOpinion);
	}

	public void deleteByEntity(SaleReturnForm saleReturnForm)
			throws Exception {
		baseHibernateService.deleteByEntity(saleReturnForm);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SaleReturnForm.class, ids);
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

	/** 索引对象列表 */
	public List<SaleReturnForm> findSaleReturnFormIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SaleReturnForm.class, null);
	}
}

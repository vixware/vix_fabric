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
import com.vix.sales.delivery.entity.DeliveryDocument;
import com.vix.sales.delivery.entity.DeliveryDocumentItem;

/**
 * @Description: 销售退货
 * @author ivan 
 * @date 2013-08-26
 */
@Component("salesDeliveryDomain")
@Transactional
public class SalesDeliveryDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				DeliveryDocument.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				DeliveryDocument.class, params);
		return p;
	}

	public DeliveryDocument findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(DeliveryDocument.class, id);
	}

	public DeliveryDocument merge(DeliveryDocument deliveryDocument)
			throws Exception {
		return baseHibernateService.merge(deliveryDocument);
	}
	
	public DeliveryDocumentItem merge(DeliveryDocumentItem deliveryDocumentItem)
			throws Exception {
		return baseHibernateService.merge(deliveryDocumentItem);
	}
	
	public ApprovalOpinion merge(ApprovalOpinion approvalOpinion)
			throws Exception {
		return baseHibernateService.merge(approvalOpinion);
	}

	public void deleteByEntity(DeliveryDocument deliveryDocument)
			throws Exception {
		baseHibernateService.deleteByEntity(deliveryDocument);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(DeliveryDocument.class, ids);
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
	public List<DeliveryDocument> findDeliveryDocumentIndex() throws Exception {
		return baseHibernateService.findAllByConditions(
				DeliveryDocument.class, null);
	}
}

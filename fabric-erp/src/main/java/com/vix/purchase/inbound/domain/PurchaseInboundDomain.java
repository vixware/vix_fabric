/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.inbound.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.inbound.entity.PurchaseInbound;
import com.vix.mdm.purchase.inbound.entity.PurchaseInboundItems;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-17
 */
@Component("purchaseInboundDomain")
@Transactional
public class PurchaseInboundDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInbound.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseInbound.class, params);
		return p;
	}

	public PurchaseInbound findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseInbound.class, id);
	}

	public PurchaseInbound merge(PurchaseInbound purchaseInbound) throws Exception {
		return baseHibernateService.merge(purchaseInbound);
	}

	public PurchaseInboundItems merge(PurchaseInboundItems purchaseInboundItems) throws Exception {
		return baseHibernateService.merge(purchaseInboundItems);
	}

	public void deleteByEntity(PurchaseInbound purchaseInbound) throws Exception {
		baseHibernateService.deleteByEntity(purchaseInbound);
	}

	/** 索引对象列表 */
	public List<PurchaseInbound> findPurchaseInboundIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseInbound.class, null);
	}
}

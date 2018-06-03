/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.pursreturn.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturnItems;

/**
 * @Description:
 * @author ivan
 * @date 2013-07-26
 */
@Component("purchaseReturnDomain")
@Transactional
public class PurchaseReturnDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PurchaseReturn.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, PurchaseReturn.class, params);
		return p;
	}

	public PurchaseReturn findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(PurchaseReturn.class, id);
	}

	public PurchaseReturn merge(PurchaseReturn purchaseReturn) throws Exception {
		return baseHibernateService.merge(purchaseReturn);
	}

	public PurchaseReturnItems merge(PurchaseReturnItems purchaseReturnItems) throws Exception {
		return baseHibernateService.merge(purchaseReturnItems);
	}

	public void deleteByEntity(PurchaseReturn purchaseReturn) throws Exception {
		baseHibernateService.deleteByEntity(purchaseReturn);
	}

	/** 索引对象列表 */
	public List<PurchaseReturn> findPurchaseReturnIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PurchaseReturn.class, null);
	}
}

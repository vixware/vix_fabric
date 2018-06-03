package com.vix.drp.rebatesManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.drp.collectManagement.entity.CollectBill;
import com.vix.drp.rebatesManagement.entity.ReturnBill;
import com.vix.drp.refundRule.entity.RefundRule;
import com.vix.mdm.crm.entity.CustomerAccount;

@Component("rebatesManagementDomain")
@Transactional
public class RebatesManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ReturnBill.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findRefundRulePagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, RefundRule.class, params);
		return p;
	}

	public ReturnBill findRebatesManagementById(String id) throws Exception {
		return baseHibernateService.findEntityById(ReturnBill.class, id);
	}

	public RefundRule findRefundRuleById(String id) throws Exception {
		return baseHibernateService.findEntityById(RefundRule.class, id);
	}

	public CustomerAccount findCustomerAccountById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerAccount.class, id);
	}

	public void saveOrUpdate(ReturnBill rebatesManagement) throws Exception {
		baseHibernateService.saveOrUpdate(rebatesManagement);
	}

	public void deleteByEntity(ReturnBill rebatesManagement) throws Exception {
		baseHibernateService.deleteByEntity(rebatesManagement);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ReturnBill.class, ids);
	}

	/** 索引对象列表 */
	public List<ReturnBill> findReturnBillList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ReturnBill.class, params);
	}

	/** 索引对象列表 */
	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, params);
	}

	public List<CollectBill> findCollectBillList(String id) throws Exception {
		return baseHibernateService.findAllByEntityClassAndAttribute(CollectBill.class, "customerAccount.id", id);
	}
}

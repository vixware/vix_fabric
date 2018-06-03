/**
 * 
 */
package com.vix.drp.refundRule.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.refundRule.entity.RefundRule;
import com.vix.drp.refundRule.entity.RefundRuleDetail;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("refundRuleDomain")
@Transactional
public class RefundRuleDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, RefundRule.class, params);
		return p;
	}

	public RefundRule findRefundRuleById(String id) throws Exception {
		return baseHibernateService.findEntityById(RefundRule.class, id);
	}

	public RefundRule merge(RefundRule refundRule) throws Exception {
		return baseHibernateService.merge(refundRule);
	}

	public RefundRuleDetail saveOrUpdateRefundRuleDetail(RefundRuleDetail refundRuleDetail) throws Exception {
		return baseHibernateService.merge(refundRuleDetail);
	}

	public void deleteByEntity(RefundRule refundRule) throws Exception {
		if (refundRule != null && refundRule.getRefundRuleDetails() != null && refundRule.getRefundRuleDetails().size() > 0) {
			for (RefundRuleDetail refundRuleDetail : refundRule.getRefundRuleDetails()) {
				baseHibernateService.deleteByEntity(refundRuleDetail);
			}
		}
		baseHibernateService.deleteByEntity(refundRule);
	}

	public Pager findInventoryCurrentStockPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(RefundRule.class, ids);
	}

	/** 索引对象列表 */
	public List<RefundRule> findRefundRuleIndex(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(RefundRule.class, params);
	}
}

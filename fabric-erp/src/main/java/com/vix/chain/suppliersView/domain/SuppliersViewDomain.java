/**
 * 
 */
package com.vix.chain.suppliersView.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.chain.productview.entity.SettlementStatement;
import com.vix.chain.productview.entity.SettlementStatementItem;
import com.vix.chain.suppliersView.entity.SuppliersView;
import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("suppliersViewDomain")
@Transactional
public class SuppliersViewDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SuppliersView.class, params);
		return p;
	}
	public Pager findSalesOrderPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
		return p;
	}

	public SettlementStatement findSettlementStatementById(String id) throws Exception {
		return baseHibernateService.findEntityById(SettlementStatement.class, id);
	}
	public SalesOrder findSalesOrderById(String id) throws Exception {
		return baseHibernateService.findEntityById(SalesOrder.class, id);
	}

	public SettlementStatementItem findSettlementStatementItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(SettlementStatementItem.class, id);
	}

	public SettlementStatement saveOrUpdateSettlementStatement(SettlementStatement settlementStatement) throws Exception {
		return baseHibernateService.merge(settlementStatement);
	}

	public SettlementStatementItem saveOrUpdateSettlementStatementItem(SettlementStatementItem settlementStatementItem) throws Exception {
		return baseHibernateService.merge(settlementStatementItem);
	}

	public void deleteByEntity(SettlementStatement settlementStatement) throws Exception {
		baseHibernateService.deleteByEntity(settlementStatement);
	}

	public void deleteSettlementStatementItemByEntity(SettlementStatementItem settlementStatementItem) throws Exception {
		baseHibernateService.deleteByEntity(settlementStatementItem);
	}

}

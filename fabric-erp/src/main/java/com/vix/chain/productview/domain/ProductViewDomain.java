/**
 * 
 */
package com.vix.chain.productview.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.chain.productview.entity.ProductView;
import com.vix.chain.productview.entity.SettlementStatement;
import com.vix.chain.productview.entity.SettlementStatementItem;
import com.vix.common.base.domain.BaseDomain;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SalesOrder;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("productViewDomain")
@Transactional
public class ProductViewDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findProductViewPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ProductView.class, params);
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

	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CurrencyType.class, params);
	}
}

/**
 * 
 */
package com.vix.drp.dayWithSales.domain;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("dayWithSalesDomain")
@Transactional
public class DayWithSalesDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecords.class, params);
		return p;
	}

	public StockRecords findStockRecordsById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecords.class, id);
	}

	public StockRecordLines findStockRecordLinesById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecordLines.class, id);
	}

	public StockRecords saveOrUpdateStockRecords(StockRecords stockRecords) throws Exception {
		return baseHibernateService.merge(stockRecords);
	}

	public StockRecordLines saveOrUpdateSaleOrderItem(StockRecordLines stockRecordLines) throws Exception {
		return baseHibernateService.merge(stockRecordLines);
	}

	public void deleteByEntity(StockRecords stockRecords) throws Exception {
		baseHibernateService.deleteByEntity(stockRecords);
	}

	public void deleteStockRecordLinesByEntity(StockRecordLines stockRecordLines) throws Exception {
		baseHibernateService.deleteByEntity(stockRecordLines);
	}

}

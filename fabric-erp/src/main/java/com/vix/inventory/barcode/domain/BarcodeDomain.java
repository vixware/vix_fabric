/**
 * 
 */
package com.vix.inventory.barcode.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.inventory.inbound.entity.StockRecords;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("barcodeDomain")
@Transactional
public class BarcodeDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecords.class, params);
		return p;
	}

	public StockRecords findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecords.class, id);
	}

	public StockRecords merge(StockRecords wimStockrecords) throws Exception {
		return baseHibernateService.merge(wimStockrecords);
	}

	public void deleteByEntity(StockRecords wimStockrecords) throws Exception {
		baseHibernateService.deleteByEntity(wimStockrecords);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(StockRecords.class, ids);
	}

	/** 索引对象列表 */
	public List<StockRecords> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(StockRecords.class, null);
	}

}

/**
 * 
 */
package com.vix.inventory.batchStandingBook.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.inbound.entity.StockRecordLines;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("batchStandingBookDomain")
@Transactional
public class BatchStandingBookDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findInvMainBatchPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InvMainBatch.class, params);
		return p;
	}

	public Pager findInvStockRecordLinesPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, StockRecordLines.class, params);
		return p;
	}

	public InvMainBatch findInvMainBatchById(String id) throws Exception {
		return baseHibernateService.findEntityById(InvMainBatch.class, id);
	}

	public StockRecordLines findInvStockRecordLinesById(String id) throws Exception {
		return baseHibernateService.findEntityById(StockRecordLines.class, id);
	}

	/**
	 * 
	 * 
	 * @param invMainBatch
	 * @return
	 * @throws Exception
	 */
	public InvMainBatch saveOrUpdateInvMainBatch(InvMainBatch invMainBatch) throws Exception {
		return baseHibernateService.merge(invMainBatch);
	}

	/**
	 * 
	 * 
	 * @param invStockRecordLines
	 * @return
	 * @throws Exception
	 */
	public StockRecordLines mergeInvStockRecordLines(StockRecordLines invStockRecordLines) throws Exception {
		return baseHibernateService.merge(invStockRecordLines);
	}

	public void deleteByEntity(InvMainBatch invMainBatch) throws Exception {
		baseHibernateService.deleteByEntity(invMainBatch);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(InvMainBatch.class, ids);
	}

	/** 索引对象列表 */
	public List<InvMainBatch> findInvMainBatchIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isTemp," + SearchCondition.NOEQUAL, "1");
		return baseHibernateService.findAllByConditions(InvMainBatch.class, params);
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		baseHibernateService.evict(obj);
	}
}

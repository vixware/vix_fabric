/**
 * 
 */
package com.vix.system.billTypeSet.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;
import com.vix.system.billTypeSet.entity.BillsPropertyDictionary;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("billTypeSetDomain")
@Transactional
public class BillTypeSetDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findBillsCategoryDictionaryPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BillsCategoryDictionary.class, params);
		return p;
	}

	public BillsCategoryDictionary findBillsCategoryDictionaryById(String id) throws Exception {
		return baseHibernateService.findEntityById(BillsCategoryDictionary.class, id);
	}

	public BillsCategoryDictionary mergeBillsCategoryDictionary(BillsCategoryDictionary billsCategoryDictionary) throws Exception {
		return baseHibernateService.mergeOriginal(billsCategoryDictionary);
	}

	public BillsPropertyDictionary mergeBillsPropertyDictionary(BillsPropertyDictionary billsPropertyDictionary) throws Exception {
		return baseHibernateService.mergeOriginal(billsPropertyDictionary);
	}

	public void deleteByEntity(BillsCategoryDictionary billsCategoryDictionary) throws Exception {
		baseHibernateService.deleteByEntity(billsCategoryDictionary);
	}

	public void deleteBillsCategoryDictionaryByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(BillsCategoryDictionary.class, ids);
	}

	/** 索引对象列表 */
	public List<BillsCategoryDictionary> findBillsCategoryDictionaryIndex() throws Exception {
		return baseHibernateService.findAllByConditions(BillsCategoryDictionary.class, null);
	}

}

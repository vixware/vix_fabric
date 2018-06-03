/**
 * 
 */
package com.vix.system.orginialBillTypeManagement.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.entity.OrginialBillsProperty;
import com.vix.common.orginialMeta.entity.OrginialBillsType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;
import com.vix.system.orginialBillTypeManagement.service.IOrginialBillTypeService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("orginialBillTypeDomain")
@Transactional
public class OrginialBillTypeDomain extends BaseDomain{

	@Autowired
	private IOrginialBillTypeService orginialBillTypeService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orginialBillTypeService.findPagerByHqlConditions(pager, OrginialBillsType.class, params);
		return p;
	}

	public OrginialBillsType findOrginialBillsTypeById(String id) throws Exception {
		return orginialBillTypeService.findEntityById(OrginialBillsType.class, id);
	}

	public OrginialBillsProperty findOrginialBillsPropertyById(String id) throws Exception {
		return orginialBillTypeService.findEntityById(OrginialBillsProperty.class, id);
	}

	public OrginialBillsCategory findOrginialBillsCategoryById(String id) throws Exception {
		return orginialBillTypeService.findEntityById(OrginialBillsCategory.class, id);
	}

	public BillsCategoryDictionary findBillsCategoryDictionaryByCode(String code) throws Exception {
		return orginialBillTypeService.findEntityByAttribute(BillsCategoryDictionary.class, "categoryCode", code);
	}

	public void saveOrUpdateOrginialBillsType(OrginialBillsType orginialBillsType) throws Exception {
		orginialBillTypeService.mergeOriginal(orginialBillsType);
	}

	public OrginialBillsCategory saveOrUpdateOrginialBillsCategory(OrginialBillsCategory orginialBillsCategory) throws Exception {
		return orginialBillTypeService.mergeOriginal(orginialBillsCategory);
	}

	public BillsCategoryDictionary saveOrUpdateBillsCategoryDictionary(BillsCategoryDictionary billsCategoryDictionary) throws Exception {
		return orginialBillTypeService.mergeOriginal(billsCategoryDictionary);
	}

	public void saveOrUpdateOrginialBillsProperty(OrginialBillsProperty orginialBillsProperty) throws Exception {
		orginialBillTypeService.mergeOriginal(orginialBillsProperty);
	}

	/** 索引对象列表 */
	public List<OrginialBillsType> findOrginialBillsTypeList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		return orginialBillTypeService.findAllByConditions(OrginialBillsType.class, params);
	}

	/** 索引对象列表 */
	public List<BillsCategoryDictionary> findBillsCategoryDictionary() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status," + SearchCondition.NOEQUAL, "2");
		return orginialBillTypeService.findAllByConditions(BillsCategoryDictionary.class, params);
	}
}

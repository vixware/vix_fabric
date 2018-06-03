/**
 * 
 */
package com.vix.system.billTypeManagement.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.billTypeManagement.service.IBillTypeManagementService;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("billTypeManagementDomain")
@Transactional
public class BillTypeManagementDomain extends BaseDomain{

	@Autowired
	private IBillTypeManagementService billTypeManagementService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = billTypeManagementService.findPagerByHqlConditions(pager, BillsType.class, params);
		return p;
	}

	public BillsType findEntityById(String id) throws Exception {
		return billTypeManagementService.findEntityById(BillsType.class, id);
	}

	public BillsProperty findBillsPropertyById(String id) throws Exception {
		return billTypeManagementService.findEntityById(BillsProperty.class, id);
	}

	public BillsCategory findBillsCategoryById(String id) throws Exception {
		return billTypeManagementService.findEntityById(BillsCategory.class, id);
	}

	public BillsCategoryDictionary findBillsCategoryDictionaryByCode(String code) throws Exception {
		return billTypeManagementService.findEntityByAttribute(BillsCategoryDictionary.class, "categoryCode", code);
	}

	public BillsCategory findBillsCategoryByCompanyCodeAndCategoryCode(String companyCode, String categoryCode) throws Exception {
		return billTypeManagementService.findBillsCategoryByCompanyCodeAndCategoryCode(companyCode, categoryCode);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return billTypeManagementService.findEntityById(Organization.class, id);
	}

	public void saveOrUpdateBillsType(BillsType billsType) throws Exception {
		billTypeManagementService.merge(billsType);
	}

	public void saveOrUpdateBillsCategory(BillsCategory billsCategory) throws Exception {
		billTypeManagementService.saveOrUpdate(billsCategory);
	}

	public void saveOrUpdateBillsProperty(BillsProperty billsProperty) throws Exception {
		billTypeManagementService.saveOrUpdate(billsProperty);
	}

	public void deleteByEntity(BillsType billsType) throws Exception {
		billTypeManagementService.deleteByEntity(billsType);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		billTypeManagementService.batchDelete(BillsType.class, ids);
	}

	/** 索引对象列表 */
	public List<BillsType> findBillsTypeIndex() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤渠道
		/* params.put("type," + SearchCondition.ANYLIKE, "2"); */
		return billTypeManagementService.findAllByConditions(BillsType.class, params);
	}

	/** 索引对象列表 */
	public List<BillsCategoryDictionary> findBillsCategoryDictionary() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤渠道
		/* params.put("type," + SearchCondition.ANYLIKE, "2"); */
		return billTypeManagementService.findAllByConditions(BillsCategoryDictionary.class, params);
	}
}

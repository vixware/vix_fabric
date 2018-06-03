/**
 * 
 */
package com.vix.system.orginialBillTypeManagement.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.service.IOrganizationService;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.entity.OrginialBillsProperty;
import com.vix.common.orginialMeta.entity.OrginialBillsType;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;
import com.vix.system.orginialBillTypeManagement.dao.IOrginialBillTypeDao;
import com.vix.system.orginialBillTypeManagement.hql.OrginialBillTypeHqlProvider;
import com.vix.system.orginialBillTypeManagement.service.IOrginialBillTypeService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("orginialBillTypeService")
public class OrginialBillTypeServiceImpl extends BaseHibernateServiceImpl implements IOrginialBillTypeService {
	private static final long serialVersionUID = 1552756851697916752L;

	@Resource(name = "orginialBillTypeHqlProvider")
	private OrginialBillTypeHqlProvider orginialBillTypeHqlProvider;
	@Autowired
	private IOrganizationService organizationService;
	@Resource(name = "orginialBillTypeDao")
	private IOrginialBillTypeDao orginialBillTypeDao;

	/**
	 * end
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<OrginialBillsType> findBillsTypeList(String billsPropertyId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsPropertyId", billsPropertyId);
		StringBuilder hql = orginialBillTypeHqlProvider.findBillsTypeList(params);
		List<OrginialBillsType> billsTypeList = orginialBillTypeDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	/**
	 * 获取单据性质
	 * 
	 * @param billsCategoryId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrginialBillsProperty> findBillsPropertyList(String billsCategoryId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsCategoryId", billsCategoryId);
		StringBuilder hql = orginialBillTypeHqlProvider.findBillsPropertyList(params);
		List<OrginialBillsProperty> billsTypeList = orginialBillTypeDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	/**
	 * B：单据分类， T：单据类型,X:单据性质
	 */
	@Override
	public List<BillTypeUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
		if (treeType.equals("B")) {
			OrginialBillsCategory billsCategoryList = findOrginialBillsCategoryById(id);
			List<OrginialBillsProperty> billsPropertyList = findBillsPropertyList(id);
			billTypeUnitList = makeBillsCategoryAndBillsPropertyTree(billsCategoryList, billsPropertyList);
		} else if (treeType.equals("X")) {
			List<OrginialBillsType> billsTypeList = findBillsTypeList(id);
			billTypeUnitList = makeBillsCategoryAndBillsTypeTree(null, billsTypeList);
		}
		return billTypeUnitList;
	}

	public OrginialBillsCategory findOrginialBillsCategoryById(String id) throws Exception {
		OrginialBillsCategory billsCategory = orginialBillTypeDao.findEntityById(OrginialBillsCategory.class, id);
		return billsCategory;
	}

	private List<BillTypeUnit> makeBillsCategoryAndBillsTypeTree(List<OrginialBillsProperty> billsPropertyList, List<OrginialBillsType> billsTypeList) {
		List<BillTypeUnit> orgUnitList = new LinkedList<BillTypeUnit>();
		if (billsTypeList != null) {
			for (OrginialBillsType billsType : billsTypeList) { // 获取类型下的所有数据
				BillTypeUnit billTypeUnit = new BillTypeUnit(billsType.getId(), "T", billsType.getTypeName(), billsType.getTypeCode());
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	private List<BillTypeUnit> makeBillsCategoryAndBillsPropertyTree(OrginialBillsCategory billsCategory, List<OrginialBillsProperty> billsPropertyList) {
		List<BillTypeUnit> orgUnitList = new LinkedList<BillTypeUnit>();
		if (billsPropertyList != null) {
			for (OrginialBillsProperty billsProperty : billsPropertyList) { // 获取类型下的所有数据
				BillTypeUnit billTypeUnit = new BillTypeUnit(billsProperty.getId(), "X", billsProperty.getPropertyName(), billsProperty.getPropertyCode());
				if (billsProperty.getOrginialBillsType() != null) {
					List<BillTypeUnit> billsPropertyUnitList = new LinkedList<BillTypeUnit>();
					for (OrginialBillsType billsType : billsProperty.getOrginialBillsType()) {
						BillTypeUnit billsPropertyUnit = new BillTypeUnit(billsType.getId(), "T", billsType.getTypeName(), billsType.getTypeCode());
						billsPropertyUnitList.add(billsPropertyUnit);
					}
					billTypeUnit.setSubBillTypeUnits(billsPropertyUnitList);
				}
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	/**
	 * 
	 */
	@Override
	public Pager findBillsTypePager(Pager pager, Map<String, Object> reqParams) throws Exception {
		StringBuilder hql = orginialBillTypeHqlProvider.findOrgPager(reqParams, pager);
		pager = orginialBillTypeDao.findPager2ByHql(pager, "billsType", hql.toString(), reqParams);
		return pager;
	}
}

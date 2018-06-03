/**
 * 
 */
package com.vix.system.billTypeManagement.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IOrganizationService;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.billTypeManagement.hql.BillTypeManagementHqlProvider;
import com.vix.system.billTypeManagement.service.IBillTypeManagementService;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("billTypeManagementService")
public class BillTypeManagementServiceImpl extends BaseHibernateServiceImpl implements IBillTypeManagementService {
	private static final long serialVersionUID = 1552756851697916752L;

	@Resource(name = "billTypeManagementHqlProvider")
	private BillTypeManagementHqlProvider billTypeManagementHqlProvider;
	@Autowired
	private IOrganizationService organizationService;
	@Resource(name = "baseHibernateDao")
	private IBaseHibernateDao baseHibernateDao;

	/**
	 * end
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BillsType> findBillsTypeList(String billsPropertyId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsPropertyId", billsPropertyId);
		StringBuilder hql = billTypeManagementHqlProvider.findBillsTypeList(params);
		List<BillsType> billsTypeList = baseHibernateDao.findAllByHql(hql.toString(), params);
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
	public List<BillsProperty> findBillsPropertyList(String billsCategoryId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsCategoryId", billsCategoryId);
		StringBuilder hql = billTypeManagementHqlProvider.findBillsPropertyList(params);
		List<BillsProperty> billsTypeList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	/**
	 * end
	 * 
	 * @param billsTypeId
	 * @return
	 * @throws Exception
	 */
	public List<BillsType> findChannelDistributorListById(Long billsCategoryId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsCategoryId", billsCategoryId);
		StringBuilder hql = billTypeManagementHqlProvider.findBillsTypeList(params);
		List<BillsType> billsTypeList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return billsTypeList;
	}

	/**
	 * C：公司， B：单据分类， T：单据类型,X:单据性质
	 */
	@Override
	public List<BillTypeUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
		if (treeType.equals("C")) {
			// 选择的树节点是公司的时候 查找子公司和单据分类
			List<Organization> organizationList = organizationService.findSubOrganizationList(id);
			List<BillsCategory> billsCategoryList = findBillsCategoryListByOrgId(id);
			billTypeUnitList = makeOrgAndUnitTree(organizationList, billsCategoryList);
		} else if (treeType.equals("B")) {
			BillsCategory billsCategoryList = findBillsCategoryListById(id);
			List<BillsProperty> billsPropertyList = findBillsPropertyList(id);
			billTypeUnitList = makeBillsCategoryAndBillsPropertyTree(billsCategoryList, billsPropertyList);
		} else if (treeType.equals("X")) {
			List<BillsType> billsTypeList = findBillsTypeList(id);
			billTypeUnitList = makeBillsCategoryAndBillsTypeTree(null, billsTypeList);
		}
		return billTypeUnitList;
	}

	/**
	 * 
	 * 根据公司ID编码获取单据分类信息
	 * 
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BillsCategory> findBillsCategoryListByOrgId(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", id);
		StringBuilder hql = billTypeManagementHqlProvider.findBillsCategoryList(params);
		List<BillsCategory> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	public BillsCategory findBillsCategoryListById(String id) throws Exception {
		BillsCategory billsCategory = baseHibernateDao.findEntityById(BillsCategory.class, id);
		return billsCategory;
	}

	/**
	 * 拼装公司和单据分类的树结构
	 * 
	 * @param organizationList
	 * @param billsCategoryList
	 * @return
	 */
	private List<BillTypeUnit> makeOrgAndUnitTree(List<Organization> organizationList, List<BillsCategory> billsCategoryList) {
		List<BillTypeUnit> orgUnitList = new LinkedList<BillTypeUnit>();
		// 拼装公司节点
		if (organizationList != null && organizationList.size() > 0) {
			for (Organization organization : organizationList) {
				if (organization != null) {
					BillTypeUnit orgUnit = new BillTypeUnit(organization.getId(), "C", organization.getOrgName(), organization.getCompanyInnerCode());

					if (organization.getSubOrganizations() != null && organization.getSubOrganizations().size() > 0) {
						List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
						for (Organization ot : organization.getSubOrganizations()) {
							if (ot != null) {
								billTypeUnitList.add(new BillTypeUnit(ot.getId(), "C", ot.getOrgName(), ot.getCompanyInnerCode()));
							}
						}
						orgUnit.setSubBillTypeUnits(billTypeUnitList);
					}
					try {
						List<BillsCategory> bcList = findBillsCategoryListByOrgId(organization.getId());
						if (bcList != null && bcList.size() > 0) {
							List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
							for (BillsCategory billsCategory : bcList) {
								if (billsCategory != null) {
									BillTypeUnit billTypeUnit = new BillTypeUnit(billsCategory.getId(), "B", billsCategory.getCategoryName(), billsCategory.getCategoryCode());
									billTypeUnitList.add(billTypeUnit);
								}
							}
							orgUnit.setSubBillTypeUnits(billTypeUnitList);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					orgUnitList.add(orgUnit);
				}
			}
		}
		// 拼装单据分类节点
		if (billsCategoryList != null && billsCategoryList.size() > 0) {
			for (BillsCategory billsCategory : billsCategoryList) {
				BillTypeUnit orgUnit = new BillTypeUnit(billsCategory.getId(), "B", billsCategory.getCategoryName(), billsCategory.getCategoryCode());
				if (billsCategory.getBillsPropertys() != null) {
					List<BillTypeUnit> billsPropertyUnitList = new LinkedList<BillTypeUnit>();
					for (BillsProperty billsProperty : billsCategory.getBillsPropertys()) {
						BillTypeUnit billsPropertyUnit = new BillTypeUnit(billsProperty.getId(), "X", billsProperty.getPropertyName(), billsProperty.getPropertyName());
						billsPropertyUnitList.add(billsPropertyUnit);
					}
					orgUnit.setSubBillTypeUnits(billsPropertyUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}
		return orgUnitList;
	}

	private List<BillTypeUnit> makeBillsCategoryAndBillsTypeTree(List<BillsProperty> billsPropertyList, List<BillsType> billsTypeList) {
		List<BillTypeUnit> orgUnitList = new LinkedList<BillTypeUnit>();
		if (billsTypeList != null) {
			for (BillsType billsType : billsTypeList) { // 获取类型下的所有数据
				BillTypeUnit billTypeUnit = new BillTypeUnit(billsType.getId(), "T", billsType.getTypeName(), billsType.getTypeCode());
				orgUnitList.add(billTypeUnit);
			}
		}
		return orgUnitList;
	}

	private List<BillTypeUnit> makeBillsCategoryAndBillsPropertyTree(BillsCategory billsCategory, List<BillsProperty> billsPropertyList) {
		List<BillTypeUnit> orgUnitList = new LinkedList<BillTypeUnit>();
		if (billsPropertyList != null) {
			for (BillsProperty billsProperty : billsPropertyList) { // 获取类型下的所有数据
				BillTypeUnit billTypeUnit = new BillTypeUnit(billsProperty.getId(), "X", billsProperty.getPropertyName(), billsProperty.getPropertyCode());
				if (billsProperty.getBillsTypes() != null) {
					List<BillTypeUnit> billsPropertyUnitList = new LinkedList<BillTypeUnit>();
					for (BillsType billsType : billsProperty.getBillsTypes()) {
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
		StringBuilder hql = billTypeManagementHqlProvider.findOrgPager(reqParams, pager);
		pager = baseHibernateDao.findPager2ByHql(pager, "billsType", hql.toString(), reqParams);
		return pager;
	}

	@Override
	public List<BillsCategory> findSubBillsCategoryList(Long id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id || id == 0) {
			params.put("parentBillsCategory.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("parentBillsCategory.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = billTypeManagementHqlProvider.findBillsCategoryListByParams(params, null, null, null);
		List<BillsCategory> billsCategoryList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return billsCategoryList;
	}

	@Override
	public BillsCategory findBillsCategoryByCompanyCodeAndCategoryCode(String companyInnerCode, String categoryCode) throws Exception {
		StringBuilder hql = billTypeManagementHqlProvider.findBillsCategoryByCompanyCodeAndCategoryCode(companyInnerCode, categoryCode);

		return baseHibernateDao.findObjectByHqlNoTenantId(hql.toString(), null);
	}

	@Override
	public List<BillTypeUnit> findBillTypeList(String treeType, String id) throws Exception {
		List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
		if (treeType.equals("B")) {
			BillsCategory billsCategoryList = findBillsCategoryListById(id);
			List<BillsProperty> billsPropertyList = findBillsPropertyList(id);
			billTypeUnitList = makeBillsCategoryAndBillsPropertyTree(billsCategoryList, billsPropertyList);
		} else if (treeType.equals("X")) {
			List<BillsType> billsTypeList = findBillsTypeList(id);
			billTypeUnitList = makeBillsCategoryAndBillsTypeTree(null, billsTypeList);
		}
		return billTypeUnitList;
	}

}

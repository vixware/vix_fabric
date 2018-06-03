/**
 * 
 */
package com.vix.system.precisionControl.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.billTypeManagement.hql.BillTypeManagementHqlProvider;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;
import com.vix.system.precisionControl.service.IPrecisionControlService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("precisionControlService")
public class PrecisionControlServiceImpl extends BaseHibernateServiceImpl implements IPrecisionControlService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1656363067921712293L;

	@Autowired
	private IBaseHibernateDao baseHibernateDao;

	@Resource(name = "billTypeManagementHqlProvider")
	private BillTypeManagementHqlProvider billTypeManagementHqlProvider;

	/**
	 * end
	 * 
	 * 拼装分类跟类型的树
	 * 
	 * @param billsCategoryList
	 * @param billsTypeList
	 * @return
	 */
	private List<BillTypeUnit> makeOrgAndUnitTree(List<BillsCategory> billsCategoryList, List<BillsType> billsTypeList) {/*
																														 * List
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * orgUnitList
																														 * =
																														 * new
																														 * LinkedList
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * (
																														 * )
																														 * ;
																														 * 
																														 * if
																														 * (
																														 * billsCategoryList
																														 * !=
																														 * null
																														 * )
																														 * {
																														 * for
																														 * (
																														 * BillsCategory
																														 * billsCategory
																														 * :
																														 * billsCategoryList
																														 * )
																														 * {
																														 * /
																														 * /
																														 * 获取分类及类型下的所有数据
																														 * BillTypeUnit
																														 * billTypeUnit
																														 * =
																														 * new
																														 * BillTypeUnit
																														 * (
																														 * billsCategory
																														 * .
																														 * getId
																														 * (
																														 * )
																														 * ,
																														 * "C"
																														 * ,
																														 * billsCategory
																														 * .
																														 * getCategoryName
																														 * (
																														 * )
																														 * ,
																														 * billsCategory
																														 * .
																														 * getCategoryCode
																														 * (
																														 * )
																														 * )
																														 * ;
																														 * if
																														 * (
																														 * billsCategory
																														 * .
																														 * getSubBillsCategorys
																														 * (
																														 * )
																														 * .
																														 * size
																														 * (
																														 * )
																														 * >
																														 * 0
																														 * )
																														 * {
																														 * List
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * subBillTypeUnitList
																														 * =
																														 * new
																														 * LinkedList
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * (
																														 * )
																														 * ;
																														 * for
																														 * (
																														 * BillsCategory
																														 * ot
																														 * :
																														 * billsCategory
																														 * .
																														 * getSubBillsCategorys
																														 * (
																														 * )
																														 * )
																														 * {
																														 * subBillTypeUnitList
																														 * .
																														 * add
																														 * (
																														 * new
																														 * BillTypeUnit
																														 * (
																														 * ot
																														 * .
																														 * getId
																														 * (
																														 * )
																														 * ,
																														 * "C"
																														 * ,
																														 * ot
																														 * .
																														 * getCategoryName
																														 * (
																														 * )
																														 * ,
																														 * ot
																														 * .
																														 * getCategoryCode
																														 * (
																														 * )
																														 * )
																														 * )
																														 * ;
																														 * }
																														 * billTypeUnit
																														 * .
																														 * setSubBillTypeUnits
																														 * (
																														 * subBillTypeUnitList
																														 * )
																														 * ;
																														 * }
																														 * if
																														 * (
																														 * billsCategory
																														 * .
																														 * getBillsTypes
																														 * (
																														 * )
																														 * .
																														 * size
																														 * (
																														 * )
																														 * >
																														 * 0
																														 * )
																														 * {
																														 * List
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * subBillTypeUnitList
																														 * =
																														 * new
																														 * LinkedList
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * (
																														 * )
																														 * ;
																														 * for
																														 * (
																														 * BillsType
																														 * billsType
																														 * :
																														 * billsCategory
																														 * .
																														 * getBillsTypes
																														 * (
																														 * )
																														 * )
																														 * {
																														 * subBillTypeUnitList
																														 * .
																														 * add
																														 * (
																														 * new
																														 * BillTypeUnit
																														 * (
																														 * billsType
																														 * .
																														 * getId
																														 * (
																														 * )
																														 * ,
																														 * "T"
																														 * ,
																														 * billsType
																														 * .
																														 * getTypeName
																														 * (
																														 * )
																														 * ,
																														 * billsType
																														 * .
																														 * getTypeCode
																														 * (
																														 * )
																														 * )
																														 * )
																														 * ;
																														 * }
																														 * billTypeUnit
																														 * .
																														 * setSubBillTypeUnits
																														 * (
																														 * subBillTypeUnitList
																														 * )
																														 * ;
																														 * }
																														 * orgUnitList
																														 * .
																														 * add
																														 * (
																														 * billTypeUnit
																														 * )
																														 * ;
																														 * }
																														 * }
																														 * 
																														 * if
																														 * (
																														 * billsTypeList
																														 * !=
																														 * null
																														 * )
																														 * {
																														 * for
																														 * (
																														 * BillsType
																														 * billsType
																														 * :
																														 * billsTypeList
																														 * )
																														 * {
																														 * /
																														 * /
																														 * 获取类型下的所有数据
																														 * BillTypeUnit
																														 * billTypeUnit
																														 * =
																														 * new
																														 * BillTypeUnit
																														 * (
																														 * billsType
																														 * .
																														 * getId
																														 * (
																														 * )
																														 * ,
																														 * "T"
																														 * ,
																														 * billsType
																														 * .
																														 * getTypeName
																														 * (
																														 * )
																														 * ,
																														 * billsType
																														 * .
																														 * getTypeCode
																														 * (
																														 * )
																														 * )
																														 * ;
																														 * if
																														 * (
																														 * billsType
																														 * .
																														 * getSubBillsTypes
																														 * (
																														 * )
																														 * .
																														 * size
																														 * (
																														 * )
																														 * >
																														 * 0
																														 * )
																														 * {
																														 * List
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * subUnitList
																														 * =
																														 * new
																														 * LinkedList
																														 * <
																														 * BillTypeUnit
																														 * >
																														 * (
																														 * )
																														 * ;
																														 * for
																														 * (
																														 * BillsType
																														 * bType
																														 * :
																														 * billsType
																														 * .
																														 * getSubBillsTypes
																														 * (
																														 * )
																														 * )
																														 * {
																														 * subUnitList
																														 * .
																														 * add
																														 * (
																														 * new
																														 * BillTypeUnit
																														 * (
																														 * bType
																														 * .
																														 * getId
																														 * (
																														 * )
																														 * ,
																														 * "T"
																														 * ,
																														 * bType
																														 * .
																														 * getTypeName
																														 * (
																														 * )
																														 * ,
																														 * bType
																														 * .
																														 * getTypeCode
																														 * (
																														 * )
																														 * )
																														 * )
																														 * ;
																														 * }
																														 * billTypeUnit
																														 * .
																														 * setSubBillTypeUnits
																														 * (
																														 * subUnitList
																														 * )
																														 * ;
																														 * }
																														 * orgUnitList
																														 * .
																														 * add
																														 * (
																														 * billTypeUnit
																														 * )
																														 * ;
																														 * }
																														 * }
																														 * return
																														 * orgUnitList
																														 * ;
																														 */
		return null;
	}

	/**
	 * end
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BillsType> findBillsTypeList(Long billsCategoryId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("billsCategoryId", billsCategoryId);
		StringBuilder hql = billTypeManagementHqlProvider.findBillsTypeList(params);
		List<BillsType> billsTypeList = baseHibernateDao.findAllByHql(hql.toString(), params);
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
	 * end
	 */
	@Override
	public List<BillTypeUnit> findOrgAndUnitTreeList(String treeType, Long id) throws Exception {
		List<BillTypeUnit> billTypeUnitList = new LinkedList<BillTypeUnit>();
		if (treeType.equals("C")) {
			List<BillsCategory> billsCategoryList = findSubBillsCategoryList(id);
			List<BillsType> billsTypeList = findBillsTypeList(id);
			billTypeUnitList = makeOrgAndUnitTree(billsCategoryList, billsTypeList);
		} else if (treeType.equals("T")) {
			List<BillsType> billsTyperList = findChannelDistributorListById(id);
			billTypeUnitList = makeOrgAndUnitTree(null, billsTyperList);
		}
		return billTypeUnitList;
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

}

/**
 * 
 */
package com.vix.drp.integralRulesSet.service.impl;

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
import com.vix.drp.integralRulesSet.service.IIntegralRulesSetService;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.billTypeManagement.hql.BillTypeManagementHqlProvider;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("integralRulesSetService")
public class IntegralRulesSetServiceImpl extends BaseHibernateServiceImpl implements IIntegralRulesSetService {

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
	private List<BillTypeUnit> makeOrgAndUnitTree(List<BillsCategory> billsCategoryList, List<BillsType> billsTypeList) {
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

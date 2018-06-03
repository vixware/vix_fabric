/**
 * 
 */
package com.vix.inventory.batch.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.inventory.batch.hql.BatchHqlProvider;
import com.vix.inventory.batch.service.IBatchService;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("batchService")
public class BatchServiceImpl extends BaseHibernateServiceImpl implements IBatchService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Autowired
	private IBaseHibernateDao baseHibernateDao;
	@Resource(name = "batchHqlProvider")
	private BatchHqlProvider batchHqlProvider;

	@Override
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception {
		List<OrgUnit> resList = new LinkedList<OrgUnit>();
		if (treeType.equals("C")) {
			List<ItemCatalog> itemCatalogList = findItemCatalogList(id);
			ItemCatalog itemCatalog = baseHibernateDao.findEntityById(ItemCatalog.class, id);
			Set<Item> itemList = new HashSet<Item>();
			if (itemCatalog != null) {
				itemList = itemCatalog.getItems();
			}
			resList = makeOrgAndUnitTree(itemCatalogList, itemList);
		}

		return resList;
	}

	public List<ItemCatalog> findItemCatalogList(String parentItemCatalogId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentItemCatalogId", parentItemCatalogId);
		StringBuilder hql = batchHqlProvider.findItemCatalogList(params);
		List<ItemCatalog> orgUnitList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return orgUnitList;
	}

	private List<OrgUnit> makeOrgAndUnitTree(List<ItemCatalog> itemCatalogList, Set<Item> itemList) {
		List<OrgUnit> orgUnitList = new LinkedList<OrgUnit>();

		if (itemCatalogList != null) {
			for (ItemCatalog cp : itemCatalogList) {
				OrgUnit orgUnit = new OrgUnit(cp.getId(), "C", cp.getName(), cp.getCode());
				if (cp.getSubItemCatalogs().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (ItemCatalog ot : cp.getSubItemCatalogs()) {
						subUnitList.add(new OrgUnit(ot.getId(), "C", ot.getName(), cp.getCode()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				if (cp.getItems().size() > 0) {
					List<OrgUnit> subUnitList = new LinkedList<OrgUnit>();
					for (Item ou : cp.getItems()) {
						subUnitList.add(new OrgUnit(ou.getId(), "I", ou.getName(), ou.getCode()));
					}
					orgUnit.setSubOrgUnits(subUnitList);
				}
				orgUnitList.add(orgUnit);
			}
		}
		if (itemList != null) {
			for (Item item : itemList) {
				OrgUnit orgUnit = new OrgUnit(item.getId(), "I", item.getName(), item.getCode());
				orgUnitList.add(orgUnit);
			}
		}

		return orgUnitList;
	}
}

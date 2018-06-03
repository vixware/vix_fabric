package com.vix.inventory.initInventory.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.jdbc.IObjectExpandDBService;
import com.vix.core.persistence.jdbc.impl.ObjectExpandDBService;
import com.vix.core.web.Pager;
import com.vix.inventory.initInventory.dao.IInitInventoryDao;
import com.vix.mdm.item.entity.Item;

@Repository("initInventoryDao")
public class InitInventoryDaoImpl extends BaseHibernateDaoImpl implements IInitInventoryDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Item> findAllItemByItemCatalogId(Long id) throws Exception {
		String hql = "from Item i left join i.itemCatalogs as ic where ic.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.EQUAL, id);
		return this.findAllByHql(hql, params);
	}

	@Override
	public Pager findPagerByItemCatalogId(Pager pager, Long id) throws Exception {
		pager = this.findPagerByHql(pager, "from Item i left join i.itemCatalogs as ic where ic.id = " + id, null);
		List<Item> itemList = new ArrayList<Item>();
		for (Object obj : pager.getResultList()) {
			if (obj instanceof Object[]) {
				Object[] o = (Object[]) obj;
				if (o.length >= 1 && o[0] instanceof Item) {
					itemList.add((Item) o[0]);
				}
			}
		}
		pager.getResultList().clear();
		pager.setResultList(itemList);
		return pager;
	}

	@Override
	public boolean checkTableExist(String dbType, String tableName) throws Exception {
		IObjectExpandDBService oedb = new ObjectExpandDBService();
		return oedb.checkTableExist(dbType, tableName);
	}

	@Override
	public Map<String, Object> findProdcutAppend(String sql) throws Exception {
		Map<String, Object> paMap = new HashMap<String, Object>();
		Object obj = findObjectBySql(sql, null);
		Object[] objArray = (Object[]) obj;
		String key = sql.substring(sql.indexOf(" "), sql.indexOf("from"));
		String[] keyArray = key.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			String k = keyArray[i];
			if (null != k && null != objArray && null != objArray[i]) {
				paMap.put(k.trim(), objArray[i]);
			}
		}
		return paMap;
	}
}

package com.vix.inventory.beginDefectiveItem.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;

public interface IBeginDefectiveItemDao extends IBaseHibernateDao {

	public List<Item> findAllItemByItemCatalogId(Long id) throws Exception;

	public boolean checkTableExist(String dbType, String tableName) throws Exception;

	public Map<String, Object> findProdcutAppend(String sql) throws Exception;

	public Pager findPagerByItemCatalogId(Pager pager, Long id) throws Exception;

}

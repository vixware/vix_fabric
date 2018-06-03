package com.vix.mdm.item.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;

public interface IItemDao extends IBaseHibernateDao {

	public List<Item> findAllItemByItemCatalogId(String id) throws Exception;
	
	public boolean checkTableExist(String dbType,String tableName) throws Exception;
	
	public Map<String,Object> findProdcutAppend(String sql) throws Exception;
	
	public Pager findPagerByItemCatalogId(Pager pager,String id, Map<String, Object> params) throws Exception;
	
	public List<List<Object>> findItemSpecification(String specTableName,String ecProductId) throws Exception;

	public List<List<Object>> findProductSpecification(String specTableName, Item ep, List<Specification> spList,
			String... sku) throws Exception;
	
}

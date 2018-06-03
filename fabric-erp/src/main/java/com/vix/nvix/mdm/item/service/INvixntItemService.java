package com.vix.nvix.mdm.item.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;


public interface INvixntItemService extends IBaseHibernateService{

	public List<Item> findAllItemByItemCatalogId(String id) throws Exception;
	
	public Pager findPagerByItemCatalogId(Pager pager,String id, Map<String, Object> params) throws Exception;
	
	public boolean checkTableExist(String dbType,String tableName) throws Exception;
	
	public Map<String,Object> findProdcutAppend(String sql) throws Exception;
	
	public Item saveOrUpdateProduct(Item item,String objectExpandValue) throws Exception;
	
	public List<List<Object>> findItemSpecification(String specTableName,String itemId) throws Exception;
	
	public List<Object> findListBySql(String sql) throws Exception;

}

/**
 * 
 */
package com.vix.inventory.beginDefectiveItem.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IBeginDefectiveItemService extends IBaseHibernateService {

	public List<Item> findAllItemByItemCatalogId(Long id) throws Exception;

	public Pager findPagerByItemCatalogId(Pager pager, Long id) throws Exception;

	public boolean checkTableExist(String dbType, String tableName) throws Exception;

	public Map<String, Object> findProdcutAppend(String sql) throws Exception;

	public Item saveOrUpdateProduct(Item item, String objectExpandValue) throws Exception;

}

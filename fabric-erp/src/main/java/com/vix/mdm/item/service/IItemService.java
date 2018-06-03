package com.vix.mdm.item.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;


public interface IItemService extends IBaseHibernateService{

	public List<Item> findAllItemByItemCatalogId(String id) throws Exception;
	
	public Pager findPagerByItemCatalogId(Pager pager,String id, Map<String, Object> params) throws Exception;
	
	public boolean checkTableExist(String dbType,String tableName) throws Exception;
	
	public Map<String,Object> findProdcutAppend(String sql) throws Exception;
	
	public Item saveOrUpdateProduct(Item item,String objectExpandValue) throws Exception;
	
	public List<List<Object>> findItemSpecification(String specTableName,String itemId) throws Exception;
	
	public List<Object> findListBySql(String sql) throws Exception;
	/**
	 * 获取商品规格明细信息
	 * @param specTableName 规格表名称
	 * @param ecProductId   商品id
	 * @param sku           SKU
	 * @return 扩展表id，sku，采购价格，商品价格，移动端价格，app端价格，市场价，挂牌价，兑换需要的积分数，库存数量（目前无效），规格编码，规格。。。
	 * @throws Exception
	 */
	public List<List<Object>> findProductSpecification(String specTableName, String id, String sku) throws Exception;

}

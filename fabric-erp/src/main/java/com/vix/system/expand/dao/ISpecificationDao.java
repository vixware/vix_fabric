package com.vix.system.expand.dao;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;

public interface ISpecificationDao extends IBaseHibernateDao {
	/** 创建表  */
	public boolean createTable(String dbType,ItemCatalog itemCatalog,String tableName) throws Exception;
	
	/** 检查表是否存在 */
	public boolean checkTableExist(String dbType,String tableName) throws Exception;
	
	/** 检查表中列是否存在 */
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception;
	
	/** 删除扩展表  */
	public boolean dropTable(String dbType,String tableName) throws Exception;
	
	/** 添加商品扩展表字段  */
	public boolean addTableField(String dbType,String tableName,String columnName) throws Exception;
	
	/** 删除商品扩展表字段  */
	public boolean deleteTableField(String dbType,String tableName,String columnName) throws Exception;

	public String updateEcProductSpecData(Item ep, String productCategoryIdStr, String specTableName, String specData)throws Exception;
}

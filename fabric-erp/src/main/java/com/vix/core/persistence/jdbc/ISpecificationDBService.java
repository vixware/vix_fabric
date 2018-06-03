package com.vix.core.persistence.jdbc;

import java.io.Serializable;

import com.vix.mdm.item.entity.ItemCatalog;

/** 通用Service */
public interface ISpecificationDBService extends Serializable{
	
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
}
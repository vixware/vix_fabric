package com.vix.core.persistence.jdbc;

import java.io.Serializable;

/** 通用DAO */
public interface IJdbcDao extends Serializable{
	
	/** 根据分类创建商品扩展表  */
	public boolean createExpandTable(String createTableString) throws Exception;
	
	/** 检查表是否存在 */
	public boolean checkTableExist(String tableName) throws Exception;
	
	/** 检查表中的列是否存在 */
	public boolean checkColumnExist(String tableName,String column) throws Exception;
	
	/** 删除商品扩展表  */
	public boolean dropExpandTable(String tableName) throws Exception;
	
	/** 修改商品扩展表列名  */
	public boolean changeTableColumnName(String tableName,String oldColumnName,String newColumnName) throws Exception;
	
	/** 添加商品扩展表字段  */
	public boolean addExpandTableField(String addColumn) throws Exception;
	
	/** 删除商品扩展表字段  */
	public boolean deleteExpandTableField(String dropColumn) throws Exception;
	
	/** 创建外键关联 */
	public boolean createForeignKey(String mainTable,String subTable) throws Exception;
}
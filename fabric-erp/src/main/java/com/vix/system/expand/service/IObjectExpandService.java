package com.vix.system.expand.service;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;

public interface IObjectExpandService extends IBaseHibernateService{

	/** 创建表  */
	public boolean createTable(String dbType,ObjectExpand objectExpand) throws Exception;
	
	/** 创建外键关系  */
	public <T> boolean createForeignKey(String dbType,Class<T> mainClass,String subTable) throws Exception;
	
	/** 检查表是否存在 */
	public boolean checkTableExist(String dbType,String tableName) throws Exception;
	
	/** 检查表中列是否存在 */
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception;
	
	/** 删除商品扩展表  */
	public boolean dropExpandTable(String dbType,String tableName) throws Exception;
	
	/** 添加商品扩展表字段  */
	public boolean addExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception;
	
	/** 删除商品扩展表字段  */
	public boolean deleteExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception;
}

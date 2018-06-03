package com.vix.system.expand.dao.impl;

import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.jdbc.IObjectExpandDBService;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.impl.ObjectExpandDBService;
import com.vix.system.expand.dao.IObjectExpandDao;

@Repository("objectExpandDao")
public class ObjectExpandDaoImpl extends BaseHibernateDaoImpl implements IObjectExpandDao {

	private static final long serialVersionUID = -7524355023279202816L;

	private IObjectExpandDBService oedb = new ObjectExpandDBService();
	
	@Override
	public boolean createTable(String dbType,ObjectExpand objectExpand) throws Exception {
		return oedb.createTable(dbType, objectExpand);
	}
	
	@Override
	public <T> boolean createForeignKey(String dbType,Class<T> mainClass,String subTable) throws Exception{
		String mainTable = getTableNameByClass(mainClass);
		return oedb.createForeignKey(dbType, mainTable, subTable);
	}
	
	/** 检查表是否存在 */
	@Override
	public boolean checkTableExist(String dbType,String tableName) throws Exception{
		return oedb.checkTableExist(dbType, tableName);
	}
	
	/** 检查表中列是否存在 */
	@Override
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception{
		return oedb.checkColumnExist(dbType, tableName, columnName);
	}
	
	/** 删除扩展表  */
	@Override
	public boolean dropExpandTable(String dbType,String tableName) throws Exception{
		return oedb.dropExpandTable(dbType, tableName);
	}
	
	/** 添加扩展表字段  */
	@Override
	public boolean addExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		return oedb.addExpandTableField(dbType, objectExpand);
	}
	
	/** 删除扩展表字段  */
	@Override
	public boolean deleteExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		return oedb.deleteExpandTableField(dbType, objectExpand);
	}
}

package com.vix.system.expand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.system.expand.dao.IObjectExpandDao;
import com.vix.system.expand.service.IObjectExpandService;

@Service("objectExpandService")
@Transactional
public class ObjectExpandServiceImpl extends BaseHibernateServiceImpl implements IObjectExpandService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IObjectExpandDao objectExpandDao;

	/** 创建表  */
	@Override
	public boolean createTable(String dbType,ObjectExpand objectExpand) throws Exception{
		return objectExpandDao.createTable(dbType, objectExpand);
	}
	
	/** 创建外键关系  */
	@Override
	public <T> boolean createForeignKey(String dbType,Class<T> mainClass,String subTable) throws Exception{
		return objectExpandDao.createForeignKey(dbType,mainClass,subTable);
	}
	
	/** 检查表是否存在 存在返回true */
	@Override
	public boolean checkTableExist(String dbType,String tableName) throws Exception{
		return objectExpandDao.checkTableExist(dbType, tableName);
	}
	
	/** 检查表中列是否存在 */
	@Override
	public boolean checkColumnExist(String dbType,String tableName,String columnName) throws Exception{
		return objectExpandDao.checkColumnExist(dbType, tableName,columnName);
	}
	
	/** 删除扩展表  */
	@Override
	public boolean dropExpandTable(String dbType,String tableName) throws Exception{
		return objectExpandDao.dropExpandTable(dbType, tableName);
	}
	
	/** 添加扩展表字段  */
	@Override
	public boolean addExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		return objectExpandDao.addExpandTableField(dbType, objectExpand);
	}
	
	/** 删除扩展表字段  */
	@Override
	public boolean deleteExpandTableField(String dbType,ObjectExpandField objectExpand) throws Exception{
		return objectExpandDao.deleteExpandTableField(dbType, objectExpand);
	}
}

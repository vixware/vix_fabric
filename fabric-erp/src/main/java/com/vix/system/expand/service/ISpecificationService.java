package com.vix.system.expand.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.mdm.item.entity.ItemCatalog;

public interface ISpecificationService extends IBaseHibernateService{
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
	
	/** 保存规格  */
	public Specification mergeSpecification(Specification specification) throws Exception;
	/**
	 * 根据分类获取规格表名,当前分类未绑定规格时，查找上一级规格表名
	 * @param categoryId
	 * @return
	 */
	public String findTableNameByProductCategoryId(String categoryId) throws Exception;

	public List<Specification> findSpecificationByProductCatetoryId(String categoryId, Map<String, Object> params) throws Exception;

	public boolean addSpecificationGroup(String id, String productCategoryIdStr,
			List<List<SpecificationDetail>> specificationDetailList, String specData, Map<String, Object> params) throws Exception;

	public boolean deleteSpecificationGroup(String sgId, String ecProductId, String productCategoryId) throws Exception;
}

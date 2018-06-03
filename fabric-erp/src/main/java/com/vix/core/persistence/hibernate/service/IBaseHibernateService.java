package com.vix.core.persistence.hibernate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.web.Pager;

/** 通用Service */
@SuppressWarnings("rawtypes")
public interface IBaseHibernateService extends Serializable {

	// 新增，用sql查询数据，可翻页，返回List<Map>
	public Pager findPagerBySqlFull(Pager pager, String fullSql, Map<String, Object> params) throws Exception;

	/** 根据ID获取数据库数据 */
	public <T> T findEntityById(Class<T> entityClass, String id) throws Exception;

	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception;

	public <T> T findEntityByAttributeNoTenantId(Class<T> entityClass, String attribute, Object value) throws Exception;

	/** 查询所有对象,适用于小数据量不需要分页的查询 */
	public <T> List<T> findAllSubEntity(Class<T> entityClass, String attributeName, String parentId,
			Map<String, Object> params) throws Exception;

	public <T> List<T> findAllByEntityClass(Class<T> entityClass) throws Exception;
	public <T> T findEntityByAttribute(Class<T> entityClass ,String attribute,Object value,String appId) throws Exception;
	/**
	 * 没有排序，如果需要排序用findPagerAllByHql,使用pager中的resultList
	 * 
	 * @param entityClass
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAllByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	// 屏蔽，貌似没人在用,最好用其他方式控制不加tenantId，这种方式不好控制
	// public <T> List<T> findAllByEntityClassNoTenantId(Class<T> entityClass)
	// throws Exception;
	/** 根据条件查询数据条数 */
	public <T> long findDataCountByHqlConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllByEntityClassAndAttribute(Class<T> entityClass, String attribute, Object value)
			throws Exception;

	/** 保存对象 */
	public <T> T save(Object entity) throws Exception;

	/** 更新对象 */
	public void update(Object entity) throws Exception;

	/** 执行插入或更新SQL */
	public void executeSql(String sql, Map<String, Object> params) throws Exception;

	/** 保存或更新对象拷贝 */
	public void saveOrUpdate(Object entity) throws Exception;

	/** 保存或更新对象拷贝 */
	public <T> T merge(Object entity) throws Exception;

	/** 原生的对象保存（更新方法） 不添加TenantId */
	public <T> T mergeOriginal(Object entity) throws Exception;

	public <T> String getTableNameByClass(Class<T> entityClass) throws Exception;

	/** 根据ID删除记录 */
	public <T> void deleteById(Class<T> entityClass, String id) throws Exception;

	public <T> void deleteByEntity(Object entity) throws Exception;

	public <T> void deleteByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception;

	/** 刷新对象,提交脏数据 */
	public void refresh(Object entity) throws Exception;

	/** 根据条件查询数据条数 */
	public <T> long findDataCountByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	/** 根据HQL获取对象，HQL语句采用配参的方式。获取到多于一条记录时会抛出异常 */
	public <T> T findObjectByHql(String hql, Map<String, Object> params) throws Exception;
	// 注释掉，没有人在用，后面很难控制
	// public <T> T findObjectBySql(String sql ,Map<String,Object> params)
	// throws Exception;

	<T> T findObjectFirstByHqlConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	/** 查询分页数据 */
	public Pager findPagerByHql(Pager pager, Class entityClass) throws Exception;

	// 注释掉，没有人在用，后面很难控制
	// public Pager findPagerBySql(Pager pager,String tableName) throws
	// Exception;
	public Pager findPagerByHqlConditions(Pager pager, Class entityClass, Map<String, Object> params) throws Exception;
	
	public Pager findPagerByHqlConditionsNoTenantId(Pager pager, Class entityClass, Map<String, Object> params) throws Exception;
	// 注释掉，没有人在用
	// public Pager findPagerBySqlConditions(Pager pager,String
	// tableName,Map<String,Object> params) throws Exception;
	// public Pager findTreePagerByHql(Pager pager,Class entityClass,String
	// attributeName , Long parentId,Map<String,Object> params) throws
	// Exception;
	// public Pager findTreePagerByHql(Pager pager,Class entityClass,String
	// attributeName , Long parentId) throws Exception;

	/**
	 * 代码同findPagerByHqlConditions，但是不处理count的值，也不设置limit
	 * 
	 * @param pager
	 * @param entityClass
	 * @param params
	 * @return
	 * @throws Exception
	 */
	// Pager findPagerAllByHqlConditions(Pager pager, Class entityClass,
	// Map<String, Object> params) throws Exception;

	/** 批量添加数据 */
	public <T> void batchSave(List<T> entityList) throws Exception;

	/** 批量修改数据 */
	public <T> void batchUpdateByList(List<T> entityList) throws Exception;

	public void batchUpdateByHql(String hql, Map<String, Object> params) throws Exception;

	public void batchUpdateBySql(String sql, Map<String, Object> params) throws Exception;

	// 注释掉，没有人在用，后面很难控制
	// public Pager findTreePagerByHql(StringBuilder pageHql,StringBuilder
	// countHql,Pager pager,Map<String,Object> params) throws Exception;

	/** 批量删除数据 */
	public <T> void batchDelete(Class<T> entityClass, List<String> ids) throws Exception;

	public void batchDeleteByHql(String hql, Map<String, Object> params) throws Exception;

	public void batchDeleteBySql(String sql, Map<String, Object> params) throws Exception;

	/** 检查特定属性的数据是否已经存在 */
	public <T> boolean isEntityExist(Class<T> entityClass, String id, String attribute, String value) throws Exception;

	/** 持久化日志 */
	public boolean persistenceLog(BaseEntity log) throws Exception;

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj);

	public void clear();

	/** 简单查询 */
	public Pager findPagerByOrHqlConditions(Pager pager, Class entityClass, Map<String, Object> params)
			throws Exception;

	/** 通过sql语句查询 */
	/**
	 * 代码中不要在用这个方法，找时间要删掉，后面处理很难控制 当前有地方使用，所以没有注释掉
	 * 
	 * @param pager
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Pager findPagerJustByHql(Pager pager, String hql) throws Exception;

	/** 直接HQL查询 注：select r.authoritys from Role r where r.id=? */
	/**
	 * 代码中不要在用这个方法，找时间要删掉，后面处理很难控制 当前有地方使用，所以没有注释掉
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAllEntityByHql(StringBuilder hql, Map<String, Object> params) throws Exception;

}
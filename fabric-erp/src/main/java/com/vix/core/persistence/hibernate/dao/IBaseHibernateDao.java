package com.vix.core.persistence.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.JDBCException;

import com.vix.core.web.Pager;

/** 通用DAO */
public interface IBaseHibernateDao extends Serializable {

	// 新增，用sql查询数据，可翻页，返回List<Map>
	public Pager findPagerBySqlFull(Pager pager, String fullSql, Map<String, Object> params) throws Exception;

	/** 根据ID获取数据库数据 */
	public <T> T findEntityById(Class<T> entityClass, String id) throws Exception;

	public <T> T queryEntityByJdbc(String tableName, Class<T> T, String id) throws Exception;

	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception;
	public <T> T findEntityByAttribute(Class<T> entityClass ,String attribute,Object value,String appId) throws Exception;
	public <T> long findDataCountByHqlConditions(String hql, Map<String, Object> params) throws Exception;

	/** 查询所有对象,适用于小数据量不需要分页的查询 */
	public <T> List<T> findAllByHql(String hql, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllBySql(String sql, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllByEntityClass(Class<T> entityClass) throws Exception;

	public <T> List<T> findAllByEntityClassNoTenantId(Class<T> entityClass) throws Exception;

	public <T> List<T> findAllByEntityClassAndAttribute(Class<T> entityClass, String attribute, Object value)
			throws Exception;

	public <T> List<T> findAllByEntityClassAndAttributeNoTenantId(Class<T> entityClass, String attribute, Object value)
			throws Exception;

	/** 根据uuid 查询对象 */
	public <T> T findObjectByUUID(Class<T> entityClass, String uuid) throws Exception;

	public <T> T findEntityByAttributeNoTenantId(Class<T> entityClass, String attribute, Object value) throws Exception;

	public <T> String getTableNameByClass(Class<T> entityClass) throws Exception;

	public <T> List<T> findAllByHql2(String hql, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllByHqlOrginial(String hql, Map<String, Object> params) throws Exception;

	public <T> T findFirstByHqlOrginial(String hql, Map<String, Object> params) throws Exception;

	public <T> List<T> findFirstListByHqlOrginial(String hql, Map<String, Object> params, int size) throws Exception;

	public <T> List<T> findAllByHql2NoTenantId(String hql, Map<String, Object> params) throws Exception;

	public <T> List<T> findAllByHql2NoTenantId(String hql, String aliasName, Map<String, Object> params)
			throws Exception;

	/** 特殊情况 使用session filter */
	public <T> List<T> findAllByHql(String filterName, Map<String, Object> filterParams, String hql,
			Map<String, Object> params) throws Exception;

	public List<Object> findListBySql(String sql, Map<String, Object> params) throws Exception;

	/** 保存对象 */
	public <T> T save(Object entity) throws Exception;

	/** 更新对象 */
	public void update(Object entity) throws Exception;

	/** 执行插入或更新SQL */
	public void executeSql(String sql, Map<String, Object> params) throws Exception;

	/** 保存或更新对象拷贝 */
	public void saveOrUpdate(Object entity) throws Exception;

	/**
	 * 原生的对象保存（更新方法） 不添加TenantId
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdateOriginal(Object entity) throws Exception;

	public <T> void saveOrUpdateOriginalBatch(List<T> entityList) throws Exception;

	/** 保存或更新对象拷贝 */
	public <T> T merge(Object entity) throws Exception;

	/** 原生的对象保存（更新方法） 不添加TenantId */
	public <T> T mergeOriginal(Object entity) throws Exception;

	/** 根据ID删除记录 */
	public <T> void deleteById(Class<T> entityClass, String id) throws Exception;

	public <T> void deleteByEntity(Object entity) throws Exception;

	public <T> void deleteByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception;

	/** 刷新对象,提交脏数据 */
	public void refresh(Object entity) throws Exception;

	/** 根据条件查询数据条数 */
	public <T> long findDataCountByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	/** 根据条件查询数据条数 */
	public long findDataCountByHql(StringBuilder hql, String alilasName, Map<String, Object> params) throws Exception;

	public long findDataCountByHqlOrginial(StringBuilder hql, String alilasName, Map<String, Object> params)
			throws Exception;

	/** 根据HQL获取对象，HQL语句采用配参的方式。获取到多于一条记录时会抛出异常 */
	public <T> T findObjectByHql(String hql, Map<String, Object> params) throws Exception;

	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception;

	public <T> T findObjectByHql(String hql, Boolean isAddTenantId, Map<String, Object> params) throws Exception;

	public <T> T findObjectBySql(String sql, Map<String, Object> params) throws Exception;

	/** 查询分页数据 */
	public Pager findPagerByHql(Pager pager, String hql, Map<String, Object> params) throws Exception;

	public Pager findPagerByHql(Pager pager, String hql, Map<String, Object> params, boolean isFindAll)
			throws Exception;

	/** 只通过hql查询列表数据 */
	public Pager findPagerJustByHql(Pager pager, String hql) throws Exception;

	public Pager findPagerBySql(Pager pager, String sql, Map<String, Object> params) throws Exception;

	/** */
	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params)
			throws Exception;

	public Pager findPager2ByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params)
			throws Exception;

	public Pager findPagerOrginialByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params)
			throws Exception;

	// public Pager findPagerBySql(Pager pager,String sql,Map<String,Object>
	// params) throws Exception;

	/** 批量添加数据 */
	public <T> void batchSave(List<T> entityList) throws Exception;

	/** 直接HQL查询 注：select r.authoritys from Role r where r.id=? */
	public Pager findTreePagerByHql(String pageHql, String countHql, Pager pager, Map<String, Object> params)
			throws Exception;

	/** 批量修改数据 */
	public <T> void batchUpdateByList(List<T> entityList) throws Exception;

	public void batchUpdateByHql(String hql, Map<String, Object> params) throws Exception;

	// public void batchUpdateByHql2(String hql,Map<String,List<Object>> params)
	// throws Exception;
	public void batchUpdateBySql(String sql, Map<String, Object> params) throws Exception;

	// public void batchUpdateBySql2(String sql,Map<String,List<Object>> params)
	// throws Exception;
	public void batchUpdateBySql(String sql, List<Object[]> params) throws Exception;

	/** 批量删除数据 */
	public <T> void batchDelete(Class<T> entityClass, List<String> ids) throws Exception;

	public void batchDeleteByHql(String hql, Map<String, Object> params) throws Exception;

	public void batchDeleteBySql(String sql, Map<String, Object> params) throws Exception;

	/** 检查特定属性的数据是否已经存在 */
	public <T> boolean isEntityExist(Class<T> entityClass, String id, String attribute, String value) throws Exception;

	// jdbc 批量处理
	public void jdbcBatchUpdate(String sql, Object... params) throws Exception;

	public void jdbcBatchUpdate(String sql, List<Object[]> params) throws Exception;

	/**
	 * 拦截处理HQL
	 * 
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	public String beforeHqlExecute(String hql, String entityClass, Map<String, Object> params);

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj);

	public void clear();

	public void flush();

	// jdbc
	/**
	 * jdbc 查询列表 @Title: queryObjectList @Description: TODO @param @param
	 * sql @param @param T @param @param values @param @return @param @throws
	 * JDBCException 设定文件 @return List<T> 返回类型 @throws
	 */
	public <T extends Object> List<T> queryObjectListBySql(String sql, Class T, Object[] values) throws JDBCException;

	public <T extends Object> T queryObjectUniqueBySql(String sql, Class T, Object[] values) throws JDBCException;

	public <T extends Object> Pager queryPagerListBySql(Pager pager, Class T, String sql, String countSql,
			Object[] values);

	public <T> T queryForObject(String sql, Class<T> T, Object[] values) throws JDBCException;
}
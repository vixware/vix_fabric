package com.vix.core.persistence.hibernate.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@SuppressWarnings("rawtypes")
@Service("baseHibernateService")
@Transactional
public class BaseHibernateServiceImpl implements IBaseHibernateService, Serializable {

	private static final long serialVersionUID = 1L;

	protected static Logger logger = LoggerFactory.getLogger(BaseHibernateServiceImpl.class);

	@Autowired
	public IBaseHibernateDao baseHibernateDao;

	// 新增，用sql查询数据，可翻页，返回List<Map>
	@Override
	public Pager findPagerBySqlFull(Pager pager, String fullSql, Map<String, Object> params) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder(fullSql);

		if (null != pager.getOrderField()) {
			sqlBuilder.append(" order by ");
			sqlBuilder.append(pager.getOrderField());
			sqlBuilder.append(" ");
			sqlBuilder.append(pager.getOrderBy());
		}

		return baseHibernateDao.findPagerBySqlFull(pager, sqlBuilder.toString(), params);
	}

	private void buildSearchQl(StringBuilder qlBuilder, Map<String, Object> params) {
		if (params == null || params.isEmpty())
			return;

		// 2014-05-04 如果为系统级管理员admin，去掉tenantId参数
		// 2014-05-16 具体逻辑不能这么判断,或者其他承租户不能叫admin
		if (SecurityUtil.getCurrentUserName().equals(BizConstant.USERACCOUNT_SUPERADMIN)) {
			if (params != null)
				params.remove(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY);
		}

		Boolean isBreak4CompInnerCode = false;
		if (params.containsKey(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY)) {
			String compInnerCodeStr = (String) params.get(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);
			if (compInnerCodeStr.equals(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY_NO)) {
				// params.remove(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);//如果此处从params中删除掉，
				// 则在调用dao时将不能验证是否增加companyInnerCode
				isBreak4CompInnerCode = true;
			}
		}

		int keyCount = params.keySet().size();
		if (keyCount > 0) {
			// 配合传入hql中的where 1=1
			qlBuilder.append(" and ");
		}

		/** 参数里需要移除的参数key */
		StringBuilder needRemoveList = new StringBuilder();
		for (String key : params.keySet()) {
			if (StrUtils.isNotEmpty(key) && key.equals(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY) && isBreak4CompInnerCode) {
				keyCount--;
				continue;
			}
			if (!StrUtils.objectIsNull(key)) {
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(StrUtils.fixParamForHql(k[0]));
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						} else if (k[1].equals(SearchCondition.ISNOT)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is not null ");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0]);
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(k[0]);
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					}
				} else {
					if (key.contains(".")) {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(StrUtils.fixParamForHql(k[0]));
					} else {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(key);
					}
				}
				if (keyCount > 1) {
					qlBuilder.append(" and ");
				}
				keyCount--;
			}
		}
		for (String k : needRemoveList.toString().split(",")) {
			if (null != k && !"".equals(k)) {
				params.remove(k);
			}
		}
	}

	/**
	 * 处理hql语句查询中含有in条件的情况
	 * 
	 * @param inValue
	 *            参数Map中in条件的value
	 * @param inList
	 *            参数Map中的in条件的key，方法执行完要将in条件的key从map中移除，in条件的hql不需要配参数
	 * @param qlBuilder
	 *            构建的hql语句
	 * @param key
	 *            参数Map的key
	 */
	private void dealHqlInCondition(Object inValue, StringBuilder needRemoveList, StringBuilder qlBuilder, String key) {
		String[] k = key.split(",");
		if (null != inValue && !"".equals(inValue)) {
			needRemoveList.append(key).append(",");
			qlBuilder.append("hentity.").append(k[0]);
			qlBuilder.append(" in (");
			String[] ivArray = inValue.toString().split(",");
			for (int i = 0; i < ivArray.length; i++) {
				String iv = ivArray[i];
				if (null != iv && !"".equals(iv)) {
					qlBuilder.append("'");
					qlBuilder.append(iv);
					qlBuilder.append("'");
					if (i < ivArray.length - 1) {
						qlBuilder.append(",");
					}
				}
			}
			qlBuilder.append(") ");
		}
	}

	/**
	 * 生成查询hql的起始部分，使用hentity作为别名 条件允许的情况下，各service中所有hql查询方法调用这个方法生成hqlBuilder
	 * 生成hql包含1=1，后面拼接hql的方法如果有参数，需要以and起头
	 * 
	 * @param entityClass
	 * @param params
	 * @return
	 */
	protected <T> StringBuilder genHqlHeadBuilder(Class<T> entityClass, Map<String, Object> params) {
		return this.genHqlHeadBuilder(entityClass, params, "hentity");
	}

	/**
	 * 生成查询hql的起始部分 条件允许的情况下，各service中所有hql查询方法调用这个方法生成hqlBuilder
	 * 生成hql包含1=1，后面拼接hql的方法如果有参数，需要以and起头
	 * 
	 * @param entityClass
	 * @param params
	 * @param asName
	 *            别名
	 * @return
	 */
	protected <T> StringBuilder genHqlHeadBuilder(Class<T> entityClass, Map<String, Object> params, String asName) {
		StringBuilder hqlBuilder = new StringBuilder("select ");
		hqlBuilder.append(asName);
		hqlBuilder.append(" from ");
		hqlBuilder.append(entityClass.getName());
		hqlBuilder.append(" ").append(asName).append(" ");

		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" where 1=1 ");
		} else {
			// 2015-05-19 现在baseDao在处理tenantId时需要hql有where字段
			hqlBuilder.append(" where 1=1 ");
		}

		return hqlBuilder;
	}

	@Override
	public <T> List<T> findAllSubEntity(Class<T> entityClass, String attributeName, String parentId, Map<String, Object> params) throws Exception {
		if (StrUtils.isNotEmpty(attributeName) && parentId != null)// &&
																	// parentId>0
		{
			params.put(attributeName + "," + SearchCondition.EQUAL, parentId);
		} else {
			params.put(attributeName + "," + SearchCondition.IS, null);
		}

		return this.findAllByConditions(entityClass, params);
	}

	@Override
	public <T> List<T> findAllByEntityClassAndAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != value && !"".equals(value)) {
			params.put(attribute + "," + SearchCondition.EQUAL, value);
		} else {
			params.put(attribute + "," + SearchCondition.IS, value);
		}

		return this.findAllByConditions(entityClass, params);
	}

	@Override
	public <T> List<T> findAllByEntityClass(Class<T> entityClass) throws Exception {

		return this.findAllByConditions(entityClass, null);
	}

	@Override
	public <T> List<T> findAllByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {

		// 这里最好保证params不是null，到baseDao处理时会有问题
		// 到具体处理方法时再new HashMap，则无法将对象回传给父一级的方法(因为当时是null)
		if (params == null)
			params = new HashMap<String, Object>();

		StringBuilder hqlBuilder = this.genHqlHeadBuilder(entityClass, params);

		buildSearchQl(hqlBuilder, params);

		String hql = this.baseHibernateDao.beforeHqlExecute(hqlBuilder.toString(), entityClass.getName(), params);

		return baseHibernateDao.findAllByHql(hql, params);
	}

	/**
	 * 生成查询使用的hql
	 * 
	 * @param pager
	 * @param entityClass
	 * @param params
	 * @return
	 */
	private String genFindPagerHqlConditions(Pager pager, Class entityClass, Map<String, Object> params) {
		StringBuilder hqlBuilder = genHqlHeadBuilder(entityClass, params);

		buildSearchQl(hqlBuilder, params);

		if (null != pager.getOrderField()) {
			hqlBuilder.append(" order by hentity.");
			hqlBuilder.append(pager.getOrderField());
			hqlBuilder.append(" ");
			hqlBuilder.append(pager.getOrderBy());
		}

		return hqlBuilder.toString();
	}

	@Override
	public Pager findPagerByHql(Pager pager, Class entityClass) throws Exception {
		return this.findPagerByHqlConditions(pager, entityClass, null);
	}

	@Override
	public Pager findPagerByOrHqlConditions(Pager pager, Class entityClass, Map<String, Object> params) throws Exception {
		return this.findPagerByHqlConditions(pager, entityClass, params);
	}

	@Override
	public Pager findPagerByHqlConditions(Pager pager, Class entityClass, Map<String, Object> params) throws Exception {
		// 这里最好保证params不是null，到baseDao处理时会有问题
		// 到具体处理方法时再new HashMap，则无法将对象回传给父一级的方法(因为当时是null)
		if (params == null)
			params = new HashMap<String, Object>();

		String hql = this.genFindPagerHqlConditions(pager, entityClass, params);

		hql = baseHibernateDao.beforeHqlExecute(hql, entityClass.getName(), params);

		return baseHibernateDao.findPagerByHql(pager, hql, params);
	}

	@Override
	public <T> T findObjectFirstByHqlConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {
		List<T> list = this.findAllByConditions(entityClass, params);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public <T> T findEntityById(Class<T> entityClass, String id) throws Exception {
		return baseHibernateDao.findEntityById(entityClass, id);
	}

	@Override
	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		return baseHibernateDao.findEntityByAttribute(entityClass, attribute, value);
	}

	@Override
	public <T> T findEntityByAttributeNoTenantId(Class<T> entityClass, String attribute, Object value) throws Exception {
		return baseHibernateDao.findEntityByAttributeNoTenantId(entityClass, attribute, value);
	}

	@Override
	public <T> T save(Object entity) throws Exception {
		return baseHibernateDao.save(entity);
	}

	@Override
	public void update(Object entity) throws Exception {
		baseHibernateDao.update(entity);
	}

	@Override
	public void executeSql(String sql, Map<String, Object> params) throws Exception {
		baseHibernateDao.executeSql(sql, params);
	}

	@Override
	public void saveOrUpdate(Object entity) throws Exception {
		baseHibernateDao.saveOrUpdate(entity);
	}

	@Override
	public <T> T merge(Object entity) throws Exception {
		return baseHibernateDao.merge(entity);
	}

	@Override
	public <T> T mergeOriginal(Object entity) throws Exception {
		return baseHibernateDao.mergeOriginal(entity);
	}

	@Override
	public <T> String getTableNameByClass(Class<T> entityClass) throws Exception {
		return baseHibernateDao.getTableNameByClass(entityClass);
	}

	@Override
	public <T> void deleteById(Class<T> entityClass, String id) throws Exception {
		baseHibernateDao.deleteById(entityClass, id);
	}

	@Override
	public <T> void deleteByEntity(Object entity) throws Exception {
		baseHibernateDao.deleteByEntity(entity);
	}

	@Override
	public <T> void deleteByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		baseHibernateDao.deleteByAttribute(entityClass, attribute, value);
	}

	@Override
	public void refresh(Object entity) throws Exception {
		baseHibernateDao.refresh(entity);
	}

	/** 根据条件查询数据条数 */
	@Override
	public <T> long findDataCountByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findDataCountByConditions(entityClass, params);
	}

	@Override
	public <T> T findObjectByHql(String hql, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findObjectByHql(hql, params);
	}

	@Override
	public <T> void batchSave(List<T> entityList) throws Exception {
		baseHibernateDao.batchSave(entityList);
	}

	@Override
	public <T> void batchUpdateByList(List<T> entityList) throws Exception {
		baseHibernateDao.batchUpdateByList(entityList);
	}

	@Override
	public void batchUpdateByHql(String hql, Map<String, Object> params) throws Exception {
		baseHibernateDao.batchUpdateByHql(hql, params);
	}

	@Override
	public void batchUpdateBySql(String sql, Map<String, Object> params) throws Exception {
		baseHibernateDao.batchUpdateBySql(sql, params);
	}

	@Override
	public <T> void batchDelete(Class<T> entityClass, List<String> ids) throws Exception {
		baseHibernateDao.batchDelete(entityClass, ids);
	}

	@Override
	public void batchDeleteByHql(String hql, Map<String, Object> params) throws Exception {
		baseHibernateDao.batchDeleteByHql(hql, params);
	}

	@Override
	public void batchDeleteBySql(String sql, Map<String, Object> params) throws Exception {
		baseHibernateDao.batchDeleteBySql(sql, params);
	}

	@Override
	public <T> boolean isEntityExist(Class<T> entityClass, String id, String attribute, String value) throws Exception {
		return baseHibernateDao.isEntityExist(entityClass, id, attribute, value);
	}

	/** 持久化日志 */
	@Override
	public boolean persistenceLog(BaseEntity log) throws Exception {
		return baseHibernateDao.save(log);
	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	@Override
	public void evict(Object obj) {
		baseHibernateDao.evict(obj);
	}

	@Override
	public void clear() {
		baseHibernateDao.clear();
	}

	@Override
	public <T> List<T> findAllEntityByHql(StringBuilder hql, Map<String, Object> params) throws Exception {
		if (null != params && params.keySet().size() > 0) {
			hql.append(" and ");
			buildSearchQl(hql, params);
		}
		return baseHibernateDao.findAllByHql(hql.toString(), params);
	}

	@Override
	public Pager findPagerJustByHql(Pager pager, String hql) throws Exception {
		return baseHibernateDao.findPagerJustByHql(pager, hql);
	}

	@Override
	/** 根据条件查询数据条数 */
	public <T> long findDataCountByHqlConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {
		StringBuilder hqlBuilder = new StringBuilder(" from ");
		hqlBuilder.append(entityClass.getName());
		hqlBuilder.append(" hentity ");

		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" where 1=1 ");
		} else {
			hqlBuilder.append(" where 1=1 ");
		}

		buildSearchQl(hqlBuilder, params);
		return baseHibernateDao.findDataCountByHqlConditions(hqlBuilder.toString(), params);
	}

	@Override
	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value, String appId) throws Exception {
		if (null == appId || "".equals(appId)) {
			return null;
		}
		return baseHibernateDao.findEntityByAttribute(entityClass, attribute, value, appId);
	}

	@Override
	public Pager findPagerByHqlConditionsNoTenantId(Pager pager, Class entityClass, Map<String, Object> params) throws Exception {
		if (params == null)
			params = new HashMap<String, Object>();

		String hql = this.genFindPagerHqlConditions(pager, entityClass, params);

		hql = baseHibernateDao.beforeHqlExecute(hql, entityClass.getName(), params);

		return baseHibernateDao.findPagerByHql(pager, hql, params);
	}
}
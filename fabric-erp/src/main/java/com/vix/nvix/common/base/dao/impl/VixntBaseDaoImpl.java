/**
 * 
 */
package com.vix.nvix.common.base.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;
import com.vix.nvix.common.base.dao.IVixntBaseDao;

/**
 * 
 * @ClassFullName com.vix.nvix.common.base.dao.impl.VixntBaseDaoImpl
 *
 * @author bjitzhang
 *
 * @date 2016年4月7日
 *
 */
@Transactional
@Repository("vixntBaseDao")
public class VixntBaseDaoImpl extends BaseHibernateDaoImpl implements IVixntBaseDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllDataByHql(String hql, Map<String, Object> params) throws Exception {

		Query query = getSession().createQuery(hql);
		if (null != params) {
			configQuery(query, params);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findObjectBySql(String sql, Map<String, Object> params) throws Exception {
		Query query = getSession().createSQLQuery(sql);
		query.setProperties(params);
		Object obj = query.uniqueResult();
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	private void configQuery(Query query, Map<String, Object> params) {
		for (String key : params.keySet()) {
			if (null != key && !"".equals(key)) {
				Object value = params.get(key);
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(k[0], value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							System.out.println(k[0] + "--" + value);
							query.setParameter(k[0], "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(k[0], "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(k[0], value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					}
				} else {
					if (key.contains(".")) {
						query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
					} else {
						query.setParameter(key, value);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllDataByHqlOrginial(String hql, Map<String, Object> params) throws Exception {
		Query query = getSession().createQuery(hql);
		if (null != params) {
			addArgs(params, query);
		}
		return query.list();
	}

	private void addArgs(Map<String, Object> argMap, Query query) {
		if (argMap != null && !argMap.isEmpty()) {
			Iterator<String> itKey = argMap.keySet().iterator();
			while (itKey.hasNext()) {
				String key = itKey.next();
				if (argMap.get(key) instanceof List) {
					query.setParameterList(key, (List<?>) argMap.get(key));
				} else {
					query.setParameter(key, argMap.get(key));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findObjectList(String hql) throws Exception {
		Query countQuery = getSession().createSQLQuery(hql);
		return countQuery.list();
	}

}

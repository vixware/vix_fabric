/**
 * 
 */
package com.vix.traceability.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;
import com.vix.traceability.dao.ITraceabilityDao;

/**
 * 
 * com.traceability.dao.impl.TraceabilityDaoImpl
 *
 * @author bjitzhang
 *
 * @date 2015年9月23日
 */
@Transactional
@Repository("traceabilityDao")
public class TraceabilityDaoImpl extends BaseHibernateDaoImpl implements ITraceabilityDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getTableName(Class<?> clazz) {
		ClassMetadata metadata = getSession().getSessionFactory().getClassMetadata(clazz);
		AbstractEntityPersister aep = (AbstractEntityPersister) metadata;
		return aep.getTableName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllDataByHql(String hql, Map<String, Object> params) throws Exception {

		Query query = getSession().createQuery(hql);
		//getTenantIdCondition4Map(params);
		if (null != params) {
			configQuery(query, params);
		}
		return query.list();
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
}

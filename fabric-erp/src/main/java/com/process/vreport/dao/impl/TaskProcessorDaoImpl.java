/**
 * 
 */
package com.process.vreport.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.process.vreport.dao.ITaskProcessorDao;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;

/**
 * 
 * com.e6soft.vreport.dao.impl.TaskProcessorDaoImpl
 *
 * @author zhanghaibing
 *
 * @date 2015年1月8日
 */
@Service("taskProcessorDao")
public class TaskProcessorDaoImpl extends BaseHibernateDaoImpl implements ITaskProcessorDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAllEntityByparams(String hql, Map<String, Object> params) throws Exception {

		Query query = getSession().createQuery(hql);
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
						// 还需要细化
						query.setParameter(key, value);
					}
				}
			}
		}
	}
}

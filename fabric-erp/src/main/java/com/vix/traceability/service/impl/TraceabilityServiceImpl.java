/**
 * 
 */
package com.vix.traceability.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.traceability.dao.ITraceabilityDao;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 
 * com.traceability.service.impl.TraceabilityServiceImpl
 *
 * @author bjitzhang
 *
 * @date 2015年9月23日
 */
@Service("traceabilityService")
public class TraceabilityServiceImpl extends BaseHibernateServiceImpl implements ITraceabilityService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2386824883563664983L;
	@Autowired
	private ITraceabilityDao traceabilityDao;

	@Override
	public void insertSql(String sql, Map<String, Object> params) throws Exception {
		traceabilityDao.executeSql(sql, params);
	}

	@Override
	public String getTableName(Class<?> clazz) {
		return traceabilityDao.getTableName(clazz);
	}

	@Override
	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {

		//这里最好保证params不是null，到baseDao处理时会有问题
		//到具体处理方法时再new HashMap，则无法将对象回传给父一级的方法(因为当时是null)
		if (params == null)
			params = new HashMap<String, Object>();

		StringBuilder hqlBuilder = this.genHqlHeadBuilder(entityClass, params);

		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" and ");
		}
		buildSearchQl(hqlBuilder, params);

		return traceabilityDao.findAllDataByHql(hqlBuilder.toString(), params);
	}

	/** 根据参数拼接hql */
	private void buildSearchQl(StringBuilder qlBuilder, Map<String, Object> params) {
		int keyCount = params.keySet().size();
		/** 参数里需要移除的参数key */
		StringBuilder needRemoveList = new StringBuilder();
		for (String key : params.keySet()) {
			if (!StrUtils.objectIsNull(key)) {
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0].replace(".", "_"));
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0].replace(".", "_"));
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0].replace(".", "_"));
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0].replace(".", "_"));
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0].replace(".", "_"));
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0].replace(".", "_"));
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
							qlBuilder.append(" (");
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null or hentity.").append(k[0]);
							qlBuilder.append(" = ''");
							qlBuilder.append(") ");
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0]);
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
						}
					}
				} else {
					if (key.contains(".")) {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(k[0].replace(".", "_"));
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
}
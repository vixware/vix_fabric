/**
 * 
 */
package com.process.vreport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.process.vreport.dao.ITaskProcessorDao;
import com.process.vreport.service.ITaskProcessorService;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Transactional
@Service("taskProcessorService")
public class TaskProcessorServiceImpl extends BaseHibernateServiceImpl implements ITaskProcessorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITaskProcessorDao taskProcessorDao;

	@Override
	public <T> List<T> findAllEntityByParams(Class<T> entityClass, Map<String, Object> params) throws Exception {

		return this.findAllByConditions(entityClass, params);
	}

	public <T> List<T> findAllByConditionsByParams(Class<T> entityClass, Map<String, Object> params) throws Exception {
		StringBuilder hqlBuilder = new StringBuilder("from ");
		hqlBuilder.append(entityClass.getName());
		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" hentity where 1=1 ");
			buildSearchQl(hqlBuilder, params);
		}
		return taskProcessorDao.findAllEntityByparams(hqlBuilder.toString(), params);
	}

	private void buildSearchQl(StringBuilder qlBuilder, Map<String, Object> params) {
		int keyCount = params.keySet().size();
		StringBuilder needRemoveList = new StringBuilder();
		// add by wang
		if (keyCount > 0) {
			qlBuilder.append(" and ");
		}
		for (String key : params.keySet()) {
			if (!StrUtils.objectIsNull(key)) {
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0].split("\\.")[0]);
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
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <> :").append(k[0]);
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
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <> :").append(k[0]);// .append(k[0].split("\\.")[0]);
						}
					}
				} else {
					if (key.contains(".")) {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(k[0].split("\\.")[0]);
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

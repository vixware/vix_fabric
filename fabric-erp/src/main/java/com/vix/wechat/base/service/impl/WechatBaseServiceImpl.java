/**
 * 
 */
package com.vix.wechat.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.wechat.base.dao.IWechatBaseDao;
import com.vix.wechat.base.service.IWechatBaseService;

/**
 * 
 * @ClassFullName com.vix.wechat.base.service.impl.WechatBaseServiceImpl
 *
 * @author bjitzhang
 *
 * @date 2016年3月15日
 *
 */
@Service("wechatBaseService")
public class WechatBaseServiceImpl extends BaseHibernateServiceImpl implements IWechatBaseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IWechatBaseDao wechatBaseDao;

	@Override
	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {

		if (params == null)
			params = new HashMap<String, Object>();

		StringBuilder hqlBuilder = this.genHqlHeadBuilder(entityClass, params);

		if (null != params && params.keySet().size() > 0) {
			hqlBuilder.append(" and ");
		}
		buildSearchQl(hqlBuilder, params);
		return wechatBaseDao.findAllDataByHql(hqlBuilder.toString(), params);
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

}
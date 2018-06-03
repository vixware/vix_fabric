package com.vix.oa.personaloffice.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.oa.personaloffice.dao.IPersonalAttendanceDao;

@Repository("personalAttendanceDao")
public class PersonalAttendanceDaoImpl extends BaseHibernateDaoImpl implements IPersonalAttendanceDao {

	private static final long serialVersionUID = 1L;

	@Override
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass,String orderField, String orderBy, int count) throws Exception {
		StringBuilder hqlBuilder = new StringBuilder("from ");
		hqlBuilder.append(entityClass.getName());
		if(StrUtils.objectIsNotNull(orderField) && StrUtils.objectIsNotNull(orderBy)){
			hqlBuilder.append(" hentity order by hentity.");
			hqlBuilder.append(orderField);
			hqlBuilder.append(" ");
			hqlBuilder.append(orderBy);
		}
		Query query = getSession().createQuery(hqlBuilder.toString());
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.list();
	}
	
	@Override
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass, String orderField, String orderBy, int count,String companyCode) throws Exception {
		if(StrUtils.objectIsNull(companyCode)){
			return null;
		}
		StringBuilder hqlBuilder = new StringBuilder("from ");
		hqlBuilder.append(entityClass.getName());
		hqlBuilder.append(" hentity where hentity.companyCode = :companyCode ");
		if(StrUtils.objectIsNotNull(orderField) && StrUtils.objectIsNotNull(orderBy)){
			hqlBuilder.append(" order by hentity.");
			hqlBuilder.append(orderField);
			hqlBuilder.append(" ");
			hqlBuilder.append(orderBy);
		}
		Query query = getSession().createQuery(hqlBuilder.toString());
		query.setParameter("companyCode", companyCode);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.list();
	}

	@Override
	public <T> List<T> findAllTopEntityByHql(String hql, int count) throws Exception {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.list();
	}

	@Override
	public <T> List<T> findAllTopEntityByCountAndConditions(Class<T> entityClass, String orderField, String orderBy, int count,Map<String, Object> params) throws Exception {
		StringBuilder hqlBuilder = new StringBuilder("from ");
		hqlBuilder.append(entityClass.getName());
		hqlBuilder.append(" hentity where 1=1 ");
		if(null != params && params.keySet().size() > 0){
			buildSearchQl(hqlBuilder,params);
		}
		if(StrUtils.objectIsNotNull(orderField) && StrUtils.objectIsNotNull(orderBy)){
			hqlBuilder.append(" order by hentity.");
			hqlBuilder.append(orderField);
			hqlBuilder.append(" ");
			hqlBuilder.append(orderBy);
		}
		Query query = getSession().createQuery(hqlBuilder.toString());
		if(null != params && params.keySet().size() > 0){
			configQuery(query,params);
		}
		
		query.setFirstResult(0);
		
		/** count 小于0 ，则查询1条数据 */
		if(count > 0){
			query.setMaxResults(count);
		}else{
			query.setMaxResults(1);
		}
		return query.list();
	}
	
	private void buildSearchQl(StringBuilder qlBuilder,Map<String,Object> params){
		if(params==null || params.isEmpty())
			return;

		//2014-05-04 如果为系统级管理员admin，去掉tenantId参数
		//2014-05-16 具体逻辑不能这么判断,或者其他承租户不能叫admin
		if(SecurityUtil.getCurrentUserName().equals(BizConstant.USERACCOUNT_SUPERADMIN)){
			if(params!=null)
				params.remove(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY);
		}
		
		Boolean isBreak4CompInnerCode = false;
		if(params.containsKey(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY) ){
			String compInnerCodeStr = (String)params.get(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);
			if(compInnerCodeStr.equals(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY_NO)){
				//params.remove(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);//如果此处从params中删除掉， 则在调用dao时将不能验证是否增加companyInnerCode
				isBreak4CompInnerCode = true;
			}
		}
		
		int keyCount = params.keySet().size();
		if(keyCount>0){
			//配合传入hql中的where 1=1 
			qlBuilder.append(" and ");
		}
		
		/** 参数里需要移除的参数key */
		StringBuilder needRemoveList = new StringBuilder();
		for(String key : params.keySet()){
			if(StrUtils.isNotEmpty(key) && key.equals(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY) && isBreak4CompInnerCode){
				keyCount--;
				continue;
			}
			if(!StrUtils.objectIsNull(key)){
				String[] k = key.split(",");
				if(k.length == 2){
					if(k[0].contains(".")){
						if(k[1].equals(SearchCondition.EQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.NOEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.ANYLIKE) ||
								k[1].equals(SearchCondition.STARTLIKE)||
										k[1].equals(SearchCondition.ENDLIKE)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.MORETHAN)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.MORETHANANDEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.LESSTHAN)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.LESSTHANANDEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(StrUtils.fixParamForHql(k[0]));
						}else if(k[1].equals(SearchCondition.BETWEENAND)){
							Object v = params.get(key);
							if(null != v && !"".equals(v)){
								String[] val = v.toString().split("!");
								if(val.length == 2){
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						}else if(k[1].equals(SearchCondition.IS)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						}else if(k[1].equals(SearchCondition.IN)){
							Object inValue = params.get(key);
							dealHqlInCondition(inValue,needRemoveList,qlBuilder,key);
						}
					}else{
						if(k[1].equals(SearchCondition.EQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0]);
						}else if(k[1].equals(SearchCondition.NOEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(k[0]);
						}else if(k[1].equals(SearchCondition.ANYLIKE) ||
								k[1].equals(SearchCondition.STARTLIKE)||
										k[1].equals(SearchCondition.ENDLIKE)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0]);
						}else if(k[1].equals(SearchCondition.MORETHAN)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0]);
						}else if(k[1].equals(SearchCondition.MORETHANANDEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0]);
						}else if(k[1].equals(SearchCondition.LESSTHAN)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0]);
						}else if(k[1].equals(SearchCondition.LESSTHANANDEQUAL)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0]);
						}else if(k[1].equals(SearchCondition.BETWEENAND)){
							Object v = params.get(key);
							if(null != v && !"".equals(v)){
								String[] val = v.toString().split("!");
								if(val.length == 2){
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						}else if(k[1].equals(SearchCondition.IS)){
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						}else if(k[1].equals(SearchCondition.IN)){
							Object inValue = params.get(key);
							dealHqlInCondition(inValue,needRemoveList,qlBuilder,key);
						}
					}
				}else{
					if(key.contains(".")){
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(StrUtils.fixParamForHql(k[0]));
					}else{
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(key);
					}
				}
				if(keyCount>1){
					qlBuilder.append(" and ");
				}
				keyCount--;
			}
		}
		for(String k : needRemoveList.toString().split(",")){
			if(null != k && !"".equals(k)){
				params.remove(k);
			}
		}
	}
	
	private void dealHqlInCondition(Object inValue,StringBuilder needRemoveList,StringBuilder qlBuilder,String key){
		String[] k = key.split(",");
		if(null != inValue && !"".equals(inValue)){
			needRemoveList.append(key).append(",");
			qlBuilder.append("hentity.").append(k[0]);
			qlBuilder.append(" in (");
			String [] ivArray = inValue.toString().split(",");
			for(int i =0;i< ivArray.length;i++){
				String iv = ivArray[i];
				if(null != iv && !"".equals(iv)){
					qlBuilder.append("'");
					qlBuilder.append(iv);
					qlBuilder.append("'");
					if(i<ivArray.length-1){
						qlBuilder.append(",");
					}
				}
			}
			qlBuilder.append(") ");
		}
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

	@Override
	public PunchCardRecord findFirstByConditions(Class<PunchCardRecord> entityClass, String orderField,String orderBy, Map<String, Object> params) throws Exception {
		StringBuilder hqlBuilder = new StringBuilder("from ");
		hqlBuilder.append(entityClass.getName());
		hqlBuilder.append(" hentity where 1=1 ");
		if(null != params && params.keySet().size() > 0){
			buildSearchQl(hqlBuilder,params);
		}
		if(StrUtils.objectIsNotNull(orderField) && StrUtils.objectIsNotNull(orderBy)){
			hqlBuilder.append(" order by hentity.");
			hqlBuilder.append(orderField);
			hqlBuilder.append(" ");
			hqlBuilder.append(orderBy);
		}
		Query query = getSession().createQuery(hqlBuilder.toString());
		if(null != params && params.keySet().size() > 0){
			configQuery(query,params);
		}
		
		query.setFirstResult(0);
		query.setMaxResults(1);
		
		List<PunchCardRecord> list = query.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


}

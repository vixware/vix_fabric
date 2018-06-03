package com.vix.common.orginialMeta.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.orginialMeta.dao.IOrginialVarDao;
import com.vix.common.orginialMeta.entity.OrginialVar;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Repository("orginialVarDao")
public class OrginialVarDaoImpl extends BaseHibernateDaoImpl implements IOrginialVarDao {

	@Override
	public Pager findOrginialVarPager(Pager pager, Map<String, Object> params)throws Exception{
		StringBuilder hql = new StringBuilder();
		String ename = "orginialVar";
		hql.append("select ").append(ename).append(" from ").append(OrginialVar.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		if(params!=null){
			if(params.containsKey("varCode")){
				hql.append(" and ").append(ename).append(".varCode like :varCode ");
			}
		}
    	
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		return findPager2ByHql(pager, ename, hql.toString(), params);
	}

}

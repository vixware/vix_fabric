package com.vix.system.systemVar.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;
import com.vix.system.systemVar.dao.ISystemVarDao;
import com.vix.system.systemVar.entity.SystemVar;

@Repository("systemVarDao")
public class SystemVarDaoImpl extends BaseHibernateDaoImpl implements ISystemVarDao {

	@Override
	public Pager findSystemVarPager(Pager pager, Map<String, Object> params)throws Exception{
		StringBuilder hql = new StringBuilder();
		String ename = "systemVar";
		hql.append("select ").append(ename).append(" from ").append(SystemVar.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		if(params!=null){
			if(params.containsKey("varCode")){
				hql.append(" and ").append(ename).append(".varCode like :varCode ");
			}
		}
    	
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		return findPager2ByHql(pager, ename, hql.toString(), params);
	}
	
	/**
	 * @Title: findSystemVarDisplayViewByTenant
	 * @Description: 查询某承租户  或者超级管理员的  系统变量值（如果tenantId 为null 则查询的是超级管理员的数据）
	 * @param @param tenantId
	 * @param @return    设定文件
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	@Override
	public Map<String,String> findSystemVarDisplayViewByTenant(String tenantId){
		Map<String,String> resMap = new ConcurrentHashMap<String, String>();
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		if(StringUtils.isEmpty(tenantId)){
			//超级管理员
			sql.append("select ov.VARCODE, ov.DEFAULTVALUE from CMN_MET_ORGINIAL_VAR ov  ");
		}else{
			sql.append("select sv.VARCODE, sv.DEFAULTVALUE from SYS_VAR sv  where sv.tenantId = ? ");
			params.add(tenantId);
		}
		
		List<SystemVar> varList = queryObjectListBySql(sql.toString(), SystemVar.class, params.toArray());
		
		if(varList!=null && !varList.isEmpty()){
			for(SystemVar sv:varList){
				resMap.put(sv.getVarCode(), sv.getDefaultValue());
			}
		}
		return resMap;
	}

}

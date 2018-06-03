package com.vix.common.orginialMeta.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.orginialMeta.dao.IOrginialBillsCategoryDao;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Repository("orginialBillsCategoryDao")
public class OrginialBillsCategoryDaoImpl extends BaseHibernateDaoImpl implements IOrginialBillsCategoryDao {

	private static final long serialVersionUID = 1L;

	/* 
	 * @see com.vix.common.orginialMeta.dao.IOrginialBillsCategoryDao#findOrginialBillsCategoryPager(com.vix.core.web.Pager, java.util.Map)
	 */ 
	@Override
	public Pager findOrginialBillsCategoryPager(Pager pager, Map<String, Object> params)throws Exception {
		StringBuilder hql = new StringBuilder();
		//hql.append("select r from Resource r left join r.authority au where au is null ");
		String ename = "oc";
		hql.append("select ").append(ename).append(" from ").append(OrginialBillsCategory.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		if(params!=null){
			if(params.containsKey("categoryName")){
				hql.append(" and ").append(ename).append(".categoryName like :categoryName ");
			}
		}
    	
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		//return resourceDao.findPagerByHql(pager, hql.toString(), params);
		//return findPager2ByHql(pager, ename, hql.toString(), params);
		return findPagerOrginialByHql(pager, ename, hql.toString(), params);
	}
	
	/* 
	 * @see com.vix.common.orginialMeta.dao.IOrginialBillsCategoryDao#findAllOrginialBillsCategoryList()
	 */ 
	@Override
	public List<OrginialBillsCategory> findAllOrginialBillsCategoryList()throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		String ename = "oc";
		hql.append("select ").append(ename).append(" from ").append(OrginialBillsCategory.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		hql.append(" order by ").append(ename).append(".id ");
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		return findAllByHql2(hql.toString(), params);
	}
}

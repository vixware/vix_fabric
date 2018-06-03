package com.vix.common.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.security.dao.IConfigUrlDao;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Repository("configUrlDao")
public class ConfigUrlDaoImpl extends BaseHibernateDaoImpl implements IConfigUrlDao {

	private static final long serialVersionUID = 1L;

	@Override
	public Pager findConfigUrlAddPager(Pager pager, Map<String, Object> params)throws Exception {
		StringBuilder hql = new StringBuilder();
		//hql.append("select r from Resource r left join r.authority au where au is null ");
		String ename = "configUrl";
		hql.append("select ").append(ename).append(" from ").append(ConfigUrlAdd.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		if(params!=null){
			if(params.containsKey("name")){
				hql.append(" and ").append(ename).append(".name like :name ");
			}
		}
    	
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		//return resourceDao.findPagerByHql(pager, hql.toString(), params);
		return findPager2ByHql(pager, ename, hql.toString(), params);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.dao.IModuleDao#findAllModuleList()
	 */
	@Override
	public List<ConfigUrlAdd> findAllConfigUrlAddList()throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		String ename = "configUrl";
		hql.append("select ").append(ename).append(" from ").append(ConfigUrlAdd.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		hql.append(" order by ").append(ename).append(".moduleCode ");
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		return findAllByHql2(hql.toString(), params);
	}

}

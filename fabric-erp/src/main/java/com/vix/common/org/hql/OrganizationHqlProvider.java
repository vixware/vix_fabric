package com.vix.common.org.hql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class OrganizationHqlProvider extends HqlProvider {

    @Override
    public String entityAsName() {
        return "organization";
    }

    public StringBuilder findOrgPager(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Organization ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subOrganizations ");
		hql.append(" left join fetch ").append(ename).append(".organizationUnits ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgId")){
				Object orgId = params.get("orgId");
				if(orgId==null){
					hql.append(" and ").append(ename).append(".parentOrganization.id is null");
					params.remove("orgId");
				}else{
					hql.append(" and ").append(ename).append(".parentOrganization.id = :orgId ");
				}
				
			}else if(params.containsKey("orgName")){
				hql.append(" and ").append(ename).append(".orgName like :orgName ");
			}
		}
		
		
		if(pager!=null && StringUtils.isEmpty(pager.getOrderField())){
			hql.append(" order by ").append(ename).append(".createTime desc ");
		}else{
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}
	
    
    
    public StringBuilder findOrgList(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Organization ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subOrganizations ");
		hql.append(" left join fetch ").append(ename).append(".organizationUnits ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgId")){
				Object orgId = params.get("orgId");
				if(orgId==null){
					hql.append(" and ").append(ename).append(".parentOrganization.id is null");
					params.remove("orgId");
				}else{
					hql.append(" and ").append(ename).append(".parentOrganization.id = :orgId ");
				}
				
			}
			if(params.containsKey("companyCode")){
				hql.append(" and ").append(ename).append(".companyCode = :companyCode");
			}
		}
		
		buildOrderQl(ename, hql, "id", "asc");
		return hql;
	}
    
    /**
     * @Title: findOrgList
     * @Description: 得到公司  并且抓取子公司和子组织
     * @param @param params
     * @param @param orderField
     * @param @param orderBy
     * @param @param extHql
     * @param @return    设定文件
     * @return StringBuilder    返回类型
     * @throws
     
    public StringBuilder findOrgList(Map<String,Object> params,String orderField,String orderBy,String extHql){
        String entityAsName = entityAsName();
        //StringBuilder hqlBuilder =new StringBuilder();
        StringBuilder joinHql =new StringBuilder();
        joinHql.append(" left join fetch ").append(entityAsName).append(".subOrganizations ");
        joinHql.append(" left join fetch ").append(entityAsName).append(".organizationUnits ");
        
        StringBuilder hql = generatorHql(Organization.class, params, orderField, orderBy,extHql,joinHql.toString());
        return hql;
    }*/
    

}

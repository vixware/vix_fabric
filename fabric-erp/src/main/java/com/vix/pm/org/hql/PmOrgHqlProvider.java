package com.vix.pm.org.hql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.org.entity.OrgView;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
@Scope("prototype")
public class PmOrgHqlProvider extends HqlProvider {

    @Override
    public String entityAsName() {
        return "pmOrg";
    }

    public StringBuilder findBusinessOrgList4Page(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from PmOrg ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBusinessOrgs ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if (params.containsKey("bizOrgViewId") || params.containsKey("bizOrgId")) {
				//Object orgId = params.get("orgId");
				//Object orgUnitId = params.get("orgUnitId");

				if (params.containsKey("bizOrgViewId")) {
					hql.append(" and ").append(ename).append(".pmView.id = :bizOrgViewId and ").append(ename).append(".parentBusinessOrg is null ");
				}
				if (params.containsKey("bizOrgId")) {
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id = :bizOrgId ");
				}
			} else if (params.containsKey("businessName")) {
				hql.append(" and ").append(ename).append(".name like :businessName ");
			}
		}else{
			
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
    
    
    public StringBuilder findBusinessOrgList(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from PmOrg ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBusinessOrgs ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("businessId")){
				Object orgId = params.get("businessId");
				if(orgId==null){
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id is null");
					params.remove("businessId");
				}else{
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id = :businessId ");
				}
				
			}else if(params.containsKey("businessOrgName")){
				hql.append(" and ").append(ename).append(".businessOrgName like :businessOrgName ");
			}
		}else{
			
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
    
    
    
    public StringBuilder findBusinessOrgViewList(Map<String,Object> params){
        String entityAsName = entityAsName();
        //StringBuilder hqlBuilder =new StringBuilder();
        StringBuilder hqlBuilder =new StringBuilder();
        hqlBuilder.append(" from ");
        
        hqlBuilder.append(OrgView.class.getName()).append(" ").append(entityAsName);
        hqlBuilder.append(" where ");
        String parentId = (String) params.get("parentId");
        if(StringUtils.isNotEmpty(parentId)){
        	hqlBuilder.append(entityAsName).append(".parentId = :parentId");
        }else{
        	hqlBuilder.append(entityAsName).append(".parentId is null ");
        	params.remove("parentId");
        }
        return hqlBuilder;
    }
    
   
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

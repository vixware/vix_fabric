package com.vix.common.org.hql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vix.common.org.entity.BusinessOrgEmpView;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

//@Component
//@Scope("prototype")
public class BusinessViewHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "businessView";
    }

    
    public StringBuilder findBusinessViewList(Map<String,Object> params,Pager pager){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(BusinessView.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	   
 	   if(params!=null){
 		   if(params.containsKey("name")){
 			   sb.append("  and  ").append(ename).append(".name like :name ");
 		   }
 		   
 		   if(params.containsKey("companyId")){
 			  sb.append("  and  ").append(ename).append(".organization.id = :companyId ");
 		   }
 	   }
 	   
 	   buildOrderQl(ename, sb, pager);
 	   return sb;
    }
    
    
    /**
     * 业务组织视图和业务组织的视图
     * @param params
     * @return
     */
    public StringBuilder findBusinessOrgViewList(Map<String,Object> params){
        String entityAsName = entityAsName();
        //StringBuilder hqlBuilder =new StringBuilder();
        StringBuilder hqlBuilder =new StringBuilder();
        hqlBuilder.append(" from ");
        
        hqlBuilder.append(BusinessOrgView.class.getName()).append(" ").append(entityAsName);
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
    public StringBuilder findBusinessOrgEmpViewList(Map<String,Object> params){
    	String entityAsName = entityAsName();
    	//StringBuilder hqlBuilder =new StringBuilder();
    	StringBuilder hqlBuilder =new StringBuilder();
    	hqlBuilder.append(" from ");
    	
    	hqlBuilder.append(BusinessOrgEmpView.class.getName()).append(" ").append(entityAsName);
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
    
}

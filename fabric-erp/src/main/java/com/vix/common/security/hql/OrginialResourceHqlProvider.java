package com.vix.common.security.hql;

import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialResource;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

public class OrginialResourceHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "orginialResource";
    }

    /**
     * 分页查询资源列表
     * @param params
     * @param pager
     * @return
     */
    public StringBuilder findOrginialResourcePagerList(Map<String,Object> params,Pager pager){
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(OrginialResource.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	   if(params!=null){
 		   if(params.containsKey("searchResourceName")){
 			   sb.append("  and  ").append(ename).append(".displayName like :searchResourceName ");
 		   }
 	   }
 	   
 	   buildOrderQl(ename, sb, pager);
 	   return sb;
    }
    
}

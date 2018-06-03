package com.vix.common.security.hql;

import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

public class OrginialAuthorityHqlProvider extends HqlProvider {

    @Override
    public String entityAsName() {
        return "orginialAuthority";
    }

    /**
     * 分页查询资源列表
     * @param params
     * @param pager
     * @return
     */
    public StringBuilder findOrginialAuthorityPagerList(Map<String,Object> params,Pager pager){
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(OrginialAuthority.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	   if(params!=null){
 		   if(params.containsKey("name")){
 			   sb.append("  and  ").append(ename).append(".name like :name ");
 		   }
 		   if(params.containsKey("parentAuthorityCode")){
 			   Object parentAuthorityCode = params.get("parentAuthorityCode");
 			   if(parentAuthorityCode!=null){
 				  sb.append("  and  ").append(ename).append(".parentAuthorityCode = :parentAuthorityCode ");
 			   }else{
 				   sb.append("  and  ").append(ename).append(".parentAuthorityCode is null ");
 				   params.remove("parentAuthorityCode");
 			   }
			   
		   }
 		   if(params.containsKey("bizType")){
 			   sb.append("  and  ").append(ename).append(".bizType = :bizType ");
 		   }
 	   }
 	   
 	   buildOrderQl(ename, sb, pager);
 	   return sb;
    }
    
    
}

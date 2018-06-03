package com.vix.common.meta.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class BaseMetaDataCategoryHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "baseMetaDataCategory";
    }

    
    public StringBuilder findBaseMetaDataCategoryList(Map<String,Object> params,Pager pager){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(BaseMetaDataCategory.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	   
 	   if(params!=null){
 		   if(params.containsKey("categoryName")){
 			   sb.append("  and  ").append(ename).append(".categoryName like :categoryName ");
 		   }
 	   }
 	   
 	   buildOrderQl(ename, sb, pager);
 	   return sb;
    }
    
}

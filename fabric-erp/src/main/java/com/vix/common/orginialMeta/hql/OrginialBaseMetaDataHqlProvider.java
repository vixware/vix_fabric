package com.vix.common.orginialMeta.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class OrginialBaseMetaDataHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "orginialBaseMetaData";
    }

    
    public StringBuilder findBaseMetaDataList(Map<String,Object> params,Pager pager){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(OrginialBaseMetaData.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	  if(params.containsKey("categoryId")){
		   sb.append("  and  ").append(ename).append(".baseMetaDataCategory.id =:categoryId ");
	   }
	   if(params.containsKey("metaDataName")){
		   sb.append("  and  ").append(ename).append(".metaDataDisplayName like :metaDataName ");
	   }
 	   
 	   buildOrderQl(ename, sb, pager);
 	   return sb;
    }
    
}

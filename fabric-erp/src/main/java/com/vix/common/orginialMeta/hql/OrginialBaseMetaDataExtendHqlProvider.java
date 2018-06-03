package com.vix.common.orginialMeta.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataExtend;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
//@Scope("prototype")
public class OrginialBaseMetaDataExtendHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "orginialBaseMetaDataExtend";
    }

    
    public StringBuilder findBaseMetaDataCategoryList(Map<String,Object> params ){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(OrginialBaseMetaDataExtend.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
 	   sb.append("and ").append(ename).append(".baseMetaData.id = :metaDataId ");
 	   
 	   if(params!=null){
 		   if(params.containsKey("metaDataName")){
 			   sb.append("  and  ").append(ename).append(".baseMetaData.metaDataName like :metaDataName ");
 		   }
 	   }
 	   
 	  buildOrderQl(ename, sb, "id", "asc");
 	   return sb;
    }
    
    public StringBuilder findBaseMetaDataByMetaDataNameList(Map<String,Object> params ){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(OrginialBaseMetaDataExtend.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
	   //if(params.containsKey("propertyName")){
 	   sb.append("  and  ").append(ename).append(".baseMetaData.metaDataName = :metaDataName ");
	   //}
 	   
 	  buildOrderQl(ename, sb, "id", "asc");
 	   return sb;
    }
    
}

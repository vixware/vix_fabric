package com.vix.common.meta.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
//@Scope("prototype")
public class BaseMetaDataDefineHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "baseMetaDataDefine";
    }

    
    public StringBuilder findBaseMetaDataCategoryList(Map<String,Object> params ){
        /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
        return hql;*/
 	   StringBuilder sb = new StringBuilder();
 	   String ename = entityAsName();
 	   sb.append("select ").append(ename);
 	   sb.append(" from ").append(BaseMetaDataDefine.class.getName()).append(" ").append(ename);
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
 	   sb.append(" from ").append(BaseMetaDataDefine.class.getName()).append(" ").append(ename);
 	   sb.append(" where 1=1 ");
 	   
	   //if(params.containsKey("propertyName")){
 	   sb.append("  and  ").append(ename).append(".baseMetaData.metaDataName = :metaDataName ");
	   //}
 	   
 	  buildOrderQl(ename, sb, "id", "asc");
 	   return sb;
    }
    
}

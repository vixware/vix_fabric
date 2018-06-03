package com.vix.common.securityDra.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
//@Scope("prototype")
public class DataResRowPolicyObjHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "dataResRowPolicyObj";
	}

	public StringBuilder findPolicyObjList(Map<String,Object> params,String orderField,String orderBy){
		StringBuilder sb = new StringBuilder();
		String ename = entityAsName();
		sb.append("select ").append(ename);
		sb.append(" from ").append(DataResRowPolicyObj.class.getName()).append(" ").append(ename);
	 	  // sb.append(" left join fetch ").append(ename).append(".roles role");
	 	  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
	 	  // sb.append(" left join fetch role.authoritys au");
	 	  // sb.append(" left join fetch au.resources ");
		sb.append(" where 1=1 ");
 	   
		if(params!=null){
			if(params.containsKey("metaDataViewName")){
 			   sb.append("  and  ").append(ename).append(".metaDataViewName like :metaDataViewName ");
			}
		}
 	   
 	   //buildOrderQl(ename, sb, pager);
 	   buildOrderQl(ename, sb, orderField, orderBy);
 	   return sb;
		
	}
}

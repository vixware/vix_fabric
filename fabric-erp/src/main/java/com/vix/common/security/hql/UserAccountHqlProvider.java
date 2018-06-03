package com.vix.common.security.hql;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

/**
 * @ClassName: UserAccountHqlProvider
 * @Description: 用户帐号的hql查询
 * @author wangmingchen
 * @date 2013-5-7 下午9:04:23
 *
 */
@Component
//@Scope("prototype")
public class UserAccountHqlProvider extends HqlProvider {

    @Override
    public String entityAsName() {
        return "userAccount";
    }
    
   public StringBuilder findUserList(Map<String,Object> params,Pager pager){
       /*StringBuilder hql = generatorHql(UserAccount.class, params, orderField, orderBy,extHql,"");
       return hql;*/
	   StringBuilder sb = new StringBuilder();
	   String ename = entityAsName();
	   sb.append("select ").append(ename);
		
	   sb.append(" from ").append(UserAccount.class.getName()).append(" ").append(ename);
	   sb.append(" left join fetch ").append(ename).append(".roles role");
	   // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
	   /*
	   sb.append(" left join fetch role.authoritys au");
	   sb.append(" left join fetch au.resources ");
	    */
	   sb.append(" left join fetch ").append(ename).append(".employee emp ");
	   sb.append(" left join fetch emp.organizationUnit unit ");
	   sb.append(" left join fetch unit.organization  ");
	   sb.append(" where 1=1 ");
	   //sb.append(" and ").append(ename).append(".id > 0 ");
	   
	   if(params!=null){
		   if(params.containsKey("account")){
			   sb.append("  and  ").append(ename).append(".account like :account ");
		   }
	   }
	   
	   buildOrderQl(ename, sb, pager);
	   return sb;
   }
   
   public StringBuilder findUserByAccount(String userName){
	   StringBuilder sb = new StringBuilder();
	   sb.append(" from ").append(UserAccount.class.getName()).append(" userAccount");
	   sb.append(" left join fetch userAccount.roles role");
	  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
	   sb.append(" left join fetch role.authoritys au");
	   sb.append(" left join fetch au.resources ");
	   sb.append(" where userAccount.account=:account ");
	   return sb;
	   
   }
   public static void main(String[] args){
      /* UserAccountHqlProvider ca = new UserAccountHqlProvider();
       Map map =new HashedMap();
       map.put("account,"+SearchCondition.ANYLIKE,"pangzi");
       map.put("pwd,"+SearchCondition.EQUAL,"pwd2");
       String hql = ca.generatorHql(UserAccount.class, map, null, null,null).toString();
       System.out.println(hql);*/
       
       
       UserAccountHqlProvider ca = new UserAccountHqlProvider();
       Map map =new HashedMap();
       map.put("account,"+SearchCondition.ANYLIKE,"pangzi");
       //map.put("pwd,"+SearchCondition.EQUAL,"pwd2");
       //map.put("status,"+SearchCondition.NOEQUAL, 2);
       String hql = ca.generatorHql(UserAccount.class, map, null, null,null,null).toString();
       System.out.println(hql);
       //String orderBy
   }

}

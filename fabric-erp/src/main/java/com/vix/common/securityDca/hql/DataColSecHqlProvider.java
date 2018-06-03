package com.vix.common.securityDca.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

/**
 * DataResColPolicy的查询
 * @author Administrator
 *
 */
@Component
//@Scope("prototype")
public class DataColSecHqlProvider extends HqlProvider {

    //@Resource(name = "userAccountHqlProvider")
    //private UserAccountHqlProvider userAccountHqlProvider;

    @Override
    public String entityAsName() {
        return "baseMetaData";
    }

    /**
     * @Title: findSelectRoleForUser
     * @Description: 查询元数据信息
     * @param @param params
     * @param @param orderField
     * @param @param orderBy
     * @param @param extHql
     * @param @return 设定文件
     * @return StringBuilder 返回类型
     * @throws
    */
    public StringBuilder findBaseMetaData(Map<String, Object> params, String orderField,String orderBy) {
        //String entityAsName = entityAsName();
        //StringBuilder hqlBuilder =new StringBuilder();
        StringBuilder joinHql =new StringBuilder();
        StringBuilder extHql =new StringBuilder();
        StringBuilder queryHql = generatorHql(BaseMetaData.class, params, orderField, orderBy, extHql.toString(), joinHql.toString() );
        //如果需要自定义参数 必须generatorHql 后面添加
        //params.put("userId,"+SearchCondition.NOEQUAL, userId);
    	return queryHql;
    } 

    public static void main(String[] args) {
        /*
          UserAccountHqlProvider ca = new UserAccountHqlProvider(); Map map
         * =new HashedMap();
         * map.put("account,"+SearchCondition.ANYLIKE,"pangzi");
         * map.put("pwd,"+SearchCondition.EQUAL,"pwd2"); String hql =
         * ca.generatorHql(UserAccount.class, map, null, null,null).toString();
         * System.out.println(hql);
         */

        // String orderBy
    }
}

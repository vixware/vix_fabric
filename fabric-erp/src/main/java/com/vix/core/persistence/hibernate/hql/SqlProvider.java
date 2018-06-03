/**   
* @Title: HqlProvider.java
* @Package com.vix.core.persistence.hibernate.hql
* @Description: TODO(用一句话描述该文件做什么)
* @author wangmingchen
* @date 2012-6-19 上午9:30:10
* @version V1.0   
*/
package com.vix.core.persistence.hibernate.hql;

import com.vix.core.persistence.hibernate.util.HqlUtil;
import com.vix.core.web.Pager;

/**
 * @ClassName: HqlProvider
 * @Description: Sql的提供者
 * @author wangmingchen
 * @date 2012-6-19 上午9:30:10
 * 
 */
public abstract class SqlProvider {

    /**
     * @Title: entityAsName
     * @Description: 当查询主体为当前对应的对象时，则使用此方法值作为改实体的别名
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public abstract String tableAsName();
    
    protected StringBuilder buildOrderQl(String tableAsName,StringBuilder sqlBuilder,String orderField,String orderBy){
       return HqlUtil.buildOrderQl(tableAsName, sqlBuilder, orderField,orderBy);
    }
    
    protected StringBuilder buildOrderQl(String tableAsName,StringBuilder sqlBuilder,Pager pager){
    	return HqlUtil.buildOrderQl(tableAsName, sqlBuilder, pager);
    }
}

/**   
* @Title: HqlProvider.java
* @Package com.vix.core.persistence.hibernate.hql
* @Description: TODO(用一句话描述该文件做什么)
* @author wangmingchen
* @date 2012-6-19 上午9:30:10
* @version V1.0   
*/
package com.vix.core.persistence.hibernate.hql;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * @ClassName: HqlProvider
 * @Description: hql的提供者
 * @author wangmingchen
 * @date 2012-6-19 上午9:30:10
 * 
 */
public abstract class HqlProvider {

    /**
     * @Title: entityAsName
     * @Description: 当查询主体为当前对应的对象时，则使用此方法值作为改实体的别名
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public abstract String entityAsName();
    
    /**
     * @Title: generatorHql
     * @Description: 基本的实现 
     * 不允许后面进行hql拦截的时候拦截此方法,子类的方法体中在调用此方法时，必须在其方法体中去对String joinHql进行赋值，然后调用，不允许定义为其方法的参数
     * @param @param entityClass 查询的主对象
     * @param @param params 主对象参数
     * @param @param orderField 主对象排序属性
     * @param @param orderBy 
     * @param @param extHql where条件最后追加的条件（需要以 and 开头）
     * @param @param joinHql 在主对象from 后来 添加的 表连接和查询hql 如from User u inner join u.b  ub whith u.id=ub.id,其中从inner join 开始到最后就是这个joinHql  
     * @param @return    设定文件
     * @return StringBuilder    返回类型
     * @throws
     */
    protected final <T> StringBuilder generatorHql(Class<T> entityClass,Map<String,Object> params,String orderField,String orderBy,String extHql,String joinHql){
        //userService
        String entityAsName = entityAsName();
        //StringBuilder hqlBuilder = new StringBuilder("select hentity from ");
        
        //StringBuilder hqlBuilder = new StringBuilder("select ");
        //hqlBuilder.append(entityAsName).append(" from ");
       
        //StringBuilder hqlBuilder = new StringBuilder(" from ");
        //hqlBuilder.append(entityClass.getName()).append(" ").append(entityAsName);
       
        StringBuilder hqlBuilder = buildFromQl(new StringBuilder(), entityClass, entityAsName);
        if(StringUtils.isNotEmpty(joinHql)){
            hqlBuilder.append(" ").append(joinHql);
        }
        hqlBuilder.append(" where 1=1 ");
        
        if(null != params && params.keySet().size() >0){
            hqlBuilder.append(" and ");
            buildSearchQl(entityAsName,hqlBuilder,params);
        }
        if(StringUtils.isNotEmpty(extHql)){
            hqlBuilder.append(" ").append(extHql).append(" ");
        }
        //设定排序
        buildOrderQl(entityAsName, hqlBuilder, orderField, orderBy);
        return hqlBuilder;
    }

    protected <T> StringBuilder buildFromQl(StringBuilder hqlBuilder,Class<T> entityClass,String entityAsName){
    	hqlBuilder.append(" select ").append(entityAsName);
        hqlBuilder.append(" from ");
        hqlBuilder.append(entityClass.getName()).append(" ").append(entityAsName);
        return hqlBuilder;
    }
    
    protected StringBuilder buildSearchQl(String entityAsName,StringBuilder qlBuilder,Map<String,Object> params){
        Map<String,Object> params_=new HashMap<String, Object>(params);
        
        int keyCount = params_.keySet().size();
        for(String key : params_.keySet()){
            if(!StrUtils.objectIsNull(key)){
                String[] k = key.split(",");
                //System.out.println(key);
                if(k.length == 2){
                    if(k[0].contains(".")){
                        String KeyBak = k[0];
                        if(k[1].equals(SearchCondition.EQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            //qlBuilder.append(" = :").append(k[0].split("\\.")[0]);
                            qlBuilder.append(" = :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.ANYLIKE) ||
                                k[1].equals(SearchCondition.STARTLIKE)||
                                        k[1].equals(SearchCondition.ENDLIKE)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" like :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.MORETHAN)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" > :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.MORETHANANDEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" >= :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.LESSTHAN)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" < :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.LESSTHANANDEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" <= :");//.append(k[0].split("\\.")[0]);
                        }else if(k[1].equals(SearchCondition.IS)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" is null");
                        }else if(k[1].equals(SearchCondition.NOEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" <> :");//.append(k[0].split("\\.")[0]);
                        }
                        String paramKey=k[0].replace(".", "_");
                        //System.out.println(KeyBak+"--"+paramKey);
                        qlBuilder.append(paramKey);
                        
                        params.remove(key);
                        params.put(paramKey, params_.get(key));
                        //params.
                    }else{
                        if(k[1].equals(SearchCondition.EQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" = :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE)|| k[1].equals(SearchCondition.ENDLIKE)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" like :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.MORETHAN)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" > :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.MORETHANANDEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" >= :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.LESSTHAN)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" < :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.LESSTHANANDEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" <= :").append(k[0]);
                        }else if(k[1].equals(SearchCondition.IS)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" is null");
                        }else if(k[1].equals(SearchCondition.NOEQUAL)){
                            qlBuilder.append(entityAsName).append(".").append(k[0]);
                            qlBuilder.append(" <> :").append(k[0]);
                        }
                    }
                }else{
                    if(key.contains(".")){
                        qlBuilder.append(entityAsName).append(".").append(key);
                        qlBuilder.append(" like :").append(k[0].split("\\.")[0]);
                    }else{
                        qlBuilder.append(entityAsName).append(".").append(key);
                        qlBuilder.append(" like :").append(key);
                    }
                }
                if(keyCount>1){
                    qlBuilder.append(" and ");
                }
                keyCount--;
            }
        }
        return qlBuilder;
    }
    
    protected StringBuilder buildOrderQl(String entityAsName,StringBuilder hqlBuilder,String orderField,String orderBy){
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(orderBy)){
            hqlBuilder.append(" order by ");
            hqlBuilder.append(entityAsName).append(".").append(orderField);
            hqlBuilder.append(" ");
            hqlBuilder.append(orderBy);
        }
        return hqlBuilder;
    }
    
    protected StringBuilder buildOrderQl(String entityAsName,StringBuilder hqlBuilder,Pager pager){
    	if(pager!=null){
    		String orderField = pager.getOrderField();
        	String orderBy = pager.getOrderBy();
            if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(orderBy)){
                hqlBuilder.append(" order by ");
                hqlBuilder.append(entityAsName).append(".").append(orderField);
                hqlBuilder.append(" ");
                hqlBuilder.append(orderBy);
            }
    	}
        return hqlBuilder;
    }
}

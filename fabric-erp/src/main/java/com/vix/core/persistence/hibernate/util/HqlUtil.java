package com.vix.core.persistence.hibernate.util;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import com.vix.core.web.Pager;

public class HqlUtil {

	public static StringBuilder buildOrderQl(String entityAsName,StringBuilder hqlBuilder,String orderField,String orderBy){
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(orderBy)){
            hqlBuilder.append(" order by ");
            hqlBuilder.append(entityAsName).append(".").append(orderField);
            hqlBuilder.append(" ");
            hqlBuilder.append(orderBy);
        }
        return hqlBuilder;
    }
    
	public static StringBuilder buildOrderQl(String entityAsName,StringBuilder hqlBuilder,Pager pager){
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
	
	/**
	 * @Title: hasPropertyInHbmXml
	 * @Description: 实体配置中是否包含某个属性的配置
	 * @param @param meta
	 * @param @param property
	 * @param @return    设定文件
	 * @return Boolean    返回类型
	 * @throws
	 */
	public static Boolean hasPropertyInHbmXml(ClassMetadata meta,String property){
		Boolean hasPropertyConfig = false;
		try {
			Type type = meta.getPropertyType(property);
			if(type!=null){
				hasPropertyConfig = true;
			}
		} catch (Exception e) {
			
		}
		return hasPropertyConfig;
	}
}

package com.vix.common.securityDra.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.security.entity.DataResRowMethod;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class DataResRowMethodHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "dataResRowMethod";
	}

	/**
	 * 分页查询
	 * @param params
	 * @param pager
	 * @return
	 */
	public StringBuilder findDataResRowMethodList(Map<String,Object> params,Pager pager){
		StringBuilder sb = new StringBuilder();
		String ename = entityAsName();
		sb.append("select ").append(ename);
		sb.append(" from ").append(DataResRowMethod.class.getName()).append(" ").append(ename);
		sb.append(" where 1=1 ");
   
		if(params!=null){
			if(params.containsKey("hqlProvider")){
				sb.append("  and  ").append(ename).append(".hqlProvider like :hqlProvider ");
			}
		}
		buildOrderQl(ename, sb, pager);
		return sb;
	}
	
	/**
	 * 选择使用
	 * @param params
	 * @param pager
	 * @return
	 */
	public StringBuilder findSelectDataResRowMethodList(Map<String,Object> params,Pager pager){
		StringBuilder sb = new StringBuilder();
		String ename = entityAsName();
		sb.append("select ").append(ename);
		sb.append(" from ").append(DataResRowMethod.class.getName()).append(" ").append(ename);
		sb.append(" where 1=1 ");
		
		if(params!=null){
			if(params.containsKey("hqlProvider")){
				sb.append("  and  ").append(ename).append(".hqlProvider like :hqlProvider ");
			}
		}
		buildOrderQl(ename, sb, pager);
		return sb;
	}
}

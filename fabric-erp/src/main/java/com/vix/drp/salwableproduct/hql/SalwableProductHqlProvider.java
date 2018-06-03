package com.vix.drp.salwableproduct.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

/**
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-17
 */
@Component
@Scope("prototype")
public class SalwableProductHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "item";
	}

	public StringBuilder findItemList(Map<String, Object> params, Pager pager) {
		StringBuilder sb = new StringBuilder();
		String ename = entityAsName();
		sb.append("select ").append(ename);

		sb.append(" from ").append(UserAccount.class.getName()).append(" ").append(ename);
		sb.append(" left join fetch ").append(ename).append(".roles role");
		sb.append(" left join fetch role.authoritys au");
		sb.append(" left join fetch au.resources ");
		sb.append(" where 1=1 ");
		//sb.append(" and ").append(ename).append(".id > 0 ");

		if (params != null) {
			if (params.containsKey("account")) {
				sb.append("  and  ").append(ename).append(".account like :account ");
			}
		}

		buildOrderQl(ename, sb, pager);
		return sb;
	}

}

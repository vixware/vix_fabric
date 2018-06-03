package com.vix.drp.order.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class DistributorOrderHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findBeforeSalesOrder(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from SalesOrder ").append(ename);
		hql.append(" where 1=1 and isTemp != 1 ");
		if (params != null) {
			if (params.containsKey("id")) {
				Long id = (Long) params.get("id");
				if (id == null) {
					params.remove("id");
				} else {
					hql.append(" and ").append(ename).append(".id < " + id);
				}
			}
		} else {
			
		}
		hql.append(" ORDER BY ").append(ename).append(".id DESC");
		return hql;
	}

	public StringBuilder findAfterSalesOrder(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from SalesOrder ").append(ename);
		hql.append(" where 1=1 and isTemp != 1 ");
		if (params != null) {
			if (params.containsKey("id")) {
				Long id = (Long) params.get("id");
				if (id == null) {
					params.remove("id");
				} else {
					hql.append(" and ").append(ename).append(".id > " + id);
				}
			}
		} else {
			
		}
		hql.append(" ORDER BY ").append(ename).append(".id ASC");
		return hql;
	}

}

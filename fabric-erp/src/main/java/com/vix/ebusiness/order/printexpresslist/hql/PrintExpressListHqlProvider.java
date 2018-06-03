package com.vix.ebusiness.order.printexpresslist.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class PrintExpressListHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findShippingHql(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select * ");
		hql.append(" from E_SHIPPING ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("orderBatchId")) {
				Long orderBatchId = (Long) params.get("orderBatchId");
				if (orderBatchId == null) {
					hql.append(" and ").append("hentity.orderBatch_id is null ");
					params.remove("orderBatchId");
				} else {
					hql.append(" and ").append("hentity.orderBatch_id =" + orderBatchId);
				}
			}
		}
		hql.append("  ORDER BY hentity.printPage asc ");
		return hql;
	}
}

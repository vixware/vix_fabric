package com.vix.oa.currentexpense.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class ExpensesSummaryPersonHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findTravelExpenseSql(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select * ");
		hql.append(" from OA_TRAVELEXPENSE ").append(ename);
		hql.append(" where 1=1 and isTemp != 1 ");
		if (params != null) {
			if (params.containsKey("id")) {
				Object id = params.get("id");
				if (id == null) {
					hql.append(" and ").append(ename).append(".employee_ID is null ");
					params.remove("id");
				} else {
					hql.append(" and ").append(ename).append(".employee_ID = :id ");
				}
			}
		} else {
			
		}
		hql.append(" group by ").append(ename).append(".employee_ID");
		return hql;
	}
}

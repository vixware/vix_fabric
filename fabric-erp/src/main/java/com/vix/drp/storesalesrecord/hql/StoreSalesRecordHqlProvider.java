package com.vix.drp.storesalesrecord.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class StoreSalesRecordHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findBeforeOrder(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from com.vix.ebusiness.entity.Order ").append(ename);
		hql.append(" where 1=1 and dealState = 1 ");
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

	public StringBuilder findAfterOrder(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from com.vix.ebusiness.entity.Order ").append(ename);
		hql.append(" where 1=1 and dealState = 1 ");
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

	public StringBuilder findNodeMonitor(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from NodeMonitor ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
		} else {
			
		}
		hql.append(" ORDER BY ").append(ename).append(".id DESC");
		return hql;
	}

}

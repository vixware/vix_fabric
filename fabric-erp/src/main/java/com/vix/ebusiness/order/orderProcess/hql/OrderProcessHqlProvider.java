package com.vix.ebusiness.order.orderProcess.hql;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class OrderProcessHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	/*
	 * public StringBuilder findBeforeOrder(Map<String, Object> params, BaseEntity
	 * entity) { String ename = entityAsName(); StringBuilder hql = new
	 * StringBuilder(); hql.append("select ").append(ename);
	 * hql.append(" from ").append(entity.getClass()).append(ename);
	 * hql.append(" where 1=1 and dealState = 1 "); if (params != null) { if
	 * (params.containsKey("id")) { Long id = (Long) params.get("id"); if (id ==
	 * null) { params.remove("id"); } else {
	 * hql.append(" and ").append(ename).append(".id < " + id); } } }
	 * hql.append(" ORDER BY ").append(ename).append(".id DESC"); return hql; }
	 */

	public StringBuilder findBeforeAndAfterEntity(Map<String, Object> params, BaseEntity entity, String condition) {

		params.put("tenantId", entity.getTenantId());
		params.put("companyInnerCode", entity.getCompanyInnerCode());

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ").append(entity.getClass().getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("createTime")) {
				String createTime = (String) params.get("createTime");
				if (createTime == null) {
					params.remove("createTime");
				} else {
					hql.append(" and ").append(ename).append(".createTime");
					if ("after".equals(condition)) {
						hql.append(" > ");
					} else if ("before".equals(condition)) {
						hql.append(" < ");
					}
					hql.append("'").append(createTime).append("'");
				}
			}
			Set<String> keys = params.keySet();
			if (keys != null && !keys.isEmpty()) {
				for (String s : keys) {
					if ("java.util.Date".equals(getTypeName(entity.getClass(), s))) {

					} else if ("java.lang.String".equals(getTypeName(entity.getClass(), s))) {
						Object storecode = params.get(s);
						if (storecode == null) {
							hql.append(" and ").append(ename).append(".").append(s).append(" is null ");
							params.remove(s);
						} else {
							hql.append(" and ").append(ename).append(".").append(s).append(" = :").append(s).append(" ");
						}
					}
				}
			}
		}
		hql.append(" ORDER BY ").append(ename);
		if ("after".equals(condition)) {
			hql.append(".createTime ASC");
		} else {
			hql.append(".createTime DESC");
		}
		return hql;
	}

	public static String getTypeName(Class<?> cls, String fieldName) {
		Field[] fieldlist = cls.getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field fld = fieldlist[i];
			if (fieldName.equals(fld.getName())) {
				return fld.getType().getName();
			}
		}
		return null;
	}

	public StringBuilder findNodeMonitor(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from NodeMonitor ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("routeId")) {
				String routeId = (String) params.get("routeId");
				if (routeId == null) {
					params.remove("routeId");
				} else {
					hql.append(" and ").append(ename).append(".routeId  = :" + routeId);
				}
			}
		}
		return hql;
	}
}

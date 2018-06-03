package com.vix.ebusiness.item.itemdownload.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class ItemProcessHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findBeforeGoods(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from com.vix.ebusiness.entity.Goods ").append(ename);
		hql.append(" where 1=1 ");
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

	public StringBuilder findAfterGoods(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from com.vix.ebusiness.entity.Goods ").append(ename);
		hql.append(" where 1=1  ");
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

package com.vix.inventory.warehouse.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.inventory.warehouse.entity.InvWarehousezone;

@Component
@Scope("prototype")
public class InvHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "invWarehouse";
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param orderField
	 * @param orderBy
	 * @param extHql
	 * @return
	 */
	public StringBuilder findOrgList(Map<String, Object> params, String orderField, String orderBy, String extHql) {
		String entityAsName = entityAsName();
		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" left join fetch ").append(entityAsName).append(".subInvWarehousezone ");
		joinHql.append(" left join fetch ").append(entityAsName).append(".subInvShelf ");

		StringBuilder hql = generatorHql(InvWarehousezone.class, params, orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

	public StringBuilder findInvWarehouseList(Map<String, Object> params, String orderField, String orderBy, String extHql) {
		String entityAsName = entityAsName();
		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" left join fetch ").append(entityAsName).append(".subInvWarehouse ");

		StringBuilder hql = generatorHql(InvWarehouse.class, params, orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

	public StringBuilder findInvShelfList(Map<String, Object> params, String orderField, String orderBy, String extHql) {
		String entityAsName = entityAsName();
		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" left join fetch ").append(entityAsName).append(".subInvShelfs ");
		StringBuilder hql = generatorHql(InvShelf.class, params, orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

}

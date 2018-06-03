package com.vix.sales.order.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class SalesOrderHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "salesOrder";
	}

	public StringBuilder findSalesOrder(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from SalesOrder ").append(ename);
		hql.append(" where 1=1 ");
		return hql;
	}
	
	public StringBuilder findOrgPositionById(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from SalesOrder ").append(ename);
		hql.append(" where salesOrder.id= :salesOrderId ");
		return hql;
	}
}

package com.vix.nvix.purchase.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
public class VixntPurchaseOrderHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findInventoryCurrentStockHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "inventoryCurrentStock";
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}
}

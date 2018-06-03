package com.vix.beiken.base.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
public class BeikenBaseHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findStoreItemPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from StoreItem ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name like :name ");
				}
			}
			if (params.containsKey("flag")) {
				Object flag = params.get("flag");
				if (flag == null) {
					hql.append(" and ").append(ename).append(".flag is null");
					params.remove("flag");
				} else {
					hql.append(" and ").append(ename).append(".flag = :flag ");
				}
			}
			if (params.containsKey("itemCatalogId")) {
				Object itemCatalogId = params.get("itemCatalogId");
				if (itemCatalogId == null) {
					hql.append(" and ").append(ename).append(".itemCatalog.id is null");
					params.remove("itemCatalogId");
				} else {
					hql.append(" and ").append(ename).append(".itemCatalog.id = :itemCatalogId ");
				}
			}
		}
		if (pager != null && StringUtils.isEmpty(pager.getOrderField())) {
			hql.append(" order by ").append(ename).append(".createTime desc ");
		} else {
			buildOrderQl(ename, hql, pager);
		}
		return hql;
	}

	public StringBuilder findStoreItem(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from StoreItem ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
			if (params.containsKey("itemIds")) {
				Object itemIds = params.get("itemIds");
				if (itemIds == null || "".equals(itemIds)) {
					params.remove("itemIds");
				} else {
					hql.append(" and ").append(ename).append(".id in (");
					String[] ivArray = itemIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
			if (params.containsKey("itemCatalogIds")) {
				Object itemCatalogIds = params.get("itemCatalogIds");
				if (itemCatalogIds == null || "".equals(itemCatalogIds)) {
					params.remove("itemCatalogIds");
				} else {
					hql.append(" and ").append(ename).append(".itemCatalog.id in (");
					String[] ivArray = itemCatalogIds.toString().split(",");
					for (int i = 0; i < ivArray.length; i++) {
						String iv = ivArray[i];
						iv = iv.trim();
						if (null != iv && !"".equals(iv)) {
							hql.append("'");
							hql.append(iv);
							hql.append("'");
							if (i < ivArray.length - 1) {
								hql.append(",");
							}
						}
					}
					hql.append(") ");
				}
			}
			if(params.containsKey("name")){
				Object name = params.get("name");
				if (name == null && "".equals(name)) {
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name like :name ");
				}
			}
		}
		return hql;
	}
	
	public StringBuilder findItemCatalog(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ItemCatalog ").append(ename);
		hql.append(" left outer join fetch ").append(ename).append(".subChannelDistributors  e ");
		hql.append(" where 1=1 and ").append(ename).append(".parentItemCatalog.id is null ");
		if (params != null) {
			if (params.containsKey("channelDistributorsId")) {
				Object channelDistributorsId = params.get("channelDistributorsId");
				if (channelDistributorsId == null) {
					hql.append(" and ").append("e.id is null");
					params.remove("channelDistributorsId");
				} else {
					hql.append(" and ").append("e.id = :channelDistributorsId ");
				}
			}
		}
		return hql;
	}
}

package com.vix.drp.distributermanagement.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
@Scope("prototype")
public class ChannelDistributorHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "channelDistributor";
	}

	/**
	 * 只获取当前公司下边的分销体系结构
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public StringBuilder findOrgPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ChannelDistributor ").append(ename);
		hql.append(" inner join ").append(ename).append(".organization org ");
		hql.append(" where 1=1 ");
		if (params != null) {
			// 根据传入的参数的不同确定查询的hql语句
			if (params.containsKey("organizationId")) {
				hql.append(" and org.id=:organizationId ");
			}
			if (params.containsKey("channelDistributorId")) {
				hql.append(" and ").append(ename).append(".id=:channelDistributorId ");
			}
		}
		buildOrderQl(ename, hql, pager);
		return hql;
	}

	public StringBuilder findChannelDistributorList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ChannelDistributor ").append(ename);
		hql.append(" where 1=1 and isTemp  !=1 and ");
		hql.append(ename).append(".isHasParentChannelDistributor !=1 ");
		if (params != null) {
			if (params.containsKey("orgId")) {
				Object orgId = params.get("orgId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".organization.id is null ");
					params.remove("orgId");
				} else {
					hql.append(" and ").append(ename).append(".organization.id = :orgId ");
				}
			}
		}

		return hql;
	}

	public StringBuilder findOrganizationUnitList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrganizationUnit ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("orgId")) {
				Object orgId = params.get("orgId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".organization.id is null ");
					params.remove("orgId");
				} else {
					hql.append(" and ").append(ename).append(".organization.id = :orgId ");
				}
			}
		}

		return hql;
	}

	public StringBuilder findChannelDistributorListById(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ChannelDistributor ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".parentChannelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".parentChannelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}
}
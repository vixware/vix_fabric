package com.vix.WebService.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class StoreManagementHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "customerAccount";
	}

	public StringBuilder findCustomerAccountList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from CustomerAccount ").append(ename);
		hql.append(" where 1=1  and isDownLoad != '2'");
		if (params != null) {
			if (params.containsKey("storecode")) {
				Object storecode = params.get("storecode");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.code is null ");
					params.remove("channelDistributor.code");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.code = :storecode ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findMSCardList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename).append(".vipcardid,");
		hql.append(ename).append(".point,");
		hql.append(ename).append(".balance_amount ");
		hql.append(" from MemberShipCard ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("phoneOffice")) {
				String phoneOffice = (String) params.get("phoneOffice");
				if (phoneOffice == null) {
					hql.append(" and ").append(ename).append(".customerAccount.phoneOffice is null ");
					params.remove("phoneOffice");
				} else {
					hql.append(" and ").append(ename).append(".customerAccount.phoneOffice ='" + phoneOffice + "'");
				}
			}
		}
		return hql;
	}

	public StringBuilder findInventoryCurrentStockList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("storecode")) {
				Object storecode = params.get("storecode");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.code is null ");
					params.remove("channelDistributor.code");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.code = :storecode ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		}
		return hql;
	}

	public StringBuilder findChannelDistributorList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from ChannelDistributor ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("storecode")) {
				Object storecode = params.get("storecode");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".code is null ");
					params.remove("channelDistributor.code");
				} else {
					hql.append(" and ").append(ename).append(".code = :storecode ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
		} 
		return hql;
	}

	public StringBuilder findVaUser(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from VaUser ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("account")) {
				Object account = params.get("account");
				if (account == null) {
					hql.append(" and ").append(ename).append(".account is null ");
					params.remove("account");
				} else {
					hql.append(" and ").append(ename).append(".account = :account ");
				}
			}
			if (params.containsKey("password")) {
				Object password = params.get("password");
				if (password == null) {
					hql.append(" and ").append(ename).append(".password is null ");
					params.remove("password");
				} else {
					hql.append(" and ").append(ename).append(".password = :password ");
				}
			}
		} 
		return hql;
	}
	public StringBuilder findUser(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from VaUser ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("account")) {
				Object account = params.get("account");
				if (account == null) {
					hql.append(" and ").append(ename).append(".account is null ");
					params.remove("account");
				} else {
					hql.append(" and ").append(ename).append(".account = :account ");
				}
			}
		} 
		return hql;
	}

	public StringBuilder findMemberShipCardList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from MemberShipCard ").append(ename);
		hql.append(" where 1=1 and isDownLoad != '2' ");
		//hql.append(" where 1=1 and opentype = 'B' and isDownLoad != '2' ");
		if (params != null) {
			if (params.containsKey("storecode")) {
				Object storecode = params.get("storecode");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.code is null ");
					params.remove("channelDistributor.code");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.code = :storecode ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
			if (params.containsKey("customerAccountId")) {
				Object customerAccountId = params.get("customerAccountId");
				if (customerAccountId == null) {
					hql.append(" and ").append(ename).append(".customerAccount.id is null ");
					params.remove("customerAccountId");
				} else {
					hql.append(" and ").append(ename).append(".customerAccount.id = :customerAccountId ");
				}
			}
		} else {
			
		}
		return hql;
	}

	public StringBuilder findSalesOrder(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from SalesOrder ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("code")) {
				Object storecode = params.get("code");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".code is null ");
					params.remove("code");
				} else {
					hql.append(" and ").append(ename).append(".code = :code ");
				}
			}
			if (params.containsKey("cashbatch")) {
				Object tenantId = params.get("cashbatch");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".cashbatch is null ");
					params.remove("cashbatch");
				} else {
					hql.append(" and ").append(ename).append(".cashbatch = :cashbatch ");
				}
			}
		} else {
			
		}
		return hql;
	}

	public StringBuilder findTranLog(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from TranLog ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("vipCardId")) {
				Object storecode = params.get("vipCardId");
				if (storecode == null) {
					hql.append(" and ").append(ename).append(".vipCardId is null ");
					params.remove("vipCardId");
				} else {
					hql.append(" and ").append(ename).append(".vipCardId = :vipCardId ");
				}
			}
			if (params.containsKey("macId")) {
				Object tenantId = params.get("macId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".macId is null ");
					params.remove("macId");
				} else {
					hql.append(" and ").append(ename).append(".macId = :macId ");
				}
			}
		} else {
			
		}
		return hql;
	}
}

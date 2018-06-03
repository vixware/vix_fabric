package com.vix.crm.business.hql;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class PurchasingBehaviorHqlProvider extends HqlProvider {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findStoreSalesInformationList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("select t1.time,t1.firstOrderAmount,t1.firstOrderNum,t1.firstOrderPrice,t2.buyBackOrderAmount,t2.buyBackOrderNum,t2.buyBackOrderPrice,t1.channelDistributorId from (select t.createTime time,SUM(t.payment) firstOrderAmount ,SUM(1) firstOrderNum,SUM(t.payment)/SUM(1) firstOrderPrice,t.channelDistributor_id channelDistributorId from SALE_SALESORDER  t ,CRM_B_CRMMEMBER c1 where t.customerAccount_id=c1.customerAccount_id and c1.itemNum=1   GROUP BY time) t1,(select f.createTime time1,SUM(f.payment) buyBackOrderAmount ,SUM(1) buyBackOrderNum,SUM(f.payment)/SUM(1) buyBackOrderPrice  from SALE_SALESORDER f,CRM_B_CRMMEMBER c2 where f.customerAccount_id=c2.customerAccount_id and c2.itemNum >1   GROUP BY  time1 ) t2 where t1.time=t2.time1");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("t1.channelDistributorId is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("t1.channelDistributorId  ='" + channelDistributorId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("t1.time BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	public StringBuilder findGoodsSaleInformationList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t.title,SUM(t.payment),SUM(t.num),SUM(t.payment)/SUM(t.num) FROM SaleOrderItem t,SalesOrder s where 1=1 and t.title is not null and t.salesOrder.id=s.id ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("s.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("s.channelDistributor.id ='" + channelDistributorId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("t.createTime BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		hql.append(" GROUP BY t.title");
		return hql;
	}

	public StringBuilder findIdAndTitleVoList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("select group_concat(s.title),group_concat(s.id) from SALE_SALEORDERITEM s where s.title is not null ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("s.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		hql.append(" GROUP BY s.salesorder_id");
		return hql;
	}

	//新客户
	public StringBuilder findNewCustomerAnalysis(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(1),SUM(s.payment),SUM(s.payment)/SUM(1) from  CRM_CUSTOMERACCOUNT c  ,SALE_SALESORDER  s where s.customerAccount_id=c.ID ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("c.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//活跃客户
	public StringBuilder findCustomerAnalysis(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(1),SUM(s.payment),SUM(s.payment)/SUM(1) from  CRM_CUSTOMERACCOUNT c  ,SALE_SALESORDER  s where s.customerAccount_id=c.ID ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("s.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//购买多次
	public StringBuilder findBuyMore(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(1),SUM(s.payment),SUM(s.payment)/SUM(1) from  CRM_B_CRMMEMBER c  ,SALE_SALESORDER  s where s.customerAccount_id=c.customerAccount_id and c.tradeCount>1");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("s.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//购买一次
	public StringBuilder findBuyOne(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(1),SUM(s.payment),SUM(s.payment)/SUM(1) from  CRM_B_CRMMEMBER c  ,SALE_SALESORDER  s where s.customerAccount_id=c.customerAccount_id and c.tradeCount=1");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("s.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//老客户
	public StringBuilder findOldCustomerAnalysis(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(1),SUM(s.payment),SUM(s.payment)/SUM(1) from  CRM_CUSTOMERACCOUNT c  ,SALE_SALESORDER  s where s.customerAccount_id=c.ID ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append("s.CREATETIME BETWEEN  '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		} 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		hql.append(" and ").append("c.createTime <'").append(sdf.format(cal.getTime()) + " 00:00:00'");

		return hql;
	}

	//获取成交金额
	public StringBuilder findSalesAnalysis(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(s.payment),SUM(1),SUM(s.payment)/SUM(1),SUM(s.num)/SUM(1) from  SALE_SALESORDER  s where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append(" s.CREATETIME BETWEEN '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//回头购买订单数
	public StringBuilder findBuyMoreData(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(s.payment),SUM(1),SUM(s.payment)/SUM(1),SUM(s.num)/SUM(1) from   CRM_B_CRMMEMBER c  ,SALE_SALESORDER  s where s.customerAccount_id=c.customerAccount_id and c.tradeCount>1");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append(" s.CREATETIME BETWEEN '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		}
		return hql;
	}

	//首次购买订单数
	public StringBuilder findBuyOneData(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT SUM(s.payment),SUM(1),SUM(s.payment)/SUM(1),SUM(s.num)/SUM(1) from   CRM_CUSTOMERACCOUNT c,SALE_SALESORDER  s where s.customerAccount_id=c.ID ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("t.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("t.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				hql.append(" and ").append(" c.CREATETIME BETWEEN '").append(startTime);
				hql.append("' AND '").append(endTime).append("'");
			}
		} 
		return hql;
	}
}

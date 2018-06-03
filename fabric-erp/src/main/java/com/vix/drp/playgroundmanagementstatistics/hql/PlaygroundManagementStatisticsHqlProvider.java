package com.vix.drp.playgroundmanagementstatistics.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class PlaygroundManagementStatisticsHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	/**
	 * 设备消费占比
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findTranLogList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename).append(".macDepartName,");
		hql.append(ename).append(".macTypeName,sum(");
		hql.append(ename).append(".amount) as amount");
		hql.append(" from TranLog ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append(ename).append(".companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append(ename).append(".companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" GROUP BY ");
		hql.append(ename);
		hql.append(".macTypeName");
		return hql;
	}

	public StringBuilder findMemberShipCardList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename).append(".vipTypeName,");
		hql.append("sum(1)");
		hql.append(" from MemberShipCard ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append(ename).append(".companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append(ename).append(".companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" and ");
		hql.append(ename).append(".viptypeid is not null GROUP BY ");
		hql.append(ename);
		hql.append(".viptypeid");
		return hql;
	}

	public StringBuilder findCustomerAccountList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename).append(".sex,");
		hql.append("sum(1)");
		hql.append(" from CustomerAccount ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append(ename).append(".companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append(ename).append(".companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" GROUP BY ");
		hql.append(ename);
		hql.append(".sex");
		return hql;
	}

	/**
	 * 设备投币数排行
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findClassifiedRevenueList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ");
		hql.append(ename).append(".macTypeName,sum(");
		hql.append(ename).append(".amount) as amount ");
		hql.append(" from TranLog ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append(ename).append(".companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append(ename).append(".companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append("GROUP BY ");
		hql.append(ename);
		hql.append(".macTypeName order by amount");
		hql.append(" desc");
		return hql;
	}

	/**
	 * 消费人次报表
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findConsumerPeopleReportList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ");
		hql.append(ename).append(".macTypeName,sum(");
		hql.append(ename).append(".amount) as amount,avg(");
		hql.append(ename).append(".amount) as avg");
		hql.append(" from TranLog ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append(ename).append(".companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append(ename).append(".companyCode ='" + companyCode + "' ");
				}
			}*/
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id =" + channelDistributorId);
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				if (startTime != null && endTime != null) {
					hql.append(" and ").append(ename).append(".tradeDate  BETWEEN '" + startTime + "' ");
					hql.append(" and ").append(" '" + endTime + "' ");
				}
			}
		} else {
			
		}
		hql.append("GROUP BY ");
		hql.append(ename);
		hql.append(".macTypeName");
		return hql;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findCustomerCountList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT channel.name, sum( CASE card.viptypeid WHEN 'TYK' THEN 1 ELSE 0 END ) AS 体验卡, " + "sum( CASE card.viptypeid WHEN 'FKK'   THEN 1 ELSE 0 END ) AS 访客卡," + "sum( CASE card.viptypeid WHEN 'JMK'   THEN 1 ELSE 0 END ) AS 居民卡," + "sum( CASE card.viptypeid WHEN 'ZLK'   THEN 1 ELSE 0 END ) AS 长老卡 ," + "sum( CASE card.viptypeid WHEN 'SRK'   THEN 1 ELSE 0 END ) AS 生日卡 " + " FROM MemberShipCard card, ChannelDistributor channel " + " WHERE card.channelDistributor.id = channel.id ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("card.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("card.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("card.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("card.companyCode ='" + companyCode + "' ");
				}
			}*/
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("card.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("card.channelDistributor.id =" + channelDistributorId);
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				if (startTime != null && endTime != null) {
					hql.append(" and ").append("card.opencarddate  BETWEEN '" + startTime + "' ");
					hql.append(" and ").append(" '" + endTime + "' ");
				}
			}
		} else {
			
		}
		hql.append("GROUP BY channel.name");
		return hql;
	}

	/**
	 * 查询会员年龄
	 * 
	 * @param params
	 * @return
	 */
	public StringBuilder findCustomerCountAgeList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c.age,SUM(1.0) from CustomerAccount c where c.age is not null ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("c.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("c.tenantId ='" + tenantId + "'");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("c.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("c.channelDistributor.id =" + channelDistributorId);
				}
			}
		} else {
			
		}
		hql.append(" GROUP BY c.age ");
		return hql;
	}

	//商品统计
	public StringBuilder findGoodsStatisticsList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT item.itemname AS 商品名称, SUM(inv.netTotal) AS 金额 FROM SaleOrderItem inv ," + " InventoryCurrentStock item where inv.item.skuCode = item.skuCode ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("item.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("item.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("item.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("item.companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" GROUP BY item.itemname");
		return hql;
	}

	public StringBuilder findEquipmentCoinNumberList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t.macTypeName as 名称,SUM(t.amount) as 金额  FROM TranLog t where 1=1 ");
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
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("t.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("t.companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" GROUP BY t.macTypeName");
		return hql;
	}

	public StringBuilder findMonthAccountBalanceList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT salesOrder.billDate, sum( CASE salesOrder.bizType WHEN '售币' THEN salesOrder.amount ELSE 0 END ) AS 售币金额," +
		" sum( CASE salesOrder.bizType WHEN '餐饮销售' THEN salesOrder.amount ELSE 0 END ) AS 餐饮销售金额, " +
				" sum( CASE salesOrder.bizType WHEN '商品销售' THEN salesOrder.amount ELSE 0 END ) AS 商品销售金额," + 
		" sum(salesOrder.amount) AS 总计, sum( CASE salesOrder.payType WHEN '现金' THEN salesOrder.amount ELSE 0 END ) AS 现金金额,"
				+ " sum( CASE salesOrder.payType WHEN 'pos机' THEN salesOrder.amount ELSE 0 END ) AS pos机金额, " +
		" sum( CASE salesOrder.payType WHEN '团购' THEN salesOrder.amount ELSE 0 END ) AS 团购金额 FROM SalesOrder salesOrder " +
				" WHERE salesOrder.billDate IS NOT NULL AND salesOrder.amount IS NOT NULL AND salesOrder.bizType IN ( '售币', '餐饮销售', '商品销售' ) " 
		+ " AND salesOrder.payType IN ('现金', 'pos机', '团购') ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("salesOrder.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("salesOrder.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("salesOrder.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("salesOrder.companyCode ='" + companyCode + "' ");
				}
			}*/
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("salesOrder.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("salesOrder.channelDistributor.id =" + channelDistributorId);
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				if (startTime != null && endTime != null) {
					hql.append(" and ").append("salesOrder.billDate  BETWEEN '" + startTime + "' ");
					hql.append(" and ").append(" '" + endTime + "' ");
				}
			}
		} else {
			
		}
		hql.append(" GROUP BY salesOrder.billDate");
		return hql;
	}

	public StringBuilder findGroupIncomeStatementList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT channelDistributor.name, salesOrder.billDate, sum( CASE salesOrder.bizType" + " WHEN '售币' THEN salesOrder.amount ELSE 0 END ) AS 售币金额, sum( CASE salesOrder.bizType WHEN '餐饮销售' " + "THEN salesOrder.amount ELSE 0 END ) AS 餐饮销售金额, sum( CASE salesOrder.bizType WHEN " + "'商品销售' THEN salesOrder.amount ELSE 0 END ) AS 商品销售金额, sum(salesOrder.amount)" + " AS 总计, sum( CASE salesOrder.payType WHEN '现金' THEN salesOrder.amount ELSE 0 END ) " + " AS 现金金额, sum( CASE salesOrder.payType WHEN 'pos机' THEN salesOrder.amount ELSE 0 END )" + " AS pos机金额, sum( CASE salesOrder.payType WHEN '团购' THEN salesOrder.amount ELSE 0 END )" + " AS 团购金额   FROM SalesOrder salesOrder, ChannelDistributor channelDistributor " + " WHERE  channelDistributor.id = salesOrder.channelDistributor.id " + " and  salesOrder.billDate IS NOT NULL AND amount IS NOT NULL AND salesOrder.bizType IN ( '售币', '餐饮销售', '商品销售' ) " + " AND salesOrder.payType IN ('现金', 'pos机', '团购') ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("salesOrder.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("salesOrder.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("salesOrder.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("salesOrder.companyCode ='" + companyCode + "' ");
				}
			}*/
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("salesOrder.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("salesOrder.channelDistributor.id =" + channelDistributorId);
				}
			}
			if (params.containsKey("startTime") && params.containsKey("endTime")) {
				String startTime = (String) params.get("startTime");
				String endTime = (String) params.get("endTime");
				if (startTime != null && endTime != null) {
					hql.append(" and ").append("salesOrder.billDate  BETWEEN '" + startTime + "' ");
					hql.append(" and ").append(" '" + endTime + "' ");
				}
			}
		} else {
			
		}
		hql.append(" GROUP BY channelDistributor.name, salesOrder.billDate");
		return hql;
	}

	public StringBuilder findSalesOrderList(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT salesorder.creator, sum(CASE salesorder.payType WHEN '现金'  THEN salesorder.amount ELSE 0 END ) AS 现金金额应付," + " sum( CASE salesorder.payType WHEN 'pos机'  THEN salesorder.amount ELSE 0 END ) AS pos机金额应付, " + "sum( CASE salesorder.payType WHEN '团购'   THEN salesorder.amount ELSE 0 END ) AS 团购金额应付, " + "sum( CASE salesorder.payType WHEN '现金'   THEN salesorder.payAmount ELSE 0 END ) AS 现金金额实付, " + "sum( CASE salesorder.payType WHEN 'pos机'  THEN salesorder.payAmount ELSE 0 END ) AS pos机金额实付, " + "sum( CASE salesorder.payType WHEN '团购'  THEN salesorder.payAmount ELSE 0 END ) AS 团购金额实付 FROM " + "SalesOrder salesorder WHERE salesorder.amount IS NOT NULL ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("salesorder.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("salesorder.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("salesorder.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("salesorder.companyCode ='" + companyCode + "' ");
				}
			}*/
		} else {
			
		}
		hql.append(" GROUP BY salesorder.creator");
		return hql;
	}

	public StringBuilder findSalesOrder(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT  salesorder.billDate,sum(salesorder.amount)   FROM SalesOrder salesorder WHERE salesorder.amount IS NOT NULL ");

		if (params != null) {
			if (params.containsKey("tenantId")) {
				String tenantId = (String) params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append("salesorder.tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append("salesorder.tenantId ='" + tenantId + "'");
				}
			}
			/*if (params.containsKey("companyCode")) {
				String companyCode = (String) params.get("companyCode");
				if (companyCode == null) {
					hql.append(" and ").append("salesorder.companyCode is null ");
					params.remove("companyCode");
				} else {
					hql.append(" and ").append("salesorder.companyCode ='" + companyCode + "' ");
				}
			}*/
			if (params.containsKey("channelDistributorId")) {
				String channelDistributorId = (String) params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append("salesorder.channelDistributor.id is null ");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append("salesorder.channelDistributor.id =" + channelDistributorId);
				}
			}
			if (params.containsKey("date")) {
				String date = (String) params.get("date");
				if (date != null) {
					hql.append(" and ").append("salesorder.billDate  BETWEEN '" + date + " 00:00:00' ");
					hql.append(" and ").append(" '" + date + " 23:59:59' ");
				}
			}
		} else {
			
		}
		return hql;
	}

}

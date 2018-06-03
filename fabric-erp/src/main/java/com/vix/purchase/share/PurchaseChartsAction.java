package com.vix.purchase.share;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class PurchaseChartsAction extends PurchaseAction {

	String dataTarget;
	String dataSource;
	String timeType;
	String timeRange;
	
	public String tjChartsBase()
	{
		return "tjChartsBase";
	}
	
	public String tjLoadChartsGrid()
	{
		//只放sql中用到的变量值，不要比价符
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
		params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		
		this.addTimeLimitToParam(params);
		
		Pager myPager = this.getPager();
		myPager.setPageSize(10);
		
		String fullSql = genDataQuerySqlFull();
		try{
			this.baseHibernateService.findPagerBySqlFull(myPager, fullSql, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "tjLoadChartsGrid";
	}
	
	String genDataQuerySqlFull()
	{
		StringBuilder sb = new StringBuilder();
		String timeLen = "10";
		if("month".equals(this.timeType))
			timeLen = "7";
		else if("year".equals(this.timeType))
			timeLen = "4";
		
		String source = "pur_arrivalitems";
		String sourceParent = "pur_arrival";
		String sourceParentIdColunn = "PURCHASEARRIVAL_ID";
		String timeColumn = "DELIVERYDATE";
		if("order".equals(this.dataSource))
		{
			source = "pur_orderlineitem";
			sourceParent = "pur_order";
			sourceParentIdColunn = "purchaseorder_id";
			timeColumn = "DELIVERYDATE";
		}
		else if("inbound".equals(this.dataSource))
		{
			source = "pur_inbounditems";
			sourceParent = "pur_inbound";
			sourceParentIdColunn = "purchaseinbound_id";
			timeColumn = "DELIVERYDATE";
		}
		
		String itemTableName = "p_item";
		String itemNameColumn = "ITEMNAME";
		String itemIdColumn = "ITEMID";
		
		if("supplier".equals(this.dataTarget))
		{
			itemTableName = "p_entity";
			itemNameColumn = "suppliername";
			itemIdColumn = "supplierid";
		}

		sb.append("SELECT LEFT(p_entity.").append(timeColumn).append(",").append(timeLen).append(") AS item_date");
		sb.append(",ifnull(").append(itemTableName).append(".").append(itemNameColumn).append(",'其他') AS item_name");
		sb.append(",ifnull(").append(itemTableName).append(".").append(itemIdColumn).append(",0) AS item_id");
		sb.append(",COUNT(1) AS tatol_count");
		sb.append(",SUM(p_item.`AMOUNT`) AS tatol_amount");
		sb.append(",SUM(p_item.`PRICE` * p_item.`AMOUNT`) AS tatol_fee");
		sb.append(",SUM(p_item.`PRICE` * p_item.`AMOUNT` * IFNULL(p_item.`TAX`,0) )/ 100 AS tatol_tax ");
		sb.append(" FROM ").append(source).append(" p_item ");
		sb.append(" JOIN ").append(sourceParent).append(" p_entity ON p_entity.`ID`=p_item.").append(sourceParentIdColunn);
		sb.append(" WHERE p_entity.TENANTID='")
			.append(SecurityUtil.getCurrentUserTenantId())
			.append("' AND p_entity.companyinnercode='")
			.append(SecurityUtil.getCurrentEmpOrgInnerCode())
			.append("' AND p_item.`AMOUNT`>0");
		
		if(StrUtils.isNotEmpty(this.timeRange) && this.timeRange.indexOf("_")!=-1)
		{
			if(this.timeRange.length()==21)
			{
				//yyyy-MM-dd_yyyy-MM-dd
				String startDate = this.timeRange.substring(0,10);
				String endDate = this.timeRange.substring(11);
				sb.append(" and p_entity.")
					.append(timeColumn)
					.append(" between '")
					.append(startDate)
					.append(" 00:00:00")
					.append("' and '")
					.append(endDate)
					.append(" 23:59:59")
					.append("' ");
			}
			else if(this.timeRange.length()==15)
			{
				//yyyy-MM_yyyy-MM
				String startDate = this.timeRange.substring(0,7);
				String endDate = this.timeRange.substring(8);
				int date = 31;
				try{					
					int month = Integer.parseInt(endDate.substring(5));
					if(month==4||month==6||month==9||month==11)
						date=30;
					else if(month==2){
						int year = Integer.parseInt(endDate.substring(0,4));
						date = (year%4==0&&year%100!=0)||year%400==0?29:28;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				sb.append(" and p_entity.")
					.append(timeColumn)
					.append(" between '")
					.append(startDate)
					.append("-01 00:00:00")
					.append("' and '")
					.append(endDate)
					.append("-").append(date).append(" 23:59:59")
					.append("' ");
			}
			else if(this.timeRange.length()==9)
			{
				//yyyy_yyyy
				//yyyy-MM_yyyy-MM
				String startDate = this.timeRange.substring(0,4);
				String endDate = this.timeRange.substring(5);
				sb.append(" and p_entity.")
					.append(timeColumn)
					.append(" between '")
					.append(startDate)
					.append("-01-01 00:00:00")
					.append("' and '")
					.append(endDate)
					.append("-12-31 23:59:59")
					.append("' ");
			}
		}
		
		sb.append(" GROUP BY ").append(itemTableName).append(".").append(itemIdColumn);
		sb.append(",LEFT(p_entity.").append(timeColumn).append(",").append(timeLen).append(")");
		sb.append(" ORDER BY p_entity.").append(timeColumn).append(" ASC");

		return sb.toString();
	}
	
	public void tjLoadChartsData()
	{
		//只放sql中用到的变量值，不要比价符
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
		params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		
		this.addTimeLimitToParam(params);
		
		Pager myPager = this.getPager();
		myPager.setPageSize(10000);
		
		String fullSql = genDataQuerySqlFull();
		try{
			this.baseHibernateService.findPagerBySqlFull(myPager, fullSql, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<Map<String,Object>> resultList = myPager.getResultList();
		if(resultList!=null && resultList.size()>0)
		{
			List<String> xAxisList = new ArrayList<String>();
			Map<String,Integer> xAxisMap = new HashMap<String,Integer>();
			Map<String,Map<String,Object>> seriesMap = new HashMap<String,Map<String,Object>>();
			List<Map<String,Object>> seriesList = new ArrayList<Map<String,Object>>();
			Integer xNum = 0;
			for(Map map:resultList)
			{
				String itemDate = String.valueOf(map.get("item_date"));
				if(!xAxisMap.containsKey(itemDate))
				{
					xAxisList.add(itemDate);
					xAxisMap.put(itemDate, xNum++);
				}
				
				String itemId = String.valueOf(map.get("item_id"));
				Map<String,Object> itemData = seriesMap.get(itemId);
				if(itemData==null)
				{
					itemData = new HashMap<String,Object>();
					itemData.put("name", map.get("item_name"));
					List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
 					itemData.put("data", dataList);
 					
 					seriesMap.put(itemId, itemData);
 					seriesList.add(itemData);
				}
				
				List<Map<String,Object>> dataList = (List<Map<String,Object>>)itemData.get("data");
				map.put("x", xAxisMap.get(itemDate));
				map.put("y", map.get("tatol_amount"));
				dataList.add(map);
			}			
			
			retMap.put("categories", xAxisList);
			retMap.put("series", seriesList);
			
			BaseSecAction.renderJson(JSonUtils.toJSon(retMap));
		}
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public String getDataTarget() {
		return dataTarget;
	}

	public void setDataTarget(String dataTarget) {
		this.dataTarget = dataTarget;
	}
}

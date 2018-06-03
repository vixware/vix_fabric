package com.vix.nvix.common.base.hql;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.properties.util.MyTool;
/** 为统计提供sql语句  **/
@Component
@Scope("prototype")
public class SaleDataHqlProvider{
	
	/** 一般返回一个num数据 
	 * @throws ParseException **/
	public StringBuilder blockNumPurchaseDashboard(Map<String, Object> hsMap) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("salesAmount&orderNum")){//salesAmount&orderNum 销售统计仪表盘查询销售订单数&查询销售金额
			hql.append(" select count(*),ifnull(sum(amounttotal),0) from sale_salesorder where 1=1 ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			hql.append(" and istemp='0' and isdeleted='0' and amounttotal is not null ");
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and billdate >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesOrderCustomerNum")) {//salesOrderCustomerNum  销售仪表盘查：今日客户数
			hql.append(" select count(DISTINCT(m.customeraccount_id)),'1' from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id  ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and m.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			hql.append(" and m.istemp='0' and m.isdeleted='0' and m.customeraccount_id <> '' and m.customeraccount_id is not null ");
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and m.billdate >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesOrderNewCustomerNum")) { //salesOrderNewCustomerNum  本月新客户数(去重)
			hql.append(" select count(distinct(m.customeraccount_id)),'1' from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and m.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			hql.append(" and m.istemp='0' and m.isdeleted='0' ");
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and m.billdate >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			hql.append(" and m.customeraccount_id not in( ");
			hql.append(" select distinct(m.customeraccount_id) from sale_salesorder m where 1=1 ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and m.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			hql.append(" and m.istemp='0' and m.isdeleted='0' and m.customeraccount_id is not null ");
			hql.append(" and m.billdate < "+ MyTool.StringUseToSql(object.get(0)));                                              
			hql.append(" ) ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesProductTypes")) {//salesProductTypes 销售产品种类
			hql.append(" select count(DISTINCT(m.item_id)),'1' from SALE_SALEORDERITEM m inner join sale_salesorder t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and t.tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and t.companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			hql.append(" and t.istemp='0' and t.isdeleted='0' ");
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and t.billdate >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("salesReturnOrederNum")) {//salesReturnOrederNum  本月退货订单数(已完成)
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");    
			hql.append(" select count(*) as sl,'1' from sale_salereturnform where 1=1 ");
			hql.append(" and returnTime >= "+ MyTool.StringUseToSql(object.get(0)));//最近30日已完成采购订单金额趋势柱图
			hql.append(" and returnTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
		}
		return hql;
	}
	/** 一般查询视图的sql，如折线图    @throws ParseException **/
	public StringBuilder slowQuerySalesOrderTrend(Map<String, Object> hsMap) throws ParseException {
		StringBuilder hql = new StringBuilder();
		@SuppressWarnings("unchecked")  
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");    
		if((hsMap.get("controlSQL")+"").toString().equals("qvSalesOrderTrendNum")){//qvSalesOrderTrendNum 查询 ‘最近30日销售订单趋势（订单数）’  
			hql.append(" select DATE_FORMAT(billdate, '%Y-%m-%d') AS M,count(*) AS sl from sale_salesorder where 1=1 ");  
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and istemp='0' and isdeleted='0' ");
			hql.append(" and billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" group by m ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qvSalesOrderTrendMoney")){// qvSalesOrderTrendMoney 最近30日销售金额趋势图
			hql.append(" select DATE_FORMAT(billdate, '%Y-%m-%d') as m, sum(ifnull(amounttotal,0)) as sl from sale_salesorder where 1=1 ");  
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and istemp='0' and isdeleted='0' ");
			hql.append(" and billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" group by m ");
		}
		return hql;
	}
	/** 一般查询视图的sql，如折线图    @throws ParseException **/
	public StringBuilder querySalesOrderTrend(Map<String, Object> hsMap) throws ParseException {
		StringBuilder hql = new StringBuilder();
		@SuppressWarnings("unchecked")  
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");   
		int iState = 0;
		if((hsMap.get("controlSQL")+"").toString().equals("querSalesOrderTrendNum")){//querSalesOrderTrendNum  查询‘销售订单数趋势图’
			iState = 1;
		}else if((hsMap.get("controlSQL")+"").toString().equals("querSalesOrderTrendMoney")){// querSalesOrderTrendMoney   查询‘销售金额趋势图’ 
			iState = 2;
		}	
		if( iState == 1 || iState == 2 ){
			String regionalID = (String)hsMap.get("regionalID");
			String customerAccountID = (String)hsMap.get("customerAccountID");
			String employeeID = (String)hsMap.get("employeeID");
			if(iState == 1) {//订单数
				hql.append(" select DATE_FORMAT(m.billdate, '%Y-%m-%d') AS rq,count(*) AS sl from sale_salesorder m "); 
			}else if(iState == 2) {//订单钱
				hql.append(" select DATE_FORMAT(m.billdate, '%Y-%m-%d') as rq, sum(ifnull(m.amounttotal,0)) as sl from sale_salesorder m ");  
			}
			if(StringUtils.isNotEmpty(regionalID)){
				hql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
			}
			if(StringUtils.isNotEmpty(customerAccountID)){
				hql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
			}
			if(StringUtils.isNotEmpty(employeeID)){
				hql.append(" inner join hr_org_employee tc on m.salePerson_id = tc.id and m.salePerson_id='"+employeeID+"'");
			}
			hql.append(" where 1=1 "); 
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and m.istemp='0' and m.isdeleted='0' ");
			hql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" group by rq ");
		}
		return hql;
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售数量TOP10  & ...   @throws ParseException **/
	public StringBuilder slowQuerySalesTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")  
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("qvSellProductsNumTop")){//qvSellProductsNumTop 查询产品销售数量TOP10
			hql.append(" select tb.name,sum(ifnull(m.amount,0)) as sqlnum from SALE_SALEORDERITEM m inner join sale_salesorder t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			hql.append((hsMap.containsKey("tenantId"))? " and t.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and t.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.istemp='0' and t.isdeleted='0' ");
			hql.append(" and t.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" group by m.item_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qvSellProductsMoneyTop")){//qvSellProductsMoneyTop 产品销售金额TOP10
			hql.append(" select tb.name,sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) as sqlnum from SALE_SALEORDERITEM m inner join sale_salesorder t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			hql.append((hsMap.containsKey("tenantId"))? " and t.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and t.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.istemp='0' and t.isdeleted='0' ");
			hql.append(" and t.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" group by m.item_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qvCustomerBuyTopMoneyTop")){//qvCustomerBuyTopMoneyTop 客户购买金额TOP10
			hql.append(" select t.name,sum(ifnull(m.amounttotal, 0)) as sqlnum from sale_salesorder m inner join CRM_CUSTOMERACCOUNT t on m.customerAccount_id = t.id  ");  
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and m.istemp='0' and m.isdeleted='0' ");
			hql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and t.name is not null  and t.name <> '' ");
			hql.append(" group by m.customerAccount_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qvSalesmanSellTopMoney")){//qvSalesmanSellTopMoney 销售人员业绩TOP10
			hql.append(" select t.name,sum(ifnull(m.amounttotal, 0)) as sqlnum from sale_salesorder m inner join HR_ORG_EMPLOYEE t on m.salePerson_id = t.id  ");  
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and m.istemp='0' and m.isdeleted='0' ");
			hql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and t.name is not null  and t.name <> '' ");
			hql.append(" group by m.salePerson_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
	/** 销售智能分析>产品销量排行>产品销售金额排行Top10  & ...   @throws ParseException **/
	public StringBuilder queryProductSalesTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")   
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		String regionalID = (String)hsMap.get("regionalID");
		String customerAccountID = (String)hsMap.get("customerAccountID");
		String employeeID = (String)hsMap.get("employeeID");
		/** 不论地域，客户，营业员是否为空，都拼装内部sql语句，只取其DISTINCT(m.id)  **/
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select DISTINCT(m.id) from sale_salesorder m "); 
		if(StringUtils.isNotEmpty(regionalID)){
			selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
		}
		if(StringUtils.isNotEmpty(customerAccountID)){
			selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
		}
		if(StringUtils.isNotEmpty(employeeID)){
			selectSql.append(" inner join hr_org_employee tc on m.salePerson_id = tc.id and m.salePerson_id='"+employeeID+"'");
		}
		selectSql.append(" where 1=1 "); 
		selectSql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		selectSql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		selectSql.append(" and m.istemp='0' and m.isdeleted='0' ");
		selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("ProductSaleTopmoney")){//ProductSaleTopmoney 产品销售金额排行Top10  
			hql.append(" select tb.name,sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) as sqlnum from sale_saleorderitem m inner join ("+selectSql.toString()+") t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" group by m.item_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("ProductSaleTopnum")){//ProductSaleTopnum  产品销售数量排行Top10
			hql.append(" select tb.name,sum(ifnull(m.amount,0)) as sqlnum from sale_saleorderitem m inner join ("+selectSql.toString()+") t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" group by m.item_id ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
	/** 销售智能分析>销售结构分析>产品类别销售排行柱图和饼图一起查询  TOP10  树形结构排名   @throws ParseException **/
	public StringBuilder queryStructureSalesTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")   
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		String regionalID = (String)hsMap.get("regionalID");
		String customerAccountID = (String)hsMap.get("customerAccountID");
		String employeeID = (String)hsMap.get("employeeID");
		/** 不论地域，客户，营业员是否为空，都拼装内部sql语句，只取其DISTINCT(m.id)  **/
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select DISTINCT(m.id) from sale_salesorder m "); 
		if(StringUtils.isNotEmpty(regionalID)){
			selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
		}
		if(StringUtils.isNotEmpty(customerAccountID)){
			selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
		}
		if(StringUtils.isNotEmpty(employeeID)){
			selectSql.append(" inner join hr_org_employee tc on m.salePerson_id = tc.id and m.salePerson_id='"+employeeID+"'");
		}
		selectSql.append(" where 1=1 "); 
		selectSql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		selectSql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		selectSql.append(" and m.istemp='0' and m.isdeleted='0' ");
		selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("SalesStructureBarAndPieTop")){//SalesStructureBarAndPieTop 产品类别销售排行柱图和饼图一起查询 
			hql.append(" select  ifnull( sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) , 0) as sqlnum,'1' from sale_saleorderitem m inner join ("+selectSql.toString()+") t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" and tb.itemcatalog_id ='"+hsMap.get("itemcatalogID")+"' ");
		}
		return hql;
	}
	/** 销售智能分析>客户购买排行>客户购买排行金额Top10  &  客户购买排行数量Top10   @throws ParseException **/
	public StringBuilder queryCustomerBuyTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")   
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		String regionalID = (String)hsMap.get("regionalID");
		String employeeID = (String)hsMap.get("employeeID");
		/** identicalSql 是查询‘客户购买排行金额Top’和‘客户购买产品总数量Top’的相同sql部分  **/
		StringBuffer identicalSql = new StringBuffer();
		identicalSql.append(" from sale_salesorder m inner join CRM_CUSTOMERACCOUNT t on m.customerAccount_id = t.id ");
		if(StringUtils.isNotEmpty(regionalID)){
			identicalSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
		}
		if(StringUtils.isNotEmpty(employeeID)){
			identicalSql.append(" inner join hr_org_employee tc on m.salePerson_id = tc.id and m.salePerson_id='"+employeeID+"'");
		}
		identicalSql.append(" where 1=1 "); 
		identicalSql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		identicalSql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		identicalSql.append(" and m.istemp='0' and m.isdeleted='0' ");
		identicalSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		identicalSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		identicalSql.append(" and t.name is not null  and t.name <> '' ");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("CustomerBuyTopmoney")){//CustomerBuyTopmoney 客户购买排行金额Top10    
			hql.append(" select t.name,sum(ifnull(m.amounttotal, 0)) as sqlnum ");
			hql.append(" "+identicalSql.toString()+" ");	
			hql.append(" group by m.customerAccount_id order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("CustomerBuyTopnum")){//CustomerBuyTopnum  客户购买排行数量Top10也就是客户购买产品总数量
			hql.append(" select t.pname , sum(ifnull(m.amount, 0)) as sqlnum from sale_saleorderitem m inner join ");
			hql.append(" ("+" select m.id,m.customeraccount_id,t.name as pname "+identicalSql.toString()+")t on m.salesorder_id = t.id "); 
			hql.append(" group by t.customeraccount_id order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
	/** 销售智能分析>销售人员业绩排行>销售人员业绩订单金额Top10  &  销售人员业绩订单数量Top10  &...  @throws ParseException **/
	public StringBuilder querySalesmanSellTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")   
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		String regionalID = (String)hsMap.get("regionalID");
		String customerAccountID = (String)hsMap.get("customerAccountID");
		/** identicalSql 是查询‘销售人员业绩订单金额Top10’和‘销售人员业绩销售产品总数量Top10’的相同sql部分  **/
		StringBuffer identicalSql = new StringBuffer();
		identicalSql.append(" from sale_salesorder m inner join hr_org_employee t on m.salePerson_id = t.id ");
		if(StringUtils.isNotEmpty(regionalID)){
			identicalSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
		}
		if(StringUtils.isNotEmpty(customerAccountID)){
			identicalSql.append(" inner join crm_customeraccount tc on m.customerAccount_id = tc.id and m.customerAccount_id='"+customerAccountID+"'");
		}
		identicalSql.append(" where 1=1 "); 
		identicalSql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		identicalSql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		identicalSql.append(" and m.istemp='0' and m.isdeleted='0' ");
		identicalSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		identicalSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		identicalSql.append(" and t.name is not null  and t.name <> '' ");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("SalesmanSellTopmoney")){//SalesmanSellTopmoney 销售人员业绩：销售产品总金额排行 
			hql.append(" select t.name,sum(ifnull(m.amounttotal, 0)) as sqlnum,m.salePerson_id ");
			hql.append(" "+identicalSql.toString()+" ");	
			hql.append(" group by m.salePerson_id order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("SalesmanSellTopnum")){//SalesmanSellTopnum 销售人员业绩：销售产品总数量排行
			hql.append(" select t.pname , sum(ifnull(m.amount, 0)) as sqlnum,t.salePerson_id from sale_saleorderitem m inner join ");
			hql.append(" ("+" select m.id,m.salePerson_id,t.name as pname "+identicalSql.toString()+")t on m.salesorder_id = t.id "); 
			hql.append(" group by t.salePerson_id order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("SalesmanSellTopOrdersnum")){//SalesmanSellTopOrdersnum 销售订单数量Top10
			hql.append(" select t.name,count(m.id) as sqlnum,m.salePerson_id ");
			hql.append(" "+identicalSql.toString()+" ");	
			hql.append(" group by m.salePerson_id order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
	/** 销售智能分析>退货订单走势图查询   **/
	public StringBuilder querySaleReturnView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();//returnapptime退货申请时间
		hql.append(" SELECT DATE_FORMAT(returnapptime, '%Y-%m-%d' ) as m ,count(*) as sl from sale_salereturnform where 1=1 ");
		hql.append(" and returnapptime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));//最近30日已完成采购订单金额趋势柱图
		hql.append(" and returnapptime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" group by m ");
		return hql;
	}
	/** 销售智能分析>货物流向分析>  销售订单数发货地图排行TOP10 & 销售订单金额发货地图排行TOP10  **/
	public StringBuilder querySendMapTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")  
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("OrderMapNumBar")){//OrderMapNumBar 销售订单数发货地图排行TOP10
			hql.append(" select receivercity,count(*) as sqlnum from e_invoicelist where 1=1 ");  
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and invoiceTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and invoiceTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		    hql.append(" and receivercity is not null  and receivercity <> '' ");
			hql.append(" group by receivercity  order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("OrderMapMoneyBar")){//OrderMapMoneyBar 销售订单金额发货地图排行TOP10
			hql.append(" select receivercity,sum(ifnull(total, 0)) as sqlnum from e_invoicelist where 1=1 ");  
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and invoiceTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and invoiceTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		    hql.append(" and receivercity is not null  and receivercity <> '' ");
			hql.append(" group by receivercity  order by sqlnum desc ");  
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("sendOrderNumInEChartsMap")){//sendOrderNumInEChartsMap 销售智能分析>货物流向分析> 发货订单数在echarts地图中显示
			hql.append(" select receivercity,count(*) as sqlnum from e_invoicelist where 1=1 ");  
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and invoiceTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and invoiceTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		    hql.append(" and receivercity is not null  and receivercity <> '' ");
			hql.append(" group by receivercity  order by sqlnum desc ");
		}
		return hql;
	}
}
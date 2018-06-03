package com.vix.nvix.common.base.hql;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.properties.util.MyTool;
/** 为统计提供sql语句  **/
@Component
@Scope("prototype")
public class PurchaseDataHqlProvider{
	
	/** 一般返回一个num数据 
	 * @throws ParseException **/
	public StringBuilder blockNumPurchaseDashboard(Map<String, Object> hsMap) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("numInquireSanbc")){//numInquireSanbc 查询 ‘采购询价单数’
			hql.append(" SELECT count(*),'1' FROM pur_inquire WHERE 1=1 ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("numPurOrderMoneySanec")){//numPurOrderMoneySanec 查询 ‘本年采购订单总金额’      或者 //numPurOrderMoneySanec ‘根据传入时间集合查询：采购订单总金额’
			hql.append(" select IFNULL(sum(totalAmount),0),'1' from pur_order WHERE 1=1 ");  
			if(hsMap.containsKey("tenantId")){
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("completedOrder&Money")){//completedOrder&Money 查询 ‘本年已完成采购订单数’和‘本年已完成采购订单金额’ Integer在前，Double在后
			hql.append(" select count(*),IFNULL(sum(totalAmount),0) from pur_order WHERE 1=1 and status = '3' ");  
			if(hsMap.containsKey("tenantId")){//status  0,待配货  1,代发货 2,配送中 3,已完成  4,待分拣
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().equals("OnPassageOrder")){//OnPassageOrder 查询 ‘本年在途采购订单数’
			hql.append(" select count(*),'1' from pur_order WHERE 1=1 and status in('0','1','2','4') ");  
			if(hsMap.containsKey("tenantId")){//status  0,待配货  1,代发货 2,配送中 3,已完成  4,待分拣
				hql.append(" and tenantId='"+hsMap.get("tenantId")+"'");  
			}
			if(hsMap.containsKey("companyInnerCode")){
				hql.append(" and companyInnerCode='"+hsMap.get("companyInnerCode")+"'");  
			}
			@SuppressWarnings("unchecked")  
			ArrayList<String> object = (ArrayList<String>)hsMap.get("timeArr");          
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(object.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size()-1))));
		}
		return hql;
	}
	/** 一般查询视图的sql，如折线图    @throws ParseException **/
	public StringBuilder slowQueryPurchaseView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("qPurchasePlanView")){//qPurchasePlanView 查询 ‘30天采购计划订单数’
			hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M ,count(*) as sl FROM pur_plan WHERE isReport = '0' ");
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" GROUP BY M ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qCompletedOrderView")){//qCompletedOrderView 查询 ‘30天已完成采购订单’
			hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M ,count(*) as sl FROM pur_order WHERE status = '3' ");
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" GROUP BY M ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("qOnPassageOrderView")){//qOnPassageOrderView 查询 ‘30天在途采购订单
			hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M ,count(*) as sl FROM pur_order WHERE status in('0','1','2','4') ");
			hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" GROUP BY M ");
		}
		return hql;
	}
	/** 一般查询视图的sql，如关于每天钱的柱状图    @throws ParseException **/
	public StringBuilder slowQueryPurchaseMoneyView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M ,IFNULL(sum(totalAmount),0) as sl FROM pur_order WHERE status = '3' ");
		hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));//最近30日已完成采购订单金额趋势柱图
		hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" GROUP BY M ");
		return hql;
	}
	/** 采购明细 采购走势图 查询    @throws ParseException **/
	public StringBuilder queryPurchaseMoneyView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d' ) as M ,IFNULL(sum(totalAmount),0) as sl FROM pur_order WHERE status = '3' ");
		hql.append(" and CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));//最近30日已完成采购订单金额趋势柱图
		hql.append(" and CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append(" and supplier_id in("+hsMap.get("supplierID")+") ");
		hql.append((hsMap.containsKey("tenantId"))? " and tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" GROUP BY M ");
		return hql;
	}
	/** 查询TOP '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品数量TOP10’   @throws ParseException **/
	//查询产品数量top10，sql一般注意查询顺序，前面是name后面是num,如 select many.ITEMNAME as sqlname,,IFNULL(sum(many.AMOUNT),0) as sqlnum from ...
	public StringBuilder slowQueryPurchaseItemQuantityTop(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" select many.ITEMNAME as sqlname,IFNULL(sum(many.AMOUNT),0) as sqlnum from pur_orderlineitem  many INNER JOIN pur_order toone on many.PURCHASEORDER_ID = toone.id "); 
		hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and toone.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" and many.AMOUNT is not null and  many.AMOUNT <> '' "); 
		hql.append(" and many.price is not null and  many.price <> '' ");
		hql.append(" and many.itemCode is not null and  many.itemCode <> '' ");
		hql.append(" and many.ITEMNAME is not null and  many.ITEMNAME <> '' ");
		hql.append(" group by many.itemCode ");
		hql.append(" ORDER BY sqlnum desc ");
		hql.append(" LIMIT 0,"+(hsMap.get("topNum")+"").toString()+" ");
		return hql;
	}
	/** 查询TOP '采购统计仪表盘'的 视图 的相关数据‘采购订单中:采购产品金额TOP10 查询 查询’   @throws ParseException **/
	public StringBuilder slowQueryPurchaseItemMoneyTop(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" select A.ITEMNAME as sqlname ,sum(A.tmoney) as sqlnum from( "); 
		hql.append(" select many.itemCode,many.ITEMNAME,(many.AMOUNT * many.price) as tmoney from pur_orderlineitem  many INNER JOIN pur_order toone on many.PURCHASEORDER_ID = toone.id "); 
		hql.append(" and toone.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and toone.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and toone.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" and many.AMOUNT is not null and  many.AMOUNT <> '' "); 
		hql.append(" and many.price is not null and  many.price <> '' ");
		hql.append(" and many.itemCode is not null and  many.itemCode <> '' ");
		hql.append(" and many.ITEMNAME is not null and  many.ITEMNAME <> '' ");
		hql.append(" )A ");
		hql.append(" group by A.itemCode ");
		hql.append(" ORDER BY sqlnum desc ");
		hql.append(" LIMIT 0,"+(hsMap.get("topNum")+"").toString()+" ");
		return hql;
	}
	/** 慢加载：采购订单中:供应商采购金额排行TOP10 查询json数据   @throws ParseException **/
	public StringBuilder slowQueryPurOrderSupplierMoneyTop(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" select toone.name as sqlname , IFNULL(sum(many.totalAmount),0) as sqlnum from pur_order  many INNER JOIN srm_supplier toone on "); 
		hql.append(" many.SUPPLIER_ID=toone.id ");
		hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and many.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" AND toone.name IS NOT NULL AND toone.name <> '' ");
		hql.append(" group by many.SUPPLIER_ID ");
		hql.append(" ORDER BY sqlnum desc ");
		hql.append(" LIMIT 0,"+(hsMap.get("topNum")+"").toString()+" ");
		return hql;
	}
	/** 采购智能分析>采购成本分析>供应商采购排行Top10  &和& 物料采购排行Top10   @throws ParseException **/
	public StringBuilder querySupplierOrderMoneyTop(Map<String, Object> hsMap) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("SupplierOrderMoneyTop")){//SupplierOrderMoneyTop 查询  采购智能分析>采购成本分析>供应商采购排行Top10
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
			hql = new StringBuilder();
			hql.append(" select toone.name as sqlname , ifnull(sum(many.totalamount),0) as sqlnum from pur_order  many inner join srm_supplier toone on "); 
			hql.append(" many.supplier_id=toone.id ");
			hql.append(" and many.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and many.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and many.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and toone.name is not null and toone.name <> '' ");
			hql.append(" and toone.id in("+hsMap.get("sqlinsupplierID")+") ");
			hql.append(" group by many.SUPPLIER_ID ");
			hql.append(" order by sqlnum desc ");
			/** /如果有PieChartTop这个键值对，就查询饼图形式TOP(饼图形式需要超过10时用其他占比处理)，否则查询柱图形式Top(柱图形式只取Top10) **/
			if(!hsMap.containsKey("PieChartTop")){
				hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
			}
		}else if((hsMap.get("controlSQL")+"").toString().equals("MaterielOrderMoneyTop")){//MaterielOrderMoneyTop 查询  采购智能分析>采购成本分析>物料采购排行Top10
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
			hql = new StringBuilder();
			hql.append(" select many.itemname as sqlname,(many.amount *  many.price) as sqlnum from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id = toone.id ");
			hql.append((hsMap.containsKey("tenantId"))? " and toone.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and many.itemname is not null and many.itemname <> '' and many.itemcode is not null and many.itemcode <> '' "); 
			hql.append(" and many.price is not null and many.price <> '' and many.amount is not null and many.amount <> '' "); 
			hql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and toone.supplier_id in("+hsMap.get("sqlinsupplierID")+") ");
			hql.append(" group by many.itemCode ");
			hql.append(" order by sqlnum desc ");
			/** /如果有PieChartTop这个键值对，就查询饼图形式TOP(饼图形式需要超过10时用其他占比处理)，否则查询柱图形式Top(柱图形式只取Top10) **/
			if(!hsMap.containsKey("PieChartTop")){
				hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
			}
		}else if((hsMap.get("controlSQL")+"").toString().equals("ItemCatalogMoneyTop")){//ItemCatalogMoneyTop 查询  采购智能分析>类型结构分析>物料类别采购排行Top10(方式1指定一级分类:和方式2混排所有二级分类:)
			@SuppressWarnings("unchecked")
			ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
			hql = new StringBuilder();
			hql.append(" select toonemulu.name,a.sqlnum from( ");
			hql.append(" select toonea.itemcatalog_id as sqlitemcatalog_id ,sum(many.amount * many.price) as sqlnum ");
			hql.append(" from pur_orderlineitem many inner join pur_order toone on many.purchaseorder_id = toone.id "); 
			hql.append(" inner join mdm_item toonea on many.itemcode = toonea.code ");
			/**  说明：如果itemCatalogID不为空，就方式1指定一级分类Top;否则如果twolevelCatalogID不为空，就方式2混排所有二级分类;否则errorNoquery错误，不查询了 **/
			if(hsMap.containsKey("itemCatalogID")){
				/** 开始分类sql处理 方式1指定一级分类  **/
				hql.append(" and many.itemcode in( ");
				hql.append(" 	select code from mdm_item where itemCatalog_ID in( ");
				hql.append(" 		select id from MDM_ITEMCATALOG where parent_id ="+hsMap.get("itemCatalogID")+" ");
				hql.append(" 	 ) ");
				hql.append("  ) ");
				/** 结果分类sql处理 **/
			}else if(hsMap.containsKey("twolevelCatalogID")){
				hql.append(" ");//接着查询
			}else if(hsMap.containsKey("errorNoquery")){
				hql.append(" and 1=3 ");//错误，不查询了；
			}
			hql.append((hsMap.containsKey("tenantId"))? " and toone.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and toone.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and many.itemname is not null and many.itemname <> '' and many.itemcode is not null and many.itemcode <> '' "); 
			hql.append(" and many.price is not null and many.price <> '' and many.amount is not null and many.amount <> '' "); 
			hql.append(" and toone.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and toone.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append(" and toone.supplier_id in("+hsMap.get("sqlinsupplierID")+") ");
			hql.append(" group by tooneA.itemCatalog_ID ");
			hql.append(" order by sqlnum desc ");
			hql.append(" limit 0,"+(hsMap.get("topNum")+"").toString()+" ");
			hql.append(" )a inner join mdm_itemcatalog toonemulu on a.sqlitemcatalog_id = toonemulu.id  and toonemulu.name is not null and toonemulu.name  <> '' ");
		}
		return hql;
	}
	/** 慢加载：采购订单中:供应商采购订单数排行TOP10 查询json @throws ParseException  **/
	public StringBuilder slowQueryPurOrderSupplierQuantityTop(Map<String, Object> hsMap) throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		hql.append(" select toone.name as sqlname , count(*) as sqlnum from pur_order  many INNER JOIN srm_supplier toone on "); 
		hql.append(" many.SUPPLIER_ID=toone.id ");
		hql.append(" and many.CREATETIME >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		hql.append(" and many.CREATETIME <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		hql.append((hsMap.containsKey("tenantId"))? " and many.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and many.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" AND toone.name IS NOT NULL AND toone.name <> '' ");
		hql.append(" group by many.SUPPLIER_ID ");
		hql.append(" ORDER BY sqlnum desc ");
		hql.append(" LIMIT 0,"+(hsMap.get("topNum")+"").toString()+" ");
		return hql;
	}
	/** 采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  **/
	public StringBuilder queryStructurePurchaseTopView(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")   
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");        
		String supplierID = (String)hsMap.get("supplierID");
		/** 不论地域，客户，营业员是否为空，都拼装内部sql语句，只取其DISTINCT(m.id)  **/
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(" select DISTINCT(m.id) from pur_order m "); 
		if(StringUtils.isNotEmpty(supplierID)){
			selectSql.append(" inner join srm_supplier tb on m.supplier_id = tb.id and m.supplier_id='"+supplierID+"'");
		}else {
			selectSql.append(" inner join srm_supplier tb on m.supplier_id = tb.id and tb.status='3' ");
			selectSql.append((hsMap.containsKey("tenantId"))? " and tb.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			selectSql.append((hsMap.containsKey("companyInnerCode"))? " and tb.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		}
		selectSql.append(" where 1=1 "); 
		selectSql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		selectSql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		selectSql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
		selectSql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("PurchaseStructureBarAndPieTop")){//PurchaseStructureBarAndPieTop  采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名
			hql.append(" select  ifnull( sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) , 0) as sqlnum,'1' from pur_orderlineitem m inner join ("+selectSql.toString()+") t on m.purchaseorder_id = t.id  inner join mdm_item tb on m.itemcode = tb.code  ");  
			hql.append(" and tb.name is not null  and tb.name <> '' ");
			hql.append(" and tb.itemcatalog_id ='"+hsMap.get("itemcatalogID")+"' ");
			hql.append(" and m.itemname is not null and m.itemname <> '' and m.itemcode is not null and m.itemcode <> '' "); 
			hql.append(" and m.price is not null and m.price <> '' and m.amount is not null and m.amount <> '' "); 
		}
		return hql;
	}
	/** 库存管理>库存报表>现存物料价值分布表 列表查询 **/
	public StringBuilder searchStockHasMoneyDistribution(Map<String, Object> hsMap) throws Exception {
		String itemID = (String)hsMap.get("itemID");
		String invWarehouseID = (String)hsMap.get("invWarehouseID");
		StringBuilder hql = new StringBuilder();
		hql.append(" select m.itemname,m.itemcode,m.price,m.avaquantity,sum((m.avaquantity * m.price)) as n ");
		hql.append(" from inv_inventory m where 1=1 ");
		hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
		hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		hql.append(" and m.avaquantity>0 and m.price>0 ");
		hql.append(" and m.warehousecode is not null and m. warehousecode <> '' ");
		hql.append(" and m.warehouse is not null and m.warehouse <> '' ");
		hql.append(" and m.itemname is not null and m.itemname <> '' ");
		hql.append(" and m.itemcode is not null and m.itemcode <> ''  and m.createtime is not null ");
		if(StringUtils.isNotEmpty(itemID)) {
			hql.append(" and m.itemcode='"+itemID+"'");
		}
		if(StringUtils.isNotEmpty(invWarehouseID)) {
			hql.append(" and m.warehousecode='"+invWarehouseID+"'");
		}
		hql.append(" group by m.itemcode order by n desc ");
		return hql;
	}
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品 入/出 库趋势图  数据查询   本方法只统计  **/
	public StringBuilder queryDataViewStockInOut(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("queryDataStockInOutNumber")){//queryDataStockInOutNumber 计算最近30日商品 入或出 库趋势图的数量
hql.append(" select DATE_FORMAT(m.createtime, '%Y-%m-%d' ) as ymddate ,IFNULL(sum(m.quantity),0) as sl from inv_stockrecordlines m inner join inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" and t.type = '2' and t.invwarehouse_id is not null  and m.quantity > 0 and m.price > 0  ");
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" "+(String)hsMap.get("inOrOutStockSQL")+" ");
			hql.append(" group by ymddate ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("queryDataStockInOutMoney")){//queryDataStockInOutMoney 计算最近30日商品入库趋势图的金额
hql.append(" select DATE_FORMAT(m.createtime, '%Y-%m-%d' ) as ymddate ,IFNULL(sum(m.price),0) as sl from inv_stockrecordlines m inner join inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" and t.type = '2' and t.invwarehouse_id is not null and m.quantity > 0 and m.price > 0 ");
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" "+(String)hsMap.get("inOrOutStockSQL")+" ");  
			hql.append(" group by ymddate ");
		}
		return hql;
	}
	/**  查询sql的返回一个数值(门店库存报表 > 现存物料SKU数,本月过期物料量,库存不足物料SKU数,库存积压物料SKU数(库存积压:现存量>=200;库存不足:现存量<20;))  **/  
	public StringBuilder queryStockGoodsNum(Map<String, Object> hsMap) throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<String> objTimeArr = (ArrayList<String>)hsMap.get("timeArr");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("existingGoodsSKUnum")){//existingGoodsSKUnum 查询现存物料SKU数(controlSQL)
			hql.append(" select count(DISTINCT(m.skuCode)), '1' from inv_inventory m where 1=1 ");
			hql.append(" and m.avaquantity > 0 and m.skucode is not null and m.skucode <> '' and m.createtime is not null ");
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("overdueThisMonthNum")){//overdueThisMonthNum 查询本月过期物料量(controlSQL)
			hql.append(" select IFNULL(sum(m.avaquantity),0), '1' from inv_inventory m where 1=1 ");
			hql.append(" and m.avaquantity > 0 and m.skucode is not null and m.skucode <> '' and m.createtime is not null ");
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and m.massunitEndTime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			hql.append(" and m.massunitEndTime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
		}else if((hsMap.get("controlSQL")+"").toString().startsWith("insufficientGoodsSKUnum!")){//insufficientGoodsSKUnum!20    查询库存不足物料SKU数20代表库存小于20(controlSQL)
			String markStr = (hsMap.get("controlSQL")+"").toString();
			String[] splitArr = markStr.split("!");
			hql.append(" select count(DISTINCT(m.skuCode)), '1' from inv_inventory m where 1=1 ");
			hql.append(" and m.avaquantity < "+splitArr[1]+" and m.skucode is not null and m.skucode <> '' and m.createtime is not null ");
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		}else if((hsMap.get("controlSQL")+"").toString().startsWith("backlogGoodsSKUnum!")){//backlogGoodsSKUnum!200    查询库存积压物料SKU数200代表库存数量大于200(controlSQL)
			String markStr = (hsMap.get("controlSQL")+"").toString();
			String[] splitArr = markStr.split("!");
			hql.append(" select count(DISTINCT(m.skuCode)), '1' from inv_inventory m where 1=1 ");
			hql.append(" and m.avaquantity >= "+splitArr[1]+" and m.skucode is not null and m.skucode <> '' and m.createtime is not null ");
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
		}
		return hql;
	}
	/** 这里计算的是所有仓库的 库龄结构:计算'库龄结构'(自定义查询'30天以下,30天-60天,60天到180天,180天以上'的入库数量)   **/
	public Map<String, Object> calculationStockAge(Map<String, Object> hsMap) throws ParseException {
		Map<String,Object> hsMapReturn = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("queryGroupByCustomTime")){//queryGroupByCustomTime构造自定义groupby时间问题
			ArrayList<String>  arrtime = new ArrayList<String>();
			int[] zoneArrows = {(-0+1),(-30+1),(-60+1),(-180+1)};
			for(int i:zoneArrows) {
				 arrtime.add(MyTool.getAppointDateString(i));
			}
			StringBuffer sb = new StringBuffer();
			if(arrtime.size() >=3) {
				 for(int x=0;x<arrtime.size()-1;x++) {
					 if(x==0) {
						sb.append(" select IFNULL(sum(m.quantity),0),'1' as sl from inv_stockrecordlines m inner join inv_stockrecords t ON m.invstockrecords_id = t.id ");
						sb.append(" and t.type = '2' and t.invwarehouse_id is not null  and m.quantity > 0 and m.price > 0 and t.flag = '1' and m.createtime is not null  ");
						sb.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
						sb.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
						sb.append(" and m.createtime >= "+MyTool.StringUseToSql(arrtime.get(x+1))+" and m.createtime < "+MyTool.StringUseToSql(arrtime.get(x)) ); 
					 }else {
					 	sb.append(" union all select IFNULL(sum(m.quantity),0),'1' as sl from inv_stockrecordlines m inner join inv_stockrecords t ON m.invstockrecords_id = t.id ");
						sb.append(" and t.type = '2' and t.invwarehouse_id is not null  and m.quantity > 0 and m.price > 0 and t.flag = '1' and m.createtime is not null  ");
						sb.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
						sb.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
						sb.append(" and m.createtime >= "+MyTool.StringUseToSql(arrtime.get(x+1))+" and m.createtime < "+MyTool.StringUseToSql(arrtime.get(x)) ); 
					 }
					 if(x == arrtime.size()-2) {
						sb.append(" union all select IFNULL(sum(m.quantity),0),'1' as sl from inv_stockrecordlines m inner join inv_stockrecords t ON m.invstockrecords_id = t.id ");
						sb.append(" and t.type = '2' and t.invwarehouse_id is not null  and m.quantity > 0 and m.price > 0 and t.flag = '1' and m.createtime is not null  ");
						sb.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
						sb.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
						sb.append(" and m.createtime < "+MyTool.StringUseToSql(arrtime.get(x+1)) );
					 }
				 } 
			 }
			 hql.append(sb.toString());
		}
		hsMapReturn.put("hqlStringBuilder", hql.toString());
		String[] hqlArr = {"30天以下","30天-60天","60天到180天","180天以上"};
		hsMapReturn.put("hqlArr", hqlArr);
		return hsMapReturn;
	}
	/** 查询:收货商品数量,收货SKU数,收货订单数 **/
	public StringBuilder queryBlockAbc(Map<String, Object> hsMap) throws ParseException {
		String queryTime = (String)hsMap.get("queryTime");
		String channelDistributorID = (String)hsMap.get("channelDistributorID");
		String warehouseID = (String)hsMap.get("warehouseID");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("inStoreQuantity")){//收货商品数量 inStoreQuantity
			StringBuffer sBuffer = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sBuffer = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sBuffer.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sBuffer.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}
			hql.append(" SELECT IFNULL(sum(m.quantity), 0) AS sl,'1' FROM inv_stockrecordlines m INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" AND t.type = '2'  AND m.quantity > 0   AND m.price > 0  ");
			hql.append(" AND t.invwarehouse_id IS NOT NULL ");
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(queryTime));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(queryTime)));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" AND t.flag = '1' ");
			hql.append(" "+sBuffer.toString());
		}else if((hsMap.get("controlSQL")+"").toString().equals("inSKUquantity")){//收货SKU数&收货订单数 inSKUquantity
			StringBuffer sBuffer = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sBuffer = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sBuffer.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sBuffer.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}
			hql.append(" SELECT count(DISTINCT(m.skuCode)) AS sl,count(t.id) FROM inv_stockrecordlines m INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" AND t.type = '2'  AND m.quantity > 0   AND m.price > 0  ");
			hql.append(" AND t.invwarehouse_id IS NOT NULL ");
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(queryTime));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(queryTime)));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" AND t.flag = '1' ");
			hql.append(" "+sBuffer.toString());
		}else if((hsMap.get("controlSQL")+"").toString().equals("inTimeLength")){//收货平均时长 inTimeLength
			StringBuffer sTail = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sTail = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sTail.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sTail.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append(" select UNIX_TIMESTAMP(m.createtime),'1' FROM inv_stockrecordlines m INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			sBuffer.append(" and t.type = '2'  AND m.quantity > 0   AND m.price > 0  ");
			sBuffer.append(" and t.invwarehouse_id IS NOT NULL ");
			sBuffer.append(" and m.createtime >= "+ MyTool.StringUseToSql(queryTime));
			sBuffer.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(queryTime)));
			sBuffer.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			sBuffer.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			sBuffer.append(" and t.flag = '1' ");
			sBuffer.append(" "+sTail.toString());
			hql.append(" ("+sBuffer.toString()+" order by m.createtime desc limit 1 "+") union all "+" ("+sBuffer.toString()+" order by m.createtime asc limit 1 "+") ");
		}
		return hql;
	}
	/**查询'进销存概览'的收货商品总数折线图和收货SKU数折线图 **/
	@SuppressWarnings("unchecked")
	public StringBuilder queryViewBrokenLineData(Map<String, Object> hsMap) throws ParseException {
	    ArrayList<String> timeArr = (ArrayList<String>)hsMap.get("timeArr");
		String channelDistributorID = (String)hsMap.get("channelDistributorID");
		String warehouseID = (String)hsMap.get("warehouseID");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("inStoreQuantityBrokenLineData")){//inStoreQuantityBrokenLineData 收货商品数量 折线图数据 
			StringBuffer sBuffer = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sBuffer = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sBuffer.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sBuffer.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}  
			hql.append(" SELECT DATE_FORMAT(m.createtime, '%Y-%m-%d' ) as ymddate ,IFNULL(sum(m.quantity),0) as sl FROM inv_stockrecordlines m "
					+ "INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" AND t.type = '2'  AND m.quantity > 0   AND m.price > 0  ");
			hql.append(" AND t.invwarehouse_id IS NOT NULL "); 
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.flag = '1' ");
			hql.append(" "+sBuffer.toString()+" ");
			hql.append(" group by ymddate ");
		}else if((hsMap.get("controlSQL")+"").toString().equals("inSKUquantityBrokenLineData")){//inSKUquantityBrokenLineData 收货SKU数量 折线图数据 
			StringBuffer sBuffer = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sBuffer = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sBuffer.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sBuffer.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}  
			hql.append(" SELECT DATE_FORMAT(m.createtime, '%Y-%m-%d' ) as ymddate ,count(DISTINCT(m.skuCode)) as sl FROM inv_stockrecordlines m "
					+ "INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" AND t.type = '2'  AND m.quantity > 0   AND m.price > 0  ");
			hql.append(" AND t.invwarehouse_id IS NOT NULL "); 
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(timeArr.get(0)));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.flag = '1' ");
			hql.append(" "+sBuffer.toString()+" ");
			hql.append(" group by ymddate ");
		}
		return hql;
	}
	/** 查询金额比例top柱图和top饼图 **/
	public StringBuilder queryViewMoneyTopData(Map<String, Object> hsMap) throws ParseException {
		String queryTime = (String)hsMap.get("queryTime");
		String channelDistributorID = (String)hsMap.get("channelDistributorID");
		String warehouseID = (String)hsMap.get("warehouseID");
		StringBuilder hql = new StringBuilder();
		if((hsMap.get("controlSQL")+"").toString().equals("GoodsReceiptMoneyTop")){//GoodsReceiptMoneyTop 查询金额比例top柱图和top饼图
			StringBuffer sBuffer = new StringBuffer();
			if(StringUtils.isEmpty(warehouseID) && StringUtils.isEmpty(channelDistributorID) ) {
				sBuffer = new StringBuffer();
			}else if(StringUtils.isNotEmpty(channelDistributorID)) {
				sBuffer.append(" and t.invwarehouse_id in ( select id from inv_warehouse where channelDistributor_ID ='"+channelDistributorID+"' ) ");
			}else if(StringUtils.isNotEmpty(warehouseID)) {
				sBuffer.append(" and t.invwarehouse_id ='"+warehouseID+"' ");
			}  
			hql.append(" SELECT m.skuCode ,IFNULL(sum(m.price),0) as sl FROM inv_stockrecordlines m "
					+ "INNER JOIN inv_stockrecords t ON m.invstockrecords_id = t.id ");
			hql.append(" AND t.type = '2'  AND m.quantity > 0   AND m.price > 0  and m.skuCode is not null and m.skuCode <> '' ");
			hql.append(" AND t.invwarehouse_id IS NOT NULL "); 
			hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(queryTime));
			hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(queryTime)));
			hql.append((hsMap.containsKey("tenantId"))? " and m.tenantId='"+hsMap.get("tenantId")+"'" : " and 1=2 ");
			hql.append((hsMap.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+hsMap.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.flag = '1' ");
			hql.append(" "+sBuffer.toString()+" ");
			hql.append(" group by m.skuCode order by sl desc ");
		}
		return hql;
	}
}
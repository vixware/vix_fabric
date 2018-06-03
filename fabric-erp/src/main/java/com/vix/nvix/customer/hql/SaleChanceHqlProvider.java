package com.vix.nvix.customer.hql;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.properties.util.MyTool;
import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class SaleChanceHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}
	/** 查询 客户关系管理 > 售前管理 > 销售机会统计 饼图分布  ***/
	@SuppressWarnings("unchecked")
	public StringBuilder queryPieView(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("saleChanceEmployee")){//saleChanceEmployee销售机会负责人分布
			hql.append(" select t.name, count(*) n,m.employee_id from ");
			hql.append(" crm_salechance m inner join hr_org_employee t on m.employee_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.isDeleted != '1' ");    
			hql.append(" group by m.employee_id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("saleChanceSaleSource")){//saleChanceSaleSource销售机会来源分布
			hql.append(" select t.name, count(*) n,t.id from ");
			hql.append(" crm_salechance m inner join crm_b_salesource t on m.saleSource_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.isDeleted != '1' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("saleChanceSaleType")){//saleChanceSaleType销售机会类型分布
			hql.append(" select t.name, count(*) n, t.id from ");
			hql.append(" crm_salechance m inner join crm_b_saletype t on m.saleType_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.isDeleted != '1' ");    
			hql.append(" group by t.id  order by n desc ");
		}else if((params.get("type")+"").toString().equals("activitySaleActivity")){//activitySaleActivity销售活动类型分布
			hql.append(" select t.name, count(*) n, t.id from ");
			hql.append(" CRM_ACTIVITY m inner join CRM_B_SALEACTIVITY t on m.saleActivity_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append(" and t.name is not null and t.name <> '' and m.isDeleted != '1' and m.startDate is not null and m.endDate is not null ");
			if(params.containsKey("timeArr")){
				hql.append(" and m.id not in(  ");
				hql.append(" select id from CRM_ACTIVITY where 1=1 ");
				hql.append((params.containsKey("tenantId"))? " and tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
				hql.append((params.containsKey("companyInnerCode"))? " and companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
				hql.append(" and isDeleted != '1' and startDate is not null and endDate is not null ");
				if(params.containsKey("timeArr")){
					ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
					String startTime =MyTool.StringUseToSql(objTimeArr.get(0));
					String endTime =MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1)));
					hql.append(" AND ( ");
					hql.append(" (startDate < "+ startTime +"  and endDate < "+ endTime +" ) ");
					hql.append(" or  ");
					hql.append(" (startDate > "+ startTime +"  and endDate > "+ endTime +" ) ");
					hql.append(" ) ");
				 }
				hql.append(" )  ");
			}
			hql.append(" group by t.id  order by n desc ");
		}
		return hql;
	}
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	@SuppressWarnings("unchecked")
	public StringBuilder discoveryTimeMonthViewQuery(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("discoveryTimeMonthStatistics")){//discoveryTimeMonthStatistics  机会发现时间月份统计
			hql.append(" select DATE_FORMAT(findDate, '%Y-%m' ) as D,count(*) n from ");
			hql.append(" crm_salechance m where 1=1 ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.isDeleted != '1' ");    
			hql.append(" group by D order by D asc ");
		}else if((params.get("type")+"").toString().equals("preOrderDateMonthStatistics")){//preOrderDateMonthStatistics 机会预计签单月份统计 
			hql.append(" select DATE_FORMAT(preOrderDate, '%Y-%m' ) as D,count(*) n from ");
			hql.append(" crm_salechance m where 1=1 ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.preOrderDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.preOrderDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.isDeleted != '1' ");    
			hql.append(" group by D order by D asc ");
		} else if((params.get("type")+"").toString().equals("createtimeMonthStatistics")){//createtimeMonthStatistics  机会创建时间月份统计
			hql.append(" select DATE_FORMAT(createtime, '%Y-%m' ) as D,count(*) n from ");
			hql.append(" crm_salechance m where 1=1 ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.createtime >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.createtime <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.isDeleted != '1' ");    
			hql.append(" group by D order by D asc ");
		} else if( (params.get("type")+"").toString().startsWith("ActivityType!") ){//ActivityType!表示查询 '销售活动类型/月份分布'  
			String sqlStrID = "";
			String tempStr = (params.get("type")+"").toString();
			if(StringUtils.isNotEmpty(tempStr) && tempStr.contains("!")) {
				String[] strArr = tempStr.split("!");
				if(strArr.length ==2 ) {
					sqlStrID = (strArr[1].equals("all") ? "":strArr[1]);
				}
			}
			hql.append(" select DATE_FORMAT(m.date, '%Y-%m' ) as D,count(*) n from ");
			hql.append(" crm_activity m inner join crm_b_saleactivity t on m.saleactivity_id = t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.date >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.date <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.isDeleted != '1' AND m.startDate IS NOT NULL AND m.endDate IS NOT NULL AND t. NAME IS NOT NULL AND t. NAME <> '' ");
			hql.append( (StringUtils.isNotEmpty(sqlStrID)) ? " and m.saleactivity_id='"+sqlStrID+"'" : " ");
			hql.append(" group by D order by D asc ");
		}
		return hql;
	}
	/** 客户关系管理 > 销售跟踪 > 负责人/机会状态统计 视图查询 **/
	@SuppressWarnings("unchecked")
	public StringBuilder chargerDivisionStatusViewQuery(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("qChargerDivisionStatus")){//qChargerDivisionStatus  负责人/机会状态统计
			StringBuffer sqlA = new StringBuffer();
			sqlA.append(" select name,id as aid from hr_org_employee where empType='S' and name is not null and name <> '' ");
			sqlA.append((params.containsKey("tenantId"))? " and tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlA.append((params.containsKey("companyInnerCode"))? " and companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			StringBuffer sqlB = new StringBuffer();
			sqlB.append(" select m.employee_id,count(*) as sl from ");
			sqlB.append(" crm_salechance m inner join hr_org_employee t on m.employee_id = t.id ");
			sqlB.append(" and m.saleChanceStatus_id = '"+params.get("legendArrId")+"' " );
			sqlB.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlB.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				sqlB.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				sqlB.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			sqlB.append(" and m.isDeleted != '1' ");    
			sqlB.append(" group by t.id ");
			hql.append(" select A.name,IFNULL(B.sl,0) from("+sqlA.toString()+")A left  join ("+sqlB.toString()+")B ON A.aid = B.employee_id order by A.aid desc ");
		}else if((params.get("type")+"").toString().equals("queryMaxNum")){//queryMaxNum  负责人/机会状态统计的查询最大值 和 负责人/机会阶段统计最大值 等 都是这个sql
			StringBuffer sqlA = new StringBuffer();
			sqlA.append(" select name,id as aid from hr_org_employee where empType='S' and name is not null and name <> '' ");
			sqlA.append((params.containsKey("tenantId"))? " and tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlA.append((params.containsKey("companyInnerCode"))? " and companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			StringBuffer sqlB = new StringBuffer();
			sqlB.append(" select m.employee_id,count(*) as sl from ");
			sqlB.append(" crm_salechance m inner join hr_org_employee t on m.employee_id = t.id ");
			sqlB.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlB.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				sqlB.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				sqlB.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			sqlB.append(" and m.isDeleted != '1' ");    
			sqlB.append(" group by t.id ");
			hql.append(" select IFNULL(max(IFNULL(B.sl, 0)), 0),'1' from("+sqlA.toString()+")A left  join ("+sqlB.toString()+")B ON A.aid = B.employee_id order by A.aid desc ");
		}else if((params.get("type")+"").toString().equals("qChargerDivisionSaleStage")){//qChargerDivisionSaleStage  负责人/机会阶段统计
			StringBuffer sqlA = new StringBuffer();
			sqlA.append(" select name,id as aid from hr_org_employee where empType='S' and name is not null and name <> '' ");
			sqlA.append((params.containsKey("tenantId"))? " and tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlA.append((params.containsKey("companyInnerCode"))? " and companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			StringBuffer sqlB = new StringBuffer();
			sqlB.append(" select m.employee_id,count(*) as sl from ");
			sqlB.append(" crm_salechance m inner join hr_org_employee t on m.employee_id = t.id ");
			sqlB.append(" and m.saleStage_id = '"+params.get("legendArrId")+"' " );
			sqlB.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlB.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				sqlB.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				sqlB.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			sqlB.append(" and m.isDeleted != '1' ");    
			sqlB.append(" group by t.id ");
			hql.append(" select A.name,IFNULL(B.sl,0) from("+sqlA.toString()+")A left  join ("+sqlB.toString()+")B ON A.aid = B.employee_id order by A.aid desc ");
		}else if((params.get("type")+"").toString().equals("findSaleChancePossibility") 
				|| (params.get("type")+"").toString().equals("findSaleChanceStagePossibility")  ){//findSaleChancePossibility  机会可能性/状态统计 & findSaleChanceStagePossibility 机会可能性/阶段统计
			String strMspot =" m.saleChanceStatus_id ";
			if( (params.get("type")+"").toString().equals("findSaleChancePossibility")  ) {
				strMspot =" m.saleChanceStatus_id ";
			}else if( (params.get("type")+"").toString().equals("findSaleChanceStagePossibility")  ) {
				strMspot =" m.saleStage_id ";
			}
			StringBuffer sqlA = new StringBuffer();
			sqlA.append(" select '10%' as a,1 as b union all select '20%' as a,2 as b union all select '30%' as a,3 as b union all select '40%' as a,4 as b union all select '50%' as a,5 as b ");
			sqlA.append(" union all select '60%' as a,6 as b union all select '70%' as a,7 as b union all select '80%' as a,8 as b union all select '90%' as a,9 as b union all select '100%' as a,10 as b "); 
			StringBuffer sqlB = new StringBuffer();
			sqlB.append(" SELECT(CASE WHEN m.possibility >= 0 AND m.possibility <= 10 THEN '10%' ");
			sqlB.append(" WHEN m.possibility > 10 AND m.possibility <= 20 THEN '20%' WHEN m.possibility > 20 AND m.possibility <= 30 THEN '30%' WHEN m.possibility > 30 AND m.possibility <= 40 THEN '40%' "); 
			sqlB.append(" WHEN m.possibility > 40 AND m.possibility <= 50 THEN '50%' WHEN m.possibility > 50 AND m.possibility <= 60 THEN '60%' WHEN m.possibility > 60 AND m.possibility <= 70 THEN '70%' ");
			sqlB.append(" WHEN m.possibility > 70 AND m.possibility <= 80 THEN '80%' WHEN m.possibility > 80 AND m.possibility <= 90 THEN '90%' WHEN m.possibility > 90 THEN '100%' ELSE IFNULL(m.possibility,'10%') END ");
			sqlB.append(" ) as num , count(*) AS cou FROM ");
			sqlB.append(" crm_salechance m  where 1=1 ");
			sqlB.append(" and "+strMspot+" = '"+params.get("legendArrId")+"' " );
			sqlB.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			sqlB.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
			ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
			sqlB.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
			sqlB.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			sqlB.append(" and m.isDeleted != '1' group by num ");
			hql.append(" select AB.a,(IFNULL(BB.cou,0)) from("+sqlA.toString()+")AB left join ("+sqlB.toString()+")BB ON AB.a = BB.num order by AB.b asc ");
		}else if((params.get("type")+"").toString().equals("findMaxNum")){//findMaxNum  机会可能性/状态统计 最大值
			hql.append(" select count(*) AS cou,'1' FROM crm_salechance m  where m.isDeleted != '1'  ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
		}
		return hql;
	}
	/** 客户关系管理 > 销售跟踪 > 各阶段机会数量漏斗 **/
	@SuppressWarnings("unchecked")
	public StringBuilder saleChanceStageFunnelQuery(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if( (params.get("type")+"").toString().startsWith("NumFunnel!") //NumFunnel!all NumFunnel!id123 包含NumFunnel!是 各阶段机会数量漏斗查询 ,感叹号后存id
			|| (params.get("type")+"").toString().startsWith("ExpectedMoneyFunnel!") //ExpectedMoneyFunnel!各阶段机会预期金额漏斗	
			){
			String sqlStrID = "";
			String sqlStrQuery = "count(*)";
			if((params.get("type")+"").toString().startsWith("NumFunnel!")) {
				sqlStrQuery = "count(*)";
			}else if((params.get("type")+"").toString().startsWith("ExpectedMoneyFunnel!")) {
				sqlStrQuery = "sum(IFNULL(m.expectedValue,0))";
			}
			String tempStr = (params.get("type")+"").toString();
			if(StringUtils.isNotEmpty(tempStr) && tempStr.contains("!")) {
				String[] strArr = tempStr.split("!");
				if(strArr.length ==2 ) {
					sqlStrID = (strArr[1].equals("all") ? "":strArr[1]);
				}
			}
			hql.append(" select t.name,  "+sqlStrQuery+"  as num from crm_salechance m INNER JOIN CRM_B_SALESTAGE t on m.saleStage_id=t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.findDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.findDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and m.isDeleted != '1' and t.name is not null and t.name <> '' ");
			hql.append( (StringUtils.isNotEmpty(sqlStrID)) ? " and m.saleChanceStatus_id='"+sqlStrID+"'" : " ");
			hql.append(" group by t.id order by num desc ");
		}
		return hql;
	}
	/** 客户关系管理 > 销售跟踪 > 销售活动类型/月份分布相关数组查询 **/
	public StringBuilder activityDivideMonthDrawQuery(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if( (params.get("type")+"").toString().startsWith("ActivityType!") ){//ActivityType!表示查询 '销售活动类型/月份分布'  (返回name数组id数组jsp页面使用)
			String sqlStrID = "";
			String tempStr = (params.get("type")+"").toString();
			if(StringUtils.isNotEmpty(tempStr) && tempStr.contains("!")) {
				String[] strArr = tempStr.split("!");
				if(strArr.length ==2 ) {
					sqlStrID = (strArr[1].equals("all") ? "":strArr[1]);
				}
			}
			hql.append(" select m.name,m.id from CRM_B_SALEACTIVITY m where 1=1 AND m.name IS NOT NULL AND m.name <> '' ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			hql.append( (StringUtils.isNotEmpty(sqlStrID)) ? " and m.id='"+sqlStrID+"'" : " ");
		}
		return hql;
	}
	/** 客户关系管理 > 销售跟踪 > 应收款对应客户排行TOP20... 视图查询 **/
	@SuppressWarnings("unchecked")
	public StringBuilder backPlanAmountCustomerTopQuery(Map<String, Object> params) throws ParseException {
		StringBuilder hql = new StringBuilder();
		if((params.get("type")+"").toString().equals("backSectionPlanAmountCustomerTop")){//backSectionPlanAmountCustomerTop 应收款对应客户排行20或50  
			hql.append(" select t.name, sum(IFNULL(m.amount,0) ) AS sqlnum from crm_backsectionplan m INNER JOIN crm_customeraccount t on m.customerAccount_id=t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.backSectionPlanDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.backSectionPlanDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.backSectionPlanDate is not null  group by t.id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}else if((params.get("type")+"").toString().equals("backSectionPlanAmountOwnerTop")){//backSectionPlanAmountOwnerTop 应收款/回款计划所有者排行20或50 
			hql.append(" select t.name, sum(IFNULL(m.amount,0) ) AS sqlnum from crm_backsectionplan m INNER JOIN HR_ORG_EMPLOYEE t on m.owner_id=t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.backSectionPlanDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.backSectionPlanDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.backSectionPlanDate is not null  group by t.id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}else if((params.get("type")+"").toString().equals("backSectionPlanAmountChargerTop")){//backSectionPlanAmountChargerTop 应收款/回款计划负责人排行20或50 
			hql.append(" select t.name, sum(IFNULL(m.amount,0) ) AS sqlnum from crm_backsectionplan m INNER JOIN HR_ORG_EMPLOYEE t on m.charger_id=t.id ");
			hql.append((params.containsKey("tenantId"))? " and m.tenantId='"+params.get("tenantId")+"'" : " and 1=2 ");
			hql.append((params.containsKey("companyInnerCode"))? " and m.companyInnerCode='"+params.get("companyInnerCode")+"'" : " and 1=2 ");
			if(params.containsKey("timeArr")){
				ArrayList<String> objTimeArr = (ArrayList<String>)params.get("timeArr");
				hql.append(" and m.backSectionPlanDate >= "+ MyTool.StringUseToSql(objTimeArr.get(0)));
				hql.append(" and m.backSectionPlanDate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(objTimeArr.get(objTimeArr.size()-1))));
			}
			hql.append(" and t.name is not null and t.name <> '' and m.backSectionPlanDate is not null  group by t.id  order by sqlnum desc ");
			hql.append(" limit 0,"+(params.get("topNum")+"").toString()+" ");
		}
		return hql;
	}
}

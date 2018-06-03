package com.vix.nvix.customer.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.base.action.BaseAction;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.SaleActivity;
import com.vix.crm.base.entity.SaleChanceStatus;
import com.vix.crm.base.entity.SaleStage;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.customer.service.ISaleChanceStatisticsService;
/** 销售机会统计 **/
@Controller
@Scope("prototype")
public class NvixSaleChanceStatisticsAction extends BaseAction {           

	private static final long serialVersionUID = 1L;
	
	@Autowired
	public ISaleChanceStatisticsService saleChanceStatisticsService;
	private String queryTime;//queryTime是一段字符串就代表其时间如'ThisMonthOT'本月
	private String customerNum;// 客户数量
	private String type;// 维度1
	private String controlSQL;// 维度2
	private String topNum;//排行数字
	private String legendArrId;
	/** 销售机会统计页面 saleChanceStatistics.jsp **/
	@Override
	public String goList() {
		reqSetAttrTwoDateStr();
		return GO_LIST;
	}
	/** 查询 客户关系管理 > 售前管理 > 销售机会统计 饼图分布  ***/
	public void queryPieView() {
		try {
		Map<String, Object> params = new HashMap<String, Object>();
		Employee employee = getEmployee();
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		}
		if (StringUtils.isNotEmpty(queryTime)) {
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			params.put("timeArr",timeArr);
		}
		/**type就是控制sql查询的条件 **/
		params.put("type",type);
		String sBufferJsonToString =(String)saleChanceStatisticsService.queryPieView(params).get("sBufferJsonToString");
		renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 初始化页面的自定义时间的2个时间值,用于初始化页面时显示**/
	public void reqSetAttrTwoDateStr() {
		String[] objMonth = MyTool.getThisMonth_SOnlyAndW();
		getRequest().setAttribute("dateStrFront",objMonth[0]);
		getRequest().setAttribute("dateStrAfter",objMonth[1]);
	}
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 **/
	public String discoveryTimeMonthView() {
		reqSetAttrTwoDateStr();
		return "discoveryTimeMonthView";
	}
	/** 客户关系管理 > 销售跟踪 > 机会预计签单月份统计 **/
	public String preOrderDateMonthView() {
		reqSetAttrTwoDateStr();
		return "preOrderDateMonthView";
	}
	/** 客户关系管理 > 销售跟踪 > 机会创建时间月份统计 **/
	public String createtimeMonthView() {
		reqSetAttrTwoDateStr();
		return "createtimeMonthView";
	}
	/** 客户关系管理 > 销售跟踪 > 机会发现时间月份统计 视图查询 **/
	public void discoveryTimeMonthViewQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
            String sBufferJsonToString =(String)saleChanceStatisticsService.discoveryTimeMonthViewQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 销售机会负责人分布 **/
	public String saleChanceChargerView() {
		reqSetAttrTwoDateStr();
		return "saleChanceChargerView";
	}
	/** 客户关系管理 > 销售跟踪 > 销售机会来源分布 **/
	public String saleChanceSaleSourceView() {
		reqSetAttrTwoDateStr();
		return "saleChanceSaleSourceView";
	}
	/** 客户关系管理 > 销售跟踪 > 销售机会类型分布 **/
	public String saleChanceSaleTypeView() {
		reqSetAttrTwoDateStr();
		return "saleChanceSaleTypeView";
	}
	/** 客户关系管理 > 销售跟踪 > 销售活动类型分布 **/
	public String activitySaleActivityPieShow() {
		reqSetAttrTwoDateStr();
		return "activitySaleActivityPieShow";
	}
	/** 客户关系管理 > 销售跟踪 > 销售机会负责人分布 视图查询 等多个处理 **/
	public void saleChanceDatePieQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
			String sBufferJsonToString =(String)saleChanceStatisticsService.saleChanceDatePieQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 销售机会负责人分布 列表查询 **/
	public void saleChanceDatePieQueryTable() {
		try {
			String tableGroupArrName = getDecodeRequestParameter("tableGroupArrName");
			String tableGroupArrValue = getDecodeRequestParameter("tableGroupArrValue");
			String tableTotalDouble = getDecodeRequestParameter("tableTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if(StringUtils.isNotEmpty(tableGroupArrName) && tableGroupArrName.startsWith("[") && tableGroupArrName.endsWith("]"  )
				&& StringUtils.isNotEmpty(tableGroupArrValue) && tableGroupArrValue.startsWith("[") && tableGroupArrValue.endsWith("]")	
			  ){
				JSONArray arrName = new JSONArray(tableGroupArrName);
				JSONArray arrValue = new JSONArray(tableGroupArrValue);
				Double  down = (  StringUtils.isNotEmpty(tableTotalDouble)  ) ? (Double.parseDouble(tableTotalDouble)) : 0.000001 ;
				if(arrName !=null  &&  arrValue!=null &&  arrName.length()>0  &&  arrValue.length()>0  &&  arrName.length()==arrValue.length()  ){
					for(int x=0;x<arrName.length();x++){
						intState++;
						String name = arrName.getString(x);
						Double upper = arrValue.getDouble(x);
						 Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
						if(x==0){
							sqlBuffer.append(" select '"+name+"' as tname,"+(x+1)+" as numsign, '"+upper+"' as valuenum ,'"+(pro*100)+"' as pro");
						}else{
							sqlBuffer.append(" union all  select '"+name+"' as tname,"+(x+1)+" as numsign, '"+upper+"' as valuenum ,'"+(pro*100)+"' as pro");
						}
					}
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as tname,null as numsign,null as valuenum,null as pro ");
			}
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select m.tname,m.numsign,m.valuenum,m.pro ");
				tableSql.append(" from ("+sqlBuffer.toString()+")m order by m.numsign asc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			if(intState == 0){
				tablePager = new Pager();
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 负责人/机会状态统计 **/
	public String chargerDivisionStatusView() {
		reqSetAttrTwoDateStr();
		return "chargerDivisionStatusView";
	}
	/** 客户关系管理 > 销售跟踪 > 查询机会状态数组legendArr **/
	public void queryLegendArr() {
		try {
			Employee employee = getEmployee();
			List<SaleChanceStatus> saleChanceStatusList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				saleChanceStatusList = baseHibernateService.findAllByEntityClass(SaleChanceStatus.class);
			}
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> idArr = new ArrayList<String>();
			if(saleChanceStatusList !=null  && saleChanceStatusList.size()>0){
				for(SaleChanceStatus s:saleChanceStatusList) {
					nameArr.add(s.getName());
					idArr.add(s.getId());
				}
			}
			Gson gosn = new Gson();
			StringBuilder json = new StringBuilder();
			json.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
			json.append(",\"idArr\":" + gosn.toJson(idArr) + "}" );
			renderJson(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 负责人/机会状态统计和阶段...等 视图查询 **/
	public void chargerDivisionStatusViewQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
			params.put("legendArrId",legendArrId);
            String sBufferJsonToString =(String)saleChanceStatisticsService.chargerDivisionStatusViewQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	/** 客户关系管理 > 销售跟踪 > 负责人/机会阶段统计 **/
	public String chargerDivisionSaleStageView() {
		reqSetAttrTwoDateStr();
		return "chargerDivisionSaleStageView";
	}
	/** 客户关系管理 > 销售跟踪 > 查询机会阶段数组legendArr **/
	public void queryLegendArrToSaleStage() {
		try {
			Employee employee = getEmployee();
			List<SaleStage> objList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				objList = baseHibernateService.findAllByEntityClass(SaleStage.class);
			}
			ArrayList<String> nameArr = new ArrayList<String>();
			ArrayList<String> idArr = new ArrayList<String>();
			if(objList !=null  && objList.size()>0){
				for(SaleStage s:objList) {
					nameArr.add(s.getName());
					idArr.add(s.getId());
				}
			}
			Gson gosn = new Gson();
			StringBuilder json = new StringBuilder();
			json.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
			json.append(",\"idArr\":" + gosn.toJson(idArr) + "}" );
			renderJson(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 机会可能性/状态统计 **/
	public String saleChancePossibilityView() {
		reqSetAttrTwoDateStr();
		return "saleChancePossibilityView";
	}
	/** 客户关系管理 > 销售跟踪 > 机会可能性/阶段统计 **/
	public String saleChanceStagePossibilityView() {
		reqSetAttrTwoDateStr();
		return "saleChanceStagePossibilityView";
	}
	/** 客户关系管理 > 销售跟踪 > 各阶段机会数量漏斗 **/
	public String saleChanceStageNumFunnel() {
		String stateStr = "NumFunnel!";//NumFunnel!表示查询 各阶段机会数量漏斗
		serveToFunnelSetJson(stateStr);
		reqSetAttrTwoDateStr();
		return "saleChanceStageNumFunnel";
	}
	/** 客户关系管理 > 销售跟踪 > 各阶段机会数量漏斗 & 各阶段机会预期金额漏斗 **/
	public void saleChanceStageFunnelQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
            String sBufferJsonToString =(String)saleChanceStatisticsService.saleChanceStageFunnelQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 各阶段机会预期金额漏斗 **/
	public String saleChanceStageExpectedMoneyFunnel() {
		String stateStr = "ExpectedMoneyFunnel!";//ExpectedMoneyFunnel!表示查询 各阶段机会预期金额漏斗
		serveToFunnelSetJson(stateStr);
		reqSetAttrTwoDateStr();
		return "saleChanceStageExpectedMoneyFunnel";
	}
	/** 服务漏斗图:拼装json串,用于帅选条件 **/
	public void serveToFunnelSetJson(String stateStr) {
		try {
			Employee employee = getEmployee();
			List<SaleChanceStatus> saleChanceStatusList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				saleChanceStatusList = baseHibernateService.findAllByEntityClass(SaleChanceStatus.class);
			}
			StringBuilder jsonSuggestObj =  new StringBuilder();
			jsonSuggestObj.append("{");
			jsonSuggestObj.append("\"value\":");
			jsonSuggestObj.append("[");
				jsonSuggestObj.append("{");
				jsonSuggestObj.append("\"id\": \""+stateStr+"all"+"\",");
				jsonSuggestObj.append("\"word\": \""+"统计全部状态的销售机会"+"\"");
				jsonSuggestObj.append("}");
			if(saleChanceStatusList!=null && saleChanceStatusList.size()>0){ 
				for(int x=0;x<saleChanceStatusList.size();x++) {
						jsonSuggestObj.append(",{");
						jsonSuggestObj.append("\"id\": \""+stateStr+saleChanceStatusList.get(x).getId()+"\",");
						jsonSuggestObj.append("\"word\": \""+"仅统计\'状态="+saleChanceStatusList.get(x).getName()+"\'的销售机会"+"\"");
						jsonSuggestObj.append("}");
				}
			}
			jsonSuggestObj.append("]");
			jsonSuggestObj.append("}");
			getRequest().setAttribute("jsonBsSuggestJava",jsonSuggestObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/** 客户关系管理 > 销售跟踪 > 销售活动类型/月份分布 **/
	public String activityTypeDivideMonthDraw() {
		String stateStr = "ActivityType!";//ActivityType!表示查询 '销售活动类型/月份分布'
		serveToDrawSetJson(stateStr);
		reqSetAttrTwoDateStr();
		return "activityTypeDivideMonthDraw";
	}
	/** 服务"销售活动类型/月份分布":拼装json串,用于帅选条件 **/
	public void serveToDrawSetJson(String stateStr) {
		try {
			Employee employee = getEmployee();
			List<SaleActivity> saleActivityList = null;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				saleActivityList = baseHibernateService.findAllByEntityClass(SaleActivity.class);
			}
			StringBuilder jsonSuggestObj =  new StringBuilder();
			jsonSuggestObj.append("{");
			jsonSuggestObj.append("\"value\":");
			jsonSuggestObj.append("[");
				jsonSuggestObj.append("{");
				jsonSuggestObj.append("\"id\": \""+stateStr+"all"+"\",");
				jsonSuggestObj.append("\"word\": \""+"统计全部类型的销售活动"+"\"");
				jsonSuggestObj.append("}");
			if(saleActivityList!=null && saleActivityList.size()>0){ 
				for(int x=0;x<saleActivityList.size();x++) {
						jsonSuggestObj.append(",{");
						jsonSuggestObj.append("\"id\": \""+stateStr+saleActivityList.get(x).getId()+"\",");
						jsonSuggestObj.append("\"word\": \""+"仅统计\'类型="+saleActivityList.get(x).getName()+"\'的销售活动"+"\"");
						jsonSuggestObj.append("}");
				}
			}
			jsonSuggestObj.append("]");
			jsonSuggestObj.append("}");
			getRequest().setAttribute("jsonBsSuggestJava",jsonSuggestObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/** 客户关系管理 > 销售跟踪 > 销售活动类型/月份分布相关数组查询      **/
	public void activityDivideMonthDrawQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
            String sBufferJsonToString =(String)saleChanceStatisticsService.activityDivideMonthDrawQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 应收款对应客户排行 **/
	public String backSectionPlanAmountCustomerTop() {
		reqSetAttrTwoDateStr();
		return "backSectionPlanAmountCustomerTop";
	}
	/** 客户关系管理 > 销售跟踪 > 应收款对应客户排行 & 应收款/回款计划所有者排行 & 应收款/回款计划负责人排行 视图查询  **/
	public void backPlanAmountCustomerTopQuery() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			if (StringUtils.isNotEmpty(queryTime)) {
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				params.put("timeArr",timeArr);
			}
			params.put("topNum",topNum);
			/**type就是控制sql查询的条件 **/
			params.put("type",type);
            String sBufferJsonToString =(String)saleChanceStatisticsService.backPlanAmountCustomerTopQuery(params).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关系管理 > 销售跟踪 > 应收款/回款计划所有者排行 **/
	public String backSectionPlanAmountOwnerTop() {
		reqSetAttrTwoDateStr();
		return "backSectionPlanAmountOwnerTop";
	}
	/** 客户关系管理 > 销售跟踪 > 应收款/回款计划负责人排行 **/
	public String backSectionPlanAmountChargerTop() {
		reqSetAttrTwoDateStr();
		return "backSectionPlanAmountChargerTop";
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public String getLegendArrId() {
		return legendArrId;
	}
	public void setLegendArrId(String legendArrId) {
		this.legendArrId = legendArrId;
	}
	
}
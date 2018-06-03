package com.vix.nvix.sales.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.sales.action.vo.SalesDetailedBookVo;
import com.vix.nvix.sales.action.vo.SalesDetailedVo;
import com.vix.nvix.sales.action.vo.SalesOrderAnalysisVo;
import com.vix.oa.personaloffice.service.ISaleDataService;

/**
 * 
 * @类全名 com.vix.nvix.purchase.action.VixntPurchaseStatisticsAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月30日
 */
@Controller
@Scope("prototype")
public class NvixntSalesStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ISaleDataService saleDataService;
	private List<Regional> regionalList;//地域
	private List<CustomerAccount> customerAccountList;//客户
	private String regionalID;//地域id     
	private String customerAccountID;//客户id    
	private String queryTime;
	private String topNum;
	private String controlSQL;
	/** 销售智能分析 > 销售统计仪表盘**/
	public String goStatisticsSaleView() {
		try {
			queryDataBlockSalesAmount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStatisticsSaleView";
	}
	/**销售智能分析 > 销售统计仪表盘>数据块>本月销售金额，今日销售金额 今日销售订单数 等**/
	public void queryDataBlockSalesAmount() {  
		try {
		Employee employee = getEmployee();
		Map<String, Object> hsMap = new HashMap<String, Object>();
		Double  todaySalesMoney = 0.0;//今日销售金额
		Double  thisMonthSalesMoney = 0.0;//本月销售金额
		Double  lastMonthSalesMoney = 0.0;//上月月销售金额
		Double  lastYearSalesMoney = 0.0;//同比去年销售金额
		Integer  todaySalesOrderNum = 0;//今日销售订单数
		Integer  thisMonthSalesOrderNum = 0;//本月销售订单数
		Integer  lastMonthSalesOrderNum = 0;//上月销售订单数
		Integer  lastYearSalesOrderNum = 0;//同比去年销售订单数  
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("Today"));
			hsMap.put("controlSQL", "salesAmount&orderNum");//salesAmount&orderNum 销售统计仪表盘查询销售订单数&查询销售金额
			Map<String, Object> obj = saleDataService.blockNumSaleDashboard(hsMap);
			todaySalesMoney = (Double) obj.get("numMoney");
			todaySalesOrderNum = (Integer) obj.get("numAmount");
			//
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("ThisMonthOT"));
			Map<String, Object> objc = saleDataService.blockNumSaleDashboard(hsMap);
			thisMonthSalesMoney = (Double) objc.get("numMoney");
			thisMonthSalesOrderNum = (Integer) objc.get("numAmount");
			//
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("LastMonthOT"));
			Map<String, Object> objd = saleDataService.blockNumSaleDashboard(hsMap);
			lastMonthSalesMoney = (Double) objd.get("numMoney");
			lastMonthSalesOrderNum = (Integer) objd.get("numAmount");
			//
			hsMap.put("timeArr", getAnMonthByThisMonthOT());
			Map<String, Object> obje = saleDataService.blockNumSaleDashboard(hsMap);
			lastYearSalesMoney = (Double) obje.get("numMoney");
			lastYearSalesOrderNum = (Integer) obje.get("numAmount");
		}
		getRequest().setAttribute("todaySalesOrderNum",todaySalesOrderNum);
		getRequest().setAttribute("thisMonthSalesOrderNum",thisMonthSalesOrderNum);
		reqSetAttributeMomToPage(thisMonthSalesOrderNum,lastMonthSalesOrderNum,"hbClassa","hbNuma");
		reqSetAttributeMomToPage(thisMonthSalesMoney,lastMonthSalesMoney,"hbClassb","hbNumb");
		reqSetAttributeMomToPage(thisMonthSalesOrderNum,lastYearSalesOrderNum,"tbClassa","tbNuma");
		reqSetAttributeMomToPage(thisMonthSalesMoney,lastYearSalesMoney,"tbClassb","tbNumb");
		getRequest().setAttribute("todaySalesMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(todaySalesMoney)));
		getRequest().setAttribute("thisMonthSalesMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(thisMonthSalesMoney)));
		//处理客户数问题
		Integer  todaySorderCustomerNum = 0;//今日客户数(没有去重)
		Integer  newCustomerNum = 0;//本月新客户数
		Integer  lastMonthnewCustomerNum = 0;//上月 新客户数
		Integer  lastYearnewCustomerNum = 0;//同比 新客户数
		Integer returnOrederNum = 0;//本月申请退货订单数
		Integer  lastMonthreturnOrederNum = 0;//上月    退货
		Integer  lastYearreturnOrederNum = 0;//同比  退货
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("Today"));
			hsMap.put("controlSQL", "salesOrderCustomerNum");//salesOrderCustomerNum  销售仪表盘查：今日客户数
			todaySorderCustomerNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			//
			hsMap.put("controlSQL", "salesOrderNewCustomerNum");//salesOrderNewCustomerNum  本月新客户数
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("ThisMonthOT"));
			newCustomerNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("LastMonthOT"));
			lastMonthnewCustomerNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			hsMap.put("timeArr", getAnMonthByThisMonthOT());
			lastYearnewCustomerNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			//
			hsMap.put("controlSQL", "salesReturnOrederNum");//salesReturnOrederNum  本月退货订单数(已完成)
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("ThisMonthOT"));
			returnOrederNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("LastMonthOT"));
			lastMonthreturnOrederNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
			hsMap.put("timeArr", getAnMonthByThisMonthOT());
			lastYearreturnOrederNum = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
		}
		getRequest().setAttribute("returnOrederNum",returnOrederNum);
		getRequest().setAttribute("newCustomerNum",newCustomerNum);
		getRequest().setAttribute("todaySorderCustomerNum",todaySorderCustomerNum);
		reqSetAttributeMomToPage(newCustomerNum,lastMonthnewCustomerNum,"hbClassc","hbNumc");
		reqSetAttributeMomToPage(newCustomerNum,lastYearnewCustomerNum,"tbClassc","tbNumc");
		reqSetAttributeMomToPage(returnOrederNum,lastMonthreturnOrederNum,"hbClassd","hbNumd");
		reqSetAttributeMomToPage(returnOrederNum,lastYearreturnOrederNum,"tbClassd","tbNumd");
		//今日销售产品种类
		Integer  todayProductTypes = 0;//今日销售产品种类
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			hsMap.put("timeArr", MyTool.getTimeArrByHtmlParameterString("Today"));
			hsMap.put("controlSQL", "salesProductTypes");//salesProductTypes 销售产品种类
			todayProductTypes = (Integer) saleDataService.blockNumSaleDashboard(hsMap).get("num");
		}
		getRequest().setAttribute("todayProductTypes",todayProductTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 处理根据查询出的今日客户数，昨日客户数，计算出环比数值，和要显示的上箭头还是下箭头，和最终要给页面getRequest().setAttribute的键值对，然后在页面显示
	 * 【注意命名不重复，防止setAttribute被覆盖...】
	 *  @param thisPeriodData代表本期数据 @param lastPeriodData代表上期数据 @param pageClass 页面要显示的箭头class @param pageNum 页面要显示的比值
	 * **/
	public void reqSetAttributeMomToPage(double thisPeriodData,double lastPeriodData,String pageClass,String pageNum) {
		String mom = MyTool.getMomStr(thisPeriodData, lastPeriodData);//环比
		String momClass = pageClass;
		String momNum = pageNum;
		if(mom.startsWith("-")) {
			getRequest().setAttribute(momClass, "down tmyColorA");
			getRequest().setAttribute(momNum, (mom.replace("-", "")) );
		}else{
			getRequest().setAttribute(momClass, "up tmyColorB");
			getRequest().setAttribute(momNum, mom);
		}
	}
	/** 通过 (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT") 获得同比的相应月初和月末**/
	public ArrayList<String> getAnMonthByThisMonthOT() {
		ArrayList<String> arr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
		ArrayList<String> arrReturn = new ArrayList<String>();
		String str ="";
		if(arr != null && arr.size()>0) {
			 str = arr.get(0);//这里str应该是"2018-11-03"
			 String year = str.substring(0,4);//2017 
			 String month = str.substring(5,7);//03  
			 String strTime = (Integer.parseInt(year)-1)+"-"+month+"{t-Sm-HOT}";
			 arrReturn = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(strTime);
		}
		return arrReturn;
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>最近30日销售订单趋势**/
	public String sLoadSalesOrderTrendNum() {
		return "sLoadSalesOrderTrendNum";
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>最近30日销售订单趋势   &  最近30日销售金额趋势图   **/
	@SuppressWarnings("unchecked")
	public void slowQuerySalesOrderTrend() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL", controlSQL);//qvSalesOrderTrendNum 查询 ‘最近30日销售订单趋势（订单数）’  和   qvSalesOrderTrendMoney 最近30日销售金额趋势图
			ArrayList<String> valueArr = (ArrayList<String>) saleDataService.slowQuerySalesOrderTrend(hsMap).get("valueArr");
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			StringBuffer fastAssemblingJson = new StringBuffer();
			fastAssemblingJson.append("{"+"\"timeStr\":" + gosn.toJson(timeStr));
			fastAssemblingJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
			renderJson(fastAssemblingJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>最近30日销售金额趋势图**/
	public String sLoadSalesOrderTrendMoney() {
		return "sLoadSalesOrderTrendMoney";
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售数量TOP10**/
	public String sLoadSaleProductTopNum() {
		return "sLoadSaleProductTopNum";
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售数量TOP10  & 产品销售金额TOP10 & 客户购买金额TOP10  **/
	public void slowQuerySalesTopView() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			hsMap.put("topNum",topNum);
/**controlSQL:   qvSellProductsNumTop 查询产品销售数量TOP10 & qvSellProductsMoneyTop 产品销售金额TOP10 & qvCustomerBuyTopMoneyTop 客户购买金额TOP10 & qvSalesmanSellTopMoney 销售人员业绩TOP10 **/
			hsMap.put("controlSQL", controlSQL);
			String sBufferJsonToString =(String)saleDataService.slowQuerySalesTopView(hsMap).get("sBufferJsonToString");
			renderJson(sBufferJsonToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>产品销售金额TOP10**/
	public String sLoadSaleProductTopMoney() {
		return "sLoadSaleProductTopMoney";
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>客户购买金额TOP10**/
	public String sLoadCustomerBuyTopMoney() {
		return "sLoadCustomerBuyTopMoney";
	}
	/** 慢加载：销售智能分析 > 销售统计仪表盘>销售人员业绩TOP10**/
	public String sLoadSalesmanSellTopMoney() {
		return "sLoadSalesmanSellTopMoney";
	}
	/** 销售智能分析 > 销售统计仪表盘> 销售订单分析列表 **/
	public void goSaleStatisticsTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			String mytitle = getDecodeRequestParameter("mytitle");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t. name as khname , sum(m.amounttotal) as tmoney , DATE_FORMAT(m.billdate, '%Y-%m-%d %H:%i:%s' ) as mbilldate ");
				tableSql.append(" from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id "); 
				tableSql.append(" and m.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and m.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and t.name is not null and t.name <> '' and m.billdate is not null and m.amounttotal is not null ");
				if (StringUtils.isNotEmpty(mytitle)) {
				tableSql.append(" and t.name like '%" + mytitle.trim() + "%' ");
				}
				tableSql.append(" group by m.customerAccount_id ");
				tableSql.append(" order by m.billdate desc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出: 销售智能分析 > 销售统计仪表盘> 销售订单分析列表 **/
	public void outExcelToSaleStatisticsTable() {
		try {
			Employee employee = getEmployee();
			String mytitle = getDecodeRequestParameter("mytitle");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t. name as khname , sum(m.amounttotal) as tmoney , DATE_FORMAT(m.billdate, '%Y-%m-%d %H:%i:%s' ) as mbilldate ");
				tableSql.append(" from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id "); 
				tableSql.append(" and m.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and m.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and t.name is not null and t.name <> '' and m.billdate is not null and m.amounttotal is not null ");
				if (StringUtils.isNotEmpty(mytitle)) {
				tableSql.append(" and t.name like '%" + mytitle.trim() + "%' ");
				}
				tableSql.append(" group by m.customerAccount_id ");
				tableSql.append(" order by m.billdate desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("excelSqlSaleStatisticsTable", tableSql.toString());
				ArrayList<SalesOrderAnalysisVo>  salesOrderAnalysisVoList = saleDataService.outExcelToSaleStatisticsTable(hsMap);
				exportSaleStatisticsExcel(salesOrderAnalysisVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>销售统计仪表盘>销售订单分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'销售智能分析 > 销售统计仪表盘> 销售订单分析列表' **/
	public void exportSaleStatisticsExcel(ArrayList<SalesOrderAnalysisVo> salesOrderAnalysisVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "销售订单分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("salesOrderAnalysis_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("salesOrderAnalysis_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesOrderAnalysisVoList", salesOrderAnalysisVoList);
					xlsArea.applyAt(new CellRef("salesOrderAnalysisVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////
	/** 销售智能分析>销售明细表    &&  销售智能分析>销售明细账**/
	public String goSaleDetailedView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				regionalList = vixntBaseService.findAllDataByConditions(Regional.class, params);
				Map<String, Object> paramsCustomer = new HashMap<String, Object>();
				paramsCustomer.put("isTemp," + SearchCondition.EQUAL, "0");
				paramsCustomer.put("isDeleted," + SearchCondition.EQUAL, "0");
				paramsCustomer.put("tenantId,"+ SearchCondition.EQUAL, params.get("tenantId,"+ SearchCondition.EQUAL)  );
				paramsCustomer.put("companyInnerCode,"+ SearchCondition.EQUAL, params.get("companyInnerCode,"+ SearchCondition.EQUAL)  );
				customerAccountList = vixntBaseService.findAllDataByConditions(CustomerAccount.class, paramsCustomer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
			StringBuffer jsonObj =  new StringBuffer();
			jsonObj.append("{");
			jsonObj.append("\"value\":");
			jsonObj.append("[");
				jsonObj.append("{");
				jsonObj.append("\"id\": \""+"all"+"\",");
				jsonObj.append("\"word\": \""+"全部地区"+"\"");
				jsonObj.append("}");
			if(regionalList!=null && regionalList.size()>0){ 
				for(int x=0;x<regionalList.size();x++) {
						jsonObj.append(",{");
						jsonObj.append("\"id\": \""+regionalList.get(x).getId()+"\",");
						jsonObj.append("\"word\": \""+regionalList.get(x).getName()+"\"");
						jsonObj.append("}");
				}
			}
			jsonObj.append("]");
			jsonObj.append("}");
			getRequest().setAttribute("jsonObj",jsonObj.toString());
			/////
			StringBuffer jsonObjC =  new StringBuffer();
			jsonObjC.append("{");
			jsonObjC.append("\"value\":");
			jsonObjC.append("[");
				jsonObjC.append("{");
				jsonObjC.append("\"id\": \""+"all"+"\",");
				jsonObjC.append("\"word\": \""+"全部客户"+"\"");
				jsonObjC.append("}");
			if(customerAccountList!=null && customerAccountList.size()>0){ 
				for(int x=0;x<customerAccountList.size();x++) {
						jsonObjC.append(",{");
						jsonObjC.append("\"id\": \""+customerAccountList.get(x).getId()+"\",");
						jsonObjC.append("\"word\": \""+customerAccountList.get(x).getName()+"\"");
						jsonObjC.append("}");
				}
			}
			jsonObjC.append("]");
			jsonObjC.append("}");
			getRequest().setAttribute("jsonObjC",jsonObjC.toString());
		
		String page = getRequestParameter("page");
		if(StringUtils.isNotEmpty(page) && (page.equals("2")) ){//销售智能分析>销售明细账  
			return "goSaleDetailedBookView";
		}
		return "goSaleDetailedView";//销售智能分析>销售明细表
	}
	/**销售智能分析>销售明细表 查询 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchSaleDetailedTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				/**  拼装选中地域，客户的条件sql为一张表 **/
				StringBuffer selectSql = new StringBuffer();
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					selectSql.append(" select m.id,m.billdate,m.code,m.customerAccount_id,m.regional_id,m.tenantid,m.companyinnercode ");
					selectSql.append(" from sale_salesorder m ");
					if(StringUtils.isNotEmpty(regionalID)){
						selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
					}
					if(StringUtils.isNotEmpty(customerAccountID)){
						selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
					}
					selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
					selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
					selectSql.append(" and m.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					selectSql.append(" and m.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				}
				////
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select date_format(t.billdate, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" m.amount as sl,m.price as dj,tb. name as cpmc, ");
				tableSql.append(" t.code as djbm, ");
				tableSql.append(" t.customerAccount_id as khid,t.regional_id as dyid,tb.code as cpbh ");
				/**  如果选中地域，客户，并且不为空，就使用拼装好的sql语句，否则使用默认查询 **/
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					tableSql.append(" from sale_saleorderitem m inner join "+"("+selectSql.toString()+")"+" t on m.salesorder_id = t.id "); 
				}else {
					tableSql.append(" from sale_saleorderitem m inner join sale_salesorder t on m.salesorder_id = t.id "); 
				}
				tableSql.append(" inner join mdm_item tb on m.item_id = tb.id "); 
				tableSql.append(" and t.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" order by t.billdate desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						/** 查询地域名称  **/
						String querySqldy = " select name,'1' from common_regional where id='"+objectMap.get("dyid")+"' and name is not null and name <> '' ";
						objectMap.put("dyname", saleDataService.queryOneDataBySql(querySqldy) );
						/** 查询客户名称  **/
						String querySqlkh = " select name,'1' from crm_customeraccount where id='"+objectMap.get("khid")+"' and name is not null and name <> '' ";
						objectMap.put("khname", saleDataService.queryOneDataBySql(querySqlkh) );   
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出: 销销售智能分析>销售明细表  **/
	public void outExcelToSaleDetailedTable() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				/**  拼装选中地域，客户的条件sql为一张表 **/
				StringBuffer selectSql = new StringBuffer();
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					selectSql.append(" select m.id,m.billdate,m.code,m.customerAccount_id,m.regional_id,m.tenantid,m.companyinnercode ");
					selectSql.append(" from sale_salesorder m ");
					if(StringUtils.isNotEmpty(regionalID)){
						selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
					}
					if(StringUtils.isNotEmpty(customerAccountID)){
						selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
					}
					selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
					selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
					selectSql.append(" and m.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					selectSql.append(" and m.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				}
				////
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select date_format(t.billdate, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" m.amount as sl,m.price as dj,tb. name as cpmc, ");
				tableSql.append(" t.code as djbm, ");
				tableSql.append(" t.customerAccount_id as khid,t.regional_id as dyid,tb.code as cpbh ");
				/**  如果选中地域，客户，并且不为空，就使用拼装好的sql语句，否则使用默认查询 **/
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					tableSql.append(" from sale_saleorderitem m inner join "+"("+selectSql.toString()+")"+" t on m.salesorder_id = t.id "); 
				}else {
					tableSql.append(" from sale_saleorderitem m inner join sale_salesorder t on m.salesorder_id = t.id "); 
				}
				tableSql.append(" inner join mdm_item tb on m.item_id = tb.id "); 
				tableSql.append(" and t.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" order by t.billdate desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("excelSqlSaleDetailedTable", tableSql.toString());
				ArrayList<SalesDetailedVo>  salesDetailedVoList = saleDataService.outExcelToSaleDetailedTable(hsMap); 
				exportSaleDetailedTableExcel(salesDetailedVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>销售明细表>销售明细表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出: 销销售智能分析>销售明细表  **/
	public void exportSaleDetailedTableExcel(ArrayList<SalesDetailedVo> salesDetailedVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "销售明细表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("salesSaleDetailedTable_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("salesSaleDetailedTable_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesDetailedVoList", salesDetailedVoList);
					xlsArea.applyAt(new CellRef("salesDetailedVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**销售智能分析>在‘销售明细表’查询基础上进行增加详细列展示，完成‘销售明细账’的查询展示 **/
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void searchSaleDetailedTableForBook() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				/**  拼装选中地域，客户的条件sql为一张表 **/
				StringBuffer selectSql = new StringBuffer();
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					selectSql.append(" select m.id,m.billdate,m.code,m.customerAccount_id,m.regional_id,m.tenantid,m.companyinnercode ");
					selectSql.append(" from sale_salesorder m ");
					if(StringUtils.isNotEmpty(regionalID)){
						selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
					}
					if(StringUtils.isNotEmpty(customerAccountID)){
						selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
					}
					selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
					selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
					selectSql.append(" and m.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					selectSql.append(" and m.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				}
				////
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select date_format(t.billdate, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" m.amount as sl,m.price as dj,tb. name as cpmc, ");
				tableSql.append(" t.code as djbm, ");
				tableSql.append(" t.customeraccount_id as khid,t.regional_id as dyid,tb.code as cpbh ");
				tableSql.append(" ,m.specification, ");
				tableSql.append(" m.taxprice, ");
				tableSql.append(" m.netprice, ");
				tableSql.append(" m.tax, ");
				tableSql.append(" m.discount, ");
				tableSql.append(" tb.measureUnit_id as zjldwid ");
				/**  如果选中地域，客户，并且不为空，就使用拼装好的sql语句，否则使用默认查询 **/
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					tableSql.append(" from sale_saleorderitem m inner join "+"("+selectSql.toString()+")"+" t on m.salesorder_id = t.id "); 
				}else {
					tableSql.append(" from sale_saleorderitem m inner join sale_salesorder t on m.salesorder_id = t.id "); 
				}
				tableSql.append(" inner join mdm_item tb on m.item_id = tb.id "); 
				tableSql.append(" and t.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" order by t.billdate desc ");
				tablePager.setOrderField(null);
				Pager pager = this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
						/** 查询地域名称  **/
						String querySqldy = " select name,'1' from common_regional where id='"+objectMap.get("dyid")+"' and name is not null and name <> '' ";
						objectMap.put("dyname", saleDataService.queryOneDataBySql(querySqldy) );
						/** 查询客户名称  **/
						String querySqlkh = " select name,'1' from crm_customeraccount where id='"+objectMap.get("khid")+"' and name is not null and name <> '' ";
						objectMap.put("khname", saleDataService.queryOneDataBySql(querySqlkh) ); 
						/** 查询主计量单位名称  **/
						String querySqldw = " select name,'1' from common_measureunit where id='"+objectMap.get("zjldwid")+"' and name is not null and name <> '' ";
						objectMap.put("dwname", saleDataService.queryOneDataBySql(querySqldw) );
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出 销售智能分析>销售明细账 **/
	public void outExcelToSaleDetailedTableBook() {
		try {
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				/**  拼装选中地域，客户的条件sql为一张表 **/
				StringBuffer selectSql = new StringBuffer();
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					selectSql.append(" select m.id,m.billdate,m.code,m.customerAccount_id,m.regional_id,m.tenantid,m.companyinnercode ");
					selectSql.append(" from sale_salesorder m ");
					if(StringUtils.isNotEmpty(regionalID)){
						selectSql.append(" inner join common_regional tb on m.regional_id = tb.id and m.regional_id='"+regionalID+"'");
					}
					if(StringUtils.isNotEmpty(customerAccountID)){
						selectSql.append(" inner join crm_customeraccount t on m.customeraccount_id = t.id and m.customeraccount_id='"+customerAccountID+"'");
					}
					selectSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
					selectSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
					selectSql.append(" and m.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
					selectSql.append(" and m.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				}
				////
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select date_format(t.billdate, '%Y-%m-%d %h:%i:%s' ) as mcreatetime, ");
				tableSql.append(" m.amount as sl,m.price as dj,tb. name as cpmc, ");
				tableSql.append(" t.code as djbm, ");
				tableSql.append(" t.customeraccount_id as khid,t.regional_id as dyid,tb.code as cpbh ");
				tableSql.append(" ,m.specification, ");
				tableSql.append(" m.taxprice, ");
				tableSql.append(" m.netprice, ");
				tableSql.append(" m.tax, ");
				tableSql.append(" m.discount, ");
				tableSql.append(" tb.measureUnit_id as zjldwid ");
				/**  如果选中地域，客户，并且不为空，就使用拼装好的sql语句，否则使用默认查询 **/
				if(StringUtils.isNotEmpty(regionalID) || StringUtils.isNotEmpty(customerAccountID) ){
					tableSql.append(" from sale_saleorderitem m inner join "+"("+selectSql.toString()+")"+" t on m.salesorder_id = t.id "); 
				}else {
					tableSql.append(" from sale_saleorderitem m inner join sale_salesorder t on m.salesorder_id = t.id "); 
				}
				tableSql.append(" inner join mdm_item tb on m.item_id = tb.id "); 
				tableSql.append(" and t.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and t.tenantid='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and t.companyinnercode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" order by t.billdate desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("excelSqlSaleDetailedTableBook", tableSql.toString());
				ArrayList<SalesDetailedBookVo>  salesDetailedBookVoList = saleDataService.outExcelToSaleDetailedTableBook(hsMap); 
				exportSaleDetailedTableExcelBook(salesDetailedBookVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'销售智能分析>销售明细账>销售明细账'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出: 销销售智能分析>销售明细账  **/
	public void exportSaleDetailedTableExcelBook(ArrayList<SalesDetailedBookVo> salesDetailedBookVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "销售明细账.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("saleDetailedTableBook_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("saleDetailedTableBook_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesDetailedBookVoList", salesDetailedBookVoList);
					xlsArea.applyAt(new CellRef("salesDetailedBookVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////
	/**
	 * 设计原型图时的table样本列表用 
	 */
	public void goTable() {
		try {
			Pager pager = getPager();
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String getRegionalID() {
		return regionalID;
	}
	public void setRegionalID(String regionalID) {
		this.regionalID = regionalID;
	}
	public String getCustomerAccountID() {
		return customerAccountID;
	}
	public void setCustomerAccountID(String customerAccountID) {
		this.customerAccountID = customerAccountID;
	}
	public List<CustomerAccount> getCustomerAccountList() {
		return customerAccountList;
	}
	public void setCustomerAccountList(List<CustomerAccount> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}
	public List<Regional> getRegionalList() {
		return regionalList;
	}
	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}
}

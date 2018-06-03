package com.vix.nvix.warehouse.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IStockQueryStatisticsService;
/**
 *   为库存管理>库存报表>库存仪表盘 的统计服务
 */
@Controller
@Scope("prototype")
public class VixntStockQueryHomePageAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String queryTime;
	private String inOrOutStock;//出库或入库
	
	@Autowired
	private IStockQueryStatisticsService stockQueryStatisticsService;
	/** 库存管理>库存报表>库存仪表盘   **/
	public String goStockQueryHomePage() {
		try {
			getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
			Double existingGoodsSKUblock = queryStockGoodsNum("Today","existingGoodsSKUnum");//existingGoodsSKUnum 查询现存物料SKU数(controlSQL)
			getRequest().setAttribute("existingGoodsSKUblock",MyTool.getIntFromDouble(existingGoodsSKUblock) );
			Double overdueThisMonthblock = queryStockGoodsNum("ThisMonthOT","overdueThisMonthNum");//overdueThisMonthNum 查询本月过期物料量(controlSQL)
			getRequest().setAttribute("overdueThisMonthblock",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal( overdueThisMonthblock )));
			Double insufficientGoodsSKUblock = queryStockGoodsNum("Today","insufficientGoodsSKUnum!20");//insufficientGoodsSKUnum!20    查询库存不足物料SKU数20代表库存小于20(controlSQL)
			getRequest().setAttribute("insufficientGoodsSKUblock",MyTool.getIntFromDouble(insufficientGoodsSKUblock) );
			Double backlogGoodsSKUblock = queryStockGoodsNum("Today","backlogGoodsSKUnum!200");//backlogGoodsSKUnum!200    查询库存积压物料SKU数200代表库存数量大于200(controlSQL)
			getRequest().setAttribute("backlogGoodsSKUblock",MyTool.getIntFromDouble(backlogGoodsSKUblock) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goStockQueryHomePage";
	}
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品入库趋势图   **/
	public String slowLoadStockHomePageInView() {
		return "slowLoadStockHomePageInView";
	}
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品出库趋势图   **/
	public String slowLoadStockHomePageOutView() {
		return "slowLoadStockHomePageOutView";
	}
	/** 慢加载：库存管理 > 库存报表 > 库存仪表盘 > 最近30日商品入库趋势图  数据查询   **/
	@SuppressWarnings("unchecked")
	public void queryDataViewStockInOut() {
		try {
		Employee employee = getEmployee();
		Map<String, Object> hsMap = new HashMap<String, Object>();
		ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		}
		hsMap.put("timeArr",timeArr);
		String inOrOutStockSQL = "";
		if(StringUtils.isNotEmpty(inOrOutStock) && "in".equals(inOrOutStock) ) {
			inOrOutStockSQL = " and t.flag = '1' ";
		}else if(StringUtils.isNotEmpty(inOrOutStock) && "out".equals(inOrOutStock) ){
			inOrOutStockSQL = " and t.flag = '2' "; 
		}else {
			inOrOutStockSQL = " and 1=2 "; 
		}
		hsMap.put("inOrOutStockSQL", inOrOutStockSQL);
		hsMap.put("controlSQL", "queryDataStockInOutNumber");//queryDataStockInOutNumber 计算最近30日商品入库趋势图的数量
		ArrayList<String> valueArrNumber = (ArrayList<String>) stockQueryStatisticsService.queryDataViewStockInOut(hsMap).get("valueArr");
		hsMap.put("controlSQL", "queryDataStockInOutMoney");//queryDataStockInOutMoney 计算最近30日商品入库趋势图的金额
		ArrayList<String> valueArrMoney = (ArrayList<String>) stockQueryStatisticsService.queryDataViewStockInOut(hsMap).get("valueArr");
		ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
		Gson gosn = new Gson();
		StringBuffer fastAssemblingJson = new StringBuffer();
		fastAssemblingJson.append("{"+"\"timeStr\":" + gosn.toJson(timeStr));
		fastAssemblingJson.append(",\"valueArrNumber\":" + valueArrNumber.toString() );
		fastAssemblingJson.append(",\"valueArrMoney\":" + valueArrMoney.toString() + "}" );
		renderJson(fastAssemblingJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 现存物料SKU数,本月过期物料量,库存不足物料SKU数,库存积压物料SKU数(库存积压:现存量>=200;库存不足:现存量<20;) **/
	public Double queryStockGoodsNum(String queryTimeStr,String controlSQLStr) {
		Double returnDou = 0.0;
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Employee employee = getEmployee();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTimeStr);
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("controlSQL", controlSQLStr);
			hsMap.put("timeArr",timeArr);
			returnDou = (Double) stockQueryStatisticsService.queryStockGoodsNum(hsMap).get("totalDouble");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnDou;
	}
	/** 门店库存报表 > 最近30日入库单据  列表查询 **/
	public void storageInTable() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			String personIn = getRequestParameter("personIn");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				if(StringUtils.isNotEmpty(personIn)) {
					params.put("creator," + SearchCondition.ANYLIKE, URLDecoder.decode(personIn.trim(), "UTF-8"));//创建人
				}
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("-Lately-Day{30}");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				params.put("flag," + SearchCondition.EQUAL, "1");
				params.put("type," + SearchCondition.EQUAL, "2");
				baseHibernateService.findPagerByHqlConditions(getPager(),StockRecords.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 最近30日出库单据  列表查询 **/
	public void storageOutTable() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			String personOut = getRequestParameter("personOut");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				if(StringUtils.isNotEmpty(personOut)) {
					params.put("creator," + SearchCondition.ANYLIKE, URLDecoder.decode(personOut.trim(), "UTF-8"));//创建人
				}
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("-Lately-Day{30}");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				params.put("flag," + SearchCondition.EQUAL, "2");
				params.put("type," + SearchCondition.EQUAL, "2");
				baseHibernateService.findPagerByHqlConditions(getPager(),StockRecords.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 本月过期物料列表  列表查询 **/
	public void storageOverdueTable() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			String mynameOverdue = getRequestParameter("mynameOverdue");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				if(StringUtils.isNotEmpty(mynameOverdue)) {
					params.put("itemname," + SearchCondition.ANYLIKE, URLDecoder.decode(mynameOverdue.trim(), "UTF-8"));//物料名称
				}
				params.put("avaquantity," + SearchCondition.MORETHAN, 0.0);//avaquantity在InventoryCurrentStock中是 java.lang.Double 类型 where avaquantity>0.0
				params.put("skuCode," + SearchCondition.MORETHAN, "");//skuCode在InventoryCurrentStock中是 java.lang.String 类型 where skuCode>''则相当于 and m.skucode is not null and m.skucode <> ''
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("massunitEndTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				baseHibernateService.findPagerByHqlConditions(getPager(),InventoryCurrentStock.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 不足物料列表  列表查询 **/
	public void storageLessTable() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			String mynameLess = getRequestParameter("mynameLess");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("avaquantity");
				}
				if(StringUtils.isNotEmpty(mynameLess)) {
					params.put("itemname," + SearchCondition.ANYLIKE, URLDecoder.decode(mynameLess.trim(), "UTF-8"));//物料名称
				}
				params.put("avaquantity," + SearchCondition.LESSTHAN, 20.0);//小于20
				params.put("skuCode," + SearchCondition.MORETHAN, "");//skuCode字段不为空,不为null
				baseHibernateService.findPagerByHqlConditions(getPager(),InventoryCurrentStock.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 门店库存报表 > 积压物料列表  列表查询 **/
	public void storageMoreTable() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			String mynameMore = getRequestParameter("mynameMore");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("avaquantity");
				}
				if(StringUtils.isNotEmpty(mynameMore)) {
					params.put("itemname," + SearchCondition.ANYLIKE, URLDecoder.decode(mynameMore.trim(), "UTF-8"));//物料名称
				}
				params.put("avaquantity," + SearchCondition.MORETHAN, 200.0);//大于200.0
				params.put("skuCode," + SearchCondition.MORETHAN, "");//skuCode字段不为空,不为null
				baseHibernateService.findPagerByHqlConditions(getPager(),InventoryCurrentStock.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 库存管理 > 库存报表 > 库存分析   **/
	public String goStockAnalysisPage() {
		return "goStockAnalysisPage";
	}
	/** 这里计算的是所有仓库的 库龄结构:计算'库龄结构'  **/
	public void calculationStockAge() {
		try {
		Map<String, Object> params = getParams();
		Employee employee = getEmployee();
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
		}
		String jsonStr = stockQueryStatisticsService.calculationStockAge(params);
		renderJson(jsonStr);
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
	public String getInOrOutStock() {
		return inOrOutStock;
	}
	public void setInOrOutStock(String inOrOutStock) {
		this.inOrOutStock = inOrOutStock;
	}
	
}
package com.vix.nvix.purchase.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
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
import com.vix.common.properties.util.ComparatorMapBeanDoubleDX;
import com.vix.common.properties.util.MapBeanDouble;
import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.purchase.action.vo.PurchaseOrderAnalysisVo;
import com.vix.nvix.purchase.action.vo.PurchaseSupplierCostVo;
import com.vix.oa.personaloffice.service.IPurchaseDataService;
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
public class VixntPurchaseStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IPurchaseDataService purchaseDataService;
	private String queryTime;
	private String topNum;
	private List<Supplier> supplierList;  
	private String controlSQL;
	private String categoryId;//树中：选中的 商品分类id
	private String supplierID;//供应商id
	/** 采购统计仪表盘页面 **/
	public String goStatisticsView() {
		queryDataBlockNum();
		orderMoneyMomAndAn();
		return "goStatisticsView";
	}
	public String goSourceView() {
		queryDataBlockNumForMonth();
		orderMoneyMomAndAn();
		return "goSourceView";
	}
	/**采购统计仪表盘>数据块>本月订单金额环比 和 本月订单金额同比 **/
	public void orderMoneyMomAndAn() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Double  thisPeriodTsMonthOrderMoney = 0.0;//本期本月采购订单金额
			Double  thisPeriodLtMonthOrderMoney = 0.0;//本期上月采购订单金额（环比）
			Double  lastPeriodTsMonthOrderMoney = 0.0;//上期本月月采购订单金额（同比）
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
				hsMap.put("timeArr", timeArr);
				hsMap.put("controlSQL", "numPurOrderMoneySanec");//numPurOrderMoneySanec ‘根据传入时间集合查询：采购订单总金额’
				thisPeriodTsMonthOrderMoney = (Double) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
				/** 对当前获得的‘本期本月时间集合’进行按顺序减少一年...获得‘上期本月时间集合’ **/
				for(int x=0;x<timeArr.size();x++){
					String dateReduceOneYear = MyTool.dateReduceOneYear(timeArr.get(x));
					timeArr.set(x, dateReduceOneYear);
				}
				hsMap.put("timeArr", timeArr);
				lastPeriodTsMonthOrderMoney = (Double) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
				timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("LastMonthOT");
				hsMap.put("timeArr", timeArr);
				thisPeriodLtMonthOrderMoney = (Double) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
			}
			String momStrOrderMoney = MyTool.getMomStr(thisPeriodTsMonthOrderMoney, thisPeriodLtMonthOrderMoney);//环比
			String momStrOrderMoneyfa = "momStrOrderMoneyfa";
			String momStrOrderMoneyColor = "momStrOrderMoneyColor";
			String momStrOrderMoneyNum = "momStrOrderMoneyNum";
			if(momStrOrderMoney.startsWith("-")) {
				getRequest().setAttribute(momStrOrderMoneyfa, "fa fa-arrow-down");
				getRequest().setAttribute(momStrOrderMoneyColor, "#05CD15");
				getRequest().setAttribute(momStrOrderMoneyNum, (momStrOrderMoney.replace("-", "")) );
			}else{
				getRequest().setAttribute(momStrOrderMoneyfa, "fa fa-arrow-up");
				getRequest().setAttribute(momStrOrderMoneyColor, "#D0000D");
				getRequest().setAttribute(momStrOrderMoneyNum, momStrOrderMoney);
			}
			String anStrOrderMoney = MyTool.getMomStr(thisPeriodTsMonthOrderMoney, lastPeriodTsMonthOrderMoney);//同比
			String anStrOrderMoneyfa = "anStrOrderMoneyfa";
			String anStrOrderMoneyColor = "anStrOrderMoneyColor";
			String anStrOrderMoneyNum = "anStrOrderMoneyNum";
			if(anStrOrderMoney.startsWith("-")) {
				getRequest().setAttribute(anStrOrderMoneyfa, "fa fa-arrow-down");
				getRequest().setAttribute(anStrOrderMoneyColor, "#05CD15");
				getRequest().setAttribute(anStrOrderMoneyNum, (anStrOrderMoney.replace("-", "")) );
			}else{
				getRequest().setAttribute(anStrOrderMoneyfa, "fa fa-arrow-up");
				getRequest().setAttribute(anStrOrderMoneyColor, "#D0000D");
				getRequest().setAttribute(anStrOrderMoneyNum, anStrOrderMoney);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	/**采购统计仪表盘>数据块>采购询价单数,本月采购计划,本月采购订单总金额,本月已完成采购订单金额，本月已完成采购订单数，本月在途采购订单数 等**/
	public void queryDataBlockNum() {
		try {
		Employee employee = getEmployee();
		Map<String, Object> hsMap = new HashMap<String, Object>();
		Integer numInquire = 0;
		Double  numPurOrderMoney = 0.0;
		Integer numCompletedOrder = 0;
		Double  numCompletedOrderMoney = 0.0;
		Integer numOnPassageOrder =0;
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
			hsMap.put("timeArr", timeArr);
			hsMap.put("controlSQL", "numInquireSanbc");//numInquireSanbc 查询 ‘采购询价单数’
			numInquire = (Integer) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
			hsMap.put("controlSQL", "numPurOrderMoneySanec");//numPurOrderMoneySanec 查询 ‘本月采购订单总金额’
			numPurOrderMoney = (Double) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
			hsMap.put("controlSQL", "completedOrder&Money");//completedOrder&Money 查询 ‘本月已完成采购订单金额’和‘本月已完成采购订单数’
			Map<String, Object> obj = purchaseDataService.blockNumPurchaseDashboard(hsMap);
			numCompletedOrder = (Integer) obj.get("numAmount");//‘本月已完成采购订单数’
			numCompletedOrderMoney = (Double) obj.get("numMoney");//‘本月已完成采购订单金额’
			hsMap.put("controlSQL", "OnPassageOrder");//OnPassageOrder 查询 ‘本月在途采购订单数’ 只要不是‘已完成’就是在途采购订单
			numOnPassageOrder = (Integer) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
		}
		getRequest().setAttribute("numOnPassageOrder",numOnPassageOrder);
		getRequest().setAttribute("numCompletedOrderMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(numCompletedOrderMoney)));
		getRequest().setAttribute("numCompletedOrder",numCompletedOrder);
		getRequest().setAttribute("numInquire",numInquire);//查询‘本月采购询价’数量  
		getRequest().setAttribute("numPurOrderMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(numPurOrderMoney)));//查询‘本月采购订单总金额’数量  
		long numPlan = 0L;
		long numPurchaseReturn = 0L;
		Map<String, Object> params = getParams();
		Map<String, Object> paramsReturn = getParams();
		if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
			if (timeArr != null && timeArr.size() > 0) {
				String startTime = timeArr.get(0);
				String endTime = timeArr.get(timeArr.size() - 1);
				params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
			}
			params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId() );
			params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode() );
			params.put("isReport," + SearchCondition.EQUAL, "0" );		
			numPlan = vixntBaseService.findDataCountByHqlConditions(PurchasePlan.class, params);
			paramsReturn.put("createTime," + SearchCondition.BETWEENAND, params.get("createTime," + SearchCondition.BETWEENAND));
			paramsReturn.put("tenantId," + SearchCondition.EQUAL, params.get("tenantId," + SearchCondition.EQUAL));
			paramsReturn.put("companyInnerCode," + SearchCondition.EQUAL, params.get("companyInnerCode," + SearchCondition.EQUAL));
			numPurchaseReturn = vixntBaseService.findDataCountByHqlConditions(PurchaseReturn.class, paramsReturn);
		}
		getRequest().setAttribute("numPlan",numPlan);//查询‘本月采购计划’数量  
		getRequest().setAttribute("numPurchaseReturn",numPurchaseReturn);//查询‘本月退货单’数量  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void queryDataBlockNumForMonth() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			Integer numInquire = 0;
			Double  numPurOrderMoney = 0.0;
			Integer numCompletedOrder = 0;
			Double  numCompletedOrderMoney = 0.0;
			Integer numOnPassageOrder =0;
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("ThisMonthOT");
				hsMap.put("timeArr", timeArr);
				hsMap.put("controlSQL", "numInquireSanbc");//numInquireSanbc 查询 ‘采购询价单数’
				numInquire = (Integer) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
				hsMap.put("controlSQL", "numPurOrderMoneySanec");//numPurOrderMoneySanec 查询 ‘本月采购订单总金额’
				numPurOrderMoney = (Double) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
				hsMap.put("controlSQL", "completedOrder&Money");//completedOrder&Money 查询 ‘本月已完成采购订单金额’和‘本月已完成采购订单数’
				Map<String, Object> obj = purchaseDataService.blockNumPurchaseDashboard(hsMap);
				numCompletedOrder = (Integer) obj.get("numAmount");//‘本月已完成采购订单数’
				numCompletedOrderMoney = (Double) obj.get("numMoney");//‘本月已完成采购订单金额’
				hsMap.put("controlSQL", "OnPassageOrder");//OnPassageOrder 查询 ‘本月在途采购订单数’ 只要不是‘已完成’就是在途采购订单
				numOnPassageOrder = (Integer) purchaseDataService.blockNumPurchaseDashboard(hsMap).get("num");
			}
			getRequest().setAttribute("numOnPassageOrder",numOnPassageOrder);
			getRequest().setAttribute("numCompletedOrderMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(numCompletedOrderMoney)));
			getRequest().setAttribute("numCompletedOrder",numCompletedOrder);
			getRequest().setAttribute("numInquire",numInquire);//查询‘本月采购询价’数量  
			getRequest().setAttribute("numPurOrderMoney",MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(numPurOrderMoney)));//查询‘本月采购订单总金额’数量  
			long numPlan = 0L;
			long numPurchaseReturn = 0L;
			Map<String, Object> params = getParams();
			Map<String, Object> paramsReturn = getParams();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString("LastMonthOT");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId() );
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode() );
				params.put("isReport," + SearchCondition.EQUAL, "0" );		
				numPlan = vixntBaseService.findDataCountByHqlConditions(PurchasePlan.class, params);
				paramsReturn.put("createTime," + SearchCondition.BETWEENAND, params.get("createTime," + SearchCondition.BETWEENAND));
				paramsReturn.put("tenantId," + SearchCondition.EQUAL, params.get("tenantId," + SearchCondition.EQUAL));
				paramsReturn.put("companyInnerCode," + SearchCondition.EQUAL, params.get("companyInnerCode," + SearchCondition.EQUAL));
				numPurchaseReturn = vixntBaseService.findDataCountByHqlConditions(PurchaseReturn.class, paramsReturn);
			}
			getRequest().setAttribute("numPlan",numPlan);//查询‘本月采购计划’数量  
			getRequest().setAttribute("numPurchaseReturn",numPurchaseReturn);//查询‘本月退货单’数量  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 慢加载：最近30日采购趋势图**/
	public String slowLoadPurchaseQuantityTrend() {
		slowQueryPurchaseQuantityTrend();
		return "slowLoadPurchaseQuantityTrend";
	}
	/** 慢加载：最近30日采购趋势图 查询**/
	@SuppressWarnings("unchecked")
	public void slowQueryPurchaseQuantityTrend() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL", "qPurchasePlanView");//qPurchasePlanView 查询 ‘30天采购计划订单数’
			ArrayList<String> valueArr = (ArrayList<String>) purchaseDataService.slowQueryPurchaseView(hsMap).get("valueArr");
			hsMap.put("controlSQL", "qCompletedOrderView");//qCompletedOrderView 查询 ‘30天已完成采购订单’
			ArrayList<String> valueArrCompleted = (ArrayList<String>) purchaseDataService.slowQueryPurchaseView(hsMap).get("valueArrCompleted");
			hsMap.put("controlSQL", "qOnPassageOrderView");//qOnPassageOrderView 查询 ‘30天在途采购订单’
			ArrayList<String> valueArrOnPassage = (ArrayList<String>) purchaseDataService.slowQueryPurchaseView(hsMap).get("valueArrOnPassage");
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			getRequest().setAttribute("timeStr",  gosn.toJson(timeStr).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", gosn.toJson(valueArr).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArrOnPassage", gosn.toJson(valueArrOnPassage).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArrCompleted", gosn.toJson(valueArrCompleted).replaceAll("\"", "\\\"") );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：最近30日已完成采购订单金额趋势图**/
	public String slowLoadPurchaseMoneyTrend() {
		slowQueryPurchaseMoneyTrend();
		return "slowLoadPurchaseMoneyTrend";
	}
	/** 慢加载：最近30日已完成采购订单金额趋势图 查询**/
	@SuppressWarnings("unchecked")
	public void slowQueryPurchaseMoneyTrend() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("timeArr",timeArr);
			ArrayList<String> valueArr = (ArrayList<String>) purchaseDataService.slowQueryPurchaseMoneyView(hsMap).get("valueArr");
			ArrayList<String> timeStr = MyTool.substringDateStrTwo_Array(timeArr);
			Gson gosn = new Gson();
			getRequest().setAttribute("timeStr",  gosn.toJson(timeStr).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", valueArr.toString() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：采购订单中:采购产品数量TOP10**/
	public String slowLoadPurchaseItemQuantity() {
		slowQueryPurchaseItemQuantity();
		return "slowLoadPurchaseItemQuantity";
	}
	/** 慢加载：采购订单中:采购产品数量TOP10 查询**/
	public void slowQueryPurchaseItemQuantity() {
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
			Map<String, Object> returnMap = purchaseDataService.slowQueryPurchaseItemQuantityTop(hsMap);
			Gson gosn = new Gson();
			getRequest().setAttribute("nameArr",  gosn.toJson(returnMap.get("nameArr")).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", returnMap.get("valueArr").toString() ); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：采购订单中:采购产品金额TOP10 **/
	public String slowLoadPurchaseItemMoney() {
		slowQueryPurchaseItemMoney();
		return "slowLoadPurchaseItemMoney";
	}
	/** 慢加载：采购订单中:采购产品金额TOP10 查询**/
	public void slowQueryPurchaseItemMoney() {
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
			Map<String, Object> returnMap = purchaseDataService.slowQueryPurchaseItemMoneyTop(hsMap);
			Gson gosn = new Gson();
			getRequest().setAttribute("nameArr",  gosn.toJson(returnMap.get("nameArr")).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", returnMap.get("valueArr").toString() ); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：采购订单中:供应商采购金额排行TOP10 **/
	public String slowLoadPurOrderSupplierMoney() {
		slowQueryPurOrderSupplierMoney();
		return "slowLoadPurOrderSupplierMoney";
	}
	/** 慢加载：采购订单中:供应商采购金额排行TOP10 查询**/
	public void slowQueryPurOrderSupplierMoney() {
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
			Map<String, Object> returnMap = purchaseDataService.slowQueryPurOrderSupplierMoneyTop(hsMap);
			Gson gosn = new Gson();
			getRequest().setAttribute("nameArr",  gosn.toJson(returnMap.get("nameArr")).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", returnMap.get("valueArr").toString() ); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 慢加载：采购订单中:供应商采购订单数排行TOP10 **/
	public String slowLoadPurOrderSupplierQuantity() {
		slowQueryPurOrderSupplierQuantity();
		return "slowLoadPurOrderSupplierQuantity";
	}
	/** 慢加载：采购订单中:供应商采购订单数排行TOP10 查询**/
	public void slowQueryPurOrderSupplierQuantity() {
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
			Map<String, Object> returnMap = purchaseDataService.slowQueryPurOrderSupplierQuantityTop(hsMap);
			Gson gosn = new Gson();
			getRequest().setAttribute("nameArr",  gosn.toJson(returnMap.get("nameArr")).replaceAll("\"", "\\\"") );
			getRequest().setAttribute("valueArr", returnMap.get("valueArr").toString() ); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购统计仪表盘>采购订单分析列表 **/
	public void goPurchaseStatisticsTable() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			String mytitle = getDecodeRequestParameter("mytitle");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select toone.name as suppliername,count(*) as ordernum, IFNULL(sum(many.totalAmount), 0) as ordermoney,many.purchaseperson,DATE_FORMAT(many.createtime, '%Y-%m-%d %H:%i:%s' ) as mcreatetime ");
				tableSql.append(" from pur_order  many INNER JOIN srm_supplier toone on  many.SUPPLIER_ID=toone.id "); 
				tableSql.append(" and many.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and many.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.name IS NOT NULL AND toone.name <> '' ");
				if (StringUtils.isNotEmpty(mytitle)) {
				tableSql.append(" and toone.name like '%" + mytitle.trim() + "%' ");
				}
				tableSql.append(" group by many.SUPPLIER_ID ");
				tableSql.append(" order BY many.CREATETIME desc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**导出: 采购统计仪表盘>采购订单分析列表 **/
	public void outExcelToPurchaseStatisticsTable() {
		try {
			Employee employee = getEmployee();
			String mytitle = getDecodeRequestParameter("mytitle");
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select toone.name as suppliername,count(*) as ordernum,IFNULL(sum(many.totalAmount), 0) as ordermoney,many.purchaseperson,DATE_FORMAT(many.createtime, '%Y-%m-%d %H:%i:%s' ) as mcreatetime ");
				tableSql.append(" from pur_order  many INNER JOIN srm_supplier toone on  many.SUPPLIER_ID=toone.id "); 
				tableSql.append(" and many.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");
				tableSql.append(" and many.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");
				tableSql.append(" and toone.name IS NOT NULL AND toone.name <> '' ");
				if (StringUtils.isNotEmpty(mytitle)) {
				tableSql.append(" and toone.name like '%" + mytitle.trim() + "%' ");
				}
				tableSql.append(" group by many.SUPPLIER_ID ");
				tableSql.append(" order BY many.CREATETIME desc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlPurchaseStatisticsTable", tableSql.toString());
				ArrayList<PurchaseOrderAnalysisVo>  purchaseOrderAnalysisVoList = purchaseDataService.outExcelToPurchaseStatisticsTable(hsMap);
				exportPurchaseStatisticsExcel(purchaseOrderAnalysisVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>采购统计仪表盘>采购订单分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'采购统计仪表盘>采购订单分析列表' **/
	public void exportPurchaseStatisticsExcel(ArrayList<PurchaseOrderAnalysisVo> purchaseOrderAnalysisVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "采购订单分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseOrderAnalysis_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseOrderAnalysis_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseOrderAnalysisVoList", purchaseOrderAnalysisVoList);
					xlsArea.applyAt(new CellRef("purchaseOrderAnalysisVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	public List<Supplier> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<Supplier> supplierList) {
		this.supplierList = supplierList;
	}
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	/////////分割线////////////////////分割线////////////////////分割线////////////////////分割线////////////////////分割线////////////////////分割线///////////
	/** 采购智能分析>类型结构分析**/
	public String goStructureAnalysisView() {
		try {
			Map<String, Object> params = getParams();
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("status," + SearchCondition.EQUAL, "3");
				supplierList = vixntBaseService.findAllByConditions(Supplier.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer jsonObj =  new StringBuffer();
		jsonObj.append("{");
		jsonObj.append("\"value\":");
		jsonObj.append("[");
			jsonObj.append("{");
			jsonObj.append("\"id\": \""+"all"+"\",");
			jsonObj.append("\"word\": \""+"全部供应商"+"\"");
			jsonObj.append("}");
		if(supplierList!=null && supplierList.size()>0){ 
			for(int x=0;x<supplierList.size();x++) {
					jsonObj.append(",{");
					jsonObj.append("\"id\": \""+supplierList.get(x).getId()+"\",");
					jsonObj.append("\"word\": \""+supplierList.get(x).getName()+"\"");
					jsonObj.append("}");
			}
		}
		jsonObj.append("]");
		jsonObj.append("}");
		getRequest().setAttribute("jsonObj",jsonObj.toString());
		getRequest().setAttribute("todayStr",MyTool.getTodayBy_yyyyMMdd());
		return "goStructureAnalysisView";
	}
	/** 采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  **/
	public void queryStructurePurchaseTopView() {  
		try {
			Employee employee = getEmployee();
			Map<String, Object> hsMap = new HashMap<String, Object>();
			ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			}
			hsMap.put("supplierID",supplierID);
			hsMap.put("timeArr",timeArr);
			hsMap.put("controlSQL",controlSQL);//PurchaseStructureBarAndPieTop  采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名
			String jsonStr = queryTopByTreeToJson(hsMap,categoryId);  
			renderJson(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  table **/
	public void queryStructurePurchaseTopTable() {
		try {
			String categoryArrMoney = getDecodeRequestParameter("categoryArrMoney");
			String categoryArrName = getDecodeRequestParameter("categoryArrName");   
			String categoryTotalDouble = getDecodeRequestParameter("categoryTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if( (StringUtils.isNotEmpty(categoryArrMoney) && categoryArrMoney.startsWith("[") && categoryArrMoney.endsWith("]")) 
			 && (StringUtils.isNotEmpty(categoryArrName) && categoryArrName.startsWith("[") && categoryArrName.endsWith("]")) 
			  ){
				JSONArray arrMoney = new JSONArray(categoryArrMoney);
				JSONArray arrName = new JSONArray(categoryArrName);
				if(arrMoney !=null && arrMoney.length()>0){
				  for(int x=0;x<arrMoney.length();x++) {  
					intState++;
				    String money = arrMoney.get(x)+"";
				    String name = arrName.get(x)+"";
				    Double upper = Double.valueOf((StringUtils.isNotEmpty(money) ? money : "0.0"  ));
				    Double down = Double.valueOf(  ( (StringUtils.isNotEmpty(categoryTotalDouble) && Double.valueOf(categoryTotalDouble)>0.0 )  ? categoryTotalDouble : "0.000000000000001"  )    );
				    Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
					if(x==0){
						sqlBuffer.append(" select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}else{
						sqlBuffer.append("  union all  select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as name,null as numsign,null as money,null as pro ");
			}
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.name,t.money,t.pro from  ("+sqlBuffer.toString()+")t order by t.numsign asc ");
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
				if(intState == 0){
					tablePager = new Pager();
				}
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/////下面是处理 树形排行的方法    /////
	/** 通过循环结合递归查询:采购智能分析>类型结构分析>产品类别排行柱图和饼图一起查询  TOP10  树形结构排名  **/
	public String queryTopByTreeToJson(Map<String, Object> hsMap,String categoryId) {
		String returnStr ="";
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			MapBeanDouble mapDou = new MapBeanDouble();
			List<MapBeanDouble> listMapDou = new ArrayList<MapBeanDouble>();
			Map<String, Object> params = getParams();
			if (null != categoryId && !"".equals(categoryId)) {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", categoryId, params);
				/** 如果 listItemCatalog == null || listItemCatalog.size()==0 说明：到分类的底层级别了，当前无子分类就，那也应该查询下当前类别数据  **/
				if(listItemCatalog == null || listItemCatalog.size()==0) {  
					ItemCatalog logByID = vixntBaseService.findEntityById(ItemCatalog.class,categoryId);
					if(logByID !=null ){
						listItemCatalog.add(logByID);
					}
				}
				if(listItemCatalog!=null && listItemCatalog.size()>0) {
					for(int x=0;x<listItemCatalog.size();x++){
						mapDou = new MapBeanDouble( (listItemCatalog.get(x).getName()) , (queryDoubleMoney(0.0, new ArrayList<ItemCatalog>(listItemCatalog.get(x).getSubItemCatalogs()),hsMap,listItemCatalog.get(x).getId())) );						
						listMapDou.add(mapDou);
						mapDou = new MapBeanDouble();
					}
				}
			}else {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
				if(listItemCatalog!=null && listItemCatalog.size()>0) {
					for(int x=0;x<listItemCatalog.size();x++){
						mapDou = new MapBeanDouble( (listItemCatalog.get(x).getName()) , (queryDoubleMoney(0.0, new ArrayList<ItemCatalog>(listItemCatalog.get(x).getSubItemCatalogs()),hsMap,listItemCatalog.get(x).getId())) );						
						listMapDou.add(mapDou);
						mapDou = new MapBeanDouble();						
					}
				}
			}
			if(listMapDou!=null && listMapDou.size()>0){
				/** 先去0：把数据中的0.0去掉 **/
				List<MapBeanDouble> listMapDouNew = new ArrayList<MapBeanDouble>();
				for(MapBeanDouble mBean:listMapDou){
					if(mBean !=null && (mBean.getValue()>0.0) ){
						listMapDouNew.add(mBean);
					}
				}
				listMapDou = listMapDouNew;
				/** 后排序：从大到小，为取数据 **/
				ComparatorMapBeanDoubleDX mySort = new ComparatorMapBeanDoubleDX() ;
				Collections.sort(listMapDou, mySort) ;
			}
			StringBuffer barJsonTopTen = sortAfterTakeOutBarJsonTopTen(listMapDou);
			StringBuffer pieJsonTopTen = sortAfterTakeOutPieJsonTopTen(listMapDou,"10");
			StringBuffer tableJsonForQuery = sortAfterTakeOutTableJsonForQuery(listMapDou);  
			returnStr = MyTool.mergeJsonStringTwo((barJsonTopTen.toString()),(pieJsonTopTen.toString()),(tableJsonForQuery.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	/** 排序之后传递查询到的数据封装成几个数组，用于查询列表,为拼装 sql语句使用  **/
	public StringBuffer sortAfterTakeOutTableJsonForQuery(List<MapBeanDouble> dataList) throws ParseException {
		ArrayList<String> nameArrTable = new ArrayList<String>();
		ArrayList<String> valueArrTable = new ArrayList<String>();
		Double totalDouble = 0.0;//totalNum 记录‘和值’
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArrTable.add(dataList.get(x).getKey());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				Double dTemp = dataList.get(x).getValue();
				valueArrTable.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dTemp)));
				totalDouble += dTemp;
			}
		}
		/*for(int x=0;x<nameArrTable.size();x++){ //数值倒序
			伞翼无人机             ..........           45796000.00
			旋翼无人机             ..........           49231.00
			扑翼无人机             ..........           2697.00
		}*/
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrTable\":" + gosn.toJson(nameArrTable));
		returnBufferJson.append(",\"totalDouble\":" + "\""+MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(totalDouble))+"\"" );   
		returnBufferJson.append(",\"valueArrTable\":" + valueArrTable.toString() + "}" );
		return returnBufferJson;
	}
	/** 排序之后取出用于柱图展示的top10**/
	public StringBuffer sortAfterTakeOutBarJsonTopTen(List<MapBeanDouble> dataList) throws ParseException {
		ArrayList<String> nameArrBarTen = new ArrayList<String>();
		ArrayList<String> valueArrBarTen = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				if(x==10){//top10，就跳出
					break;
				}
				nameArrBarTen.add(dataList.get(x).getKey());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				valueArrBarTen.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue())));
			}
		}
		 for (int start = 0, end = nameArrBarTen.size() - 1; start < end; start++, end--) {
			 String temp = nameArrBarTen.get(end);
			 nameArrBarTen.set(end, nameArrBarTen.get(start));
			 nameArrBarTen.set(start, temp);
		 }
		 for (int start = 0, end = valueArrBarTen.size() - 1; start < end; start++, end--) {
			 String temp = valueArrBarTen.get(end);
			 valueArrBarTen.set(end, valueArrBarTen.get(start));
			 valueArrBarTen.set(start, temp);
		 }
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrBarTen\":" + gosn.toJson(nameArrBarTen));
		returnBufferJson.append(",\"valueArrBarTen\":" + valueArrBarTen.toString() + "}" );
		return returnBufferJson;
	}
	/** 排序之后取出用于饼图展示的top10   注意：超过top10，top11,top12....合并为top10处理**/
	public StringBuffer sortAfterTakeOutPieJsonTopTen(List<MapBeanDouble> dataList,String otherTopNum) throws ParseException {
		ArrayList<String> nameArrPieTen = new ArrayList<String>();
		ArrayList<String> valueArrPieTen = new ArrayList<String>();
		Integer otherTopNumInt = Integer.parseInt(otherTopNum);
		Integer otherTopNumIntSmall = otherTopNumInt-1;
		if(dataList !=null && dataList.size()>0){
			if( dataList.size() <=otherTopNumIntSmall ){
				for(int x=0;x<dataList.size();x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue()));//钱
					String name = dataList.get(x).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				}
			}else{
				Double temp = 0.0;
				 for(int x=0;x<otherTopNumIntSmall;x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(x).getValue()));//钱
					String name = dataList.get(x).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				 }
				 if(dataList.size() == otherTopNumInt ){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(dataList.get(otherTopNumIntSmall).getValue()));//钱
					String name = dataList.get(otherTopNumIntSmall).getKey();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
				 }else{
					 for(int x=otherTopNumIntSmall;x<dataList.size();x++){
						 Double money = dataList.get(x).getValue();//钱
						 temp += money;
					 }
					 valueArrPieTen.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(temp))+"");//钱
					 nameArrPieTen.add("其他");
				 }
			}
	}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArrPieTen\":" + gosn.toJson(nameArrPieTen));
		returnBufferJson.append(",\"valueArrPieTen\":" + valueArrPieTen.toString() + "}" );
		return returnBufferJson;
	}
	/** 递归方式查询 :  树形结构排名   **/
	private Double queryDoubleMoney(Double doubleTotal, List<ItemCatalog> listItemCatalog,Map<String, Object> hsMap,String logID) throws Exception {
		if(listItemCatalog != null && listItemCatalog.size()>0){
			for (int i = 0; i < listItemCatalog.size(); i++) {
				ItemCatalog ic = listItemCatalog.get(i);
				if (ic.getSubItemCatalogs().size() > 0) {
					hsMap.put("itemcatalogID", ic.getId());
					Double num = purchaseDataService.queryStructurePurchaseTopView(hsMap);
					doubleTotal = doubleTotal+num;
					queryDoubleMoney(doubleTotal, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()) ,hsMap,ic.getId());
				} else {
					hsMap.put("itemcatalogID", ic.getId());
					Double num = purchaseDataService.queryStructurePurchaseTopView(hsMap);
					doubleTotal = doubleTotal+num;
				}
			}
		}else{
			hsMap.put("itemcatalogID", logID);
			Double num = purchaseDataService.queryStructurePurchaseTopView(hsMap);
			doubleTotal = doubleTotal+num;
		}
		return doubleTotal;
	}
	////////上面是处理 树形排行的方法    ////////////////////////////////////////////////////////////////////////////////////分割线///
	/** 导出采购智能分析 > 类型结构分析 > 产品类别采购分析列表  **/
	public void outExcelToStructurePurchaseTopTable() {
		try {
			String categoryArrMoney = getDecodeRequestParameter("categoryArrMoney");
			String categoryArrName = getDecodeRequestParameter("categoryArrName");   
			String categoryTotalDouble = getDecodeRequestParameter("categoryTotalDouble");
			StringBuffer sqlBuffer = new StringBuffer();
			int intState = 0;
			if( (StringUtils.isNotEmpty(categoryArrMoney) && categoryArrMoney.startsWith("[") && categoryArrMoney.endsWith("]")) 
			 && (StringUtils.isNotEmpty(categoryArrName) && categoryArrName.startsWith("[") && categoryArrName.endsWith("]")) 
			  ){
				JSONArray arrMoney = new JSONArray(categoryArrMoney);
				JSONArray arrName = new JSONArray(categoryArrName);
				if(arrMoney !=null && arrMoney.length()>0){
				  for(int x=0;x<arrMoney.length();x++) {  
					intState++;
				    String money = arrMoney.get(x)+"";
				    String name = arrName.get(x)+"";
				    Double upper = Double.valueOf((StringUtils.isNotEmpty(money) ? money : "0.0"  ));
				    Double down = Double.valueOf(  ( (StringUtils.isNotEmpty(categoryTotalDouble) && Double.valueOf(categoryTotalDouble)>0.0 )  ? categoryTotalDouble : "0.000000000000001"  )    );
				    Double pro = MyTool.roundingDoubleAppointDecimal((upper/down),4);//pro 占比
					if(x==0){
						sqlBuffer.append(" select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}else{
						sqlBuffer.append("  union all  select '"+name+"' as name,"+x+" as numsign,'"+money+"' as money,'"+(pro*100)+"' as pro");
					}
				  }
				}
			}
			/** intState==0时，必须拼装一个可执行的空子查询sql **/
			if(intState == 0){
				sqlBuffer = new StringBuffer(" select null as name,null as numsign,null as money,null as pro ");
			}
			Employee employee = getEmployee();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				StringBuffer tableSql = new StringBuffer();
				tableSql.append(" select t.name,t.money,t.pro from  ("+sqlBuffer.toString()+")t order by t.numsign asc ");
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("outExcelSqlStructurePurchaseTop", tableSql.toString());
				ArrayList<PurchaseSupplierCostVo>  purchaseSupplierCostVoList = purchaseDataService.outExcelToStructurePurchaseTopTable(hsMap);
				exportStructurePurchaseTopExcel(purchaseSupplierCostVoList);
				vixOperateLog.saveOperateLog("", "", "",employee.getName() + "!导出'采购智能分析>类型结构分析>产品类别采购分析列表'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 服务  导出'产品类别采购分析列表' **/
	public void exportStructurePurchaseTopExcel(ArrayList<PurchaseSupplierCostVo> purchaseSupplierCostVoList) {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");
			String ua = getRequest().getHeader("user-agent");
			String fileName = "产品类别采购分析列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseStructureTop_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseStructureTop_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseSupplierCostVoList", purchaseSupplierCostVoList);
					xlsArea.applyAt(new CellRef("purchaseSupplierCostVo!A1"), context);
					transformer.write();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

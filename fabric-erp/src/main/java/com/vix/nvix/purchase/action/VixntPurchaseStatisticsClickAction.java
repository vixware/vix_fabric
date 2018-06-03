package com.vix.nvix.purchase.action;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.inquire.entity.PurchaseInquire;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**@date 2018年3月16日 VixntPurchaseStatisticsClickAction为 采购智能分析 > 采购统计仪表盘 的数字点击查询服务等...
 */
@Controller
@Scope("prototype")
public class VixntPurchaseStatisticsClickAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String queryTime;
	private String queryWhat;//查询什么,queryWhat专为页面上的数字点击事件服务!
	private String goToWhere;//页面上的数字点击事件,点击后,要去哪个页面?
	private String returnPage;//页面上的数字点击事件,查询后,要返回哪个页面?
	private String backupsStr;//页面上的数字点击事件中备份字符串,以备使用
	
	/** 点击数字查询  "本年采购询价"   **/
	public String clickLookPurchaseInquiry() {
		return "clickLookPurchaseInquiry";
	}
	 /** 点击数字查询  "本年采购询价" 列表查询   **/
	public void clickLookPurchaseInquiryQuery() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> params = getParams();
			Pager pager = this.getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				String name = getDecodeRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				String purchasePerson = getDecodeRequestParameter("purchasePerson");
				if (StringUtils.isNotEmpty(purchasePerson)) {
					params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
				}
				String code = getDecodeRequestParameter("code");
				if (StringUtils.isNotEmpty(code)) {
					params.put("code," + SearchCondition.ANYLIKE, code);
				}
				String createTime = getRequestParameter("createTime");
				if (StringUtils.isNotEmpty(createTime)) {
					params.put("appDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
				}
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseInquire.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "本年采购计划"   **/
	public String clickLookPurchasePlan() {
		return "clickLookPurchasePlan";
	}
	/** 点击数字查询  "本年采购计划" 列表查询   **/
	public void clickLookPurchasePlanQuery() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> params = getParams();
			Pager pager = this.getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("isReport," + SearchCondition.EQUAL, "0");
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				String name = getDecodeRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("purchasePlanName," + SearchCondition.ANYLIKE, name);
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "本年采购退货单 "   **/
	public String clickLookPurchaseReturn() {
		return "clickLookPurchaseReturn";
	}
	/** 点击数字查询  "本年采购计划" 列表查询   **/
	public void clickLookPurchaseReturnQuery() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> params = getParams();
			Pager pager = this.getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				String name = getDecodeRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseReturn.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//LookPurchaseOrderListShow负责采购智能分析 > 采购统计仪表盘 (本月订单金额环比&本月订单金额同比&本年采购订单总金额&本年已完成采购订单金额&本年已完成采购订单数&本年在途采购订单数)  的展示
	/** 点击数字查询  "..."   
	 LookPurchaseOrderListShow负责采购智能分析 > 采购统计仪表盘 (本月订单金额环比&本月订单金额同比&本年采购订单总金额&本年已完成采购订单金额&本年已完成采购订单数&本年在途采购订单数)  的展示 **/
	public String clickLookPurchaseOrderListShow() {
		return "clickLookPurchaseOrderListShow";
	}
	/** 点击数字查询  "本年采购计划" 列表查询   
	 LookPurchaseOrderListShow负责采购智能分析 > 采购统计仪表盘 (本月订单金额环比&本月订单金额同比&本年采购订单总金额&本年已完成采购订单金额&本年已完成采购订单数&本年在途采购订单数)  的展示  **/
	public void clickLookPurchaseOrderListShowQuery() {
		try {
			String stateIN = "";  
			/**采购订单 PurchaseOrder status 0,待配货 1,代发货 2,配送中 3,已完成 4,待分拣    这里用 abcde代表其 status **/
			if(StringUtils.isNotEmpty(backupsStr)) {
				backupsStr = decode(backupsStr, "UTF-8");
			}
			if(StringUtils.isNotEmpty(backupsStr) && backupsStr.contains("!")  ) {
				String[] splitArr = backupsStr.split("!");
				String purchaseOrderStatus = splitArr[0].toString();
				if(StringUtils.isNotEmpty(purchaseOrderStatus) && purchaseOrderStatus.equals("all")  ) {
					stateIN = "";
				}else if(StringUtils.isNotEmpty(purchaseOrderStatus) && !purchaseOrderStatus.equals("all") ) {
					char[] charArray = purchaseOrderStatus.toCharArray();
					for(int x=0;x<charArray.length;x++) {
						String c = (charArray[x]+"");
						if(x==0) {
							if(c.equals("a")) {stateIN += "0";}
							if(c.equals("b")) {stateIN += "1";}
							if(c.equals("c")) {stateIN += "2";}	
							if(c.equals("d")) {stateIN += "3";}
							if(c.equals("e")) {stateIN += "4";}
						}else {
							if(c.equals("a")) {stateIN += ",0";}
							if(c.equals("b")) {stateIN += ",1";}	
							if(c.equals("c")) {stateIN += ",2";}
							if(c.equals("d")) {stateIN += ",3";}
							if(c.equals("e")) {stateIN += ",4";}	
						}
					}
				}
			}
			Employee employee = getEmployee();
			Map<String, Object> params = getParams();
			Pager pager = this.getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				String name = getDecodeRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("createTime");
				}
				if(StringUtils.isNotEmpty(stateIN)) {
					params.put("status," + SearchCondition.IN, stateIN);
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 为做原型页面时,原型'空列表'服务   **/
	public void emptyList() {
		renderDataTable(getPager());
	}
	public String getBackupsStr() {
		return backupsStr;
	}
	public void setBackupsStr(String backupsStr) {
		this.backupsStr = backupsStr;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getQueryWhat() {
		return queryWhat;
	}
	public void setQueryWhat(String queryWhat) {
		this.queryWhat = queryWhat;
	}
	public String getGoToWhere() {
		return goToWhere;
	}
	public void setGoToWhere(String goToWhere) {
		this.goToWhere = goToWhere;
	}
	public String getReturnPage() {
		return returnPage;
	}
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
	
}

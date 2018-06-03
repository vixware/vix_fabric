package com.vix.nvix.sales.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.order.entity.SalesOrder;

/**@date 2018年3月16日  为 销售智能分析 > 销售统计仪表盘 的数字点击查询服务等...
 */
@Controller
@Scope("prototype")
public class NvixntSalesStatisticsClickAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String queryTime;
	private String queryWhat;//查询什么,queryWhat专为页面上的数字点击事件服务!
	private String goToWhere;//页面上的数字点击事件,点击后,要去哪个页面?
	private String returnPage;//页面上的数字点击事件,查询后,要返回哪个页面?
	private String backupsStr;//页面上的数字点击事件中备份字符串,以备使用
	/** 点击数字查询  "本月销售订单数" 或 "本月销售金额" 或 "今日销售订单数" 或 "今日销售金额"   **/
	public String clickSeeNumOrMoneyOfSalesOrders() {
		return "clickSeeNumOrMoneyOfSalesOrders";
	}
	/** 点击数字查询  "本月销售订单数" 或 "本月销售金额" 或 "今日销售订单数" 或 "今日销售金额"  列表   **/
	public void clickSeeNumOrMoneyOfSalesOrdersQuery() {
		try {
			Employee employee = getEmployee();
			Map<String, Object> params = getParams();
			Pager pager = this.getPager();
			String[] excludes = {" "};
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("isTemp," + SearchCondition.EQUAL, "0");
				params.put("isDeleted," + SearchCondition.EQUAL, "0");
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("billDate," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				String title = getDecodeRequestParameter("title");
				if (StringUtils.isNotEmpty(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				String code = getDecodeRequestParameter("code");
				if(StringUtils.isNotEmpty(code)){
					params.put("code,"+SearchCondition.ANYLIKE,code);
				}
				String salePerson = getDecodeRequestParameter("salePerson");
				if(StringUtils.isNotEmpty(salePerson)){
					params.put("salePerson.name,"+SearchCondition.ANYLIKE,salePerson);
				}
				String createTime = getDecodeRequestParameter("createTime");
				if(StringUtils.isNotEmpty(createTime)){
					params.put("billDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("billDate");
				}
				String customerAccountName = getDecodeRequestParameter("customerAccountName");
				if(StringUtils.isNotEmpty(customerAccountName)){
					params.put("customerAccount.name,"+SearchCondition.ANYLIKE,customerAccountName);
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			}
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "本月申请退货订单数"   **/
	public String clickSeeReturnGoods() {
		return "clickSeeReturnGoods";
	}
	/** 点击数字查询  "本月申请退货订单数"   列表   **/
	public void clickSeeReturnGoodsQuery() {
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
					params.put("returnTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				String code = getDecodeRequestParameter("code");
				if(StringUtils.isNotEmpty(code)){
					params.put("code,"+SearchCondition.ANYLIKE,code);
				}
				String createTime = getDecodeRequestParameter("createTime");
				if(StringUtils.isNotEmpty(createTime)){
					params.put("returnTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
				}
				if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
					pager.setOrderBy("desc");
					pager.setOrderField("returnTime");
				}
				String customerAccountName = getDecodeRequestParameter("customerAccountName");
				if(StringUtils.isNotEmpty(customerAccountName)){
					params.put("customerAccount.name,"+SearchCondition.ANYLIKE,customerAccountName);
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, SaleReturnForm.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "本月新客户"   **/
	public String clickSeeTheNewCustomer() {
		return "clickSeeTheNewCustomer";
	}
	/** 点击数字查询  "本月新客户"   列表    **/
	public void clickSeeTheNewCustomerQuery() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				String tenantIdStr = SecurityUtil.getCurrentUserTenantId();
				String innerCodeStr = SecurityUtil.getCurrentEmpOrgInnerCode();
				StringBuilder hqlA = new StringBuilder();
				hqlA.append(" select m.customeraccount_id from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id ");  
				hqlA.append(" and m.tenantId='"+tenantIdStr+"'"); 
				hqlA.append(" and m.companyInnerCode='"+innerCodeStr+"'");  
				hqlA.append(" and m.istemp='0' and m.isdeleted='0' ");
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				hqlA.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				hqlA.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				hqlA.append(" and m.customeraccount_id >'' ");
				hqlA.append(" and m.customeraccount_id not in( ");
				hqlA.append(" select distinct(m.customeraccount_id) from sale_salesorder m where 1=1 "); 
				hqlA.append(" and m.tenantId='"+tenantIdStr+"'"); 
				hqlA.append(" and m.companyInnerCode='"+innerCodeStr+"'");  
				hqlA.append(" and m.istemp='0' and m.isdeleted='0' and m.customeraccount_id is not null ");
				hqlA.append(" and m.billdate < "+ MyTool.StringUseToSql(timeArr.get(0)));   
				hqlA.append(" ) ");
				StringBuilder tableSql = new StringBuilder();  
				tableSql.append(" select t.name as tname,sum(ifnull(m.amountTotal,0)) as tnum from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id ");  
				tableSql.append(" and m.tenantId='"+tenantIdStr+"'"); 
				tableSql.append(" and m.companyInnerCode='"+innerCodeStr+"'");  
				tableSql.append(" and m.istemp='0' and m.isdeleted='0' and m.customeraccount_id <> '' and m.customeraccount_id is not null and t.name <> '' and t.name is not null ");
				tableSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and m.customeraccount_id in("+hqlA+") ");
				tableSql.append(" group by m.customeraccount_id order by sum(ifnull(m.amountTotal,0)) desc ");
				tablePager.setOrderField(null);
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "今日客户数"   **/
	public String clickSeeTheTodayCustomer() {
		return "clickSeeTheTodayCustomer";
	}
	/** 点击数字查询  "今日客户数"   列表    **/
	public void clickSeeTheTodayCustomerQuery() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				String tenantIdStr = SecurityUtil.getCurrentUserTenantId();
				String innerCodeStr = SecurityUtil.getCurrentEmpOrgInnerCode();
				StringBuilder hqlA = new StringBuilder();
				hqlA.append(" select m.customeraccount_id from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id ");  
				hqlA.append(" and m.tenantId='"+tenantIdStr+"'"); 
				hqlA.append(" and m.companyInnerCode='"+innerCodeStr+"'");  
				hqlA.append(" and m.istemp='0' and m.isdeleted='0' ");
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				hqlA.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				hqlA.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				hqlA.append(" and m.customeraccount_id >'' ");
				StringBuilder tableSql = new StringBuilder();  
				tableSql.append(" select t.name as tname,sum(ifnull(m.amountTotal,0)) as tnum from sale_salesorder m inner join crm_customeraccount t on m.customeraccount_id = t.id ");  
				tableSql.append(" and m.tenantId='"+tenantIdStr+"'"); 
				tableSql.append(" and m.companyInnerCode='"+innerCodeStr+"'");  
				tableSql.append(" and m.istemp='0' and m.isdeleted='0' and m.customeraccount_id <> '' and t.name <> '' and m.customeraccount_id is not null and t.name is not null ");
				tableSql.append(" and m.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and m.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" and m.customeraccount_id in("+hqlA+") ");
				tableSql.append(" group by m.customeraccount_id order by sum(ifnull(m.amountTotal,0)) desc ");
				tablePager.setOrderField(null);
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 点击数字查询  "今日销售产品种类"   **/
	public String clickSeeTypesOfSalesProducts() {
		return "clickSeeTypesOfSalesProducts";
	}
	/** 点击数字查询  "今日销售产品种类"    列表    **/
	public void clickSeeTypesOfSalesProductsQuery() {
		try {
			Employee employee = getEmployee();
			Pager tablePager = getPager();
			if(employee != null && SecurityUtil.getCurrentUserTenantId() != null  && SecurityUtil.getCurrentEmpOrgInnerCode() != null ){
				String tenantIdStr = SecurityUtil.getCurrentUserTenantId();
				String innerCodeStr = SecurityUtil.getCurrentEmpOrgInnerCode();
				ArrayList<String> timeArr = (ArrayList<String>)MyTool.getTimeArrByHtmlParameterString(queryTime);
				StringBuilder tableSql = new StringBuilder();
				tableSql.append(" select tb.name as tname ,sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) as tnum,tb.code as code  from SALE_SALEORDERITEM m inner "
						+ "join sale_salesorder t on m.salesorder_id = t.id  inner join MDM_ITEM tb on m.item_id = tb.id  ");  
				tableSql.append(" and t.tenantId='"+tenantIdStr+"'"); 
				tableSql.append(" and t.companyInnerCode='"+innerCodeStr+"'"); 
				tableSql.append(" and t.istemp='0' and t.isdeleted='0' and tb.name is not null and tb.name <> '' "); 
				tableSql.append(" and t.billdate >= "+ MyTool.StringUseToSql(timeArr.get(0)));
				tableSql.append(" and t.billdate <  "+ MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size()-1))));
				tableSql.append(" group by m.item_id order by sum( (ifnull(m.amount, 0)) * (ifnull(m.price, 0)) ) desc ");
				tablePager.setOrderField(null);
				this.baseHibernateService.findPagerBySqlFull(tablePager, tableSql.toString(), new HashMap<String, Object>());
			}
			renderDataTable(tablePager);
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
	public String getBackupsStr() {
		return backupsStr;
	}
	public void setBackupsStr(String backupsStr) {
		this.backupsStr = backupsStr;
	}
	
}

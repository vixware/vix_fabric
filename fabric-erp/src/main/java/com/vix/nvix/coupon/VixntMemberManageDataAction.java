package com.vix.nvix.coupon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.properties.util.MyTool;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.service.IQueryDataService;

@Controller
@Scope("prototype")
public class VixntMemberManageDataAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private CustomerAccount customerAccount;
	private CustomerAccountClip customerAccountClip;
	@Autowired
	private IQueryDataService queryDataService;
	private String queryTime;
	private String startTime;
	private String endTime;
	private String signStr;

	private String changeSQL;
	private String fromPage;
	private String frequency;// 频率，次数 ;
	/**
	 * 会员管理>会员列表(默认显示本月新增会员) 列表显示 guo
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goSingleListStatistics() {
		// NewAdd 代表新增会员 | NewAdd{Today} NewAdd{ThisMonthOT} NewAdd{Yesterday}
		// 查询今日，昨日，本月 |ThisMonthOnlyTwo代表查询本月，但不要每天，只要首日，末日2天即可(用ThisMonthOT简写)
		try {
			String controlSQLWd = getDecodeRequestParameter("controlSQLWd");
			List<String> timeArr = new ArrayList<String>();
			int stateInt = -1;
			if (StringUtils.isNotEmpty(controlSQLWd)) {
				if (controlSQLWd.contains("NewAdd")) {// 查询‘新增’
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 0;
				} else if (controlSQLWd.contains("Birthday")) {// 查询‘过生日’
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 1;
				} else if (controlSQLWd.contains("Money")) {// 查询‘会员消费’
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 2;
				} else if (controlSQLWd.contains("Card")) {// 查询‘会员卡过期’
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 3;
				} else if (controlSQLWd.contains("OrderNum")) {// 查询‘本月订单数’列表
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 4;
				} else if (controlSQLWd.contains("IncreaseC")) {// 查询‘新增会员卡’
					String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					stateInt = 5;
				}
			}
			if (stateInt == 0) {// 查询‘新增’
				Map<String, Object> params = getParams();
				Pager pager = getPager();		
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
					params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
					params.put("channelDistributor.id,"+ SearchCondition.EQUAL, employee.getChannelDistributor().getId() );
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						params.put("mobilePhone," + SearchCondition.ANYLIKE, phone);
					}
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						params.put("name," + SearchCondition.ANYLIKE, selectName);
					}
					String carNumber = getDecodeRequestParameter("carNumber");
					if (StringUtils.isNotEmpty(carNumber)) {
						params.put("clipNumber," + SearchCondition.ANYLIKE, carNumber);
					}
					if (timeArr != null && timeArr.size() > 0) {// 查询‘新增’
						String startTime = timeArr.get(0);
						String endTime = timeArr.get(timeArr.size() - 1);
						params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
					}
					if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {// 查询‘新增’
						pager.setOrderBy("desc");
						pager.setOrderField("createTime");
					}
					pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
				}		
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					Map<String, Object> hsMap = new HashMap<String, Object>();
					hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
					hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
					if(params.containsKey("channelDistributor.id,"+ SearchCondition.EQUAL)){
						hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
					}
					CustomerAccount customer = (CustomerAccount) resultList.get(x);
					String customerID = customer.getId();
					CustomerAccountClip customerClip = new CustomerAccountClip();

					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// stateInt == 0
								// 代表查询‘开店到现在消费总金额’而不是‘昨日消费’‘今日消费’...
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");// selfExtendStringField1
																			// 查询会员开店到现在消费总金额
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					customer.setSelfExtendStringField1("" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					customer.setSelfExtendStringField2(expiryDate);// selfExtendStringField2‘会员卡有效期限’
					customer.setSelfExtendStringField3(typeNameStr);// selfExtendStringField3‘会员卡类型’
					resultList.set(x, customer);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			} else if (stateInt == 1) {// 查询‘过生日’
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				Pager myPager = this.getPager();

				StringBuffer fullSql = new StringBuffer();
				fullSql.append(" SELECT id from CRM_CUSTOMERACCOUNT ");
				fullSql.append(" where birthday is not null ");
				fullSql.append(" and TENANTID ='" + params.get("tenantId") + "' ");
				fullSql.append(" and COMPANYINNERCODE = '" + params.get("companyInnerCode") + "' ");
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					fullSql.append(" and name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					fullSql.append(" and mobilePhone like '%" + phone.trim() + "%' ");
				} // and DATE_FORMAT(birthday,'%m-%d') >= '11-03' and
					// DATE_FORMAT(birthday,'%m-%d') < '11-09'
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = MyTool.queryBirthdayBYyyyyMMddTwo(timeArr.get(0));
					String endTime = MyTool.queryBirthdayBYyyyyMMddTwo(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1)));// 先加一天，在取出‘11-08’月日
					fullSql.append(" and DATE_FORMAT(birthday,'%m-%d') >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and DATE_FORMAT(birthday,'%m-%d') < " + MyTool.StringUseToSql(endTime));
				}
				fullSql.append(" ORDER BY createTime desc ");

				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();

					Map<String, Object> hsMap = new HashMap<String, Object>();
					CustomerAccountClip customerClip = new CustomerAccountClip();

					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// '过生日' 也查询‘开店到现在消费总金额’而不是‘昨日消费’‘今日消费’...
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");// selfExtendStringField1
																			// 查询会员开店到现在消费总金额
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			} else if (stateInt == 2) {// 查询‘会员消费’
				Map<String, Object> params = new HashMap<String, Object>();
				Map<String, Object> hsMap = new HashMap<String, Object>();
				Pager myPager = this.getPager();
				StringBuffer fullSql = new StringBuffer();
				fullSql.append(" SELECT many.customerAccount_id as id  from DRP_REQUIREGOODSORDER many ");
				fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id and many.isTemp !=\'1\'  and many.type = '1'  ");
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
				}
				fullSql.append(" where many.customerAccount_id is not null ");
				fullSql.append(" and many.payTime is not null ");
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
					fullSql.append(" and many.CHANNELDISTRIBUTOR_ID='"+employee.getChannelDistributor().getId()+"' " );
				}else{
					fullSql.append(" and 1=2 ");
				}
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				fullSql.append(" and many.TENANTID ='" + SecurityUtil.getCurrentUserTenantId() + "' ");
				fullSql.append(" and many.COMPANYINNERCODE = '" + SecurityUtil.getCurrentEmpOrgInnerCode() + "' ");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
					fullSql.append(" and many.payTime >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and many.payTime <  " + MyTool.StringUseToSql(endTime));
				}
				fullSql.append(" GROUP BY many.customerAccount_id ");
				fullSql.append(" ORDER BY many.createTime desc ");

				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					CustomerAccountClip customerClip = new CustomerAccountClip();

					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// 查询‘会员消费’就是‘昨日消费’‘今日消费’...
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1Money");// selfExtendStringField1Money
																				// 查询当前时间消费总金额
						if (timeArr != null && timeArr.size() > 0) {
							String startTime = timeArr.get(0);
							String endTime = timeArr.get(timeArr.size() - 1);
							hsMap.put("startTimeMoney", startTime);
							hsMap.put("endTimeMoney", endTime);
						}
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			} else if (stateInt == 3) {// 查询‘会员卡过期’
				Map<String, Object> params = new HashMap<String, Object>();
				Pager myPager = this.getPager();
				StringBuffer fullSql = new StringBuffer();
				fullSql.append(" SELECT many.customerAccount_id as id  from CRM_CUSTOMERACCOUNTCILP many ");
				fullSql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone on many.customerAccount_id = toone.id ");
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
				}
				fullSql.append(" where many.customerAccount_id is not null and ( many.ISTEMP != '1') ");
				fullSql.append(" and many.expiryDate is not null ");
				fullSql.append(" and many.TENANTID ='" + SecurityUtil.getCurrentUserTenantId() + "' ");
				fullSql.append(" and many.COMPANYINNERCODE = '" + SecurityUtil.getCurrentEmpOrgInnerCode() + "' ");
				Employee employee = getEmployee();
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
					fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+employee.getChannelDistributor().getId()+"' " );
				}else{
					fullSql.append(" and 1=2 ");
				}
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
					fullSql.append(" and many.expiryDate >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and many.expiryDate <  " + MyTool.StringUseToSql(endTime));
				}
				fullSql.append(" GROUP BY many.customerAccount_id ");
				fullSql.append(" ORDER BY many.createTime desc ");

				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					CustomerAccountClip customerClip = new CustomerAccountClip();
					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// selfExtendStringField1 查询会员开店到现在消费总金额
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			} else if (stateInt == 4) {// 查询‘订单数’列表
				Map<String, Object> params = getParams();
				Pager pager = getPager();
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("channelDistributor.id,"+ SearchCondition.EQUAL, employee.getChannelDistributor().getId() );
					params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
					params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
					params.put("isTemp," + SearchCondition.NOEQUAL, "1");
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						params.put("customerAccount.mobilePhone," + SearchCondition.ANYLIKE, phone);
					}
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						params.put("customerAccount.name," + SearchCondition.ANYLIKE, selectName);
					}
					if (timeArr != null && timeArr.size() > 0) {
						String startTime = timeArr.get(0);
						String endTime = timeArr.get(timeArr.size() - 1);
						params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
					}
					if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
						pager.setOrderBy("desc");
						pager.setOrderField("payTime");
					}
					params.put("type," + SearchCondition.EQUAL, "1");
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				}
				renderDataTable(pager);
			} else if (stateInt == 5) {// 查询‘新增会员卡’
				Map<String, Object> params = new HashMap<String, Object>();
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("channelDistributorId", employee.getChannelDistributor().getId() );
				}
				Pager myPager = this.getPager();
				StringBuffer fullSql = new StringBuffer();
				fullSql.append(" SELECT many.customerAccount_id  as id  FROM CRM_CUSTOMERACCOUNTCILP many ");
				fullSql.append(" inner join CRM_CUSTOMERACCOUNT toone  on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') ");
				fullSql.append(" and many.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");  
				fullSql.append(" and many.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");  
				if(params.containsKey("channelDistributorId")){
					fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
				}else{
					fullSql.append(" and 1=2 ");  
				}
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
				}
				fullSql.append(" WHERE many.customerAccount_id is not null ");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
					fullSql.append(" and  many.CREATETIME >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and  many.CREATETIME <  " + MyTool.StringUseToSql(endTime));
				}
				fullSql.append(" ORDER BY  toone.CREATETIME desc ");
				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					Map<String, Object> hsMap = new HashMap<String, Object>();
					CustomerAccountClip customerClip = new CustomerAccountClip();
					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// selfExtendStringField1 查询会员开店到现在消费总金额
						hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
						hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
						if(params.containsKey("channelDistributorId")){
							hsMap.put("channelDistributorId", params.get("channelDistributorId"));
						}
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 会员管理>会员列表(默认显示本月新增会员) 列表显示 查询数据方法2 guo
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void goStatisticsData() {
		// IntoTS <!-- 进店Into the store 简写为‘IntoTS’代表‘进店客户’ -->//
		try {
			String controlSQLWd = getDecodeRequestParameter("controlSQLWd");
			List<String> timeArr = new ArrayList<String>();
			int stateInt = -1;
			if (StringUtils.isNotEmpty(controlSQLWd)) {
				if (controlSQLWd.startsWith("sui")) {
					if (controlSQLWd.startsWith("suiaAMACp{")) {// 'suiaAMACp{MemberAll}'会员总数，'suiaAMACp{ClipAll}'会员卡总数
						String str = MyTool.analysisJsonStringFive(controlSQLWd);
						if ("MemberAll".equals(str)) {
							stateInt = 23;
						} else if ("ClipAll".equals(str)) {
							stateInt = 24;
						}
					}
					if (controlSQLWd.startsWith("suicRemainderMoney{")) {// 会员卡总余额
						stateInt = 25;
					}
				} else {
					if (controlSQLWd.contains("IntoTS")) {// 查询‘进店客户’列表
						String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
						timeArr = new ArrayList<String>();
						timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
						stateInt = 21;
					} else if (controlSQLWd.contains("RechargeM")) {// 查询‘充值记录’列表
						String timeStr = MyTool.analysisJsonStringFive(controlSQLWd);
						timeArr = new ArrayList<String>();
						timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
						stateInt = 22;
					}
				}
			}
			if (stateInt == 25) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("channelDistributorId", employee.getChannelDistributor().getId() );
				}
				Pager myPager = this.getPager();

				StringBuffer fullSql = new StringBuffer();
				fullSql.append(" SELECT  customerAccount_id  as id FROM  CRM_CUSTOMERACCOUNTCILP many ");
				fullSql.append(" inner join CRM_CUSTOMERACCOUNT toone  on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') ");
				if(params.containsKey("tenantId")){
					fullSql.append(" and many.tenantId='"+params.get("tenantId")+"'");  
				}
				if(params.containsKey("companyInnerCode")){
					fullSql.append(" and many.companyInnerCode='"+params.get("companyInnerCode")+"'");  
				}
				if(params.containsKey("channelDistributorId")){
					fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
				}else{
					fullSql.append(" and 1=2 ");
				}
				fullSql.append(" and many.money is not null and many.money > 0 ");// 余额大于0
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
				}
				fullSql.append(" WHERE many.customerAccount_id is not null ");
				if (timeArr != null && timeArr.size() > 0) {
					String startTime = timeArr.get(0);
					String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
					fullSql.append(" and  many.CREATETIME >= " + MyTool.StringUseToSql(startTime));
					fullSql.append(" and  many.CREATETIME <  " + MyTool.StringUseToSql(endTime));
				}
				fullSql.append(" ORDER BY  toone.CREATETIME desc ");

				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();

					Map<String, Object> hsMap = new HashMap<String, Object>();
					hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
					hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
					if(params.containsKey("channelDistributorId")){
						hsMap.put("channelDistributorId",params.get("channelDistributorId"));
					}
					
					CustomerAccountClip customerClip = new CustomerAccountClip();

					Double remainderMoney = 0.0;// 会员卡总余额
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// remainderMoneySufngtfk 查询会员卡总余额
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "remainderMoneySufngtfk");
						remainderMoney = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("remainderMoney", "" + MyTool.roundingTwoDecimal(remainderMoney));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			}
			if (stateInt == 23 || stateInt == 24) {// 查询'suiaAMACp{MemberAll}'会员总数，'suiaAMACp{ClipAll}'会员卡总数
				timeArr = new ArrayList<String>();
				timeArr = MyTool.getTimeArrByHtmlParameterString("Today");

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("channelDistributorId", employee.getChannelDistributor().getId() );
				}
				Pager myPager = this.getPager();

				StringBuffer fullSql = new StringBuffer();
				if (stateInt == 24) {// 会员卡总数
					fullSql.append(" SELECT ");
					fullSql.append(" customerAccount_id  as id ");
					fullSql.append(" FROM ");
					fullSql.append(" CRM_CUSTOMERACCOUNTCILP many ");
					fullSql.append(" inner join CRM_CUSTOMERACCOUNT toone  on many.customerAccount_id = toone.id and ( many.ISTEMP != '1') ");
					fullSql.append(" and (many.isReport != 'Y') ");
					if(params.containsKey("tenantId")){
						fullSql.append(" and many.tenantId='"+params.get("tenantId")+"'");  
					}
					if(params.containsKey("companyInnerCode")){
						fullSql.append(" and many.companyInnerCode='"+params.get("companyInnerCode")+"'");  
					}
					if(params.containsKey("channelDistributorId")){
						fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
					}else{
						fullSql.append(" and 1=2 ");
					}
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
					}
					fullSql.append(" WHERE many.customerAccount_id is not null ");
					if (timeArr != null && timeArr.size() > 0) {
						String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
						fullSql.append(" and  many.CREATETIME <  " + MyTool.StringUseToSql(endTime));
					}
					fullSql.append(" ORDER BY  toone.CREATETIME desc ");
				}
				if (stateInt == 23) {// 会员总数
					fullSql.append(" SELECT id as id FROM  CRM_CUSTOMERACCOUNT toone where 1=1 ");
					if(params.containsKey("channelDistributorId")){
						fullSql.append(" and toone.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");
					}else{
						fullSql.append(" and 1=2 ");
					}
					fullSql.append(" and toone.companyInnerCode='"+params.get("companyInnerCode")+"'");
					fullSql.append(" and toone.tenantId='"+params.get("tenantId")+"'");
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						fullSql.append(" and toone.name like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						fullSql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
					}
					if (timeArr != null && timeArr.size() > 0) {
						String endTime = MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1));
						fullSql.append(" and  toone.CREATETIME <  " + MyTool.StringUseToSql(endTime));
					}
					fullSql.append(" ORDER BY  toone.CREATETIME desc ");
				}
				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, fullSql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					Map<String, Object> hsMap = new HashMap<String, Object>();
					hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
					hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
					if(params.containsKey("channelDistributorId")){
						hsMap.put("channelDistributorId",params.get("channelDistributorId"));
					}
					CustomerAccountClip customerClip = new CustomerAccountClip();
					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
						objectMap.put("isReport", customerClip.getIsReport());// 会员卡是否挂失
						objectMap.put("expiryDate", customerClip.getExpiryDate());// 会员卡到期
					} catch (Exception e) {
					}
					if (true) {// selfExtendStringField1 查询会员开店到现在消费总金额
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			}
			if (stateInt == 21) {// 查询‘进店客户’列表
				Map<String, Object> params = new HashMap<String, Object>();
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("channelDistributorId", employee.getChannelDistributor().getId() );
				}
				Pager myPager = this.getPager();
				StringBuffer hql = new StringBuffer();
				hql.append(" SELECT  aa as id FROM ( ");
				hql.append(" SELECT tooneBB.id as aa FROM ");
				hql.append(" CRM_CUSTOMERACCOUNT tooneBB ");
				hql.append(" inner JOIN  DRP_REQUIREGOODSORDER tooneAA on tooneAA.customerAccount_id = tooneBB.id and tooneAA.isTemp != \'1\' and tooneAA.type = '1' ");
				hql.append(" and tooneAA.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");  
				hql.append(" and tooneAA.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");  
				if(params.containsKey("channelDistributorId")){
					hql.append(" and tooneAA.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
				}else{
					hql.append(" and 1=2 ");  
				}
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					hql.append(" and tooneBB.name like '%" + selectName.trim() + "%' ");
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					hql.append(" and tooneBB.mobilePhone like '%" + phone.trim() + "%' ");
				}
				String cardNum = getDecodeRequestParameter("cardNum");
				if (StringUtils.isNotEmpty(cardNum)) {
					hql.append(" and tooneBB.clipNumber like '%" + cardNum.trim() + "%' ");
				}
				hql.append(" and tooneAA.CREATETIME >= " + MyTool.StringUseToSql(timeArr.get(0)));
				hql.append(" and tooneAA.CREATETIME <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1))));
				hql.append(" GROUP BY tooneBB.id ");
				hql.append(" union all SELECT tooneB.id as aa FROM CRM_CUSTOMERACCOUNT tooneB ");
				hql.append(" inner JOIN  CRM_CUSTOMERACCOUNTCILP tooneA on tooneA.customerAccount_id = tooneB.id and ( tooneA.ISTEMP != '1') ");
				hql.append(" inner JOIN  CRM_RECHARGERECORD many on many.customerAccountClip_id = tooneA.id  and (many.isTemp!='1') ");
				hql.append(" and many.tenantId='"+SecurityUtil.getCurrentUserTenantId()+"'");  
				hql.append(" and many.companyInnerCode='"+SecurityUtil.getCurrentEmpOrgInnerCode()+"'");  
				if(params.containsKey("channelDistributorId")){
					hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+params.get("channelDistributorId")+"'");  
				}else{
					hql.append(" and 1=2 ");  
				}
				hql.append(" and many.payDate >= " + MyTool.StringUseToSql(timeArr.get(0)));
				hql.append(" and many.payDate <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArr.get(timeArr.size() - 1))));
				if (StringUtils.isNotEmpty(selectName)) {
					hql.append(" and tooneB.name like '%" + selectName.trim() + "%' ");
				}
				if (StringUtils.isNotEmpty(phone)) {
					hql.append(" and tooneB.mobilePhone like '%" + phone.trim() + "%' ");
				}
				if (StringUtils.isNotEmpty(cardNum)) {
					hql.append(" and tooneB.clipNumber like '%" + cardNum.trim() + "%' ");
				}
				hql.append(" GROUP BY tooneB.id ");
				hql.append(" )C ");
				hql.append(" GROUP BY C.aa ");
				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
				List resultList = pager.getResultList();
				Map<String, Object> hsMap = new HashMap<String, Object>();
				hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				if(params.containsKey("channelDistributorId")){
					hsMap.put("channelDistributorId", params.get("channelDistributorId") );
				}
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					Double selfExtendStringField1 = 0.0;
					if (true) {// 查询‘会员消费’就是‘昨日消费’‘今日消费’...
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1Money");// selfExtendStringField1Money  查询当前时间消费总金额
						if (timeArr != null && timeArr.size() > 0) {
							String startTime = timeArr.get(0);
							String endTime = timeArr.get(timeArr.size() - 1);
							hsMap.put("startTimeMoney", startTime);
							hsMap.put("endTimeMoney", endTime);
						}
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					Double rechargeTotal = 0.0;
					if (true) {// 查询'当前充值总金额'
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "rechargeTotalSuuqo");// rechargeTotalSuuqo
																		// 查询当前时间充值总金额
						if (timeArr != null && timeArr.size() > 0) {
							hsMap.put("timeArrRecharge", timeArr);
						}
						rechargeTotal = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("rechargeTotal", "" + MyTool.roundingTwoDecimal(rechargeTotal));
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			} else if (stateInt == 22) {// 查询‘充值记录’列表
				Map<String, Object> params = getParams();
				Pager pager = getPager();
				Employee employee = getEmployee();
				if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
					params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
					params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
					params.put("channelDistributor.id,"+ SearchCondition.EQUAL, employee.getChannelDistributor().getId() );
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						params.put("customerAccountClip.customerAccount.mobilePhone," + SearchCondition.ANYLIKE, phone);
					}
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						params.put("customerAccountClip.customerAccount.name," + SearchCondition.ANYLIKE, selectName);
					}
					if (timeArr != null && timeArr.size() > 0) {
						String startTime = timeArr.get(0);
						String endTime = timeArr.get(timeArr.size() - 1);
						params.put("payDate," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
					}
					if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
						pager.setOrderBy("desc");
						pager.setOrderField("payDate");
					}
					params.put("isTemp," + SearchCondition.NOEQUAL, "1");
					pager = vixntBaseService.findPagerByHqlConditions(pager, RechargeRecord.class, params);
				}
				renderDataTable(pager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询不同的会员的列表 数据
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void differentCustomerData() {
		try {
			String changeSQL = getDecodeRequestParameter("changeSQL");
			String frequency = getDecodeRequestParameter("frequency");
			String startTime = getDecodeRequestParameter("startTime");
			String endTime = getDecodeRequestParameter("endTime");
			/** HQLhsMap用于根据不同的参数完成拼接不同的sql语句  **/
			Map<String, Object> HQLhsMap = new HashMap<String, Object>();
			HQLhsMap.put("frequency", frequency);
			HQLhsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			HQLhsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				HQLhsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}

			int stateInt = -1;
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.startsWith("JaeSgtdg{")) {// 查询不同的会员的列表
					stateInt = 30;
				}
			}
			if (stateInt == 30) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("tenantId", SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
				Pager myPager = this.getPager();
				String conditionStr = MyTool.analysisJsonStringFive(changeSQL);
				StringBuffer hql = new StringBuffer();
				if (conditionStr.equals("NewCustomer")) {// ‘新客户’列表
					hql = new StringBuffer();
					hql.append(" select A.hyid as id  from ");
					hql.append(" ( ");
					hql.append(" SELECT ");
					hql.append(" many.customerAccount_id as hyid, ");
					hql.append(" count(*) as goumaicishu  ");
					hql.append(" FROM DRP_REQUIREGOODSORDER many ");
					hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id and many.type = '1' ");
					hql.append(" and many.tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hql.append(" and many.companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hql.append(" and 1=2 ");  
					}	
					hql.append(" WHERE many.payTime is not null  and many.isTemp !=\'1\' ");
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						hql.append(" and toone.name like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						hql.append(" and toone.mobilePhone like '%" + phone.trim() + "%' ");
					}
					hql.append(" GROUP BY ");
					hql.append(" many.customerAccount_id ");
					hql.append(" ORDER BY toone.CREATETIME desc ");
					hql.append(" )A where A.goumaicishu =" + MyTool.getValueToStrByHashMapKey(HQLhsMap, "frequency") + " ");
				} else if (conditionStr.equals("MainCustomer")) {// 查询主力客户
					if (startTime.contains("LatelyMonths")) {// 最近月份
						Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
						start = ((start * 30) - 1) * (-1);
						ArrayList<String> timeArr = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																							// N个月的时间集合（30天/月）
						HQLhsMap.put("timeArr", timeArr);
					}
					hql = new StringBuffer();
					hql.append(" select A.hyid as id, A.goumaicishu as mynum  from ");
					hql.append(" ( ");
					hql.append(" SELECT ");
					hql.append(" many.customerAccount_id as hyid, ");
					hql.append(" count(*) as goumaicishu  ");
					hql.append(" FROM ");
					hql.append(" DRP_REQUIREGOODSORDER many ");
					hql.append(" INNER JOIN CRM_CUSTOMERACCOUNT toone ON many.customerAccount_id = toone.id and many.type = '1' ");
					hql.append(" and many.tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hql.append(" and many.companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hql.append(" and 1=2 ");  
					}	
					hql.append(" WHERE ");
					ArrayList<String> object = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append("     many.payTime >= " + MyTool.StringUseToSql(object.get(0)));
					hql.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size() - 1))));
					hql.append(" and  many.isTemp !=\'1\' ");
					hql.append(" GROUP BY ");
					hql.append(" many.customerAccount_id ");
					hql.append(" )A where A.goumaicishu >=" + MyTool.getValueToStrByHashMapKey(HQLhsMap, "frequency") + " ");
					hql.append(" ORDER BY A.goumaicishu desc ");
				} else if (conditionStr.equals("WillLossCustomer")) {// WillLossCustomer‘
																		// 将要流失客户
																		// ’
					ArrayList<String> timeArrStart = new ArrayList<String>();
					ArrayList<String> timeArrEnd = new ArrayList<String>();
					if (startTime.contains("LatelyMonths")) {// 最近月份
						Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
						start = ((start * 30) - 1) * (-1);
						timeArrStart = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																				// N个月的时间集合（30天/月）
						HQLhsMap.put("timeArrStart", timeArrStart);// timeArrStart
																	// 最近1个月
						Integer end = Integer.parseInt(MyTool.analysisJsonStringFive(endTime));
						end = ((end * 30) - 1) * (-1);
						timeArrEnd = MyTool.getNewlyDateArrayByInt_ZX(end);
						timeArrEnd.removeAll(timeArrStart);
						HQLhsMap.put("timeArrEnd", timeArrEnd);// timeArrEnd
																// 最近上个月
					}
					StringBuffer hqlOne = new StringBuffer();
					hqlOne.append(" select id from CRM_CUSTOMERACCOUNT WHERE id not in  ");
					hqlOne.append(" ( ");
					hqlOne.append(" SELECT ");
					hqlOne.append(" many.customerAccount_id as HYid ");
					hqlOne.append(" FROM ");
					hqlOne.append(" DRP_REQUIREGOODSORDER many ");
					hqlOne.append(" WHERE ");
					ArrayList<String> timeArrStartUse = (ArrayList<String>) HQLhsMap.get("timeArrStart");
					hqlOne.append("     many.payTime >= " + MyTool.StringUseToSql(timeArrStartUse.get(0)));
					hqlOne.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrStartUse.get(timeArrStartUse.size() - 1))));
					hqlOne.append(" and many.tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hqlOne.append(" and many.companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hqlOne.append(" and many.CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hqlOne.append(" and 1=2 ");  
					}
					hqlOne.append(" and many.customerAccount_id is not null ");
					hqlOne.append("  and   many.isTemp !=\'1\' and many.type = '1' ");
					hqlOne.append(" GROUP BY ");
					hqlOne.append(" many.customerAccount_id ");
					hqlOne.append(" ) ");
					hqlOne.append(" and tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hqlOne.append(" and companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hqlOne.append(" and CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hqlOne.append(" and 1=2 ");  
					}

					StringBuffer hqlTwo = new StringBuffer();
					hqlTwo.append(" SELECT ");
					hqlTwo.append(" many.customerAccount_id as HYidTwo ");
					hqlTwo.append(" FROM ");
					hqlTwo.append(" DRP_REQUIREGOODSORDER many ");
					hqlTwo.append(" WHERE ");
					ArrayList<String> timeArrEndUse = (ArrayList<String>) HQLhsMap.get("timeArrEnd");
					hqlTwo.append("     many.payTime >= " + MyTool.StringUseToSql(timeArrEndUse.get(0)));
					hqlTwo.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(timeArrEndUse.get(timeArrEndUse.size() - 1))));
					hqlTwo.append(" and many.customerAccount_id is not null ");
					hqlTwo.append("  and   many.isTemp !=\'1\' and many.type = '1' ");
					hqlTwo.append(" GROUP BY ");
					hqlTwo.append(" many.customerAccount_id ");

					StringBuffer hqlThree = new StringBuffer();
					String hqlA = "(" + hqlOne.toString() + ")";
					String hqlB = "(" + hqlTwo.toString() + ")";
					hqlThree.append(" select id from CRM_CUSTOMERACCOUNT WHERE id in " + hqlA);
					hqlThree.append(" and id in " + hqlB);
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						hqlThree.append(" and name like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						hqlThree.append(" and mobilePhone like '%" + phone.trim() + "%' ");
					}
					hqlThree.append(" ORDER BY CREATETIME desc ");
					hql = new StringBuffer();
					hql.append(hqlThree.toString());
				} else if (conditionStr.equals("AlreadyLostCustomer")) {// |已经流失客户
																		// AlreadyLostCustomer
					if (startTime.contains("LatelyMonths")) {// 最近月份
						Integer start = Integer.parseInt(MyTool.analysisJsonStringFive(startTime));
						start = ((start * 30) - 1) * (-1);
						ArrayList<String> timeArr = MyTool.getNewlyDateArrayByInt_ZX(start);// 这就得到最近
																							// N个月的时间集合（30天/月）
						HQLhsMap.put("timeArr", timeArr);
					}
					hql = new StringBuffer();
					hql.append(" select id from CRM_CUSTOMERACCOUNT WHERE id not in  ");
					hql.append(" ( ");
					hql.append(" SELECT ");
					hql.append(" many.customerAccount_id ");
					hql.append(" FROM ");
					hql.append(" DRP_REQUIREGOODSORDER many ");
					hql.append(" WHERE ");
					ArrayList<String> object = (ArrayList<String>) HQLhsMap.get("timeArr");
					hql.append("     many.payTime >= " + MyTool.StringUseToSql(object.get(0)));
					hql.append(" and many.payTime <  " + MyTool.StringUseToSql(MyTool.date_add_BYyyyyMMdd(object.get(object.size() - 1))));
					hql.append(" and many.tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hql.append(" and many.companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hql.append(" and many.CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hql.append(" and 1=2 ");  
					}
					hql.append(" and many.isTemp !=\'1\' and many.type = '1' ");
					hql.append(" and many.customerAccount_id is not null ");
					hql.append(" GROUP BY ");
					hql.append(" many.customerAccount_id ");
					hql.append(" ) ");
					hql.append(" and CREATETIME is not null and CREATETIME < " + MyTool.StringUseToSql(object.get(0)));
					hql.append(" and tenantId='"+HQLhsMap.get("tenantId")+"'");  
					hql.append(" and companyInnerCode='"+HQLhsMap.get("companyInnerCode")+"'");  
					if(HQLhsMap.containsKey("channelDistributorId")){
						hql.append(" and CHANNELDISTRIBUTOR_ID='"+HQLhsMap.get("channelDistributorId")+"'");  
					}else{
						hql.append(" and 1=2 ");  
					}
					String selectName = getDecodeRequestParameter("selectName");
					if (StringUtils.isNotEmpty(selectName)) {
						hql.append(" and name like '%" + selectName.trim() + "%' ");
					}
					String phone = getDecodeRequestParameter("phone");
					if (StringUtils.isNotEmpty(phone)) {
						hql.append(" and mobilePhone like '%" + phone.trim() + "%' ");
					}
					hql.append(" ORDER BY CREATETIME desc ");
				}
				Pager pager = this.baseHibernateService.findPagerBySqlFull(myPager, hql.toString(), params);
				List resultList = pager.getResultList();
				for (int x = 0; x < resultList.size(); x++) {
					HashMap<String, Object> objectMap = (HashMap<String, Object>) resultList.get(x);
					String customerID = objectMap.get("id").toString();
					if (objectMap.containsKey("mynum")) {
						String spendMoneyNum = objectMap.get("mynum").toString(); // spendMoneyNum
																					// "近1个月消费次数"
																					// 主力客户
						objectMap.put("spendMoneyNum", "" + MyTool.getIntFromDouble(Double.parseDouble(spendMoneyNum)));
					}
					Map<String, Object> hsMap = new HashMap<String, Object>();
					CustomerAccountClip customerClip = new CustomerAccountClip();

					Double selfExtendStringField1 = 0.0;
					String expiryDate = "";
					String typeNameStr = "";
					try {
						customerClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerID);
						if (customerClip.getExpiryDate() != null) {
							expiryDate = MyTool.dateFormatAyMdHms(customerClip.getExpiryDate());// 获得‘会员卡有效期限’
						}
						typeNameStr = customerClip.getTypeNameStr();// 获得‘会员卡类型’
					} catch (Exception e) {
					}
					if (true) {// selfExtendStringField1 查询会员开店到现在消费总金额
						hsMap.put("customerID", customerID);
						hsMap.put("controlSQL", "selfExtendStringField1");
						selfExtendStringField1 = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
					}
					objectMap.put("selfExtendStringField1", "" + MyTool.roundingTwoDecimal(selfExtendStringField1));
					objectMap.put("selfExtendStringField2", expiryDate);
					objectMap.put("selfExtendStringField3", typeNameStr);
					// 需要补充的‘姓名’‘手机’‘生日’‘会员创建时间’ name mobilePhone birthdayStr
					// createTimeFormatA
					String name = "";
					String mobilePhone = "";
					String birthdayStr = "";
					String createTimeFormatA = "";
					CustomerAccount customer = new CustomerAccount();
					try {
						customer = vixntBaseService.findEntityByAttribute(CustomerAccount.class, "id", customerID);
						if (customer != null) {
							name = customer.getName();
							mobilePhone = customer.getMobilePhone();
							birthdayStr = customer.getBirthdayStr();
							createTimeFormatA = customer.getCreateTimeFormatA();
							objectMap.put("customerClipType", customer.getCustomerClipType());
							objectMap.put("channelDistributorName", customer.getChannelDistributorName());
							objectMap.put("clipNumber", customer.getClipNumber());
						}
					} catch (Exception e) {
					}
					objectMap.put("name", name);
					objectMap.put("mobilePhone", mobilePhone);
					objectMap.put("birthdayStr", birthdayStr);
					objectMap.put("createTimeFormatA", createTimeFormatA);
					resultList.set(x, objectMap);
				}
				pager.setResultList(resultList);
				renderDataTable(pager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员消费分析 的点击数字查询消费列表 **/
	public void goConsumptionDataSajk() {
		try {
			List<String> timeArr = new ArrayList<String>();
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("tenantId," + SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
			params.put("companyInnerCode," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId() );
				pager.setOrderBy("desc");
				pager.setOrderField("payTime");
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
				String selectName = getDecodeRequestParameter("selectName");
				if (StringUtils.isNotEmpty(selectName)) {
					params.put("customerAccount.name," + SearchCondition.ANYLIKE, selectName);
				}
				String phone = getDecodeRequestParameter("phone");
				if (StringUtils.isNotEmpty(phone)) {
					params.put("customerAccount.mobilePhone," + SearchCondition.ANYLIKE, phone);
				}
				String controlSQL = getDecodeRequestParameter("controlSQL");
				if (StringUtils.isNotEmpty(controlSQL)) {
					String conditionStr = MyTool.analysisJsonStringFive(controlSQL);
					if (conditionStr.startsWith("All{")) {// 'suidaConMode{All{Today}}'
						// 什么也不做
					} else if (conditionStr.startsWith("Cash{")) {
						params.put("payType," + SearchCondition.EQUAL, "1");
					} else if (conditionStr.startsWith("WeChat{")) {
						params.put("payType," + SearchCondition.EQUAL, "4");
					} else if (conditionStr.startsWith("Alipay{")) {
						params.put("payType," + SearchCondition.EQUAL, "5");
					} else if (conditionStr.startsWith("Other{")) {// 银行卡和余额
						params.put("payType," + SearchCondition.IN, ",3,2");
					}
					// （Cash 现金 ，WeChat 微信 ，Alipay 支付宝 ，BankCard银行卡 ,MemberCard
					// 会员卡也就是余额 ，All 全部 其他 Other）//suidaConMode{All}
					String condiStr = MyTool.analysisJsonStringFive(conditionStr);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(condiStr);
					String startTime = timeArr.get(0);
					String endTime = timeArr.get(timeArr.size() - 1);
					params.put("payTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				params.put("type," + SearchCondition.EQUAL, "1");
				pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 本月过期现存商品,库存不足预警商品,库存积压预警商品 查询 **/
	public void goGoodsDataAbgj() {
		try {
			String changeSQL = getDecodeRequestParameter("changeSQL");
			List<String> timeArr = new ArrayList<String>();
			Double douNum = -10.0;
			int intState = -20;
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.contains("{OverdueExist{")) {// 本月过期现存商品
					String timeStr = MyTool.analysisJsonStringFive(MyTool.analysisJsonStringFive(changeSQL));
					timeArr = MyTool.getTimeArrByHtmlParameterString(timeStr);
					intState = 3;
				} else if (changeSQL.contains("{notEnoughGoods{")) {// 'JaSdGoodScv{notEnoughGoods{20}}',库存不足预警商品
					String intStr = MyTool.analysisJsonStringFive(MyTool.analysisJsonStringFive(changeSQL));
					douNum = Double.parseDouble(intStr);
					intState = 1;
				} else if (changeSQL.contains("{tooMuchGoods{")) {// 'JaSdGoodScv{tooMuchGoods{200}}',库存积压预警商品
					String intStr = MyTool.analysisJsonStringFive(MyTool.analysisJsonStringFive(changeSQL));
					douNum = Double.parseDouble(intStr);
					intState = 2;
				}
			}
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				params.put("tenantId,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentUserTenantId());
				params.put("companyInnerCode,"+ SearchCondition.EQUAL, SecurityUtil.getCurrentEmpOrgInnerCode());
				params.put("channelDistributor.id,"+ SearchCondition.EQUAL, employee.getChannelDistributor().getId() );
				String mytitle = getDecodeRequestParameter("mytitle");
				if (StringUtils.isNotEmpty(mytitle)) {
					params.put("itemname," + SearchCondition.ANYLIKE, mytitle.trim());
				}
				String mynum = getDecodeRequestParameter("mynum");
				if (StringUtils.isNotEmpty(mynum)) {
					params.put("itemcode," + SearchCondition.ANYLIKE, mynum.trim());
				}
				if (timeArr != null && timeArr.size() > 0 && intState == 3 ) {
						String startTime = timeArr.get(0);
						String endTime = timeArr.get(timeArr.size() - 1);
						params.put("massunitEndTime," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
				}
				if (intState == 1) {// 库存不足预警商品 and avaquantity < 20
					params.put("avaquantity," + SearchCondition.LESSTHAN, douNum);
				}
				if (intState == 2) {// 库存积压预警商品 // and avaquantity >= 200
					params.put("avaquantity," + SearchCondition.MORETHANANDEQUAL, douNum);
				}
				pager.setOrderBy("desc");
				pager.setOrderField("massunitEndTime");
				pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 会员管理>会员列表(默认显示本月新增会员) 查看详情 guo
	 */
	public String goSaveOrUpdateAnalysis() {
		try {
			customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
			if (StringUtils.isNotEmpty(controlSQL)) {
				controlSQL = new String("?controlSQL=" + controlSQL);
			}
			getRequest().setAttribute("controlSQL", controlSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAnalysis";
	}
	/**
	 * 会员管理>会员列表(默认显示本月新增会员) 查看详情 返回‘工作台页面’
	 */
	public String goDetailsWorkHomePage() {
		try {
			customerAccount = vixntBaseService.findEntityById(CustomerAccount.class, id);
			customerAccountClip = vixntBaseService.findEntityByAttribute(CustomerAccountClip.class, "customerAccount.id", customerAccount.getId());
			String newReturnPage = "";
			if (StringUtils.isNotEmpty(controlSQL)) {
				newReturnPage = new String("?controlSQL=" + controlSQL + "&" + "returnPage=" + returnPage);
			}
			getRequest().setAttribute("controlSQL", controlSQL);
			getRequest().setAttribute("newReturnPage", newReturnPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDetailsWorkHomePage";
	}
	/**
	 * 会员管理>查询‘订单明细’
	 */
	public String goOrderDetailsPage() {
		try {
			getRequest().setAttribute("RGOrderID", id);
			String controlSQLStr = "";
			if (StringUtils.isNotEmpty(controlSQL)) {
				controlSQLStr = new String("?controlSQL=" + controlSQL);
			}
			getRequest().setAttribute("controlSQLStr", controlSQLStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderDetailsPage";
	}
	/**
	 * 会员管理>查询‘订单明细’的具体数据 列表显示
	 */
	public void goOrderDetailsPageData() {
		try {
			String RGOrderID = getDecodeRequestParameter("RGOrderID");
			Map<String, Object> params = getParams();
			params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, RGOrderID);
			String selectNameB = getDecodeRequestParameter("selectNameB");
			if (StringUtils.isNotEmpty(selectNameB)) {
				params.put("item.name," + SearchCondition.ANYLIKE, selectNameB);
			}
			String code = getDecodeRequestParameter("phoneB");
			if (StringUtils.isNotEmpty(code)) {
				params.put("itemCode," + SearchCondition.ANYLIKE, code);
			}
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrderItem.class, params);
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>‘本月订单数’查询 **/
	public void orderNumData() {
		try {
			// 本月订单数|thisMonthORNum|
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "OrderNumSnkki");// OrderNumSnkki查询‘本月订单数’
			hsMap.put("timeStr", "ThisMonthOT");
			String newTimeStr = "1";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("{t-Sm-HOT}")) {
					hsMap.put("timeStr", queryTime);
					newTimeStr = new String(queryTime.replace("{t-Sm-HOT}", "{l-Tm-HOT}"));
				}
			}
			Double thisMonthORNum = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
			hsMap.put("timeStr", "LastMonthOT");
			if (newTimeStr.length() > 3) {
				hsMap.put("timeStr", newTimeStr);
			}
			Double lastMonthORNum = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");

			getRequest().setAttribute("thisMonthORNum", MyTool.getIntFromDouble(thisMonthORNum));
			getRequest().setAttribute("lastMonthORNum", MyTool.getIntFromDouble(lastMonthORNum));

			String lastMonthMom = MyTool.getMomOne(thisMonthORNum, lastMonthORNum);
			if (lastMonthMom.startsWith("-")) {
				String num = lastMonthMom.replace("-", "");
				getRequest().setAttribute("iclastMonthMomORNum", "fa fa-arrow-down");
				getRequest().setAttribute("islastMonthMomORNum", "#05CD15");
				getRequest().setAttribute("ilastMonthMomORNumnum", num);
			} else if (lastMonthMom.startsWith("+")) {
				String num = lastMonthMom.replace("+", "");
				getRequest().setAttribute("iclastMonthMomORNum", "fa fa-arrow-up");
				getRequest().setAttribute("islastMonthMomORNum", "#D0000D");
				getRequest().setAttribute("ilastMonthMomORNumnum", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>头部数据块'总消费金额 '&'进店客户'&'新增会员卡'查询 **/
	public void headDataBlockC() {
		try {
			// 本月总消费金额{controlSQL:'NTtodayConsumptionMoney'}
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "NTtodayConsumptionMoney");// 这里的‘NTtodayConsumptionMoney’是查询的‘本月’不要看today
			List<String> timeArr = new ArrayList<String>();
			String[] obj = MyTool.getThisMonth_SOnlyAndW();
			timeArr.add(obj[0]);
			timeArr.add(obj[1]);
			hsMap.put("timeArr", timeArr);
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("{t-Sm-HOT}")) {
					hsMap.put("timeStr", queryTime);
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
					hsMap.put("timeArr", timeArr);
				}
			}
			Double HDBCMoney = (Double) queryDataService.findnvixContentJsonB(hsMap).get("totalDouble");
			getRequest().setAttribute("HDBCMoney", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(HDBCMoney)));// 本月总消费金额
			// 本月进店客户{controlSQL:'NTtodayIntoStoreCustomers'}
			hsMap.put("controlSQL", "NTtodayIntoStoreCustomers");
			Double HDBCCustomers = (Double) queryDataService.findnvixContentJsonB(hsMap).get("totalDouble");
			getRequest().setAttribute("HDBCCustomers", MyTool.getIntFromDouble(HDBCCustomers));// 本月进店客户
			// 本月新增会员卡 'NTtodayAddCardNum'
			hsMap.put("controlSQL", "NTtodayAddCardNum");
			Double HDBCCard = (Double) queryDataService.findnvixContentJsonB(hsMap).get("totalDouble");
			getRequest().setAttribute("HDBCCard", MyTool.getIntFromDouble(HDBCCard));// 本月新增会员卡
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>‘今日新增会员’查询 **/
	public void newAddMemberData() {
		try {
			// 今日新增会员|todayNewAdd|简写为：todayNA
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "NewAddSnkli");// NewAddSnkli查询新增会员的相关数量
			hsMap.put("timeStr", "ThisMonthOT");
			String newTimeStr = "1";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("{t-Sm-HOT}")) {
					hsMap.put("timeStr", queryTime);
					newTimeStr = new String(queryTime.replace("{t-Sm-HOT}", "{l-Tm-HOT}"));
				}
			}
			getRequest().setAttribute("queryTimeDY", newTimeStr);
			Double thisMonthNA = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
			hsMap.put("timeStr", "LastMonthOT");
			signStr = "BY";// 本月
			if (newTimeStr.length() > 3) {
				hsMap.put("timeStr", newTimeStr);
				signStr = "DY";// 当月
			}
			getRequest().setAttribute("signStr", signStr);
			Double lastMonthNA = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");

			getRequest().setAttribute("thisMonthNA", MyTool.getIntFromDouble(thisMonthNA));
			getRequest().setAttribute("lastMonthNA", MyTool.getIntFromDouble(lastMonthNA));

			String lastMonthMom = MyTool.getMomOne(thisMonthNA, lastMonthNA);
			if (lastMonthMom.startsWith("-")) {
				String num = lastMonthMom.replace("-", "");
				getRequest().setAttribute("iclastMonthMomNA", "fa fa-arrow-down");
				getRequest().setAttribute("islastMonthMomNA", "#05CD15");
				getRequest().setAttribute("ilastMonthMomNAnum", num);
			} else if (lastMonthMom.startsWith("+")) {
				String num = lastMonthMom.replace("+", "");
				getRequest().setAttribute("iclastMonthMomNA", "fa fa-arrow-up");
				getRequest().setAttribute("islastMonthMomNA", "#D0000D");
				getRequest().setAttribute("ilastMonthMomNAnum", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>‘过生日的会员’查询 **/
	public void birthdayMemberData() {
		try {
			// 今日过生日的会员|todayBirthdayMember|简写为：todayBM
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("controlSQL", "BirthdayMemberSbkli");// BirthdayMemberSbkli查询‘过生日的会员’的相关数量
			hsMap.put("timeStr", "Today");
			Double todayBM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
			hsMap.put("timeStr", "Tomorrow");
			Double tomorrowBM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
			hsMap.put("timeStr", "ThisMonthOT");
			Double thisMonthBM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");

			getRequest().setAttribute("todayBM", MyTool.getIntFromDouble(todayBM));
			getRequest().setAttribute("tomorrowBM", MyTool.getIntFromDouble(tomorrowBM));
			getRequest().setAttribute("thisMonthBM", MyTool.getIntFromDouble(thisMonthBM));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>‘会员消费’查询 **/
	public void moneyMemberData() {
		try {
			// 今日会员消费|todayMoneyMember|简写为：todayMM
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "MoneyMemberSnuli");// MoneyMemberSnuli 查询'会员消费'的相关数量
			hsMap.put("timeStr", "ThisMonthOT");
			String newTimeStr = "1";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("{t-Sm-HOT}")) {
					hsMap.put("timeStr", queryTime);
					newTimeStr = new String(queryTime.replace("{t-Sm-HOT}", "{l-Tm-HOT}"));
				}
			}
			Map<String, Object> todayMap = queryDataService.findCustomerDataSouliA(hsMap);
			Double thisMonthMM = (Double) todayMap.get("totalDouble");
			Double thisMonthMMPeopleNum = (Double) todayMap.get("totalDoublePeopleNum");
			hsMap.put("timeStr", "LastMonthOT");
			if (newTimeStr.length() > 3) {
				hsMap.put("timeStr", newTimeStr);
			}
			Double lastMonthMM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");

			getRequest().setAttribute("thisMonthMM", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(thisMonthMM)));
			getRequest().setAttribute("thisMonthMMPeopleNum", MyTool.getIntFromDouble(thisMonthMMPeopleNum));
			getRequest().setAttribute("lastMonthMM", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(lastMonthMM)));

			String lastMonthMom = MyTool.getMomOne(thisMonthMM, lastMonthMM);
			if (lastMonthMom.startsWith("-")) {
				String num = lastMonthMom.replace("-", "");
				getRequest().setAttribute("iclastMonthMomMM", "fa fa-arrow-down");
				getRequest().setAttribute("islastMonthMomMM", "#05CD15");
				getRequest().setAttribute("ilastMonthMomMMnum", num);
			} else if (lastMonthMom.startsWith("+")) {
				String num = lastMonthMom.replace("+", "");
				getRequest().setAttribute("iclastMonthMomMM", "fa fa-arrow-up");
				getRequest().setAttribute("islastMonthMomMM", "#D0000D");
				getRequest().setAttribute("ilastMonthMomMMnum", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 会员管理>‘会员卡过期会员’查询 **/
	public void cardTermMemberData() {
		try {
			// 会员卡本周过期会员|thisWeekCardTermMember|简写为：thisWeekCTM
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if(employee != null && employee.getChannelDistributor()!=null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId()) ){
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId() );
			}
			hsMap.put("controlSQL", "CardTermMemberSbtli");// CardTermMemberSbtli查询‘会员卡过期会员’数量
			hsMap.put("timeStr", "ThisMonthOT");
			String newTimeStr = "1";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("{t-Sm-HOT}")) {
					hsMap.put("timeStr", queryTime);
					newTimeStr = new String(queryTime.replace("{t-Sm-HOT}", "{l-Tm-HOT}"));
				}
			}
			Double thisMonthCTM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");
			hsMap.put("timeStr", "LastMonthOT");
			if (newTimeStr.length() > 3) {
				hsMap.put("timeStr", newTimeStr);
			}
			Double lastMonthCTM = (Double) queryDataService.findCustomerDataSouliA(hsMap).get("totalDouble");

			getRequest().setAttribute("thisMonthCTM", MyTool.getIntFromDouble(thisMonthCTM));
			getRequest().setAttribute("lastMonthCTM", MyTool.getIntFromDouble(lastMonthCTM));
			String lastMonthMom = MyTool.getMomOne(thisMonthCTM, lastMonthCTM);
			if (lastMonthMom.startsWith("-")) {
				String num = lastMonthMom.replace("-", "");
				getRequest().setAttribute("iclastMonthMomCTM", "fa fa-arrow-down");
				getRequest().setAttribute("islastMonthMomCTM", "#05CD15");
				getRequest().setAttribute("ilastMonthMomCTMnum", num);
			} else if (lastMonthMom.startsWith("+")) {
				String num = lastMonthMom.replace("+", "");
				getRequest().setAttribute("iclastMonthMomCTM", "fa fa-arrow-up");
				getRequest().setAttribute("islastMonthMomCTM", "#D0000D");
				getRequest().setAttribute("ilastMonthMomCTMnum", num);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goMember() {
		String queryTimeText = "";
		String titleDate = "本月";
		if (StringUtils.isNotEmpty(queryTime)) {
			if (queryTime.contains("{l-Tm-HOT}")) {
				queryTime = new String(queryTime.replace("{l-Tm-HOT}", "{t-Sm-HOT}"));
			}
			if (queryTime.contains("{t-Sm-HOT}")) {// rN-pE{代表要返回的页面
													// queryTime是这样的格式2017-01-012018-11-03{rN-pE{自定义返回页面A}}
				queryTimeText = queryTime.substring(0, 7);
				titleDate = "当月";
			}
		}
		getRequest().setAttribute("queryTimeText", queryTimeText);
		getRequest().setAttribute("titleDate", titleDate);
		newAddMemberData();
		headDataBlockC();
		orderNumData();
		moneyMemberData();
		cardTermMemberData();
		return "goMember";
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public CustomerAccountClip getCustomerAccountClip() {
		return customerAccountClip;
	}
	public void setCustomerAccountClip(CustomerAccountClip customerAccountClip) {
		this.customerAccountClip = customerAccountClip;
	}
	public String controlSQL;
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}
	public String returnPage;
	public String getReturnPage() {
		return returnPage;
	}
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}
	/**
	 * 会员管理>会员列表(默认显示本月新增会员) 查看详情 guo
	 */
	public String goMemberListNewHtml() {
		try { // <!-- drawMember 会员画像分析页面 drawMberPageSoy -->
			if (controlSQL.startsWith("sui")) {
				if (controlSQL.startsWith("suiaAMACp{")) {
					// <!-- CustomerAnalysisPage客户分析 用‘cusAnaPage’代替 -->
					return "goAllMemberAllClipHtml";// <!-- suiaAMACp 是
													// AllMemberAllClip 的变化
													// 所有的会员，所有的会员卡
													// 客户分析页面中查询‘所有会员人数超链接’
													// 和‘所有会员卡超链接’ 其中suia代表随机数
													// -->
				} else if (controlSQL.startsWith("suibCusConSuib{")) {// <!--
																		// CustomerConsumption
																		// 客户消费
																		// CusConSuib
																		// 'suibCusConSuib{CusConSuib}'
																		// -->
					return "goCusConSuibHtml";
				} else if (controlSQL.startsWith("suicRemainderMoney{")) {// <!--
																			// suicRemainderMoney
																			// 会员卡总余额
																			// 'suicRemainderMoney{suic}'
																			// -->
					return "goRemainderMoneySuicHtml";
				} else if (controlSQL.startsWith("suidaConMode{")) {
					// <!-- suidaConsumptionMode 消费方式 suidaConMode --> （Cash 现金
					// ，WeChat 微信 ，Alipay 支付宝 ，BankCard银行卡 ,MemberCard 会员卡也就是余额
					// ，All 全部）
					// <!-- consumptionAnalysis消费分析页面 取 conAnaPageSagj -->
					// 'suidaConMode{All{Today}}','conAnaPageSagj'
					return "goConsumptionAnalysisPage";
				}
			} else {
				// <!-- IncreaseC说明：IncreaseC是IncreaseCard的简写 代表‘新增会员卡’--> <!--
				// Card说明： 代表‘会员卡过期’-->
				if (controlSQL.contains("NewAdd") || controlSQL.contains("Birthday") || controlSQL.contains("Card") || controlSQL.contains("IncreaseC")) {
					getRequest().setAttribute("controlSQL", controlSQL);
					return "goMemberListNewHtml";
				} else if (controlSQL.contains("Money")) {
					getRequest().setAttribute("controlSQL", controlSQL);
					return "goMemberMoneyListNewHtml";
				} else if (controlSQL.contains("OrderNum")) {
					getRequest().setAttribute("controlSQL", controlSQL);
					return "goMemberOrderNumListNewHtml";
				} else if (controlSQL.contains("IntoTS")) {// <!-- 进店Into the
															// store
															// 简写为‘IntoTS’代表‘进店客户’
															// -->
					// <!-- returnPage： ‘workHomePage’代表工作台 -->
					getRequest().setAttribute("controlSQL", controlSQL);
					return "goIntoTheStoreNewHtml";
				} else if (controlSQL.contains("RechargeM")) {// <!-- RechargeM
																// 充值记录 -->
					getRequest().setAttribute("controlSQL", controlSQL);
					return "goRechargeMoneyNewHtml";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 工作台>通过时间检索跳转页面 **/
	public String searchTimeWHPage() {
		try {
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {// rN-pE{代表要返回的页面
													// queryTime是这样的格式2017-01-012018-11-03{rN-pE{自定义返回页面A}}
					startTime = queryTime.substring(0, 10);
				}
			}
			return "searchTimeWHPage";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 点击页面数字跳转查询相关列表
	 */
	public String startsWithForListPage() {
		try {
			if (StringUtils.isNotEmpty(changeSQL)) {
				if (changeSQL.startsWith("JaeSgtdg{")) {// (changeSQL如果是‘JabNewCustomer’代表‘新客户’)
					return "goDifferentCustomerPage";
				} else if (changeSQL.startsWith("JaSqExistGoodS")) {// <!--
																	// storeStockPageSvy
																	// 表示 门店库存报表
																	// -->controlSQLTwo('JaSqExistGoodS{}','storeStockPageSvy');"
																	// 现存商品量
					return "goExistGoodSPage";
				} else if (changeSQL.startsWith("JaSdGoodScv{")) {// 'JaSdGoodScv{OverdueExist{ThisMonthOT}}','storeStockPageSvy'
																	// 本月过期现存商品
					return "goGoodSDataAbPage";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getChangeSQL() {
		return changeSQL;
	}
	public void setChangeSQL(String changeSQL) {
		this.changeSQL = changeSQL;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	public String getSignStr() {
		return signStr;
	}
	public void setSignStr(String signStr) {
		this.signStr = signStr;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}

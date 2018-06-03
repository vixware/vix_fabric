/**
 * 
 */
package com.vix.nvix.customer.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.activity.entity.Activity;
import com.vix.crm.base.entity.AccountType;
import com.vix.crm.base.entity.ContactPersonType;
import com.vix.crm.base.entity.CredentialType;
import com.vix.crm.base.entity.CrmSet;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.crm.base.entity.StaffScale;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.crm.lead.entity.SaleLead;
import com.vix.crm.project.entity.Competitor;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.project.entity.ProjectRequirement;
import com.vix.crm.project.entity.ProjectSolution;
import com.vix.crm.salechance.entity.BackSectionPlan;
import com.vix.crm.salechance.entity.BackSectionRecord;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.crm.service.entity.CustomerCare;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerShare;
import com.vix.mdm.crm.service.ICustomerAccountService;
import com.vix.nvix.common.base.entity.AddressInfo;
import com.vix.sales.invoice.entity.SalesInvoice;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.salepayment.entity.Expenses;

/**
 * @author Bluesnow
 * @data 2015-12-14 PM - 14:30
 *
 */
@Controller
@Scope("prototype")
public class NvixCustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;

	private String id;
	private String ids;
	private String name;
	private String code;
	private String parentId;
	private String employeeId;
	private String entityName;
	private String syncTag;
	private String source;
	private CustomerAccount customerAccount;
	private ContactPerson contactPerson;

	private List<HotLevel> hotLevelList;
	private List<CustomerType> customerTypeList;
	private List<CustomerSource> customerSourceList;
	private List<CustomerStage> customerStageList;
	private List<Industry> industryList;
	private List<AccountType> accountTypeList;
	private List<RelationshipClass> relationshipClassList;
	private List<StaffScale> staffScaleList;
	private List<CredentialType> credentialTypeList;
	private List<ContactPersonType> contactPersonTypeList;
	private List<AddressInfo> provinceList;
	private List<AddressInfo> cityList;
	private List<AddressInfo> districtList;
	/** 分类 */
	private CustomerAccountCategory customerAccountCategory;
	private String customerAccountType;
	private List<CustomerAccount> caList;
	
	private Long customerCount;// 客户数量
	private Long salesOrderCount;// 联系人数量
	private Long projectCount;// 项目数量
	public String goCrmList(){
		try{
			String startTime = DateUtil.getBegindate();
			String endTime = DateUtil.getEnddate();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.EQUAL, "enterPrise");
			params.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:01!" + endTime + " 23:59:59");
			customerCount = customerAccountService.findDataCountByHqlConditions(CustomerAccount.class, params);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("isTemp," + SearchCondition.EQUAL, "0");
			p.put("isDeleted," + SearchCondition.EQUAL, "0");
			p.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:01!" + endTime + " 23:59:59");
			salesOrderCount = customerAccountService.findDataCountByHqlConditions(SalesOrder.class, p);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("createTime," + SearchCondition.BETWEENAND, startTime + " 00:00:01!" + endTime + " 23:59:59");
			projectCount = customerAccountService.findDataCountByHqlConditions(CrmProject.class, param);
			Map<String, Object> pa = new HashMap<String, Object>();
			pa.put("backSectionDate," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + endTime + " 23:59:59");
			List<BackSectionRecord> backSectionRecords = customerAccountService.findAllByConditions(BackSectionRecord.class, pa);
			if(backSectionRecords != null && backSectionRecords.size() > 0){
				for (BackSectionRecord backSectionRecord : backSectionRecords) {
					if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
						amount += backSectionRecord.getAmount();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goCrmList";
	}
	
	@Override
	public String goList(){
		try{
			Map<String, Object> params = getParams();
			params.put("enable," + SearchCondition.EQUAL, "1");

			customerTypeList = customerAccountService.findAllByConditions(CustomerType.class, params);
			relationshipClassList = customerAccountService.findAllByConditions(RelationshipClass.class, params);
			customerSourceList = customerAccountService.findAllByConditions(CustomerSource.class, params);
			customerStageList = customerAccountService.findAllByConditions(CustomerStage.class, params);
			hotLevelList = customerAccountService.findAllByConditions(HotLevel.class, params);
			Map<String, Object> p = getParams();
			p.put("parentAddressInfo.id," + SearchCondition.IS, null);
			provinceList = customerAccountService.findAllByConditions(AddressInfo.class,p);
			industryList = customerAccountService.findAllByConditions(Industry.class, params);
			accountTypeList = customerAccountService.findAllByConditions(AccountType.class, params);
			staffScaleList = customerAccountService.findAllByConditions(StaffScale.class, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	public String goCustomerList(){
		try{
			Map<String, Object> params = getParams();
			params.put("enable," + SearchCondition.EQUAL, "1");

			customerTypeList = customerAccountService.findAllByConditions(CustomerType.class, params);
			relationshipClassList = customerAccountService.findAllByConditions(RelationshipClass.class, params);
			customerSourceList = customerAccountService.findAllByConditions(CustomerSource.class, params);
			customerStageList = customerAccountService.findAllByConditions(CustomerStage.class, params);
			hotLevelList = customerAccountService.findAllByConditions(HotLevel.class, params);
			Map<String, Object> p = getParams();
			p.put("parentAddressInfo.id," + SearchCondition.IS, null);
			provinceList = customerAccountService.findAllByConditions(AddressInfo.class,p);
			industryList = customerAccountService.findAllByConditions(Industry.class, params);
			accountTypeList = customerAccountService.findAllByConditions(AccountType.class, params);
			staffScaleList = customerAccountService.findAllByConditions(StaffScale.class, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goCustomerList";
	}
	
	public void goCustomerAccountRemind(){
		try{
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.EQUAL, "enterPrise");
			String tenantId = SecurityUtil.getCurrentUserTenantId();
			if (StringUtils.isNotEmpty(tenantId)) {
				CrmSet crmSet = customerAccountService.findEntityByAttribute(CrmSet.class, "tenantId", tenantId);
				if (crmSet != null) {
					params.put("stagnateDay," + SearchCondition.MORETHANANDEQUAL, crmSet.getRemindDay());
					pager = customerAccountService.findPagerByHqlConditions(pager, CustomerAccount.class,params);
				}
			}
			String[] excludes = { "*.subCouponDetails", "*staffScale", "*.industry", "*.customerCreditInfos", "*.customerAccountCategory", "*.customerAccountGroup", "*.contactPersons", "*.saleChances", "*.saleLeads", "*.customerComplaints", "*.customerServices", "*.subCouponDetails" };
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void goListContentJson() {
		syncTag = getRequestParameter("syncTag");
		try {
			Pager pager = getPager();
			//StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerShare cs left join cs.customerAccount ca where cs.customerAccount.id = ca.id AND ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			
			//hqlBuilder.append("and ca.employee.id is not null ");
			params.put("employee.id," + SearchCondition.ISNOT, null);
			if (null != source && "member".equals(source)) {
				params.put("type," + SearchCondition.EQUAL, "member");
				//hqlBuilder.append("and ca.type = :type ");
			}

			if (null != syncTag && !"".equals(syncTag)) {
				params.put("type," + SearchCondition.EQUAL, syncTag);
				//hqlBuilder.append("and ca.type = :type ");
			} else {
				params.put("type," + SearchCondition.EQUAL, "enterPrise");
				//hqlBuilder.append("and ca.type = :type ");
			}

			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccountCategory.id," + SearchCondition.EQUAL, parentId);
				//hqlBuilder.append("and ca.customerAccountCategory.id like :customerAccountCategory.id ");
			}

			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.name like :name ");
			}

			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.code like :code ");
			}
			
			String shorterName = getRequestParameter("shortName");
			if (null != shorterName && !"".equals(shorterName)) {
				params.put("shorterName," + SearchCondition.ANYLIKE, URLDecoder.decode(shorterName.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.shorterName like :shorterName ");
			}
			
			String customerStageId = getRequestParameter("customerStageId");
			if (null != customerStageId && !"".equals(customerStageId)) {
				params.put("customerStage.id," + SearchCondition.EQUAL, customerStageId);
				//hqlBuilder.append("and ca.customerStage.id like :customerStageId ");
			}
			String customerTypeId = getRequestParameter("customerTypeId");
			if (null != customerTypeId && !"".equals(customerTypeId)) {
				params.put("customerType.id," + SearchCondition.EQUAL, customerTypeId);
				//hqlBuilder.append("and ca.customerType.id like :customerType.id ");
			}
			String relationshipClassId = getRequestParameter("relationshipClassId");
			if (null != relationshipClassId && !"".equals(relationshipClassId)) {
				params.put("relationshipClass.id," + SearchCondition.EQUAL, relationshipClassId);
				//hqlBuilder.append("and ca.relationshipClass.id =:relationshipClass.id ");
			}
			String customerSourceId = getRequestParameter("customerSourceId");
			if (null != customerSourceId && !"".equals(customerSourceId)) {
				params.put("customerSource.id," + SearchCondition.EQUAL, customerSourceId);
				//hqlBuilder.append("and ca.customerSource.id =:customerSource.id ");
			}
			String industryId = getRequestParameter("industryId");
			if (null != industryId && !"".equals(industryId)) {
				params.put("industry.id," + SearchCondition.EQUAL, industryId);
				//hqlBuilder.append("and ca.industry.id =:industry.id ");
			}
			String staffScaleId = getRequestParameter("staffScaleId");
			if (null != staffScaleId && !"".equals(staffScaleId)) {
				params.put("staffScale.id," + SearchCondition.EQUAL, staffScaleId);
				//hqlBuilder.append("and ca.staffScale.id =:staffScale.id ");
			}
			String hotLevelId = getRequestParameter("hotLevelId");
			if (null != hotLevelId && !"".equals(hotLevelId)) {
				params.put("hotLevel.id," + SearchCondition.EQUAL, hotLevelId);
				//hqlBuilder.append("and ca.hotLevel.id =:hotLevel.id ");
			}
			String valueAssessment = getRequestParameter("valueAssessment");
			if (null != valueAssessment && !"".equals(valueAssessment)) {
				params.put("valueAssessment," + SearchCondition.EQUAL, valueAssessment);
				//hqlBuilder.append("and ca.valueAssessment =:valueAssessment ");
			}
			String creditLevel = getRequestParameter("creditLevel");
			if (null != creditLevel && !"".equals(creditLevel)) {
				params.put("creditLevel," + SearchCondition.EQUAL, creditLevel);
				//hqlBuilder.append("and ca.creditLevel =:creditLevel ");
			}
			String employeeName = getRequestParameter("employeeName");
			if (null != employeeName && !"".equals(employeeName)) {
				params.put("employee.name," + SearchCondition.ANYLIKE, URLDecoder.decode(employeeName.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.employee.name like :employee.name ");
			}
			String phoneFax = getRequestParameter("phoneFax");
			if (null != phoneFax && !"".equals(phoneFax)) {
				params.put("phoneFax," + SearchCondition.ANYLIKE, URLDecoder.decode(phoneFax.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.phoneFax like :phoneFax ");
			}
			String provinceId = getRequestParameter("provinceId");
			if (null != provinceId && !"".equals(provinceId)) {
				params.put("province.id," + SearchCondition.EQUAL, provinceId);
				//hqlBuilder.append("and ca.province.id =:province.id ");
			}
			String cityId = getRequestParameter("cityId");
			if (null != cityId && !"".equals(cityId)) {
				params.put("city.id," + SearchCondition.EQUAL, cityId);
				//hqlBuilder.append("and ca.city.id =:city.id ");
			}
			String createStartTime = getRequestParameter("createStartTime");
			String createEndTime = getRequestParameter("createEndTime");
			if(StrUtils.isNotEmpty(createStartTime) && StrUtils.isNotEmpty(createEndTime)){
				params.put("createTime," + SearchCondition.BETWEENAND, createStartTime + " 00:00:01!" + createEndTime + " 23:59:59");
			}else if (null != createStartTime && !"".equals(createStartTime)) {
				params.put("createTime," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(createStartTime));
				//hqlBuilder.append("and ca.createTime  >=:createStartTime ");
			}else if (null != createEndTime && !"".equals(createEndTime)) {
				params.put("createTime," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(createEndTime));
				//hqlBuilder.append("and ca.createTime <=:createEndTime ");
			}
			String updateStartTime = getRequestParameter("updateStartTime");
			String updateEndTime = getRequestParameter("updateEndTime");
			if(StrUtils.isNotEmpty(updateStartTime) && StrUtils.isNotEmpty(updateEndTime)){
				params.put("updateTime," + SearchCondition.BETWEENAND, updateStartTime + " 00:00:01!" + updateEndTime + " 23:59:59");
			}else if (null != updateStartTime && !"".equals(updateStartTime)) {
				params.put("updateTime," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(updateStartTime));
				//hqlBuilder.append("and ca.updateTime >=:updateStartTime ");
			}else if (null != updateEndTime && !"".equals(updateEndTime)) {
				params.put("updateTime," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(updateStartTime));
				//hqlBuilder.append("and ca.updateTime <=:updateEndTime ");
			}
			String stagnateStartDay = getRequestParameter("stagnateStartDay");
			String btnType = getRequestParameter("btnType");
			if(StrUtils.isNotEmpty(stagnateStartDay) && StrUtils.isNotEmpty(btnType)){
				if("lt".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.LESSTHAN, Integer.parseInt(stagnateStartDay));
				}else if("gt".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.MORETHAN, Integer.parseInt(stagnateStartDay));
				}else if("eq".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.EQUAL, Integer.parseInt(stagnateStartDay));
				}
			}
			
			String contactPersonName = getRequestParameter("contactPersonName");
			if (null != contactPersonName && !"".equals(contactPersonName)) {
				params.put("contactPersonNames," + SearchCondition.ANYLIKE, URLDecoder.decode(contactPersonName.trim(), "UTF-8"));
			}

			/*
			 * Employee emp = getEmployee(); if (null != emp &&
			 * StrUtils.objectIsNotNull(emp.getId())) { params.put("creator," +
			 * SearchCondition.EQUAL, emp.getId()); hqlBuilder.append(
			 * "and ca.creator = :creator "); }
			 */

			//params.put("isHighSea," + SearchCondition.EQUAL, "1");
			//hqlBuilder.append(" or ca.isHighSea = :isHighSea ");

			//customerAccountService.findPagerByHql(pager, "ca", hqlBuilder.toString(), params);
			pager = customerAccountService.findPagerByHqlConditions(pager, CustomerAccount.class,params);
			String[] excludes = { "*.subCouponDetails", "*staffScale", "*.industry", "*.customerCreditInfos", "*.customerAccountCategory", "*.customerAccountGroup", "*.contactPersons", "*.saleChances", "*.saleLeads", "*.customerComplaints", "*.customerServices", "*.subCouponDetails" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goCustomerListContentJson() {
		syncTag = getRequestParameter("syncTag");
		try {
			Pager pager = getPager();
			//StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerShare cs left join cs.customerAccount ca where cs.customerAccount.id = ca.id AND ca.isTemp = :isTemp and ca.isDeleted = :isDeleted ");
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");

			//hqlBuilder.append("and ca.employee.id is null ");
			params.put("employee.id," + SearchCondition.IS, null);
			if (null != source && "member".equals(source)) {
				params.put("type," + SearchCondition.EQUAL, "member");
				//hqlBuilder.append("and ca.type = :type ");
			}

			if (null != syncTag && !"".equals(syncTag)) {
				params.put("type," + SearchCondition.EQUAL, syncTag);
				//hqlBuilder.append("and ca.type = :type ");
			} else {
				params.put("type," + SearchCondition.EQUAL, "enterPrise");
				//hqlBuilder.append("and ca.type = :type ");
			}

			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccountCategory.id," + SearchCondition.EQUAL, parentId);
				//hqlBuilder.append("and ca.customerAccountCategory.id like :customerAccountCategory.id ");
			}

			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.name like :name ");
			}

			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.code like :code ");
			}
			
			String shorterName = getRequestParameter("shortName");
			if (null != shorterName && !"".equals(shorterName)) {
				params.put("shorterName," + SearchCondition.ANYLIKE, URLDecoder.decode(shorterName.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.shorterName like :shorterName ");
			}
			
			String customerStageId = getRequestParameter("customerStageId");
			if (null != customerStageId && !"".equals(customerStageId)) {
				params.put("customerStage.id," + SearchCondition.EQUAL, customerStageId);
				//hqlBuilder.append("and ca.customerStage.id like :customerStageId ");
			}
			String customerTypeId = getRequestParameter("customerTypeId");
			if (null != customerTypeId && !"".equals(customerTypeId)) {
				params.put("customerType.id," + SearchCondition.EQUAL, customerTypeId);
				//hqlBuilder.append("and ca.customerType.id like :customerType.id ");
			}
			String relationshipClassId = getRequestParameter("relationshipClassId");
			if (null != relationshipClassId && !"".equals(relationshipClassId)) {
				params.put("relationshipClass.id," + SearchCondition.EQUAL, relationshipClassId);
				//hqlBuilder.append("and ca.relationshipClass.id =:relationshipClass.id ");
			}
			String customerSourceId = getRequestParameter("customerSourceId");
			if (null != customerSourceId && !"".equals(customerSourceId)) {
				params.put("customerSource.id," + SearchCondition.EQUAL, customerSourceId);
				//hqlBuilder.append("and ca.customerSource.id =:customerSource.id ");
			}
			String industryId = getRequestParameter("industryId");
			if (null != industryId && !"".equals(industryId)) {
				params.put("industry.id," + SearchCondition.EQUAL, industryId);
				//hqlBuilder.append("and ca.industry.id =:industry.id ");
			}
			String staffScaleId = getRequestParameter("staffScaleId");
			if (null != staffScaleId && !"".equals(staffScaleId)) {
				params.put("staffScale.id," + SearchCondition.EQUAL, staffScaleId);
				//hqlBuilder.append("and ca.staffScale.id =:staffScale.id ");
			}
			String hotLevelId = getRequestParameter("hotLevelId");
			if (null != hotLevelId && !"".equals(hotLevelId)) {
				params.put("hotLevel.id," + SearchCondition.EQUAL, hotLevelId);
				//hqlBuilder.append("and ca.hotLevel.id =:hotLevel.id ");
			}
			String valueAssessment = getRequestParameter("valueAssessment");
			if (null != valueAssessment && !"".equals(valueAssessment)) {
				params.put("valueAssessment," + SearchCondition.EQUAL, valueAssessment);
				//hqlBuilder.append("and ca.valueAssessment =:valueAssessment ");
			}
			String creditLevel = getRequestParameter("creditLevel");
			if (null != creditLevel && !"".equals(creditLevel)) {
				params.put("creditLevel," + SearchCondition.EQUAL, creditLevel);
				//hqlBuilder.append("and ca.creditLevel =:creditLevel ");
			}
			String employeeName = getRequestParameter("employeeName");
			if (null != employeeName && !"".equals(employeeName)) {
				params.put("employee.name," + SearchCondition.ANYLIKE, URLDecoder.decode(employeeName.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.employee.name like :employee.name ");
			}
			String phoneFax = getRequestParameter("phoneFax");
			if (null != phoneFax && !"".equals(phoneFax)) {
				params.put("phoneFax," + SearchCondition.ANYLIKE, URLDecoder.decode(phoneFax.trim(), "UTF-8"));
				//hqlBuilder.append("and ca.phoneFax like :phoneFax ");
			}
			String provinceId = getRequestParameter("provinceId");
			if (null != provinceId && !"".equals(provinceId)) {
				params.put("province.id," + SearchCondition.EQUAL, provinceId);
				//hqlBuilder.append("and ca.province.id =:province.id ");
			}
			String cityId = getRequestParameter("cityId");
			if (null != cityId && !"".equals(cityId)) {
				params.put("city.id," + SearchCondition.EQUAL, cityId);
				//hqlBuilder.append("and ca.city.id =:city.id ");
			}
			String createStartTime = getRequestParameter("createStartTime");
			String createEndTime = getRequestParameter("createEndTime");
			if(StrUtils.isNotEmpty(createStartTime) && StrUtils.isNotEmpty(createEndTime)){
				params.put("createTime," + SearchCondition.BETWEENAND, createStartTime + " 00:00:01!" + createEndTime + " 23:59:59");
			}else if (null != createStartTime && !"".equals(createStartTime)) {
				params.put("createTime," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(createStartTime));
				//hqlBuilder.append("and ca.createTime  >=:createStartTime ");
			}else if (null != createEndTime && !"".equals(createEndTime)) {
				params.put("createTime," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(createEndTime));
				//hqlBuilder.append("and ca.createTime <=:createEndTime ");
			}
			String updateStartTime = getRequestParameter("updateStartTime");
			String updateEndTime = getRequestParameter("updateEndTime");
			if(StrUtils.isNotEmpty(updateStartTime) && StrUtils.isNotEmpty(updateEndTime)){
				params.put("updateTime," + SearchCondition.BETWEENAND, updateStartTime + " 00:00:01!" + updateEndTime + " 23:59:59");
			}else if (null != updateStartTime && !"".equals(updateStartTime)) {
				params.put("updateTime," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(updateStartTime));
				//hqlBuilder.append("and ca.updateTime >=:updateStartTime ");
			}else if (null != updateEndTime && !"".equals(updateEndTime)) {
				params.put("updateTime," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(updateStartTime));
				//hqlBuilder.append("and ca.updateTime <=:updateEndTime ");
			}
			String stagnateStartDay = getRequestParameter("stagnateStartDay");
			String btnType = getRequestParameter("btnType");
			if(StrUtils.isNotEmpty(stagnateStartDay) && StrUtils.isNotEmpty(btnType)){
				if("lt".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.LESSTHAN, Integer.parseInt(stagnateStartDay));
				}else if("gt".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.MORETHAN, Integer.parseInt(stagnateStartDay));
				}else if("eq".equals(btnType)){
					params.put("stagnateDay," + SearchCondition.EQUAL, Integer.parseInt(stagnateStartDay));
				}
			}
			
			String contactPersonName = getRequestParameter("contactPersonName");
			if (null != contactPersonName && !"".equals(contactPersonName)) {
				params.put("contactPersonNames," + SearchCondition.ANYLIKE, URLDecoder.decode(contactPersonName.trim(), "UTF-8"));
			}

			/*
			 * Employee emp = getEmployee(); if (null != emp &&
			 * StrUtils.objectIsNotNull(emp.getId())) { params.put("creator," +
			 * SearchCondition.EQUAL, emp.getId()); hqlBuilder.append(
			 * "and ca.creator = :creator "); }
			 */

			//params.put("isHighSea," + SearchCondition.EQUAL, "1");
			//hqlBuilder.append(" or ca.isHighSea = :isHighSea ");

			//customerAccountService.findPagerByHql(pager, "ca", hqlBuilder.toString(), params);
			pager = customerAccountService.findPagerByHqlConditions(pager, CustomerAccount.class,params);
			String[] excludes = { "*.subCouponDetails", "*staffScale", "*.industry", "*.customerCreditInfos", "*.customerAccountCategory", "*.customerAccountGroup", "*.contactPersons", "*.saleChances", "*.saleLeads", "*.customerComplaints", "*.customerServices", "*.subCouponDetails" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 联系人列表 */
	public void getContactPersonListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("lastName");
				pager.setOrderBy("desc");
			}
			Map<String, Object> params = getParams();

			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);

				if (null != name && !"".equals(name)) {
					params.put("lastName," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, ContactPerson.class, params);
			}

			String[] excludes = { "*.customerAccount", "*credentialType", "*.crmContactType" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 项目列表 */
	public void getCrmProjectListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, CrmProject.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 销售机会列表 */
	public void getSaleChanceListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, SaleChance.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 销售线索列表 */
	public void getSaleLeadListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, SaleLead.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售活动列表 */
	public void getSaleActivityListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("title," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, Activity.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 详细需求列表 */
	public void getProjectRequirementListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, ProjectRequirement.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 竞争对手列表 */
	public void getCompetitorListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("companyName," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, Competitor.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 详细需求列表 */
	public void getProjectSolutionListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, ProjectSolution.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 开票记录列表 */
	public void getSalesInvoiceListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("code," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, SalesInvoice.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 回款计划列表 */
	public void getBackSectionPlanListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("owner.name,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, BackSectionPlan.class, params);
			}
			
			String[] excludes = { "*.customerAccount", "*.owner", "*.charger" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 回款记录列表 */
	public void getBackSectionRecordListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("owner.name,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, BackSectionRecord.class, params);
			}
			
			String[] excludes = { "*.customerAccount", "*.owner" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 费用支出列表 */
	public void getExpensesListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("employee.name,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, Expenses.class, params);
			}
			
			String[] excludes = { "*.customerAccount", "*.owner" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户关怀列表 */
	public void getCustomerCareListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				customerAccountService.findPagerByHqlConditions(pager, CustomerCare.class, params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取客户列表 */
	public String goChooseCustomerAccount() {
		return "goChooseCustomerAccount";
	}

	/** 选择客户列表 */
	public void goSingleListJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");

			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}

			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}

			customerAccountService.findPagerByHqlConditions(pager, CustomerAccount.class, params);

			String[] excludes = { "*.subCouponDetails", "*staffScale", "*.industry", "*.customerCreditInfos", "*.customerAccountCategory", "*.customerAccountGroup", "*.contactPersons", "*.saleChances", "*.saleLeads", "*.customerComplaints", "*.customerServices", "*.subCouponDetails" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 新增或修改 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			params.put("enable," + SearchCondition.EQUAL, "1");

			customerTypeList = customerAccountService.findAllByConditions(CustomerType.class, params);
			relationshipClassList = customerAccountService.findAllByConditions(RelationshipClass.class, params);
			customerSourceList = customerAccountService.findAllByConditions(CustomerSource.class, params);
			customerStageList = customerAccountService.findAllByConditions(CustomerStage.class, params);
			hotLevelList = customerAccountService.findAllByConditions(HotLevel.class, params);
			Map<String, Object> p = getParams();
			p.put("parentAddressInfo.id," + SearchCondition.IS, null);
			provinceList = customerAccountService.findAllByConditions(AddressInfo.class,p);
			//credentialTypeList = customerAccountService.findAllByConditions(CredentialType.class, params);

			if ("enterPrise".equals(customerAccountType) || "internal".equals(customerAccountType)) {
				industryList = customerAccountService.findAllByConditions(Industry.class, params);
				accountTypeList = customerAccountService.findAllByConditions(AccountType.class, params);
				staffScaleList = customerAccountService.findAllByConditions(StaffScale.class, params);
			} else {
				credentialTypeList = customerAccountService.findAllByConditions(CredentialType.class, params);
				contactPersonTypeList = customerAccountService.findAllByConditions(ContactPersonType.class, params);
			}

			if (StrUtils.objectIsNotNull(id) && !"0".equals(id)) {
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				if (!"enterPrise".equals(customerAccountType) && !"internal".equals(customerAccountType)) {
					contactPerson = customerAccountService.findEntityByAttribute(ContactPerson.class, "customerAccount.id", id);
				}
				if(customerAccount != null && customerAccount.getProvince() != null && StrUtils.isNotEmpty(customerAccount.getProvince().getId())){
					cityList = customerAccountService.findAllByEntityClassAndAttribute(AddressInfo.class, "parentAddressInfo.id", customerAccount.getProvince().getId());
				}
				if(customerAccount != null && customerAccount.getCity() != null && StrUtils.isNotEmpty(customerAccount.getCity().getId())){
					districtList = customerAccountService.findAllByEntityClassAndAttribute(AddressInfo.class, "parentAddressInfo.id", customerAccount.getCity().getId());
				}
			} else {
				customerAccount = new CustomerAccount();
				customerAccount.setIsDeleted("0");
				customerAccount.setIsHighSea("0");
				customerAccount.setCreateTime(new Date());
				customerAccount.setUpdateTime(new Date());
				customerAccount.setType(customerAccountType);
				Employee emp = getEmployee();
				if (null != emp) {
					//customerAccount.setCreatorCode(emp.getCode());
					//customerAccount.setCreator(emp.getName());
					customerAccount.setEmployee(emp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerAccountType;
	}
	
	private Double planAmount = 0d;// 回款计划金额
	private Double amount = 0d;// 回款价金额
	private Double contractAmount = 0d;//合同金额
	private Integer contractAccount;
	private Double payAmount = 0d;//开票金额
	private Double expenseAmount = 0d;//费用金额
	private Double customerOnYear;// 客户同比
	private String names;
	private String salesJson;
	private List<ContactPerson> contactPersonList;
	public String view(){
		try{
			if(null != id && !"".equals(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class,id);
				if(customerAccount != null && StrUtils.isNotEmpty(customerAccount.getId())){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("customerAccount.id," + SearchCondition.EQUAL, customerAccount.getId());
					contactPersonList = customerAccountService.findAllByConditions(ContactPerson.class, params);
					List<BackSectionPlan> backSectionPlans = customerAccountService.findAllByConditions(BackSectionPlan.class, params);
					if(backSectionPlans != null && backSectionPlans.size() > 0){
						for (BackSectionPlan backSectionPlan : backSectionPlans) {
							if(backSectionPlan != null && backSectionPlan.getAmount() != null && backSectionPlan.getAmount() > 0){
								planAmount += backSectionPlan.getAmount();
							}
						}
					}
					List<BackSectionRecord> backSectionRecords = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
					if(backSectionRecords != null && backSectionRecords.size() > 0){
						for (BackSectionRecord backSectionRecord : backSectionRecords) {
							if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
								amount += backSectionRecord.getAmount();
							}
						}
					}
					List<SalesInvoice> salesInvoices = customerAccountService.findAllByConditions(SalesInvoice.class, params);
					if(salesInvoices != null && salesInvoices.size() > 0){
						for (SalesInvoice salesInvoice : salesInvoices) {
							if(salesInvoice != null && salesInvoice.getAmount() != null && salesInvoice.getAmount() > 0){
								payAmount += salesInvoice.getAmount();
							}
						}
					}
					List<Expenses> expensess = customerAccountService.findAllByConditions(Expenses.class, params);
					if(expensess != null && expensess.size() > 0){
						for (Expenses expenses : expensess) {
							if(expenses != null && expenses.getAmount() != null && expenses.getAmount() > 0){
								expenseAmount += expenses.getAmount();
							}
						}
					}
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("secondPartyId," + SearchCondition.EQUAL, customerAccount.getId());
					p.put("contractType," + SearchCondition.EQUAL, 3);
					List<Contract> contracts = customerAccountService.findAllByConditions(Contract.class, p);
					contractAccount = contracts.size();
					if(contracts != null && contracts.size() > 0){
						for (Contract contract : contracts) {
							if(contract != null && contract.getTotalAmount() != null && contract.getTotalAmount() != 0){
								contractAmount += contract.getTotalAmount();
							}
						}
					}
					// 计算客户同比
					String beginDate = DateUtil.getBegindate();
					String endDate = DateUtil.getEnddate();
					int year = DateUtil.getYear() - 1;
					int month = DateUtil.getMonth();
					String lastBeginDate = year + "-" + beginDate.split("-")[1] + "-" + beginDate.split("-")[2];
					String lastEndDate = year + "-" + endDate.split("-")[1] + "-" + endDate.split("-")[2];
					if(year%4 == 0 && month == 2){
						lastEndDate = year + "-" + endDate.split("-")[1] + "-29";
					}
					params.put("backSectionDate," + SearchCondition.BETWEENAND, beginDate + " 00:00:01!" + endDate + " 23:59:59");
					List<BackSectionRecord> newBackSectionRecords = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
					params.put("backSectionDate," + SearchCondition.BETWEENAND, lastBeginDate + " 00:00:01!" + lastEndDate + " 23:59:59");
					List<BackSectionRecord> lastBackSectionRecords = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
					Double newRecord = 0d;
					Double lastRecord = 0d;
					if(newBackSectionRecords != null && newBackSectionRecords.size() > 0){
						for (BackSectionRecord backSectionRecord : newBackSectionRecords) {
							if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
								newRecord += backSectionRecord.getAmount();
							}
						}
					}
					if(lastBackSectionRecords != null && lastBackSectionRecords.size() > 0){
						for (BackSectionRecord backSectionRecord : lastBackSectionRecords) {
							if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
								lastRecord += backSectionRecord.getAmount();
							}
						}
					}
					if(newRecord == 0 && lastRecord == 0){
						customerOnYear = 0.0;
					}else if(lastRecord == 0){
						customerOnYear = 100.0;
					}else{
						DecimalFormat df = new DecimalFormat(".#");
						customerOnYear = (newRecord - lastRecord)/lastRecord*100;
						customerOnYear = Double.parseDouble(df.format(customerOnYear));
					}
					// 销售额占比
					StringBuilder name = new StringBuilder("[");
					StringBuilder sales = new StringBuilder("[");
					name.append("'"+customerAccount.getShorterName()+"',");
					sales.append("{value:"+amount+",name:'"+customerAccount.getShorterName()+"',selected:true},");
					if(customerAccount.getEmployee() != null && StrUtils.isNotEmpty(customerAccount.getEmployee().getId())){
						Map<String, Object> cp = new HashMap<String, Object>();
						cp.put("id," + SearchCondition.NOEQUAL, customerAccount.getId());
						cp.put("employee.id," + SearchCondition.EQUAL, customerAccount.getEmployee().getId());
						List<CustomerAccount> customerAccounts = customerAccountService.findAllByConditions(CustomerAccount.class, cp);
						if(customerAccounts != null && customerAccounts.size() > 0){
							for (CustomerAccount customer : customerAccounts) {
								name.append("'"+customer.getShorterName()+"',");
								params.put("customerAccount.id," + SearchCondition.EQUAL, customer.getId());
								List<BackSectionRecord> backSectionRecordList = customerAccountService.findAllByConditions(BackSectionRecord.class, params);
								Double records = 0d;
								if(backSectionRecordList != null && backSectionRecordList.size() > 0){
									for (BackSectionRecord backSectionRecord : backSectionRecordList) {
										if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
											records += backSectionRecord.getAmount();
										}
									}
								}
								sales.append("{value:"+records+",name:'"+customer.getShorterName()+"'},");
							}
						}
					}
					names = name.substring(0, name.length()-1).toString() + "]";
					salesJson = sales.substring(0, sales.length()-1).toString() + "]";
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if(null !=customerAccountType && "enterPrise".equals(customerAccountType)){
			return "enterPriseView";
		}else{
			return "personalView";
		}
	}
	
	/** 近期销售活动列表 */
	public void getRecentSaleActivityListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				StringBuilder hql = new StringBuilder("select hentity from Activity hentity where 1=1 and ");
				hql.append("hentity.customerAccount.id = '"+parentId+"' ");
				if (null != name && !"".equals(name)) {
					hql.append("hentity.title like %"+URLDecoder.decode(name.trim(), "UTF-8")+"% ");
				}
				hql.append(" order by hentity.date desc limit 10 ");
				pager = customerAccountService.findPagerByHql(pager, "hentity", hql.toString(), params);
			}
			
			String[] excludes = { "*.customerAccount" };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goContactPersonDetail(){
		try{
			if(StrUtils.isNotEmpty(id)){
				contactPerson = baseHibernateService.findEntityById(ContactPerson.class, id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goContactPersonDetail";
	}

	/** 保存 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(customerAccount.getId())) {
				isSave = false;
				customerAccount.setDateModified(new Date());
				customerAccount.setIsTemp("0");
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
			} else {
				loadCommonData(customerAccount);
				customerAccount.setDateEntered(new Date());
				customerAccount.setCreateTime(new Date());
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount.setIsTemp("0");
			}
			if(customerAccount.getEmployee() != null){
				customerAccount.setIsReceive("1");
			}
			loadCommonData(customerAccount);
			/** 检查空值对象 */
			String[] attrArray = { "customerAccountCategory", "hotLevel", "customerType", "relationshipClass", "industry", "staffScale", "customerSource", "customerStage", "customerAccountGroup", "memberLevel", "mbtags", "accountType", "employee", "province", "city", "district" };
			checkEntityNullValue(customerAccount, attrArray);

			if ("enterPrise".equals(customerAccount.getType().trim()) || "internal".equals(customerAccount.getType().trim())) {
				customerAccount.setChineseFirstLetter(ChnToPinYin.getPinYinHeadChar(customerAccount.getName()).toUpperCase());
				initEntityBaseController.initEntityBaseAttribute(customerAccount);
				customerAccount = customerAccountService.merge(customerAccount);
			} else {
				if (null != contactPerson.getId() || StringUtils.isNotEmpty(contactPerson.getId())) {
					if (null != contactPerson.getName() && StringUtils.isNotEmpty(contactPerson.getName())) {
						customerAccount.setName(contactPerson.getName());
						customerAccount.setChineseFirstLetter(ChnToPinYin.getPinYinHeadChar(contactPerson.getName()).toUpperCase());
						initEntityBaseController.initEntityBaseAttribute(customerAccount);
						customerAccount = customerAccountService.merge(customerAccount);
					}
				}
				contactPerson.setNameAllSpelling(ChnToPinYin.getPYString(contactPerson.getName()).toUpperCase());
				contactPerson.setIsBlack("0");
				contactPerson.setCustomerAccount(customerAccount);

				String[] attrArrays = { "contactPersonType", "credentialType", "crmContactType" };
				checkEntityNullValue(contactPerson, attrArrays);

				initEntityBaseController.initEntityBaseAttribute(contactPerson);
				loadCommonData(contactPerson);
				contactPerson = customerAccountService.merge(contactPerson);
			}

			CustomerShare cs = new CustomerShare();
			cs.setCustomerAccount(customerAccount);
			Employee emp = getEmployee();
			if (null != emp) {
				cs.setEmployee(new Employee());
				cs.getEmployee().setId(emp.getId());
			}
			cs.setRead("1");
			cs.setWrite("1");
			loadCommonData(cs);
			customerAccountService.merge(cs);

			if (isSave) {
				renderText(SAVE_SUCCESS+":"+customerAccount.getId());
			} else {
				renderText(UPDATE_SUCCESS+":"+customerAccount.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	/**
	 * 保存图片
	 */
	public void saveOrUpdatePicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPicture();
			if (savePathAndName != null && savePathAndName.length == 2) {
				if (StrUtils.isNotEmpty(id)) {
					customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				}
				if (customerAccount != null) {
					customerAccount.setLogo("/img/wechat/" + savePathAndName[1].toString());
					customerAccount.setUpdateTime(new Date());
					customerAccount = customerAccountService.merge(customerAccount);
					renderText(customerAccount.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,上传失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] saveUploadPicture() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
			if (null != customerAccount) {
				customerAccount.setIsDeleted("1");
				customerAccount.setIsTemp("1");
				customerAccountService.mergeOriginal(customerAccount);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 获取客户所属部门 */

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<CustomerAccountCategory> listCustomerAccountCategory = new ArrayList<CustomerAccountCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCustomerAccountCategory = baseHibernateService.findAllSubEntity(CustomerAccountCategory.class, "parentCustomerAccountCategory.id", id, params);
			} else {
				listCustomerAccountCategory = baseHibernateService.findAllSubEntity(CustomerAccountCategory.class, "parentCustomerAccountCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllCustomerAccountCategory(strSb, listCustomerAccountCategory);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllCustomerAccountCategory(StringBuilder strSb, List<CustomerAccountCategory> listCustomerAccountCategory) throws Exception {
		for (int i = 0; i < listCustomerAccountCategory.size(); i++) {
			CustomerAccountCategory pc = listCustomerAccountCategory.get(i);
			if (pc.getSubCustomerAccountCategorys().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllCustomerAccountCategory(strSb, new ArrayList<CustomerAccountCategory>(pc.getSubCustomerAccountCategorys()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(pc.getId());
				strSb.append("\",name:\"");
				strSb.append(pc.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listCustomerAccountCategory.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}
	
	/** 领取客户 */
	public void receive(){
		try{
			if(StrUtils.isNotEmpty(id) && StrUtils.isNotEmpty(employeeId)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				Employee employee = customerAccountService.findEntityById(Employee.class, employeeId);
				if(customerAccount != null && employee != null){
					customerAccount.setEmployee(employee);
					customerAccount.setIsReceive("1");
					customerAccount.setUpdateTime(new Date());
					customerAccount.setStagnateDay(0);
					customerAccount = customerAccountService.merge(customerAccount);
					renderText("1:领取客户成功");
				}else{
					renderText("0:领取客户失败");
				}
			}else{
				renderText("0:领取客户失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText("0:领取客户失败");
		}
	}
	
	public void receiveAll() {
		try {
			if(StrUtils.isNotEmpty(ids) && StrUtils.isNotEmpty(employeeId)){
				Employee employee = customerAccountService.findEntityById(Employee.class, employeeId);
				if(employee != null){
					Map<String, Object> params = getParams();
					params.put("id," + SearchCondition.IN, ids);
					List<CustomerAccount> customerAccounts = customerAccountService.findAllByConditions(CustomerAccount.class, params);
					if (customerAccounts != null && customerAccounts.size() > 0) {
						for (CustomerAccount customerAccount : customerAccounts) {
							if (customerAccount != null) {
								customerAccount.setEmployee(employee);
								customerAccount.setIsReceive("1");
								customerAccount.setUpdateTime(new Date());
								customerAccount.setStagnateDay(0);
								customerAccount = customerAccountService.merge(customerAccount);
							}
						}
						renderText("1:领取客户成功");
					}
				}else{
					renderText("0:领取客户失败");
				}
			}else{
				renderText("0:领取客户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:领取客户失败");
		}
	}
	
	/** 释放客户 */
	public void release(){
		try{
			if(StrUtils.isNotEmpty(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class, id);
				if(customerAccount != null){
					customerAccount.setEmployee(null);
					customerAccount.setIsReceive("0");
					customerAccount.setUpdateTime(new Date());
					customerAccount.setStagnateDay(0);
					customerAccount = customerAccountService.merge(customerAccount);
					renderText("1:释放客户成功");
				}else{
					renderText("0:释放客户失败");
				}
			}else{
				renderText("0:释放客户失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText("0:释放客户失败");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public ICustomerAccountService getCustomerAccountService() {
		return customerAccountService;
	}

	public void setCustomerAccountService(ICustomerAccountService customerAccountService) {
		this.customerAccountService = customerAccountService;
	}

	public List<HotLevel> getHotLevelList() {
		return hotLevelList;
	}

	public void setHotLevelList(List<HotLevel> hotLevelList) {
		this.hotLevelList = hotLevelList;
	}

	public List<CustomerType> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<CustomerType> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}

	public List<CustomerSource> getCustomerSourceList() {
		return customerSourceList;
	}

	public void setCustomerSourceList(List<CustomerSource> customerSourceList) {
		this.customerSourceList = customerSourceList;
	}

	public List<CustomerStage> getCustomerStageList() {
		return customerStageList;
	}

	public void setCustomerStageList(List<CustomerStage> customerStageList) {
		this.customerStageList = customerStageList;
	}

	public List<Industry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<Industry> industryList) {
		this.industryList = industryList;
	}

	public List<AccountType> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<AccountType> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}

	public List<RelationshipClass> getRelationshipClassList() {
		return relationshipClassList;
	}

	public void setRelationshipClassList(List<RelationshipClass> relationshipClassList) {
		this.relationshipClassList = relationshipClassList;
	}

	public List<StaffScale> getStaffScaleList() {
		return staffScaleList;
	}

	public void setStaffScaleList(List<StaffScale> staffScaleList) {
		this.staffScaleList = staffScaleList;
	}

	public List<CredentialType> getCredentialTypeList() {
		return credentialTypeList;
	}

	public void setCredentialTypeList(List<CredentialType> credentialTypeList) {
		this.credentialTypeList = credentialTypeList;
	}

	public List<ContactPersonType> getContactPersonTypeList() {
		return contactPersonTypeList;
	}

	public void setContactPersonTypeList(List<ContactPersonType> contactPersonTypeList) {
		this.contactPersonTypeList = contactPersonTypeList;
	}

	public List<AddressInfo> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<AddressInfo> provinceList) {
		this.provinceList = provinceList;
	}

	public List<AddressInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<AddressInfo> cityList) {
		this.cityList = cityList;
	}

	public List<AddressInfo> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<AddressInfo> districtList) {
		this.districtList = districtList;
	}

	public List<CustomerAccount> getCaList() {
		return caList;
	}

	public void setCaList(List<CustomerAccount> caList) {
		this.caList = caList;
	}

	public String getSyncTag() {
		if (null != syncTag && !"".equals(syncTag)) {
			return syncTag;
		} else {
			syncTag = "enterPrise";
			return syncTag;
		}
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
	}

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Double getPlanAmount() {
		return planAmount;
	}
	
	public String getPlanAmountStr() {
		if (null != planAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(planAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public Double getAmount() {
		return amount;
	}
	
	public String getAmountStr() {
		if (null != amount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(amount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getContractAmount() {
		return contractAmount;
	}
	
	public String getContractAmountStr() {
		if (null != contractAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(contractAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getPayAmount() {
		return payAmount;
	}
	
	public String getPayAmountStr() {
		if (null != payAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(payAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Double getExpenseAmount() {
		return expenseAmount;
	}
	
	public String getExpenseAmountStr() {
		if (null != expenseAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(expenseAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public Integer getContractAccount() {
		return contractAccount;
	}

	public void setContractAccount(Integer contractAccount) {
		this.contractAccount = contractAccount;
	}

	public Double getCustomerOnYear() {
		return customerOnYear;
	}

	public void setCustomerOnYear(Double customerOnYear) {
		this.customerOnYear = customerOnYear;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSalesJson() {
		return salesJson;
	}

	public void setSalesJson(String salesJson) {
		this.salesJson = salesJson;
	}

	public Long getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Long customerCount) {
		this.customerCount = customerCount;
	}

	public Long getSalesOrderCount() {
		return salesOrderCount;
	}

	public void setSalesOrderCount(Long salesOrderCount) {
		this.salesOrderCount = salesOrderCount;
	}

	public Long getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(Long projectCount) {
		this.projectCount = projectCount;
	}

}
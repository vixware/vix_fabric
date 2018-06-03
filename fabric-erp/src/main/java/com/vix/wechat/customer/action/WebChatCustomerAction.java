/**
 * 
 */
package com.vix.wechat.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.crm.base.entity.CrmContactType;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.Industry;
import com.vix.crm.customer.entity.CustomerAccountCategory;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerShare;
import com.vix.wechat.base.action.WechatBaseAction;
import com.vix.wechat.base.entity.WxpQyWeixinSite;

/**
 * @author Bluesnow 2016年5月17日
 * 
 *         移动CRM
 */
@Controller
@Scope("prototype")
public class WebChatCustomerAction extends WechatBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String code;
	private String parentId;
	private String sync;
	private CustomerAccount customerAccount;
	private String customerAccountType;
	private String chooseType;
	private List<CustomerAccount> customerAccountList;
	private ContactPerson contactPerson;
	private List<CustomerType> customerTypeList;
	private List<CustomerSource> customerSourceList;
	private List<CustomerStage> customerStageList;
	private List<Industry> industryList;
	private List<ContactPerson> contactPersonList;
	private List<SaleChance> saleChanceList;
	private List<CrmContactType> crmContactTypeList;
	/** 客户分类 */
	private CustomerAccountCategory customerAccountCategory;
	private List<CustomerAccountCategory> customerCategoryList;

	public String goCustomerAccountList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.NOEQUAL, "internal");
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
			}
			customerAccountList = wechatBaseService.findAllDataByConditions(CustomerAccount.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCustomerAccountList";
	}

	public String goSaveOrUpdateCustomerAccont() {
		try {
			Map<String, Object> params = getParams();
			params.put("enable," + SearchCondition.EQUAL, "1");
			customerCategoryList = wechatBaseService.findAllDataByConditions(CustomerAccountCategory.class, null);
			customerTypeList = wechatBaseService.findAllDataByConditions(CustomerType.class, params);
			customerSourceList = wechatBaseService.findAllDataByConditions(CustomerSource.class, params);
			customerStageList = wechatBaseService.findAllDataByConditions(CustomerStage.class, params);
			industryList = wechatBaseService.findAllDataByConditions(Industry.class, params);
			if (StrUtils.isNotEmpty(id)) {
				customerAccount = wechatBaseService.findEntityById(CustomerAccount.class, id);
				params.clear();
				params.put("customerAccount.id," + SearchCondition.EQUAL, id);
				/** 联系人 */
				contactPersonList = wechatBaseService.findAllDataByConditions(ContactPerson.class, params);
				/** 机会与跟踪 */
				saleChanceList = wechatBaseService.findAllDataByConditions(SaleChance.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chooseType;
	}

	public String deleteCustomerAccountById() {
		try {
			CustomerAccount ca = wechatBaseService.findEntityById(CustomerAccount.class, id);
			if (null != ca) {
				ca.setIsDeleted("1");
				wechatBaseService.mergeOriginal(ca);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("没有找到该用户数据"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void saveOrUpdateCustomerAccont() {
		boolean isSave = true;
		CustomerAccount ca = null;
		try {
			WxpQyWeixinSite site = wechatBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
			if (site != null) {
				customerAccount.setTenantId(site.getTenantId());
				customerAccount.setCompanyInnerCode(site.getCompanyInnerCode());
			}

			if (StrUtils.objectIsNotNull(customerAccount.getId())) {
				ca = wechatBaseService.findEntityById(CustomerAccount.class, customerAccount.getId());
				if (null != ca) {
					ca.setDateModified(new Date());
					ca.setIsTemp("0");
					isSave = false;
				}
			} else {
				ca = new CustomerAccount();
				ca.setDateEntered(new Date());
				ca.setIsTemp("0");
				ca.setIsDeleted("0");
				loadCommonData(ca);
			}

			/** 检查空值对象 */
			String[] attrArray = { "customerAccountCategory", "hotLevel", "customerType", "relationshipClass", "industry", "contactPerson" };
			checkEntityNullValue(customerAccount, attrArray);

			ca.setName(customerAccount.getName());
			ca.setCode(customerAccount.getCode());
			ca.setChineseFirstLetter(ChnToPinYin.getPinYinHeadChar(customerAccount.getName()).toUpperCase());
			ca.setEnglishName(customerAccount.getEnglishName());
			ca.setCompanyBrief(customerAccount.getCompanyBrief());
			ca.setContactPersons(customerAccount.getContactPersons());
			ca.setCustomerAccountCategory(customerAccount.getCustomerAccountCategory());
			ca.setType(customerAccount.getType());
			ca.setIndustry(customerAccount.getIndustry());
			ca.setCustomerSource(customerAccount.getCustomerSource());
			initEntityBaseController.initEntityBaseAttribute(ca);
			customerAccount = wechatBaseService.mergeOriginal(ca);

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
			wechatBaseService.mergeOriginal(customerAccount);

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
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

	public String goContactPersonList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Employee emp = getEmployee();
			if (null != emp && StrUtils.objectIsNotNull(emp.getId())) {
				params.put("creator," + SearchCondition.EQUAL, emp.getId());
			}
			contactPersonList = wechatBaseService.findAllDataByConditions(ContactPerson.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goContactPersonList";
	}

	public String goSaveOrUpdateContactPerson() {
		try {
			Map<String,Object> params = getParams();
			params.put("enable,"+SearchCondition.EQUAL, "1");
			crmContactTypeList = wechatBaseService.findAllByConditions(CrmContactType.class, params);
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				contactPerson = wechatBaseService.findEntityById(ContactPerson.class, id);
				contactPerson.setDateModified(new Date());
			} else {
				customerAccountList = wechatBaseService.findAllByEntityClass(CustomerAccount.class);
				contactPerson = new ContactPerson();
				contactPerson.setCreateTime(new Date());
				contactPerson.setIsTemp("0");
				contactPerson.setIsDeleted("0");
				loadCommonData(contactPerson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateContactPerson";
	}

	public String chooseCustomerAccount() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.NOEQUAL, "internal");
			customerAccountList = wechatBaseService.findAllDataByConditions(CustomerAccount.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "chooseCustomerAccount";
	}

	public String saveOrUpdateContactPerson() {
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public CustomerAccountCategory getCustomerAccountCategory() {
		return customerAccountCategory;
	}

	public void setCustomerAccountCategory(CustomerAccountCategory customerAccountCategory) {
		this.customerAccountCategory = customerAccountCategory;
	}

	public List<CustomerAccount> getCustomerAccountList() {
		return customerAccountList;
	}

	public void setCustomerAccountList(List<CustomerAccount> customerAccountList) {
		this.customerAccountList = customerAccountList;
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

	public List<CustomerAccountCategory> getCustomerCategoryList() {
		return customerCategoryList;
	}

	public void setCustomerCategoryList(List<CustomerAccountCategory> customerCategoryList) {
		this.customerCategoryList = customerCategoryList;
	}

	public String getCustomerAccountType() {
		return customerAccountType;
	}

	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public String getChooseType() {
		return chooseType;
	}

	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	public List<SaleChance> getSaleChanceList() {
		return saleChanceList;
	}

	public void setSaleChanceList(List<SaleChance> saleChanceList) {
		this.saleChanceList = saleChanceList;
	}

	public List<CrmContactType> getCrmContactTypeList() {
		return crmContactTypeList;
	}

	public void setCrmContactTypeList(List<CrmContactType> crmContactTypeList) {
		this.crmContactTypeList = crmContactTypeList;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}
}
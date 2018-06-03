package com.vix.nvix.customer.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ContactPersonType;
import com.vix.crm.base.entity.CrmContactType;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixContactPersonAction extends BaseAction {


	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String entityName;
	private String parentId;
	private String parentName;
	private String source;
	private ContactPerson contactPerson;
	private List<CrmContactType> crmContactTypeList;
	private List<ContactPersonType> contactPersonTypeList;
	
	@Override
	public String goList() {
		try{
			Map<String,Object> params = getParams();
			params.put("enable,"+SearchCondition.EQUAL, "1");
			crmContactTypeList = baseHibernateService.findAllByConditions(CrmContactType.class, params);
			contactPersonTypeList = baseHibernateService.findAllByConditions(ContactPersonType.class, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_LIST;
	}
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			
			if(null != parentName && !"".equals(parentName)){
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, URLDecoder.decode(parentName.trim(), "UTF-8"));
			}
			
			if (null != name && !"".equals(name)) {
				params.put("lastName," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			String firstName = getRequestParameter("firstName");
			if (null != firstName && !"".equals(firstName)) {
				params.put("chineseFirstLetter," + SearchCondition.EQUAL, firstName.toUpperCase());
			}
			
			String sex = getRequestParameter("sex");
			if (null != sex && !"".equals(sex)) {
				params.put("sex," + SearchCondition.EQUAL, sex);
			}
			String position = getRequestParameter("position");
			if (null != position && !"".equals(position)) {
				params.put("position," + SearchCondition.ANYLIKE, URLDecoder.decode(position.trim(), "UTF-8"));
			}
			String callTitle = getRequestParameter("callTitle");
			if (null != callTitle && !"".equals(callTitle)) {
				params.put("callTitle," + SearchCondition.ANYLIKE, URLDecoder.decode(callTitle.trim(), "UTF-8"));
			}
			String crmContactTypeId = getRequestParameter("crmContactTypeId");
			if (null != crmContactTypeId && !"".equals(crmContactTypeId)) {
				params.put("crmContactType.id," + SearchCondition.EQUAL, crmContactTypeId);
			}
			String contactPersonTypeId = getRequestParameter("contactPersonTypeId");
			if (null != contactPersonTypeId && !"".equals(contactPersonTypeId)) {
				params.put("contactPersonType.id," + SearchCondition.EQUAL, contactPersonTypeId);
			}
			String presideBusiness = getRequestParameter("presideBusiness");
			if (null != presideBusiness && !"".equals(presideBusiness)) {
				params.put("presideBusiness," + SearchCondition.ANYLIKE, URLDecoder.decode(presideBusiness.trim(), "UTF-8"));
			}
			String directPhone = getRequestParameter("directPhone");
			if (null != directPhone && !"".equals(directPhone)) {
				params.put("directPhone," + SearchCondition.ANYLIKE, URLDecoder.decode(directPhone.trim(), "UTF-8"));
			}
			String mobile = getRequestParameter("mobile");
			if (null != mobile && !"".equals(mobile)) {
				params.put("mobile," + SearchCondition.ANYLIKE, URLDecoder.decode(mobile.trim(), "UTF-8"));
			}
			String email = getRequestParameter("email");
			if (null != email && !"".equals(email)) {
				params.put("email," + SearchCondition.ANYLIKE, URLDecoder.decode(email.trim(), "UTF-8"));
			}
			String phoneHome = getRequestParameter("phoneHome");
			if (null != phoneHome && !"".equals(phoneHome)) {
				params.put("phoneHome," + SearchCondition.ANYLIKE, URLDecoder.decode(phoneHome.trim(), "UTF-8"));
			}
			String address = getRequestParameter("address");
			if (null != address && !"".equals(address)) {
				params.put("address," + SearchCondition.ANYLIKE, URLDecoder.decode(address.trim(), "UTF-8"));
			}
			String msnAccount = getRequestParameter("msnAccount");
			if (null != msnAccount && !"".equals(msnAccount)) {
				params.put("msnAccount," + SearchCondition.ANYLIKE, URLDecoder.decode(msnAccount.trim(), "UTF-8"));
			}
			String qqAccount = getRequestParameter("qqAccount");
			if (null != position && !"".equals(position)) {
				params.put("qqAccount," + SearchCondition.ANYLIKE, URLDecoder.decode(qqAccount.trim(), "UTF-8"));
			}
			String department = getRequestParameter("department");
			if (null != position && !"".equals(position)) {
				params.put("department," + SearchCondition.ANYLIKE, URLDecoder.decode(department.trim(), "UTF-8"));
			}
			String createStartTime = getRequestParameter("createStartTime");
			String createEndTime = getRequestParameter("createEndTime");
			if(StrUtils.isNotEmpty(createStartTime) && StrUtils.isNotEmpty(createEndTime)){
				params.put("createTime," + SearchCondition.BETWEENAND, createStartTime + " 00:00:01!" + createEndTime + " 23:59:59");
			}else if (null != createStartTime && !"".equals(createStartTime)) {
				params.put("createTime," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(createStartTime));
			}else if (null != createEndTime && !"".equals(createEndTime)) {
				params.put("createTime," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(createEndTime));
			}
			String birthdayStartTime = getRequestParameter("birthdayStartTime");
			String birthdayEndTime = getRequestParameter("birthdayEndTime");
			if(StrUtils.isNotEmpty(birthdayStartTime) && StrUtils.isNotEmpty(birthdayEndTime)){
				params.put("birthday," + SearchCondition.BETWEENAND, birthdayStartTime + " 00:00:01!" + birthdayEndTime + " 23:59:59");
			}else if (null != birthdayStartTime && !"".equals(birthdayStartTime)) {
				params.put("birthday," + SearchCondition.MORETHANANDEQUAL, DateUtil.praseSqlDate(birthdayStartTime));
			}else if (null != birthdayEndTime && !"".equals(birthdayEndTime)) {
				params.put("birthday," + SearchCondition.LESSTHANANDEQUAL, DateUtil.praseSqlDate(birthdayEndTime));
			}
			
			baseHibernateService.findPagerByHqlConditions(pager, ContactPerson.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<ContactPerson> contactPersonList;
	public String loadContactPersonOption(){
		try {
			Map<String,Object> params = getParams();
			if(null != parentId){
				params.put("customerAccount.id", parentId);
				contactPersonList = baseHibernateService.findAllByConditions(ContactPerson.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "loadContactPersonOption";
	}
	
	public String goSaveOrUpdate(){
		try {
			Map<String,Object> params = getParams();
			params.put("enable,"+SearchCondition.EQUAL, "1");
			crmContactTypeList = baseHibernateService.findAllByConditions(CrmContactType.class, params);
			contactPersonTypeList = baseHibernateService.findAllByConditions(ContactPersonType.class, params);
			
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				contactPerson = baseHibernateService.findEntityById(ContactPerson.class,id);
				if(null == contactPerson.getCreatedBy() || "".equals(contactPerson.getCreatedBy())){
					if(null != SecurityUtil.getCurrentEmpId() && !"".equals(SecurityUtil.getCurrentEmpId())){
						Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if(null != emp){
							contactPerson.setCreatedBy(emp.getName());
						}
					}
				}
				if(null == contactPerson.getCreateTime()){
					if(null == contactPerson.getDateEntered()){
						contactPerson.setDateEntered(new Date());
						contactPerson.setCreateTime(new Date());
					}
				}else {
					contactPerson.setDateEntered(contactPerson.getCreateTime());
				}
			}else {
				contactPerson = new ContactPerson();
				loadCommonData(contactPerson);
				if(StrUtils.isNotEmpty(parentId)){
					CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, parentId);
					if(customerAccount != null){
						contactPerson.setCustomerAccount(customerAccount);
					}
				}
				Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if(null != emp){
					contactPerson.setCreatedBy(emp.getName());
				}
				contactPerson.setDateEntered(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if (null != contactPerson.getId() && !"".equals(contactPerson.getId())) {
				isSave = false;
			} else {
				contactPerson.setCreateTime(new Date());
			}
			
			if(null == contactPerson.getPrimaryContact()){
				contactPerson.setPrimaryContact("0");
			}
			
			/** 检查空值对象 */
			String[] attrArray = {"customerAccount","contactPersonType","credentialType","crmContactType"};
			checkEntityNullValue(contactPerson, attrArray);
			
			if(null != contactPerson.getLastName() && !"".equals(contactPerson.getLastName())){
				contactPerson.setName(contactPerson.getLastName());
				contactPerson.setChineseFirstLetter(ChnToPinYin.getPYString(contactPerson.getLastName()).toUpperCase().substring(0, 1));
			}
			if(contactPerson.getCustomerAccount() != null && StrUtils.isNotEmpty(contactPerson.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, contactPerson.getCustomerAccount().getId());
				if(customerAccount != null){
					if(StrUtils.isNotEmpty(customerAccount.getContactPersonIds())){
						customerAccount.setContactPersonIds(customerAccount.getContactPersonIds() + "," + contactPerson.getId());
						customerAccount.setContactPersonNames(customerAccount.getContactPersonNames() + "," + contactPerson.getName());
					}else{
						customerAccount.setContactPersonIds(contactPerson.getId());
						customerAccount.setContactPersonNames(contactPerson.getName());
					}
					customerAccount = baseHibernateService.merge(customerAccount);
				}
			}
			initEntityBaseController.initEntityBaseAttribute(contactPerson);
			contactPerson = baseHibernateService.merge(contactPerson);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	public void deleteById(){
		try {
			contactPerson = baseHibernateService.findEntityById(ContactPerson.class,id);
			if(null != contactPerson){
				baseHibernateService.deleteById(ContactPerson.class,id);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<CrmContactType> getCrmContactTypeList() {
		return crmContactTypeList;
	}

	public void setCrmContactTypeList(List<CrmContactType> crmContactTypeList) {
		this.crmContactTypeList = crmContactTypeList;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<ContactPersonType> getContactPersonTypeList() {
		return contactPersonTypeList;
	}

	public void setContactPersonTypeList(List<ContactPersonType> contactPersonTypeList) {
		this.contactPersonTypeList = contactPersonTypeList;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
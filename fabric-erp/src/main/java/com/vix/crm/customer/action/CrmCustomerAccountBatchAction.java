package com.vix.crm.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.CustomerSource;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.crm.base.entity.CustomerType;
import com.vix.crm.base.entity.HotLevel;
import com.vix.crm.base.entity.RelationshipClass;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.entity.CustomerShare;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Controller
@Scope("prototype")
public class CrmCustomerAccountBatchAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;
	
	private Long id;
	private String ids;
	private CustomerAccount customerAccount;
	private ContactPerson contactPerson;
	private String pageNo;
	private List<HotLevel> hotLevelList;
	private List<CustomerType> customerTypeList;
	private List<CustomerSource> customerSourceList;
	private List<CustomerStage> customerStageList;
	private List<RelationshipClass> relationshipClassList;
	private String customerAccountType;//客户类型
	
	/** 获取列表数据  */
	public String goBatchAddList(){
		return "goBatchAddList";
	}
	
	/** 获取列表数据  */
	public String goBatchUpdateList(){
		return "goBatchUpdateList";
	}
	
	/** 获取列表数据  */
	public String goBatchAddContent(){
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goBatchAddContent";
	}
 
	/** 获取列表数据  */
	public String goBatchUpdateContent(){
		try {
			customerTypeList = customerAccountService.findAllByEntityClassAndAttribute(CustomerType.class, "enable", "1");
			relationshipClassList = customerAccountService.findAllByEntityClassAndAttribute(RelationshipClass.class, "enable", "1");
			customerSourceList = customerAccountService.findAllByEntityClassAndAttribute(CustomerSource.class, "enable", "1");
			customerStageList = customerAccountService.findAllByEntityClassAndAttribute(CustomerStage.class, "enable", "1");
			hotLevelList = customerAccountService.findAllByEntityClassAndAttribute(HotLevel.class, "enable", "1");
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = customerAccountService.findPagerByHqlConditions(getPager(), CustomerAccount.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goBatchUpdateContent";
	}
	
	public String batchAdd(){
		boolean isSave = true;
		try {
			customerAccount.setCreateTime(new Date());
			loadCommonData(customerAccount);
			customerAccount.setIsHighSea("0");
			customerAccount.setIsTemp("0");
			customerAccount.setIsDeleted("0");
			if(null == customerAccount.getHotLevel() || null == customerAccount.getHotLevel().getId() || !customerAccount.getHotLevel().getId().equals("") || !customerAccount.getHotLevel().getId().equals("0")){
				customerAccount.setHotLevel(null);
			}
			if(null == customerAccount.getCustomerType() || null == customerAccount.getCustomerType().getId() || !customerAccount.getCustomerType().equals("") || !customerAccount.getCustomerType().getId().equals("0")){
				customerAccount.setCustomerType(null);
			}
			if(null == customerAccount.getCustomerSource() || null == customerAccount.getCustomerSource().getId() || !customerAccount.getCustomerSource().equals("") || !customerAccount.getCustomerSource().getId().equals("0")){
				customerAccount.setCustomerSource(null);
			}
			if(null == customerAccount.getCustomerStage() || null == customerAccount.getCustomerStage().getId() || !customerAccount.getCustomerStage().equals("") || !customerAccount.getCustomerStage().getId().equals("0")){
				customerAccount.setCustomerStage(null);
			}
			if(null == customerAccount.getRelationshipClass() || null == customerAccount.getRelationshipClass().getId() || !customerAccount.getRelationshipClass().equals("") || !customerAccount.getRelationshipClass().getId().equals("0")){
				customerAccount.setRelationshipClass(null);
			}
			customerAccount = customerAccountService.merge(customerAccount);
			CustomerShare cs = new CustomerShare();
			cs.setCustomerAccount(customerAccount);
			cs.setEmployee(new Employee());
			cs.getEmployee().setId(getEmployee().getId());
			cs.setRead("1");cs.setWrite("1");
			loadCommonData(cs);
			customerAccountService.merge(cs);
			if("personal".equals(customerAccount.getType())){
				if(null == contactPerson.getContactPersonType() || null == contactPerson.getContactPersonType().getId() || !contactPerson.getContactPersonType().getId().equals("") || !contactPerson.getContactPersonType().getId().equals("0")){
					contactPerson.setContactPersonType(null);
				}
				if(null == contactPerson.getCredentialType() || null == contactPerson.getCredentialType().getId() || !contactPerson.getCredentialType().getId().equals("") || !contactPerson.getCredentialType().getId().equals("0")){
					contactPerson.setCredentialType(null);
				}
				contactPerson.setCustomerAccount(customerAccount);
				contactPerson = customerAccountService.merge(contactPerson);
			}
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
		return UPDATE;
	}
	
	public String batchUpdate(){
		try {
			if(null != customerAccount.getId() && !customerAccount.getId().equals("") && !customerAccount.getId().equals("0")){
				CustomerAccount ca = customerAccountService.findEntityById(CustomerAccount.class, customerAccount.getId());
				ca.setUpdateTime(new Date());
				ca.setCode(customerAccount.getCode());
				ca.setName(customerAccount.getName());
				ca.setShorterName(customerAccount.getShorterName());
				if(null == customerAccount.getHotLevel() || null == customerAccount.getHotLevel().getId() || !customerAccount.getHotLevel().getId().equals("") || !customerAccount.getHotLevel().getId().equals("0")){
					customerAccount.setHotLevel(null);
				}
				if(null == customerAccount.getCustomerType() || null == customerAccount.getCustomerType().getId() || !customerAccount.getCustomerType().equals("") || !customerAccount.getCustomerType().getId().equals("0")){
					customerAccount.setCustomerType(null);
				}
				if(null == customerAccount.getCustomerSource() || null == customerAccount.getCustomerSource().getId() || !customerAccount.getCustomerSource().equals("") || !customerAccount.getCustomerSource().getId().equals("0")){
					customerAccount.setCustomerSource(null);
				}
				if(null == customerAccount.getCustomerStage() || null == customerAccount.getCustomerStage().getId() || !customerAccount.getCustomerStage().equals("") || !customerAccount.getCustomerStage().getId().equals("0")){
					customerAccount.setCustomerStage(null);
				}
				if(null == customerAccount.getRelationshipClass() || null == customerAccount.getRelationshipClass().getId() || !customerAccount.getRelationshipClass().equals("") || !customerAccount.getRelationshipClass().getId().equals("0")){
					customerAccount.setRelationshipClass(null);
				}
				 customerAccountService.merge(ca);
			}
			renderText(UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
		return UPDATE;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<RelationshipClass> getRelationshipClassList() {
		return relationshipClassList;
	}

	public void setRelationshipClassList(
			List<RelationshipClass> relationshipClassList) {
		this.relationshipClassList = relationshipClassList;
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
}

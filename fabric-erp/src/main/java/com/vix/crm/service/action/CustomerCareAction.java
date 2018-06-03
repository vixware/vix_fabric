package com.vix.crm.service.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.service.entity.CustomerCare;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class CustomerCareAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String customerAccountId;
	private CustomerCare customerCare;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			baseHibernateService.findPagerByHqlConditions(getPager(), CustomerCare.class, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取列表数据  */
	public String goListContentForCrmProject(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<CustomerCare> pcList = baseHibernateService.findAllByEntityClassAndAttribute(CustomerCare.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListForCrmProject";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				customerCare = baseHibernateService.findEntityById(CustomerCare.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateForCrmProject() {
		try {
			if(null != id && !"".equals(id)){
				customerCare = baseHibernateService.findEntityById(CustomerCare.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateForCrmProject";
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(customerCare.getId())){
				isSave = false;
			}else{
				customerCare.setCreateTime(new Date());
				customerCare.setIsDeleted("0");
				loadCommonData(customerCare);
			}
			
			String [] attrArray = {"employee","crmProject","customerAccount","contactPerson"};
			checkEntityNullValue(customerCare,attrArray);
			
			customerCare = baseHibernateService.merge(customerCare);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			CustomerCare pb = baseHibernateService.findEntityById(CustomerCare.class,id);
			if(null != pb){
				pb.setIsDeleted("1");
				baseHibernateService.merge(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goSaveOrUpdateForCustomerAccount(){
		goSaveOrUpdate();
		return "goSaveOrUpdateForCustomerAccount";
	}
	
	public String saveOrUpdateForCustomerAccount(){
		saveOrUpdate();
		return UPDATE;
	}

	public String goListContentForCustomerAccount(){
		Map<String,Object> params = getParams();
		if(null != id && !"".equals(id)){
			params.put("customerAccount.id,"+SearchCondition.EQUAL, id);
		}
		goListContent();
		return "goSingleListForCustomerAccount";
	}
	
	private List<ContactPerson> contactPersonList = new ArrayList<ContactPerson>();
	public String loadCustomerContactPerson(){
		try{
			if(null != id && !"".equals(id)){
				customerCare = baseHibernateService.findEntityById(CustomerCare.class, id);
			}
			if(null != customerAccountId && !customerAccountId.equals("")){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
				if(null != customerAccount && null != customerAccount.getContactPersons() && customerAccount.getContactPersons().size() > 0){
					contactPersonList.addAll(customerAccount.getContactPersons());
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "loadCustomerContactPerson";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerCare getCustomerCare() {
		return customerCare;
	}

	public void setCustomerCare(CustomerCare customerCare) {
		this.customerCare = customerCare;
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}
}

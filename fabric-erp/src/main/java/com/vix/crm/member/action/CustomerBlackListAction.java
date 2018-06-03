package com.vix.crm.member.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.crm.service.ICustomerAccountService;

@Controller
@Scope("prototype")
public class CustomerBlackListAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;
	
	private String id;
	private CustomerAccount customerAccount;
	private ContactPerson contactPerson;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			StringBuilder hqlBuilder = new StringBuilder("select ca from CustomerShare cs left join cs.customerAccount ca left join ca.contactPersons cp where cp.isBlack = '1' and cs.customerAccount.id = ca.id AND ca.isTemp = :isTemp and ca.isDeleted = :isDeleted and ca.type = :type");
			Map<String,Object> params = getParams();
			params.put("isTemp,"+SearchCondition.EQUAL, "0");
			params.put("isDeleted,"+SearchCondition.EQUAL, "0");
			params.put("type,"+SearchCondition.EQUAL, "member");
			String name = getRequestParameter("name");
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
				hqlBuilder.append("and ca.name like :name ");
			}
			Employee emp = getEmployee();
			if(null != emp){
				params.put("creatorCode,"+SearchCondition.EQUAL, emp.getCode());
				hqlBuilder.append(" and ca.creatorCode = :creatorCode ");
			}
			params.put("isHighSea,"+SearchCondition.EQUAL, "1");
			hqlBuilder.append(" or ca.isHighSea = :isHighSea ");
			if(null != getPager().getOrderField()){
				hqlBuilder.append(" order by ca.");
				hqlBuilder.append(getPager().getOrderField());
				if(null != getPager().getOrderBy()){
					hqlBuilder.append(" ");
					hqlBuilder.append(getPager().getOrderBy());
				}
			}
			customerAccountService.findPagerByHql(getPager(), "ca", hqlBuilder.toString(), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	public String showMember(){
		try{
			if(null != id && !"".equals(id)){
				customerAccount = customerAccountService.findEntityById(CustomerAccount.class,id);
				contactPerson = customerAccountService.findEntityByAttribute(ContactPerson.class, "customerAccount.id", id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "showMember";
	}
	
	private String type;
	public String goAddReason(){
		try{
			if(null != id && !"".equals(id)){
				contactPerson = customerAccountService.findEntityByAttribute(ContactPerson.class, "customerAccount.id", id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "addReason";
	}
	
	public String addReason(){
		try{
			if(null != id && !"".equals(id)){
				contactPerson = customerAccountService.findEntityByAttribute(ContactPerson.class, "customerAccount.id", id);
				String reason = getRequestParameter("reason");
				String type = getRequestParameter("type");
				if(null != type && "add".equals(type)){
					contactPerson.setIsBlack("1");
					contactPerson.setBlackReason(reason);
					contactPerson.setRemoveBlackReason("");
				}
				if(null != type && "remove".equals(type)){
					contactPerson.setIsBlack("0");
					contactPerson.setBlackReason("");
					contactPerson.setRemoveBlackReason(reason);
				}
				customerAccountService.merge(contactPerson);
			}
			renderText(UPDATE_SUCCESS);
		}catch(Exception ex){
			ex.printStackTrace();
			renderText(UPDATE_FAIL);
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			CustomerAccount pb = customerAccountService.findEntityById(CustomerAccount.class,id);
			if(null != pb){
				pb.setIsDeleted("1");
				customerAccountService.merge(pb);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

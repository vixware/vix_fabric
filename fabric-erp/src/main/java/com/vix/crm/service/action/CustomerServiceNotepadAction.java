package com.vix.crm.service.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.service.entity.CustomerServiceNotepad;

@Controller
@Scope("prototype")
public class CustomerServiceNotepadAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private CustomerServiceNotepad customerServiceNotepad;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<CustomerServiceNotepad> pcList = baseHibernateService.findAllByEntityClassAndAttribute(CustomerServiceNotepad.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goListContentForCustomerAccount(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			if(null != id && !"".equals(id)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, id);
			}
			List<CustomerServiceNotepad> pcList = baseHibernateService.findAllByConditions(CustomerServiceNotepad.class,params);
			pager.setResultList(pcList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListForCustomerAccount";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				customerServiceNotepad = baseHibernateService.findEntityById(CustomerServiceNotepad.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != customerServiceNotepad.getId()){
				isSave = false;
			}else{
				customerServiceNotepad.setCreateTime(new Date());
				loadCommonData(customerServiceNotepad);
			}
			customerServiceNotepad = baseHibernateService.merge(customerServiceNotepad);
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
			CustomerServiceNotepad pb = baseHibernateService.findEntityById(CustomerServiceNotepad.class,id);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerServiceNotepad getCustomerServiceNotepad() {
		return customerServiceNotepad;
	}

	public void setCustomerServiceNotepad(CustomerServiceNotepad customerServiceNotepad) {
		this.customerServiceNotepad = customerServiceNotepad;
	}
}

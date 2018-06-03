package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.CustomerStage;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixCustomerStageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private CustomerStage customerStage;
	
	/** 获取列表数据  */
	public void getCustomerStageJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerStage.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				customerStage = vixntBaseService.findEntityById(CustomerStage.class, id);
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
			if(StrUtils.isNotEmpty(customerStage.getId())){
				isSave = false;
				customerStage.setUpdateTime(new Date());
			}else{
				customerStage.setCreateTime(new Date());
				customerStage.setUpdateTime(new Date());
			}
			customerStage = vixntBaseService.merge(customerStage);
			if("1".equals(customerStage.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("id," + SearchCondition.NOEQUAL, customerStage.getId());
				List<CustomerStage> customerStages = vixntBaseService.findAllDataByConditions(CustomerStage.class, params);
				if(customerStages != null && customerStages.size() > 0){
					for (CustomerStage cs : customerStages) {
						cs.setIsDefault("0");
						cs = vixntBaseService.merge(cs);
					}
				}
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			customerStage = vixntBaseService.findEntityById(CustomerStage.class, id);
			if(null != customerStage){
				vixntBaseService.deleteByEntity(customerStage);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("客户阶段已使用,不可删除");
		}
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

	public CustomerStage getCustomerStage() {
		return customerStage;
	}

	public void setCustomerStage(CustomerStage customerStage) {
		this.customerStage = customerStage;
	}

}

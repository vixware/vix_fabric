package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CustomerType;

@Controller
@Scope("prototype")
public class CustomerTypeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<CustomerType> customerTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			customerTypeList = baseHibernateService.findAllByEntityClass(CustomerType.class);
			if(null != customerTypeList && customerTypeList.size()<8){
				int stepCount = 8-customerTypeList.size();
				for(int i=0;i<stepCount;i++){
					customerTypeList.add(new CustomerType());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String data = getRequestParameter("data");
			if(null != data && !"".equals(data)){
				String[] cs = data.split(",");
				for(String s :cs){
					String[] csItem = s.split(":");
					CustomerType customerType = new CustomerType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						customerType.setId(csItem[0].toString());
						isSave = false;
					}
					customerType.setIsDefault(csItem[1]);
					customerType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						customerType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						customerType.setMemo(csItem[4]);
					}
					loadCommonData(customerType);
					baseHibernateService.merge(customerType);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CustomerType> getCustomerTypeList() {
		return customerTypeList;
	}

	public void setCustomerTypeList(List<CustomerType> customerTypeList) {
		this.customerTypeList = customerTypeList;
	}
}

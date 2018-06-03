package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CustomerServiceStatus;

@Controller
@Scope("prototype")
public class CustomerServiceStatusAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<CustomerServiceStatus> customerServiceStatusList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			customerServiceStatusList = baseHibernateService.findAllByEntityClass(CustomerServiceStatus.class);
			if(null != customerServiceStatusList && customerServiceStatusList.size()<8){
				int stepCount = 8-customerServiceStatusList.size();
				for(int i=0;i<stepCount;i++){
					customerServiceStatusList.add(new CustomerServiceStatus());
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
					CustomerServiceStatus customerServiceStatus = new CustomerServiceStatus();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						customerServiceStatus.setId(csItem[0].toString());
						isSave = false;
					}
					customerServiceStatus.setIsDefault(csItem[1]);
					customerServiceStatus.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						customerServiceStatus.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						customerServiceStatus.setMemo(csItem[4]);
					}
					loadCommonData(customerServiceStatus);
					baseHibernateService.merge(customerServiceStatus);
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

	public List<CustomerServiceStatus> getCustomerServiceStatusList() {
		return customerServiceStatusList;
	}

	public void setCustomerServiceStatusList(List<CustomerServiceStatus> customerServiceStatusList) {
		this.customerServiceStatusList = customerServiceStatusList;
	}
}

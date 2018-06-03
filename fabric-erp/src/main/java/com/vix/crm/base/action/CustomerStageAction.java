package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CustomerStage;

@Controller
@Scope("prototype")
public class CustomerStageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<CustomerStage> customerStageList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			customerStageList = baseHibernateService.findAllByEntityClass(CustomerStage.class);
			if(null != customerStageList && customerStageList.size()<8){
				int stepCount = 8-customerStageList.size();
				for(int i=0;i<stepCount;i++){
					customerStageList.add(new CustomerStage());
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
					CustomerStage customerStage = new CustomerStage();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						customerStage.setId(csItem[0].toString());
						isSave = false;
					}
					customerStage.setIsDefault(csItem[1]);
					customerStage.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						customerStage.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						customerStage.setMemo(csItem[4]);
					}
					loadCommonData(customerStage);
					baseHibernateService.merge(customerStage);
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

	public List<CustomerStage> getCustomerStageList() {
		return customerStageList;
	}

	public void setCustomerStageList(List<CustomerStage> customerStageList) {
		this.customerStageList = customerStageList;
	}
}

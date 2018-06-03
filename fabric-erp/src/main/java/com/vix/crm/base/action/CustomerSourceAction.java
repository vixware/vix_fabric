package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.CustomerSource;

@Controller
@Scope("prototype")
public class CustomerSourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<CustomerSource> customerSourceList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			customerSourceList = baseHibernateService.findAllByEntityClass(CustomerSource.class);
			if(null != customerSourceList && customerSourceList.size()<8){
				int stepCount = 8-customerSourceList.size();
				for(int i=0;i<stepCount;i++){
					customerSourceList.add(new CustomerSource());
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
					CustomerSource customerSource = new CustomerSource();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						customerSource.setId(csItem[0].toString());
						isSave = false;
					}
					customerSource.setIsDefault(csItem[1]);
					customerSource.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						customerSource.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						customerSource.setMemo(csItem[4]);
					}
					loadCommonData(customerSource);
					baseHibernateService.merge(customerSource);
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

	public List<CustomerSource> getCustomerSourceList() {
		return customerSourceList;
	}

	public void setCustomerSourceList(List<CustomerSource> customerSourceList) {
		this.customerSourceList = customerSourceList;
	}
}

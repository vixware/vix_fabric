package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ServiceType;

@Controller
@Scope("prototype")
public class ServiceTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ServiceType> serviceTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			serviceTypeList = baseHibernateService.findAllByEntityClass(ServiceType.class);
			if(null != serviceTypeList && serviceTypeList.size()<8){
				int stepCount = 8-serviceTypeList.size();
				for(int i=0;i<stepCount;i++){
					serviceTypeList.add(new ServiceType());
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
					ServiceType serviceType = new ServiceType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						serviceType.setId(csItem[0]);
						isSave = false;
					}
					serviceType.setIsDefault(csItem[1]);
					serviceType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						serviceType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						serviceType.setMemo(csItem[4]);
					}
					loadCommonData(serviceType);
					baseHibernateService.merge(serviceType);
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

	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}
}

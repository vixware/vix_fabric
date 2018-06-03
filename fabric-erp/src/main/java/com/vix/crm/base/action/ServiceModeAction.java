package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ServiceMode;

@Controller
@Scope("prototype")
public class ServiceModeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ServiceMode> serviceModeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			serviceModeList = baseHibernateService.findAllByEntityClass(ServiceMode.class);
			if(null != serviceModeList && serviceModeList.size()<8){
				int stepCount = 8-serviceModeList.size();
				for(int i=0;i<stepCount;i++){
					serviceModeList.add(new ServiceMode());
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
					ServiceMode serviceMode = new ServiceMode();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						serviceMode.setId(csItem[0]);
						isSave = false;
					}
					serviceMode.setIsDefault(csItem[1]);
					serviceMode.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						serviceMode.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						serviceMode.setMemo(csItem[4]);
					}
					loadCommonData(serviceMode);
					baseHibernateService.merge(serviceMode);
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

	public List<ServiceMode> getServiceModeList() {
		return serviceModeList;
	}

	public void setServiceModeList(List<ServiceMode> serviceModeList) {
		this.serviceModeList = serviceModeList;
	}
}

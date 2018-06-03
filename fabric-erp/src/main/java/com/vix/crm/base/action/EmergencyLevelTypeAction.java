package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.EmergencyLevelType;

@Controller
@Scope("prototype")
public class EmergencyLevelTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<EmergencyLevelType> emergencyLevelTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			emergencyLevelTypeList = baseHibernateService.findAllByEntityClass(EmergencyLevelType.class);
			if(null != emergencyLevelTypeList && emergencyLevelTypeList.size()<8){
				int stepCount = 8-emergencyLevelTypeList.size();
				for(int i=0;i<stepCount;i++){
					emergencyLevelTypeList.add(new EmergencyLevelType());
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
					EmergencyLevelType emergencyLevelType = new EmergencyLevelType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						emergencyLevelType.setId(csItem[0].toString());
						isSave = false;
					}
					emergencyLevelType.setIsDefault(csItem[1]);
					emergencyLevelType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						emergencyLevelType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						emergencyLevelType.setMemo(csItem[4]);
					}
					loadCommonData(emergencyLevelType);
					baseHibernateService.merge(emergencyLevelType);
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

	public List<EmergencyLevelType> getEmergencyLevelTypeList() {
		return emergencyLevelTypeList;
	}

	public void setEmergencyLevelTypeList(List<EmergencyLevelType> emergencyLevelTypeList) {
		this.emergencyLevelTypeList = emergencyLevelTypeList;
	}
}

package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.StaffScale;

@Controller
@Scope("prototype")
public class StaffScaleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<StaffScale> staffScaleList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			staffScaleList = baseHibernateService.findAllByEntityClass(StaffScale.class);
			if(null != staffScaleList && staffScaleList.size()<8){
				int stepCount = 8-staffScaleList.size();
				for(int i=0;i<stepCount;i++){
					staffScaleList.add(new StaffScale());
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
					StaffScale staffScale = new StaffScale();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						staffScale.setId(csItem[0]);
						isSave = false;
					}
					staffScale.setIsDefault(csItem[1]);
					staffScale.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						staffScale.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						staffScale.setMemo(csItem[4]);
					}
					loadCommonData(staffScale);
					baseHibernateService.merge(staffScale);
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

	public List<StaffScale> getStaffScaleList() {
		return staffScaleList;
	}

	public void setStaffScaleList(List<StaffScale> staffScaleList) {
		this.staffScaleList = staffScaleList;
	}
}

package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ProjectCostType;

@Controller
@Scope("prototype")
public class ProjectCostTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ProjectCostType> projectCostTypeList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			projectCostTypeList = baseHibernateService.findAllByEntityClass(ProjectCostType.class);
			if(null != projectCostTypeList && projectCostTypeList.size()<8){
				int stepCount = 8-projectCostTypeList.size();
				for(int i=0;i<stepCount;i++){
					projectCostTypeList.add(new ProjectCostType());
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
					ProjectCostType projectCostType = new ProjectCostType();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						projectCostType.setId(csItem[0].toString());
						isSave = false;
					}
					projectCostType.setIsDefault(csItem[1]);
					projectCostType.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						projectCostType.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						projectCostType.setMemo(csItem[4]);
					}
					loadCommonData(projectCostType);
					baseHibernateService.merge(projectCostType);
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

	public List<ProjectCostType> getProjectCostTypeList() {
		return projectCostTypeList;
	}

	public void setProjectCostTypeList(List<ProjectCostType> projectCostTypeList) {
		this.projectCostTypeList = projectCostTypeList;
	}
}

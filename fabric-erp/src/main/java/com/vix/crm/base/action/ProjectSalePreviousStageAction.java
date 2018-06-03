package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ProjectSalePreviousStage;

@Controller
@Scope("prototype")
public class ProjectSalePreviousStageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ProjectSalePreviousStage> projectSalePreviousStageList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			projectSalePreviousStageList = baseHibernateService.findAllByEntityClass(ProjectSalePreviousStage.class);
			if(null != projectSalePreviousStageList && projectSalePreviousStageList.size()<8){
				int stepCount = 8-projectSalePreviousStageList.size();
				for(int i=0;i<stepCount;i++){
					projectSalePreviousStageList.add(new ProjectSalePreviousStage());
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
					ProjectSalePreviousStage projectSalePreviousStage = new ProjectSalePreviousStage();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						projectSalePreviousStage.setId(csItem[0].toString());
						isSave = false;
					}
					projectSalePreviousStage.setIsDefault(csItem[1]);
					projectSalePreviousStage.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						projectSalePreviousStage.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						projectSalePreviousStage.setMemo(csItem[4]);
					}
					loadCommonData(projectSalePreviousStage);
					baseHibernateService.merge(projectSalePreviousStage);
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

	public List<ProjectSalePreviousStage> getProjectSalePreviousStageList() {
		return projectSalePreviousStageList;
	}

	public void setProjectSalePreviousStageList(List<ProjectSalePreviousStage> projectSalePreviousStageList) {
		this.projectSalePreviousStageList = projectSalePreviousStageList;
	}
}

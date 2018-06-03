package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ProjectStage;

@Controller
@Scope("prototype")
public class ProjectStageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ProjectStage> projectStageList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			projectStageList = baseHibernateService.findAllByEntityClass(ProjectStage.class);
			if(null != projectStageList && projectStageList.size()<8){
				int stepCount = 8-projectStageList.size();
				for(int i=0;i<stepCount;i++){
					projectStageList.add(new ProjectStage());
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
					ProjectStage projectStage = new ProjectStage();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						projectStage.setId(csItem[0].toString());
						isSave = false;
					}
					projectStage.setIsDefault(csItem[1]);
					projectStage.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						projectStage.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						projectStage.setMemo(csItem[4]);
					}
					loadCommonData(projectStage);
					baseHibernateService.merge(projectStage);
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

	public List<ProjectStage> getProjectStageList() {
		return projectStageList;
	}

	public void setProjectStageList(List<ProjectStage> projectStageList) {
		this.projectStageList = projectStageList;
	}
}

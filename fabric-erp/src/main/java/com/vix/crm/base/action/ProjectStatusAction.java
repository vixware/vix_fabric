package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ProjectStatus;

@Controller
@Scope("prototype")
public class ProjectStatusAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ProjectStatus> projectStatusList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			projectStatusList = baseHibernateService.findAllByEntityClass(ProjectStatus.class);
			if(null != projectStatusList && projectStatusList.size()<8){
				int stepCount = 8-projectStatusList.size();
				for(int i=0;i<stepCount;i++){
					projectStatusList.add(new ProjectStatus());
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
					ProjectStatus projectStatus = new ProjectStatus();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						projectStatus.setId(csItem[0].toString());
						isSave = false;
					}
					projectStatus.setIsDefault(csItem[1]);
					projectStatus.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						projectStatus.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						projectStatus.setMemo(csItem[4]);
					}
					loadCommonData(projectStatus);
					baseHibernateService.merge(projectStatus);
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

	public List<ProjectStatus> getProjectStatusList() {
		return projectStatusList;
	}

	public void setProjectStatusList(List<ProjectStatus> projectStatusList) {
		this.projectStatusList = projectStatusList;
	}
}

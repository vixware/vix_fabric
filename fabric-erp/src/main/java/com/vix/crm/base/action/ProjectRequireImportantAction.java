package com.vix.crm.base.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.crm.base.entity.ProjectRequireImportant;

@Controller
@Scope("prototype")
public class ProjectRequireImportantAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private List<ProjectRequireImportant> projectRequireImportantList;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			projectRequireImportantList = baseHibernateService.findAllByEntityClass(ProjectRequireImportant.class);
			if(null != projectRequireImportantList && projectRequireImportantList.size()<8){
				int stepCount = 8-projectRequireImportantList.size();
				for(int i=0;i<stepCount;i++){
					projectRequireImportantList.add(new ProjectRequireImportant());
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
					ProjectRequireImportant projectRequireImportant = new ProjectRequireImportant();
					if(!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])){
						projectRequireImportant.setId(csItem[0].toString());
						isSave = false;
					}
					projectRequireImportant.setIsDefault(csItem[1]);
					projectRequireImportant.setEnable(csItem[2]);
					if(!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])){
						projectRequireImportant.setName(csItem[3]);
					}
					if(!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])){
						projectRequireImportant.setMemo(csItem[4]);
					}
					loadCommonData(projectRequireImportant);
					baseHibernateService.merge(projectRequireImportant);
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

	public List<ProjectRequireImportant> getProjectRequireImportantList() {
		return projectRequireImportantList;
	}

	public void setProjectRequireImportantList(List<ProjectRequireImportant> projectRequireImportantList) {
		this.projectRequireImportantList = projectRequireImportantList;
	}
}

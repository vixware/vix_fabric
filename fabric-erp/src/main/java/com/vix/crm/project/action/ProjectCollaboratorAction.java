package com.vix.crm.project.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.CollaboratorType;
import com.vix.crm.project.entity.ProjectCollaborator;

/** 项目协作方 */
@Controller
@Scope("prototype")
public class ProjectCollaboratorAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ProjectCollaborator projectCollaborator;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<ProjectCollaborator> pcList = baseHibernateService.findAllByEntityClassAndAttribute(ProjectCollaborator.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	private List<CollaboratorType> collaboratorTypeList;
	public String goSaveOrUpdate() {
		try {
			collaboratorTypeList = baseHibernateService.findAllByEntityClassAndAttribute(CollaboratorType.class, "enable", "1");
			if(null != id && !"".equals(id)){
				projectCollaborator = baseHibernateService.findEntityById(ProjectCollaborator.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != projectCollaborator.getId()){
				isSave = false;
			}else{
				projectCollaborator.setCreateTime(new Date());
				loadCommonData(projectCollaborator);
			}
			if(null == projectCollaborator.getCollaboratorType().getId() || !projectCollaborator.getCollaboratorType().getId().equals("") || !projectCollaborator.getCollaboratorType().getId().equals("0")){
				projectCollaborator.setCollaboratorType(null);
			}
			if(null == projectCollaborator.getCustomerAccount().getId() || null == projectCollaborator.getCustomerAccount().getId()){
				projectCollaborator.setCustomerAccount(null);
			}
			projectCollaborator = baseHibernateService.merge(projectCollaborator);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ProjectCollaborator pb = baseHibernateService.findEntityById(ProjectCollaborator.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CollaboratorType> getCollaboratorTypeList() {
		return collaboratorTypeList;
	}

	public void setCollaboratorTypeList(List<CollaboratorType> collaboratorTypeList) {
		this.collaboratorTypeList = collaboratorTypeList;
	}

	public ProjectCollaborator getProjectCollaborator() {
		return projectCollaborator;
	}

	public void setProjectCollaborator(ProjectCollaborator projectCollaborator) {
		this.projectCollaborator = projectCollaborator;
	}
}

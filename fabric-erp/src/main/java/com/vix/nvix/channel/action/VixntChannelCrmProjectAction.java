package com.vix.nvix.channel.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ProjectSalePreviousStage;
import com.vix.crm.base.entity.ProjectStage;
import com.vix.crm.base.entity.ProjectStatus;
import com.vix.crm.project.entity.CrmProject;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class VixntChannelCrmProjectAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private CrmProject crmProject;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String title = getDecodeRequestParameter("title");
			if (null != title && !"".equals(title)) {
				params.put("subject," + SearchCondition.ANYLIKE, title);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			params.put("channelDistributor.id," + SearchCondition.ISNOT, null);
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), CrmProject.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<ProjectStage> projectStageList;//项目阶段
	private List<ProjectStatus> projectStatusList;//项目状态
	private List<ProjectSalePreviousStage> projectSalePreviousStageList;//售前阶段
	public String goSaveOrUpdate() {
		try {
			projectStageList = baseHibernateService.findAllByEntityClass(ProjectStage.class);
			projectStatusList = baseHibernateService.findAllByEntityClass(ProjectStatus.class);
			projectSalePreviousStageList = baseHibernateService.findAllByEntityClass(ProjectSalePreviousStage.class);
			if(null != id && !"".equals(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class,id);
			}else{
				crmProject = new CrmProject();
				crmProject.setProjectEstablishDate(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						crmProject.setChannelDistributor(employee.getChannelDistributor());
					} else {
						ChannelDistributor channelDistributor = baseHibernateService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
						if (channelDistributor != null) {
							crmProject.setChannelDistributor(channelDistributor);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(crmProject.getId())){
				isSave = false;
			}else{
				crmProject.setCreateTime(new Date());
				loadCommonData(crmProject);
			}
			
			String[] attrArray ={"customerAccount","projectStage","projectStatus","personInCharge","projectSalePreviousStage"};
			checkEntityNullValue(crmProject,attrArray);
			
			crmProject = baseHibernateService.merge(crmProject);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			CrmProject pb = baseHibernateService.findEntityById(CrmProject.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public List<ProjectStage> getProjectStageList() {
		return projectStageList;
	}

	public void setProjectStageList(List<ProjectStage> projectStageList) {
		this.projectStageList = projectStageList;
	}

	public List<ProjectStatus> getProjectStatusList() {
		return projectStatusList;
	}

	public void setProjectStatusList(List<ProjectStatus> projectStatusList) {
		this.projectStatusList = projectStatusList;
	}

	public List<ProjectSalePreviousStage> getProjectSalePreviousStageList() {
		return projectSalePreviousStageList;
	}

	public void setProjectSalePreviousStageList(List<ProjectSalePreviousStage> projectSalePreviousStageList) {
		this.projectSalePreviousStageList = projectSalePreviousStageList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
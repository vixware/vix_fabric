package com.vix.crm.project.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ProjectSolution;

@Controller
@Scope("prototype")
public class ProjectSolutionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String subject;
	private String crmProject;
	private String customerAccount;
	private ProjectSolution projectSolution;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			if(StrUtils.objectIsNotNull(crmProject)){
				crmProject = decode(crmProject, "UTF-8");
				params.put("crmProject.subject,"+SearchCondition.ANYLIKE, crmProject);
			}
			if(StrUtils.objectIsNotNull(customerAccount)){
				customerAccount = decode(customerAccount, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerAccount);
			}
			baseHibernateService.findPagerByHqlConditions(getPager(), ProjectSolution.class,params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取列表数据  */
	public String goListContentForCrmProject(){
		try {
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				List<ProjectSolution> pcList = baseHibernateService.findAllByEntityClassAndAttribute(ProjectSolution.class, "crmProject.id", id);
				pager.setResultList(pcList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSingleListForCrmProject";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id) && !"0".equals(id)){
				projectSolution = baseHibernateService.findEntityById(ProjectSolution.class,id);
			}else{
				projectSolution = new ProjectSolution();
				projectSolution.setSubmitDate(new Date());
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
			if(StrUtils.objectIsNotNull(projectSolution.getId())){
				isSave = false;
			}else{
				projectSolution.setCreateTime(new Date());
				loadCommonData(projectSolution);
			}
			
			String[] attrArray = {"customerAccount","crmProject"};
			checkEntityNullValue(projectSolution, attrArray);
			
			projectSolution = baseHibernateService.merge(projectSolution);
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
			ProjectSolution pb = baseHibernateService.findEntityById(ProjectSolution.class,id);
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

	//	弹框
	public String goSearch() {
		return "goSearch";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjectSolution getProjectSolution() {
		return projectSolution;
	}

	public void setProjectSolution(ProjectSolution projectSolution) {
		this.projectSolution = projectSolution;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(String crmProject) {
		this.crmProject = crmProject;
	}

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
}

package com.vix.crm.project.action;

import java.util.ArrayList;
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
import com.vix.crm.project.entity.ProjectCollaborator;
import com.vix.mdm.crm.entity.ContactPerson;

@Controller
@Scope("prototype")
public class CrmProjectAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private CrmProject crmProject;
	private String pageNo;
	private String name;
	private String companyCode;
	private List<String> possibilityList;
	
	public CrmProjectAction(){
		possibilityList = new ArrayList<String>();
		for(int i=1;i<11;i++){
			possibilityList.add(i+"0%");
		}
	}
	
	public String goMenuContent(){
		return "goMenuContent";
	}
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			if(null != companyCode && !"".equals(companyCode)){
				params.put("companyCode,"+SearchCondition.EQUAL, companyCode);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CrmProject.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

//	高级搜索
	public String goSearch() {
		return "goSearch";
	}
	
	
	/** 获取列表数据  */
	public String goSubListContent(){
		try {
			Map<String,Object> params = getParams();
			
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			getPager().setPageSize(10);
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CrmProject.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}
	
	public String goChooseCrmProject(){
		return "goChooseCrmProject";
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
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if(StrUtils.objectIsNotNull(crmProject.getId())){
				isSave = false;
			}else{
				crmProject.setCreateTime(new Date());
				loadCommonData(crmProject);
			}
			
			String[] attrArray ={"customerAccount","projectStage","projectStatus","personInCharge","projectSalePreviousStage"};
			checkEntityNullValue(crmProject,attrArray);
			
			crmProject = baseHibernateService.merge(crmProject);
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
			CrmProject pb = baseHibernateService.findEntityById(CrmProject.class,id);
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
	
	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if(null != ids && !"".equals(ids)){
				List<String> delIds = new ArrayList<String>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)){
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(CrmProject.class,delIds);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 项目视图 */
	public String goView(){
		try{
			if(null != id && !"".equals(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class,id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goView";
	}
	
	private List<ContactPerson> projectContactPerson = new ArrayList<ContactPerson>();
	public String goContactPersonListContent(){
		try{
			if(null != id && !"".equals(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class,id);
				if(null != crmProject && null != crmProject.getProjectCollaborators() && crmProject.getProjectCollaborators().size() > 0){
					for(ProjectCollaborator pc : crmProject.getProjectCollaborators()){
						if(null != pc.getCustomerAccount() && null != pc.getCustomerAccount().getContactPersons() && pc.getCustomerAccount().getContactPersons().size() > 0){
							projectContactPerson.addAll(pc.getCustomerAccount().getContactPersons());
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "goContactPersonListContent";
	}
	
	public String goSaleFunnel(){
		return "goSaleFunnel";
	}
	
	public String goChooseType(){
		return "goChooseType";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public List<ContactPerson> getProjectContactPerson() {
		return projectContactPerson;
	}

	public void setProjectContactPerson(List<ContactPerson> projectContactPerson) {
		this.projectContactPerson = projectContactPerson;
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

	public void setProjectSalePreviousStageList(
			List<ProjectSalePreviousStage> projectSalePreviousStageList) {
		this.projectSalePreviousStageList = projectSalePreviousStageList;
	}

	public List<String> getPossibilityList() {
		return possibilityList;
	}

	public void setPossibilityList(List<String> possibilityList) {
		this.possibilityList = possibilityList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}

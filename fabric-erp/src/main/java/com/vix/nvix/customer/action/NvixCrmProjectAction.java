package com.vix.nvix.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.activity.entity.Activity;
import com.vix.crm.base.entity.ProjectSalePreviousStage;
import com.vix.crm.base.entity.ProjectStage;
import com.vix.crm.base.entity.ProjectStatus;
import com.vix.crm.lead.entity.SaleLead;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.BackSectionPlan;
import com.vix.crm.salechance.entity.BackSectionRecord;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.service.ICustomerAccountService;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.sales.salepayment.entity.Expenses;

@Controller
@Scope("prototype")
public class NvixCrmProjectAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICustomerAccountService customerAccountService;
	
	private String id;
	private String name;
	private CrmProject crmProject;
	private String entityName;
	private String chooseType;
	private String employeeIds;
	private String employeeNames;
	private String parentId;
	private Integer projectSchedule;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Employee emp = getEmployee();
			if (emp != null) {
				Map<String,Object> params = getParams();
				StringBuilder hql = new StringBuilder();
				String ename = "hentity";
				hql.append("select ").append(ename);
				hql.append(" from CrmProject ").append(ename);
				hql.append(" left outer join fetch ").append(ename).append(".projectEmployees e ");
				hql.append(" where 1=1 and ").append(ename).append(".channelDistributor.id is null ");;
				String title = getDecodeRequestParameter("title");
				if (null != title && !"".equals(title)) {
					params.put("subject", title);
					hql.append(" and ").append(ename).append(".subject like :subject ");
				}
				String customerName = getRequestParameter("customerName");
				if(StrUtils.objectIsNotNull(customerName)){
					customerName = decode(customerName, "UTF-8");
					params.put("customerName", customerName);
					hql.append(" and ").append(ename).append(".customerAccount.name like :customerName ");
				}
				params.put("employeeId", emp.getId());
				hql.append(" and ").append("e.id = :employeeId ");
				pager = customerAccountService.findPagerByHql(pager, ename, hql.toString(), params);
			}
			String[] excludes = { " "};
			renderDataTable(pager,excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 销售机会列表 */
	public void getSaleChanceListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, SaleChance.class, params);
			}
			
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 销售线索列表 */
	public void getSaleLeadListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, SaleLead.class, params);
			}
			
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 回款计划列表 */
	public void getBackSectionPlanListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("owner.name,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, BackSectionPlan.class, params);
			}
			
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 回款记录列表 */
	public void getBackSectionRecordListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("owner.name,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, BackSectionRecord.class, params);
			}
			
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 销售活动列表 */
	public void getSaleActivityListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, parentId);
				
				if (null != name && !"".equals(name)) {
					params.put("title,"+ SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, Activity.class, params);
			}
			
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 项目沟通列表 */
	public void getEvaluationReviewListJson() {
		try {
			/** 设置分页信息 */
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("crmProject.id," + SearchCondition.ISNOT, null);
			baseHibernateService.findPagerByHqlConditions(pager, EvaluationReview.class, params);
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
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
				if (crmProject != null && crmProject.getProjectEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : crmProject.getProjectEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			}else{
				crmProject = new CrmProject();
				crmProject.setProjectEstablishDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					employeeIds = "" + e.getId();
					employeeNames = "" + e.getName();
					crmProject.setPersonInCharge(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	private Double contractAmount = 0d;// 回款计划金额
	private Double planAmount = 0d;// 回款计划金额
	private Double amount = 0d;// 回款价金额
	private Double expenseAmount = 0d;// 回款价金额
	public String view(){
		try{
			projectStageList = baseHibernateService.findAllByEntityClass(ProjectStage.class);
			projectStatusList = baseHibernateService.findAllByEntityClass(ProjectStatus.class);
			projectSalePreviousStageList = baseHibernateService.findAllByEntityClass(ProjectSalePreviousStage.class);
			if(null != id && !"".equals(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class,id);
				if (crmProject != null && crmProject.getProjectEmployees() != null) {
					employeeNames = "";
					for (Employee e : crmProject.getProjectEmployees()) {
						if (e != null) {
							employeeNames += "," + e.getName();
						}
					}
				}
				if(crmProject != null && StrUtils.isNotEmpty(crmProject.getId())){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("crmProject.id," + SearchCondition.EQUAL, crmProject.getId());
					List<BackSectionPlan> backSectionPlans = baseHibernateService.findAllByConditions(BackSectionPlan.class, params);
					if(backSectionPlans != null && backSectionPlans.size() > 0){
						for (BackSectionPlan backSectionPlan : backSectionPlans) {
							if(backSectionPlan != null && backSectionPlan.getAmount() != null && backSectionPlan.getAmount() > 0){
								planAmount += backSectionPlan.getAmount();
							}
						}
					}
					List<BackSectionRecord> backSectionRecords = baseHibernateService.findAllByConditions(BackSectionRecord.class, params);
					if(backSectionRecords != null && backSectionRecords.size() > 0){
						for (BackSectionRecord backSectionRecord : backSectionRecords) {
							if(backSectionRecord != null && backSectionRecord.getAmount() != null && backSectionRecord.getAmount() > 0){
								amount += backSectionRecord.getAmount();
							}
						}
					}
					List<Expenses> expensess = baseHibernateService.findAllByConditions(Expenses.class, params);
					if(expensess != null && expensess.size() > 0){
						for (Expenses expenses : expensess) {
							if(expenses != null && expenses.getAmount() != null && expenses.getAmount() > 0){
								expenseAmount += expenses.getAmount();
							}
						}
					}
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("crmProject.id," + SearchCondition.EQUAL, crmProject.getId());
					p.put("contractType," + SearchCondition.EQUAL, 3);
					List<Contract> contracts = baseHibernateService.findAllByConditions(Contract.class, p);
					if(contracts != null && contracts.size() > 0){
						for (Contract contract : contracts) {
							if(contract != null && contract.getTotalAmount() != null && contract.getTotalAmount() != 0){
								contractAmount += contract.getTotalAmount();
							}
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "crmProjectView";
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		try {
			if(StrUtils.objectIsNotNull(crmProject.getId())){
				crmProject.setUpdateTime(new Date());
			}else{
				crmProject.setCreateTime(new Date());
				crmProject.setUpdateTime(new Date());
				loadCommonData(crmProject);
			}
			
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				List<Employee> employeeList = baseHibernateService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
					}
				}
				crmProject.setProjectEmployees(subEmployees);
			}
			
			String[] attrArray ={"customerAccount","projectStage","projectStatus","personInCharge","projectSalePreviousStage"};
			checkEntityNullValue(crmProject,attrArray);
			
			crmProject = baseHibernateService.merge(crmProject);
			renderText("1:" + crmProject.getId());
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + OPER_FAIL);
		}
	}
	
	/** 项目进度 */
	public String goSaveOrUpdateFeedback(){
		try{
			if(StrUtils.isNotEmpty(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class, id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goSaveOrUpdateFeedback";
	}
	
	public void saveOrUpdateFeedback(){
		try{
			if(StrUtils.isNotEmpty(id) && projectSchedule != null){
				crmProject = baseHibernateService.findEntityById(CrmProject.class, id);
				crmProject.setUpdateTime(new Date());
				crmProject.setProjectSchedule(projectSchedule);
				crmProject = baseHibernateService.merge(crmProject);
				renderText(OPER_SUCCESS);
			}else{
				renderText(OPER_FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText(OPER_FAIL);
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
			//e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/** 获取项目列表 */
	public String goChooseCrmProject() {
		return "goChooseCrmProject";
	}

	/** 选择项目列表 */
	public void goSingleListJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != name && !"".equals(name)) {
				params.put("subject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			params.put("channelDistributor.id," + SearchCondition.IS, null);
			baseHibernateService.findPagerByHqlConditions(pager, CrmProject.class, params);
			renderDataTable(pager,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goChooseEmployees(){
		return "goChooseEmployees";
	}
	/**
	 * 人员列表 TODO:后期查询需要修改
	 */
	public void goEmployeeList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");

			String searchName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			String orgId = getRequestParameter("orgId");
			if(StrUtils.isNotEmpty(orgId)){
				params.put("organizationUnit.id," + SearchCondition.ANYLIKE, orgId);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<CrmProject> crmProjectList;
	public String loadCrmProject(){
		try {
			Map<String,Object> params = getParams();
			if(null != id){
				params.put("customerAccount.id," + SearchCondition.EQUAL, id);
				crmProjectList = baseHibernateService.findAllByConditions(CrmProject.class,params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "loadCrmProject";
	}
	
	// 获取附件列表
	public void goUploaderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id)) {
				params.put("crmProject.id," + SearchCondition.EQUAL, id);
				String searchName = getDecodeRequestParameter("searchName");
				if (StringUtils.isNotEmpty(searchName)) {
					params.put("fileName," + SearchCondition.ANYLIKE, searchName);
				}
				pager = baseHibernateService.findPagerByHqlConditions(pager, Uploader.class, params);
			}
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存附件
	 */
	public void saveOrUpdateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				Uploader uploader = new Uploader();
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
				}
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					crmProject = baseHibernateService.findEntityById(CrmProject.class, id);
					if (crmProject != null) {
						uploader.setCrmProject(crmProject);
					}
				}
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader = baseHibernateService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载附件
	 */
	public String downloadUploader() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				Uploader uploader = baseHibernateService.findEntityById(Uploader.class, this.id);
				String fileName = uploader.getFileName();
				this.setOriFileName(fileName);
				String baseFolder = "c:/img/";
				String downloadFile = baseFolder + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
				if (uploader.getDownloadNum() != null) {
					uploader.setDownloadNum(uploader.getDownloadNum() + 1);
				} else {
					uploader.setDownloadNum(1);
				}
				uploader = baseHibernateService.merge(uploader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadUploader";
	}
	
	// 删除附件
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Uploader uploader = baseHibernateService.findEntityById(Uploader.class, id);
				if (null != uploader) {
					String fileName = uploader.getFileName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					baseHibernateService.deleteByEntity(uploader);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	// 项目沟通
	private List<Employee> empList;
	private Set<EvaluationReview> evaluationReviewList;
	private Integer evaluationReviewNum = 0;
	private Integer employeeNum = 0;
	@SuppressWarnings("unchecked")
	public String goCrmProjectDiscuss() {
		try {
			Employee emp = getEmployee();
			Pager pager = getPager();
			if (emp != null) {
				Map<String,Object> params = getParams();
				StringBuilder hql = new StringBuilder();
				String ename = "hentity";
				hql.append("select ").append(ename);
				hql.append(" from CrmProject ").append(ename);
				hql.append(" left outer join fetch ").append(ename).append(".projectEmployees e ");
				hql.append(" where 1=1 and ").append(ename).append(".channelDistributor.id is null ");;
				params.put("employeeId", emp.getId());
				hql.append(" and ").append("e.id = :employeeId ");
				pager = customerAccountService.findPagerByHql(pager, ename, hql.toString(), params);
				if (pager.getResultList() != null && pager.getResultList().size() > 0) {
					crmProjectList = pager.getResultList();
					crmProject = (CrmProject) pager.getResultList().get(0);
					if (crmProject != null) {
						if (crmProject.getSubEvaluationReviews() != null && crmProject.getSubEvaluationReviews().size() > 0) {
							evaluationReviewNum = crmProject.getSubEvaluationReviews().size();
							evaluationReviewList = crmProject.getSubEvaluationReviews();
						}
						if (crmProject.getProjectEmployees() != null && crmProject.getProjectEmployees().size() > 0) {
							empList = new ArrayList<Employee>();
							empList.addAll(crmProject.getProjectEmployees());
							employeeNum = crmProject.getProjectEmployees().size();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCrmProjectDiscuss";
	}
	
	private EvaluationReview evaluationReview;
	public String saveOrUpdateEvaluationReview() {
		try {
			evaluationReview.setCrmProject(crmProject);
			Employee employee = getEmployee();
			if (employee != null) {
				evaluationReview.setCreator(employee.getName());
				evaluationReview.setEmployee(employee);
			}
			evaluationReview.setEvaluationTime(new Date());
			evaluationReview.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(evaluationReview);
			evaluationReview = vixntBaseService.merge(evaluationReview);
			crmProject = baseHibernateService.findEntityById(CrmProject.class, crmProject.getId());
			if (crmProject != null) {
				if (crmProject.getSubEvaluationReviews() != null && crmProject.getSubEvaluationReviews().size() > 0) {
					evaluationReviewNum = crmProject.getSubEvaluationReviews().size();
					evaluationReviewList = crmProject.getSubEvaluationReviews();
				}
				if (crmProject.getProjectEmployees() != null && crmProject.getProjectEmployees().size() > 0) {
					empList = new ArrayList<Employee>();
					empList.addAll(crmProject.getProjectEmployees());
					employeeNum = crmProject.getProjectEmployees().size();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowEvaluationReview";
	}
	
	public String loadCrmProjectDiscuss(){
		try {
			if(StrUtils.isNotEmpty(id)){
				crmProject = baseHibernateService.findEntityById(CrmProject.class, id);
				if (crmProject != null) {
					if (crmProject.getSubEvaluationReviews() != null && crmProject.getSubEvaluationReviews().size() > 0) {
						evaluationReviewNum = crmProject.getSubEvaluationReviews().size();
						evaluationReviewList = crmProject.getSubEvaluationReviews();
					}
					if (crmProject.getProjectEmployees() != null && crmProject.getProjectEmployees().size() > 0) {
						empList = new ArrayList<Employee>();
						empList.addAll(crmProject.getProjectEmployees());
						employeeNum = crmProject.getProjectEmployees().size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowEvaluationReview";
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String getChooseType() {
		return chooseType;
	}

	@Override
	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public Double getPlanAmount() {
		return planAmount;
	}
	
	public String getPlanAmountStr() {
		if (null != planAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(planAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}

	public Double getAmount() {
		return amount;
	}
	
	public String getAmountStr() {
		if (null != amount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(amount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getContractAmount() {
		return contractAmount;
	}
	
	public String getContractAmountStr() {
		if (null != contractAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(contractAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	public Double getExpenseAmount() {
		return expenseAmount;
	}
	
	public String getExpenseAmountStr() {
		if (null != expenseAmount) {
			DecimalFormat myFormatter = new DecimalFormat("0.00");
			return myFormatter.format(expenseAmount); // 注意,这句整体作为String
		} else {
			return "0.00";
		}
	}

	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public Set<EvaluationReview> getEvaluationReviewList() {
		return evaluationReviewList;
	}

	public void setEvaluationReviewList(Set<EvaluationReview> evaluationReviewList) {
		this.evaluationReviewList = evaluationReviewList;
	}

	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

	public Integer getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(Integer employeeNum) {
		this.employeeNum = employeeNum;
	}

	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public Integer getProjectSchedule() {
		return projectSchedule;
	}

	public void setProjectSchedule(Integer projectSchedule) {
		this.projectSchedule = projectSchedule;
	}
}
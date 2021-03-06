package com.vix.nvix.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.ProjectRequirement;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.oa.task.taskDefinition.entity.Uploader;

@Controller
@Scope("prototype")
public class NvixProjectRequirementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private ProjectRequirement projectRequirement;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			
			String crmProject = getRequestParameter("crmProject");
			if(StrUtils.objectIsNotNull(crmProject)){
				crmProject = decode(crmProject, "UTF-8");
				params.put("crmProject.subject,"+SearchCondition.ANYLIKE, crmProject);
			}
			
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			baseHibernateService.findPagerByHqlConditions(pager, ProjectRequirement.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<SaleChance> saleChanceList;
	public String goSaveOrUpdate() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				projectRequirement = baseHibernateService.findEntityById(ProjectRequirement.class, id);
				if(null != projectRequirement.getCustomerAccount() && StrUtils.isNotEmpty(projectRequirement.getCustomerAccount().getId())){
					saleChanceList = baseHibernateService.findAllByEntityClassAndAttribute(SaleChance.class, "customerAccount.id", projectRequirement.getCustomerAccount().getId());
				}
			} else {
				projectRequirement = new ProjectRequirement();
				projectRequirement.setRecordDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					projectRequirement.setProvider(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		try {
			if (StrUtils.objectIsNotNull(projectRequirement.getId())) {
				projectRequirement.setUpdateTime(new Date());
			} else {
				projectRequirement.setCreateTime(new Date());
				loadCommonData(projectRequirement);
			}
			if(null == projectRequirement.getSaleChance() || StrUtils.isEmpty(projectRequirement.getSaleChance().getId())){
				projectRequirement.setSaleChance(null);
			}
			String[] attrArray = { "customerAccount", "provider", "crmProject","saleChance" };
			checkEntityNullValue(projectRequirement, attrArray);

			projectRequirement = baseHibernateService.merge(projectRequirement);
			// 增加行动历史和客户更新
			if(projectRequirement.getCustomerAccount() != null && StrUtils.isNotEmpty(projectRequirement.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, projectRequirement.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("详细需求");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(projectRequirement.getCrmProject());
				actionHistory.setDescription("详细需求编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			renderText("1:" + projectRequirement.getId());
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:" + OPER_FAIL);
		}
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				ProjectRequirement pb = baseHibernateService.findEntityById(ProjectRequirement.class, id);
				if (null != pb) {
					List<Uploader> uploaders = baseHibernateService.findAllByEntityClassAndAttribute(Uploader.class, "projectRequirement.id", pb.getId());
					if(uploaders != null && uploaders.size() > 0){
						renderText("包含附件不能删除!");
					}else{
						baseHibernateService.deleteByEntity(pb);
						renderText(DELETE_SUCCESS);
					}
				}else{
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	// 获取附件列表
	public void goUploaderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(id)) {
				params.put("projectRequirement.id," + SearchCondition.EQUAL, id);
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
					projectRequirement = baseHibernateService.findEntityById(ProjectRequirement.class, id);
					if (projectRequirement != null) {
						uploader.setProjectRequirement(projectRequirement);
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
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public ProjectRequirement getProjectRequirement() {
		return projectRequirement;
	}

	public void setProjectRequirement(ProjectRequirement projectRequirement) {
		this.projectRequirement = projectRequirement;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<SaleChance> getSaleChanceList() {
		return saleChanceList;
	}

	public void setSaleChanceList(List<SaleChance> saleChanceList) {
		this.saleChanceList = saleChanceList;
	}

}
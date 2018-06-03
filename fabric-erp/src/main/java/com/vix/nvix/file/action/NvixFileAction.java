package com.vix.nvix.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.personaloffice.entity.Communication;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.UploaderType;
import com.vix.oa.task.taskDefinition.entity.UploaderTypeKeyWord;

/**
 * 
 * com.vix.nvix.file.action.NvixFileAction
 *
 * @author bjitzhang
 *
 * @date 2015年12月18日
 */
@Controller
@Scope("prototype")
public class NvixFileAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private List<Uploader> uploaderList;
	private List<UploaderType> uploaderTypeList;
	private Uploader uploader;
	private Communication communication;
	private String source;
	private UploaderType uploaderType;
	private UploaderTypeKeyWord uploaderTypeKeyWord;
	private String id;
	private String ids;
	private String employeeIds;
	private String employeeNames;
	private String tag;
	private String projectId;

	private List<Project> projectList;
	private List<Employee> employeeList;

	@Override
	public String goList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			uploaderTypeList = vixntBaseService.findAllByConditions(UploaderType.class, p);
			Employee e = getEmployee();
			if (e != null) {
				Map<String, Object> projectparams = new HashMap<String, Object>();
				projectparams.put("isTemp", "1");
				projectparams.put("isDeleted", "0");
				projectparams.put("employeeId", e.getId());
				projectList = vixntBaseService.findProjectByHql(projectparams);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	//
	public String goFile() {
		return "goFile";
	}

	public void goFileList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Employee employee = getEmployee();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (employee != null) {
				params.put("employee.id," + SearchCondition.EQUAL, employee.getId());
			}
			String logContent = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(logContent)) {
				params.put("logContent," + SearchCondition.EQUAL, logContent);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Communication.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void goSingleDocList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			if (pageNo != null) {
				pager.setPageNo(pageNo);
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findUploaderPager(pager, params);
				if (pager.getResultList().size() < 5) {
					int listSize = pager.getResultList().size();
					for (int i = 0; i < 5 - listSize; i++) {
						pager.getResultList().add(new Uploader());
					}
				}
			}
			String[] excludes = { " " };
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goUploaderPager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee e = getEmployee();
			if (pageNo != null) {
				pager.setPageNo(pageNo);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("uploaderTypeId", id);
			}
			/*
			 * if (StringUtils.isNotEmpty(projectId)) { params.put("projectId",
			 * projectId); }
			 */
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findUploaderPager(pager, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUploaderPager";
	}

	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(communication.getId())) {
				isSave = false;
			}
			/** 拿到当前用户的姓名，保存 */
			Employee employee = getEmployee();
			if (employee != null) {
				communication.setEmployee(employee);
			}
			// 获取选定成员
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
					}
				}
				subEmployees.add(employee);
				communication.setSubEmployees(subEmployees);
			}
			communication.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(communication);
			communication = vixntBaseService.merge(communication);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}

	public void goUploaderList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("fileName," + SearchCondition.ANYLIKE, searchName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("communication.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
			}
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goProjectUploaderPager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (pageNo != null) {
				pager.setPageNo(pageNo);
			}
			if (StringUtils.isNotEmpty(projectId)) {
				params.put("project.id," + SearchCondition.EQUAL, projectId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
			} else {
				Employee e = getEmployee();
				if (e != null) {
					Map<String, Object> projectparams = new HashMap<String, Object>();
					projectparams.put("isTemp", "1");
					projectparams.put("isDeleted", "0");
					projectparams.put("employeeId", e.getId());
					projectList = vixntBaseService.findProjectByHql(projectparams);
					String projectIds = "";
					if (projectList != null && projectList.size() > 0) {
						for (Project project : projectList) {
							if (project != null) {
								projectIds += "," + project.getId();
							}
						}
					}
					if (StringUtils.isNotEmpty(projectIds)) {
						params.put("project.id," + SearchCondition.IN, projectIds);
						pager = vixntBaseService.findPagerByHqlConditions(pager, Uploader.class, params);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUploaderPager";
	}

	public String goUploaderTypeList() {
		return "goUploaderTypeList";
	}

	public void goUploaderTypePager() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (pageNo != null) {
				pager.setPageNo(pageNo);
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, UploaderType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传附件
	 * 
	 * @return
	 */
	public String goAddUploader() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			uploaderTypeList = vixntBaseService.findAllByConditions(UploaderType.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddUploader";
	}

	public String goEmployeeChoose() {
		return "goEmployeeChoose";
	}

	public void goEmployeeList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			String[] excludes = {"organizationUnit"};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				communication = vixntBaseService.findEntityById(Communication.class, id);
				if (communication != null && communication.getSubEmployees() != null) {
					employeeIds = "";
					employeeNames = "";
					for (Employee e : communication.getSubEmployees()) {
						if (e != null) {
							employeeIds += "," + e.getId();
							employeeNames += "," + e.getName();
						}
					}
				}
			} else {
				communication = new Communication();
				communication.setIsTemp("1");
				communication = vixntBaseService.merge(communication);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goSaveOrUpdate";
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
				// 获取选定成员
				Map<String, Object> p = getParams();
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (employeeList != null && employeeList.size() > 0) {
					for (Employee e : employeeList) {
						subEmployees.add(e);
					}
				}
				subEmployees.add(employee);
				uploader.setSubEmployees(subEmployees);
				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					communication = vixntBaseService.findEntityById(Communication.class, id);
					if (communication != null) {
						uploader.setCommunication(communication);
					}
				}
				uploader.setEmployee(employee);
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader = vixntBaseService.merge(uploader);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	public String goSaveOrUpdateUploaderType() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				uploaderType = vixntBaseService.findEntityById(UploaderType.class, id);
			} else {
				uploaderType = new UploaderType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateUploaderType";
	}

	/**
	 * 保存分类
	 * 
	 * @return
	 */
	public void saveOrUpdateUploaderType() {
		try {
			uploaderType.setCreateTime(new Date());
			uploaderType = vixntBaseService.merge(uploaderType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkDeclassified() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						Uploader uploader = vixntBaseService.findEntityById(Uploader.class, idStr);
						Employee employee = getEmployee();
						// if (uploader != null && employee != null &&
						// employee.getDeclassified() != null &&
						// employee.getDeclassified() <=
						// uploader.getDeclassified() &&
						// employee.getName().equals(uploader.getCreator())) {
						if (uploader != null && employee != null && employee.getName().equals(uploader.getCreator())) {
							renderText("1");
						} else {
							renderText("2");
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateUploaderTypeKeyWord() {
		return "goSaveOrUpdateUploaderTypeKeyWord";
	}

	/**
	 * 保存关键字
	 * 
	 * @return
	 */
	public void saveOrUpdateUploaderTypeKeyWord() {
		try {
			uploaderTypeKeyWord.setCreateTime(new Date());
			uploaderTypeKeyWord = vixntBaseService.merge(uploaderTypeKeyWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				uploader.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					uploader.setCreator(employee.getName());
					uploader.setEmployee(employee);
				}
				if (uploader.getUploaderType() == null || StringUtils.isEmpty(uploader.getUploaderType().getId())) {
					uploader.setUploaderType(null);
				}
				if (uploader.getUploaderTypeKeyWord() == null || StringUtils.isEmpty(uploader.getUploaderTypeKeyWord().getId())) {
					uploader.setUploaderTypeKeyWord(uploaderTypeKeyWord);
				}

				// 获取选定成员
				if (StringUtils.isNotEmpty(employeeIds)) {
					Map<String, Object> p = getParams();
					p.put("id," + SearchCondition.IN, employeeIds);
					employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
					Set<Employee> subEmployees = new HashSet<Employee>();
					if (employeeList != null && employeeList.size() > 0) {
						for (Employee e : employeeList) {
							subEmployees.add(e);
						}
					}
					subEmployees.add(employee);
					uploader.setSubEmployees(subEmployees);
				}

				uploader.setFilePath("/img/wechat/" + saveDocPathAndName[1].toString());
				uploader.setFilesize(saveDocPathAndName[2].toString());
				uploader.setFileType(saveDocPathAndName[3].toString());
				uploader.setCreateTime(new Date());
				initEntityBaseController.initEntityBaseAttribute(uploader);
				uploader = vixntBaseService.merge(uploader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUploadFileSavePic() {
		String baseFolder = "c:/img";
		String newFilePath = "";
		newFilePath = baseFolder;
		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();
		return newFilePath;
	}

	public void deleteUploaderByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				for (String idStr : ids.split(",")) {
					if (StringUtils.isNotEmpty(idStr)) {
						uploader = vixntBaseService.findEntityById(Uploader.class, idStr);
						if (null != uploader) {
							String fileName = uploader.getFileName();
							String baseFolder = "c:/img/";
							String downloadFile = baseFolder + fileName;
							File f = new File(downloadFile); // 输入要删除的文件位置
							if (f.exists()) {
								f.delete();
							}
							vixntBaseService.deleteByEntity(uploader);
							renderText(DELETE_SUCCESS);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String downloadUploader() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				Uploader uploader = vixntBaseService.findEntityById(Uploader.class, this.id);
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
				uploader = vixntBaseService.merge(uploader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "downloadUploader";
	}

	public void deleteUploaderTypeById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				uploaderType = vixntBaseService.findEntityById(UploaderType.class, id);
				if (null != uploaderType) {
					vixntBaseService.deleteByEntity(uploaderType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void goOtherCommunication() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp", "1");
			// 标题
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title", "%" + title + "%");
			}
			// 创建人
			String creator = getDecodeRequestParameter("creator");
			if (StringUtils.isNotEmpty(creator)) {
				params.put("creator", "%" + creator + "%");
			}
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findCommunicationPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goCommunication() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				communication = vixntBaseService.findEntityById(Communication.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCommunication";
	}

	public String goCommunicationAttachment() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				communication = vixntBaseService.findEntityById(Communication.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("communication.id," + SearchCondition.EQUAL, id);
				uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCommunicationAttachment";
	}

	public void deleteCommunicationById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				communication = vixntBaseService.findEntityById(Communication.class, id);

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("communication.id," + SearchCondition.EQUAL, id);
				List<Uploader> uploaderList = vixntBaseService.findAllByConditions(Uploader.class, params);
				for (Uploader uploader : uploaderList) {
					if (null != uploader) {
						String fileName = uploader.getFileName();
						String baseFolder = "c:/img/";
						String downloadFile = baseFolder + fileName;
						File f = new File(downloadFile); // 输入要删除的文件位置
						if (f.exists()) {
							f.delete();
						}
						vixntBaseService.deleteByEntity(uploader);
					}
				}
				if (null != communication) {
					vixntBaseService.deleteByEntity(communication);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the uploaderTypeList
	 */
	public List<UploaderType> getUploaderTypeList() {
		return uploaderTypeList;
	}

	/**
	 * @param uploaderTypeList
	 *            the uploaderTypeList to set
	 */
	public void setUploaderTypeList(List<UploaderType> uploaderTypeList) {
		this.uploaderTypeList = uploaderTypeList;
	}

	/**
	 * @return the uploaderTypeKeyWord
	 */
	public UploaderTypeKeyWord getUploaderTypeKeyWord() {
		return uploaderTypeKeyWord;
	}

	/**
	 * @param uploaderTypeKeyWord
	 *            the uploaderTypeKeyWord to set
	 */
	public void setUploaderTypeKeyWord(UploaderTypeKeyWord uploaderTypeKeyWord) {
		this.uploaderTypeKeyWord = uploaderTypeKeyWord;
	}

	/**
	 * @return the uploader
	 */
	public Uploader getUploader() {
		return uploader;
	}

	/**
	 * @param uploader
	 *            the uploader to set
	 */
	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	/**
	 * @return the uploaderType
	 */
	public UploaderType getUploaderType() {
		return uploaderType;
	}

	/**
	 * @param uploaderType
	 *            the uploaderType to set
	 */
	public void setUploaderType(UploaderType uploaderType) {
		this.uploaderType = uploaderType;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	/**
	 * @return the uploaderList
	 */
	public List<Uploader> getUploaderList() {
		return uploaderList;
	}

	/**
	 * @param uploaderList
	 *            the uploaderList to set
	 */
	public void setUploaderList(List<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getEmployeeNames() {
		return employeeNames;
	}

	public void setEmployeeNames(String employeeNames) {
		this.employeeNames = employeeNames;
	}

	public Communication getCommunication() {
		return communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}

}
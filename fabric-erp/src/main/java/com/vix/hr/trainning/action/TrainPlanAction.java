package com.vix.hr.trainning.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.trainning.controller.TrainingPlanningController;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.hr.trainning.service.ITrainingPlanningService;

/**
 * @Description: 培训计划
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TrainPlanAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(TrainingPlanning.class);
	/** 注入service */
	@Autowired
	private TrainingPlanningController trainingPlanningController;
	/*
	 * @Autowired private IUseCache useCache;
	 */
	private List<TrainingPlanning> trainingPlanningList;
	private TrainingPlanning trainingPlanning;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingPlanningService iTrainingPlanningService;
	private TrainingPlanningDetail detail;
	private String id;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private String parentId;
	private String pageNo;

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String goList() {
		try {
			trainingPlanningList = trainingPlanningController.findTrainingPlanningIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String planStatus = getRequestParameter("planStatus");
			if (null != planStatus && !"".equals(planStatus)) {
				params.put("planStatus," + SearchCondition.EQUAL, Integer.parseInt(planStatus));
			}
			/* 按最近使用 */
			String proposedTime = getRequestParameter("proposedTime");
			if (proposedTime != null && !"".equals(proposedTime)) {
				params.put("proposedTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(proposedTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("proposedTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingPlanningController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 计划主题 */
			String applicationName = getRequestParameter("applicationName");
			if (null != applicationName && !"".equals(applicationName)) {
				applicationName = URLDecoder.decode(applicationName, "utf-8");
			}
			/* 负责部门与负责人 */
			String orgUnitAndLeadingOfficial = getRequestParameter("orgUnitAndLeadingOfficial");
			if (null != orgUnitAndLeadingOfficial && !"".equals(orgUnitAndLeadingOfficial)) {
				orgUnitAndLeadingOfficial = URLDecoder.decode(orgUnitAndLeadingOfficial, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
				pager = trainingPlanningController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != applicationName && !"".equals(applicationName)) {
					params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
				}
				if (null != orgUnitAndLeadingOfficial && !"".equals(orgUnitAndLeadingOfficial)) {
					params.put("orgUnitAndLeadingOfficial," + SearchCondition.ANYLIKE, orgUnitAndLeadingOfficial);
				}
				pager = trainingPlanningController.goSingleList(params, getPager());
			}
			logger.info("获取活动计划搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goChooseEmployee() {
		return "goChooseEmployee";
	}

	/** 获取列表数据，跳转 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = trainingPlanningController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingPlanning = trainingPlanningController.doListEntityById(id);
			} else {
				trainingPlanning = new TrainingPlanning();
				trainingPlanning.setIsTemp("1");
				trainingPlanning = trainingPlanningController.doSaveTrainingPlanning(trainingPlanning);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingPlanning.getId()) && !"0".equals(trainingPlanning.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingPlanning.getApplicationName();
			String py = ChnToPinYin.getPYString(title);
			trainingPlanning.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingPlanning);
			trainingPlanning = trainingPlanningController.doSaveTrainingPlanning(trainingPlanning);
			trainingPlanning.setIsTemp("");

			this.trainingPlanning.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingPlanning.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingPlanning.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingPlanning);
			logger.info("对活动计划进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			TrainingPlanning trainingPlanning = trainingPlanningController.doListEntityById(id);
			if (null != trainingPlanning) {
				trainingPlanningController.doDeleteByEntity(trainingPlanning);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除活动计划");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转到培训计划明细页面 */
	public String goAddTrainingPlanningDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				detail = trainingPlanningController.doListTrainingPlanningDetailById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddTrainingPlanningDetail";
	}

	public String addHrAttachments() {
		return "addHrAttachments";
	}

	/** 获取明细json数据 */
	public void getTrainingPlanningDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingPlanning tp = trainingPlanningController.findEntityById(id);
				if (tp != null) {
					json = convertListToJson(new ArrayList<TrainingPlanningDetail>(tp.getTrainingPlanningDetails()), tp.getTrainingPlanningDetails().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 培训计划明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingPlanningDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				trainingPlanning = trainingPlanningController.doListEntityById(id);
				detail.setTrainingPlanning(trainingPlanning);
				trainingPlanningController.doSaveTrainingPlanningDetail(detail);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iTrainingPlanningService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iTrainingPlanningService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除tab明细操作 */
	public String deleteTrainingPlanningDetailById() {
		try {
			TrainingPlanningDetail trainingPlanningDetail = trainingPlanningController.doListTrainingPlanningDetailById(id);
			if (null != trainingPlanningDetail) {
				trainingPlanningController.deleteTrainingPlanningDetailEntity(trainingPlanningDetail);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取附件json数据 */
	public void getHrAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				TrainingPlanning tp = trainingPlanningController.findEntityById(id);
				if (tp != null) {
					json = convertListToJson(new ArrayList<HrAttachments>(tp.getAttachments()), tp.getAttachments().size(), "trainingPlanning");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 上传附件 */
	public void uploadHrAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				TrainingPlanning trainingPlanning = trainingPlanningController.findEntityById(idStr);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					/*
					 * String baseDir =
					 * getServletContext().getRealPath(separator +
					 * "richContent");
					 */
					String baseDir = "H:/upload";
					BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length - 1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName + "_" + timeTemp + "." + extFileName;
					newFilePath = baseDir + separator + newFileName;
					BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024 * 100];
					int len = -1;
					while ((len = bufIn.read(buf)) != -1) {
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					HrAttachments atts = new HrAttachments();
					atts.setName(newFileName);
					atts.setTrainingPlanning(trainingPlanning);
					trainingPlanningController.mergeAttachments(atts);
					renderJson("文件上传成功!");
				}
			} else {
				renderJson("订单id获取失败!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}

	}

	/** 删除附件 */
	public void deleteHrAttachments() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				HrAttachments atts = trainingPlanningController.findHrAttachmentsEntityById(afId);
				if (null != atts) {
					/** 上传目录 */
					String separator = System.getProperty("file.separator");
					String baseDir = getServletContext().getRealPath(separator + "richContent");
					baseDir += separator;
					baseDir += atts.getName();
					File file = new File(baseDir);
					if (file.exists()) {
						file.delete();
					}
					trainingPlanningController.deleteHrAttachmentsEntity(atts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TrainingPlanning> getTrainingPlanningList() {
		return trainingPlanningList;
	}

	public void setTrainingPlanningList(List<TrainingPlanning> trainingPlanningList) {
		this.trainingPlanningList = trainingPlanningList;
	}

	public TrainingPlanning getTrainingPlanning() {
		return trainingPlanning;
	}

	public void setTrainingPlanning(TrainingPlanning trainingPlanning) {
		this.trainingPlanning = trainingPlanning;
	}

	public ITrainingPlanningService getiTrainingPlanningService() {
		return iTrainingPlanningService;
	}

	public void setiTrainingPlanningService(ITrainingPlanningService iTrainingPlanningService) {
		this.iTrainingPlanningService = iTrainingPlanningService;
	}

	public TrainingPlanningDetail getDetail() {
		return detail;
	}

	public void setDetail(TrainingPlanningDetail detail) {
		this.detail = detail;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
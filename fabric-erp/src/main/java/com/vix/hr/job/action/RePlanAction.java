package com.vix.hr.job.action;

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
import com.vix.hr.job.controler.RePlanController;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.job.entity.HrRecruitmentPlanDetails;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.job.service.IRePlanService;

/**
 * @Description: 招聘计划
 * @author 李大鹏
 * @date 2013-07-29
 */
@Controller
@Scope("prototype")
public class RePlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitplan.class);
	/**
	 * 注入service
	 */
	@Autowired
	private RePlanController rePlanController;
	private List<HrRecruitplan> hrrecruiptplanList;
	private HrRecruitplan hrRecruitplan;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IRePlanService iRePlanService;
	private HrRecruitmentPlanDetails details;
	private String id;
	private String pageNo;
	private List<HrRecruitplan> hrRecruitplans;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

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

	public List<HrRecruitplan> getHrrecruiptplanList() {
		return hrrecruiptplanList;
	}

	public void setHrrecruiptplanList(List<HrRecruitplan> hrrecruiptplanList) {
		this.hrrecruiptplanList = hrrecruiptplanList;
	}

	public HrRecruitmentPlanDetails getDetails() {
		return details;
	}

	public void setDetails(HrRecruitmentPlanDetails details) {
		this.details = details;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String goList() {
		try {
			hrrecruiptplanList = rePlanController.findSalesOrderIndex();
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

	/**
	 * 获取招聘计划列表页数据
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String planStatus = getRequestParameter("planStatus");
			if (null != planStatus && !"".equals(planStatus)) {
				params.put("planStatus," + SearchCondition.ANYLIKE, planStatus);
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
			Pager pager = rePlanController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取招聘计划列表页搜索功能数据
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 计划主题
			String programName = getRequestParameter("programName");
			if (null != programName && !"".equals(programName)) {
				programName = URLDecoder.decode(programName, "utf-8");
			}
			// 规划性质
			String planningNature = getRequestParameter("planningNature");
			if (null != planningNature && !"".equals(planningNature)) {
				planningNature = URLDecoder.decode(planningNature, "utf-8");
			}
			// 计划提出部门
			String org = getRequestParameter("org");
			if (null != org && !"".equals(org)) {
				org = URLDecoder.decode(org, "utf-8");
			}
			// 组织部门
			String responsibleForOrgUnit = getRequestParameter("responsibleForOrgUnit");
			if (null != responsibleForOrgUnit && !"".equals(responsibleForOrgUnit)) {
				responsibleForOrgUnit = URLDecoder.decode(responsibleForOrgUnit, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("programName," + SearchCondition.ANYLIKE, programName);
				pager = rePlanController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != programName && !"".equals(programName)) {
					params.put("programName," + SearchCondition.ANYLIKE, programName);
				}
				if (null != planningNature && !"".equals(planningNature)) {
					params.put("planningNature," + SearchCondition.ANYLIKE, planningNature);
				}
				if (null != org && !"".equals(org)) {
					params.put("org," + SearchCondition.ANYLIKE, org);
				}
				if (null != responsibleForOrgUnit && !"".equals(responsibleForOrgUnit)) {
					params.put("responsibleForOrgUnit," + SearchCondition.ANYLIKE, responsibleForOrgUnit);
				}
				pager = rePlanController.goSingleList(params, getPager());
			}
			logger.info("获取招聘计划搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 
	 * 跳转至招聘计划主表修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitplan = rePlanController.doListEntityById(id);
			} else {
				hrRecruitplan = new HrRecruitplan();
				hrRecruitplan.setIsTemp("1");
				hrRecruitplan = rePlanController.doSaveRePlan(hrRecruitplan);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * 跳转到招聘计划明细页面
	 * 
	 */
	public String goAddHrRecruitmentPlanDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				details = rePlanController.doListHrRecruitmentPlanDetailsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddHrRecruitmentPlanDetails";
	}

	/**
	 * 
	 * 处理招聘计划主表的修改，保存功能
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitplan.getId()) && !"0".equals(hrRecruitplan.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrRecruitplan.getProgramName();
			String py = ChnToPinYin.getPYString(title);
			hrRecruitplan.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrRecruitplan);
			hrRecruitplan = rePlanController.doSaveRePlan(hrRecruitplan);
			hrRecruitplan.setIsTemp("");

			this.hrRecruitplan.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrRecruitplan.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrRecruitplan.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrRecruitplan);
			logger.info("对招聘计划进行了修改！");
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

	/**
	 * 根据招聘计划Id删除招聘计划主表数据
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			HrRecruitplan hrRecruitplan = rePlanController.doListEntityById(id);
			if (null != hrRecruitplan) {
				rePlanController.doDeleteByEntity(hrRecruitplan);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除招聘计划信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HrRecruitplan getHrRecruitplan() {
		return hrRecruitplan;
	}

	public void setHrRecruitplan(HrRecruitplan hrRecruitplan) {
		this.hrRecruitplan = hrRecruitplan;
	}

	public List<HrRecruitplan> getHrRecruitplans() {
		return hrRecruitplans;
	}

	public void setHrRecruitplans(List<HrRecruitplan> hrRecruitplans) {
		this.hrRecruitplans = hrRecruitplans;
	}

	/**
	 * 获取招聘计划明细json数据
	 */
	public void getHrRecruitmentPlanDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrRecruitplan hr = rePlanController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrRecruitmentPlanDetails>(hr.getHrRecruitmentPlanDetails()), hr.getHrRecruitmentPlanDetails().size(), "hrRecruitplan");
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
	 * 保存，修改招聘计划明细
	 * 
	 * @return
	 */
	public String saveOrUpdateHrRecruitmentPlanDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitplan = rePlanController.doListEntityById(id);
				details.setHrRecruitplan(hrRecruitplan);
				rePlanController.doSaveHrRecruitmentPlanDetails(details);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/**
	 * 根据json跳转选择部门弹出窗口
	 */
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iRePlanService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iRePlanService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization sc = listCategory.get(i);
				if (sc.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}

	/**
	 * 按Id删除招聘计划明细表数据
	 * 
	 * @return
	 */
	public String deleteHrRecruitmentPlanDetailsById() {
		try {
			HrRecruitmentPlanDetails hrRecruitmentPlanDetails = rePlanController.doListHrRecruitmentPlanDetailsById(id);
			if (null != hrRecruitmentPlanDetails) {
				rePlanController.deleteHrRecruitmentPlanDetailsEntity(hrRecruitmentPlanDetails);
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

	/**
	 * 获取附件json数据
	 */
	public void getHrAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrRecruitplan hrRecruitplan = rePlanController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrAttachments>(hrRecruitplan.getAttachments()), hrRecruitplan.getAttachments().size(), "hrRecruitplan");
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

	public String addHrAttachments() {
		return "addHrAttachments";
	}

	/**
	 * 上传附件
	 * 
	 * TODO: 附件上传处理需要完善
	 */
	public void uploadHrAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				HrRecruitplan hrRecruitplan = rePlanController.findEntityById(idStr);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					/** 上传目录 */
					String baseDir = getServletContext().getRealPath(separator + "richContent");
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

					atts.setHrRecruitplan(hrRecruitplan);
					rePlanController.mergeAttachments(atts);
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

	/**
	 * 删除附件
	 */
	public void deleteHrAttachments() {
		try {
			String afId = getRequestParameter("afId");
			if (StringUtils.isNotEmpty(afId)) {
				HrAttachments atts = rePlanController.findHrAttachmentsEntityById(afId);
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
					rePlanController.deleteHrAttachmentsEntity(atts);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理下载附件方法
	 * 
	 * @return
	 */
	public String downloadEqDocument() {
		if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
			try {
				HrAttachments doc = this.iRePlanService.findEntityById(HrAttachments.class, this.id);
				String fileName = doc.getName();
				String separator = System.getProperty("file.separator");
				/** 上传目录 */
				String filePath = getServletContext().getRealPath(separator + "richContent");
				String titleName = doc.getTitleName();
				int intidx = fileName.lastIndexOf(".");
				if (intidx != -1) {
					titleName = titleName + fileName.substring(intidx);
				}

				// 设置下载文件名，必须执行，title为不包含路径的文件名称
				this.setOriFileName(titleName);

				String downloadFile = filePath + separator + fileName;
				// 设置下载文件读取位置，必须设置，downloadFile是完整的文件路径和文件名
				this.setInputStream(new FileInputStream(downloadFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadEqDocument ";
	}

	public IRePlanService getiRePlanService() {
		return iRePlanService;
	}

	public void setiRePlanService(IRePlanService iRePlanService) {
		this.iRePlanService = iRePlanService;
	}
}

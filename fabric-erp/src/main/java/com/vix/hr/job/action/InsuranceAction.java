package com.vix.hr.job.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.controler.RePlanController;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.job.entity.HrRecruitmentPlanDetails;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.job.service.IRePlanService;

@Controller
@Scope("prototype")
public class InsuranceAction extends BaseAction {

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
	private IRePlanService iRePlanService;
	private HrRecruitmentPlanDetails details;
	private String id;
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

	@Override
	public String goList() {
		try {
			hrrecruiptplanList = rePlanController.findSalesOrderIndex();
		} catch (Exception e) {
			// TODO: handle exception
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
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按状态查询
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
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
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 计划名称
			String pName = getRequestParameter("pName");
			// 职位名称
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("programName," + SearchCondition.ANYLIKE, searchContent);
				params.put("org," + SearchCondition.ANYLIKE, searchContent);
				params.put("responsibleForOrgUnit," + SearchCondition.ANYLIKE, searchContent);
				params.put("schemer," + SearchCondition.ANYLIKE, searchContent);
				params.put("leadingOfficial," + SearchCondition.ANYLIKE, searchContent);
				pager = rePlanController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("schemer," + SearchCondition.ANYLIKE, contacts);
				}
				if (null != pName && !"".equals(pName)) {
					params.put("programName," + SearchCondition.ANYLIKE, pName);
				}
				params.remove("name");
				pager = rePlanController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 获取列表数据，跳转
	 * 
	 * @return
	 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = rePlanController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
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
			if (null != hrRecruitplan.getId()) {
				isSave = false;
			}
			hrRecruitplan = rePlanController.doSaveRePlan(hrRecruitplan);
			logger.info("对订单进行了修改！");
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
			logger.info("删除订单信息");
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
	 * 计算最近使用日期
	 * 
	 * @param up
	 * @return
	 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
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
			List<HrCategory> listCategory = new ArrayList<HrCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iRePlanService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iRePlanService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				HrCategory cc = listCategory.get(i);
				if (cc.getSubCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
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

	public IRePlanService getiRePlanService() {
		return iRePlanService;
	}

	public void setiRePlanService(IRePlanService iRePlanService) {
		this.iRePlanService = iRePlanService;
	}
}

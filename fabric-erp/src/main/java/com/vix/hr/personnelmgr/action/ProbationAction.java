package com.vix.hr.personnelmgr.action;

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
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.personnelmgr.controller.BecomeRegularController;
import com.vix.hr.personnelmgr.entity.HrApprovalOpinion;
import com.vix.hr.personnelmgr.entity.HrBecomeRegular;
import com.vix.hr.personnelmgr.service.IBecomeRegularService;

/**
 * @Description: 人事事务-转正
 * @author 李大鹏
 * @date 2013-09-23
 */
@Controller
@Scope("prototype")
public class ProbationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrBecomeRegular.class);
	/** 注入service */
	@Autowired
	private BecomeRegularController becomeRegularController;
	private List<HrBecomeRegular> hrBecomeRegularList;
	private HrBecomeRegular hrBecomeRegular;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IBecomeRegularService iBecomeRegularService;
	private String id;
	private String pageNo;
	private List<HrBecomeRegular> hrBecomeRegulars;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	public List<HrBecomeRegular> getHrBecomeRegularList() {
		return hrBecomeRegularList;
	}

	public void setHrBecomeRegularList(List<HrBecomeRegular> hrBecomeRegularList) {
		this.hrBecomeRegularList = hrBecomeRegularList;
	}

	public HrBecomeRegular getHrBecomeRegular() {
		return hrBecomeRegular;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setHrBecomeRegular(HrBecomeRegular hrBecomeRegular) {
		this.hrBecomeRegular = hrBecomeRegular;
	}

	public IBecomeRegularService getiBecomeRegularService() {
		return iBecomeRegularService;
	}

	public void setiBecomeRegularService(IBecomeRegularService iBecomeRegularService) {
		this.iBecomeRegularService = iBecomeRegularService;
	}

	public List<HrBecomeRegular> getHrBecomeRegulars() {
		return hrBecomeRegulars;
	}

	public void setHrBecomeRegulars(List<HrBecomeRegular> hrBecomeRegulars) {
		this.hrBecomeRegulars = hrBecomeRegulars;
	}

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

	@Override
	public String goList() {
		try {
			hrBecomeRegularList = becomeRegularController.findHrBecomeRegularIndex();
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
			String transactionState = getRequestParameter("transactionState");
			if (null != transactionState && !"".equals(transactionState)) {
				params.put("transactionState," + SearchCondition.EQUAL, Integer.parseInt(transactionState));
			}
			/* 按最近使用 */
			String licenseDate = getRequestParameter("licenseDate");
			if (licenseDate != null && !"".equals(licenseDate)) {
				params.put("licenseDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(licenseDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("licenseDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = becomeRegularController.goSingleList(params, getPager());
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
			/* 事物主题 */
			String theme = getRequestParameter("theme");
			if (null != theme && !"".equals(theme)) {
				theme = URLDecoder.decode(theme, "utf-8");
			}
			/* 当事人 */
			String employeeName = getRequestParameter("employeeName");
			if (null != employeeName && !"".equals(employeeName)) {
				employeeName = URLDecoder.decode(employeeName, "utf-8");
			}
			/* 审批人 */
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			/* 试用期职务 */
			String probationPost = getRequestParameter("probationPost");
			if (null != probationPost && !"".equals(probationPost)) {
				probationPost = URLDecoder.decode(probationPost, "utf-8");
			}
			/* 转正后所属部门 */
			String afterThePromotionDepartment = getRequestParameter("afterThePromotionDepartment");
			if (null != afterThePromotionDepartment && !"".equals(afterThePromotionDepartment)) {
				afterThePromotionDepartment = URLDecoder.decode(afterThePromotionDepartment, "utf-8");
			}
			/* 转正后职位 */
			String afterThePromotionPost = getRequestParameter("afterThePromotionPost");
			if (null != afterThePromotionPost && !"".equals(afterThePromotionPost)) {
				afterThePromotionPost = URLDecoder.decode(afterThePromotionPost, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("theme," + SearchCondition.ANYLIKE, theme);
				pager = becomeRegularController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != theme && !"".equals(theme)) {
					params.put("theme," + SearchCondition.ANYLIKE, theme);
				}
				if (null != employeeName && !"".equals(employeeName)) {
					params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != probationPost && !"".equals(probationPost)) {
					params.put("probationPost," + SearchCondition.ANYLIKE, probationPost);
				}
				if (null != afterThePromotionDepartment && !"".equals(afterThePromotionDepartment)) {
					params.put("afterThePromotionDepartment," + SearchCondition.ANYLIKE, afterThePromotionDepartment);
				}
				if (null != afterThePromotionPost && !"".equals(afterThePromotionPost)) {
					params.put("afterThePromotionPost," + SearchCondition.ANYLIKE, afterThePromotionPost);
				}
				pager = becomeRegularController.goSingleList(params, getPager());
			}
			logger.info("获取活动计划搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据，跳转 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = becomeRegularController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至转正修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			}
			/*
			 * 不符合业务需求 屏蔽人：bobchen
			 */
			/*
			 * else { hrBecomeRegular = new HrBecomeRegular(); UserAccount
			 * userAccount = SecurityUtil.getCurrentUserAccount(); BaseEmployee
			 * employee =userAccount.getEmployee();
			 * hrBecomeRegular.setApproval(employee.getName());
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 异动 */
	public String goSaveOrUpdateXtaffTurnover() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateXtaffTurnover";
	}

	/** 离职 */
	public String goSaveOrUpdateXresign() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateXresign";
	}

	/** 借调 */
	public String goSaveOrUpdateStaffSecondments() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateStaffSecondments";
	}

	/** 请假 */
	public String goSaveOrUpdateAskForLeave() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAskForLeave";
	}

	/** 辞退 */
	public String goSaveOrUpdateDismiss() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDismiss";
	}

	/** 离退休 */
	public String goSaveOrUpdateRetirement() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRetirement";
	}

	/** 返聘 */
	public String goSaveOrUpdateBridget() {
		try {
			if (null != id && !"".equals(id)) {
				hrBecomeRegular = becomeRegularController.doListEntityById(id);
				logger.info("");
			} else {
				hrBecomeRegular = new HrBecomeRegular();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				hrBecomeRegular.setApproval(employee.getName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBridget";
	}

	/** 处理修改/保存操作转正 */
	public String saveOrUpdateHrBecomeRegular() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrBecomeRegular.getId()) && !"".equals(hrBecomeRegular.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrBecomeRegular.getTheme();
			String py = ChnToPinYin.getPYString(title);
			hrBecomeRegular.setChineseFirstLetter(py.toUpperCase());

			// 审批
			String types = getRequestParameter("types");
			initEntityBaseController.initEntityBaseAttribute(hrBecomeRegular);
			hrBecomeRegular = becomeRegularController.doSaveHrBecomeRegular(hrBecomeRegular, types, null);

			this.hrBecomeRegular.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.hrBecomeRegular.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			hrBecomeRegular.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.hrBecomeRegular);
			logger.info("对人事事物管理转正进行了修改！");
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
			HrBecomeRegular hrBecomeRegular = becomeRegularController.doListEntityById(id);
			if (null != hrBecomeRegular) {
				becomeRegularController.doDeleteByEntity(hrBecomeRegular);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除信息");
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

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iBecomeRegularService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iBecomeRegularService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public String goChooseEmployee() {
		return "goChooseEmployee";
	}

	/** 获取附件json数据 */
	public void getHrAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrBecomeRegular hrBecomeRegular = becomeRegularController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrAttachments>(hrBecomeRegular.getAttachments()), hrBecomeRegular.getAttachments().size(), "hrBecomeRegular");
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
	 * 
	 * 上传附件 附件上传处理需要完善
	 */
	public void uploadHrAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				HrBecomeRegular hrBecomeRegular = becomeRegularController.findEntityById(idStr);
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

					atts.setHrBecomeRegular(hrBecomeRegular);
					becomeRegularController.mergeAttachments(atts);
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
				String afIdLong = afId;
				if (!"".equals(afIdLong)) {
					HrAttachments atts = becomeRegularController.findHrAttachmentsEntityById(afIdLong);
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
						becomeRegularController.deleteHrAttachmentsEntity(atts);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取审批json数据 */
	public void getHrApprovalOpinionJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrBecomeRegular hrBecomeRegular = becomeRegularController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrApprovalOpinion>(hrBecomeRegular.getHrapprovalOpinions()), hrBecomeRegular.getHrapprovalOpinions().size(), "hrBecomeRegular");
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
}

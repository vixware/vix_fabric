package com.vix.hr.hrmgr.action;

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
import com.vix.hr.hrmgr.controller.PersonnelContractController;
import com.vix.hr.hrmgr.entity.PersonnelContract;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * @Description: 人事合同
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class AgreementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonnelContract.class);
	/** 注入service */
	@Autowired
	private PersonnelContractController personnelContractController;
	private List<PersonnelContract> personnelContractList;
	private PersonnelContract personnelContract;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IPersonalAttendanceService iPersonalAttendanceService;
	private String id;
	private String pageNo;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	public void executeDrools() throws Exception {

	}
	public void executeEsper() throws Exception {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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
			personnelContractList = personnelContractController.findPersonnelContractIndex();
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

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String contractState = getRequestParameter("contractState");
			if (null != contractState && !"".equals(contractState)) {
				params.put("contractState," + SearchCondition.ANYLIKE, contractState);
			}
			/* 按最近使用 */
			String signingDate = getRequestParameter("signingDate");
			if (signingDate != null && !"".equals(signingDate)) {
				params.put("signingDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(signingDate));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("signingDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personnelContractController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
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
			/* 合同编号 */
			String contractCode = getRequestParameter("contractCode");
			if (null != contractCode && !"".equals(contractCode)) {
				contractCode = URLDecoder.decode(contractCode, "utf-8");
			}
			/* 当事人 */
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			/* 经办人 */
			String attn = getRequestParameter("attn");
			if (null != attn && !"".equals(attn)) {
				attn = URLDecoder.decode(attn, "utf-8");
			}
			/* 甲方名称 */
			String categoryName = getRequestParameter("categoryName");
			if (null != categoryName && !"".equals(categoryName)) {
				categoryName = URLDecoder.decode(categoryName, "utf-8");
			}
			/* 甲方代表人 */
			String representative = getRequestParameter("representative");
			if (null != representative && !"".equals(representative)) {
				representative = URLDecoder.decode(representative, "utf-8");
			}
			/* 乙方名称 */
			String lesseenName = getRequestParameter("lesseenName");
			if (null != lesseenName && !"".equals(lesseenName)) {
				lesseenName = URLDecoder.decode(lesseenName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				pager = personnelContractController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contractCode && !"".equals(contractCode)) {
					params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != attn && !"".equals(attn)) {
					params.put("attn," + SearchCondition.ANYLIKE, attn);
				}
				if (null != categoryName && !"".equals(categoryName)) {
					params.put("categoryName," + SearchCondition.ANYLIKE, categoryName);
				}
				if (null != representative && !"".equals(representative)) {
					params.put("representative," + SearchCondition.ANYLIKE, representative);
				}
				if (null != lesseenName && !"".equals(lesseenName)) {
					params.put("lesseenName," + SearchCondition.ANYLIKE, lesseenName);
				}
				pager = personnelContractController.goSingleList(params, getPager());
			}
			logger.info("获取人事合同搜索列表数据页");
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
			Pager pager = personnelContractController.doSubSingleList(params, getPager());
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
				personnelContract = personnelContractController.doListEntityById(id);
				logger.info("");
			} else {
				personnelContract = new PersonnelContract();
				UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
				BaseEmployee employee = userAccount.getEmployee();
				personnelContract.setJiaName(employee.getName());

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
			if (StringUtils.isNotEmpty(personnelContract.getId()) && !"".equals(personnelContract.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = personnelContract.getAttn();
			String py = ChnToPinYin.getPYString(title);
			personnelContract.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(personnelContract);
			personnelContract = personnelContractController.doSavePersonnelContract(personnelContract);

			this.personnelContract.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.personnelContract.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			personnelContract.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.personnelContract);
			logger.info("对人事合同进行了修改！");
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
			PersonnelContract personnelContract = personnelContractController.doListEntityById(id);
			if (null != personnelContract) {
				personnelContractController.doDeleteByEntity(personnelContract);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除人事合同");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
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
				listOrganization = iPersonalAttendanceService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iPersonalAttendanceService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	/** 弹出选择部门，职位窗体 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public List<PersonnelContract> getPersonnelContractList() {
		return personnelContractList;
	}

	public void setPersonnelContractList(List<PersonnelContract> personnelContractList) {
		this.personnelContractList = personnelContractList;
	}

	public PersonnelContract getPersonnelContract() {
		return personnelContract;
	}

	public void setPersonnelContract(PersonnelContract personnelContract) {
		this.personnelContract = personnelContract;
	}

	public IPersonalAttendanceService getiPersonalAttendanceService() {
		return iPersonalAttendanceService;
	}

	public void setiPersonalAttendanceService(IPersonalAttendanceService iPersonalAttendanceService) {
		this.iPersonalAttendanceService = iPersonalAttendanceService;
	}

	/** 获取附件json数据 */
	public void getHrAttachmentsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				PersonnelContract personnelContract = personnelContractController.findEntityById(id);
				json = convertListToJson(new ArrayList<HrAttachments>(personnelContract.getAttachments()), personnelContract.getAttachments().size(), "personnelContract");
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
	 * 上传附件 TODO: 附件上传处理需要完善
	 */
	public void uploadHrAttachments() {
		try {
			String idStr = getRequestParameter("id");
			if (null != idStr && !"".equals(idStr)) {
				PersonnelContract personnelContract = personnelContractController.findEntityById(idStr);
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
					atts.setPersonnelContract(personnelContract);
					personnelContractController.mergeAttachments(atts);
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
				if (StringUtils.isNotEmpty(afIdLong) && !afIdLong.equals("0")) {
					HrAttachments atts = personnelContractController.findHrAttachmentsEntityById(afIdLong);
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
						personnelContractController.deleteHrAttachmentsEntity(atts);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

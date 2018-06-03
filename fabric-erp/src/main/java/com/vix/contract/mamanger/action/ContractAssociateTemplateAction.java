package com.vix.contract.mamanger.action;

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
import com.vix.common.security.util.SecurityUtil;
import com.vix.contract.mamanger.controller.ContractAssociateTemplateController;
import com.vix.contract.mamanger.controller.ContractController;
import com.vix.contract.mamanger.controller.ContractPaymentPlanController;
import com.vix.contract.mamanger.entity.ContractAssociateTemplate;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ContractAssociateTemplateAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractPaymentPlanController.class);
	@Autowired
	private ContractAssociateTemplateController contractAssociateTemplateController;
	private String id;
	@Autowired
	private ContractController contractController;
	private String pageNo;
	private ContractAssociateTemplate contractAssociateTemplate;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private List<ContractAssociateTemplate> contractAssociateTemplateList;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			contractAssociateTemplateList = contractAssociateTemplateController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/*合同编码*/
			String contractCode = getRequestParameter("contractCode");
			if (null != contractCode && !"".equals(contractCode)) {
				contractCode = URLDecoder.decode(contractCode, "utf-8");
			}
			/*模板编号*/
			String templeteId = getRequestParameter("templeteId");
			if (null != templeteId && !"".equals(templeteId)) {
				templeteId = URLDecoder.decode(templeteId, "utf-8");
			}
			/*审批单编号*/
			String contractApproveCode = getRequestParameter("contractApproveCode");
			if (null != contractApproveCode && !"".equals(contractApproveCode)) {
				contractApproveCode = URLDecoder.decode(contractApproveCode, "utf-8");
			}
			/*主题*/
			String theme = getRequestParameter("theme");
			if (null != theme && !"".equals(theme)) {
				theme = URLDecoder.decode(theme, "utf-8");
			}
			/*创建人*/
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("theme," + SearchCondition.ANYLIKE, theme);
				pager = contractAssociateTemplateController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != theme && !"".equals(theme)) {
					params.put("theme," + SearchCondition.ANYLIKE, theme);
				}
				if (null != contractCode && !"".equals(contractCode)) {
					params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				}
				if (null != templeteId && !"".equals(templeteId)) {
					params.put("templeteId," + SearchCondition.ANYLIKE, templeteId);
				}
				if (null != contractApproveCode && !"".equals(contractApproveCode)) {
					params.put("contractApproveCode," + SearchCondition.ANYLIKE, contractApproveCode);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = contractAssociateTemplateController.goSingleList(params, getPager());
			}
			logger.info("获取合同模板搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}


	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/*按最近使用*/
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			/*uploadPersonId包含当前登录人id*/
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			Pager pager = contractAssociateTemplateController.goSingleList(params, getPager());
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
	

	/** 获取列表数据 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = contractAssociateTemplateController.doSubSingleList(params,getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	// ** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				contractAssociateTemplate = contractAssociateTemplateController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(contractAssociateTemplate.getId()) && !"0".equals(contractAssociateTemplate.getId())) {
				isSave = false;
			}
			/**索引 */
			String title = contractAssociateTemplate.getTheme();
			String py = ChnToPinYin.getPYString(title);
			contractAssociateTemplate.setChineseFirstLetter(py.toUpperCase());
			
			contractAssociateTemplate = contractAssociateTemplateController.doSaveSalesOrder(contractAssociateTemplate);
			initEntityBaseController.initEntityBaseAttribute(contractAssociateTemplate);
			
			this.contractAssociateTemplate.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.contractAssociateTemplate.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			contractAssociateTemplate.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.contractAssociateTemplate);
			logger.info("对合同模板进行了修改！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ContractAssociateTemplate pb = contractAssociateTemplateController.findEntityById(id);
			if (null != pb) {
				contractAssociateTemplateController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 获取json数据 */
	public void getContractTemplate() {
		try {
			String json = "";
			/* String id = getRequestParameter("id"); */
			if (contractAssociateTemplate != null && null != contractAssociateTemplate.getId()
					&& !"".equals(contractAssociateTemplate.getId())) {
				ContractAssociateTemplate so = contractController.findEntityById2(contractAssociateTemplate.getId());
				json = convertListToJson(new ArrayList<ContractTemplate>(so.getContractTemplate()), so.getContractTemplate().size(), "contractAssociateTemplate");
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
	
	public String addAttachFile2() {
		return "addAttachFile2";
	}
	
	@Override
	public void uploadAttachment() {
		try {
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ContractAssociateTemplate so = contractController.findEntityById2(id);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					// ** 上传目录 *//*
					String baseDir = getServletContext().getRealPath(separator + "richContent");
					BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
					String[] fileNames = fileToUploadFileName.split("\\.");
					String fileName = fileNames[0];
					String extFileName = fileNames[fileNames.length - 1];
					String newFilePath = "";
					long timeTemp = System.currentTimeMillis();
					String newFileName = fileName + "_" + timeTemp + "."+ extFileName;
					newFilePath = baseDir + separator + newFileName;
					BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(newFilePath));
					byte[] buf = new byte[1024 * 100];
					int len = -1;
					while ((len = bufIn.read(buf)) != -1) {
						bufOut.write(buf, 0, len);
					}
					bufOut.close();
					bufIn.close();
					ContractTemplate saf = new ContractTemplate();
					saf.setName(newFileName);
					saf.setContractAssociateTemplate(so);
					contractController.mergeContractTemplate(saf);
					renderJson("文件上传成功!");
				}
			} else {
				renderJson("合同id获取失败!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("文件上传失败!");
		}

	}
	
	/** 删除附件 */
	public void deleteAttachFile() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				String afIdLong = afId;
				if (!afIdLong.equals("")) {
					ContractTemplate saf = contractController
							.findContractTemplateEntityById(afIdLong);
					if (null != saf) {
						// ** 上传目录 *//*
						String separator = System.getProperty("file.separator");
						String baseDir = getServletContext().getRealPath(separator + "richContent");
						baseDir += separator;
						baseDir += saf.getName();
						File file = new File(baseDir);
						if (file.exists()) {
							file.delete();
						}
						contractController.deleteContractTemplateEntity(saf);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseChkSimpleGrid() {
		return "goChooseChkSimpleGrid";
	}

	public String goChooseRadioSimpleGrid() {
		return "goChooseRadioSimpleGrid";
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

	public List<ContractAssociateTemplate> getContractAssociateTemplateList() {
		return contractAssociateTemplateList;
	}

	public void setContractAssociateTemplateList(
			List<ContractAssociateTemplate> contractAssociateTemplateList) {
		this.contractAssociateTemplateList = contractAssociateTemplateList;
	}

	public ContractAssociateTemplate getContractAssociateTemplate() {
		return contractAssociateTemplate;
	}

	public void setContractAssociateTemplate(
			ContractAssociateTemplate contractAssociateTemplate) {
		this.contractAssociateTemplate = contractAssociateTemplate;
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

}

package com.vix.contract.mamanger.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.contract.mamanger.controller.ContractAssociateTemplateController;
import com.vix.contract.mamanger.controller.ContractController;
import com.vix.contract.mamanger.controller.ContractPaymentPlanController;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ProtocolTemplete;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ProtocolTempleteAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractPaymentPlanController.class);
	@Autowired
	private ContractAssociateTemplateController contractAssociateTemplateController;
	private String id;
	@Autowired
	private ContractController contractController;
	private String pageNo;
	private ProtocolTemplete protocolTemplete;
	private Date updateTime;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private List<ProtocolTemplete> protocolTempleteList;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/** 跳转到列表页面 */

	@Override
	public String goList() {
		try {
			protocolTempleteList = baseHibernateService.findAllByEntityClass(ProtocolTemplete.class);
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
			// 内容
			String searchContent = getRequestParameter("searchContent");
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			/// 名称
			String name = getRequestParameter("name");
			//创建人
			String created = getRequestParameter("created");
			//描述
			String description = getRequestParameter("description");
			if (null != created && !"".equals(created)) {
				created = URLDecoder.decode(created, "utf-8");
			}
			if (null != description && !"".equals(description)) {
				description = URLDecoder.decode(description, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("created," + SearchCondition.ANYLIKE, searchContent);
				params.put("description," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = contractAssociateTemplateController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				//将内容(name)的值remove掉
				if (params.containsKey("name,anylike")) {
					params.remove("name,anylike");
				}
				if (null != name && !"".equals(name)) {
					params.put("theme," + SearchCondition.ANYLIKE, name);
				}
				if (null != created && !"".equals(created)) {
					params.put("created," + SearchCondition.ANYLIKE, created);
				}
				if (null != description && !"".equals(description)) {
					params.put("description," + SearchCondition.ANYLIKE, description);
				}
				pager = contractAssociateTemplateController.goSingleList(params, getPager());
			}
			logger.info("获取合同模板表数据页");
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
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = contractAssociateTemplateController.doSubSingleList1(params, getPager());
			logger.info("获取待办合同列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// ** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				protocolTemplete = contractAssociateTemplateController.doListEntityById1(id);
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
			if (protocolTemplete != null && null != protocolTemplete.getId()) {
				isSave = false;
			}
			protocolTemplete = contractAssociateTemplateController.doSaveSalesOrder1(protocolTemplete);
			initEntityBaseController.initEntityBaseAttribute(protocolTemplete);
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
			ProtocolTemplete pb = contractAssociateTemplateController.findEntityById1(id);
			if (null != pb) {
				contractAssociateTemplateController.doDeleteByEntity1(pb);
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
			if (protocolTemplete != null && null != protocolTemplete.getId() && !"".equals(protocolTemplete.getId())) {
				ProtocolTemplete so = contractController.findEntityById3(protocolTemplete.getId());
				json = convertListToJson(new ArrayList<ContractTemplate>(so.getContractTemplate()), so.getContractTemplate().size(), "protocolTemplete");
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

	public String addAttachFile1() {
		return "addAttachFile1";
	}

	@Override
	public void uploadAttachment() {
		try {
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ProtocolTemplete so = contractController.findEntityById3(id);
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
					ContractTemplate saf = new ContractTemplate();
					saf.setName(newFileName);
					saf.setProtocolTemplete(so);
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
	public void deleteAttachFile1() {
		try {
			String afId = getRequestParameter("afId");
			if (null != afId && !"".equals(afId)) {
				String afIdLong = afId;
				if (!"".equals(afIdLong)) {
					ContractTemplate saf = contractController.findContractTemplateEntityById(afIdLong);
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

	public List<ProtocolTemplete> getProtocolTempleteList() {
		return protocolTempleteList;
	}

	public void setProtocolTempleteList(List<ProtocolTemplete> protocolTempleteList) {
		this.protocolTempleteList = protocolTempleteList;
	}

	public ProtocolTemplete getProtocolTemplete() {
		return protocolTemplete;
	}

	public void setProtocolTemplete(ProtocolTemplete protocolTemplete) {
		this.protocolTemplete = protocolTemplete;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

package com.vix.contract.contractInquiry.action;

import java.io.File;
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
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.contract.config.entity.ContractGroupType;
import com.vix.contract.config.entity.ContractNatureType;
import com.vix.contract.config.entity.ContractStageGroupType;
import com.vix.contract.config.entity.ContractTypeCombine;
import com.vix.contract.config.entity.EnableStageType;
import com.vix.contract.config.entity.ModeType;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.contract.contractInquiry.controller.ContractInquiryController;
import com.vix.contract.contractInquiry.entity.ContractChildItem;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 
 * @ClassName: ContractInquiryAction
 * @Description: 合同查询
 * @author bobchen
 * @date 2015年12月24日 下午5:19:28
 *
 */
@Controller
@Scope("prototype")
public class ContractInquiryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractInquiry.class);
	/** 注入service */
	@Autowired
	private ContractInquiryController contractInquiryController;
	/*
	 * @Autowired private IUseCache useCache;
	 */
	private List<ContractInquiry> contractInquiryList;
	private ContractInquiry contractInquiry;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	/** 合同子项 */
	private ContractChildItem childItem;
	/** 合同标的 */
	private ContractSubject subject;
	/** 合同预警 */
	private ContractWarning warning;
	/** 合同审批单 */
	private ApplicationForm applications;
	/** 价格条件 */
	private PriceConditions price;
	/** 合同采购定价条件 */
	private ContractPricingConditions pricingConditions;
	/** 销售定价条件 */
	private ContractMarket market;
	private Item item;
	private String id;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private String parentId;
	private String pageNo;
	public Integer contractType;

	/** 供应商 */
	private Supplier supplier;
	/** 所属合同组 */
	private List<ContractGroupType> contractGroupTypeList;
	/** 合同类型集合 */
	private List<ContractTypeCombine> contractTypeCombineList;
	/** 合同性质 */
	private List<ContractNatureType> contractNatureTypeList;
	/** 所属行业 */
	private List<ViewIndustryType> viewIndustryList;
	/** 合同履行状态 */
	private List<ModeType> modeTypeList;
	/** 启用阶段 */
	private List<EnableStageType> enableStageTypeList;
	/** 合同阶段组 */
	private List<ContractStageGroupType> contractStageGroupTypeList;
	/** 币种 */
	private List<CurrencyType> currencyTypeList;
	/** 汇率 */
	private List<ExchangeRate> exchangeRateList;

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
			contractInquiryList = contractInquiryController.findContractIndex();
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
			/* 类型 */
			String contractType = getRequestParameter("contractType");
			if (null != contractType && !"".equals(contractType)) {
				params.put("contractType," + SearchCondition.EQUAL, Integer.parseInt(contractType));
			}
			/* 按最近使用 */
			String contractStartTime = getRequestParameter("contractStartTime");
			if (contractStartTime != null && !"".equals(contractStartTime)) {
				params.put("contractStartTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(contractStartTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("contractStartTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = contractInquiryController.goSingleList(params, getPager());
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
			/* 主合同编码 */
			String masterContractCoding = getRequestParameter("masterContractCoding");
			if (null != masterContractCoding && !"".equals(masterContractCoding)) {
				masterContractCoding = URLDecoder.decode(masterContractCoding, "utf-8");
			}
			/* 合同编码 */
			String contractCode = getRequestParameter("contractCode");
			if (null != contractCode && !"".equals(contractCode)) {
				contractCode = URLDecoder.decode(contractCode, "utf-8");
			}
			/* 合同名称 */
			String contractName = getRequestParameter("contractName");
			if (null != contractName && !"".equals(contractName)) {
				contractName = URLDecoder.decode(contractName, "utf-8");
			}
			/* 项目代码 */
			String projectCode = getRequestParameter("projectCode");
			if (null != projectCode && !"".equals(projectCode)) {
				projectCode = URLDecoder.decode(projectCode, "utf-8");
			}
			/* 项目名称 */
			String projectName = getRequestParameter("projectName");
			if (null != projectName && !"".equals(projectName)) {
				projectName = URLDecoder.decode(projectName, "utf-8");
			}
			/* 所属部门 */
			String departmentName = getRequestParameter("departmentName");
			if (null != departmentName && !"".equals(departmentName)) {
				departmentName = URLDecoder.decode(departmentName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				pager = contractInquiryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != masterContractCoding && !"".equals(masterContractCoding)) {
					params.put("masterContractCoding," + SearchCondition.ANYLIKE, masterContractCoding);
				}
				if (null != contractCode && !"".equals(contractCode)) {
					params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				}
				if (null != contractName && !"".equals(contractName)) {
					params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				}
				if (null != projectCode && !"".equals(projectCode)) {
					params.put("projectCode," + SearchCondition.ANYLIKE, projectCode);
				}
				if (null != projectName && !"".equals(projectName)) {
					params.put("projectName," + SearchCondition.ANYLIKE, projectName);
				}
				if (null != departmentName && !"".equals(departmentName)) {
					params.put("departmentName," + SearchCondition.ANYLIKE, departmentName);
				}
				pager = contractInquiryController.goSingleList(params, getPager());
			}
			logger.info("获取合同查询搜索列表数据页");
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
			Pager pager = contractInquiryController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至采购合同修改页面 */
	public String goSaveOrUpdate() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			} else {
				contractInquiry = new ContractInquiry();
				contractInquiry.setIsPublish(0);
				contractInquiry.setIsTemp("1");
				contractInquiry.setContractType(contractType);
				contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至采购框架协议修改页面 */
	public String goPurchaseFA() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			} else {
				contractInquiry = new ContractInquiry();
				contractInquiry.setIsPublish(0);
				contractInquiry.setIsTemp("1");
				contractInquiry.setContractType(contractType);
				contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseFA";
	}

	/** 跳转至销售合同修改页面 */
	public String goSalesContract() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			} else {
				contractInquiry = new ContractInquiry();
				contractInquiry.setIsPublish(0);
				contractInquiry.setIsTemp("1");
				contractInquiry.setContractType(contractType);
				contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesContract";
	}

	/** 跳转至销售定价条件修改页面 */
	public String goContractMarket() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			} else {
				contractInquiry = new ContractInquiry();
				contractInquiry.setIsPublish(0);
				contractInquiry.setIsTemp("1");
				contractInquiry.setContractType(contractType);
				contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goContractMarket";
	}

	/** 跳转至项目合同修改页面 */
	public String goPmContract() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			} else {
				contractInquiry = new ContractInquiry();
				contractInquiry.setIsPublish(0);
				contractInquiry.setIsTemp("1");
				contractInquiry.setContractType(contractType);
				contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPmContract";
	}

	public String goFixedPrice() {
		try {
			if (null != id && !"".equals(id)) {
				item = baseHibernateService.findEntityById(Item.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fixedPrice";
	}

	/********************************************************************************************************************************/
	/** 获取项目列表数据 */
	public String goProjectNameList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = contractInquiryController.goProjectName(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectNameList";
	}

	/** 获取供应商列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = contractInquiryController.goCustomerAccount(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}

	/** 跳转到快速新建供应商页面 */
	public String goAddQuicklySupplier() {
		return "goAddQuicklySupplier";
	}

	/**
	 * 保存供应商
	 * 
	 * @return
	 */
	public String saveOrUpdateSupplier() {
		try {
			supplier.setStatus("S3");
			contractInquiryController.doSaveSupplier(supplier);
			setMessage(SAVE_SUCCESS + "," + supplier.getCode() + "," + supplier.getName() + ",");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/************************************************************* 合同子项 *******************************************************************/
	/** 跳转到培训计划明细页面 */
	public String goAddContractLine() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				childItem = contractInquiryController.doListContractLineById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractLine";
	}

	/** 获取合同子项明细json数据 */
	public void getContractLineJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractChildItem>(ci.getContractChildItems()), ci.getContractChildItems().size());
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
	 * 合同子项明细
	 * 
	 * @return
	 */
	public String saveOrUpdateContractLine() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				childItem.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveContractLine(childItem);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除tab明细操作 */
	public String deleteContractLineById() {
		try {
			ContractChildItem contractChildItem = contractInquiryController.doListContractLineById(id);
			if (null != contractChildItem) {
				contractInquiryController.deleteContractLineEntity(contractChildItem);
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

	/****************************************************** 合同标的 **************************************************************************/
	/** 跳转到合同标的明细页面 */
	public String goAddContractSubject() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				subject = contractInquiryController.doListContractSubjectById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractSubject";
	}

	/** 获取合同标的明细json数据 */
	public void getContractSubjectJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractSubject>(ci.getContractSubjects()), ci.getContractSubjects().size());
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
	 * 合同标的明细
	 * 
	 * @return
	 */
	public String saveOrUpdateContractSubject() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				subject.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveContractSubject(subject);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除标的明细操作 */
	public String deleteContractSubjectById() {
		try {
			ContractSubject contractSubject = contractInquiryController.doListContractSubjectById(id);
			if (null != contractSubject) {
				contractInquiryController.deleteContractSubjectEntity(contractSubject);
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

	/****************************************************** 合同预警 **************************************************************************/
	/** 跳转到合同预警明细页面 */
	public String goAddContractWarning() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				warning = contractInquiryController.doListContractWarningById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractWarning";
	}

	/** 获取合同预警明细json数据 */
	public void getContractWarningJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractWarning>(ci.getContractWarnings()), ci.getContractWarnings().size());
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
	 * 合同预警明细
	 * 
	 * @return
	 */
	public String saveOrUpdateContractWarning() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				warning.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveContractWarning(warning);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除预警明细操作 */
	public String deleteContractWarningById() {
		try {
			ContractWarning contractWarning = contractInquiryController.doListContractWarningById(id);
			if (null != contractWarning) {
				contractInquiryController.deleteContractWarningEntity(contractWarning);
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

	/****************************************************** 合同审批单 **************************************************************************/
	/** 跳转到合同审批明细页面 */
	public String goAddApplicationForm() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				applications = contractInquiryController.doListApplicationFormById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddApplicationForm";
	}

	/** 获取合同审批明细json数据 */
	public void getApplicationFormJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ApplicationForm>(ci.getApplicationForms()), ci.getApplicationForms().size());
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
	 * 合同审批明细
	 * 
	 * @return
	 */
	public String saveOrUpdateApplicationForm() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				applications.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveApplicationForm(applications);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除审批明细操作 */
	public String deleteApplicationFormById() {
		try {
			ApplicationForm applicationForm = contractInquiryController.doListApplicationFormById(id);
			if (null != applicationForm) {
				contractInquiryController.deleteApplicationFormEntity(applicationForm);
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

	/****************************************************** 价格条件 **************************************************************************/
	/** 跳转到价格条件明细页面 */
	public String goAddPriceConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				price = contractInquiryController.doListPriceConditionsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPriceConditions";
	}

	/** 获取价格条件明细json数据 */
	public void getPriceConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<PriceConditions>(ci.getPriceConditionss()), ci.getPriceConditionss().size());
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
	 * 价格条件明细
	 * 
	 * @return
	 */
	public String saveOrUpdatePriceConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				price.setContractInquiry(contractInquiry);
				contractInquiryController.doSavePriceConditions(price);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除价格条件明细操作 */
	public String deletePriceConditionsById() {
		try {
			PriceConditions priceConditions = contractInquiryController.doListPriceConditionsById(id);
			if (null != priceConditions) {
				contractInquiryController.deletePriceConditionsEntity(priceConditions);
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

	/****************************************************** 采购定价条件 **************************************************************************/
	/** 跳转到采购定价条件明细页面 */
	public String goAddContractPricingConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				pricingConditions = contractInquiryController.doListContractPricingConditionsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractPricingConditions";
	}

	/** 获取采购定价条件明细json数据 */
	public void getContractPricingConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractPricingConditions>(ci.getContractPricingConditionss()), ci.getContractPricingConditionss().size());
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
	 * 采购定价条件明细
	 * 
	 * @return
	 */
	public String saveOrUpdateContractPricingConditions() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				pricingConditions.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveContractPricingConditions(pricingConditions);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除采购定价条件明细操作 */
	public String deleteContractPricingConditionsById() {
		try {
			ContractPricingConditions contractPricingConditions = contractInquiryController.doListContractPricingConditionsById(id);
			if (null != contractPricingConditions) {
				contractInquiryController.deleteContractPricingConditionsEntity(contractPricingConditions);
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

	/****************************************************** 销售定价条件 **************************************************************************/

	/** 跳转到销售定价条件明细页面 */
	public String goAddContractMarket() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				market = contractInquiryController.doListContractMarketById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractMarket";
	}

	/** 获取采销售定价条件明细json数据 */
	public void getContractMarketJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractMarket>(ci.getContractMarkets()), ci.getContractMarkets().size());
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
	 * 销售定价条件明细
	 * 
	 * @return
	 */
	public String saveOrUpdateContractMarket() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				contractInquiry = contractInquiryController.doListEntityById(id);
				market.setContractInquiry(contractInquiry);
				contractInquiryController.doSaveContractMarket(market);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 处理删除销售定价条件明细操作 */
	public String deleteContractMarketById() {
		try {
			ContractMarket contractMarket = contractInquiryController.doListContractMarketById(id);
			if (null != contractMarket) {
				contractInquiryController.deleteContractMarketEntity(contractMarket);
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

	/********************************************************************************************************************************/
	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(contractInquiry.getId()) && !"0".equals(contractInquiry.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = contractInquiry.getContractName();
			String py = ChnToPinYin.getPYString(title);
			contractInquiry.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(contractInquiry);
			contractInquiry = contractInquiryController.doSaveContract(contractInquiry);
			contractInquiry.setIsTemp("");
			contractInquiry.setIsPublish(0);
			contractInquiry.setContractType(contractType);

			this.contractInquiry.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.contractInquiry.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			contractInquiry.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.contractInquiry);
			logger.info("对合同查询进行了修改！");
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
			ContractInquiry contractInquiry = contractInquiryController.doListEntityById(id);
			if (null != contractInquiry) {
				contractInquiryController.doDeleteByEntity(contractInquiry);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除合同查询");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String addHrAttachments() {
		return "addHrAttachments";
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public List<ContractInquiry> getContractInquiryList() {
		return contractInquiryList;
	}

	public void setContractInquiryList(List<ContractInquiry> contractInquiryList) {
		this.contractInquiryList = contractInquiryList;
	}


	public ContractChildItem getChildItem() {
		return childItem;
	}

	public void setChildItem(ContractChildItem childItem) {
		this.childItem = childItem;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
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

	public List<ContractGroupType> getContractGroupTypeList() {
		return contractGroupTypeList;
	}

	public void setContractGroupTypeList(List<ContractGroupType> contractGroupTypeList) {
		this.contractGroupTypeList = contractGroupTypeList;
	}

	public List<ContractTypeCombine> getContractTypeCombineList() {
		return contractTypeCombineList;
	}

	public void setContractTypeCombineList(List<ContractTypeCombine> contractTypeCombineList) {
		this.contractTypeCombineList = contractTypeCombineList;
	}

	public List<ContractNatureType> getContractNatureTypeList() {
		return contractNatureTypeList;
	}

	public void setContractNatureTypeList(List<ContractNatureType> contractNatureTypeList) {
		this.contractNatureTypeList = contractNatureTypeList;
	}

	public List<ViewIndustryType> getViewIndustryList() {
		return viewIndustryList;
	}

	public void setViewIndustryList(List<ViewIndustryType> viewIndustryList) {
		this.viewIndustryList = viewIndustryList;
	}

	public List<ModeType> getModeTypeList() {
		return modeTypeList;
	}

	public void setModeTypeList(List<ModeType> modeTypeList) {
		this.modeTypeList = modeTypeList;
	}

	public List<EnableStageType> getEnableStageTypeList() {
		return enableStageTypeList;
	}

	public void setEnableStageTypeList(List<EnableStageType> enableStageTypeList) {
		this.enableStageTypeList = enableStageTypeList;
	}

	public List<ContractStageGroupType> getContractStageGroupTypeList() {
		return contractStageGroupTypeList;
	}

	public void setContractStageGroupTypeList(List<ContractStageGroupType> contractStageGroupTypeList) {
		this.contractStageGroupTypeList = contractStageGroupTypeList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ContractSubject getSubject() {
		return subject;
	}

	public void setSubject(ContractSubject subject) {
		this.subject = subject;
	}

	public ContractWarning getWarning() {
		return warning;
	}

	public void setWarning(ContractWarning warning) {
		this.warning = warning;
	}

	public ApplicationForm getApplications() {
		return applications;
	}

	public void setApplications(ApplicationForm applications) {
		this.applications = applications;
	}

	public PriceConditions getPrice() {
		return price;
	}

	public void setPrice(PriceConditions price) {
		this.price = price;
	}

	public ContractPricingConditions getPricingConditions() {
		return pricingConditions;
	}

	public void setPricingConditions(ContractPricingConditions pricingConditions) {
		this.pricingConditions = pricingConditions;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ContractMarket getMarket() {
		return market;
	}

	public void setMarket(ContractMarket market) {
		this.market = market;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
}
package com.vix.contract.mamanger.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.contract.config.entity.ContractCatalog;
import com.vix.contract.config.entity.ContractGroupType;
import com.vix.contract.config.entity.ContractNatureType;
import com.vix.contract.config.entity.ContractStageGroupType;
import com.vix.contract.config.entity.ContractTypeCombine;
import com.vix.contract.config.entity.EnableStageType;
import com.vix.contract.config.entity.ModeType;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.contract.mamanger.controller.ContractController;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.contract.mamanger.entity.ContractLine;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PmContract;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IPriceConditionService;
import com.vix.mdm.srm.share.entity.Supplier;

import flexjson.JSONDeserializer;

/**
 * 合同起草
 */
@Controller
@Scope("prototype")
public class ContractDraftingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractController.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/** 注入service */
	@Autowired
	private ContractController contractController;
	@Autowired
	private IPriceConditionService priceConditionService;
	private Contract contract;
	public Integer contractType;
	private ContractSubject contractSubject;
	private PmContract pmContract;
	private ContractMarket contractMarket;
	private ContractPricingConditions contractPricingConditions;
	/** 跳转到列表页面，contractList为列表页面的索引功能数据集 */
	private List<Contract> contractList;
	private String id;
	private Date updateTime;
	private Item item;
	private Item entity;
	private String itemid;
	private String priceConditionRuleId;
	/** 是否拒投票*//*
	private List<IsBidType> isBidTypeList;
	*//** 时效控制方式*//*
	private List<AgingControlType> agingControlTypeList;
	*//** 时效控制环节*//*
	private List<AgingControlLinkType> agingControlLinkTypeList;
	*//** 所属部门*//*
	private List<ViewDepartmentType> viewDepartmentTypeList;
	*//** 业务类型*//*
	private List<BusinessType> businessTypeList;*/
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	/** 供应商*/
	private Supplier supplier;
	/** 客户*/
	private CustomerAccount customerAccount;
	private String pageNo;
	/**所属合同组*/
	private List<ContractGroupType> contractGroupTypeList;
	/**合同类型集合*/
	private List<ContractTypeCombine> contractTypeCombineList;
	/**合同性质*/
	private List<ContractNatureType> contractNatureTypeList;
	/**所属行业*/
	private List<ViewIndustryType> viewIndustryList;
	/**合同履行状态*/
	private List<ModeType> modeTypeList;
	/**启用阶段*/
	private List<EnableStageType> enableStageTypeList;
	/**合同阶段组*/
	private List<ContractStageGroupType> contractStageGroupTypeList;
	/**币种*/
	private List<CurrencyType> currencyTypeList;
	/**汇率*/
	private List<ExchangeRate> exchangeRateList;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	
	@Override
	public String goList() {
		try {
			contractList = baseHibernateService.findAllByEntityClass(Contract.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String contractName = getRequestParameter("contractName");
			String operator = getRequestParameter("operator");
			String mode = getRequestParameter("mode");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != mode && !"".equals(mode)) {
				params.put("mode," + SearchCondition.EQUAL, mode);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null != contractName && !"".equals(contractName)) {
				params.put("contractName," + SearchCondition.ANYLIKE,contractName);
			}
			if (null != operator && !"".equals(operator)) {
				params.put("operator," + SearchCondition.ANYLIKE, operator);
			}
			// 最近使用
			String rencentDate = getRequestParameter("rencentDate");
			if (rencentDate != null && !"".equals(rencentDate)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(rencentDate));
			}
			Pager pager = contractController.doSubSingleList(params, getPager());
			logger.info("获取合同查询列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 经办人
			String operator = getRequestParameter("operator");
			String code = getRequestParameter("code");
			// 批准人
			String approval = getRequestParameter("approval");
			if (null != approval && !"".equals(approval)) {
				approval = URLDecoder.decode(approval, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractName," + SearchCondition.ANYLIKE,searchContent);
				params.put("operator," + SearchCondition.ANYLIKE, searchContent);
				params.put("approval," + SearchCondition.ANYLIKE, searchContent);
				pager = contractController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != approval && !"".equals(approval)) {
					params.put("approval," + SearchCondition.ANYLIKE, approval);
				}
				if (null != operator && !"".equals(operator)) {
					params.put("operator," + SearchCondition.ANYLIKE, operator);
				}
				if (null != code && !"".equals(code)) {
					params.put("contractName," + SearchCondition.ANYLIKE, code);
				}
				pager = contractController.goSingleList(params, getPager());
			}
			logger.info("获取合同查询列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Contract supplier = contractController.doListEntityById(id);
			if (null != supplier) {
				contractController.doDeleteByEntity(supplier);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除合同信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转至起草供应商采购订单修改页面 */
	public String goSaveOrUpdate() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				logger.info("");
			}else{
				contract = new Contract();
				contract.setContractType(contractType);
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 跳转至采购框架协议修改页面 */
	public String goPurchaseAgreement() {
		try {
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_PURCHASE_AGREEMENT;
	}
	
	/** 跳转至销售框架协议修改页面 */
	public String goSalesAgreement() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SALES_AGREEMENT;
	}
	
	public String goFixedPrice(){
		try {
			if(null != id && !"".equals(id)){
				item = baseHibernateService.findEntityById(Item.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fixedPrice";
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
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_PM_CONTRACT;
	}

	/** 跳转至起草销售订单修改页面 */
	public String goSaveOrUpdateApply() {
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				logger.info("");
			}else{
				contract = new Contract();
				contract.setContractType(contractType);
				contract.setIsTemp("1");
				contract=this.baseHibernateService.merge(contract);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE_APPLY;
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			contractGroupTypeList = baseHibernateService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = baseHibernateService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = baseHibernateService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = baseHibernateService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = baseHibernateService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = baseHibernateService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = baseHibernateService.findAllByEntityClass(ContractStageGroupType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (null != contract.getId()) {
				isSave = false;
			}
			/** 合同项 */
			String dlJson = getRequestParameter("dlJson");
			List<ContractLine> dlList = new JSONDeserializer<List<ContractLine>>()
					.use("values", ContractLine.class).deserialize(dlJson);
			/** 合同预警 */
			String dpJson = getRequestParameter("dpJson");
			List<ContractWarning> dpList = new JSONDeserializer<List<ContractWarning>>()
					.use("values", ContractWarning.class).deserialize(dpJson);
			/** 合同审批 */
			String spJson = getRequestParameter("spJson");
			List<ApplicationForm> spList = new JSONDeserializer<List<ApplicationForm>>()
					.use("values", ApplicationForm.class).deserialize(spJson);
			/** 价格条件*/
			String jgJson = getRequestParameter("jgJson");
			List<PriceConditions> jgList = new JSONDeserializer<List<PriceConditions>>()
					.use("values", PriceConditions.class).deserialize(jgJson);
			contract.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(contract);
			contract = contractController.doSaveSalesOrder1(contract, dlList,dpList, spList,jgList);
			logger.info("新增！");
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
		}
		return UPDATE;
	}

	/** 获取json数据 */
	public void getContract1() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract so = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractLine>(so.getContractLines()), so.getContractLines().size(), "contract");
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

	/** 获取json数据 */
	public void getContract2() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract so = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractWarning>(so.getContractWarning()),so.getContractWarning().size(), "contract");
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

	/** 获取json数据 */
	public void getContract3() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract so = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ApplicationForm>(so.getApplicationForm()),so.getApplicationForm().size(), "contract");
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
	/** 获取json数据 */
	public void getContract4() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract so = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<PriceConditions>(so.getPriceConditions()),so.getPriceConditions().size(), "contract");
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

	/** 获取采购列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			Pager pager = contractController.goSingleList3(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}
	/** 获取列表数据 */
	public String goProjectNameList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = contractController.goSingleList2(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goProjectNameList";
	}
	
	/**部门*/
	public String goChooseOrgUnit(){
		return "goChooseOrgUnit";
	}

	/** 获取列表数据 */
	public String goSubRadioSingleList1() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = contractController.goSingleList3(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList1";
	}
	/** 获取json数据 */
	public void getContractTemplate() {
		try {
			String json = "";
			if (id != null  && !"".equals(id)) {
				Contract so = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractTemplate>(so.getContractTemplate()), so.getContractTemplate().size());
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

	/** 删除附件 */
	public void deleteAttachFile() {
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

	@Override
	public void uploadAttachment() {
		try {
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract so = contractController.findEntityById1(id);
				if (null != fileToUpload) {
					String separator = System.getProperty("file.separator");
					// ** 上传目录 *//*
					String baseDir = "E:/upload";
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
					saf.setContract(so);
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

	/** 获取明细json数据 */
	public void getContractSubjectJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract hr = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractSubject>(hr.getContractSubject()),hr.getContractSubject().size(), "contract");
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
	/** 获取明细json数据 */
	public void getContractPmJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract hr = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<PmContract>(hr.getPmContract()),hr.getContractSubject().size(), "contract");
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
	/** 获取明细json数据 */
	public void getContractMarketJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract hr = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractMarket>(hr.getContractMarket()),hr.getContractMarket().size(), "contract");
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

	/** 保存合同Tab页弹框页面*/
	public String saveOrUpdateContractSubjectDetails() {
		try {
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				contractSubject.setContract(contract);
				contractController.doSaveContractSubject(contractSubject);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}
	/** 保存合同Tab页弹框页面*/
	public String saveOrUpdatePmContractDetails() {
		try {
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				pmContract.setContract(contract);
				contractController.doSavePmContract(pmContract);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}
	/** 保存合同Tab页弹框页面*/
	public String saveOrUpdateMarket() {
		try {
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				contractMarket.setContract(contract);
				contractController.doSaveContractMarket(contractMarket);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 跳转到合同标的明细页面 */
	public String goAddContractSubject() {
		try {
			if (null != id && !"".equals(id)) {
				contractSubject = contractController.doListContractSubjectById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddContractSubject";
	}
	
	/** 获取合同采购定价条件json数据 */
	public void getContractSubjectJson1() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				Contract hr = contractController.findEntityById(id);
				json = convertListToJson(new ArrayList<ContractPricingConditions>(hr.getContractPricingConditions()),hr.getContractPricingConditions().size(), "contract");
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
	
	/** 跳转到采购定价条件明细页面 */
	public String goAddProcurementPricing() {
		try {
			if (null != id && !"".equals(id)) {
				contractPricingConditions = contractController.doListContractPricingConditionsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddProcurementPricing";
	}
	
	/** 处理删除tab采购定价条件明细操作 */
	public String deleteContractSubjectById1() {
		try {
			ContractPricingConditions contractPricingConditions = contractController.doListContractPricingConditionsById(id);
			if (null != contractPricingConditions) {
				contractController.deleteContractPricingConditionsEntity(contractPricingConditions);
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

	/**
	 * 保存采购定价条件弹框页面
	 */
	public String saveOrUpdateContractSubjectDetails1() {
		try {
			/*保存到合同的采购定价条件表*/
			if (null != id && !"".equals(id)) {
				contract = contractController.doListEntityById(id);
				contractPricingConditions.setContract(contract);
				contractController.doSaveContractSubject(contractPricingConditions);
			}
			boolean b = priceConditionService.addPriceCondition(contractPricingConditions.getItem().getId(),
				contractPricingConditions.getFrom(), contractPricingConditions.getTo(),
				contractPricingConditions.getPrice(),
				contractPricingConditions.getDiscount(),
				contractPricingConditions.getStartTime(),
				contractPricingConditions.getEndTime());
			if (b == true)
				setMessage("保存成功！");
			else {
				setMessage("保存失败！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}
	
	/** 跳转到销售定价条件明细页面 */
	public String goAddSalesPricing() {
		try {
			if (null != id && !"".equals(id)) {
				contractMarket = contractController.doListContractMarketById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddSalesPricing";
	}
	
	/** 跳转到项目合同明细页面 */
	public String goAddPmContract() {
		try {
			if (null != id && !"".equals(id)) {
				pmContract = contractController.doListPmContractById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddPmContract";
	}

	/** 处理删除tab明细操作 */
	public String deleteContractSubjectById() {
		try {
			ContractSubject contractSubject = contractController.doListContractSubjectById(id);
			if (null != contractSubject) {
				contractController.deleteContractSubjectEntity(contractSubject);
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
	/** 处理删除tab明细操作 */
	public String deletePmContractById() {
		try {
			PmContract pmContract = contractController.doListPmContractById(id);
			if (null != contractSubject) {
				contractController.deletePmContractEntity(pmContract);
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
	
	/** 处理删除tab明细操作 */
	public String deleteContractMarketById() {
		try {
			ContractMarket contractMarket = contractController.doListContractMarketById(id);
			if (null != contractSubject) {
				contractController.deleteContractMarketEntity(contractMarket);
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
	

	/** 处理删除tab合同项操作 */
	public String deleteContractLineById() {
		try {
			ContractLine contractLine = contractController.doListContractLineById(id);
			if (null != contractLine) {
				contractController.deleteContractLineEntity(contractLine);
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

	/** 处理删除tab合同预警操作 */
	public String deleteContractWarningById() {
		try {
			ContractWarning contractWarning = contractController.doListContractWarningById(id);
			if (null != contractWarning) {
				contractController.deleteContractWarningEntity(contractWarning);
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

	/** 处理删除tab合同审批操作 */
	public String deleteApplicationFormById() {
		try {
			ApplicationForm applicationForm = contractController.doListApplicationFormById(id);
			if (null != applicationForm) {
				contractController.deleteApplicationFormEntity(applicationForm);
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
	/** 处理删除tab价格条件 */
	public String deletePriceConditionsById() {
		try {
			PriceConditions priceConditions = contractController.doListPriceConditionsById(id);
			if (null != priceConditions) {
				contractController.deletePriceConditionsEntity(priceConditions);
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

	/** 跳转到快速新建供应商页面 */
	public String goAddQuicklySupplier() {
		return "goAddQuicklySupplier";
	}

	/** 跳转到快速新建客户页面 */
	public String goAddCustomer() {
		return "goAddCustomer";
	}

	/**
	 * 保存合同明细
	 * 
	 * @return
	 */
	public String saveOrUpdateSupplier() {
		try {
			supplier.setStatus("S3");
			contractController.doSaveSupplier(supplier);
			setMessage(SAVE_SUCCESS + "," + supplier.getCode() + "," + supplier.getName() + ",");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 客户信息
	 * 
	 * @return
	 */
	public String saveOrUpdateSupplier1() {
		try {
			contractController.doSaveSupplier(customerAccount);
			setMessage(SAVE_SUCCESS + "," + customerAccount.getCode() + "," + customerAccount.getName() + ",");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<ContractCatalog> listCategory = new ArrayList<ContractCatalog>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listCategory = baseHibernateService.findAllSubEntity(ContractCatalog.class, "parentCategory.id", id, params);
			}else{
				listCategory = baseHibernateService.findAllSubEntity(ContractCatalog.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listCategory.size();i++){
				ContractCatalog cc = listCategory.get(i);
				if(cc.getSubCategorys().size() > 0){
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				}else{
					strSb.append("{id:\"");
					strSb.append(cc.getId());
					strSb.append("\",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if(i < listCategory.size() -1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ContractSubject getContractSubject() {
		return contractSubject;
	}

	public void setContractSubject(ContractSubject contractSubject) {
		this.contractSubject = contractSubject;
	}

	public ContractMarket getContractMarket() {
		return contractMarket;
	}

	public void setContractMarket(ContractMarket contractMarket) {
		this.contractMarket = contractMarket;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<ContractTypeCombine> getContractTypeCombineList() {
		return contractTypeCombineList;
	}

	public void setContractTypeCombineList(
			List<ContractTypeCombine> contractTypeCombineList) {
		this.contractTypeCombineList = contractTypeCombineList;
	}


	public List<ContractNatureType> getContractNatureTypeList() {
		return contractNatureTypeList;
	}

	public void setContractNatureTypeList(
			List<ContractNatureType> contractNatureTypeList) {
		this.contractNatureTypeList = contractNatureTypeList;
	}

	public List<ViewIndustryType> getViewIndustryList() {
		return viewIndustryList;
	}

	public void setViewIndustryList(List<ViewIndustryType> viewIndustryList) {
		this.viewIndustryList = viewIndustryList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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

	public void setContractStageGroupTypeList(
			List<ContractStageGroupType> contractStageGroupTypeList) {
		this.contractStageGroupTypeList = contractStageGroupTypeList;
	}

	public List<ContractGroupType> getContractGroupTypeList() {
		return contractGroupTypeList;
	}

	public void setContractGroupTypeList(
			List<ContractGroupType> contractGroupTypeList) {
		this.contractGroupTypeList = contractGroupTypeList;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}


	public String addAttachFile() {
		return "addAttachFile";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getPriceConditionRuleId() {
		return priceConditionRuleId;
	}

	public void setPriceConditionRuleId(String priceConditionRuleId) {
		this.priceConditionRuleId = priceConditionRuleId;
	}

	public IPriceConditionService getPriceConditionService() {
		return priceConditionService;
	}

	public void setPriceConditionService(
			IPriceConditionService priceConditionService) {
		this.priceConditionService = priceConditionService;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getEntity() {
		return entity;
	}

	public void setEntity(Item entity) {
		this.entity = entity;
	}

	public ContractPricingConditions getContractPricingConditions() {
		return contractPricingConditions;
	}

	public void setContractPricingConditions(
			ContractPricingConditions contractPricingConditions) {
		this.contractPricingConditions = contractPricingConditions;
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public PmContract getPmContract() {
		return pmContract;
	}

	public void setPmContract(PmContract pmContract) {
		this.pmContract = pmContract;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

}

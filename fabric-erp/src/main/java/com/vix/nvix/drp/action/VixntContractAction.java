package com.vix.nvix.drp.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.contract.config.entity.ContractGroupType;
import com.vix.contract.config.entity.ContractNatureType;
import com.vix.contract.config.entity.ContractStageGroupType;
import com.vix.contract.config.entity.ContractTypeCombine;
import com.vix.contract.config.entity.EnableStageType;
import com.vix.contract.config.entity.ModeType;
import com.vix.contract.config.entity.ViewIndustryType;
import com.vix.contract.contractInquiry.entity.ContractChildItem;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.salechance.entity.BackSectionPlan;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntContractAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String contractId;
	private Contract contract;
	private String type;
	private ContractChildItem contractChildItem;//合同子项
	private ContractSubject contractSubject;//合同标的
	private ContractWarning contractWarning;//合同预警
	private ApplicationForm applicationForm;//合同审批
	private PriceConditions priceConditions;//价格条件
	private ContractTemplate contractTemplate;//附件
	private ContractPricingConditions contractPricingConditions;//采购定价条件
	private ContractMarket contractMarket;//销售定价条件
	private BackSectionPlan backSectionPlan;//回款计划
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
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("signDate");
			pager = vixntBaseService.findPagerByHqlConditions(pager, Contract.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			contractGroupTypeList = vixntBaseService.findAllByEntityClass(ContractGroupType.class);
			contractTypeCombineList = vixntBaseService.findAllByEntityClass(ContractTypeCombine.class);
			contractNatureTypeList = vixntBaseService.findAllByEntityClass(ContractNatureType.class);
			viewIndustryList = vixntBaseService.findAllByEntityClass(ViewIndustryType.class);
			modeTypeList = vixntBaseService.findAllByEntityClass(ModeType.class);
			enableStageTypeList = vixntBaseService.findAllByEntityClass(EnableStageType.class);
			contractStageGroupTypeList = vixntBaseService.findAllByEntityClass(ContractStageGroupType.class);
			exchangeRateList = vixntBaseService.findAllByEntityClass(ExchangeRate.class);
			if(StringUtils.isNotEmpty(id)){
				contract = vixntBaseService.findEntityById(Contract.class, id);
			}else{
				contract = new Contract();
				contract.setContractCode(VixUUID.createCode(12));
				contract.setContractType(Integer.parseInt(type));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(contract.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(contract);
			contract = vixntBaseService.merge(contract);
			if (isSave) {
				renderText("1:"+contract.getId()+":"+SAVE_SUCCESS);
			} else {
				renderText("1:"+contract.getId()+":"+UPDATE_SUCCESS);
			}
		}catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+UPDATE_FAIL);
			}
		}
	}
	
	/**
	 * 获取合同子项
	 */
	public void getcontractChildItemList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ContractChildItem.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增或修改合同子项
	 * @return
	 */
	public String goSaveOrUpdateContractChildItem(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				contractChildItem = vixntBaseService.findEntityById(ContractChildItem.class, id);
			}else{
				contractChildItem = new ContractChildItem();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract!= null){
						contractChildItem.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatecontractChildItem";
	}
	/**
	 * 保存合同子项
	 */
	public void saveOrUpdateContractChildItem(){
		try {
			if(contractChildItem != null){
				initEntityBaseController.initEntityBaseAttribute(contractChildItem);
				contractChildItem = vixntBaseService.merge(contractChildItem);
				renderText("1:"+SAVE_SUCCESS);
			}
		} catch (Exception e) {
			renderText("0:"+SAVE_FAIL);
			e.printStackTrace();
		}
	}
	/**
	 * 删除合同子项
	 */
	public void deleteContractChildItemById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contractChildItem = vixntBaseService.findEntityById(ContractChildItem.class, id);
				if(contractChildItem != null){
					vixntBaseService.deleteByEntity(contractChildItem);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/**
	 * 获取合同预警
	 */
	public void getContractWarningtList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ContractWarning.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增合同预警
	 * @return
	 */
	public String goSaveOrUpdateContractWarning(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				contractWarning = vixntBaseService.findEntityById(ContractWarning.class, id);
			}else{
				contractWarning = new ContractWarning();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract != null){
						contractWarning.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateContractWarning";
	}
	/**
	 * 保存合预警
	 */
	public void saveOrUpdateContractWarning(){
		try {
			if(contractWarning != null){
				initEntityBaseController.initEntityBaseAttribute(contractWarning);
				contractWarning = vixntBaseService.merge(contractWarning);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除合同预警
	 */
	public void deleteContractWarningById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contractWarning = vixntBaseService.findEntityById(ContractWarning.class, id);
				if(contractWarning != null){
					vixntBaseService.deleteByEntity(contractWarning);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取合同审批
	 */
	public void getApplicationFormList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ApplicationForm.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增合同审批
	 * @return
	 */
	public String goSaveOrUpdateApplicationForm(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				applicationForm = vixntBaseService.findEntityById(ApplicationForm.class, id);
			}else{
				applicationForm = new ApplicationForm();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract != null){
						applicationForm.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateApplicationForm";
	}
	/**
	 * 保存合同审批
	 */
	public void saveOrUpdateApplicationForm(){
		try {
			if(applicationForm != null){
				initEntityBaseController.initEntityBaseAttribute(applicationForm);
				applicationForm = vixntBaseService.merge(applicationForm);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除合同审批
	 */
	public void deleteApplicationFormById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				applicationForm = vixntBaseService.findEntityById(ApplicationForm.class, id);
				if(applicationForm != null){
					vixntBaseService.deleteByEntity(applicationForm);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取价格条件
	 */
	public void getPriceConditionsList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PriceConditions.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增价格条件
	 * @return
	 */
	public String goSaveOrUpdatePriceConditions(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				priceConditions = vixntBaseService.findEntityById(PriceConditions.class, id);
			}else{
				priceConditions = new PriceConditions();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract != null){
						priceConditions.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePriceConditions";
	}
	/**
	 * 保存价格条件
	 */
	public void saveOrUpdatePriceConditions(){
		try {
			if(priceConditions != null){
				initEntityBaseController.initEntityBaseAttribute(priceConditions);
				priceConditions = vixntBaseService.merge(priceConditions);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除价格条件
	 */
	public void deletePriceConditionsById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				priceConditions = vixntBaseService.findEntityById(PriceConditions.class, id);
				if(priceConditions != null){
					vixntBaseService.deleteByEntity(priceConditions);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取采购定价条件
	 */
	public void getContractPricingConditionsList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ContractPricingConditions.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增采购定价条件
	 * @return
	 */
	public String goSaveOrUpdateContractPricingConditions(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				contractPricingConditions = vixntBaseService.findEntityById(ContractPricingConditions.class, id);
			}else{
				contractPricingConditions = new ContractPricingConditions();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract != null){
						contractPricingConditions.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateContractPricingConditions";
	}
	/**
	 * 保存采购定价条件
	 */
	public void saveOrUpdateContractPricingConditions(){
		try {
			if(contractPricingConditions != null){
				initEntityBaseController.initEntityBaseAttribute(contractPricingConditions);
				contractPricingConditions = vixntBaseService.merge(contractPricingConditions);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除采购定价条件
	 */
	public void deleteContractPricingConditionsById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contractPricingConditions = vixntBaseService.findEntityById(ContractPricingConditions.class, id);
				if(contractPricingConditions != null){
					vixntBaseService.deleteByEntity(contractPricingConditions);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取合同标的
	 */
	public void getContractSubjectList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ContractSubject.class,params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增合同标的
	 * @return
	 */
	public String goSaveOrUpdateContractSubject(){
		try {
			if(StringUtils.isNotEmpty(id)&&!"undefined".equals(id)){
				contractSubject = vixntBaseService.findEntityById(ContractSubject.class, id);
			}else{
				contractSubject = new ContractSubject();
				if(StringUtils.isNotEmpty(contractId)){
					contract = vixntBaseService.findEntityById(Contract.class, contractId);
					if(contract != null){
						contractSubject.setContract(contract);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateContractSubject";
	}
	/**
	 * 保存合同标的
	 */
	public void saveOrUpdateContractSubject(){
		try {
			if(contractSubject != null){
				initEntityBaseController.initEntityBaseAttribute(contractSubject);
				contractSubject = vixntBaseService.merge(contractSubject);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contract = vixntBaseService.findEntityById(Contract.class, id);
				if(contract != null){
					vixntBaseService.deleteByEntity(contract);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	/**
	 * 删除合同标的
	 */
	public void deleteContractSubjectById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				contractSubject = vixntBaseService.findEntityById(ContractSubject.class, id);
				if(contractSubject != null){
					vixntBaseService.deleteByEntity(contractSubject);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void getContractTemplateList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			if(StringUtils.isNotEmpty(id)){
				params.put("contract.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ContractTemplate.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存附件
	 */
	public void saveOrUpdateAttachments() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				ContractTemplate contractTemplate = new ContractTemplate();
				contractTemplate.setName(savePathAndName[1].toString());
				contractTemplate.setPath("/img/ws/" + savePathAndName[1].toString());
				contractTemplate.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					contract = vixntBaseService.findEntityById(Contract.class, id);
					if (contract != null) {
						contractTemplate.setContract(contract);
					}
				}
				vixntBaseService.merge(contractTemplate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除附件
	 */
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				ContractTemplate contractTemplate = vixntBaseService.findEntityById(ContractTemplate.class, id);
				if (null != contractTemplate) {
					String fileName = contractTemplate.getName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(contractTemplate);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
	}
	/**
	 * 获取回款计划列表
	 */
	public void getBackSectionPlanSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, searchName);
			}
			if(StringUtils.isNotEmpty(contractId)){
				params.put("contract.id,"+SearchCondition.EQUAL, contractId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, BackSectionPlan.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateBackSectionPlan(){
		try {
			if(StringUtils.isNotEmpty(id)){
				backSectionPlan = vixntBaseService.findEntityById(BackSectionPlan.class, id);
			}else{
				backSectionPlan = new BackSectionPlan();
				backSectionPlan.setBackSectionPlanDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBackSectionPlan";
	}
	public void saveOrUpdateBackSectionPlan(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(backSectionPlan.getId())){
				isSave = false;
			}else{
				backSectionPlan.setCreateTime(new Date());
				loadCommonData(backSectionPlan);
			}
			String[] attrArray ={"customerAccount","crmProject","charger","owner"};
			checkEntityNullValue(backSectionPlan,attrArray);
			
			if(null != backSectionPlan.getCustomerAccount() && null != backSectionPlan.getCustomerAccount().getId() &&
					!backSectionPlan.getCustomerAccount().getId().equals("") && !backSectionPlan.getCustomerAccount().getId().equals("0")){
				CustomerAccount ca = baseHibernateService.findEntityById(CustomerAccount.class, backSectionPlan.getCustomerAccount().getId());
				String name = ca.getShorterName();
				String py = ChnToPinYin.getPYString(name);
				backSectionPlan.setChineseFirstLetter(py.toUpperCase());
			}
			backSectionPlan = baseHibernateService.merge(backSectionPlan);
			// 增加行动历史和客户更新
			if(backSectionPlan.getCustomerAccount() != null && StrUtils.isNotEmpty(backSectionPlan.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, backSectionPlan.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("回款计划");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(backSectionPlan.getCrmProject());
				actionHistory.setDescription("回款计划编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
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
	}
	/** 处理删除操作 */
	public void deleteBackSectionPlanById() {
		try {
			BackSectionPlan pb = baseHibernateService.findEntityById(BackSectionPlan.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/** 选择客户列表 */
	public void chooseCustomer() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			params.put("type,"+SearchCondition.EQUAL, "enterPrise");
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			String code = getDecodeRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goChooseCustomer(){
		return "goChooseCustomer";
	}
	public String goChooseProject() {
		return "goChooseProject";
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ContractChildItem getContractChildItem() {
		return contractChildItem;
	}

	public void setContractChildItem(ContractChildItem contractChildItem) {
		this.contractChildItem = contractChildItem;
	}

	public ContractSubject getContractSubject() {
		return contractSubject;
	}

	public void setContractSubject(ContractSubject contractSubject) {
		this.contractSubject = contractSubject;
	}

	public ContractWarning getContractWarning() {
		return contractWarning;
	}

	public void setContractWarning(ContractWarning contractWarning) {
		this.contractWarning = contractWarning;
	}

	public PriceConditions getPriceConditions() {
		return priceConditions;
	}

	public void setPriceConditions(PriceConditions priceConditions) {
		this.priceConditions = priceConditions;
	}

	public ContractTemplate getContractTemplate() {
		return contractTemplate;
	}

	public void setContractTemplate(ContractTemplate contractTemplate) {
		this.contractTemplate = contractTemplate;
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

	public ApplicationForm getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public ContractPricingConditions getContractPricingConditions() {
		return contractPricingConditions;
	}

	public void setContractPricingConditions(ContractPricingConditions contractPricingConditions) {
		this.contractPricingConditions = contractPricingConditions;
	}

	public ContractMarket getContractMarket() {
		return contractMarket;
	}

	public void setContractMarket(ContractMarket contractMarket) {
		this.contractMarket = contractMarket;
	}

	public BackSectionPlan getBackSectionPlan() {
		return backSectionPlan;
	}

	public void setBackSectionPlan(BackSectionPlan backSectionPlan) {
		this.backSectionPlan = backSectionPlan;
	}
}

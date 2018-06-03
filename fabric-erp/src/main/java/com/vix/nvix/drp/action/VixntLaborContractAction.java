package com.vix.nvix.drp.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 劳动合同管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntLaborContractAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private String id;
	private String contractId;
	private Contract contract;
	private ContractChildItem contractChildItem;//合同子项
	private ContractWarning contractWarning;//合同预警
	private ApplicationForm applicationForm;//合同审批
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
	
	private ContractTemplate contractTemplate;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("contractType,"+SearchCondition.EQUAL, 6);
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
				contract.setContractCode(sdf.format(new Date()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(contract.getId())){
				isSave = false;
			}
			contract.setContractType(6);
			initEntityBaseController.initEntityBaseAttribute(contract);
			contract = vixntBaseService.merge(contract);
			if (isSave) {
				renderText("1:"+contract.getId()+SAVE_SUCCESS);
			} else {
				renderText("1:"+contract.getId()+UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+UPDATE_FAIL);
			}
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
	public ContractChildItem getContractChildItem() {
		return contractChildItem;
	}
	public void setContractChildItem(ContractChildItem contractChildItem) {
		this.contractChildItem = contractChildItem;
	}
	public ContractWarning getContractWarning() {
		return contractWarning;
	}
	public void setContractWarning(ContractWarning contractWarning) {
		this.contractWarning = contractWarning;
	}
	public ApplicationForm getApplicationForm() {
		return applicationForm;
	}
	public void setApplicationForm(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
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

	public ContractTemplate getContractTemplate() {
		return contractTemplate;
	}

	public void setContractTemplate(ContractTemplate contractTemplate) {
		this.contractTemplate = contractTemplate;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}

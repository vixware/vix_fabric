package com.vix.contract.contractInquiry.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.contract.contractInquiry.domain.ContractInquiryDomain;
import com.vix.contract.contractInquiry.entity.ContractChildItem;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.Supplier;

@Scope("prototype")
@Controller("contractInquirycontroller")
public class ContractInquiryController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("ContractController");
	@Autowired
	private ContractInquiryDomain contractInquiryDomain;

	public ContractInquiryDomain getContractInquiryDomain() {
		return contractInquiryDomain;
	}

	public void setContractInquiryDomain(ContractInquiryDomain contractInquiryDomain) {
		this.contractInquiryDomain = contractInquiryDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		if (params.containsKey("name,anylike")) {
			if ("undefined".equals(params.get("name,anylike"))) {
				params.remove("name,anylike");
			}
		}
		Pager p = contractInquiryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public ContractInquiry findEntityById(String id) throws Exception {
		return contractInquiryDomain.findEntityById(id);
	}

	public UserAccount findUserAccountById(String id) throws Exception {
		return contractInquiryDomain.findUserAccountById(id);
	}

	public ContractInquiry merge(ContractInquiry contractInquiry) throws Exception {
		contractInquiryDomain.merge(contractInquiry);
		return null;
	}

	public void deleteByEntity(ContractInquiry contractInquiry) throws Exception {
		contractInquiryDomain.deleteByEntity(contractInquiry);
	}

	public void deleteContractLineEntity(ContractChildItem childItem) throws Exception {
		contractInquiryDomain.deleteContractLineEntity(childItem);
	}

	public void deleteContractSubjectEntity(ContractSubject subject) throws Exception {
		contractInquiryDomain.deleteContractSubjectEntity(subject);
	}

	public void deleteContractWarningEntity(ContractWarning warning) throws Exception {
		contractInquiryDomain.deleteContractWarningEntity(warning);
	}

	public void deletePriceConditionsEntity(PriceConditions conditions) throws Exception {
		contractInquiryDomain.deletePriceConditionsEntity(conditions);
	}

	public void deleteContractPricingConditionsEntity(ContractPricingConditions pricingConditions) throws Exception {
		contractInquiryDomain.deleteContractPricingConditionsEntity(pricingConditions);
	}

	public void deleteContractMarketEntity(ContractMarket contractMarket) throws Exception {
		contractInquiryDomain.deleteContractMarketEntity(contractMarket);
	}

	public void deleteApplicationFormEntity(ApplicationForm application) throws Exception {
		contractInquiryDomain.deleteApplicationFormEntity(application);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		contractInquiryDomain.deleteByIds(ids);
	}

	public List<ContractInquiry> findContractIndex() throws Exception {
		return contractInquiryDomain.findContractIndex();
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
	}

	/** 获取项目列表数据 */
	public Pager goProjectName(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = contractInquiryDomain.findProjectName(params, pager);
		return p;
	}

	/** 获取供应商列表数据 */
	public Pager goCustomerAccount(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = contractInquiryDomain.findCustomerAccount(params, pager);
		return p;
	}

	/**
	 * doSaveExecute实现保存供应商的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public Supplier doSaveSupplier(Supplier supplier) {
		Supplier supplierTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			supplierTemp = contractInquiryDomain.merge(supplier);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存供应商：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存供应商：" + supplier + "失败！失败原因：" + ex.getMessage());
		}
		return supplierTemp;
	}

	/** 获取合同查询 */
	public ContractInquiry doListEntityById(String id) {
		ContractInquiry contractInquiry = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			contractInquiry = contractInquiryDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同查询成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同查询失败！" + "失败原因:" + ex.getMessage());
		}

		return contractInquiry;
	}

	/** 获取合同子项明细 */
	public ContractChildItem doListContractLineById(String id) {
		ContractChildItem childItem = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			childItem = contractInquiryDomain.findContractLineById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同子项成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同子项失败！" + "失败原因:" + ex.getMessage());
		}

		return childItem;
	}

	/** 获取合同标的明细 */
	public ContractSubject doListContractSubjectById(String id) {
		ContractSubject childItem = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			childItem = contractInquiryDomain.findContractSubjectById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同标的成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同标的失败！" + "失败原因:" + ex.getMessage());
		}

		return childItem;
	}

	/** 获取合同预警明细 */
	public ContractWarning doListContractWarningById(String id) {
		ContractWarning warning = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			warning = contractInquiryDomain.findContractWarningById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同预警成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同预警失败！" + "失败原因:" + ex.getMessage());
		}

		return warning;
	}

	/** 获取价格条件明细 */
	public PriceConditions doListPriceConditionsById(String id) {
		PriceConditions conditions = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			conditions = contractInquiryDomain.findPriceConditionsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询价格条件成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询价格条件失败！" + "失败原因:" + ex.getMessage());
		}

		return conditions;
	}

	/** 获取采购价格条件明细 */
	public ContractPricingConditions doListContractPricingConditionsById(String id) {
		ContractPricingConditions pricingConditions = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			pricingConditions = contractInquiryDomain.findContractPricingConditionsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询采购价格条件成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询采购价格条件失败！" + "失败原因:" + ex.getMessage());
		}

		return pricingConditions;
	}

	/** 获取销售价格条件明细 */
	public ContractMarket doListContractMarketById(String id) {
		ContractMarket contractMarket = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			contractMarket = contractInquiryDomain.findContractMarketById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询采购价格条件成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询采购价格条件失败！" + "失败原因:" + ex.getMessage());
		}

		return contractMarket;
	}

	/** 获取合同审批明细 */
	public ApplicationForm doListApplicationFormById(String id) {
		ApplicationForm application = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			application = contractInquiryDomain.findApplicationFormById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同审批成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同审批失败！" + "失败原因:" + ex.getMessage());
		}

		return application;
	}

	// 处理保存方法
	public ContractInquiry doSaveContract(ContractInquiry contractInquiry) {
		ContractInquiry contract2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contract2 = contractInquiryDomain.merge(contractInquiry);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同查询：" + contractInquiry + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同查询：" + contractInquiry + "失败！失败原因：" + ex.getMessage());
		}
		return contract2;
	}

	// 合同子项明细
	public ContractChildItem doSaveContractLine(ContractChildItem childItem) throws Exception {
		ContractChildItem contractChildItems = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractChildItems = contractInquiryDomain.merge(childItem);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同子项：" + childItem + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同子项：" + childItem + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同子项失败", ex);
		}
		return contractChildItems;
	}

	// 合同标的明细
	public ContractSubject doSaveContractSubject(ContractSubject subject) throws Exception {
		ContractSubject contractSubjects = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractSubjects = contractInquiryDomain.merge(subject);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同标的：" + subject + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同标的：" + subject + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同标的失败", ex);
		}
		return contractSubjects;
	}

	// 合同预警明细
	public ContractWarning doSaveContractWarning(ContractWarning warning) throws Exception {
		ContractWarning contractWarning = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractWarning = contractInquiryDomain.merge(warning);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同预警：" + warning + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同预警：" + warning + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同预警失败", ex);
		}
		return contractWarning;
	}

	// 合同审批明细
	public ApplicationForm doSaveApplicationForm(ApplicationForm applications) throws Exception {
		ApplicationForm applicationForm = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			applicationForm = contractInquiryDomain.merge(applications);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同审批：" + applications + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同审批：" + applications + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同审批失败", ex);
		}
		return applicationForm;
	}

	// 价格条件明细
	public PriceConditions doSavePriceConditions(PriceConditions price) throws Exception {
		PriceConditions priceConditions = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			priceConditions = contractInquiryDomain.merge(price);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存价格条件：" + price + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存价格条件：" + price + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存价格条件失败", ex);
		}
		return priceConditions;
	}

	// 采购价格条件明细
	public ContractPricingConditions doSaveContractPricingConditions(ContractPricingConditions pricingConditions) throws Exception {
		ContractPricingConditions contractPricingConditions = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractPricingConditions = contractInquiryDomain.merge(pricingConditions);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存采购价格条件：" + pricingConditions + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存采购价格条件：" + pricingConditions + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存采购价格条件失败", ex);
		}
		return contractPricingConditions;
	}

	// 销售价格条件明细
	public ContractMarket doSaveContractMarket(ContractMarket market) throws Exception {
		ContractMarket contractMarket = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contractMarket = contractInquiryDomain.merge(market);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存销售价格条件：" + market + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存销售价格条件：" + market + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存销售价格条件失败", ex);
		}
		return contractMarket;
	}

	public ContractInquiry doSaveContract(ContractInquiry contractInquiry, List<ContractChildItem> dlList) throws Exception {

		ContractInquiry contract2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != contractInquiry) {
				contract2 = contractInquiryDomain.merge(contractInquiry);
				if (null != dlList) {
					for (ContractChildItem da : dlList) {
						if (null != da) {
							da.setContractInquiry(contract2);
							contractInquiryDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同查询：" + contractInquiry + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存合同查询：" + contractInquiry + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同查询失败", ex);
		}
		return contract2;

	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = contractInquiryDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = contractInquiryDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同查询成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同查询失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(ContractInquiry contractInquiry) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			contractInquiryDomain.deleteByEntity(contractInquiry);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同查询成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同查询失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除合同查询的业务逻辑处理
	 * 
	 * @param ids
	 */
	public void doDeleteByIds(List<String> ids) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			contractInquiryDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同查询成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同查询失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

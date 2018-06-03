package com.vix.contract.mamanger.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.contract.mamanger.domain.ContractDomain;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.contract.mamanger.entity.ContractAssociateTemplate;
import com.vix.contract.mamanger.entity.ContractLine;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PmContract;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.contract.mamanger.entity.ProtocolTemplete;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @ClassName: ContractController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("contractController")
@Scope("prototype")
public class ContractController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ContractController");

	@Autowired
	private ContractDomain contractDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSingleList3(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractDomain.findPagerByHqlConditions3(params, pager);
		return p;
	}

	public Contract findEntityById(String id) throws Exception {
		return contractDomain.findEntityById(id);
	}
	
	public Contract findEntityById1(String id) throws Exception{
		return  contractDomain.findEntityById(id);
	}
	
	public ContractAssociateTemplate findEntityById2(String id) throws Exception {
		return contractDomain.findEntityById2(id);
	}
	public ProtocolTemplete findEntityById3(String id) throws Exception {
		return contractDomain.findEntityById3(id);
	}

	/**
	 * doSaveExecute实现保存合同的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public Contract doSaveSalesOrder(Contract contract /*Date warnningTime*/)
			throws Exception {
		Contract contract1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			contract.setUpdateTime(new Date());
			// 3. 执行保存操作
			contract1 = contractDomain.merge(contract);
		/*	ContractWarning contractWarning = new ContractWarning();
			if (warnningTime != null) {

				contractWarning.setWarnningTime(warnningTime);
			}
			contractWarning.setWarnningContractCode(contract.getCode());
			contractWarning.setContractName(contract.getName());
			contractDomain.merge(contractWarning);*/
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同：" + contract + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存合同：" + contract + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同", ex);
		}
		return contract1;
	}

	public Contract doSaveSalesOrder1(Contract contract,
			List<ContractLine> dlList, List<ContractWarning> dpList,List<ApplicationForm> spList,List<PriceConditions> jgList
			/*Date warnningTime*/) throws Exception {
		Contract contract1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			contract.setUpdateTime(new Date());
			// 3. 执行保存操作
			contract1 = contractDomain.merge(contract);
			for (ContractLine contractLine : dlList) {
				//合同子项关联合同的ID
				contractLine.setContract(contract1);
				contractDomain.merge(contractLine);
			}
			for (ContractWarning contractWarning1 : dpList) {
				contractWarning1.setContract(contract1);					
				contractDomain.merge(contractWarning1);
			}
			for (ApplicationForm applicationForm : spList) {
				applicationForm.setContract(contract1);	
				contractDomain.merge(applicationForm);
			}
			for (PriceConditions priceConditions : jgList) {
				priceConditions.setContract(contract1);	
				contractDomain.merge(priceConditions);
			}

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存合同：" + contract + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存合同：" + contract + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存合同", ex);
		}
		return contract1;
	}

	/**
	 * doSaveExecute实现获取合同列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListSalesOrder(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = contractDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同信息失败！失败原因：" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现删除合同的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByEntity(Contract contract) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			contractDomain.deleteByEntity(contract);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同信息失败！失败原因：" + ex.getMessage());
		}
	}

	
	 /** doSaveExecute实现删除合同的业务逻辑处理
	 * 
	 * @param ids*/
	 
	public void doDeleteByIds(List<String> ids) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			contractDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除合同信息失败！" + "失败原因:" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现获取合同列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = contractDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	public ContractTemplate findContractTemplateEntityById(String id) throws Exception{
		return  contractDomain.findContractTemplateEntityById(id);
	}
	
	public void deleteContractTemplateEntity(ContractTemplate contractTemplate) throws Exception{
		contractDomain.deleteContractTemplateEntity(contractTemplate);
	}
	
	

	/**
	 * doSaveExecute实现保存合同业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Contract doListEntityById(String id) throws Exception {
		Contract contract = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			contract = contractDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同信息失败！" + "失败原因:" + ex.getMessage());
		}

		return contract;
	}
	
	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 */
	public Contract doSavePurchaseOrder(Contract contract12) {
		Contract contract112 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			contract112 = contractDomain.merge(contract12);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + contract12 + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存订单：" + contract12 + "失败！失败原因：" + ex.getMessage());
		}
		return contract112;
	}
	
	/**
	 * doSaveExecute实现保存合同业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Contract doListEntityById1(String id) throws Exception {
		Contract contract = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			contract = contractDomain.findEntityById1(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同信息失败！" + "失败原因:" + ex.getMessage());
		}

		return contract;
	}
	
	/** 获取合同标的明细*/
	public ContractSubject doListContractSubjectById(String id) {
		ContractSubject detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = contractDomain.findContractSubjectById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return detailsTemp;
	}
	/** 获取项目合同明细*/
	public PmContract doListPmContractById(String id) {
		PmContract detailsTemp = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp = contractDomain.findPmContractById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		
		return detailsTemp;
	}
	/** 获取销售定价条件 明细*/
	public ContractMarket doListContractMarketById(String id) {
		ContractMarket detailsTemp = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp = contractDomain.findContractMarketById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		
		return detailsTemp;
	}
	/** 获取合同定价明细*/
	public ContractPricingConditions doListContractPricingConditionsById(String id) {
		ContractPricingConditions detailsTemp = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp = contractDomain.findContractPricingConditionsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		
		return detailsTemp;
	}
	/** 删除时获取合同项明细*/
	public ContractLine doListContractLineById(String id) {
		ContractLine detailsTemp = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp = contractDomain.findContractLineById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return detailsTemp;
	}
	
	/** 删除时获取合同预警明细*/
	public ContractWarning doListContractWarningById(String id) {
		ContractWarning detailsTemp1 = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp1 = contractDomain.findContractWarningById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return detailsTemp1;
	}
	/** 删除时获取合同审批明细*/
	public ApplicationForm doListApplicationFormById(String id) {
		ApplicationForm detailsTemp2 = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp2 = contractDomain.findApplicationFormById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return detailsTemp2;
	}
	/** 删除时获取价格条件*/
	public PriceConditions doListPriceConditionsById(String id) {
		PriceConditions detailsTemp3 = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			detailsTemp3 = contractDomain.findPriceConditionsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return detailsTemp3;
	}
	//合同标的
		public ContractSubject doSaveContractSubject(ContractSubject contractSubject) throws Exception {
			ContractSubject contractSubject2 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();

				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3. 执行保存操作
				contractSubject2 = contractDomain.merge(contractSubject);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存：" + contractSubject + "成功！");
			} catch (Exception ex) {
				executeLogger(true, "保存：" + contractSubject + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存失败", ex);
			}
			return contractSubject2;
		}
		//项目合同
		public PmContract doSavePmContract(PmContract pmContract) throws Exception {
			PmContract pmContract2 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3. 执行保存操作
				pmContract2 = contractDomain.merge(pmContract);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存：" + pmContract + "成功！");
			} catch (Exception ex) {
				executeLogger(true, "保存：" + pmContract + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存失败", ex);
			}
			return pmContract2;
		}
		//销售定价条件
		public ContractMarket doSaveContractMarket(ContractMarket contractMarket) throws Exception {
			ContractMarket contractMarket1 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3. 执行保存操作
				contractMarket1 = contractDomain.merge(contractMarket);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存：" + contractMarket + "成功！");
			} catch (Exception ex) {
				executeLogger(true, "保存：" + contractMarket + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存失败", ex);
			}
			return contractMarket1;
		}
		
		//合同定价条件
		public ContractPricingConditions doSaveContractSubject(ContractPricingConditions contractPricingConditions) throws Exception {
			ContractPricingConditions contractPricingConditions2 = null;
			try {
				// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
				// Map parameters = baExecutor.executeParameterProcess();
				
				// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
				beforeEventTrigger("PO_CREATE_BEFORE");
				// 3. 执行保存操作
				contractPricingConditions2 = contractDomain.merge(contractPricingConditions);
				// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
				afterEventTrigger("PO_CREATE_AFTER");
				// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
				// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
				executeLogger(true, "保存：" + contractPricingConditions + "成功！");
			} catch (Exception ex) {
				executeLogger(true, "保存：" + contractPricingConditions + "失败！失败原因：" + ex.getMessage());
				throw new Exception("保存失败", ex);
			}
			return contractPricingConditions2;
		}
		
		public void deleteContractSubjectEntity(ContractSubject contractSubject) throws Exception {
			contractDomain.deleteContractSubjectEntity(contractSubject);
		}
		public void deletePmContractEntity(PmContract pmContract) throws Exception {
			contractDomain.deletePmContractEntity(pmContract);
		}
		public void deleteContractMarketEntity(ContractMarket contractMarket) throws Exception {
			contractDomain.deleteContractMarketEntity(contractMarket);
		}
		public void deleteContractPricingConditionsEntity(ContractPricingConditions contractPricingConditions) throws Exception {
			contractDomain.deleteContractPricingConditionsEntity(contractPricingConditions);
		}
		public void deleteContractLineEntity(ContractLine contractLine) throws Exception {
			contractDomain.deleteContractLineEntity(contractLine);
		}
		public void deleteContractWarningEntity(ContractWarning contractWarning) throws Exception {
			contractDomain.deleteContractWarningEntity(contractWarning);
		}
		public void deleteApplicationFormEntity(ApplicationForm applicationForm) throws Exception {
			contractDomain.deleteApplicationFormEntity(applicationForm);
		}
		public void deletePriceConditionsEntity(PriceConditions priceConditions) throws Exception {
			contractDomain.deletePriceConditionsEntity(priceConditions);
		}

	/**
	 * doSaveExecute实现查询合同的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Contract> doListSalesOrderIndex() {
		List<Contract> contract = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			contract = contractDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询合同信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询合同信息失败！" + "失败原因:" + ex.getMessage());
		}
		return contract;
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
			supplierTemp = contractDomain.merge(supplier);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + supplier + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + supplier + "失败！失败原因："
					+ ex.getMessage());
		}
		return supplierTemp;
	}
	
	/**
	 * doSaveExecute实现保存客户的业务逻辑处理
	 * 
	 * @param salesOrder
	 * @return
	 */
	public CustomerAccount doSaveSupplier(CustomerAccount customerAccount) {
		CustomerAccount customerAccountTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			customerAccountTemp = contractDomain.merge(customerAccount);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + customerAccount + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + customerAccount + "失败！失败原因："
					+ ex.getMessage());
		}
		return customerAccountTemp;
	}
	
	public ContractTemplate mergeContractTemplate(ContractTemplate contractTemplate) throws Exception{
		return contractDomain.merge(contractTemplate);
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
	 * 异常错误日志方便程序员调试
	 * 
	 * @param message
	 */
	public void executeErrorLogger(String... errorMessage) {
	}

	/**
	 * bizFlowExecute 需要对流程进行操作时调用
	 * 
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName,
			Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName,
			Map<String, Object> flowParameter) {
		// to do something

	}

	/**
 * 
 */
	public void doProcessEvent() {
		// to do something

	}

	/**
	 * 
	 */
	public void doExecute() {
		// to do something
	}

	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction() {
		return null;
	}

	/**
	 * 
	 */
	public void doPrint() {
		// to do something
	}

	/**
	 * 
	 */
	public void doDrools() {
		// to do something
	}
}

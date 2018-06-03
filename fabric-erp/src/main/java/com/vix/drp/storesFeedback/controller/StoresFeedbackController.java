package com.vix.drp.storesFeedback.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.policyfeedback.entity.PolicyInformation;
import com.vix.drp.storesFeedback.domain.StoresFeedbackDomain;

@Controller("storesFeedbackController")
@Scope("prototype")
public class StoresFeedbackController {

	Logger logger = Logger.getLogger(StoresFeedbackController.class);

	@Autowired
	private StoresFeedbackDomain storesFeedbackDomain;

	/**
	 * 
	 * @param policyInformation
	 * @return
	 * @throws Exception
	 */
	public PolicyInformation doSavePolicyInformation(PolicyInformation policyInformation) throws Exception {
		PolicyInformation policyInformation1 = new PolicyInformation();
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		policyInformation1 = storesFeedbackDomain.saveOrUpdatePolicyInformation(policyInformation);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return policyInformation1;
	}

	//Controller
	public void doDeleteByEntity(PolicyInformation policyInformation) throws Exception {
		storesFeedbackDomain.deleteByEntity(policyInformation);
	}

	public void doDeleteByIds(List<String> ids) throws Exception {
		storesFeedbackDomain.deleteByIds(ids);
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager findPolicyInformationPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = storesFeedbackDomain.findPolicyInformationPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public List<PolicyInformation> doListPolicyInformationList(Map<String, Object> params) throws Exception {
		List<PolicyInformation> policyInformationList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		policyInformationList = storesFeedbackDomain.findPolicyInformationList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return policyInformationList;
	}

	/**
	 * 
	 * @param policyInformation
	 * @throws Exception
	 */
	public void doDeletePolicyInformation(PolicyInformation policyInformation) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		storesFeedbackDomain.deleteByEntity(policyInformation);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyInformation doListPolicyInformationById(String id) throws Exception {
		PolicyInformation policyInformation = new PolicyInformation();
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		policyInformation = storesFeedbackDomain.findPolicyInformationById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return policyInformation;
	}

	public ChannelDistributor doListChannelDistributorById(String id) throws Exception {
		ChannelDistributor channelDistributor = new ChannelDistributor();
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		channelDistributor = storesFeedbackDomain.findChannelDistributorById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return channelDistributor;
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
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
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

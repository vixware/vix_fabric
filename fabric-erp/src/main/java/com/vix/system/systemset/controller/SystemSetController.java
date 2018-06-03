/**
 * 
 */
package com.vix.system.systemset.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.mail.entity.PersonalEmail;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.system.systemset.domain.SystemSetDomain;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("systemSetController")
@Scope("prototype")
public class SystemSetController {

	Logger logger = Logger.getLogger(SystemSetController.class);

	@Autowired
	private SystemSetDomain systemSetDomain;

	/**
	 * 
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public PersonalEmail doSavePersonalEmail(PersonalEmail personalEmail) throws Exception {
		PersonalEmail personalEmail1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		personalEmail1 = systemSetDomain.mergePersonalEmail(personalEmail);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return personalEmail1;
	}

	public UserAccount doSaveUserAccount(UserAccount userAccount) throws Exception {
		UserAccount userAccount1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		userAccount1 = systemSetDomain.mergeUserAccount(userAccount);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return userAccount1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListPersonalEmail(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 执行保存操作
		p = systemSetDomain.findPersonalEmailPager(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param personalEmail
	 * @throws Exception
	 */
	public void doDeleteByEntity(PersonalEmail personalEmail) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		systemSetDomain.deleteByEntity(personalEmail);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void doDeleteModuleCategoryByIds(List<String> ids) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		systemSetDomain.deleteModuleCategoryByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PersonalEmail doListPersonalEmailById(String id) throws Exception {
		PersonalEmail personalEmail = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		personalEmail = systemSetDomain.findPersonalEmailById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return personalEmail;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<PersonalEmail> doListPersonalEmailIndex() throws Exception {
		List<PersonalEmail> personalEmail = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		personalEmail = systemSetDomain.findPersonalEmailIndex();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return personalEmail;
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

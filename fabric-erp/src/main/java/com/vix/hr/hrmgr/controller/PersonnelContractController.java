package com.vix.hr.hrmgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.domain.PersonnelContractDomain;
import com.vix.hr.hrmgr.entity.PersonnelContract;
import com.vix.hr.job.entity.HrAttachments;

@Scope("prototype")
@Controller("personnelcontractcontroller")
public class PersonnelContractController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("PersonnelContractController");
	@Autowired
	private PersonnelContractDomain personnelContractDomain;

	public PersonnelContractDomain getPersonnelContractDomain() {
		return personnelContractDomain;
	}

	public void setPersonnelContractDomain(PersonnelContractDomain personnelContractDomain) {
		this.personnelContractDomain = personnelContractDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = personnelContractDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public PersonnelContract findEntityById(String id) throws Exception {
		return personnelContractDomain.findEntityById(id);
	}

	public PersonnelContract merge(PersonnelContract personnelContract) throws Exception {
		personnelContractDomain.merge(personnelContract);
		return null;
	}

	public void deleteByEntity(PersonnelContract personnelContract) throws Exception {
		personnelContractDomain.deleteByEntity(personnelContract);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		personnelContractDomain.deleteByIds(ids);
	}

	public List<PersonnelContract> findPersonnelContractIndex() throws Exception {
		return personnelContractDomain.findPersonnelContractIndex();
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

	// 删除
	public PersonnelContract doListEntityById(String id) {

		PersonnelContract personnelContract = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			personnelContract = personnelContractDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return personnelContract;
	}

	// 保存
	public PersonnelContract doSavePersonnelContract(PersonnelContract personnelContract) throws Exception {
		PersonnelContract personnelContract2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			personnelContract2 = personnelContractDomain.merge(personnelContract);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + personnelContract + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + personnelContract + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return personnelContract2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = personnelContractDomain.findPagerByOrHqlConditions(params, pager);
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
			p = personnelContractDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	public void doDeleteByEntity(PersonnelContract personnelContract) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			personnelContractDomain.deleteByEntity(personnelContract);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
	}

	/**
	 * 
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
			personnelContractDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return personnelContractDomain.findHrAttachmentsEntityById(id);
	}
	public HrAttachments mergeAttachments(HrAttachments attachments) throws Exception {
		return personnelContractDomain.merge(attachments);
	}
	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		personnelContractDomain.deleteHrAttachmentsEntity(attachments);
	}
}

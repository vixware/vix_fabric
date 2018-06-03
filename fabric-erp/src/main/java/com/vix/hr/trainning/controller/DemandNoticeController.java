package com.vix.hr.trainning.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.DemandNoticeDomain;
import com.vix.hr.trainning.entity.DemandNotice;

@Scope("prototype")
@Controller("trainingdemandacquisitionnoticecontroller")
public class DemandNoticeController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("DemandNoticeController");
	@Autowired
	private DemandNoticeDomain demandNoticeDomain;

	public DemandNoticeDomain getDemandNoticeDomain() {
		return demandNoticeDomain;
	}

	public void setDemandNoticeDomain(DemandNoticeDomain demandNoticeDomain) {
		this.demandNoticeDomain = demandNoticeDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandNoticeDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public DemandNotice findEntityById(String id) throws Exception {
		return demandNoticeDomain.findEntityById(id);
	}

	public DemandNotice merge(DemandNotice demandNotice) throws Exception {
		demandNoticeDomain.merge(demandNotice);
		return null;
	}

	public void deleteByEntity(DemandNotice demandNotice) throws Exception {
		demandNoticeDomain.deleteByEntity(demandNotice);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		demandNoticeDomain.deleteByIds(ids);
	}

	public List<DemandNotice> findDemandNoticeIndex() throws Exception {
		return demandNoticeDomain.findDemandNoticeIndex();
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
	public DemandNotice doListEntityById(String id) {

		DemandNotice demandNotice = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			demandNotice = demandNoticeDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询需求通知信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询需求通知信息失败！" + "失败原因:" + ex.getMessage());
		}

		return demandNotice;
	}

	// 保存
	public DemandNotice doSaveDemandNotice(DemandNotice demandNotice) throws Exception {
		DemandNotice demandNotice2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			demandNotice2 = demandNoticeDomain.merge(demandNotice);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存需求通知：" + demandNotice + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存需求通知：" + demandNotice + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return demandNotice2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandNoticeDomain.findPagerByOrHqlConditions(params, pager);
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
			p = demandNoticeDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询需求通知信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询需求通知信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(DemandNotice demandNotice) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			demandNoticeDomain.deleteByEntity(demandNotice);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除需求通知信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除需求通知信息失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除需求通知的业务逻辑处理
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
			demandNoticeDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除需求通知信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除需求通知信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

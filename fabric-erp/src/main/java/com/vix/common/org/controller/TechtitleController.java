package com.vix.common.org.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.domain.TechtitleDomain;
import com.vix.common.org.entity.Techtitle;
import com.vix.hr.organization.entity.Employee;

@Controller("techtitleController")
@Scope("prototype")
public class TechtitleController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("TechtitleController");

	@Autowired
	private TechtitleDomain techtitleDomain;

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

	public void deletTechtitleEntity(Techtitle techtitle) throws Exception {
		techtitleDomain.deleteTechtitleEntity(techtitle);
	}

	public Employee doListEmployee(String id) {
		Employee employee = new Employee();

		try {
			employee = techtitleDomain.findEmployeeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	/** 获取专业技术职务明细 */
	public Techtitle doListTechtitleById(String id) {
		Techtitle techtitle = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			techtitle = techtitleDomain.findTechtitleById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return techtitle;
	}

	// 专业技术职务明细
	public Techtitle doSaveTechtitle(Techtitle techtitle) throws Exception {
		Techtitle techtitle2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			techtitle2 = techtitleDomain.merge(techtitle);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + techtitle + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + techtitle + "失败！失败原因：" + ex.getMessage());

		}
		return techtitle2;
	}
}

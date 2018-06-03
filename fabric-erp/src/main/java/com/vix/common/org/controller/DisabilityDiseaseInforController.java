package com.vix.common.org.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.domain.DisabilityDiseaseInforDomain;
import com.vix.common.org.entity.DisabilityDiseaseInfor;
import com.vix.hr.organization.entity.Employee;

@Controller("disabilitydiseaseinforController")
@Scope("prototype")
public class DisabilityDiseaseInforController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("DisabilityDiseaseInforController");

	@Autowired
	private DisabilityDiseaseInforDomain disabilityDiseaseInforDomain;

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

	public void deleteDisabilityDiseaseInforEntity(DisabilityDiseaseInfor disabilityDiseaseInfor) throws Exception {
		disabilityDiseaseInforDomain.deleteDisabilityDiseaseInforEntity(disabilityDiseaseInfor);
	}

	public Employee doListEmployee(String id) {
		Employee employee = new Employee();

		try {
			employee = disabilityDiseaseInforDomain.findEmployeeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	/** 获取伤残病明细 */
	public DisabilityDiseaseInfor doListDisabilityDiseaseInforById(String id) {
		DisabilityDiseaseInfor disabilityDiseaseInfor = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			disabilityDiseaseInfor = disabilityDiseaseInforDomain.findDisabilityDiseaseInforById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return disabilityDiseaseInfor;
	}

	// 伤残病明细
	public DisabilityDiseaseInfor doSaveDisabilityDiseaseInfor(DisabilityDiseaseInfor disabilityDiseaseInfor) throws Exception {
		DisabilityDiseaseInfor disabilityDiseaseInfor2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			disabilityDiseaseInfor2 = disabilityDiseaseInforDomain.merge(disabilityDiseaseInfor);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + disabilityDiseaseInfor + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + disabilityDiseaseInfor + "失败！失败原因：" + ex.getMessage());

		}
		return disabilityDiseaseInfor2;
	}
}

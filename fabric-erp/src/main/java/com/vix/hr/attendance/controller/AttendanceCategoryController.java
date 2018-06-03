package com.vix.hr.attendance.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.attendance.domain.AttendanceCategoryDomain;
import com.vix.hr.attendance.entity.AttendanceCategory;

@Controller("attendancecategorycontroller")
@Scope("prototype")
public class AttendanceCategoryController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("AttendanceCategoryController");

	@Autowired
	private AttendanceCategoryDomain attendanceCategoryDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceCategoryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceCategoryDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesOrder
	 * @return
	 * @throws Exception
	 */
	public AttendanceCategory doSaveAttendanceCategory(AttendanceCategory attendanceCategory) throws Exception {
		AttendanceCategory attendanceCategory2 = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行保存操作
		attendanceCategory2 = attendanceCategoryDomain.merge(attendanceCategory);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return attendanceCategory2;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public AttendanceCategory doListAttendanceCategoryById(String id) {
		AttendanceCategory attendanceCategory = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			attendanceCategory = attendanceCategoryDomain.findAttendanceCategoryById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return attendanceCategory;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByAttendanceCategory(AttendanceCategory attendanceCategory) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			attendanceCategoryDomain.deleteByEntity(attendanceCategory);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
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

	public Pager goSubSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceCategoryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public AttendanceCategory findAttendanceCategoryById(String id) throws Exception {
		return attendanceCategoryDomain.findAttendanceCategoryById(id);
	}

	public AttendanceCategory merge(AttendanceCategory attendanceCategory) throws Exception {
		attendanceCategoryDomain.merge(attendanceCategory);
		return null;
	}

	public void deleteByCategory(AttendanceCategory attendanceCategory) throws Exception {
		attendanceCategoryDomain.deleteByEntity(attendanceCategory);
	}

	public List<AttendanceCategory> findAttendanceCategoryIndex() throws Exception {
		return attendanceCategoryDomain.findAttendanceCategoryIndex();
	}

	public List<AttendanceCategory> findAllSubEntity(String attributeName, String parentId, Map<String, Object> params) throws Exception {
		return attendanceCategoryDomain.findAllSubEntity(attributeName, parentId, params);
	}
}

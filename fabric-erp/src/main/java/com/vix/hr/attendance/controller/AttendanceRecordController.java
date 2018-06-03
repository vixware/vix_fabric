package com.vix.hr.attendance.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.attendance.domain.AttendanceRecordDomain;
import com.vix.hr.attendance.entity.AttendanceRecord;

/**
 * @Description: 考勤记录
 * @author ivan
 * @date 2013-09-02
 */
@Controller("attendanceRecordController")
@Scope("prototype")
public class AttendanceRecordController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("AttendanceRecordController");

	@Autowired
	private AttendanceRecordDomain attendanceRecordDomain;

	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	public Pager doListAttendanceRecordByCon(String hql, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			p = attendanceRecordDomain.findAttendanceRecordPagerByOrHqlConditions(hql, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = attendanceRecordDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param salesArrival
	 * @return
	 */
	public AttendanceRecord doSaveAttendanceRecord(AttendanceRecord attendanceRecord) {
		AttendanceRecord attendanceRecordTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveArrival(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			attendanceRecordTemp = attendanceRecordDomain.merge(attendanceRecord);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + attendanceRecord + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + attendanceRecord + "失败！失败原因：" + ex.getMessage());
		}
		return attendanceRecordTemp;
	}

	// public PurchaseArrival doSavePurchaseArrival(PurchaseArrival
	// purchaseArrival,
	// List<PurchaseArrivalItems> aiList) throws Exception {
	//
	// PurchaseArrival purchaseArrivalTemp2 = null;
	// try {
	// // 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
	// // Map parameters = baExecutor.executeParameterProcess();
	//
	// // 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
	// beforeEventTrigger("PO_CREATE_BEFORE");
	// // 3. 执行保存操作
	// if (null != purchaseArrival) {
	// purchaseArrivalTemp2 = purchaseArrivalDomain.merge(purchaseArrival);
	// if (null != aiList && aiList instanceof List) {
	// for (PurchaseArrivalItems ad : aiList) {
	// if (null != ad && null != ad.getItemCode()
	// && !"".equals(ad.getItemCode())) {
	// ad.setPurchaseArrival(purchaseArrivalTemp2);
	// purchaseArrivalDomain.merge(ad);
	// }
	// }
	// }
	// }
	// // 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
	// afterEventTrigger("PO_CREATE_AFTER");
	// // 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
	// // 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	// executeLogger(true, "保存订单：" + purchaseArrival + "成功！");
	// } catch (Exception ex) {
	// executeLogger(true,
	// "保存订单：" + purchaseArrival + "失败！失败原因：" + ex.getMessage());
	//
	// }
	// return purchaseArrivalTemp2;
	//
	// }

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public AttendanceRecord doListEntityById(String id) {
		AttendanceRecord attendanceRecordTemp = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveArrival(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			attendanceRecordTemp = attendanceRecordDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return attendanceRecordTemp;
	}

	/**
	 * 
	 * 
	 * @param supplier
	 */
	public void doDeleteByEntity(AttendanceRecord attendanceRecord) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveApply(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			attendanceRecordDomain.deleteByEntity(attendanceRecord);
			// 4. 触发事件 afterEventSaveApply(parameter); 抽象方法，需要重载；
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
		Pager p = attendanceRecordDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public AttendanceRecord findEntityById(String id) throws Exception {
		return attendanceRecordDomain.findEntityById(id);
	}

	public AttendanceRecord merge(AttendanceRecord attendanceRecord) throws Exception {
		attendanceRecordDomain.merge(attendanceRecord);
		return null;
	}

	public void deleteByEntity(AttendanceRecord attendanceRecord) throws Exception {
		attendanceRecordDomain.deleteByEntity(attendanceRecord);
	}

	public List<AttendanceRecord> findAttendanceRecordIndex() throws Exception {
		return attendanceRecordDomain.findAttendanceRecordIndex();
	}
}

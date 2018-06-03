package com.vix.oa.personaloffice.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.domain.PersonalAttendanceDomain;
import com.vix.oa.personaloffice.entity.Attendance;
import com.vix.oa.personaloffice.entity.OvertimeRecords;
import com.vix.oa.personaloffice.entity.PersonalAttendance;
import com.vix.oa.personaloffice.entity.TLeaveRecords;
import com.vix.oa.personaloffice.entity.TripRecord;

/**
 * 
 * @ClassName: PersonalAttendanceController
 * @Description:完成对前端业务请求的参数处理和理解,并调用Domain层对象 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-16 下午1:32:39
 */
@Controller("personalAttendanceController")
@Scope("prototype")
public class PersonalAttendanceController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("PersonalAttendanceController");

	@Autowired
	private PersonalAttendanceDomain personalAttendanceDomain;
	
	/** 获取外出列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取外出搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	
	/** 获取请假列表数据 */
	public Pager goTleaveRecordsList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByHqlConditions1(params, pager);
		return p;
	}
	
	
	/** 获取请假记录搜索列表数据 */
	public Pager goSearchList1(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByOrHqlConditions1(params, pager);
		return p;
	}
	
	/** 获取出差列表数据 */
	public Pager goTripRecordList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}
	
	/** 获取出差记录搜索列表数据 */
	public Pager goSearchList2(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByOrHqlConditions2(params, pager);
		return p;
	}
	
	/** 获取加班登记列表数据 */
	public Pager goOvertimeRecordsList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByHqlConditions3(params, pager);
		return p;
	}
	
	/** 获取加班登记搜索列表数据 */
	public Pager goSearchList3(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByOrHqlConditions3(params, pager);
		return p;
	}
	
	/** 获取考勤记录列表数据 */
	public Pager goAttendanceList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByHqlConditions4(params, pager);
		return p;
	}
	
	/** 获取考勤记录搜索列表数据 */
	public Pager goSearchList4(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = personalAttendanceDomain.findPagerByOrHqlConditions4(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取外出记录列表的业务逻辑处理
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
			p = personalAttendanceDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询外出记录成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询外出记录失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取请假记录列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList1(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = personalAttendanceDomain.findPagerByHqlConditions1(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询请假记录成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询请假记录失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现获取出差列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList2(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = personalAttendanceDomain.findPagerByHqlConditions2(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询出差成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询出差失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取加班登记列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList3(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = personalAttendanceDomain.findPagerByHqlConditions3(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询加班登记成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询加班登记失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	public Pager doSubSingleList4(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = personalAttendanceDomain.findPagerByHqlConditions4(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现查询考勤管理的业务逻辑处理
	 */
	public List<PersonalAttendance> doListSalesOrderIndex() {
		List<PersonalAttendance> personalAttendance = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			personalAttendance = personalAttendanceDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return personalAttendance;
	}
	
	/**
	 * doSaveExecute实现查询考勤管理的业务逻辑处理
	 */
	public List<TLeaveRecords> doListSalesOrderIndex1() {
		List<TLeaveRecords> tLeaveRecords = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			tLeaveRecords = personalAttendanceDomain.findSalesOrderIndex1();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return tLeaveRecords;
	}
	/**
	 * doSaveExecute实现查询考勤管理的业务逻辑处理
	 */
	public List<TripRecord> doListSalesOrderIndex2() {
		List<TripRecord> tripRecord = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			tripRecord = personalAttendanceDomain.findSalesOrderIndex2();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return tripRecord;
	}
	/**
	 * doSaveExecute实现查询考勤管理的业务逻辑处理
	 */
	public List<OvertimeRecords> doListSalesOrderIndex3() {
		List<OvertimeRecords> overtimeRecords = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			overtimeRecords = personalAttendanceDomain.findSalesOrderIndex3();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return overtimeRecords;
	}
	/**
	 * doSaveExecute实现查询考勤管理的业务逻辑处理
	 */
	public List<Attendance> doListAttendanceIndex() {
		List<Attendance> attendance = null;
		
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			attendance = personalAttendanceDomain.findAttendanceIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤失败！" + "失败原因:" + ex.getMessage());
		}
		return attendance;
	}
	
	
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PersonalAttendance doListEntityById(String id) throws Exception {
		PersonalAttendance personalAttendance = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			personalAttendance = personalAttendanceDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤信息失败！" + "失败原因:" + ex.getMessage());
		}

		return personalAttendance;
	}
	
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TLeaveRecords doListEntityById1(String id) throws Exception {
		TLeaveRecords tleaveRecords = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			tleaveRecords = personalAttendanceDomain.findEntityById1(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return tleaveRecords;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TripRecord doListEntityById2(String id) throws Exception {
		TripRecord tripRecord = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			tripRecord = personalAttendanceDomain.findEntityById2(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return tripRecord;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OvertimeRecords doListEntityById3(String id) throws Exception {
		OvertimeRecords overtimeRecords = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			overtimeRecords = personalAttendanceDomain.findEntityById3(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return overtimeRecords;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Attendance doListEntityById4(String id) throws Exception {
		Attendance attendance = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			attendance = personalAttendanceDomain.findEntityById4(id);
			
			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询考勤信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询考勤信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return attendance;
	}
	
	
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 */
	public PersonalAttendance doSaveSalesOrder(PersonalAttendance personalAttendance)throws Exception {
		PersonalAttendance personalAttendance1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			personalAttendance1 = personalAttendanceDomain.merge(personalAttendance);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存考勤管理：" + personalAttendance + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存考勤管理：" + personalAttendance + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存考勤管理", ex);
		}
		return personalAttendance1;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 */
	public TLeaveRecords doSaveSalesOrder1(TLeaveRecords tleaveRecords)throws Exception {
		TLeaveRecords tleaveRecords1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			tleaveRecords1 = personalAttendanceDomain.merge1(tleaveRecords);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存考勤管理：" + tleaveRecords + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存考勤管理：" + tleaveRecords + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存考勤管理", ex);
		}
		return tleaveRecords1;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 */
	public TripRecord doSaveSalesOrder2(TripRecord tripRecord)throws Exception {
		TripRecord tripRecord1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			tripRecord1 = personalAttendanceDomain.merge2(tripRecord);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存考勤管理：" + tripRecord + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存考勤管理：" + tripRecord + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存考勤管理", ex);
		}
		return tripRecord1;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 */
	public OvertimeRecords doSaveSalesOrder3(OvertimeRecords overtimeRecords)throws Exception {
		OvertimeRecords overtimeRecords1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			overtimeRecords1 = personalAttendanceDomain.merge3(overtimeRecords);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存考勤管理：" + overtimeRecords + "成功！");
		} catch (Exception ex) {
			executeLogger(true,
					"保存考勤管理：" + overtimeRecords + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存考勤管理", ex);
		}
		return overtimeRecords1;
	}
	/**
	 * doSaveExecute实现保存考勤业务逻辑处理
	 */
	public Attendance doSaveAttendance(Attendance attendance)throws Exception {
		Attendance attendance1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			/*taskDefinition.setUpdateTime(new Date());*/
			// 3. 执行保存操作
			attendance1 = personalAttendanceDomain.merge3(attendance);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存考勤管理：" + attendance + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存考勤管理：" + attendance + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存考勤管理", ex);
		}
		return attendance1;
	}
	
	/**处理删除外出记录操作*/
	public PersonalAttendance findEntityById(String id) throws Exception {
		return personalAttendanceDomain.findEntityById(id);
	}
	/**处理删除请假记录操作*/
	public TLeaveRecords findLeaveById(String id) throws Exception {
		return personalAttendanceDomain.findLeaveById(id);
	}
	/**处理删除出差记录操作*/
	public TripRecord findTripRecordById(String id) throws Exception {
		return personalAttendanceDomain.findTripRecordById(id);
	}
	/**处理删除加班登记操作*/
	public OvertimeRecords findOvertimeRecordsById(String id) throws Exception {
		return personalAttendanceDomain.findOvertimeRecordsById(id);
	}
	/**处理删除考勤记录操作*/
	public Attendance findAttendanceById(String id) throws Exception {
		return personalAttendanceDomain.findAttendanceById(id);
	}
	
	/**
	 * doSaveExecute实现删除外出的业务逻辑处理
	 */
	public void doDeleteByEntity(PersonalAttendance personalAttendance) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			personalAttendanceDomain.deleteByEntity(personalAttendance);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除外出成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除外出失败！失败原因：" + ex.getMessage());
		}
	}
	
	/**
	 * doSaveExecute实现删除请假的业务逻辑处理
	 */
	public void doDeleteByLeave(TLeaveRecords tLeaveRecords) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			personalAttendanceDomain.deleteByLeave(tLeaveRecords);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除请假成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除请假失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除出差记录的业务逻辑处理
	 */
	public void doDeleteByTripRecord(TripRecord tripRecord) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			personalAttendanceDomain.deleteByTripRecord(tripRecord);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除出差记录成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除出差记录失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除加班登记的业务逻辑处理
	 */
	public void doDeleteByOvertimeRecords(OvertimeRecords overtimeRecords) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			personalAttendanceDomain.deleteByOvertimeRecords(overtimeRecords);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除加班登记成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除加班登记失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除考勤记录的业务逻辑处理
	 */
	public void doDeleteByAttendance(Attendance attendance) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			personalAttendanceDomain.deleteByAttendance(attendance);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除考勤记录成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除考勤记录失败！失败原因：" + ex.getMessage());
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

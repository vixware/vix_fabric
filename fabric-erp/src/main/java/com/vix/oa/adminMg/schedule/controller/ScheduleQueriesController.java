package com.vix.oa.adminMg.schedule.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.schedule.domain.ScheduleQueriesDomain;
import com.vix.oa.adminMg.schedule.entity.PeriodicTransaction;
import com.vix.oa.adminMg.schedule.entity.ScheduleQueries;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * @ClassName: 
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("ScheduleQueriesController")
@Scope("prototype")
public class ScheduleQueriesController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ScheduleQueriesController");

	@Autowired
	private ScheduleQueriesDomain scheduleQueriesDomain;
	
	
	
	/**
	 * doSaveExecute实现查询日程安排的业务逻辑处理
	 */
	public List<Calendars> doListSalesOrderIndex() {
		List<Calendars> calendars = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			calendars = scheduleQueriesDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询日程安排成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询日程安排失败！" + "失败原因:" + ex.getMessage());
		}
		return calendars;
	}
	
	/**
	 * doSaveExecute实现获取日程安排列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doCalendarsList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = scheduleQueriesDomain.findPagerByCalendarsConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询日程安排信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询日程安排信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/** 获取日程安排搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	
	
	
	/** 获取日程安排数据 */
	public Pager goList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	
	
	/** 获取周期事物搜索列表数据 */
	public Pager goSearchList1(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByOrHqlConditions1(params, pager);
		return p;
	}
	
	/** 获取任务搜索列表数据 */
	public Pager goSearchList2(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByOrHqlConditions2(params, pager);
		return p;
	}
	
	
		/** 获取日常事物列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取周期事物列表数据 */
	public Pager goSingleList1(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByHqlConditions1(params, pager);
		return p;
	}
	
	/** 获取任务事物列表数据 */
	public Pager goSingleList2(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = scheduleQueriesDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取日常事物列表的业务逻辑处理
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
			p = scheduleQueriesDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询日常事物信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询日常事物信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取周期事物列表的业务逻辑处理
	 * 
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
			p = scheduleQueriesDomain.findPagerByHqlConditions1(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询周期事物信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询周期事物信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存日常事物的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public ScheduleQueries doSaveSalesOrder(ScheduleQueries scheduleQueries) {
		ScheduleQueries scheduleQueries1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			scheduleQueries.setUpdateTime(new Date());
			// 3. 执行保存操作
			scheduleQueries1 = scheduleQueriesDomain.merge(scheduleQueries);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存日常事物：" + scheduleQueries + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存日常事物：" + scheduleQueries + "失败！失败原因：" + ex.getMessage());
		}
		return scheduleQueries1;
	}	
	/**
	 * doSaveExecute实现保存周期事物的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public PeriodicTransaction doSaveSalesOrder1(PeriodicTransaction periodicTransaction) {
		PeriodicTransaction periodicTransaction1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			periodicTransaction.setUpdateTime(new Date());
			// 3. 执行保存操作
			periodicTransaction1 = scheduleQueriesDomain.merge(periodicTransaction);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存周期事物：" + periodicTransaction + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存周期事物：" + periodicTransaction + "失败！失败原因：" + ex.getMessage());
		}
		return periodicTransaction1;
	}	
	
	/**
	 * doSaveExecute实现保存任务的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public VixTask doSaveSalesOrder2(VixTask taskDefinition) {
		VixTask taskDefinition1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			taskDefinition.setUpdateTime(new Date());
			// 3. 执行保存操作
			taskDefinition1 = scheduleQueriesDomain.merge(taskDefinition);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存任务付款：" + taskDefinition + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存任务付款：" + taskDefinition + "失败！失败原因：" + ex.getMessage());
		}
		return taskDefinition1;
	}	
	
	/**
	 * doSaveExecute实现保存日常事物业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ScheduleQueries doListEntityById(String id) throws Exception {
		ScheduleQueries scheduleQueries = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			scheduleQueries = scheduleQueriesDomain.findEntityById(id);

			
			  // 3.执行查询操作 contractDomain.findEntityById(id);
			 
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询日常事物信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询日常事物信息失败！" + "失败原因:" + ex.getMessage());
		}

		return scheduleQueries;
	}
	/**
	 * doSaveExecute实现保存周期事物业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PeriodicTransaction doListEntityById1(String id) throws Exception {
		PeriodicTransaction periodicTransaction = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			periodicTransaction = scheduleQueriesDomain.findEntityById1(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询周期事物信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询周期事物信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return periodicTransaction;
	}
	
	/**
	 * doSaveExecute实现保存任务业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VixTask doListEntityById2(String id) throws Exception {
		VixTask taskDefinition = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			taskDefinition = scheduleQueriesDomain.findEntityById2(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询任务信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询任务信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return taskDefinition;
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
}
package com.vix.oa.adminMg.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.domain.WorkPlanDomain;
import com.vix.oa.adminMg.entity.Postil;
import com.vix.oa.adminMg.entity.ProgressLog;
import com.vix.oa.adminMg.entity.ProjectManagement;
import com.vix.oa.adminMg.entity.WorkPlanType;

/**
 * @ClassName: 
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("WorkPlanController")
@Scope("prototype")
public class WorkPlanController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("WorkPlanController");

	@Autowired
	private WorkPlanDomain workPlanDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = workPlanDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = workPlanDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/** 获取工作计划数据 */
	public Pager goList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = workPlanDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取工作计划数据 */
	public Pager goListProject(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = workPlanDomain.findPagerByHqlConditions1(params, pager);
		return p;
	}
	
	/** 获取工作计划数据 */
	public Pager goListType(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = workPlanDomain.findPagerByHqlConditions2(params, pager);
		return p;
	}
	
	public List<ProgressLog> findMoneyTypeIndex() throws Exception {
		return workPlanDomain.findMoneyTypeIndex();
	}
	
	/**
	 * doSaveExecute实现查询新闻 的业务逻辑处理
	 */
	public List<ProjectManagement> doListSalesOrderIndex() {
		List<ProjectManagement> projectManagement = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			projectManagement = workPlanDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询新闻 成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询新闻 失败！" + "失败原因:" + ex.getMessage());
		}
		return projectManagement;
	}
	
	/**
	 * doSaveExecute实现获取工作计划列表的业务逻辑处理
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
			p = workPlanDomain.findPagerByHqlConditions(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取领导批注的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doSubSingleList4(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = workPlanDomain.findPagerByHqlConditions4(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询领导批注信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询领导批注信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取工作计划列表的业务逻辑处理
	 * 
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
			p = workPlanDomain.findPagerByHqlConditions3(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询进度日志成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询进度日志失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现获取工作计划列表的业务逻辑处理
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
			p = workPlanDomain.findPagerByHqlConditions1(params, pager);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现获取工作计划列表的业务逻辑处理
	 * 
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
			p = workPlanDomain.findPagerByHqlConditions2(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	
	/**
	 * doSaveExecute实现保存工作计划的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public ProgressLog doSaveSalesOrder(ProgressLog workPlan) {
		ProgressLog progressLog1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			workPlan.setUpdateTime(new Date());
			// 3. 执行保存操作
			progressLog1 = workPlanDomain.merge(workPlan);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存工作计划：" + workPlan + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存工作计划：" + workPlan + "失败！失败原因：" + ex.getMessage());
		}
		return progressLog1;
	}
	/**
	 * doSaveExecute实现保存工作计划的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public ProjectManagement doSaveSalesOrder(ProjectManagement projectManagement) {
		ProjectManagement projectManagement1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			projectManagement.setUpdateTime(new Date());
			// 3. 执行保存操作
			projectManagement1 = workPlanDomain.merge(projectManagement);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存工作计划：" + projectManagement + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存工作计划：" + projectManagement + "失败！失败原因：" + ex.getMessage());
		}
		return projectManagement1;
	}
	

	/**
	 * doSaveExecute实现保存工作计划的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public ProjectManagement doSaveSalesOrder1(ProjectManagement projectManagement) {
		ProjectManagement projectManagement1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			projectManagement.setUpdateTime(new Date());
			// 3. 执行保存操作
			projectManagement1 = workPlanDomain.merge(projectManagement);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存工作计划：" + projectManagement + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存工作计划：" + projectManagement + "失败！失败原因：" + ex.getMessage());
		}
		return projectManagement1;
	}
	
	/**
	 * doSaveExecute实现保存工作计划的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public Postil doSavePostilOrPlan(Postil postil) {
		Postil postil1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			postil.setUpdateTime(new Date());
			// 3. 执行保存操作
			postil1 = workPlanDomain.merge(postil);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存工作计划：" + postil + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存工作计划：" + postil + "失败！失败原因：" + ex.getMessage());
		}
		return postil1;
	}	
	/**
	 * doSaveExecute实现保存工作计划类型设置的业务逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public WorkPlanType doSaveSalesOrder2(WorkPlanType workPlanType) {
		WorkPlanType workPlanType1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			workPlanType.setUpdateTime(new Date());
			// 3. 执行保存操作
			workPlanType1 = workPlanDomain.merge(workPlanType);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存工作计划：" + workPlanType + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存工作计划：" + workPlanType + "失败！失败原因：" + ex.getMessage());
		}
		return workPlanType1;
	}	
	
	/**
	 * doSaveExecute实现保存工作计划业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProgressLog doListEntityById(String id) throws Exception {
		ProgressLog workPlan = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			workPlan = workPlanDomain.findEntityById(id);

			
			  // 3.执行查询操作 contractDomain.findEntityById(id);
			 
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}

		return workPlan;
	}
	
	/**
	 * doSaveExecute实现保存工作计划业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProjectManagement doListEntityById1(String id) throws Exception {
		ProjectManagement projectManagement = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			projectManagement = workPlanDomain.findEntityById1(id);			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return projectManagement;
	}
	
	/**
	 * doSaveExecute实现保存工作计划业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Postil doListPostilOrPlan(String id) throws Exception {
		Postil postil = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			postil = workPlanDomain.findPostilOrPlan(id);			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return postil;
	}
	
	/**
	 * doSaveExecute实现保存工作计划业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WorkPlanType doListEntityById2(String id) throws Exception {
		WorkPlanType workPlanType = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3.执行查询操作
			workPlanType = workPlanDomain.findEntityById2(id);
			
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询工作计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询工作计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		
		return workPlanType;
	}
	
	
	
	/**
	 * doSaveExecute实现删除工作计划类型设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByEntity(WorkPlanType workPlanType) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			workPlanDomain.deleteByEntity(workPlanType);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除工作计划类型设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除工作计划类型设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除进度日志设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByEntity3(ProgressLog progressLog) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			workPlanDomain.deleteByEntity(progressLog);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除进度日志成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除进度日志失败！失败原因：" + ex.getMessage());
		}
	}
	/**
	 * doSaveExecute实现删除工作计划类型设置的业务逻辑处理
	 * 
	 * @param 
	 */
	public void doDeleteByEntity(ProjectManagement projectManagement) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			workPlanDomain.deleteByEntity(projectManagement);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除工作计划类型设置成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除工作计划类型设置信息失败！失败原因：" + ex.getMessage());
		}
	}
	
	
	/**删除*/
	public ProjectManagement findEntityById(String id) throws Exception {
		return workPlanDomain.findEntityById1(id);
	}
	public ProgressLog findEntityById3(String id) throws Exception {
		return workPlanDomain.findEntityById3(id);
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
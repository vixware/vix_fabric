package com.vix.oa.bulletin.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.bulletin.doman.AnnouncementNotificationDomain;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.entity.NoticeUploader;

/**
 * 
 * @ClassName: AnnouncementNotificationController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-3 下午1:42:28
 */
@Controller("announcementNotificationController")
@Scope("prototype")
public class AnnouncementNotificationController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("AnnouncementNotificationController");

	@Autowired
	private AnnouncementNotificationDomain announcementNotificationDomain;
	
	/** 获取列表数据 */
	public Pager goList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = announcementNotificationDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = announcementNotificationDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = announcementNotificationDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取公告列表的业务逻辑处理
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
			p = announcementNotificationDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询公告设置 成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询公告设置 失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	/**
	 * doSaveExecute实现获取查看公告通知人员列表的业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doaccountStatementsList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = announcementNotificationDomain.findPagerByHqlAccountStatements(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询公告通知人员成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询公告通知人员失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现查询公告的业务逻辑处理
	 */
	public List<AnnouncementNotification> doListSalesOrderIndex() {
		List<AnnouncementNotification> announcementNotification = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			announcementNotification = announcementNotificationDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询公告成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询公告 失败！" + "失败原因:" + ex.getMessage());
		}
		return announcementNotification;
	}
	
	
	/**
	 * doSaveExecute实现保存公告 业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AnnouncementNotification doListEntityById(String id) throws Exception {
		AnnouncementNotification announcementNotification = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			announcementNotification = announcementNotificationDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询公告信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询公告信息失败！" + "失败原因:" + ex.getMessage());
		}

		return announcementNotification;
	}
	
	
	/**
	 * doSaveExecute实现上传附件业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NoticeUploader doNoticeUploader(String id) throws Exception {
		NoticeUploader noticeUploader = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			noticeUploader = announcementNotificationDomain.findNoticeUploader(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询上传附件信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询上传附件信息失败！" + "失败原因:" + ex.getMessage());
		}

		return noticeUploader;
	}
	
	/**
	 * doSaveExecute实现获取附件列表数据业务逻辑处理
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doNoticeUploader(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = announcementNotificationDomain.findPagerByHqlNoticeUploader(params, pager);
			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询附件列表数据成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询附件列表数据失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	
	
	/**
	 * doSaveExecute实现保存公告业务逻辑处理
	 */
	public AnnouncementNotification doSaveSalesOrder(AnnouncementNotification announcementNotification)throws Exception {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			announcementNotification.setUpdateTime(new Date());
			// 3. 执行保存操作
			announcementNotification = announcementNotificationDomain.merge(announcementNotification);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存公告 ：" + announcementNotification + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存公告：" + announcementNotification + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存公告", ex);
		}
		return announcementNotification;
	}
	
	/**删除*/
	public AnnouncementNotification findEntityById(String id) throws Exception {
		return announcementNotificationDomain.findEntityById(id);
	}
	
	/**
	 * doSaveExecute实现删除公告的业务逻辑处理
	 */
	public void doDeleteByEntity(AnnouncementNotification announcementNotification) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			announcementNotificationDomain.deleteByEntity(announcementNotification);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除公告成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除公告失败！失败原因：" + ex.getMessage());
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

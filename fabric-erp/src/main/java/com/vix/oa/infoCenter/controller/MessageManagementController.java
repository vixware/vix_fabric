package com.vix.oa.infoCenter.controller;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.infoCenter.domain.MessageManagementDomain;
import com.vix.oa.infoCenter.entity.ManagementUploader;
import com.vix.oa.infoCenter.entity.MessageManagement;
import com.vix.oa.infoCenter.entity.NewsComment;

/**
 * @ClassName: 
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("MessageManagementController")
@Scope("prototype")
public class MessageManagementController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("MessageManagementController");

	@Autowired
	private MessageManagementDomain messageManagementDomain;
	
	/** 获取消息管理数据 */
	public Pager goList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = messageManagementDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = messageManagementDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取消息列表的业务逻辑处理
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
			p = messageManagementDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询消息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询消息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**删除发送未发送消息*/
	public MessageManagement findEntityById(String id) throws Exception {
		return messageManagementDomain.findEntityById(id);
	}
	/**删除附件*/
	public ManagementUploader findUploaderById(String id) throws Exception {
		return messageManagementDomain.findNoticeUploader(id);
	}
	
	/**
	 * doSaveExecute实现删除发送未发送消息逻辑处理
	 */
	public void doDeleteByEntity(MessageManagement messageManagement) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			messageManagementDomain.deleteByEntity(messageManagement);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除发送未发送消息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除发送未发送消息失败！失败原因：" + ex.getMessage());
		}
	}
	
	
	/**
	 * doSaveExecute实现删除附件逻辑处理
	 */
	public void doDeleteByUploader(ManagementUploader managementUploader) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			
			// 3. 执行删除操作
			messageManagementDomain.deleteManagementUploader(managementUploader);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除附件成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除附件失败！失败原因：" + ex.getMessage());
		}
	}
	
	/**
	 * doSaveExecute实现附件列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doManagementUploader(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = messageManagementDomain.findManagementUploader(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询附件成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询附件失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存发送消息逻辑处理
	 * 
	 * @param contractClause
	 * @return
	 */
	public MessageManagement doSaveSalesOrder(MessageManagement messageManagement) {
		MessageManagement messageManagement1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			//保存系统当前时间
			messageManagement.setUpdateTime(new Date());
			// 3. 执行保存操作
			messageManagement1 = messageManagementDomain.merge(messageManagement);			
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存消息：" + messageManagement + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存消息：" + messageManagement + "失败！失败原因：" + ex.getMessage());
		}
		return messageManagement1;
	}	
	
	/**
	 * doSaveExecute实现保存至草稿务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MessageManagement doListEntityById(String id) throws Exception {
		MessageManagement messageManagement = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			messageManagement = messageManagementDomain.findEntityById(id);

			// 3.执行查询操作 contractDomain.findEntityById(id);
			 
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询草稿信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询草稿信息失败！" + "失败原因:" + ex.getMessage());
		}

		return messageManagement;
	}
	
	/**
	 * doSaveExecute实现保存至消息评论业务逻辑处理
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NewsComment doNewsCommentById(String id) throws Exception {
		NewsComment newsComment = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			newsComment = messageManagementDomain.findNewsCommentById(id);
			
			// 3.执行查询操作 contractDomain.findEntityById(id);
			 
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询消息评论成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询消息评论失败！" + "失败原因:" + ex.getMessage());
		}

		return newsComment;
	}
	
	/**
	 * doSaveExecute实现保存消息评论业务逻辑处理
	 */
	public NewsComment doSaveNewsComment(NewsComment newsComment)throws Exception {
		NewsComment newsComments = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();
			
			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			newsComments = messageManagementDomain.merge(newsComment);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存消息评论：" + newsComment + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存消息评论：" + newsComment + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存消息评论", ex);
		}
		return newsComments;
	}
	
	/**
	 * doSaveExecute实现上传附件业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ManagementUploader doNoticeUploader(String id) throws Exception {
		ManagementUploader managementUploader = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			managementUploader = messageManagementDomain.findNoticeUploader(id);

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

		return managementUploader;
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
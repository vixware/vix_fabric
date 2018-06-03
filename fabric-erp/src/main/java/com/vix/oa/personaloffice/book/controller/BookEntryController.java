package com.vix.oa.personaloffice.book.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.book.domain.BookEntryDomain;
import com.vix.oa.personaloffice.book.entity.BookEntry;
import com.vix.oa.personaloffice.book.entity.BookRegister;

/**
 * @ClassName: BookEntryController
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("bookEntryController")
@Scope("prototype")
public class BookEntryController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("BookEntryController");

	@Autowired
	private BookEntryDomain bookEntryDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = bookEntryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goBookRegister(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = bookEntryDomain.findWimStockrecordsPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = bookEntryDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}
	
	/** 获取搜索列表数据 */
	public Pager goSearchList1(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = bookEntryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	
	/** 获取图书借用搜索列表数据 */
	public Pager gobookLibrarianList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = bookEntryDomain.findBookLibrarian(params, pager);
		return p;
	}
	
	/**
	 * doSaveExecute实现获取图书录入列表的业务逻辑处理
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
			p = bookEntryDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书录入成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书录入失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}
	
	
	/**
	 * doSaveExecute实现查询图书录入的业务逻辑处理
	 */
	public List<BookEntry> doListSalesOrderIndex() {
		List<BookEntry> bookEntry = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			bookEntry = bookEntryDomain.findSalesOrderIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书录入成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书录入失败！" + "失败原因:" + ex.getMessage());
		}
		return bookEntry;
	}
	
	
	/**
	 * doSaveExecute实现保存图书录入业务逻辑处理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BookEntry doListEntityById(String id) throws Exception {
		BookEntry bookEntry = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			bookEntry = bookEntryDomain.findEntityById(id);

			/*
			 * // 3.执行查询操作 contractDomain.findEntityById(id);
			 */
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书录入信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书录入信息失败！" + "失败原因:" + ex.getMessage());
		}

		return bookEntry;
	}
	
	/**
	 * doSaveExecute实现保存图书的业务逻辑处理
	 * 
	 * @param id
	 * @return
	 */
	public BookRegister doListBookRegisterId(String id) {
		BookRegister bookRegister = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			bookRegister = bookEntryDomain.findWimStockrecordsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书信息失败！" + "失败原因:" + ex.getMessage());
		}

		return bookRegister;
	}
	
	/**
	 * doSaveExecute实现保存图书录入业务逻辑处理
	 */
	public BookEntry doSaveSalesOrder(BookEntry bookEntry)throws Exception {
		BookEntry bookEntry1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			bookEntry1 = bookEntryDomain.merge(bookEntry);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存图书录入：" + bookEntry + "成功！");
		} catch (Exception ex) {
			executeLogger(true,"保存图书录入义：" + bookEntry + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存图书录入", ex);
		}
		return bookEntry1;
	}
	
	
	
	/**删除*/
	public BookEntry findEntityById(String id) throws Exception {
		return bookEntryDomain.findEntityById(id);
	}
	
	/**
	 * doSaveExecute实现删除图书录入的业务逻辑处理
	 */
	public void doDeleteByEntity(BookEntry bookEntry) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			bookEntryDomain.deleteByEntity(bookEntry);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除图书录入成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除图书录入失败！失败原因：" + ex.getMessage());
		}
	}
	
	
	/**
	 * doSaveExecute实现查询图书的业务逻辑处理
	 * 
	 * @param parameters
	 * @return
	 */
	public List<BookRegister> doListWimStockrecordsIndex() {
		List<BookRegister> bookRegister = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行查询操作
			bookRegister = bookEntryDomain.findWimStockrecordsIndex();
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询办图书失败！" + "失败原因:" + ex.getMessage());
		}
		return bookRegister;
	}
	
	/**
	 * doSaveExecute实现获取图书列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListWimStockrecords(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = bookEntryDomain.findWimStockrecordsPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书失败！失败原因：" + ex.getMessage());
		}
		return p;
	}
	
	
	/**
	 * doSaveExecute实现获取图书借用列表的业务逻辑处理
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListBookLibrarian(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行保存操作
			p = bookEntryDomain.findBookLibrarianPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询借用图书成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询借用图书失败！失败原因：" + ex.getMessage());
		}
		return p;
	}
	
	/**
	 * doSaveExecute实现保存图书的业务逻辑处理
	 * 
	 * @param id
	 * @return
	 */
	public BookRegister doListBookEntityById(String id) {
		BookRegister bookRegister = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			bookRegister = bookEntryDomain.findWimStockrecordsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询图书信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询图书信息失败！" + "失败原因:" + ex.getMessage());
		}

		return bookRegister;
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

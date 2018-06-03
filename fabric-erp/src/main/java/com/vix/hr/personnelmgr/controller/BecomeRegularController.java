package com.vix.hr.personnelmgr.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.personnelmgr.domain.BecomeRegularDomain;
import com.vix.hr.personnelmgr.entity.HrApprovalOpinion;
import com.vix.hr.personnelmgr.entity.HrBecomeRegular;

@Scope("prototype")
@Controller("becomeregularController")
public class BecomeRegularController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("BecomeRegularController");
	@Autowired
	private BecomeRegularDomain becomeRegularDomain;

	public BecomeRegularDomain getBecomeRegularDomain() {
		return becomeRegularDomain;
	}

	public void setBecomeRegularDomain(BecomeRegularDomain becomeRegularDomain) {
		this.becomeRegularDomain = becomeRegularDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = becomeRegularDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrBecomeRegular findEntityById(String id) throws Exception {
		return becomeRegularDomain.findEntityById(id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		becomeRegularDomain.deleteHrAttachmentsEntity(attachments);
	}

	public HrBecomeRegular merge(HrBecomeRegular hrBecomeRegular) throws Exception {
		becomeRegularDomain.merge(hrBecomeRegular);
		return null;
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return becomeRegularDomain.findHrAttachmentsEntityById(id);
	}

	public HrAttachments mergeAttachments(HrAttachments attachments) throws Exception {
		return becomeRegularDomain.merge(attachments);
	}

	public void deleteByEntity(HrBecomeRegular hrBecomeRegular) throws Exception {
		becomeRegularDomain.deleteByEntity(hrBecomeRegular);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		becomeRegularDomain.deleteByIds(ids);
	}

	public List<HrBecomeRegular> findHrBecomeRegularIndex() throws Exception {
		return becomeRegularDomain.findHrBecomeRegularIndex();
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

	// 删除
	public HrBecomeRegular doListEntityById(String id) {

		HrBecomeRegular hrBecomeRegular = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrBecomeRegular = becomeRegularDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return hrBecomeRegular;
	}

	// 保存
	public HrBecomeRegular doSaveHrBecomeRegular(HrBecomeRegular hrBecomeRegular, String types, List<HrApprovalOpinion> aoList) {
		HrBecomeRegular hrBecomeRegular2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			// 操作类型为转正
			hrBecomeRegular.setTypes(types);
			hrBecomeRegular2 = becomeRegularDomain.merge(hrBecomeRegular);
			if (null != aoList) {
				for (HrApprovalOpinion ao : aoList) {
					if (null != ao) {
						ao.setHrBecomeRegular(hrBecomeRegular2);
						becomeRegularDomain.merge(ao);
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + hrBecomeRegular + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + hrBecomeRegular + "失败！失败原因：" + ex.getMessage());
		}
		return hrBecomeRegular2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = becomeRegularDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = becomeRegularDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	public void doDeleteByEntity(HrBecomeRegular hrBecomeRegular) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			becomeRegularDomain.deleteByEntity(hrBecomeRegular);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
	}

	/**
	 * 
	 * 
	 * @param ids
	 */
	public void doDeleteByIds(List<String> ids) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			becomeRegularDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

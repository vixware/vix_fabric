package com.vix.hr.job.controler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.domain.RecruitactivitityDomain;
import com.vix.hr.job.entity.HrRecruitactivitity;
import com.vix.hr.job.entity.HrRecruitactivitityDetails;

@Scope("prototype")
@Controller("recruitactivititycontroller")
public class RecruitactivitityController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("RecruitactivitityController");

	@Autowired
	private RecruitactivitityDomain recruitactivitityDomain;

	public RecruitactivitityDomain getRecruitactivitityDomain() {
		return recruitactivitityDomain;
	}

	public void setRecruitactivitityDomain(RecruitactivitityDomain recruitactivitityDomain) {
		this.recruitactivitityDomain = recruitactivitityDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitactivitityDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrRecruitactivitity findEntityById(String id) throws Exception {
		return recruitactivitityDomain.findEntityById(id);
	}

	public HrRecruitactivitity merge(HrRecruitactivitity hrRecruitactivitity) throws Exception {
		recruitactivitityDomain.merge(hrRecruitactivitity);
		return null;
	}

	public void deleteByEntity(HrRecruitactivitity hrRecruitactivitity) throws Exception {
		recruitactivitityDomain.deleteByEntity(hrRecruitactivitity);
	}

	public void deleteHrRecruitactivitityDetailsDetailsEntity(HrRecruitactivitityDetails hrRecruitactivitityDetails) throws Exception {
		recruitactivitityDomain.deleteHrRecruitactivitityDetailsEntity(hrRecruitactivitityDetails);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		recruitactivitityDomain.deleteByIds(ids);
	}

	public List<HrRecruitactivitity> findRecruitactivitityIndex() throws Exception {
		return recruitactivitityDomain.findRecruitactivitityIndex();
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

	/**
	 * 根据Id获取招聘活动主表数据
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitactivitity doListEntityById(String id) {

		HrRecruitactivitity hrRecruitactivitity = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrRecruitactivitity = recruitactivitityDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return hrRecruitactivitity;
	}

	/**
	 * 根据对象保存招聘活动主表数据
	 * 
	 * @param hrRecruitactivitity
	 * @return
	 */
	public HrRecruitactivitity doSaveRecruitactivitity(HrRecruitactivitity hrRecruitactivitity) {
		HrRecruitactivitity hrRecruitactivitity2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitactivitity2 = recruitactivitityDomain.merge(hrRecruitactivitity);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + hrRecruitactivitity + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + hrRecruitactivitity + "失败！失败原因：" + ex.getMessage());
		}
		return hrRecruitactivitity2;
	}

	/**
	 * 根据对象保存招聘活动明细数据
	 * 
	 * @param hrRecruitactivitityDetails
	 * @return
	 * @throws Exception
	 */
	public HrRecruitactivitityDetails doSaveHrRecruitactivitityDetails(HrRecruitactivitityDetails hrRecruitactivitityDetails) throws Exception {
		HrRecruitactivitityDetails hrRecruitactivitityDetails2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitactivitityDetails2 = recruitactivitityDomain.merge(hrRecruitactivitityDetails);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + hrRecruitactivitityDetails + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + hrRecruitactivitityDetails + "失败！失败原因：" + ex.getMessage());

		}
		return hrRecruitactivitityDetails2;
	}

	/**
	 * 获取招聘活动列表页搜索数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitactivitityDomain.findPagerByOrHqlConditions(params, pager);
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
			p = recruitactivitityDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	/**
	 * 根据Id获取招聘活动明细数据
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitactivitityDetails doListHrRecruitactivitityDetailsById(String id) {

		HrRecruitactivitityDetails detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = recruitactivitityDomain.findHrRecruitactivitityDetailsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return detailsTemp;
	}

	/**
	 * 根据对象删除招聘活动主表
	 * 
	 * @param hrRecruitactivitity
	 */
	public void doDeleteByEntity(HrRecruitactivitity hrRecruitactivitity) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			recruitactivitityDomain.deleteByEntity(hrRecruitactivitity);
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
			recruitactivitityDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

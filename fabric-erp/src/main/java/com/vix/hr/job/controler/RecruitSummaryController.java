package com.vix.hr.job.controler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.domain.RecruitSummaryDomain;
import com.vix.hr.job.entity.HrRecruitSummary;

/**
 * @Description: 招聘总结
 * @author 李大鹏
 * @date 2013-08-21
 */
@Scope("prototype")
@Controller("recruitsummarycontroller")
public class RecruitSummaryController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("SeniorController");
	@Autowired
	private RecruitSummaryDomain recruitSummaryDomain;

	public RecruitSummaryDomain getRecruitSummaryDomain() {
		return recruitSummaryDomain;
	}

	public void setRecruitSummaryDomain(RecruitSummaryDomain recruitSummaryDomain) {
		this.recruitSummaryDomain = recruitSummaryDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitSummaryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrRecruitSummary findEntityById(String id) throws Exception {
		return recruitSummaryDomain.findEntityById(id);
	}

	public HrRecruitSummary merge(HrRecruitSummary hrRecruitSummary) throws Exception {
		recruitSummaryDomain.merge(hrRecruitSummary);
		return null;
	}

	public void deleteByEntity(HrRecruitSummary hrRecruitSummary) throws Exception {
		recruitSummaryDomain.deleteByEntity(hrRecruitSummary);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		recruitSummaryDomain.deleteByIds(ids);
	}

	public List<HrRecruitSummary> findRecruitSummaryIndex() throws Exception {
		return recruitSummaryDomain.findRecruitSummaryIndex();
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
	 * 根据Id获取招聘总结信息数据
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitSummary doListEntityById(String id) {

		HrRecruitSummary hrRecruitSummary = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrRecruitSummary = recruitSummaryDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return hrRecruitSummary;
	}

	/**
	 * 根据实体对象保存招聘总结数据
	 * 
	 * @param hrRecruitSummary
	 * @return
	 * @throws Exception
	 */
	public HrRecruitSummary doSaveRecruitSummary(HrRecruitSummary hrRecruitSummary) throws Exception {
		HrRecruitSummary hrRecruitSummary2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitSummary2 = recruitSummaryDomain.merge(hrRecruitSummary);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + hrRecruitSummary + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + hrRecruitSummary + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return hrRecruitSummary2;
	}

	/**
	 * 获取招聘总结列表页搜索数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitSummaryDomain.findPagerByOrHqlConditions(params, pager);
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
			p = recruitSummaryDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	/**
	 * 根据实体对象获取招聘总结信息
	 * 
	 * @param hrRecruitSummary
	 */
	public void doDeleteByEntity(HrRecruitSummary hrRecruitSummary) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			recruitSummaryDomain.deleteByEntity(hrRecruitSummary);
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
			recruitSummaryDomain.deleteByIds(ids);
			// 4. recruitSummaryDomain发事件 afterEventSaveOrder(parameter);
			// 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}

}

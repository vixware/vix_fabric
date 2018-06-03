package com.vix.hr.job.controler;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.domain.RecruitmentPlanSummaryDomain;
import com.vix.hr.job.entity.HrRecruitmentPlansummary;
import com.vix.hr.job.entity.HrRecruitplan;

@Scope("prototype")
@Controller("recruitmentPlanSummaryController")
public class RecruitmentPlanSummaryController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("HrRecruitmentPlansummaryController");
	@Autowired
	private RecruitmentPlanSummaryDomain recruitmentPlanSummaryDomain;

	public RecruitmentPlanSummaryDomain getDemandApplicationDomain() {
		return recruitmentPlanSummaryDomain;
	}

	public void setDemandApplicationDomain(RecruitmentPlanSummaryDomain recruitmentPlanSummaryDomain) {
		this.recruitmentPlanSummaryDomain = recruitmentPlanSummaryDomain;
	}

	/** 获取招聘计划列表数据 */
	public Pager goHrRecruitplanList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryDomain.findPagerByHqlHrRecruitplan(params, pager);
		return p;
	}
	/** 获取招聘计划汇总列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrRecruitmentPlansummary findEntityById(String id) throws Exception {
		return recruitmentPlanSummaryDomain.findEntityById(id);
	}

	public HrRecruitmentPlansummary merge(HrRecruitmentPlansummary hrRecruitmentPlansummary) throws Exception {
		recruitmentPlanSummaryDomain.merge(hrRecruitmentPlansummary);
		return null;
	}

	public void deleteByEntity(HrRecruitmentPlansummary hrRecruitmentPlansummary) throws Exception {
		recruitmentPlanSummaryDomain.deleteByEntity(hrRecruitmentPlansummary);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		recruitmentPlanSummaryDomain.deleteByIds(ids);
	}

	public List<HrRecruitmentPlansummary> findRecruitmentPlansummaryIndex() throws Exception {
		return recruitmentPlanSummaryDomain.findRecruitmentPlansummaryIndex();
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
	public HrRecruitmentPlansummary doListEntityById(String id) {

		HrRecruitmentPlansummary hrRecruitmentPlansummary = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrRecruitmentPlansummary = recruitmentPlanSummaryDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划汇总失败！" + "失败原因:" + ex.getMessage());
		}

		return hrRecruitmentPlansummary;
	}

	// 保存
	public HrRecruitmentPlansummary doSaveHrRecruitmentPlansummary(HrRecruitmentPlansummary hrRecruitmentPlansummary) throws Exception {
		HrRecruitmentPlansummary hrRecruitmentPlansummary2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitmentPlansummary2 = recruitmentPlanSummaryDomain.merge(hrRecruitmentPlansummary);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存招聘计划汇总：" + hrRecruitmentPlansummary + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存招聘计划汇总：" + hrRecruitmentPlansummary + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return hrRecruitmentPlansummary2;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitmentPlanSummaryDomain.findPagerByOrHqlConditions(params, pager);
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
			p = recruitmentPlanSummaryDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划汇总失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	public void doDeleteByEntity(HrRecruitmentPlansummary hrRecruitmentPlansummary) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			recruitmentPlanSummaryDomain.deleteByEntity(hrRecruitmentPlansummary);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除招聘计划汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除招聘计划汇总失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除招聘计划汇总的业务逻辑处理
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
			recruitmentPlanSummaryDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除招聘计划汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除招聘计划汇总失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public void deleteHrRecruitplanEntity(HrRecruitplan hrRecruitplan) throws Exception {
		recruitmentPlanSummaryDomain.deleteHrRecruitplanEntity(hrRecruitplan);
	}

	/** 获取招聘计划明细 */
	public HrRecruitplan doListHrRecruitplanById(String id) {
		HrRecruitplan detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = recruitmentPlanSummaryDomain.findHrRecruitplanById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划汇总失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	// 招聘计划明细
	public HrRecruitplan doSaveHrRecruitplan(HrRecruitplan hrRecruitplan) throws Exception {
		HrRecruitplan hrRecruitplan2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitplan2 = recruitmentPlanSummaryDomain.merge(hrRecruitplan);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存招聘计划汇总：" + hrRecruitplan + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存招聘计划汇总：" + hrRecruitplan + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存招聘计划汇总失败", ex);
		}
		return hrRecruitplan2;
	}

	public HrRecruitplan findHrRecruitplanById(String id) throws Exception {
		return recruitmentPlanSummaryDomain.findHrRecruitplanById(id);
	}

}

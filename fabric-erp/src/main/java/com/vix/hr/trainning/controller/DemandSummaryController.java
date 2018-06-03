package com.vix.hr.trainning.controller;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.domain.DemandSummaryDomain;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.DemandSummary;
@Scope("prototype")
@Controller("demandSummaryController")
public class DemandSummaryController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("DemandSummaryController");
	@Autowired
	private DemandSummaryDomain demandSummaryDomain;

	public DemandSummaryDomain getDemandSummaryDomain() {
		return demandSummaryDomain;
	}

	public void setDemandSummaryDomain(DemandSummaryDomain demandSummaryDomain) {
		this.demandSummaryDomain = demandSummaryDomain;
	}

	public List<DemandSummary> findDemandSummaryIndex() throws Exception {
		return demandSummaryDomain.findDemandSummaryIndex();
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandSummaryDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandSummaryDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

	/** 根据ID查询 */
	public DemandSummary doListEntityById(String id) {
		DemandSummary demandSummary = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			demandSummary = demandSummaryDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训汇总信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训汇总信息失败！" + "失败原因:" + ex.getMessage());
		}

		return demandSummary;
	}

	/** 保存 */
	public DemandSummary doSaveDemandSummary(DemandSummary demandSummary) throws Exception {
		DemandSummary demandSummary2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			demandSummary2 = demandSummaryDomain.merge(demandSummary);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训汇总：" + demandSummary + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训汇总：" + demandSummary + "失败！失败原因：" + ex.getMessage());

			throw new Exception(ex.getMessage());
		}
		return demandSummary2;
	}

	/** 删除 */
	public void doDeleteByEntity(DemandSummary demandSummary) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			demandSummaryDomain.deleteByEntity(demandSummary);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训汇总失败！失败原因：" + ex.getMessage());
		}
	}

	public DemandSummary findEntityById(String id) throws Exception {
		return demandSummaryDomain.findEntityById(id);
	}

	/*******************************************
	 * 培训需求申请
	 ********************************************************************************/
	public DemandApply findDemandApplyById(String id) throws Exception {
		return demandSummaryDomain.findDemandApplyById(id);
	}

	/** 培训申请明细 */
	public DemandApply doSaveDemandApply(DemandApply demandApply) throws Exception {
		DemandApply demandApply2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			demandApply2 = demandSummaryDomain.merge(demandApply);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训申请：" + demandApply + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训申请：" + demandApply + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训申请失败", ex);
		}
		return demandApply2;
	}

	/** 获取培训申请明细 */
	public DemandApply doListDemandApplyById(String id) {
		DemandApply detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = demandSummaryDomain.findDemandApplyById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训申请成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培申请师失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	public void deleteDemandApplyEntity(DemandApply demandApply) throws Exception {
		demandSummaryDomain.deleteDemandApplyEntity(demandApply);
	}

	/** 获取课程列表数据 */
	public Pager goDemandApplyList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = demandSummaryDomain.findPagerByHqlDemandApply(params, pager);
		return p;
	}

	public DemandApply merge(DemandApply demandApply) throws Exception {
		demandSummaryDomain.merge(demandApply);
		return null;
	}

	public void deleteByEntity(DemandSummary demandSummary) throws Exception {
		demandSummaryDomain.deleteByEntity(demandSummary);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		demandSummaryDomain.deleteByIds(ids);
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

	public Pager doSubSingleList(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3.执行查询操作
			p = demandSummaryDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询培训课程成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询培训课程失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	/**
	 * doSaveExecute实现删除培训汇总的业务逻辑处理
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
			demandSummaryDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除培训汇总成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除培训汇总失败！" + "失败原因:" + ex.getMessage());
		}
	}

	public DemandSummary doSaveTrainingCM(DemandSummary demandSummary, List<DemandApply> dlList) throws Exception {
		DemandSummary demandSummary2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != demandSummary) {
				demandSummary2 = demandSummaryDomain.merge(demandSummary2);
				if (null != dlList) {
					for (DemandApply da : dlList) {
						if (null != da) {
							da.setDemandSummary(demandSummary2);
							demandSummaryDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存培训汇总：" + demandSummary + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存培训汇总：" + demandSummary + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存培训汇总失败", ex);
		}
		return demandSummary2;

	}

}

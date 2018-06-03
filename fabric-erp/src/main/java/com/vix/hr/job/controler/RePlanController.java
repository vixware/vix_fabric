package com.vix.hr.job.controler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.domain.ReplanDomain;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.job.entity.HrRecruitmentPlanDetails;
import com.vix.hr.job.entity.HrRecruitplan;

@Controller("replanController")
@Scope("prototype")
public class RePlanController extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("RePlanController");

	@Autowired
	private ReplanDomain replanDomain;

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		if (params.containsKey("name,anylike")) {
			if ("undefined".equals(params.get("name,anylike"))) {
				params.remove("name,anylike");
			}
		}
		Pager p = replanDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrRecruitplan findEntityById(String id) throws Exception {
		return replanDomain.findEntityById(id);
	}

	public void deleteHrAttachmentsEntity(HrAttachments attachments) throws Exception {
		replanDomain.deleteHrAttachmentsEntity(attachments);
	}

	public HrAttachments findHrAttachmentsEntityById(String id) throws Exception {
		return replanDomain.findHrAttachmentsEntityById(id);
	}

	public HrAttachments mergeAttachments(HrAttachments attachments) throws Exception {
		return replanDomain.merge(attachments);
	}

	public HrRecruitplan merge(HrRecruitplan hrRecruitplan) throws Exception {
		replanDomain.merge(hrRecruitplan);
		return null;
	}

	public void deleteByEntity(HrRecruitplan hrRecruitplan) throws Exception {
		replanDomain.deleteByEntity(hrRecruitplan);
	}

	public void deleteHrRecruitmentPlanDetailsEntity(HrRecruitmentPlanDetails hrRecruitmentPlanDetails) throws Exception {
		replanDomain.deleteHrRecruitmentPlanDetailsEntity(hrRecruitmentPlanDetails);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		replanDomain.deleteByIds(ids);
	}

	public List<HrRecruitplan> findSalesOrderIndex() throws Exception {
		return replanDomain.findSalesOrderIndex();
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
	 * 根据Id获取招聘计划
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitplan doListEntityById(String id) {
		HrRecruitplan hrRecruitplan = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrRecruitplan = replanDomain.findEntityById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划信息失败！" + "失败原因:" + ex.getMessage());
		}

		return hrRecruitplan;
	}

	/**
	 * 根据Id获取招聘计划明细数据
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitmentPlanDetails doListHrRecruitmentPlanDetailsById(String id) {
		HrRecruitmentPlanDetails detailsTemp = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			detailsTemp = replanDomain.findHrRecruitmentPlanDetailsById(id);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划信息失败！" + "失败原因:" + ex.getMessage());
		}

		return detailsTemp;
	}

	/**
	 * 根据对象保存招聘计划数据
	 * 
	 * @param hrRecruitplan
	 * @return
	 */
	public HrRecruitplan doSaveRePlan(HrRecruitplan hrRecruitplan) {
		HrRecruitplan hrRecruitplan1 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitplan1 = replanDomain.merge(hrRecruitplan);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存招聘计划：" + hrRecruitplan + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存招聘计划：" + hrRecruitplan + "失败！失败原因：" + ex.getMessage());
		}
		return hrRecruitplan1;
	}

	/**
	 * 根据招聘计划子表对象保存招聘计划明细数据
	 * 
	 * @param hrRecruitmentPlanDetails
	 * @return
	 * @throws Exception
	 */
	public HrRecruitmentPlanDetails doSaveHrRecruitmentPlanDetails(HrRecruitmentPlanDetails hrRecruitmentPlanDetails) throws Exception {
		HrRecruitmentPlanDetails hrRecruitmentPlanDetails2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitmentPlanDetails2 = replanDomain.merge(hrRecruitmentPlanDetails);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存招聘计划：" + hrRecruitmentPlanDetails + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存招聘计划：" + hrRecruitmentPlanDetails + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存招聘计划失败", ex);
		}
		return hrRecruitmentPlanDetails2;
	}

	/**
	 * 用集合绑定招聘计划主表和子表的数据，一起提交保存招聘计划主表和子表的信息
	 * 
	 * @param hrRecruitplan
	 * @param dlList
	 * @return
	 * @throws Exception
	 */
	public HrRecruitplan doSaveRePlan(HrRecruitplan hrRecruitplan, List<HrRecruitmentPlanDetails> dlList) throws Exception {

		HrRecruitplan hrRecruitplanTemp2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			if (null != hrRecruitplan) {
				hrRecruitplanTemp2 = replanDomain.merge(hrRecruitplan);
				if (null != dlList) {
					for (HrRecruitmentPlanDetails da : dlList) {
						if (null != da) {
							da.setHrRecruitplan(hrRecruitplanTemp2);
							replanDomain.merge(da);
						}
					}
				}
			}
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存招聘计划：" + hrRecruitplan + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存招聘计划：" + hrRecruitplan + "失败！失败原因：" + ex.getMessage());
			throw new Exception("保存招聘计划失败", ex);
		}
		return hrRecruitplanTemp2;

	}

	/**
	 * 获取招聘计划列表页面搜索数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = replanDomain.findPagerByOrHqlConditions(params, pager);
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
			p = replanDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询招聘计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询招聘计划信息失败！" + "失败原因:" + ex.getMessage());
		}
		return p;
	}

	/**
	 * 根据对象删除招聘计划主表信息
	 * 
	 * @param hrRecruitplan
	 */
	public void doDeleteByEntity(HrRecruitplan hrRecruitplan) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			replanDomain.deleteByEntity(hrRecruitplan);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除招聘计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除招聘计划信息失败！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * doSaveExecute实现删除招聘计划的业务逻辑处理
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
			replanDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "删除招聘计划信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "删除招聘计划信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

package com.vix.hr.job.controler;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.job.domain.RecruitnoficaitionDomain;
import com.vix.hr.job.entity.HrRecruitnoficaition;

@Scope("prototype")
@Controller("recruitnoficaitionController")
public class RecruitnoficaitionController extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";
	Logger logger = Logger.getLogger("RecruitnoficaitionController");

	@Autowired
	private RecruitnoficaitionDomain recruitnoficaitionDomain;

	public RecruitnoficaitionDomain getRecruitnoficaitionDomain() {
		return recruitnoficaitionDomain;
	}

	public void setRecruitnoficaitionDomain(RecruitnoficaitionDomain recruitnoficaitionDomain) {
		this.recruitnoficaitionDomain = recruitnoficaitionDomain;
	}

	public Pager goSingleList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitnoficaitionDomain.findPagerByHqlConditions(params, pager);
		return p;
	}

	public HrRecruitnoficaition findEntityById(String id) throws Exception {
		return recruitnoficaitionDomain.findEntityById(id);
	}

	public HrRecruitnoficaition merge(HrRecruitnoficaition hrRecruitplan) throws Exception {
		recruitnoficaitionDomain.merge(hrRecruitplan);
		return null;
	}

	public void deleteByEntity(HrRecruitnoficaition hrRecruitplan) throws Exception {
		recruitnoficaitionDomain.deleteByEntity(hrRecruitplan);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		recruitnoficaitionDomain.deleteByIds(ids);
	}

	public List<HrRecruitnoficaition> findRecruitnoficaitionIndex() throws Exception {
		return recruitnoficaitionDomain.findRecruitnoficaitionIndex();
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
	 * 根据Id获取招聘征集信息
	 * 
	 * @param id
	 * @return
	 */
	public HrRecruitnoficaition doListEntityById(String id) {

		HrRecruitnoficaition hrRecruitnoficaition = null;

		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3.执行查询操作
			hrRecruitnoficaition = recruitnoficaitionDomain.findEntityById(id);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}

		return hrRecruitnoficaition;
	}

	/**
	 * 根据实体对象保存招聘征集信息
	 * 
	 * 人力资源部门通过此功能发起招聘信息的收集工作，在此增加一个新的招聘征集表，并开始引导给个部门提交相关的招聘需求。
	 * 
	 * @param hrRecruitnoficaition
	 * @return
	 */
	public HrRecruitnoficaition doSaveRecruitnoficaition(HrRecruitnoficaition hrRecruitnoficaition) {
		HrRecruitnoficaition hrRecruitnoficaition2 = null;
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");
			// 3. 执行保存操作
			hrRecruitnoficaition2 = recruitnoficaitionDomain.merge(hrRecruitnoficaition);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");
			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "保存订单：" + hrRecruitnoficaition + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "保存订单：" + hrRecruitnoficaition + "失败！失败原因：" + ex.getMessage());
		}
		return hrRecruitnoficaition2;
	}

	/**
	 * 获取招聘征集搜索列表数据
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager goSearchList(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = recruitnoficaitionDomain.findPagerByOrHqlConditions(params, pager);
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
			p = recruitnoficaitionDomain.findPagerByHqlConditions(params, pager);

			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {

		}
		return p;
	}

	/**
	 * 根据实体对象删除招聘征集信息
	 * 
	 * @param hrRecruitnoficaition
	 */
	public void doDeleteByEntity(HrRecruitnoficaition hrRecruitnoficaition) {
		try {
			// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
			// Map parameters = baExecutor.executeParameterProcess();

			// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
			beforeEventTrigger("PO_CREATE_BEFORE");

			// 3. 执行删除操作
			recruitnoficaitionDomain.deleteByEntity(hrRecruitnoficaition);
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
			recruitnoficaitionDomain.deleteByIds(ids);
			// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
			afterEventTrigger("PO_CREATE_AFTER");

			// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		} catch (Exception ex) {
			executeLogger(true, "删除订单信息失败！" + "失败原因:" + ex.getMessage());
		}
	}
}

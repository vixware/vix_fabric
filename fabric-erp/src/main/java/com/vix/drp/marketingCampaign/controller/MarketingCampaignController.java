/**
 * 
 */
package com.vix.drp.marketingCampaign.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.marketingCampaign.domain.MarketingCampaignDomain;
import com.vix.drp.marketingCampaign.entity.MarketingCampaign;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignDetail;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("marketingCampaignController")
@Scope("prototype")
public class MarketingCampaignController {

	Logger logger = Logger.getLogger(MarketingCampaignController.class);

	@Autowired
	private MarketingCampaignDomain marketingCampaignDomain;

	/**
	 * 
	 * @param marketingCampaign
	 * @return
	 * @throws Exception
	 */
	public MarketingCampaign doSaveOrUpdateMarketingCampaign(MarketingCampaign marketingCampaign, List<MarketingCampaignDetail> marketingCampaignDetailList) throws Exception {
		MarketingCampaign marketingCampaign1 = null;
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 设置类型为渠道类型

		marketingCampaign1 = marketingCampaignDomain.mergeMarketingCampaign(marketingCampaign);
		if (marketingCampaignDetailList != null && marketingCampaignDetailList.size() > 0) {
			for (MarketingCampaignDetail marketingCampaignDetail : marketingCampaignDetailList) {
				marketingCampaignDetail.setMarketingCampaign(marketingCampaign1);
				marketingCampaignDomain.saveOrUpdateMarketingCampaignDetail(marketingCampaignDetail);
			}
		}
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return marketingCampaign1;
	}

	public MarketingCampaign doSaveOrUpdateMarketingCampaign(MarketingCampaign marketingCampaign) throws Exception {
		MarketingCampaign marketingCampaign1 = null;
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 设置类型为渠道类型

		marketingCampaign1 = marketingCampaignDomain.mergeMarketingCampaign(marketingCampaign);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return marketingCampaign1;
	}

	public void doDeleteByIds(List<String> ids) throws Exception {
		marketingCampaignDomain.deleteByIds(ids);
	}

	public MarketingCampaignTask doSaveOrUpdateMarketingCampaignTask(MarketingCampaignTask marketingCampaignTask) throws Exception {
		MarketingCampaignTask marketingCampaignTask1 = null;
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 设置类型为渠道类型

		marketingCampaignTask1 = marketingCampaignDomain.mergeMarketingCampaignTask(marketingCampaignTask);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return marketingCampaignTask1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListMarketingCampaignPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行保存操作
		p = marketingCampaignDomain.findPagerByHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * @param marketingCampaign
	 * @throws Exception
	 */
	public void doDeleteMarketingCampaignByEntity(MarketingCampaign marketingCampaign) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		marketingCampaignDomain.deleteByEntity(marketingCampaign);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MarketingCampaign doListMarketingCampaignById(String id) throws Exception {
		MarketingCampaign marketingCampaign = new MarketingCampaign();
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		marketingCampaign = marketingCampaignDomain.findMarketingCampaignById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return marketingCampaign;
	}

	public ChannelDistributor doListChannelDistributorById(String id) throws Exception {
		ChannelDistributor channelDistributor = new ChannelDistributor();
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3.执行查询操作
		channelDistributor = marketingCampaignDomain.findChannelDistributorById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return channelDistributor;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<MarketingCampaign> doListMarketingCampaignList(Map<String, Object> params) throws Exception {
		List<MarketingCampaign> marketingCampaignList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		marketingCampaignList = marketingCampaignDomain.findMarketingCampaignList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return marketingCampaignList;
	}

	public List<ChannelDistributor> doListChannelDistributorList(Map<String, Object> params) throws Exception {
		List<ChannelDistributor> channelDistributorList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		channelDistributorList = marketingCampaignDomain.findChannelDistributorList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return channelDistributorList;
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
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
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

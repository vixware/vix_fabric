/**
 * 
 */
package com.vix.dtbcenter.deliveryroute.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.deliveryroute.domain.DeliveryRouteDomain;
import com.vix.dtbcenter.deliveryroute.entity.DispatchRoute;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("deliveryRouteController")
@Scope("prototype")
public class DeliveryRouteController {

	Logger logger = Logger.getLogger(DeliveryRouteController.class);

	@Autowired
	private DeliveryRouteDomain deliveryRouteDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public DispatchRoute doSaveOrUpdateDispatchRoute(DispatchRoute dispatchRoute) throws Exception {
		DispatchRoute dispatchRoute1 = null;
		try {
			dispatchRoute.setUpdateTime(new Date());
			dispatchRoute1 = deliveryRouteDomain.mergeDispatchRouter(dispatchRoute);
			executeLogger(true, "" + dispatchRoute1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + dispatchRoute1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return dispatchRoute1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListDispatchRoute(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行查询操作
			p = deliveryRouteDomain.findDispatchRoutePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListDispatchRouteByCon(String hql, Pager pager) {
		Pager p = null;
		try {

			// 3.执行查询操作
			p = deliveryRouteDomain.findDispatchRoutePagerByOrHqlConditions(hql, pager);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DispatchRoute doListDispatchRouteById(String id) throws Exception {
		DispatchRoute dispatchRoute = null;
		// 执行查询操作
		dispatchRoute = deliveryRouteDomain.findDispatchRouteById(id);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return dispatchRoute;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DispatchRoute> doListDispatchRouteList(Map<String, Object> params) throws Exception {
		List<DispatchRoute> dispatchRouteList = null;

		// 3. 执行查询操作
		dispatchRouteList = deliveryRouteDomain.findDispatchRouteList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return dispatchRouteList;
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
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		deliveryRouteDomain.evict(obj);
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

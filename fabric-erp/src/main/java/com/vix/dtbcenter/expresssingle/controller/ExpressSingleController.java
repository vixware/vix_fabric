/**
 * 
 */
package com.vix.dtbcenter.expresssingle.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.expresssingle.domain.ExpressSingleDomain;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingle;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingleDetail;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("expressSingleController")
@Scope("prototype")
public class ExpressSingleController {

	Logger logger = Logger.getLogger(ExpressSingleController.class);

	@Autowired
	private ExpressSingleDomain expressSingleDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public ExpressSingle doSaveExpressSingleDetail(ExpressSingle expressSingle1, List<ExpressSingleDetail> expressSingleDetailList) throws Exception {
		ExpressSingle expressSingle = null;
		try {
			expressSingle1.setUpdateTime(new Date());
			expressSingle = expressSingleDomain.mergeExpressSingle(expressSingle1);
			if (expressSingleDetailList != null && expressSingleDetailList.size() > 0) {
				for (ExpressSingleDetail expressSingleDetail : expressSingleDetailList) {
					expressSingleDomain.mergeExpressSingleDetail(expressSingleDetail);
				}
			}
			executeLogger(true, "" + expressSingle + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + expressSingle + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return expressSingle;
	}

	public ExpressSingleDetail doSaveExpressSingleDetail(ExpressSingleDetail expressSingleDetail) throws Exception {
		ExpressSingleDetail expressSingleDetail1 = null;
		try {
			expressSingleDetail.setUpdateTime(new Date());
			expressSingleDetail1 = expressSingleDomain.mergeExpressSingleDetail(expressSingleDetail);
			executeLogger(true, "" + expressSingleDetail1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + expressSingleDetail1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return expressSingleDetail1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListExpressSingle(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行查询操作
			p = expressSingleDomain.findExpressSinglePagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 
	 * @param expressSingle
	 */
	public void doDeleteExpressSingle(ExpressSingle expressSingle) {
		try {
			// 执行删除操作
			expressSingleDomain.deleteExpressSingleByEntity(expressSingle);
			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "！");
		} catch (Exception ex) {
			executeLogger(true, "！失败原因：" + ex.getMessage());
		}
	}

	public void doDeleteExpressSingleDetail(ExpressSingleDetail expressSingleDetail) {
		try {
			// 执行删除操作
			expressSingleDomain.deleteExpressSingleDetailByEntity(expressSingleDetail);
			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "！");
		} catch (Exception ex) {
			executeLogger(true, "！失败原因：" + ex.getMessage());
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public ExpressSingle doListExpressSingleById(String id) {
		ExpressSingle expressSingle = null;
		try {

			// 3.执行查询操作
			expressSingle = expressSingleDomain.findExpressSingleById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return expressSingle;
	}

	public ExpressSingleDetail doListExpressSingleDetailById(String id) {
		ExpressSingleDetail expressSingleDetail = null;
		try {

			// 3.执行查询操作
			expressSingleDetail = expressSingleDomain.findExpressSingleDetailById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return expressSingleDetail;
	}

	public Item doListItemById(String id) {
		Item item = null;
		try {

			// 3.执行查询操作
			item = expressSingleDomain.findItemById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询商品信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询商品信息失败！" + "失败原因:" + ex.getMessage());
		}

		return item;
	}

	public void convItemToGoodsDetail(ExpressSingle expressSingle, Item item) throws Exception {
		if (expressSingle != null) {
			// 将商品信息转化成货物信息
			if (item != null) {
				ExpressSingleDetail expressSingleDetail = new ExpressSingleDetail();
				expressSingleDetail.setExpressSingle(expressSingle);
				expressSingleDomain.mergeExpressSingleDetail(expressSingleDetail);
			}
		}
	}

	public Pager getItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = expressSingleDomain.findItem(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<ExpressSingle> doListExpressSingleList(Map<String, Object> params) throws Exception {
		List<ExpressSingle> expressSingle = null;

		// 3. 执行查询操作
		expressSingle = expressSingleDomain.findExpressSingleList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return expressSingle;
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
		expressSingleDomain.evict(obj);
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

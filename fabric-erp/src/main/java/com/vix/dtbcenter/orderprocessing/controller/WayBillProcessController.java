/**
 * 
 */
package com.vix.dtbcenter.orderprocessing.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.dtbcenter.orderprocessing.domain.WayBillProcessDomain;
import com.vix.dtbcenter.orderprocessing.entity.WayBill;
import com.vix.dtbcenter.orderprocessing.entity.WayBillItem;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("wayBillProcessController")
@Scope("prototype")
public class WayBillProcessController {

	Logger logger = Logger.getLogger(WayBillProcessController.class);

	@Autowired
	private WayBillProcessDomain wayBillProcessDomain;

	/**
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public WayBill doSaveWayBill(WayBill wayBill, List<WayBillItem> wayBillItemList) throws Exception {
		WayBill wayBill1 = null;
		try {
			wayBill.setUpdateTime(new Date());
			wayBill1 = wayBillProcessDomain.mergeWayBill(wayBill);
			if (wayBillItemList != null && wayBillItemList.size() > 0) {
				for (WayBillItem wayBillItem : wayBillItemList) {
					wayBillProcessDomain.mergeWayBillItem(wayBillItem);
				}
			}
			executeLogger(true, "" + wayBill1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + wayBill1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return wayBill1;
	}

	public WayBillItem doSaveWayBillItem(WayBillItem wayBillItem) throws Exception {
		WayBillItem wayBillItem1 = null;
		try {
			wayBillItem.setUpdateTime(new Date());
			if (wayBillItem.getPrice() != null && !"".equals(wayBillItem.getPrice()) && wayBillItem.getAmount() != null && !"".equals(wayBillItem.getAmount())) {
				wayBillItem.setNetTotal(wayBillItem.getPrice() * wayBillItem.getAmount());
			}
			wayBillItem1 = wayBillProcessDomain.mergeWayBillItem(wayBillItem);
			executeLogger(true, "" + wayBillItem1 + "成功！");
		} catch (Exception ex) {
			executeLogger(true, "" + wayBillItem1 + "失败！失败原因：" + ex.getMessage());
			throw new Exception("", ex);
		}
		return wayBillItem1;
	}

	/**
	 * 
	 * @param params
	 * @param pager
	 * @return
	 */
	public Pager doListWayBill(Map<String, Object> params, Pager pager) {
		Pager p = null;
		try {

			// 执行查询操作
			p = wayBillProcessDomain.findWayBillPagerByHqlConditions(params, pager);

			// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}
		return p;
	}

	/**
	 * 
	 * @param wayBill
	 */
	public void doDeleteWayBill(WayBill wayBill) {
		try {
			// 执行删除操作
			wayBillProcessDomain.deleteWayBillByEntity(wayBill);
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
	public WayBill doListWayBillById(String id) {
		WayBill wayBill = null;
		try {

			// 3.执行查询操作
			wayBill = wayBillProcessDomain.findWayBillById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return wayBill;
	}

	public WayBillItem doListWayBillItemById(String id) {
		WayBillItem wayBillItem = null;
		try {

			// 3.执行查询操作
			wayBillItem = wayBillProcessDomain.findWayBillItemById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			
		} catch (Exception ex) {
			
		}

		return wayBillItem;
	}

	public Item doListItemById(String id) {
		Item item = null;
		try {

			// 3.执行查询操作
			item = wayBillProcessDomain.findItemById(id);

			// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
			executeLogger(true, "查询商品信息成功！");
		} catch (Exception ex) {
			executeLogger(true, "查询商品信息失败！" + "失败原因:" + ex.getMessage());
		}

		return item;
	}

	public void convItemToGoodsDetail(WayBill wayBill, Item item) throws Exception {
		if (wayBill != null) {
			// 将商品信息转化成货物信息
			if (item != null) {
				WayBillItem wayBillItem = new WayBillItem();
				wayBillItem.setItemCode(item.getCode());
				wayBillItem.setName(item.getName());
				wayBillItem.setSpecification(item.getSpecification());
				wayBillItem.setWayBill(wayBill);
				wayBillProcessDomain.mergeWayBillItem(wayBillItem);
			}
		}
	}

	public Pager getItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = wayBillProcessDomain.findItem(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception 
	 */
	public List<WayBill> doListWayBillList(Map<String, Object> params) throws Exception {
		List<WayBill> wayBillList = null;

		// 3. 执行查询操作
		wayBillList = wayBillProcessDomain.findWayBillList(params);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return wayBillList;
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
		wayBillProcessDomain.evict(obj);
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

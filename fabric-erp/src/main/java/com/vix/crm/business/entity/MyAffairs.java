package com.vix.crm.business.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 我的事务 com.vix.crm.business.entity.MyAffairs
 *
 * @author zhanghaibing
 *
 * @date 2014年12月25日
 */
public class MyAffairs extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 事务类型
	 */
	private String affairsType;
	/**
	 * 重要性
	 */
	private String importance;
	/**
	 * 处理人
	 */
	private String processor;

	/**
	 * 延迟时间
	 */
	private Date delayTime;
	/**
	 * 采购订单
	 */
	private SalesOrder salesOrder;

	public String getAffairsType() {
		return affairsType;
	}

	public void setAffairsType(String affairsType) {
		this.affairsType = affairsType;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public Date getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Date delayTime) {
		this.delayTime = delayTime;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

}

/**
 * 
 */
package com.vix.drp.channel.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 短信计费规则
 * 
 * @类全名 com.vix.drp.channel.entity.SMSCostRules
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月1日
 */
public class SMSCostRules extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 每条短信价格
	 */
	private Double price;

	public SMSCostRules() {
		super();
	}

	public SMSCostRules(String id) {
		super();
		setId(id);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
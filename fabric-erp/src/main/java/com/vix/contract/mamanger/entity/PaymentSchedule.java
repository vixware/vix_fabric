/**   
* @Title: PaymentSchedule.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-19 下午2:52:30  
*/
package com.vix.contract.mamanger.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: PaymentSchedule
 * @Description:付款方式 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-19 下午2:52:30
 */
public class PaymentSchedule extends BaseEntity{

	private static final long serialVersionUID = 4252178531561926207L;
	/**
	 * 日期
	 */
	private String planDate;
	/**
	 * 金额
	 */
	private Double planMoney;
	/**
	 * 合同关联模板
	 *//*
	private ContractAssociateTemplate contractAssociateTemplate;*/
	
	public PaymentSchedule() {
		super();
	}
	
	public PaymentSchedule(String id) {
		super();
		setId(id);
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public Double getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(Double planMoney) {
		this.planMoney = planMoney;
	}

}

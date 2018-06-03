/**
 * 
 */
package com.vix.drp.integralRulesSet.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;

/**
 * 积分兑换记录
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class IntegralChangeHistory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会员
	 */
	private CustomerAccount customerAccount;
	/**
	 * 兑换商品
	 */
	private Item item;
	/**
	 * 消耗积分
	 */
	private Double integralAmount;
	/**
	 * 兑换日期
	 */
	private Date changeDate;
	/**
	 * 兑换金额
	 */
	private Double money;
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public String getCustomerName(){
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getItemName(){
		if(item != null){
			return item.getName();
		}
		return "";
	}
	public Double getIntegralAmount() {
		return integralAmount;
	}
	public void setIntegralAmount(Double integralAmount) {
		this.integralAmount = integralAmount;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getChangeDateStr(){
		if(changeDate != null){
			return DateUtil.format(changeDate);
		}
		return "";
	}
	public String getChangeDateTimeStr(){
		if(changeDate != null){
			return DateUtil.formatTime(changeDate);
		}
		return "";
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}

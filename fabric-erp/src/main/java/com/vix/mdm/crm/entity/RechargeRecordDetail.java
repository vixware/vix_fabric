package com.vix.mdm.crm.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 充值记录明细
 * 
 * @author jackie
 *
 */
public class RechargeRecordDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 充值记录
	 */
	private RechargeRecord rechargeRecord;
	/**
	 * 服务项目
	 */
	private Item item;
	/**
	 * 充值次数
	 */
	private Long number;
	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}
	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getitemName(){
		if(null != item){
			return item.getName();
		}
		return "";
	}
	public String getItemServiceContent(){
		if(item != null){
			return item.getServiceContent();
		}
		return "";
	}
}

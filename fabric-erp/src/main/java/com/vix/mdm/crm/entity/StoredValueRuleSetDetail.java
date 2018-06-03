package com.vix.mdm.crm.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

/**
 * 
 * @类全名 com.vix.mdm.crm.entity.StoredValueRuleSetDetail
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月12日
 */
public class StoredValueRuleSetDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoredValueRuleSet storedValueRuleSet;
	/**
	 * 服务项目
	 */
	private Item item;
	/**
	 * 次数
	 */
	private Long num;

	public StoredValueRuleSet getStoredValueRuleSet() {
		return storedValueRuleSet;
	}

	public void setStoredValueRuleSet(StoredValueRuleSet storedValueRuleSet) {
		this.storedValueRuleSet = storedValueRuleSet;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	public String getItemname(){
		if(item != null){
			return item.getName();
		}
		return "";
	}
	public String getServiceContent(){
		if(null != item){
			return item.getServiceContent();
		}
		return "";
	}
}

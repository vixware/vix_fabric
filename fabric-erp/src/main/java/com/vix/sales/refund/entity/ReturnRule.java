package com.vix.sales.refund.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;

/** 返款规则 */
public class ReturnRule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 返款规则类型 customerAccount: 客户 item: 物料*/
	private String rrType;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 物料 */
	private Item item;
	/** 最低销售数量 */
	private Double lowerSaleCount;
	/** 返款比率 */
	private Double ratio;
	/** 明细类型 count: 数字 money: 金额*/
	private String detailType;
	/** 返款规则明细 */
	private Set<ReturnRuleItem> returnRuleItems = new HashSet<ReturnRuleItem>();

	public ReturnRule(){}

	public String getRrType() {
		return rrType;
	}

	public void setRrType(String rrType) {
		this.rrType = rrType;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public String getCustomerAccountName() {
		if(null != customerAccount){
			return customerAccount.getName();
		}
		return "";
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Item getItem() {
		return item;
	}
	public String getItemName() {
		if(item != null){
			return item.getName();
		}
		return "";
	}
	public void setItem(Item item) {
		this.item = item;
	}

	public Double getLowerSaleCount() {
		return lowerSaleCount;
	}

	public void setLowerSaleCount(Double lowerSaleCount) {
		this.lowerSaleCount = lowerSaleCount;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public Set<ReturnRuleItem> getReturnRuleItems() {
		return returnRuleItems;
	}

	public void setReturnRuleItems(Set<ReturnRuleItem> returnRuleItems) {
		this.returnRuleItems = returnRuleItems;
	}
}

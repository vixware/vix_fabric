package com.vix.sales.commission.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.mdm.item.entity.Item;

public class SaleAmountItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 期间(月份) */
	private Long month;
	/** 季度 */
	private String quarter;
	/** 物料 */
	private Item item;
	/** 期间销售数量定额 */
	private Long saleCountQuota;
	/** 到当前期间的累计销售数量定额 */
	private Long saleCountQuotaTotal;
	/** 期间销售金额定额 */
	private Double saleAmountQuota;
	/** 到当前期间的累计销售金额定额 */
	private Double saleAmountQuotaTotal;
	/** 销售定额 */
	private SaleAmount saleAmount;

	public SaleAmountItem(){}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public String getQuarter() {
		if(null != month && null == quarter){
			if(month >= 1 && month <= 3){
				quarter = "1";
			}else if(month >= 4 && month <= 6){
				quarter = "2";
			}else if(month >= 7 && month <= 9){
				quarter = "3";
			}else if(month >= 10 && month <= 12){
				quarter = "4";
			}
		}
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Item getItem() {
		return item;
	}
	public String getItemName() {
		if(item != null) {
			return item.getName();
		}
		return "";		
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getSaleCountQuota() {
		return saleCountQuota;
	}

	public void setSaleCountQuota(Long saleCountQuota) {
		this.saleCountQuota = saleCountQuota;
	}

	public Long getSaleCountQuotaTotal() {
		return saleCountQuotaTotal;
	}

	public void setSaleCountQuotaTotal(Long saleCountQuotaTotal) {
		this.saleCountQuotaTotal = saleCountQuotaTotal;
	}

	public Double getSaleAmountQuota() {
		return saleAmountQuota;
	}

	public void setSaleAmountQuota(Double saleAmountQuota) {
		this.saleAmountQuota = saleAmountQuota;
	}

	public Double getSaleAmountQuotaTotal() {
		return saleAmountQuotaTotal;
	}

	public void setSaleAmountQuotaTotal(Double saleAmountQuotaTotal) {
		this.saleAmountQuotaTotal = saleAmountQuotaTotal;
	}

	public SaleAmount getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(SaleAmount saleAmount) {
		this.saleAmount = saleAmount;
	}
}

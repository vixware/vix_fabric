package com.vix.common.share.entity;
/**
 * 税率
 * @author jackie
 *
 */
public class TaxRate extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double taxRate;
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
}

/**
 * 
 */
package com.vix.system.precisionControl.entity;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseEntity;

/**
 * 数据精度
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-20
 */
public class PrecisionControl extends BaseEntity {
	private static final long serialVersionUID = -6092983906280140416L;
	/**
	 * 存货数量
	 */
	private Integer stockSizes;
	/**
	 * 存货单价
	 */
	private Integer stockPrice;
	/**
	 * 开票单价
	 */
	private Integer noteTheUnitPrice;
	/**
	 * 件数
	 */
	private Integer amount;
	/**
	 * 换算率
	 */
	private Integer conversiorate;
	/**
	 * 税率
	 */
	private Integer taxRate;
	/**
	 * 金额小数位
	 */
	private Integer amountDecimal;
	/**
	 * 存货体积小数位
	 */
	private Integer stockDimensionDecimal;
	/**
	 * 存货重量小数位
	 */
	private Integer stockWeightDecimal;

	/**
	 * 公司
	 */
	private Organization organization;

	public Integer getStockSizes() {
		return stockSizes;
	}

	public void setStockSizes(Integer stockSizes) {
		this.stockSizes = stockSizes;
	}

	public Integer getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Integer stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Integer getNoteTheUnitPrice() {
		return noteTheUnitPrice;
	}

	public void setNoteTheUnitPrice(Integer noteTheUnitPrice) {
		this.noteTheUnitPrice = noteTheUnitPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getConversiorate() {
		return conversiorate;
	}

	public void setConversiorate(Integer conversiorate) {
		this.conversiorate = conversiorate;
	}

	public Integer getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getAmountDecimal() {
		return amountDecimal;
	}

	public void setAmountDecimal(Integer amountDecimal) {
		this.amountDecimal = amountDecimal;
	}

	public Integer getStockDimensionDecimal() {
		return stockDimensionDecimal;
	}

	public void setStockDimensionDecimal(Integer stockDimensionDecimal) {
		this.stockDimensionDecimal = stockDimensionDecimal;
	}

	public Integer getStockWeightDecimal() {
		return stockWeightDecimal;
	}

	public void setStockWeightDecimal(Integer stockWeightDecimal) {
		this.stockWeightDecimal = stockWeightDecimal;
	}

}

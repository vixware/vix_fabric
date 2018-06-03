package com.vix.crm.business.vo;

/**
 * 商品销售信息
 * 
 * @author Think
 *
 */
public class CombinedSalesGoods {
	/**
	 * 商品名称A
	 */
	private String agoodsName;
	/**
	 * 购买A商品人数
	 */
	private Double apeoplenumber;
	/**
	 * 商品名称B
	 */
	private String bgoodsName;
	/**
	 * 购买B商品人数
	 */
	private Double bpeoplenumber;

	/**
	 * 购买连带率
	 */
	private Double purchaseJointRate;

	public String getAgoodsName() {
		return agoodsName;
	}

	public void setAgoodsName(String agoodsName) {
		this.agoodsName = agoodsName;
	}

	public String getBgoodsName() {
		return bgoodsName;
	}

	public void setBgoodsName(String bgoodsName) {
		this.bgoodsName = bgoodsName;
	}

	public Double getApeoplenumber() {
		return apeoplenumber;
	}

	public void setApeoplenumber(Double apeoplenumber) {
		this.apeoplenumber = apeoplenumber;
	}

	public Double getBpeoplenumber() {
		return bpeoplenumber;
	}

	public void setBpeoplenumber(Double bpeoplenumber) {
		this.bpeoplenumber = bpeoplenumber;
	}

	public Double getPurchaseJointRate() {
		return purchaseJointRate;
	}

	public void setPurchaseJointRate(Double purchaseJointRate) {
		this.purchaseJointRate = purchaseJointRate;
	}

}

package com.vix.crm.project.entity;

import com.vix.common.share.entity.BaseEntity;

/** 竞争产品 */
public class CompetitorProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 规格 */
	private String specification;
	/** 价格 */
	private Double price;
	/** 销售区域 */
	private String saleArea;
	/** 产品描述 */
	private String productContent;
	/** 竞争对手 */
	private Competitor competitor;
	
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSaleArea() {
		return saleArea;
	}
	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public Competitor getCompetitor() {
		return competitor;
	}
	public void setCompetitor(Competitor competitor) {
		this.competitor = competitor;
	}
}

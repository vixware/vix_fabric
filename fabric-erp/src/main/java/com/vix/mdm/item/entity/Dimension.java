package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 分类维度，用于分类下商品过滤搜索
 * 
 * @author arron
 *
 */
public class Dimension extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 顺序 */
	private Long orderBy;
	/** 维度明细 */
	// private Set<DimensionDetail> dimensionDetails = new
	// HashSet<DimensionDetail>();
	/** 所属分类 */
	private ItemCatalog itemCatalog;

	public Dimension() {
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public ItemCatalog getItemCatalog() {
		return itemCatalog;
	}

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}
	public String getItemCatalogName() {
		if (null != itemCatalog) {
			return itemCatalog.getName();
		}
		return "";
	}
}

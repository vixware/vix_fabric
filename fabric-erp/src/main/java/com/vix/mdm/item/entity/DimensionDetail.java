package com.vix.mdm.item.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 分类维度明细
 * @author arron
 *
 */
public class DimensionDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/** 维度明细名称 */
	private String name;
	/** 顺序 */
	private Long orderBy;
	/** 所属维度 */
	private Dimension dimension;
	
	public DimensionDetail(){}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getOrderBy() {
		return orderBy;
	}

	@Override
	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
}

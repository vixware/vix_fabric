package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @author bjitzhang
 *
 */
public class ProductRelation extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long relationId;

	private Long fromProductId;

	private Long toProductId;

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public Long getFromProductId() {
		return fromProductId;
	}

	public void setFromProductId(Long fromProductId) {
		this.fromProductId = fromProductId;
	}

	public Long getToProductId() {
		return toProductId;
	}

	public void setToProductId(Long toProductId) {
		this.toProductId = toProductId;
	}

}

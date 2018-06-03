/**
 * 
 */
package com.vix.inventory.batch.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 物料 批次号 关系表
 * 
 * @author zhanghaibing
 * 
 * @date 2013-8-1
 */
public class ItemToBaseCoder extends BaseEntity {
	private static final long serialVersionUID = -77889765221439934L;
	/**
	 * 物料ID
	 */
	private Long itemId;
	/**
	 * 批次号ID
	 */
	private String baseId;
	/**
	 * 编码类型
	 */
	private String codeType;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the baseId
	 */
	public String getBaseId() {
		return baseId;
	}

	/**
	 * @param baseId
	 *            the baseId to set
	 */
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}

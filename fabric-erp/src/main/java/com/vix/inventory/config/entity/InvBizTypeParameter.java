/**
 * 
 */
package com.vix.inventory.config.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存业务类型参数
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-30
 */
public class InvBizTypeParameter extends BaseEntity {
	private static final long serialVersionUID = -3811780192844178018L;
	/** 库存业务类型编码 */
	private String docTypeCode;
	/** 参数键 */
	private String paramKey;
	/** 参数值 */
	private String paramValue;

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}

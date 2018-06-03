/**
 * 
 */
package com.vix.inventory.config.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存业务类型
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-30
 */
public class InvBizType extends BaseEntity {
	private static final long serialVersionUID = -7669775365101165210L;
	/** 库存业务类型编码 */
	private String pdCode;
	/** 库存业务类型缩写 */
	private String invBizType;
	/** 库存组织 */
	private String invOrg;
	/** 库存部门 */
	private String invDeparment;

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getInvBizType() {
		return invBizType;
	}

	public void setInvBizType(String invBizType) {
		this.invBizType = invBizType;
	}

	public String getInvOrg() {
		return invOrg;
	}

	public void setInvOrg(String invOrg) {
		this.invOrg = invOrg;
	}

	public String getInvDeparment() {
		return invDeparment;
	}

	public void setInvDeparment(String invDeparment) {
		this.invDeparment = invDeparment;
	}

}

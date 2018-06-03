/**
 * 
 */
package com.vix.inventory.config.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 库存单据/凭证类型
 * 
 * @author zhanghaibing
 * 
 * @date 2013-7-30
 */
public class InvDocumentationType extends BaseEntity {
	private static final long serialVersionUID = 6461368144199649572L;

	/** 单据类型编码 */
	private String pdCode;
	/** 单据类型缩写 */
	private String formType;
	/** 库存组织 */
	private String purOrg;
	/** 库存部门 */
	private String purDeparment;

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getPurOrg() {
		return purOrg;
	}

	public void setPurOrg(String purOrg) {
		this.purOrg = purOrg;
	}

	public String getPurDeparment() {
		return purDeparment;
	}

	public void setPurDeparment(String purDeparment) {
		this.purDeparment = purDeparment;
	}

}

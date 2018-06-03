package com.vix.sales.credit.entity;

import com.vix.common.share.entity.BaseEntity;

/***
 * 信用控制条件按照单据类型可以进行分类显示, Tree Grid, Tree显示信用控制的单据.
 * @author Administrator
 *
 */
public class CreditControlItem extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 控制主体 */
	private String ccSubject;
	/** 控制类型 */
	private String ccType;
	/** 风险等级 */
	private String creditLevel;
	/** 控制值 */
	private String creditValue;
	/** 控制方法 */
	private String creditMethod;
	/** 是否生效 */
	private String isValid;
	/** 信用控制 */
	private CreditControl creditControl;
	
	public CreditControlItem(){}

	public String getCcSubject() {
		return ccSubject;
	}

	public void setCcSubject(String ccSubject) {
		this.ccSubject = ccSubject;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}

	public String getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}

	public String getCreditMethod() {
		return creditMethod;
	}

	public void setCreditMethod(String creditMethod) {
		this.creditMethod = creditMethod;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public CreditControl getCreditControl() {
		return creditControl;
	}

	public void setCreditControl(CreditControl creditControl) {
		this.creditControl = creditControl;
	}
}

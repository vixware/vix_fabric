/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 竞争分析
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class CompetitiveAssay extends BaseEntity {
	/** @pdOid 竞争因素 */
	private String caFactor;
	/** @pdOid 类型 */
	private String caType;
	public String getCaFactor() {
		return caFactor;
	}
	public void setCaFactor(String caFactor) {
		this.caFactor = caFactor;
	}
	public String getCaType() {
		return caType;
	}
	public void setCaType(String caType) {
		this.caType = caType;
	}
	
}

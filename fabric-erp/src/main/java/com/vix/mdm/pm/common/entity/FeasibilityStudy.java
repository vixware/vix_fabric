/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 可行性研究
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class FeasibilityStudy extends BaseEntity {
	/** @pdOid 研究报告名称 */
	private String fsName;
	/** @pdOid 研究影响 */
	private String fsEffect;
	/** @pdOid 报告内容 */
	private String fsContent;

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public String getFsEffect() {
		return fsEffect;
	}

	public void setFsEffect(String fsEffect) {
		this.fsEffect = fsEffect;
	}

	public String getFsContent() {
		return fsContent;
	}

	public void setFsContent(String fsContent) {
		this.fsContent = fsContent;
	}

}

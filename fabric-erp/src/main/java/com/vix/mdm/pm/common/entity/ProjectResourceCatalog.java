/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS资源分类
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectResourceCatalog extends BaseEntity {
	/** 分类编码 */
	private String ctCode;
	/** 分类名称 */
	private String ctName;

	public String getCtCode() {
		return ctCode;
	}

	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}

	public String getCtName() {
		return ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

}

/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目资源
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectResource extends BaseEntity {
	/** 资源名称 */
	private String resourceCode;
	/** 资源编码 */
	private String resourceName;
	/** 工作量占比 */
	private Double percent;
	/** 资源类型 */
	private String type;

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS资源负载
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class WbsAIMRsum extends BaseEntity {
	/** 资源名称 */
	private String resourceName;
	/** 项目编码 */
	private String projectCode;
	/** 使用开始日期 */
	private Date usedBeginTime;
	/** 使用结束日期 */
	private Date usedEndTime;
	/** 工作量 */
	private Double workLoad;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getUsedBeginTime() {
		return usedBeginTime;
	}

	public void setUsedBeginTime(Date usedBeginTime) {
		this.usedBeginTime = usedBeginTime;
	}

	public Date getUsedEndTime() {
		return usedEndTime;
	}

	public void setUsedEndTime(Date usedEndTime) {
		this.usedEndTime = usedEndTime;
	}

	public Double getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(Double workLoad) {
		this.workLoad = workLoad;
	}

}

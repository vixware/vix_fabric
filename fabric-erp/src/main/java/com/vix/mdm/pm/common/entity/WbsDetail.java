/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS明细详情
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class WbsDetail extends BaseEntity {
	/** 任务编码 */
	private String taskCode;
	/** 工作量 */
	private String workLoad;
	/** 限制日期 */
	private Date limitedTime;
	/** 里程碑 */
	private String mileStone;
	/** 是否项目阶段 */
	private String isProjectPhase;
	/** 项目阶段 */
	private String projectPhase;
	/** 自动发布任务 */
	private String isAutoPublish;
	/** 分类 */
	private String catalog;

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getWorkLoad() {
		return workLoad;
	}

	public void setWorkLoad(String workLoad) {
		this.workLoad = workLoad;
	}

	public Date getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(Date limitedTime) {
		this.limitedTime = limitedTime;
	}

	public String getMileStone() {
		return mileStone;
	}

	public void setMileStone(String mileStone) {
		this.mileStone = mileStone;
	}

	public String getIsProjectPhase() {
		return isProjectPhase;
	}

	public void setIsProjectPhase(String isProjectPhase) {
		this.isProjectPhase = isProjectPhase;
	}

	public String getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}

	public String getIsAutoPublish() {
		return isAutoPublish;
	}

	public void setIsAutoPublish(String isAutoPublish) {
		this.isAutoPublish = isAutoPublish;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

}

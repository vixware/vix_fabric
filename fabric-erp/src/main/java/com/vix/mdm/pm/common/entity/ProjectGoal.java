/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目目标
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectGoal extends BaseEntity {
	/** 目标名称 */
	private String targetName;
	/** 目标类型（约束性目标\成果性目标） */
	private String targetType;
	/** 目标达成情况 */
	private String targetSituation;
	/** 目标状态 */
	private String targetStatus;
	/** 目标内容 */
	private String targetContent;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetSituation() {
		return targetSituation;
	}

	public void setTargetSituation(String targetSituation) {
		this.targetSituation = targetSituation;
	}

	public String getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}

	public String getTargetContent() {
		return targetContent;
	}

	public void setTargetContent(String targetContent) {
		this.targetContent = targetContent;
	}

}

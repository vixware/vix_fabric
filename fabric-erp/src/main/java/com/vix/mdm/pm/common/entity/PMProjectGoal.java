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
 * @date 2014-1-22
 */
public class PMProjectGoal extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1354803730779443800L;
	/** @pdOid 目标名称 */
	private String targetName;
	/** @pdOid 目标类型（约束性目标\成果性目标） */
	private String targetType;
	/** @pdOid 目标达成情况 */
	private String targetCompletion;
	/** @pdOid 目标状态 */
	private String targetStatus;
	/** @pdOid 目标内容 */
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

	public String getTargetCompletion() {
		return targetCompletion;
	}

	public void setTargetCompletion(String targetCompletion) {
		this.targetCompletion = targetCompletion;
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

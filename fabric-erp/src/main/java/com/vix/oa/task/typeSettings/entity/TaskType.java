package com.vix.oa.task.typeSettings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassName: TaskType
 * @Description: 任务类型
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-2-24 下午4:44:48
 */
public class TaskType extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 不能删除 */
	private String notDeleted;
	/** 是否启用 */
	private String enable;
	/** 是否缺省 */
	private String isDefault;
	/** 备注 */
	private String memo;

	/** 任务定义 */
	private Set<VixTask> taskDefinition = new HashSet<VixTask>();

	public TaskType() {
	}

	public Set<VixTask> getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(Set<VixTask> taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public String getNotDeleted() {
		return notDeleted;
	}

	public void setNotDeleted(String notDeleted) {
		this.notDeleted = notDeleted;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

}

package com.vix.oa.task.typeSettings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassName: TaskSourceType
 * @Description: 任务来源 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-24 下午4:34:19
 */
public class TaskSourceType extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	/** 任务定义*/
	private Set<VixTask> taskDefinition = new HashSet<VixTask>();
	
	public TaskSourceType(){}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Set<VixTask> getTaskDefinition() {
		return taskDefinition;
	}

	public void setTaskDefinition(Set<VixTask> taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

}

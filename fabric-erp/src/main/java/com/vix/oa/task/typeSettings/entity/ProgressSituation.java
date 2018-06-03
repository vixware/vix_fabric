package com.vix.oa.task.typeSettings.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;

/**
 * 
 * @ClassName: ProgressSituation
 * @Description: 进度情况 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-5 下午1:57:05
 */
public class ProgressSituation extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private String code;
	/** 名称 */
	private String name;
	/** 是否启用 */
	private String disabled;
	
	/** 进度情况*/
	private Set<ExecutionFeedback> executionFeedback = new HashSet<ExecutionFeedback>();
	
	public ProgressSituation(){}

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

	public Set<ExecutionFeedback> getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(Set<ExecutionFeedback> executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

}

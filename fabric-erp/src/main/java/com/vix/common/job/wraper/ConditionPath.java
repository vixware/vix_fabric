package com.vix.common.job.wraper;

/**
 * 流程的路由条件
 * @author Administrator
 *
 */
public class ConditionPath {
	
	private String id;//路由id
	private String name;//路由名称
	private String conditionRule;//路由规则
	
	public ConditionPath(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConditionRule() {
		return conditionRule;
	}

	public void setConditionRule(String conditionRule) {
		this.conditionRule = conditionRule;
	}

}

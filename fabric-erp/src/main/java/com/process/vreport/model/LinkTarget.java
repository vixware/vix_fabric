package com.process.vreport.model;

public class LinkTarget {

	private String text;
	
	private String condition;
	
	private String targetId;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	public String getTargetId() {
		return targetId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}

package com.process.vreport.model;

import java.util.List;

public class Link {

	private String id;
	private String text;
	private String condition;
	private String from;
	private Integer isMul;
	private List<LinkTarget> to;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<LinkTarget> getTo() {
		return to;
	}
	
	public void setTo(List<LinkTarget> to) {
		this.to = to;
	}

	public Integer getIsMul() {
		return isMul;
	}
	public void setIsMul(Integer isMul) {
		this.isMul = isMul;
	}
}

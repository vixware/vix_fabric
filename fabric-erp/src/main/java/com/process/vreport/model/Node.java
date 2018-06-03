package com.process.vreport.model;

import java.util.Map;

public class Node {

	private Integer x;
	private Integer y;
	private String id;
	private String text;
	private String type;
	private String value;
	private String icon;
	private String param;
	private Map<String,String> ext;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Map<String, String> getExt() {
		return ext;
	}
	
	public String getParam() {
		return param;
	}
	
	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	public Integer getX() {
		return x;
	}
	public Integer getY() {
		return y;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
}

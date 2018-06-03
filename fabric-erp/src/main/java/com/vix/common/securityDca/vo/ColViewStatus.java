package com.vix.common.securityDca.vo;

import java.io.Serializable;

public class ColViewStatus implements Serializable {

	private String viewId;
	
	private String viewName;

	public ColViewStatus(String viewId, String viewName) {
		super();
		this.viewId = viewId;
		this.viewName = viewName;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	
}

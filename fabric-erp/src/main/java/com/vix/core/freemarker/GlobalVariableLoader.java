package com.vix.core.freemarker;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;

public class GlobalVariableLoader extends FreemarkerManager {

	private Map<String,Object> root = new HashMap<String,Object>();
	
	@Override
	protected void loadSettings(ServletContext serverContext) {
		super.loadSettings(serverContext);
	}
	
	public Map<String, Object> getRoot() {
		return root;
	}
	
	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}
}

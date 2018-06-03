package com.vix.core.freemarker;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.struts2.views.freemarker.FreemarkerManager;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;

public class SnowInternationalLoader extends FreemarkerManager {

	private Map<String,Object> root = new HashMap<String,Object>();
	
	@Override
	protected void loadSettings(ServletContext serverContext) {
		ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("ApplicationResources");
		   ResourceBundleModel rsbm = new ResourceBundleModel(RESOURCE_BUNDLE,new BeansWrapper());
		   root.put("bundle", rsbm);
	}
	
	public Map<String, Object> getRoot() {
		return root;
	}
	
	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}
}
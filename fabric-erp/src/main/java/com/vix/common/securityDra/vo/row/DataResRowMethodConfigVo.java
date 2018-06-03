package com.vix.common.securityDra.vo.row;

import java.util.List;

import com.vix.common.security.entity.DataResRowMethodConfig;

public class DataResRowMethodConfigVo extends DataResRowMethodConfig {

	private String metaDataName;
	
	private List<String> methodArrayList;
	
	//private BiMap<String,String> metaDataMethodMap
	
	
	
	
	private String hqlProvider;

	/** 拦截hqlprovider包含哪些方法 */
	private String methodList;

	public DataResRowMethodConfigVo() {
		super();
	}
	
	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public List<String> getMethodArrayList() {
		return methodArrayList;
	}

	public void setMethodArrayList(List<String> methodArrayList) {
		this.methodArrayList = methodArrayList;
	}

	public String getHqlProvider() {
		return hqlProvider;
	}

	public void setHqlProvider(String hqlProvider) {
		this.hqlProvider = hqlProvider;
	}

	public String getMethodList() {
		return methodList;
	}

	public void setMethodList(String methodList) {
		this.methodList = methodList;
	}

}

package com.vix.common.securityDca.vo;

public class UserAccountColConfigInfo {

	private String id;
	
    /** 元数据全称 */
    private String metaDataName;
    
    /** 属性 */
    private String property;
    
    /** 列表页面属性可视状态 */
    private String viewListStatus;
    
    /** 详细页面属性可视状态 */
    private String viewDetailStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getViewListStatus() {
		return viewListStatus;
	}

	public void setViewListStatus(String viewListStatus) {
		this.viewListStatus = viewListStatus;
	}

	public String getViewDetailStatus() {
		return viewDetailStatus;
	}

	public void setViewDetailStatus(String viewDetailStatus) {
		this.viewDetailStatus = viewDetailStatus;
	}
    
}

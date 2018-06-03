package com.vix.common.securityDca.vo;

import java.io.Serializable;

import com.vix.common.security.entity.DataResColPolicy;


public class MetaDataProperty extends DataResColPolicy implements Serializable {

	/** metadataDefine 或者 metadaDefineExtend的id ＋ | ＋ 类型（D为基本，E为扩展） */
	
	private String mdpId;
	
	/**D为基本，E为扩展，S为系统常量  */
	private String type;
	
	private String propertyName;
	
	
	private String property;
	
	/** 为系统常量时 的id */
	private String sysParamId;
	
	/** 数据的选择链接 
	 * 
	 */
	private String sysParamUrl;
	
	
	//行集权限使用
	//private String isCollectionType;
	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public MetaDataProperty() {
		super();
	}

	public MetaDataProperty(String type, String propertyName, String property) {
		super();
		this.type = type;
		this.propertyName = propertyName;
		this.property = property;
	}


	public MetaDataProperty(String type, String propertyName, String property,
			String sysParamId, String sysParamUrl) {
		super();
		this.type = type;
		this.propertyName = propertyName;
		this.property = property;
		this.sysParamId = sysParamId;
		this.sysParamUrl = sysParamUrl;
	}

	public String getMdpId() {
		return mdpId;
	}

	public void setMdpId(String mdpId) {
		this.mdpId = mdpId;
	}


	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSysParamId() {
		return sysParamId;
	}

	public void setSysParamId(String sysParamId) {
		this.sysParamId = sysParamId;
	}

	public String getSysParamUrl() {
		return sysParamUrl;
	}

	public void setSysParamUrl(String sysParamUrl) {
		this.sysParamUrl = sysParamUrl;
	}

	/*public String getIsCollectionType() {
		return isCollectionType;
	}

	public void setIsCollectionType(String isCollectionType) {
		this.isCollectionType = isCollectionType;
	}*/
	
}

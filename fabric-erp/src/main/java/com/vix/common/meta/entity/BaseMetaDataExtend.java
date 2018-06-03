package com.vix.common.meta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: BaseMetaDataExtend
 * @Description: 元数据扩展(用户自定义)
 * @author wangmingchen
 * @date 2013-4-18 下午9:35:51
 *
 */
public class BaseMetaDataExtend extends BaseEntity {

	/** 自定对象名称 */
	private String custBoName;
	
	/** 业务对象 */
	private String boName;
	
	/** 业务对象类型  */
	private String boType;
	
	/** 属性描述 */
	private String propertyName;
	
	/** 属性编码 */
	private String propertyCode;
	
	/**
	 * 数据类型
	 * 比如 com.vix.common.org.entity.OrgPosition
	 * 如果类中声明是集合的 如 Set<OrgPosition> 其类型仍然为上述的
	 */
	private String propertyType;
	
	/** 属性 */
	private String property;
	
	/**  数据类型
	 * 这里为简单类型
	 * 业务对象使用使用propetyType
	 *  
	 *  */
	private String dataType;
	

	/** 是否集合属性 
	 * 0 null 空 都为简单属性
	 * 1  业务对象属性
	 * 2 集合属性
	 * （List Set 等等   用户数据权限的标识） 
	 * */
	private String isCollectionType;
	/** 元数据属性需要从用户登录中获取值的key */
	//private String collectionTypeUserContext;
	/** 是否显示 （  用户数据权限的业务对象的选择  是否在列表中显示 ） */
	private String isSelectView;

	
	/** 默认值 */
	private String defaultValue;
	
	/** 行业编码 */
	private String industryCode;
	
	/** 用户编码 */
	private String userId;
	
	/** 部门编码 */
	private String orgUnitId;
	
	/**  行 */
	private String rowName;
	
	/**  列 */
	private String columnName;
	
    /** 元数据对象  */
    private BaseMetaData baseMetaData;

    public BaseMetaData getBaseMetaData() {
        return baseMetaData;
    }

    public void setBaseMetaData(BaseMetaData baseMetaData) {
        this.baseMetaData = baseMetaData;
    }

	public String getCustBoName() {
		return custBoName;
	}

	public void setCustBoName(String custBoName) {
		this.custBoName = custBoName;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getIsCollectionType() {
		return isCollectionType;
	}

	public void setIsCollectionType(String isCollectionType) {
		this.isCollectionType = isCollectionType;
	}

	public String getIsSelectView() {
		return isSelectView;
	}

	public void setIsSelectView(String isSelectView) {
		this.isSelectView = isSelectView;
	}


}

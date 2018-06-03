package com.vix.common.meta.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: BaseMetaDataDefine
 * @Description: 元数据定义(固有属性)
 * @author wangmingchen
 * @date 2013-4-18 下午9:35:01
 *
 */
public class BaseMetaDataDefine extends BaseEntity {

	/** 业务对象类型 */
	private String boType;
	
	/** 属性描述 */
	private String propertyName;
	
	/** 属性编码 */
	private String propertyCode;

	/** 属性
	 * 业务对象的真实属性
	 *  */
	private String property;
	/**
	 * 数据类型
	 * 比如 com.vix.common.org.entity.OrgPosition
	 * 如果类中声明是集合的 如 Set<OrgPosition> 其类型仍然为上述的
	 */
	private String propertyType;

	/**  简单数据类型
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
	
	//下面属性没有使用

	/** 位置X */
	private String xpos;

	/** 位置Y */
	private String ypos;

	/** 行 */
	private String row;

	/** 列 */
	private String columnName;

	/** 颜色 */
	private String color;

	/**  样式 */
	private String style;

	/** 字体  */
	private String font;

	/** 字体大小 */
	private String fontSize;

	/** 行业编码 */
	private String industryCode;	
	
    /** 元数据对象  */
    private BaseMetaData baseMetaData;
    
    /**  
     * S  选择
     * 
     * */
    private String dataSelectType;
    /** 数据选择url 
    private String url;*/

    
    
    
    //非持久化属性
    /**  当属性是业务对象属性（集合）时  对应的元数据的id */
    private String propertyMetadataId;
    
    //bianma
    private String baseMetaDataCode;
    
    public BaseMetaData getBaseMetaData() {
        return baseMetaData;
    }

    public void setBaseMetaData(BaseMetaData baseMetaData) {
        this.baseMetaData = baseMetaData;
    }

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
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

	public String getXpos() {
		return xpos;
	}

	public void setXpos(String xpos) {
		this.xpos = xpos;
	}

	public String getYpos() {
		return ypos;
	}

	public void setYpos(String ypos) {
		this.ypos = ypos;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getDataSelectType() {
		return dataSelectType;
	}

	public void setDataSelectType(String dataSelectType) {
		this.dataSelectType = dataSelectType;
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

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyMetadataId() {
		return propertyMetadataId;
	}

	public void setPropertyMetadataId(String propertyMetadataId) {
		this.propertyMetadataId = propertyMetadataId;
	}

	public String getBaseMetaDataCode() {
		return baseMetaDataCode;
	}

	public void setBaseMetaDataCode(String baseMetaDataCode) {
		this.baseMetaDataCode = baseMetaDataCode;
	}

}

package com.vix.common.meta.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: BaseMetaData
 * @Description: 元数据对象
 * @author wangmingchen
 * @date 2013-4-20 上午10:43:29
 *
 */
public class BaseMetaData extends BaseEntity {

	/** 元数据对象名称 */
	private String metaDataName;
	
	/** 元数据对象显示名称 */
	private String metaDataDisplayName;
	
	/** 元数据对象分类 */
	private BaseMetaDataCategory baseMetaDataCategory;
	
    /** 元数据固有属性  */
    private Set<BaseMetaDataDefine> baseMetaDataDefines;
    /** 元数据扩展属性  */
    private Set<BaseMetaDataExtend> baseMetaDataExtends;
    
    
    
    
    //非持久化属性
    private String baseMetaDataCategoryCode;
   
    public BaseMetaData() {
		super();
	}

    public BaseMetaData(String id) {
		super();
		setId(id);
	}
    
	public Set<BaseMetaDataDefine> getBaseMetaDataDefines() {
        return baseMetaDataDefines;
    }
    
    public void setBaseMetaDataDefines(Set<BaseMetaDataDefine> baseMetaDataDefines) {
        this.baseMetaDataDefines = baseMetaDataDefines;
    }
    
    public Set<BaseMetaDataExtend> getBaseMetaDataExtends() {
        return baseMetaDataExtends;
    }
    
    public void setBaseMetaDataExtends(Set<BaseMetaDataExtend> baseMetaDataExtends) {
        this.baseMetaDataExtends = baseMetaDataExtends;
    }

	public String getMetaDataName() {
		return metaDataName;
	}

	public void setMetaDataName(String metaDataName) {
		this.metaDataName = metaDataName;
	}

	public String getMetaDataDisplayName() {
		return metaDataDisplayName;
	}

	public void setMetaDataDisplayName(String metaDataDisplayName) {
		this.metaDataDisplayName = metaDataDisplayName;
	}

	public BaseMetaDataCategory getBaseMetaDataCategory() {
		return baseMetaDataCategory;
	}

	public void setBaseMetaDataCategory(BaseMetaDataCategory baseMetaDataCategory) {
		this.baseMetaDataCategory = baseMetaDataCategory;
	}

	public String getBaseMetaDataCategoryCode() {
		return baseMetaDataCategoryCode;
	}

	public void setBaseMetaDataCategoryCode(String baseMetaDataCategoryCode) {
		this.baseMetaDataCategoryCode = baseMetaDataCategoryCode;
	}
    
}

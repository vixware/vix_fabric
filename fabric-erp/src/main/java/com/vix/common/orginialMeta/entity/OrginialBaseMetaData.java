package com.vix.common.orginialMeta.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;

/**
 * @ClassName: BaseMetaData
 * @Description: 元数据对象
 * @author wangmingchen
 * @date 2013-4-20 上午10:43:29
 *
 */
public class OrginialBaseMetaData extends BaseEntity {

	/** 元数据对象名称 */
	private String metaDataName;
	
	/** 元数据对象显示名称 */
	private String metaDataDisplayName;
	
	/** 元数据对象分类 */
	private OrginialBaseMetaDataCategory baseMetaDataCategory;
	
    /** 元数据固有属性  */
    private Set<OrginialBaseMetaDataDefine> baseMetaDataDefines;
    /** 元数据扩展属性  */
    private Set<OrginialBaseMetaDataExtend> baseMetaDataExtends;
    
    
    /** 所属行业模块 */
	private Set<IndustryManagementModule> industryManagementModules = new HashSet<IndustryManagementModule>();
	
    
    //非持久化属性
    private String baseMetaDataCategoryCode;
   
    public OrginialBaseMetaData() {
		super();
	}

    public OrginialBaseMetaData(String id) {
		super();
		setId(id);
	}
    
	public Set<OrginialBaseMetaDataDefine> getBaseMetaDataDefines() {
        return baseMetaDataDefines;
    }
    
    public void setBaseMetaDataDefines(Set<OrginialBaseMetaDataDefine> baseMetaDataDefines) {
        this.baseMetaDataDefines = baseMetaDataDefines;
    }
    
    public Set<OrginialBaseMetaDataExtend> getBaseMetaDataExtends() {
        return baseMetaDataExtends;
    }
    
    public void setBaseMetaDataExtends(Set<OrginialBaseMetaDataExtend> baseMetaDataExtends) {
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

	public OrginialBaseMetaDataCategory getBaseMetaDataCategory() {
		return baseMetaDataCategory;
	}

	public void setBaseMetaDataCategory(OrginialBaseMetaDataCategory baseMetaDataCategory) {
		this.baseMetaDataCategory = baseMetaDataCategory;
	}

	public String getBaseMetaDataCategoryCode() {
		return baseMetaDataCategoryCode;
	}

	public void setBaseMetaDataCategoryCode(String baseMetaDataCategoryCode) {
		this.baseMetaDataCategoryCode = baseMetaDataCategoryCode;
	}

	public Set<IndustryManagementModule> getIndustryManagementModules() {
		return industryManagementModules;
	}

	public void setIndustryManagementModules(
			Set<IndustryManagementModule> industryManagementModules) {
		this.industryManagementModules = industryManagementModules;
	}
    
}

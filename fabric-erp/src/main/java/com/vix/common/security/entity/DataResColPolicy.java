package com.vix.common.security.entity;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DataResourceColPolicy
 * @Description: 列级数据权限策略配置
 * 初步考虑  存储要求控制的权限
 * @author wangmingchen
 * @date 2013-4-18 下午9:03:43
 *
 */
public class DataResColPolicy extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /** 人事范围 */
    //private String rangeScope;
    /** 列表页面属性可视状态 */
    private String viewListStatus;
    /** 详细页面属性可视状态 */
    private String viewDetailStatus;
   
    /** 组织框架分类(0:按照公司级别,1,按照组织机构) */
    private String orgSysType;
    
    /** 公司编码 */
    private String compCode;
    /** 组织机构编码 */
    private String orgCode;
    
    /**
     * 组织机构可视约束
     * CY:子部门都要按此规则,
     * CN:子部门不按照,只有该部门下面的员工按照
     */
    private String orgViewConfig;
    
    /** 列级权限控制 */
    private DataResColConfig dataResColConfig;
    
    /** 元数据对象 */
    private BaseMetaData baseMetaData;
    
    /** 元数据属性 */
    private BaseMetaDataDefine baseMetaDataDefine;
    
    /** 元数据扩展属性 */
    private BaseMetaDataExtend baseMetaDataExtend;

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

    public String getOrgSysType() {
        return orgSysType;
    }

    public void setOrgSysType(String orgSysType) {
        this.orgSysType = orgSysType;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgViewConfig() {
        return orgViewConfig;
    }

    public void setOrgViewConfig(String orgViewConfig) {
        this.orgViewConfig = orgViewConfig;
    }

    public DataResColConfig getDataResColConfig() {
        return dataResColConfig;
    }

    public void setDataResColConfig(DataResColConfig dataResColConfig) {
        this.dataResColConfig = dataResColConfig;
    }

    public BaseMetaData getBaseMetaData() {
        return baseMetaData;
    }

    public void setBaseMetaData(BaseMetaData baseMetaData) {
        this.baseMetaData = baseMetaData;
    }

	public BaseMetaDataDefine getBaseMetaDataDefine() {
		return baseMetaDataDefine;
	}

	public void setBaseMetaDataDefine(BaseMetaDataDefine baseMetaDataDefine) {
		this.baseMetaDataDefine = baseMetaDataDefine;
	}

	public BaseMetaDataExtend getBaseMetaDataExtend() {
		return baseMetaDataExtend;
	}

	public void setBaseMetaDataExtend(BaseMetaDataExtend baseMetaDataExtend) {
		this.baseMetaDataExtend = baseMetaDataExtend;
	}

    
}

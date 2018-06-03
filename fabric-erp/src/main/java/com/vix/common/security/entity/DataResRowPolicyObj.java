package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DataResRowPolicyObj
 * @Description: 业务对象数据权限标识
 * @author wangmingchen
 * @date 2013-4-18 下午10:07:38
 *
 */
public class DataResRowPolicyObj extends BaseEntity {

  
    private static final long serialVersionUID = 1L;
    
    /** 业务对象全称 */
    private String metaDataSrcName;
    /** 权限标识名称 */
    private String metaDataViewName;
    
    /** 权限优先级 */
    private Integer priory;
    
    /**  行级权限策略 */
    private Set<DataResRowPolicy> dataResRowPolicys;
    /** 业务对象数据权限标识的具体设置 */
    //private Set<DataResRowPolicyObjConfig> dataResRowPolicyObjConfigs;
    
    /** 元数据对象 */
    private BaseMetaData baseMetaData;
    /** 元数据权限配置 JSON */
    private String dataConfigRule;
    
    
    /**
     * 编译过的hql
     */
    private String wheres;
    /**
     * 编译过的变量 map
     */
    private String paramsMap;
    
    /**
     * 用于存放业务对象属性 或者 集合业务对象属性的条件下 的约束条件
     */
    private String bizAndSetParamsJson;
    /** 嵌入hql中使用的key， 存放如  {SysUserAccount},{SysUserRole} */
    private String bizHqlMap;
    
    public String getMetaDataSrcName() {
        return metaDataSrcName;
    }
    public void setMetaDataSrcName(String metaDataSrcName) {
        this.metaDataSrcName = metaDataSrcName;
    }
    public String getMetaDataViewName() {
        return metaDataViewName;
    }
    public void setMetaDataViewName(String metaDataViewName) {
        this.metaDataViewName = metaDataViewName;
    }
    public Integer getPriory() {
        return priory;
    }
    public void setPriory(Integer priory) {
        this.priory = priory;
    }
	public Set<DataResRowPolicy> getDataResRowPolicys() {
		return dataResRowPolicys;
	}
	public void setDataResRowPolicys(Set<DataResRowPolicy> dataResRowPolicys) {
		this.dataResRowPolicys = dataResRowPolicys;
	}
	public BaseMetaData getBaseMetaData() {
		return baseMetaData;
	}
	public void setBaseMetaData(BaseMetaData baseMetaData) {
		this.baseMetaData = baseMetaData;
	}
	public String getDataConfigRule() {
		return dataConfigRule;
	}
	public void setDataConfigRule(String dataConfigRule) {
		this.dataConfigRule = dataConfigRule;
	}
	public String getWheres() {
		return wheres;
	}
	public void setWheres(String wheres) {
		this.wheres = wheres;
	}
	public String getParamsMap() {
		return paramsMap;
	}
	public void setParamsMap(String paramsMap) {
		this.paramsMap = paramsMap;
	}
	public String getBizAndSetParamsJson() {
		return bizAndSetParamsJson;
	}
	public void setBizAndSetParamsJson(String bizAndSetParamsJson) {
		this.bizAndSetParamsJson = bizAndSetParamsJson;
	}
	public String getBizHqlMap() {
		return bizHqlMap;
	}
	public void setBizHqlMap(String bizHqlMap) {
		this.bizHqlMap = bizHqlMap;
	}
	
}

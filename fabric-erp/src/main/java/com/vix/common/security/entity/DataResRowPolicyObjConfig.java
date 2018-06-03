package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 
 * @ClassName: DataResRowPolicyObjConfig
 * @Description: 业务对象数据权限标识的具体设置
 * @author wangmingchen
 * @date 2013-4-18 下午10:10:47
 *
 */
public class DataResRowPolicyObjConfig extends BaseEntity {

    /** 对象业务权限名称 */
    private String dataPriName;
    /** 业务对象权限规则（核心，字符串描述数据） */
    private String priRule;
    /** 权限规则编译sql */
    private String ruleCompSql;
    /** 权限规则编译hql */
    private String ruleCompHql;

    /** 业务对象数据权限标识 */
    private DataResRowPolicyObj dataResRowPolicyObj;

    public String getDataPriName() {
        return dataPriName;
    }

    public void setDataPriName(String dataPriName) {
        this.dataPriName = dataPriName;
    }

    public String getPriRule() {
        return priRule;
    }

    public void setPriRule(String priRule) {
        this.priRule = priRule;
    }

    public String getRuleCompSql() {
        return ruleCompSql;
    }

    public void setRuleCompSql(String ruleCompSql) {
        this.ruleCompSql = ruleCompSql;
    }

    public String getRuleCompHql() {
        return ruleCompHql;
    }

    public void setRuleCompHql(String ruleCompHql) {
        this.ruleCompHql = ruleCompHql;
    }

    public DataResRowPolicyObj getDataResRowPolicyObj() {
        return dataResRowPolicyObj;
    }

    public void setDataResRowPolicyObj(DataResRowPolicyObj dataResRowPolicyObj) {
        this.dataResRowPolicyObj = dataResRowPolicyObj;
    }
    
}

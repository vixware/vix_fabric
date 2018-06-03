package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: RoleBizConfig
 * @Description: 业务角色的配置
 * @author wangmingchen
 * @date 2013-4-18 下午8:25:20
 * 
 */
public class RoleBizConfig extends BaseEntity {

    /** 业务类型  */
    private String bizType;
    /** 人事范围  */
    //private String rangeScope;
    
    /** 所属角色 */
    private Role role;
    
    public String getBizType() {
        return bizType;
    }
    
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
    
   /* public String getRangeScope() {
        return rangeScope;
    }
    
    public void setRangeScope(String rangeScope) {
        this.rangeScope = rangeScope;
    }*/

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
}

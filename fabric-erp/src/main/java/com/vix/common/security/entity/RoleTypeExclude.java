package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: RoleTypeExclude
 * @Description: 角色类型互斥
 * @author wangmingchen
 * @date 2013-4-18 下午8:34:50
 *
 */
public class RoleTypeExclude extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主角色类型 */
    private RoleType roleType;
    
    /** 主角色类型的排斥类型 */
    private Set<RoleType> excludeRoleTypes;

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Set<RoleType> getExcludeRoleTypes() {
        return excludeRoleTypes;
    }

    public void setExcludeRoleTypes(Set<RoleType> excludeRoleTypes) {
        this.excludeRoleTypes = excludeRoleTypes;
    }
    
}

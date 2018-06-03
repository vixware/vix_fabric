package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: RoleExclude
 * @Description: 角色互斥
 * @author wangmingchen
 * @date 2013-4-18 下午8:32:06
 *
 */
public class RoleExclude extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主角色 */
    private Role role;
    
    /** 主角色的排斥角色 */
    private Set<Role> excludeRoles;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Role> getExcludeRoles() {
        return excludeRoles;
    }

    public void setExcludeRoles(Set<Role> excludeRoles) {
        this.excludeRoles = excludeRoles;
    }
    
}

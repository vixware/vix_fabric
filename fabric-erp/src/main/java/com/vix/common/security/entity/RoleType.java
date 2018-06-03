package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: RoleType
 * @Description: 角色类型
 * @author wangmingchen
 * @date 2013-4-18 下午8:26:13
 *
 */
public class RoleType extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
   
    /** 角色类型名称 */
    private String typeName;
    
    /** 所属角色 */
    private Role role;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}

package com.vix.common.security.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: UserGroup
 * @Description: 用户组
 * @author wangmingchen
 * @date 2013-4-18 下午8:10:02
 *
 */

public class UserGroup extends BaseEntity {

    /** 用户组名称  */
    private String groupName;
    /** 用户组状态  */
    private String groupStatus;
    
    /** 职员  */
    private Set<BaseEmployee> employees;
    
    /** 帐号集合 */
    //private Set<UserAccount> userAccounts = new HashSet<UserAccount>(0);
    
    /** 角色集合 */
    private Set<Role> roles = new HashSet<Role>(0);
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupStatus() {
        return groupStatus;
    }
    
    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }


    public Set<BaseEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<BaseEmployee> employees) {
        this.employees = employees;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

   /* public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    */
    
}

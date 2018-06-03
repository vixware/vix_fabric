package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DataResRowOwner
 * @Description: 行集数据权限主体
 * @author wangmingchen
 * @date 2013-4-18 下午9:52:09
 *
 */
public class DataResRowOwner extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /** 主体类型分类 */
    private String ownerName;
    /** 主体类型 
     * R  角色
     * E  职员
     * G  用户组       待定
     * */
    private String ownerType;
    
    /** 主体角色  */
    private Set<Role> roles;
    
    /** 职员  暂时不用 */
    private BaseEmployee employee;
    
    /** 数据权限策略 */
    private DataResRowPolicy dataResRowPolicy;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public BaseEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(BaseEmployee employee) {
        this.employee = employee;
    }

	public DataResRowPolicy getDataResRowPolicy() {
		return dataResRowPolicy;
	}

	public void setDataResRowPolicy(DataResRowPolicy dataResRowPolicy) {
		this.dataResRowPolicy = dataResRowPolicy;
	}

}

package com.vix.common.security.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DataResourceColConfig
 * @Description: 列级权限系统配置
 * @author wangmingchen
 * @date 2013-4-18 下午9:00:25
 *
 */
public class DataResColConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;
   
    /** 人事范围 */
    //private String rangeScope;
    
    //分类名称
    private String configName;
    /** 是否开启列级权限  0 未开  1 开启 */
    private Integer flag;
    
    ///需要使用公司编码
    /** 所属角色 */
    private Set<Role> roles = new HashSet<Role>(0);
    
    /** 列级权限策略 */
    private Set<DataResColPolicy> dataResColPolicys = new HashSet<DataResColPolicy>(0);
    
    public Integer getFlag() {
        return flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<DataResColPolicy> getDataResColPolicys() {
        return dataResColPolicys;
    }

    public void setDataResColPolicys(Set<DataResColPolicy> dataResColPolicys) {
        this.dataResColPolicys = dataResColPolicys;
    }

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}

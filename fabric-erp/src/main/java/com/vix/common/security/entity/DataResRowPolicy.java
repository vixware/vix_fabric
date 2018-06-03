package com.vix.common.security.entity;

import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: DataResRowPolicy
 * @Description: 行级权限策略
 * @author wangmingchen
 * @date 2013-4-18 下午10:04:09
 *
 */
public class DataResRowPolicy extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /** 策略名称 */
    private String policyName;
    /** 策略状态
     * Y  正常
     * N  禁用
     *  */
    private String policyStatus;
    
    /** 数据权限主体 */
    private Set<DataResRowOwner> dataResRowOwners;
    
    /** 业务对象数据权限标识 */
    private Set<DataResRowPolicyObj> dataResRowPolicyObjs;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

	public Set<DataResRowOwner> getDataResRowOwners() {
		return dataResRowOwners;
	}

	public void setDataResRowOwners(Set<DataResRowOwner> dataResRowOwners) {
		this.dataResRowOwners = dataResRowOwners;
	}

	public Set<DataResRowPolicyObj> getDataResRowPolicyObjs() {
        return dataResRowPolicyObjs;
    }

    public void setDataResRowPolicyObjs(Set<DataResRowPolicyObj> dataResRowPolicyObjs) {
        this.dataResRowPolicyObjs = dataResRowPolicyObjs;
    }
    
}

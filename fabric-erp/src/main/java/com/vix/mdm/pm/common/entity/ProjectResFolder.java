/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 资源文件夹
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectResFolder extends BaseEntity {
	/** 是否共享 */
	private String isShare;

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

}

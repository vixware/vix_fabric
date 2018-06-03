/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目文件夹共享项目配置
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class ProjectFolderShareConfig extends BaseEntity {
	/** 项目编码 */
	private String projectCode;
	/** 文件夹编码 */
	private String folderCode;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getFolderCode() {
		return folderCode;
	}

	public void setFolderCode(String folderCode) {
		this.folderCode = folderCode;
	}
}

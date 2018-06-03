/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * WBS
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class WBS extends BaseEntity {
	/** @pdOid 项目编号 */
	private String projectCode;
	/** @pdOidwbs模板 */
	private String wbsTemplate;
	/** @pdOid 版本 */
	private String version;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getWbsTemplate() {
		return wbsTemplate;
	}

	public void setWbsTemplate(String wbsTemplate) {
		this.wbsTemplate = wbsTemplate;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

}

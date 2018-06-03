/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目里程碑
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class MileStone extends BaseEntity {
	/** 里程碑名称 */
	private String msName;
	/** 里程碑完成时间 */
	private Date msTime;

	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	public Date getMsTime() {
		return msTime;
	}

	public void setMsTime(Date msTime) {
		this.msTime = msTime;
	}

}

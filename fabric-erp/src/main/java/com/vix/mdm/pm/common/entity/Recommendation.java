/**
 * 
 */
package com.vix.mdm.pm.common.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 项目建议书
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-24
 */
public class Recommendation extends BaseEntity {
	/** 建议书名称 */
	private String rcmTitle;
	/** 建议书内容 */
	private String rcmContent;

	public String getRcmTitle() {
		return rcmTitle;
	}

	public void setRcmTitle(String rcmTitle) {
		this.rcmTitle = rcmTitle;
	}

	public String getRcmContent() {
		return rcmContent;
	}

	public void setRcmContent(String rcmContent) {
		this.rcmContent = rcmContent;
	}

}

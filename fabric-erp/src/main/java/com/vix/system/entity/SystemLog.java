package com.vix.system.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 应用系统运行的错误日志
 * 
 * @author arron
 */
public class SystemLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String title;
	/** 内容 */
	private String content;

	public SystemLog() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
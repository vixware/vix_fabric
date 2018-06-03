package com.vix.common.log.entity;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 错误日志
 * @author arron
 */
public class ErrorLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String title;
	/** 错误日志文件名称 */
	private String fileName;
	/** 错误日志内容 */
	private String content;

	public ErrorLog() {}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
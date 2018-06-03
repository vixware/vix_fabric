package com.vix.system.entity;

import java.util.List;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 应用系统运行的bug报告
 * @author arron
 */
public class BugReport extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 标题 */
	private String title;
	/** 优先级 */
	private String priority;
	/** 状态 */
	private String status;
	/** 内容 */
	private String content;
	/** bug附件，在HIBERNATE层面不关联，不会自动抓取 */
	private List<Attachment> bugAttach;

	public BugReport() {}
	
	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Attachment> getBugAttach() {
		return bugAttach;
	}

	public void setBugAttach(List<Attachment> bugAttach) {
		this.bugAttach = bugAttach;
	}
}
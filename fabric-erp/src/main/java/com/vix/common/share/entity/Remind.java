package com.vix.common.share.entity;

import java.util.Date;

/** 提醒 */
public class Remind extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 业务对象类型 */
	private String boType;
	/** 业务对象编码 */
	private String boCode;
	/** 提醒内容 */
	private String content;
	/** 是否关闭 */
	private String isClose;
	/** 来源 */
	private String source;
	/** 开始提醒时间 */
	private Date startTime;
	/** 结束提醒时间 */
	private Date endTime;

	private String searchText1;
	private String searchText2;

	public Remind() {
	}

	public String getBoType() {
		return boType;
	}

	public void setBoType(String boType) {
		this.boType = boType;
	}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String getSearchText1() {
		return searchText1;
	}

	@Override
	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}

	@Override
	public String getSearchText2() {
		return searchText2;
	}

	@Override
	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}

}

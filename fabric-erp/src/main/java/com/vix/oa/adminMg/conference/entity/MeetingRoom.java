package com.vix.oa.adminMg.conference.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: MeetingRoom
 * @Description: 会议室申请
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-9-29 下午4:54:18
 */
public class MeetingRoom extends BaseEntity {
	private static final long serialVersionUID = 6171661095914313622L;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 会议室状态*/
	public Integer bookingSituation;
	/** 会议名称 */
	private String meetingName;
	/** 会议内容 */
	private String content;
	/** 开始的日期 天 */
	private Date startDay;
	/** 开始小时 */
	private Integer startHour;
	/** 开始分钟 */
	private Integer startMinute;
	/** 结束的日期 天 */
	private Date endDay;
	/** 截至小时 */
	private Integer endHour;
	/** 截至分钟 */
	private Integer endMinute;
	/** 开始提醒时间 */
	private Date remindStartTime;
	/** 截至提醒时间 */
	private Date remindEndTime;
	/* 提醒时间 */
	private Date remindTime;
	/* 是否提醒 */
	private String isRemind;
	/* 是否重复 */
	private String isRepeat;
	/**整天*/
	private Boolean allDay;
	/**类型:任务1,安排2,项目3,提醒4,工单任务5,工作日历6*/
	private String taskType;
	/** 发布人id */
	private String uploadPersonId;	
	/** id发布人 */
	private String uploadPerson;
	private String uploadPersonName;

	/** 日程安排状态 0 未开始 1 进行中 2 已完成 3 推迟 4 取消  */
	
	
	public MeetingRoom() {
		super();
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Integer getStartHour() {
		return startHour;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public Date getEndDay() {
		return endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public Integer getEndHour() {
		return endHour;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public Date getRemindStartTime() {
		return remindStartTime;
	}

	public void setRemindStartTime(Date remindStartTime) {
		this.remindStartTime = remindStartTime;
	}

	public Date getRemindEndTime() {
		return remindEndTime;
	}

	public void setRemindEndTime(Date remindEndTime) {
		this.remindEndTime = remindEndTime;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public String getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(String isRemind) {
		this.isRemind = isRemind;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getUploadPersonId() {
		return uploadPersonId;
	}

	public void setUploadPersonId(String uploadPersonId) {
		this.uploadPersonId = uploadPersonId;
	}

	public String getUploadPerson() {
		return uploadPerson;
	}

	public void setUploadPerson(String uploadPerson) {
		this.uploadPerson = uploadPerson;
	}

	public String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public Integer getBookingSituation() {
		return bookingSituation;
	}

	public void setBookingSituation(Integer bookingSituation) {
		this.bookingSituation = bookingSituation;
	}

}

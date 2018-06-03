package com.vix.common.scheduling.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.trainning.entity.DemandNotice;

/**
 * @ClassName: Calendars
 * @Description: 日程安排 日程对象
 * @author wangmingchen
 * @date 2012-7-21 上午8:57:36
 * 
 */
public class Calendars extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String scheduleName;
	/** 日程内容 */
	private String calendarsContent;
	/**
	 * 颜色
	 */
	private String priority;
	/**
	 * 图标
	 */
	private String icon;
	/**  */
	private String view;
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
	// 设置不重复提醒的日程安排为已提醒 1,未提醒 2,已提醒
	private String isHasRemind;
	/**
	 * 整天
	 */
	private Boolean allDay;
	/**
	 * 类型:任务1,安排2,项目3,提醒4,工单任务5,工作日历6,审批7,8,征集，9
	 */
	private String taskType;
	/**
	 * 是否查看 N :新数据,O:已查看数据
	 */
	public String isNew;

	private Employee employee;
	/** 征集通知*/
	private DemandNotice demandNotice;
	/** 任务 */
	private String taskId;

	/**
	 * 日程安排状态 0 未开始 1 进行中 2 已完成 3 推迟 4 取消
	 * 
	 */
	public Calendars() {
		super();
	}

	public String getCalendarsContent() {
		return calendarsContent;
	}

	public void setCalendarsContent(String calendarsContent) {
		this.calendarsContent = calendarsContent;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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

	/**
	 * @return the isNew
	 */
	public String getIsNew() {
		return isNew;
	}

	/**
	 * @param isNew
	 *            the isNew to set
	 */
	public void setIsNew(String isNew) {
		this.isNew = isNew;
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

	/**
	 * @return the isHasRemind
	 */
	public String getIsHasRemind() {
		return isHasRemind;
	}

	/**
	 * @param isHasRemind
	 *            the isHasRemind to set
	 */
	public void setIsHasRemind(String isHasRemind) {
		this.isHasRemind = isHasRemind;
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

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public DemandNotice getDemandNotice() {
		return demandNotice;
	}

	public void setDemandNotice(DemandNotice demandNotice) {
		this.demandNotice = demandNotice;
	}
	
	
}

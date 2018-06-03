package com.vix.common.scheduling.service;

import java.util.Date;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface ICalendarsService extends IBaseHibernateService {
	/**
	 * 
	 * @param id
	 *            主键ID
	 * @param name
	 *            名称
	 * @param content
	 *            内容
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param remindTime
	 *            提醒时间
	 * @param isRemind
	 *            是否提醒
	 * @param isRepeat
	 *            是否重复
	 * @param allDay
	 *            是否全天
	 * @param taskType
	 *            类型:任务1,安排2,项目3,提醒4,工单任务5,工作日历6
	 * @return
	 * @throws Exception
	 */
	public Calendars saveOrUpdate(String id, String name, String content, Date startTime, Date endTime, Date remindTime, String isRemind, String isRepeat, Boolean allDay, String taskType) throws Exception;
}

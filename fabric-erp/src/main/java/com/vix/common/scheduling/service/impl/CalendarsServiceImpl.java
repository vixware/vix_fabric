package com.vix.common.scheduling.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.scheduling.service.ICalendarsService;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;

@Service("calendarsService")
@Transactional
public class CalendarsServiceImpl extends BaseHibernateServiceImpl implements ICalendarsService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;

	@Override
	public Calendars saveOrUpdate(String id, String name, String content, Date startTime, Date endTime, Date remindTime, String isRemind, String isRepeat, Boolean allDay, String taskType) throws Exception {
		Calendars calendars = null;
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (id != null && id.longValue() > 0) {
			calendars = baseHibernateDao.findEntityById(Calendars.class, id);
		} else {
			calendars = new Calendars();
		}
		if (name != null) {
			calendars.setName(name);
		}
		if (content != null) {
			calendars.setCalendarsContent(content);
		}
		if (startTime != null) {
			calendars.setStartTime(startTime);
		}
		if (endTime != null) {
			calendars.setEndTime(endTime);
		}
		if (remindTime != null) {
			calendars.setRemindTime(remindTime);
		}
		if (isRemind != null) {
			calendars.setIsRemind(isRemind);
		}
		if (isRepeat != null) {
			calendars.setIsRepeat(isRepeat);
		}
		if (allDay != null) {
			calendars.setAllDay(allDay);
		}
		if (taskType != null) {
			calendars.setTaskType(taskType);
		}
		calendars = baseHibernateDao.merge(calendars);
		return calendars;
	}
}

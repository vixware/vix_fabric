package com.vix.oa.adminMg.conference.service.impl;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.oa.adminMg.conference.entity.MeetingRoom;
import com.vix.oa.adminMg.conference.service.IMeetingRoomService;

@Service("meetingRoomService")
@Transactional
public class MeetingRoomServiceImpl extends BaseHibernateServiceImpl implements IMeetingRoomService {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseHibernateDao baseHibernateDao;

	@Override
	public MeetingRoom saveOrUpdate(String id, String name, String content, Date startTime, Date endTime, Date remindTime, String isRemind, String isRepeat, Boolean allDay, String taskType) throws Exception {
		MeetingRoom meetingRoom = null;
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){
			meetingRoom = baseHibernateDao.findEntityById(MeetingRoom.class, id);
		} else {
			meetingRoom = new MeetingRoom();
		}
		if (name != null) {
			meetingRoom.setName(name);
		}
		if (content != null) {
			meetingRoom.setContent(content);
		}
		if (startTime != null) {
			meetingRoom.setStartTime(startTime);
		}
		if (endTime != null) {
			meetingRoom.setEndTime(endTime);
		}
		if (remindTime != null) {
			meetingRoom.setRemindTime(remindTime);
		}
		if (isRemind != null) {
			meetingRoom.setIsRemind(isRemind);
		}
		if (isRepeat != null) {
			meetingRoom.setIsRepeat(isRepeat);
		}
		if (allDay != null) {
			meetingRoom.setAllDay(allDay);
		}
		if (taskType != null) {
			meetingRoom.setTaskType(taskType);
		}
		meetingRoom = baseHibernateDao.merge(meetingRoom);
		return meetingRoom;
	}
}

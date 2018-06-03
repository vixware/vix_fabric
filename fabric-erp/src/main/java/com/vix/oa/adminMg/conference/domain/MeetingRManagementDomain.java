package com.vix.oa.adminMg.conference.domain;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.entity.MeetingRoom;

/**
 * 
 * @ClassName: MeetingRManagementDomain
 * @Description: 会议管理  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-19 上午11:16:49
 */
@Component("meetingRManagementDomain")
@Transactional
public class MeetingRManagementDomain extends BaseDomain{

	
	/** 索引对象列表 */
	public List<MeetingRoom> findMeetingRoomIndex() throws Exception {
		return baseHibernateService.findAllByConditions(MeetingRoom.class, null);
	}

	/** 获取会议设置列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				MeetingRoom.class, params);
		return p;
	}
	
	/** 获取会议室设置搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, MeetingRoom.class, params);
		return p;
	}
	
	public MeetingRoom findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(MeetingRoom.class, id);
	}
	
	public MeetingRoom merge(MeetingRoom meetingRoom) throws Exception {
		return baseHibernateService.merge(meetingRoom);
	}
	
}

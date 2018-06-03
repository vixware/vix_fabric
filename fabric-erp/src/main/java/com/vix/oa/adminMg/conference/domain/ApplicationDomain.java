package com.vix.oa.adminMg.conference.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingDevice;

/**
 * 
 * @ClassName: ApplicationDomain
 * @Description: 会议申请安排
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-20 下午1:41:47
 */
@Component("applicationDomain")
@Transactional
public class ApplicationDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ApplicationMg.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlMeetingDevice(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MeetingDevice.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<ApplicationMg> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ApplicationMg.class, null);
	}

	/** 索引对象列表 */
	public List<MeetingDevice> findMeetingDeviceIndex() throws Exception {
		return baseHibernateService.findAllByConditions(MeetingDevice.class, null);
	}

	public ApplicationMg findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ApplicationMg.class, id);
	}

	public MeetingDevice findMeetingDeviceById(String id) throws Exception {
		return baseHibernateService.findEntityById(MeetingDevice.class, id);
	}

	public void deleteByEntity(ApplicationMg applicationMg) throws Exception {
		baseHibernateService.deleteByEntity(applicationMg);
	}

	public void deleteByMeetingDevice(MeetingDevice meetingDevice) throws Exception {
		baseHibernateService.deleteByEntity(meetingDevice);
	}

	public ApplicationMg merge(ApplicationMg applicationMg) throws Exception {
		return baseHibernateService.merge(applicationMg);
	}

	public MeetingDevice merge(MeetingDevice meetingDevice) throws Exception {
		return baseHibernateService.merge(meetingDevice);
	}

}

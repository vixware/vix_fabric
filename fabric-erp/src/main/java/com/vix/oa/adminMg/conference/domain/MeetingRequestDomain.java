package com.vix.oa.adminMg.conference.domain;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 
 * @ClassName: MeetingRequestDomain
 * @Description: 会议室设置  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-19 上午11:16:49
 */
@Component("meetingRequestDomain")
@Transactional
public class MeetingRequestDomain extends BaseDomain{

	
	/** 获取会议室设置列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				MeetingRequest.class, params);
		return p;
	}
	
	/** 获取会议室设置搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, MeetingRequest.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<MeetingRequest> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(MeetingRequest.class, null);
	}
	
	public MeetingRequest findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(MeetingRequest.class, id);
	}
	
	public void deleteByEntity(MeetingRequest meetingRequest) throws Exception {
		baseHibernateService.deleteByEntity(meetingRequest);
	}
	
	public MeetingRequest merge(MeetingRequest meetingRequest) throws Exception {
		return baseHibernateService.merge(meetingRequest);
	}
	
}

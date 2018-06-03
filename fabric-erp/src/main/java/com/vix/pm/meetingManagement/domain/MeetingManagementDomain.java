package com.vix.pm.meetingManagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.pm.meetingManagement.entity.MeetingManagement;

@Component("meetingManagementDomain")
@Transactional
public class MeetingManagementDomain extends BaseDomain{

	
	/** 索引对象列表 */
	public List<MeetingManagement> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(MeetingManagement.class, null);
	}

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				MeetingManagement.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, MeetingManagement.class, params);
		return p;
	}
	
	public MeetingManagement findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(MeetingManagement.class, id);
	}
	
	public MeetingManagement merge(MeetingManagement checkListtemplate) throws Exception {
		return baseHibernateService.merge(checkListtemplate);
	}
	
	
	
}
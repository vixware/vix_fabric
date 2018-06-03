package com.vix.oa.adminMg.domain;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.bulletin.entity.AccountStatements;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
@Component("notificationClauseDomain")
@Transactional
public class NotificationClauseDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,AnnouncementNotification.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, AnnouncementNotification.class, params);
		return p;
	}
	
	/** 获取列表数据 */
	public Pager findPagerByHqlAccountStatements(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,AccountStatements.class, params);
		return p;
	}

	public AnnouncementNotification findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(AnnouncementNotification.class, id);
	}
	
	public AnnouncementNotification merge(AnnouncementNotification announcementNotification) throws Exception {
		return baseHibernateService.merge(announcementNotification);
	}
}	
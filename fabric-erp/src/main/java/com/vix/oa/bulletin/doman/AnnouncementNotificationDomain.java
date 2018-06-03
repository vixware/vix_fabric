package com.vix.oa.bulletin.doman;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.bulletin.entity.AccountStatements;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.entity.NoticeUploader;
import com.vix.oa.bulletin.service.IAnnouncementNotificationService;

/**
 * 
 * @ClassName: AnnouncementNotificationDomain
 * @Description: 行政办公——公告通知 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-3 下午1:43:43
 */
@Component("announcementNotificationDomain")
@Transactional
public class AnnouncementNotificationDomain extends BaseDomain{

	@Autowired
	private IAnnouncementNotificationService announcementNotificationService;
	
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = announcementNotificationService.findPagerByHqlConditions(pager,AnnouncementNotification.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = announcementNotificationService.findPagerByOrHqlConditions(pager, AnnouncementNotification.class, params);
		return p;
	}
	/** 获取公告通知人员列表数据 */
	public Pager findPagerByHqlAccountStatements(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = announcementNotificationService.findPagerByHqlConditions(pager,AccountStatements.class, params);
		return p;
	}
	/** 获取公告通知人员列表数据 */
	public Pager findPagerByHqlNoticeUploader(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = announcementNotificationService.findPagerByHqlConditions(pager,NoticeUploader.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<AnnouncementNotification> findSalesOrderIndex() throws Exception {
		return announcementNotificationService.findAllByConditions(AnnouncementNotification.class, null);
	}
	
	public AnnouncementNotification findEntityById(String id) throws Exception {
		return announcementNotificationService.findEntityById(AnnouncementNotification.class, id);
	}
	
	public NoticeUploader findNoticeUploader(String id) throws Exception {
		return announcementNotificationService.findEntityById(NoticeUploader.class, id);
	}
		
	public void deleteByEntity(AnnouncementNotification announcementNotification) throws Exception {
		announcementNotificationService.deleteByEntity(announcementNotification);
	}
	
	public AnnouncementNotification merge(AnnouncementNotification announcementNotification) throws Exception {
		return announcementNotificationService.merge(announcementNotification);
	}
	
	
}

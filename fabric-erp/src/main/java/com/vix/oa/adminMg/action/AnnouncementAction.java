package com.vix.oa.adminMg.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.controller.NotificationClauseController;
import com.vix.oa.bulletin.entity.AccountStatements;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.bulletin.entity.NoticeUploader;

@Controller
@Scope("prototype")
public class AnnouncementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(NotificationClauseController.class);

	@Autowired
	private NotificationClauseController notificationClauseController;

	private List<AnnouncementNotification> announcementNotificationList;
	private AnnouncementNotification announcementNotification;
	private String id;
	public Integer isRead;
	private Date updateTime;
	private String pageNo;

	/**
	 * 公告通知列表附件集合
	 */
	private Set<NoticeUploader> noticeUploaderList;

	/** 获取列表数据 */
	@Override
	public String goList() {
		try {
			announcementNotificationList = baseHibernateService.findAllByEntityClass(AnnouncementNotification.class);
			Map<String, Object> params = getParams();
			Pager pager = notificationClauseController.doSubSingleList(params, getPager());
			logger.info("获取通知公告列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("announcementNotification.isPublish," + SearchCondition.EQUAL, 1);
			// 状态
			String isPublish = getRequestParameter("isPublish");
			if (null != isPublish && !"".equals(isPublish)) {
				params.put("isPublish," + SearchCondition.EQUAL, Integer.parseInt(isPublish));
			}
			// 按最近使用
			String activeStartDate = getRequestParameter("activeStartDate");
			if (activeStartDate != null && !"".equals(activeStartDate)) {
				params.put("announcementNotification.activeStartDate," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(activeStartDate));
			}
			// 主题
			String title = getRequestParameter("title");
			if (null != title && !"".equals(title)) {
				title = URLDecoder.decode(title, "utf-8");
				params.put("announcementNotification.title," + SearchCondition.ANYLIKE, title);
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
				params.put("announcementNotification.uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
			}
			// 发布内容
			String pubNames = getRequestParameter("pubNames");
			if (null != pubNames && !"".equals(pubNames)) {
				pubNames = URLDecoder.decode(pubNames, "utf-8");
				params.put("announcementNotification.pubNames," + SearchCondition.ANYLIKE, pubNames);
			}

			// pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("announcementNotification.pubIds," + SearchCondition.ANYLIKE, "," + employeeId + ",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("announcementNotification.activeStartDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = notificationClauseController.doAccountStatements(params, getPager());
			logger.info("获取公告列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取公告通知搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String title = getRequestParameter("title");
			if (null != title && !"".equals(title)) {
				title = URLDecoder.decode(title, "utf-8");
			}
			// 简介
			String plotSummary = getRequestParameter("plotSummary");
			if (null != plotSummary && !"".equals(plotSummary)) {
				plotSummary = URLDecoder.decode(plotSummary, "utf-8");
			}
			// 发布内容
			String content = getRequestParameter("content");
			if (null != content && !"".equals(content)) {
				content = URLDecoder.decode(content, "utf-8");
			}
			// 关键字
			String keywords = getRequestParameter("keywords");
			if (null != keywords && !"".equals(keywords)) {
				keywords = URLDecoder.decode(keywords, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 发布范围
			String pubNames = getRequestParameter("pubNames");
			if (null != pubNames && !"".equals(pubNames)) {
				pubNames = URLDecoder.decode(pubNames, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
				pager = notificationClauseController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != title && !"".equals(title)) {
					params.put("title," + SearchCondition.ANYLIKE, title);
				}
				if (null != keywords && !"".equals(keywords)) {
					params.put("keywords," + SearchCondition.ANYLIKE, keywords);
				}
				if (null != content && !"".equals(content)) {
					params.put("content," + SearchCondition.ANYLIKE, content);
				}
				if (null != plotSummary && !"".equals(plotSummary)) {
					params.put("plotSummary," + SearchCondition.ANYLIKE, plotSummary);
				}
				if (null != pubNames && !"".equals(pubNames)) {
					params.put("pubNames," + SearchCondition.ANYLIKE, pubNames);
				}
				pager = notificationClauseController.goSingleList(params, getPager());
			}
			logger.info("获取公告通知搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 查看公告通知的同时保存查看人员的帐号、ID、帐号对应的名字、查看公告时间，保存查看次数，查看已读未读
	 * 
	 * @return
	 */
	public String goSeenoticenotice() {
		try {
			System.out.println(id + "----");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				announcementNotification = notificationClauseController.doListEntityById(id);
				if (announcementNotification != null) {
					Map<String, Object> params = getParams();
					params.put("announcementNotification.id," + SearchCondition.EQUAL, id);
					params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
					// 获取该公告通知对应的附件
					noticeUploaderList = announcementNotification.getNoticeUploader();
					List<AccountStatements> accountStatementsList = baseHibernateService.findAllByConditions(AccountStatements.class, params);
					for (AccountStatements accountStatements : accountStatementsList) {
						accountStatements.setIsPublish(1);
						accountStatements.setUploadPersonId(SecurityUtil.getCurrentUserId());
						accountStatements.setUploadPerson(SecurityUtil.getCurrentUserName());
						accountStatements.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
						accountStatements.setCreateTime(new Date());
						baseHibernateService.merge(accountStatements);
					}
					/**
					 * 查看公告通知的同时保存查看人员的帐号、ID、帐号对应的名字、查看公告时间，保存查看次数
					 * 
					 * @return
					 */
					/*
					 * Map<String, Object> params = getParams(); params.put("uploadPersonId," +
					 * SearchCondition.EQUAL, SecurityUtil.getCurrentUserId());
					 * params.put("announcementNotification.id," + SearchCondition.EQUAL, id); long
					 * count =
					 * this.baseHibernateService.findDataCountByConditions(AccountStatements.class,
					 * params); if(count==0){ AccountStatements accountStatements = new
					 * AccountStatements();
					 * accountStatements.setAnnouncementNotification(announcementNotification);
					 * accountStatements.setUploadPersonId(SecurityUtil.getCurrentUserId());
					 * accountStatements.setUploadPerson(SecurityUtil.getCurrentUserName());
					 * accountStatements.setUploadPersonName(SecurityUtil.getCurrentRealUser().
					 * getName());
					 * //accountStatements.setUploadDepartments(SecurityUtil.getCurrentUserName().
					 * getDepartment()); accountStatements.setCreateTime(new Date());
					 * this.saveBaseEntity(accountStatements); }
					 */
					System.out.println(announcementNotification.getReadTimes() + "=====");
					if (announcementNotification.getReadTimes() == null) {
						announcementNotification.setReadTimes(1l);
					} else {
						announcementNotification.setReadTimes(announcementNotification.getReadTimes() + 1);
					}
					notificationClauseController.doSaveSalesOrder(announcementNotification);
				}
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSeenoticenotice";
	}

	/** 处理修改操作 */
	public String popNews() {
		return "popNews";
	}

	public List<AnnouncementNotification> getAnnouncementNotificationList() {
		return announcementNotificationList;
	}

	public void setAnnouncementNotificationList(List<AnnouncementNotification> announcementNotificationList) {
		this.announcementNotificationList = announcementNotificationList;
	}

	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotificationClauseController getNotificationClauseController() {
		return notificationClauseController;
	}

	public void setNotificationClauseController(NotificationClauseController notificationClauseController) {
		this.notificationClauseController = notificationClauseController;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<NoticeUploader> getNoticeUploaderList() {
		return noticeUploaderList;
	}

	public void setNoticeUploaderList(Set<NoticeUploader> noticeUploaderList) {
		this.noticeUploaderList = noticeUploaderList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}

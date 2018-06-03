package com.vix.common.base.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.job.entity.JobTodo;
import com.vix.common.message.entity.Message;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.AlertNotice;
import com.vix.common.share.entity.News;
import com.vix.core.constant.SearchCondition;
import com.vix.core.constant.SecurityScope;
import com.vix.core.flow.activiti.service.IStandardActivitiService;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.JSONFlexUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.system.latestOperate.entity.LatestOperateEntity;

@Controller
@Scope("prototype")
public class VixIndexDataAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IBaseHibernateService baseHibernateService;
	@Autowired
	private IStandardActivitiService standardActivitiService;
	/* 待办任务 */
	private JobTodo jobTodo;
	private String id;
	private String onchangedate;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 待办任务 */
	private List<JobTodo> jobTodoList;
	private List<VixTask> taskList;
	private Integer tasksize = 0;
	private Integer allTasksize = 0;

	private List<AlertNotice> alertNoticeList;
	/** 新闻 */
	private List<Trends> trendsList;

	private Trends trends;

	private AnnouncementNotification announcementNotification;
	private String keyContent;
	private List<AnnouncementNotification> announcementNotificationList;
	/**
	 * 今天任务
	 */
	private List<VixTask> vixTaskList;
	/**
	 * 任务列表
	 */
	private List<VixTask> allVixTaskList;

	private String searchContent;

	/** 获取公告的首页面列表数据 */
	public String goIndexBulletinPage() {
		return "goIndexBulletin";
	}

	/* 跳转到待办事宜 */
	public String goToDo() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			// 根据时间过滤待办事宜
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			jobTodoList = baseHibernateService.findAllByConditions(JobTodo.class, params);
			taskList = baseHibernateService.findAllByConditions(VixTask.class, params);
			alertNoticeList = baseHibernateService.findAllByConditions(AlertNotice.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todo";
	}

	/* 工作台-日清管理 */
	public String goOECC() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskType.code," + SearchCondition.EQUAL, "3");
		try {
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			vixTaskList = baseHibernateService.findAllByConditions(VixTask.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gooecc";
	}

	/* 跳转到通知公告 */
	public String goBulletInboardNotices() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("title," + SearchCondition.ANYLIKE, searchContent);
			}
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			announcementNotificationList = baseHibernateService.findAllByConditions(AnnouncementNotification.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gobulletinboardnotices";
	}

	/* 跳转到仪表盘 */
	public String goHighcharts() {
		return "goHighcharts";
	}

	/* 跳转到待办任务 */
	public String goCalendar() {
		return "goCalendar";
	}

	/** 获取新闻的首页面列表数据 */
	public String goIndexTrends() {
		return "goIndexTrends";
	}

	/** 获取待办事宜的首页面列表数据 */
	public String goDoLists() {
		return "goDoLists";
	}

	public String goTaskCenter() {

		try {
			//获取当日任务
			Map<String, Object> params = new HashMap<String, Object>();
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			vixTaskList = baseHibernateService.findAllByConditions(VixTask.class, params);
			if (vixTaskList != null && vixTaskList.size() > 0) {
				tasksize = vixTaskList.size();
			}
			//获取所有任务
			Map<String, Object> allParams = new HashMap<String, Object>();
			if (getEmployee() != null && getEmployee().getName() != null) {
				allParams.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			allVixTaskList = baseHibernateService.findAllByConditions(VixTask.class, allParams);
			if (allVixTaskList != null && allVixTaskList.size() > 0) {
				allTasksize = allVixTaskList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskCenterList";
	}

	/** 获取待办事宜的首页面列表数据 */
	public String goIndexDoList() {
		Map<String, Object> params = new HashMap<String, Object>();
		if (getEmployee() != null && getEmployee().getName() != null) {
			params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
		}
		try {
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				//params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}

			Object objUser = getSession().getAttribute("userInfo");
			if (null != objUser && objUser instanceof UserAccount) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date startDate = null;
				Date endDate = null;
				if (onchangedate != null && !"".equals(onchangedate)) {
					Date currentDate = sdf1.parse(onchangedate);
					String cd = sdf1.format(currentDate);
					startDate = sdf2.parse(cd + " 00:00:01");
					endDate = sdf2.parse(cd + " 23:59:59");
				}
				UserAccount user = (UserAccount) objUser;
				jobTodoList = new ArrayList<JobTodo>();
				Pager pager = standardActivitiService.getAgencyTaskList(user.getAccount(), null, startDate, endDate);
				for (Object obj : pager.getResultList()) {
					if (null != obj) {
						JobTodo jobTodo = (JobTodo) obj;
						if (null != jobTodo.getSourceClass() && !"".equals(jobTodo.getSourceClass())) {
							jobTodo.setSourceClass(getText(jobTodo.getSourceClass()));
						}
					}
				}
				this.setPager(pager);
			}
			//jobTodoList = baseHibernateService.findAllByConditions(JobTodo.class, params);
			taskList = baseHibernateService.findAllByConditions(VixTask.class, params);
			alertNoticeList = baseHibernateService.findAllByConditions(AlertNotice.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexDoList";
	}

	/**
	 * 获取首页新闻列表
	 */
	public String goIndexTrendsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			trendsList = baseHibernateService.findAllByConditions(Trends.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexTrendsContent";
	}

	/** 获取新闻的首页面列表数据 */
	public String goIndexNews() {
		return "goIndexNews1";
	}

	/**
	 * 获取新闻信息2
	 */
	public String goIndexTrendsPage() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Trends.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexTrendsPage";
	}

	/**
	 * 跳转到通知公告
	 * 
	 * @return
	 */
	/*	public String goNotice() {
			return "goNotice";
		}*/

	public String goIndexBulletinList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			pager.setPageSize(5);

			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexBulletinContent";
	}

	/** 获取代办任务的首页面列表数据 */
	public String goIndexJobtodoPage() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			if (onchangedate != null && !"".equals(onchangedate)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
			} else {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			}
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			jobTodoList = baseHibernateService.findAllByConditions(JobTodo.class, params);
			taskList = baseHibernateService.findAllByConditions(VixTask.class, params);
			alertNoticeList = baseHibernateService.findAllByConditions(AlertNotice.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexJobtodo";
	}

	public String goIndexJobtodoList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, JobTodo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexJobtodoContent";
	}

	public String goApprovalPendingList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, JobTodo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goApprovalPendingList";
	}

	public String goTaskList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, VixTask.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTaskList";
	}

	//首页提醒数据
	public String goRemindList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			Employee emp = getEmployee();
			if (null != emp) {
				if (getEmployee().getName() != null) {
					params.put("creator," + SearchCondition.EQUAL, emp.getName().toString());
				}
			}
			params.put("status," + SearchCondition.NOEQUAL, "2");
			pager = baseHibernateService.findPagerByHqlConditions(pager, AlertNotice.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRemindList";
	}

	/** 获取消息的首页面列表数据 */
	public String goIndexMsgPage() {
		return "goIndexMsg";
	}

	/** 获取职员姓名 */
	@Override
	public Employee getEmployee() {
		Employee emp = null;
		try {
			String empId = SecurityUtil.getCurrentEmpId();
			if (empId != null) {
				emp = baseHibernateService.findEntityById(Employee.class, empId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return emp;
	}

	public String goIndexMsgList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Message.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexMsgContent";
	}

	/** 获取新闻的首页面列表数据 */
	public String goIndexNewsPage() {
		return "goIndexNews";
	}

	public String goIndexNewsList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, News.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexNewsContent";
	}

	/** 获取操作历史的首页面列表数据 */
	public String goIndexOperHisPage() {
		return "goIndexOperHis";
	}

	public String goIndexOperHisList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, LatestOperateEntity.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexOperHisContent";
	}

	/** 获取日程安排的首页面列表数据 */
	public String goIndexSchedulPage() {
		return "goIndexSchedul";
	}

	public String goIndexSchedulList() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			if (getEmployee() != null && getEmployee().getName() != null) {
				params.put("creator," + SearchCondition.EQUAL, getEmployee().getName());
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Calendars.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexSchedulContent";
	}

	public JobTodo getJobTodo() {
		return jobTodo;
	}

	public void setJobTodo(JobTodo jobTodo) {
		this.jobTodo = jobTodo;
	}

	public String goNews() {
		try {
			if (id != null && !"".equals(id)) {
				trends = baseHibernateService.findEntityById(Trends.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goNews";
	}

	public String goAnnouncementNotification() {
		try {
			if (id != null && !"".equals(id)) {
				announcementNotification = baseHibernateService.findEntityById(AnnouncementNotification.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAnnouncementNotification";
	}

	@SuppressWarnings("unchecked")
	public void findPdcData() {
		Object pdcObjList = getRequest().getSession().getAttribute(SecurityScope.USERINFO_INDEX_PDC);
		List<IndexPageDataConfig> pdcList = new ArrayList<IndexPageDataConfig>(0);
		if (pdcObjList != null) {
			pdcList = (List<IndexPageDataConfig>) pdcObjList;
		}
		renderJson(JSONFlexUtils.toJson(pdcList));
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the onchangedate
	 */
	public String getOnchangedate() {
		return onchangedate;
	}

	/**
	 * @param onchangedate
	 *            the onchangedate to set
	 */
	public void setOnchangedate(String onchangedate) {
		this.onchangedate = onchangedate;
	}

	/**
	 * @return the jobTodoList
	 */
	public List<JobTodo> getJobTodoList() {
		return jobTodoList;
	}

	/**
	 * @param jobTodoList
	 *            the jobTodoList to set
	 */
	public void setJobTodoList(List<JobTodo> jobTodoList) {
		this.jobTodoList = jobTodoList;
	}

	/**
	 * @return the taskList
	 */
	public List<VixTask> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList
	 *            the taskList to set
	 */
	public void setTaskList(List<VixTask> taskList) {
		this.taskList = taskList;
	}

	/**
	 * @return the alertNoticeList
	 */
	public List<AlertNotice> getAlertNoticeList() {
		return alertNoticeList;
	}

	/**
	 * @param alertNoticeList
	 *            the alertNoticeList to set
	 */
	public void setAlertNoticeList(List<AlertNotice> alertNoticeList) {
		this.alertNoticeList = alertNoticeList;
	}

	/**
	 * @return the trendsList
	 */
	public List<Trends> getTrendsList() {
		return trendsList;
	}

	/**
	 * @param trendsList
	 *            the trendsList to set
	 */
	public void setTrendsList(List<Trends> trendsList) {
		this.trendsList = trendsList;
	}

	/**
	 * @return the trends
	 */
	public Trends getTrends() {
		return trends;
	}

	/**
	 * @param trends
	 *            the trends to set
	 */
	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	/**
	 * @return the announcementNotification
	 */
	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	/**
	 * @param announcementNotification
	 *            the announcementNotification to set
	 */
	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	/**
	 * @return the announcementNotificationList
	 */
	public List<AnnouncementNotification> getAnnouncementNotificationList() {
		return announcementNotificationList;
	}

	/**
	 * @param announcementNotificationList
	 *            the announcementNotificationList to set
	 */
	public void setAnnouncementNotificationList(List<AnnouncementNotification> announcementNotificationList) {
		this.announcementNotificationList = announcementNotificationList;
	}

	/**
	 * @return the vixTaskList
	 */
	public List<VixTask> getVixTaskList() {
		return vixTaskList;
	}

	/**
	 * @param vixTaskList
	 *            the vixTaskList to set
	 */
	public void setVixTaskList(List<VixTask> vixTaskList) {
		this.vixTaskList = vixTaskList;
	}

	/**
	 * @return the searchContent
	 */
	public String getSearchContent() {
		return searchContent;
	}

	/**
	 * @param searchContent
	 *            the searchContent to set
	 */
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	/**
	 * @return the keyContent
	 */
	public String getKeyContent() {
		return keyContent;
	}

	/**
	 * @param keyContent
	 *            the keyContent to set
	 */
	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}

	/**
	 * @return the allVixTaskList
	 */
	public List<VixTask> getAllVixTaskList() {
		return allVixTaskList;
	}

	/**
	 * @param allVixTaskList
	 *            the allVixTaskList to set
	 */
	public void setAllVixTaskList(List<VixTask> allVixTaskList) {
		this.allVixTaskList = allVixTaskList;
	}

	/**
	 * @return the tasksize
	 */
	public Integer getTasksize() {
		return tasksize;
	}

	/**
	 * @param tasksize
	 *            the tasksize to set
	 */
	public void setTasksize(Integer tasksize) {
		this.tasksize = tasksize;
	}

	/**
	 * @return the allTasksize
	 */
	public Integer getAllTasksize() {
		return allTasksize;
	}

	/**
	 * @param allTasksize
	 *            the allTasksize to set
	 */
	public void setAllTasksize(Integer allTasksize) {
		this.allTasksize = allTasksize;
	}
}

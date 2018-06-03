package com.vix.oa.adminMg.schedule.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.schedule.controller.ScheduleQueriesController;

/**
 * 日程安排管理
 * @author Thinkpad
 *
 */
@Controller
@Scope("prototype")
public class SchedulesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(ScheduleQueriesController.class);
	@Autowired
	private ScheduleQueriesController scheduleQueriesController;
	private Calendars calendars;
	private String id;
	public Integer publishType;
	private String pageNo;
	private Date updateTime;
	/** 行政办公-日程安排管理*/
	private List<Calendars> calendarsList;	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		/*try {
			calendarsList = scheduleQueriesController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//状态
			String view = getRequestParameter("view");
			if (null != view && !"".equals(view)) {
				//params.put("view," + SearchCondition.EQUAL, Integer.parseInt(view));
				params.put("view," + SearchCondition.ANYLIKE, view);
			}			
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}	
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = scheduleQueriesController.doCalendarsList(params,getPager());
			logger.info("获取日程安排列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}

	/** 获取日程安排搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String scheduleName = getRequestParameter("scheduleName");
			if (null != scheduleName && !"".equals(scheduleName)) {
				scheduleName = URLDecoder.decode(scheduleName, "utf-8");
			}
			// 内容
			String calendarsContent = getRequestParameter("calendarsContent");
			if (null != calendarsContent && !"".equals(calendarsContent)) {
				calendarsContent = URLDecoder.decode(calendarsContent, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("scheduleName," + SearchCondition.ANYLIKE, scheduleName);
				pager = scheduleQueriesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != scheduleName && !"".equals(scheduleName)) {
					params.put("scheduleName," + SearchCondition.ANYLIKE, scheduleName);
				}
				if (null != calendarsContent && !"".equals(calendarsContent)) {
					params.put("calendarsContent," + SearchCondition.ANYLIKE, calendarsContent);
				}
				pager = scheduleQueriesController.goSingleList(params, getPager());
			}
			logger.info("获取日程安排搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public Calendars getCalendars() {
		return calendars;
	}

	public void setCalendars(Calendars calendars) {
		this.calendars = calendars;
	}

	public List<Calendars> getCalendarsList() {
		return calendarsList;
	}

	public void setCalendarsList(List<Calendars> calendarsList) {
		this.calendarsList = calendarsList;
	}
}

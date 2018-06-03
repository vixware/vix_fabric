package com.vix.nvix.schedule.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 日程安排
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.schedule.action.NvixScheduleAction
 *
 * @date 2018年1月17日
 */
@Controller
@Scope("prototype")
public class NvixScheduleAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private Calendars calendars;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private List<Calendars> calendarsList;
	private String taskType;

	public String goCanlendar() {
		return "goCanlendar";
	}

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
			calendarsList = vixntBaseService.findAllByConditions(Calendars.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goList";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String start = getRequestParameter("start");
			String end = getRequestParameter("end");
			Boolean allDay = Boolean.valueOf(getRequestParameter("allDay"));
			if (StringUtils.isNotEmpty(id)) {
				calendars = vixntBaseService.findEntityById(Calendars.class, id);
			} else {
				calendars = new Calendars();
				if (StringUtils.isNotEmpty(start)) {
					calendars.setStartDay(dateFormat.parse(start));
				}
				if (StringUtils.isNotEmpty(end)) {
					calendars.setEndDay(dateFormat.parse(end));
				}
				if (allDay != null) {
					calendars.setAllDay(allDay);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String showCanlendar() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				calendars = vixntBaseService.findEntityById(Calendars.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCanlendar";
	}

	/** 处理删除操作 */
	public void deleteCanlendarById() {
		try {
			Calendars calendars = vixntBaseService.findEntityById(Calendars.class, id);
			if (null != calendars) {
				vixntBaseService.deleteByEntity(calendars);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != calendars.getId() && !"".equals(calendars.getId())) {
				isSave = false;
			}
			String tasktype = getCalendarsType(calendars.getPriority());
			calendars.setTaskType(tasktype);
			calendars.setIsHasRemind("1");
			calendars.setStatus("0");
			calendars.setEmployee(getEmployee());
			calendars.setCreator(SecurityUtil.getCurrentUserName());
			initEntityBaseController.initEntityBaseAttribute(calendars);
			calendars = vixntBaseService.merge(calendars);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	/**
	 * 类型:任务1,待办2,消息3,提醒4,工单任务5,工作日历6,审批7,通知8,
	 */
	public String getCalendarsType(String type) {
		if ("bg-color-pink txt-color-white".equals(type)) {
			return "1";
		} else if ("bg-color-blue txt-color-white".equals(type)) {
			return "2";
		} else if ("bg-color-orange txt-color-white".equals(type)) {
			return "3";
		} else if ("bg-color-greenLight txt-color-white".equals(type)) {
			return "4";
		} else if ("bg-color-blueLight txt-color-white".equals(type)) {
			return "8";
		}
		return "";
	}

	public void saveOrUpdateCalendars() {
		boolean isSave = true;
		try {
			if (null != calendars.getId() && !"".equals(calendars.getId())) {
				isSave = false;
			}
			calendars.setTaskType("8");
			calendars.setIsHasRemind("1");
			calendars.setStatus("0");
			initEntityBaseController.initEntityBaseAttribute(calendars);
			vixntBaseService.merge(calendars);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	/** 处理修改操作 */
	public void updateCanlendar() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			String startTime = getRequestParameter("start");
			String endTime = getRequestParameter("end");
			calendars = vixntBaseService.findEntityById(Calendars.class, id);
			if (calendars != null) {
				Calendars c = new Calendars();
				BeanUtils.copyProperties(calendars, c, new String[]{"id"});
				if (StringUtils.isNotEmpty(startTime)) {
					c.setStartDay(dateFormat.parse(startTime));
				}
				if (StringUtils.isNotEmpty(endTime)) {
					c.setEndDay(dateFormat.parse(endTime));
				}
				c.setTaskType("2");
				vixntBaseService.merge(c);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	/** 处理修改操作 */
	public void eventDropCanlendar() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			String startTime = getRequestParameter("start");
			String endTime = getRequestParameter("end");
			calendars = vixntBaseService.findEntityById(Calendars.class, id);
			if (calendars != null) {
				if (StringUtils.isNotEmpty(startTime)) {
					calendars.setStartDay(dateFormat.parse(startTime));
				}
				if (StringUtils.isNotEmpty(endTime)) {
					calendars.setEndDay(dateFormat.parse(endTime));
				}
				vixntBaseService.merge(calendars);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	/**
	 * 移动后日程状态改变
	 * 
	 * @return
	 */
	public void updateCanlendarStatus() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				isSave = false;
			}
			calendars = vixntBaseService.findEntityById(Calendars.class, id);
			if (calendars != null) {
				calendars.setStatus("1");
				vixntBaseService.merge(calendars);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	public void calendarEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(taskType)) {
				params.put("taskType," + SearchCondition.EQUAL, taskType);
			}
			params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
			calendarsList = vixntBaseService.findAllByConditions(Calendars.class, params);
			if (calendarsList != null && calendarsList.size() > 0) {
				JSONArray array = new JSONArray();
				for (Calendars calendars : calendarsList) {
					JSONObject object = new JSONObject();
					object.put("id", calendars.getId());
					object.put("title", calendars.getScheduleName());
					if (calendars.getStartDay() != null) {
						object.put("start", dateFormat.format(calendars.getStartDay()));
					}
					if (calendars.getEndDay() != null) {
						object.put("end", dateFormat.format(calendars.getEndDay()));
					}
					object.put("allDay", calendars.getAllDay());
					if (StringUtils.isNotEmpty(calendars.getPriority())) {
						String[] priority = calendars.getPriority().split(" ");
						object.put("className", "[\"event\", \"" + priority[0] + "\"]");
					}
					object.put("icon", calendars.getIcon());
					array.add(object);
				}
				json = array.toString();
			}
			if (!"".equals(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goCanlendarList() {
		List<Calendars> calendars = new ArrayList<Calendars>();
		String json = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String tag = getRequestParameter("tag");
			if (StringUtils.isNotEmpty(tag)) {
				params.put("taskType," + SearchCondition.EQUAL, tag);
			}
			calendars = vixntBaseService.findAllByConditions(Calendars.class, params);
			if (calendars != null && calendars.size() > 0) {
				json = convertListToJson(calendars, calendars.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"".equals(json)) {
			renderJson(json);
		} else {
			renderJson("{\"total\":0,\"rows\":[]}");
		}
	}

	public void deleteCalendarsById() {
		try {
			Calendars calendars = vixntBaseService.findEntityById(Calendars.class, id);
			if (null != calendars) {
				vixntBaseService.deleteByEntity(calendars);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Calendars getCalendars() {
		return calendars;
	}

	public void setCalendars(Calendars calendars) {
		this.calendars = calendars;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the calendarsList
	 */
	public List<Calendars> getCalendarsList() {
		return calendarsList;
	}

	/**
	 * @param calendarsList
	 *            the calendarsList to set
	 */
	public void setCalendarsList(List<Calendars> calendarsList) {
		this.calendarsList = calendarsList;
	}

}

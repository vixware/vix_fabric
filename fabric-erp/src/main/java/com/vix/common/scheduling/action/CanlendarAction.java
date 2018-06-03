package com.vix.common.scheduling.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.scheduling.service.ICalendarsService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;

@Controller
@Scope("prototype")
public class CanlendarAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 注入service */
	@Autowired
	private ICalendarsService calendarsService;
	private String id;
	private Calendars calendars;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public String goCanlendar() {
		return "goCanlendar";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String start = getRequestParameter("start");
			String end = getRequestParameter("end");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && id.longValue() > 0) {
				calendars = calendarsService.findEntityById(Calendars.class, id);
			} else {
				calendars = new Calendars();
				calendars.setCreator(SecurityUtil.getCurrentUserName());
				calendars.setStartTime(dateFormat.parse(start));
				calendars.setEndTime(dateFormat.parse(end));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String showCanlendar() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && id.longValue() > 0) {
				calendars = calendarsService.findEntityById(Calendars.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCanlendar";
	}

	/** 处理删除操作 */
	public String deleteCanlendarById() {
		try {
			Calendars calendars = calendarsService.findEntityById(Calendars.class, id);
			if (null != calendars) {
				calendarsService.deleteByEntity(calendars);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != calendars.getId() && !"".equals(calendars.getId())) {
				isSave = false;
			}
			calendars.setTaskType("2");
			calendars.setIsHasRemind("1");
			initEntityBaseController.initEntityBaseAttribute(calendars);
			calendarsService.merge(calendars);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理修改操作 */
	public String updateCanlendar() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (null != id) {
				isSave = false;
			}
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			calendars = calendarsService.findEntityById(Calendars.class, id);
			calendars.setStartTime(dateFormat.parse(startTime));
			calendars.setEndTime(dateFormat.parse(endTime));
			calendarsService.merge(calendars);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public void calendarEvents() {
		List<Calendars> calendars = new ArrayList<Calendars>();
		String json = "";
		try {
			calendars = calendarsService.findAllByEntityClass(Calendars.class);
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

	public void goCanlendarList() {
		List<Calendars> calendars = new ArrayList<Calendars>();
		String json = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String tag = getRequestParameter("tag");
			if (StringUtils.isNotEmpty(tag) && !"0".equals(tag)) {
				params.put("taskType," + SearchCondition.EQUAL, tag);
			}
			calendars = calendarsService.findAllByConditions(Calendars.class, params);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendars getCalendars() {
		return calendars;
	}

	public void setCalendars(Calendars calendars) {
		this.calendars = calendars;
	}

}

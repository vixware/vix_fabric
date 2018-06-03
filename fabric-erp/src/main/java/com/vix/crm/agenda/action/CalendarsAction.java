package com.vix.crm.agenda.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.scheduling.service.ICalendarsService;

@Controller
@Scope("prototype")
public class CalendarsAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ICalendarsService calendarsService;
	private String id;
	private Calendars calendars;
	
	public String goCalendars(){
		return "goCalendars";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				calendars = calendarsService.findEntityById(Calendars.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String showCanlendar() {
		try {
			if (null != id && !"".equals(id)) {
				calendars = calendarsService.findEntityById(Calendars.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCanlendar";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != calendars.getId()) {
				isSave = false;
			}else{
				calendars.setCreateTime(new Date());
				loadCommonData(calendars);
			}
			calendarsService.merge(calendars);
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

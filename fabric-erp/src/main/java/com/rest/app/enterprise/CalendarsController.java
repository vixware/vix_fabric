package com.rest.app.enterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.rest.core.exception.RestException;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 日程安排接口
 * 
 * @ClassFullName com.rest.app.enterprise.CalendarsController
 *
 * @author bjitzhang
 *
 * @date 2016年1月15日
 *
 */
@Controller
@RequestMapping(value = "restService/app/calendars")
public class CalendarsController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取日程安排列表
	 * 
	 * @param request
	 * @param response
	 * @param calendars
	 * @throws Exception
	 */
	@RequestMapping(value = "findCalendarsList.rs", method = RequestMethod.POST)
	public void findCalendarsList(HttpServletRequest request, HttpServletResponse response, Calendars calendars) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Calendars> calendarsList = traceabilityService.findAllDataByConditions(Calendars.class, params);
		List<Calendars> cList = new ArrayList<Calendars>();
		for (Calendars c : calendarsList) {
			Calendars t = new Calendars();
			BeanUtils.copyProperties(c, t, new String[] { "" });
			cList.add(t);
		}
		renderResult(response, cList);
	}

	/**
	 * 获取日程安排分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param calendars
	 * @throws Exception
	 */
	@RequestMapping(value = "findCalendarsPager.rs", method = RequestMethod.POST)
	public void findCalendarsPager(HttpServletRequest request, HttpServletResponse response, Pager pager, Calendars calendars) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(calendars.getName())) {
			params.put("name", calendars.getName());
		}
		if (StrUtils.isNotEmpty(calendars.getCode())) {
			params.put("code", calendars.getCode());
		}
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, Calendars.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 根据ID查询日程安排
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Calendars t = null;
		if (StringUtils.isNotEmpty(id)) {
			Calendars calendars = traceabilityService.findEntityByAttributeNoTenantId(Calendars.class, "id", id);
			String message = "";
			if (calendars == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new Calendars();
				BeanUtils.copyProperties(calendars, t, new String[] { "" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 保存日程安排
	 * 
	 * @param request
	 * @param response
	 * @param calendars
	 * @throws Exception
	 */
	@RequestMapping(value = "saveCalendars.rs", method = RequestMethod.POST)
	public void saveTask(HttpServletRequest request, HttpServletResponse response, Calendars calendars) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				//String field, String errorKey, String errorMessage
				//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
				//如果是自定义的特殊格式教研，请使用 addError
				//addError("moduleCodeKey", "模块编码重复！");
				//System.out.println(getMsgMap());
				validateRequiredString("code", "codeMsg", "请输日程编码!");
				validateRequiredString("name", "nameMsg", "请输日程名称!");
			}
		}, calendars, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		calendars = traceabilityService.mergeOriginal(calendars);
		renderResult(response, calendars);
	}

	/**
	 * 删除日程安排
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "delete.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			Calendars calendars = traceabilityService.findEntityByAttributeNoTenantId(Calendars.class, "id", id);
			if (calendars == null) {
				throw new ValidateException("没有查询到待删除的日程安排id！");
			} else {
				traceabilityService.deleteByEntity(calendars);
			}
		}
		renderResult(response, null);
	}

	@RequestMapping(value = "findCalendarsListCount.rs", method = RequestMethod.POST)
	public void findCalendarsListCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Calendars> calendarsList = traceabilityService.findAllByConditions(Calendars.class, params);
		if (calendarsList != null && calendarsList.size() > 0) {
			renderResult(response, calendarsList.size());
		} else {
			renderResult(response, 0);
		}
	}

	/**
	 * 更新查看状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "update.rs", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			Calendars calendars = traceabilityService.findEntityByAttribute(Calendars.class, "id", id);
			if (calendars == null) {
			} else {
				calendars.setIsNew("O");
				traceabilityService.mergeOriginal(calendars);
			}
		}
		renderResult(response, null);
	}
}

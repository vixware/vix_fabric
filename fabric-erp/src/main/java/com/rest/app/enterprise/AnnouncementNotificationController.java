package com.rest.app.enterprise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.vix.common.security.util.DaysUtils;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 动态
 * 
 * @ClassFullName com.rest.app.enterprise.AnnouncementNotificationController
 *
 * @author bjitzhang
 *
 * @date 2016年4月14日
 *
 */
@Controller
@RequestMapping(value = "restService/app/announcementNotification")
public class AnnouncementNotificationController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取动态列表
	 * 
	 * @param request
	 * @param response
	 * @param announcementNotification
	 * @throws Exception
	 */
	@RequestMapping(value = "findAnnouncementNotificationList.rs", method = RequestMethod.POST)
	public void findAnnouncementNotificationList(HttpServletRequest request, HttpServletResponse response, AnnouncementNotification announcementNotification) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String onchangedate = request.getParameter("onchangedate");
		if (onchangedate != null && !"".equals(onchangedate)) {
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
		} else {
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
		}
		List<AnnouncementNotification> announcementNotificationList = traceabilityService.findAllDataByConditions(AnnouncementNotification.class, params);
		List<AnnouncementNotification> cList = new ArrayList<AnnouncementNotification>();
		for (AnnouncementNotification c : announcementNotificationList) {
			AnnouncementNotification t = new AnnouncementNotification();
			BeanUtils.copyProperties(c, t, new String[] { "" });
			cList.add(t);
		}
		renderResult(response, cList);
	}

	/**
	 * 获取动态分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param announcementNotification
	 * @throws Exception
	 */
	@RequestMapping(value = "findAnnouncementNotificationPager.rs", method = RequestMethod.POST)
	public void findAnnouncementNotificationPager(HttpServletRequest request, HttpServletResponse response, Pager pager, AnnouncementNotification announcementNotification) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(announcementNotification.getName())) {
			params.put("name", announcementNotification.getName());
		}
		if (StrUtils.isNotEmpty(announcementNotification.getCode())) {
			params.put("code", announcementNotification.getCode());
		}
		String onchangedate = request.getParameter("onchangedate");
		if (onchangedate != null && !"".equals(onchangedate)) {
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(onchangedate)) + "!" + DaysUtils.getEndDay(dateFormat.parse(onchangedate)));
		} else {
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
		}
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 根据ID查询动态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "query.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		AnnouncementNotification announcementNotification = null;
		if (StringUtils.isNotEmpty(id)) {
			AnnouncementNotification t = traceabilityService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
			String message = "";
			if (t == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				announcementNotification = new AnnouncementNotification();
				BeanUtils.copyProperties(t, announcementNotification, new String[] { "" });
			}
		}
		renderResult(response, announcementNotification);
	}

	/**
	 * 保存动态
	 * 
	 * @param request
	 * @param response
	 * @param announcementNotification
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAnnouncementNotification.rs", method = RequestMethod.POST)
	public void saveAnnouncementNotification(HttpServletRequest request, HttpServletResponse response, AnnouncementNotification announcementNotification) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				//String field, String errorKey, String errorMessage
				//field 对应的实体的属性， errorKey 是自定义的唯一标识，errorMessage 是错误提示
				//如果是自定义的特殊格式教研，请使用 addError
				//addError("moduleCodeKey", "模块编码重复！");
				//System.out.println(getMsgMap());
				validateRequiredString("code", "codeMsg", "请输动态编码!");
				validateRequiredString("name", "nameMsg", "请输动态名称!");
			}
		}, announcementNotification, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		announcementNotification = traceabilityService.mergeOriginal(announcementNotification);
		renderResult(response, announcementNotification);
	}

	/**
	 * 删除动态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "delete.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			AnnouncementNotification announcementNotification = traceabilityService.findEntityByAttributeNoTenantId(AnnouncementNotification.class, "id", id);
			if (announcementNotification == null) {
				throw new ValidateException("没有查询到待删除的动态！");
			} else {
				traceabilityService.deleteByEntity(announcementNotification);
			}
		}
		renderResult(response, null);
	}

	/**
	 * 获取动态列表数量
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findAnnouncementNotificationListCount.rs", method = RequestMethod.POST)
	public void findAnnouncementNotificationListCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<AnnouncementNotification> announcementNotificationList = traceabilityService.findAllByConditions(AnnouncementNotification.class, params);
		if (announcementNotificationList != null && announcementNotificationList.size() > 0) {
			renderResult(response, announcementNotificationList.size());
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
			AnnouncementNotification announcementNotification = traceabilityService.findEntityByAttribute(AnnouncementNotification.class, "id", id);
			if (announcementNotification == null) {
			} else {
				announcementNotification.setIsNew("O");
				traceabilityService.mergeOriginal(announcementNotification);
			}
		}
		renderResult(response, null);
	}
}

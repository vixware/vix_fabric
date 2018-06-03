package com.rest.app.oa.meeting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 
 * @ClassName: ApplicationMgAppController
 * @Description: App 会议
 * @author bobchen
 * @date 2015年12月15日 上午11:00:50
 *
 */

@Controller
@RequestMapping(value = "restService/app/oa/meeting/applicationMgApp")
public class ApplicationMgAppController extends BaseRestController {

	/**
	 * 根据ID获取会议室记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findMeetingRequest.rs", method = RequestMethod.POST)
	public void queryMeetingRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MeetingRequest t = null;
		if (StringUtils.isNotEmpty(id)) {
			MeetingRequest meetingRequest = userAccountService.findEntityByAttributeNoTenantId(MeetingRequest.class, "id", id);
			String message = "";
			if (meetingRequest == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new MeetingRequest();
				BeanUtils.copyProperties(meetingRequest, t, new String[] { "applicationMg", "equipmentDetails" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 获取会议列表
	 * 
	 * @param request
	 * @param response
	 * @param meetingRequest
	 * @throws Exception
	 */
	@RequestMapping(value = "findMeetingRequestList.rs", method = RequestMethod.POST)
	// @ResponseBody
	public void findMeetingRequestList(HttpServletRequest request, HttpServletResponse response, MeetingRequest meetingRequest) throws Exception {
		List<MeetingRequest> alist = userAccountService.findAllByEntityClass(MeetingRequest.class);
		List<MeetingRequest> resList = new ArrayList<MeetingRequest>();
		if (null != alist && alist.size() > 0) {
			for (MeetingRequest a : alist) {
				MeetingRequest amg = new MeetingRequest();
				BeanUtils.copyProperties(a, amg, new String[] { "applicationMg", "equipmentDetails" });
				resList.add(amg);
			}
		}
		renderResult(response, resList);
	}

	/**
	 * 获取会议记录分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param meetingRequest
	 * @throws Exception
	 */
	@RequestMapping(value = "findMeetingRequestPager.rs", method = RequestMethod.POST)
	public void findApplicationMgPager(HttpServletRequest request, HttpServletResponse response, Pager pager, MeetingRequest meetingRequest) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(meetingRequest.getMeetingName())) {
			params.put("meetingName", meetingRequest.getMeetingName());
		}
		Pager pagerRes = userAccountService.findPagerByHqlConditions(pager, MeetingRequest.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 保存会议
	 * 
	 * @param request
	 * @param response
	 * @param meetingRequest
	 * @throws Exception
	 */
	@RequestMapping(value = "saveMeetingRequest.rs", method = RequestMethod.POST)
	public void saveMeetingRequest(HttpServletRequest request, HttpServletResponse response, MeetingRequest meetingRequest) throws Exception {
		// 方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("meetingTheme", "meetingThemeMsg", "请输会议室!");
				validateRequiredString("meetingStartTime", "meetingStartTimeMsg", "请输开始时间!");
				validateRequiredString("meetingEndTime", "meetingEndTimeMsg", "请输结束时间!");
			}
		}, meetingRequest, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		meetingRequest = userAccountService.mergeOriginal(meetingRequest);
		renderResult(response, meetingRequest);
	}

	/**
	 * 删除会议
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteMeetingRequest.rs", method = RequestMethod.DELETE)
	public void deleteMeetingRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			MeetingRequest meetingRequest = userAccountService.findEntityByAttributeNoTenantId(MeetingRequest.class, "id", id);
			if (meetingRequest == null) {
				throw new ValidateException("没有查询到待删除的会议id！");
			} else {
				userAccountService.deleteByEntity(meetingRequest);
			}
		}
		renderResult(response, null);
	}

	/**************************************************************
	 * 会议申请
	 *************************************************************************/

	/**
	 * 根据ID获取会议记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findApplicationMg.rs", method = RequestMethod.POST)
	public void queryApplicationMg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		ApplicationMg t = null;
		if (StringUtils.isNotEmpty(id)) {
			ApplicationMg applicationMg = userAccountService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
			String message = "";
			if (applicationMg == null) {
				message = "对象不存在(id:" + id + ")";
				throw new RestException(HttpStatus.NOT_FOUND, message);
			} else {
				t = new ApplicationMg();
				BeanUtils.copyProperties(applicationMg, t, new String[] { "meetingDevice", "equipmentDetails" });
			}
		}
		renderResult(response, t);
	}

	/**
	 * 获取会议列表
	 * 
	 * @param request
	 * @param response
	 * @param applicationMg
	 * @throws Exception
	 */
	@RequestMapping(value = "findApplicationMgList.rs", method = RequestMethod.POST)
	// @ResponseBody
	public void findApplicationMgList(HttpServletRequest request, HttpServletResponse response, ApplicationMg applicationMg) throws Exception {
		List<ApplicationMg> alist = userAccountService.findAllByEntityClass(ApplicationMg.class);
		List<ApplicationMg> resList = new ArrayList<ApplicationMg>();
		if (null != alist && alist.size() > 0) {
			for (ApplicationMg a : alist) {
				ApplicationMg amg = new ApplicationMg();
				BeanUtils.copyProperties(a, amg, new String[] { "meetingDevice", "equipmentDetails" });
				resList.add(amg);
			}
		}
		renderResult(response, resList);
	}

	/**
	 * 获取会议记录分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param applicationMg
	 * @throws Exception
	 */
	@RequestMapping(value = "findApplicationMgPager.rs", method = RequestMethod.POST)
	public void findApplicationMgPager(HttpServletRequest request, HttpServletResponse response, Pager pager, ApplicationMg applicationMg) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(applicationMg.getMeetingTheme())) {
			params.put("meetingTheme", applicationMg.getMeetingTheme());
		}
		Pager pagerRes = userAccountService.findPagerByHqlConditions(pager, ApplicationMg.class, params);
		renderResultPager(response, pagerRes);
	}

	/**
	 * 保存会议
	 * 
	 * @param request
	 * @param response
	 * @param applicationMg
	 * @throws Exception
	 */
	@RequestMapping(value = "saveApplicationMg.rs", method = RequestMethod.POST)
	public void saveApplicationMg(HttpServletRequest request, HttpServletResponse response, ApplicationMg applicationMg) throws Exception {
		// 方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("meetingTheme", "meetingThemeMsg", "请输会议室!");
				validateRequiredString("meetingStartTime", "meetingStartTimeMsg", "请输开始时间!");
				validateRequiredString("meetingEndTime", "meetingEndTimeMsg", "请输结束时间!");
			}
		}, applicationMg, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		applicationMg = userAccountService.mergeOriginal(applicationMg);
		renderResult(response, applicationMg);
	}

	/**
	 * 删除会议
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteApplicationMg.rs", method = RequestMethod.DELETE)
	public void deleteApplicationMg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			ApplicationMg applicationMg = userAccountService.findEntityByAttributeNoTenantId(ApplicationMg.class, "id", id);
			if (applicationMg == null) {
				throw new ValidateException("没有查询到待删除的会议id！");
			} else {
				userAccountService.deleteByEntity(applicationMg);
			}
		}
		renderResult(response, null);
	}
}

package com.rest.app.oa.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.core.utils.StrUtils;
import com.vix.core.validation.ValidateException;
import com.vix.core.validation.ValidatonUtil;
import com.vix.core.validation.Validator;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.entity.Attendance;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;
/**
 * 
* @ClassName: AttendanceAppController
* @Description: App 考勤-考勤记录
* @author bobchen
* @date 2015年12月15日 上午10:50:31
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/attendance/attendanceApp")
public class AttendanceAppController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(AttendanceAppController.class);
	
	@Resource(name="personalAttendanceService")
	private IPersonalAttendanceService personalAttendanceService;
	
	/**
	 * 获取考勤列表
	 * @param request
	 * @param response
	 * @param attendance
	 * @throws Exception
	 */
	@RequestMapping(value = "findAttendanceList.rs", method = RequestMethod.POST)
	public void findAttendanceList(HttpServletRequest request, HttpServletResponse response, Attendance attendance) throws Exception {
		List<Attendance> attendanceList = personalAttendanceService.findAllByEntityClass(Attendance.class);
		renderResult(response, attendanceList);
	}
	
	/**
	 * 获取考勤记录分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param attendance
	 * @throws Exception
	 */
	@RequestMapping(value = "findAttendancePager.rs", method = RequestMethod.POST)
	public void findAttendancePager(HttpServletRequest request, HttpServletResponse response, Pager pager, Attendance attendance) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(attendance.getUploadPersonName())) {
			params.put("uploadPersonName", attendance.getUploadPersonName());
		}
		Pager pagerRes = personalAttendanceService.findPagerByHqlConditions(pager, Attendance.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取考勤记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findattendance.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		Attendance attendance = null;
		if(StrUtils.isNotEmpty(id)){
			 attendance = personalAttendanceService.findEntityByAttributeNoTenantId(Attendance.class, "id",id);
			if (attendance == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,attendance);
	}
	
	/**
	 * 保存考勤
	 * @param request
	 * @param response
	 * @param attendance
	 * @throws Exception
	 */
	@RequestMapping(value = "saveAttendance.rs", method = RequestMethod.POST)
	public void saveAttendance(HttpServletRequest request, HttpServletResponse response, Attendance attendance) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("uploadPersonName", "uploadPersonNameMsg", "未获取到登陆人姓名!");
				validateRequiredString("reason", "reasonMsg", "选择考勤状态!");
			}
		}, attendance, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		attendance = personalAttendanceService.mergeOriginal(attendance);
		renderResult(response, attendance);
	}

}

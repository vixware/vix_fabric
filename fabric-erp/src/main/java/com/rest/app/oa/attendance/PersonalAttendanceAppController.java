package com.rest.app.oa.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.vix.oa.personaloffice.entity.PersonalAttendance;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * 
* @ClassName: PersonalAttendanceAppController
* @Description: App 考勤-外出记录
* @author bobchen
* @date 2015年12月15日 上午10:02:22
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/attendance/personalAttendanceApp")
public class PersonalAttendanceAppController extends BaseRestController{
	
	private static Logger logger = LoggerFactory.getLogger(PersonalAttendanceAppController.class);

	@Resource(name="personalAttendanceService")
	private IPersonalAttendanceService personalAttendanceService;
	
	/**
	 * 获取外出列表
	 * @param request
	 * @param response
	 * @param personalAttendance
	 * @throws Exception
	 */
	@RequestMapping(value = "findPersonalAttendanceList.rs", method = RequestMethod.POST)
	public void findTLeaveRecordsList(HttpServletRequest request, HttpServletResponse response, PersonalAttendance personalAttendance) throws Exception {
		List<PersonalAttendance> personalAttendanceList = personalAttendanceService.findAllByEntityClass(PersonalAttendance.class);
		renderResult(response, personalAttendanceList);
	}
	
	/**
	 * 获取外出记录分页列表
	 * @param request
	 * @param response
	 * @param pager
	 * @param personalAttendance
	 * @throws Exception
	 */
	@RequestMapping(value = "findPersonalAttendancePager.rs", method = RequestMethod.POST)
	public void findPersonalAttendancePager(HttpServletRequest request, HttpServletResponse response, Pager pager, PersonalAttendance personalAttendance) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(personalAttendance.getApprover())) {
			params.put("approver", personalAttendance.getApprover());
		}
		Pager pagerRes = personalAttendanceService.findPagerByHqlConditions(pager, PersonalAttendance.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取外出记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findPersonalAttendance.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		PersonalAttendance personalAttendance = null;
		if(StrUtils.isNotEmpty(id)){
			personalAttendance = personalAttendanceService.findEntityByAttributeNoTenantId(PersonalAttendance.class, "id",id);
			if (personalAttendance == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,personalAttendance);
	}
	
	/**
	 * 保存外出记录
	 * @param request
	 * @param response
	 * @param personalAttendance
	 * @throws Exception
	 */
	@RequestMapping(value = "savePersonalAttendance.rs", method = RequestMethod.POST)
	public void savePersonalAttendance(HttpServletRequest request, HttpServletResponse response, PersonalAttendance personalAttendance) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("approver", "approverMsg", "请选择审批人!");
				validateRequiredString("outstartDate", "outstartDateMsg", "请选开始时间!");
				validateRequiredString("outendDate", "outendDateMsg", "请选结束时间!");
			}
		}, personalAttendance, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		personalAttendance = personalAttendanceService.mergeOriginal(personalAttendance);
		renderResult(response, personalAttendance);
	}
	
	
	/**
	 * 删除外出记录
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deletePersonalAttendance.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			PersonalAttendance personalAttendance = personalAttendanceService.findEntityByAttributeNoTenantId(PersonalAttendance.class, "id", id);
			if (personalAttendance == null) {
				throw new ValidateException("没有查询到待删除的外出记录id！");
			} else {
				personalAttendanceService.deleteByEntity(personalAttendance);
			}
		}
		renderResult(response, null);
	}
}

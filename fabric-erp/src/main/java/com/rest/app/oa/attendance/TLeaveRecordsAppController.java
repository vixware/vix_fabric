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
import com.vix.oa.personaloffice.entity.TLeaveRecords;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * 
* @ClassName: TLeaveRecordsAppController
* @Description: App 考勤-请假记录
* @author bobchen
* @date 2015年12月15日 上午10:41:00
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/attendance/tLeaveRecordsApp")
public class TLeaveRecordsAppController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(TLeaveRecordsAppController.class);
	
	@Resource(name="personalAttendanceService")
	private IPersonalAttendanceService personalAttendanceService;
	
	/**
	 * 获取请假列表
	 * @param request
	 * @param response
	 * @param tLeaveRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "findTLeaveRecordsList.rs", method = RequestMethod.POST)
	public void findTLeaveRecordsList(HttpServletRequest request, HttpServletResponse response, TLeaveRecords tLeaveRecords) throws Exception {
		List<TLeaveRecords> tLeaveRecordsList = personalAttendanceService.findAllByEntityClass(TLeaveRecords.class);
		renderResult(response, tLeaveRecordsList);
	}
	
	/**
	 * 获取请假记录分页列表
	 * @param request
	 * @param response
	 * @param pager
	 * @param tLeaveRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "findTLeaveRecordsPager.rs", method = RequestMethod.POST)
	public void findTLeaveRecordsPager(HttpServletRequest request, HttpServletResponse response, Pager pager, TLeaveRecords tLeaveRecords) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(tLeaveRecords.getUploadPersonName())) {
			params.put("uploadPersonName", tLeaveRecords.getUploadPersonName());
		}
		Pager pagerRes = personalAttendanceService.findPagerByHqlConditions(pager, TLeaveRecords.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取请假记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findTLeaveRecords.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		TLeaveRecords tLeaveRecords = null;
		if(StrUtils.isNotEmpty(id)){
			tLeaveRecords = personalAttendanceService.findEntityByAttributeNoTenantId(TLeaveRecords.class, "id",id);
			if (tLeaveRecords == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,tLeaveRecords);
	}
	
	/**
	 * 保存请假记录
	 * @param request
	 * @param response
	 * @param attendance
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTLeaveRecords.rs", method = RequestMethod.POST)
	public void saveTLeaveRecords(HttpServletRequest request, HttpServletResponse response, TLeaveRecords tLeaveRecords) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("approver", "approverMsg", "请选择审批人!");
				validateRequiredString("vacateendDate", "vacateendDateMsg", "请选开始时间!");
				validateRequiredString("vacateStartdate", "vacateStartdateMsg", "请选结束时间!");
			}
		}, tLeaveRecords, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		tLeaveRecords = personalAttendanceService.mergeOriginal(tLeaveRecords);
		renderResult(response, tLeaveRecords);
	}
	
	/**
	 * 删除请假记录
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteTLeaveRecords.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			TLeaveRecords tLeaveRecords = personalAttendanceService.findEntityByAttributeNoTenantId(TLeaveRecords.class, "id", id);
			if (tLeaveRecords == null) {
				throw new ValidateException("没有查询到待删除的请假记录id！");
			} else {
				personalAttendanceService.deleteByEntity(tLeaveRecords);
			}
		}
		renderResult(response, null);
	}
	
}

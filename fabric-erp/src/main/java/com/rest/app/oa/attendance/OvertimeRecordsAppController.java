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
import com.vix.oa.personaloffice.entity.OvertimeRecords;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * 
* @ClassName: OvertimeRecordsAppController
* @Description: App 考勤-加班登记
* @author bobchen
* @date 2015年12月15日 上午10:47:12
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/attendance/overtimeRecordsApp")
public class OvertimeRecordsAppController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(OvertimeRecordsAppController.class);
	
	@Resource(name="personalAttendanceService")
	private IPersonalAttendanceService personalAttendanceService;
	
	/**
	 * 获取加班记录列表
	 * @param request
	 * @param response
	 * @param overtimeRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "findOvertimeRecordsList.rs", method = RequestMethod.POST)
	public void findOvertimeRecordsList(HttpServletRequest request, HttpServletResponse response, OvertimeRecords overtimeRecords) throws Exception {
		List<OvertimeRecords> overtimeRecordsList = personalAttendanceService.findAllByEntityClass(OvertimeRecords.class);
		renderResult(response, overtimeRecordsList);
	}
	
	/**
	 * 获取加班记录分页列表
	 * @param request
	 * @param response
	 * @param pager
	 * @param overtimeRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "findOvertimeRecordsPager.rs", method = RequestMethod.POST)
	public void findOvertimeRecordsPager(HttpServletRequest request, HttpServletResponse response, Pager pager, OvertimeRecords overtimeRecords) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(overtimeRecords.getUploadPersonName())) {
			params.put("uploadPersonName", overtimeRecords.getUploadPersonName());
		}
		if (StrUtils.isNotEmpty(overtimeRecords.getApprover())) {
			params.put("approver", overtimeRecords.getApprover());
		}
		Pager pagerRes = personalAttendanceService.findPagerByHqlConditions(pager, OvertimeRecords.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取加班记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findOvertimeRecords.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		OvertimeRecords overtimeRecords = null;
		if(StrUtils.isNotEmpty(id)){
			overtimeRecords = personalAttendanceService.findEntityByAttributeNoTenantId(OvertimeRecords.class, "id",id);
			if (overtimeRecords == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,overtimeRecords);
	}
	
	
	/**
	 * 保存加班记录
	 * @param request
	 * @param response
	 * @param overtimeRecords
	 * @throws Exception
	 */
	@RequestMapping(value = "saveOvertimeRecords.rs", method = RequestMethod.POST)
	public void saveOvertimeRecords(HttpServletRequest request, HttpServletResponse response, OvertimeRecords overtimeRecords) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("approver", "approverMsg", "请选择审批人!");
				validateRequiredString("overtimestartDate", "overtimestartDateMsg", "请选开始时间!");
				validateRequiredString("overtimesendDate", "overtimesendDateMsg", "请选结束时间!");
			}
		}, overtimeRecords, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		overtimeRecords = personalAttendanceService.mergeOriginal(overtimeRecords);
		renderResult(response, overtimeRecords);
	}
	
	/**
	 * 删除加班记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteOvertimeRecords.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			OvertimeRecords overtimeRecords = personalAttendanceService.findEntityByAttributeNoTenantId(OvertimeRecords.class, "id", id);
			if (overtimeRecords == null) {
				throw new ValidateException("没有查询到待删除的加班记录id！");
			} else {
				personalAttendanceService.deleteByEntity(overtimeRecords);
			}
		}
		renderResult(response, null);
	}
	
}

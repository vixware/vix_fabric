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
import com.vix.oa.personaloffice.entity.TripRecord;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

/**
 * 
* @ClassName: TripRecordAppController
* @Description: App 考勤-出差记录
* @author bobchen
* @date 2015年12月15日 上午10:43:41
*
 */

@Controller
@RequestMapping(value = "restService/app/oa/attendance/tripRecordApp")
public class TripRecordAppController extends BaseRestController{

	private static Logger logger = LoggerFactory.getLogger(TripRecordAppController.class);
	
	@Resource(name="personalAttendanceService")
	private IPersonalAttendanceService personalAttendanceService;
	
	/**
	 * 获取出差列表
	 * @param request
	 * @param response
	 * @param tripRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "findTripRecordList.rs", method = RequestMethod.POST)
	public void findTripRecordList(HttpServletRequest request, HttpServletResponse response, TripRecord tripRecord) throws Exception {
		List<TripRecord> tripRecordList = personalAttendanceService.findAllByEntityClass(TripRecord.class);
		renderResult(response, tripRecordList);
	}
	
	/**
	 * 获取出差记录分页列表
	 * @param request
	 * @param response
	 * @param pager
	 * @param tripRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "findTripRecordPager.rs", method = RequestMethod.POST)
	public void findTripRecordPager(HttpServletRequest request, HttpServletResponse response, Pager pager, TripRecord tripRecord) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (StrUtils.isNotEmpty(tripRecord.getApprover())) {
			params.put("approver", tripRecord.getApprover());
		}
		Pager pagerRes = personalAttendanceService.findPagerByHqlConditions(pager, TripRecord.class, params);
		renderResultPager(response, pagerRes);
	}
	
	/**
	 * 根据ID获取出差记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "findTripRecord.rs", method = RequestMethod.POST)
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		logger.info("单一查询:{}.",id);
		TripRecord tripRecord = null;
		if(StrUtils.isNotEmpty(id)){
			tripRecord = personalAttendanceService.findEntityByAttributeNoTenantId(TripRecord.class, "id",id);
			if (tripRecord == null) {
				throw new ValidateException("没有查询到id="+id+"的客户！");
			}
		}else{
			throw new ValidateException("没有查询到id="+id+"的客户！");
		}
		renderResult(response,tripRecord);
	}
	
	/**
	 * 保存出差记录
	 * @param request
	 * @param response
	 * @param tripRecord
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTripRecord.rs", method = RequestMethod.POST)
	public void saveTripRecord(HttpServletRequest request, HttpServletResponse response, TripRecord tripRecord) throws Exception {
		//方式2 使用简单验证器
		Map<String, String> valMsgMap = ValidatonUtil.validator(new Validator() {
			@Override
			protected void validate() {
				validateRequiredString("approver", "approverMsg", "请输出差人!");
				validateRequiredString("businessLocation", "businessLocationMsg", "请输出差地区!");
				validateRequiredString("reason", "reasonMsg", "请输出差原因!");
				validateRequiredString("isPublish", "isPublishMsg", "请输是否用车!");
				validateRequiredString("businessstartDate", "businessstartDateMsg", "请输开始时间!");
				validateRequiredString("businessendDate", "businessendDateMsg", "请输结束时间");
			}
		}, tripRecord, false);
		if (!valMsgMap.isEmpty()) {
			renderResultNotValid(response, valMsgMap);
			return;
		}
		tripRecord = personalAttendanceService.mergeOriginal(tripRecord);
		renderResult(response, tripRecord);
	}
	
	
	/**
	 * 删除出差记录
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteTripRecord.rs", method = RequestMethod.DELETE)
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotEmpty(id)) {
			TripRecord tripRecord = personalAttendanceService.findEntityByAttributeNoTenantId(TripRecord.class, "id", id);
			if (tripRecord == null) {
				throw new ValidateException("没有查询到待删除的出差记录id！");
			} else {
				personalAttendanceService.deleteByEntity(tripRecord);
			}
		}
		renderResult(response, null);
	}
}

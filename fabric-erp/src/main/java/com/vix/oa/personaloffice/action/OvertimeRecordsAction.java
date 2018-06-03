
package com.vix.oa.personaloffice.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.billtype.BillType;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.oa.personaloffice.controller.PersonalAttendanceController;
import com.vix.oa.personaloffice.entity.OvertimeRecords;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: OvertimeRecordsAction
 * @Description: 加班记录
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-7-8 下午3:15:45
 */
@Controller
@Scope("prototype")
public class OvertimeRecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalAttendanceController.class);

	@Autowired
	private PersonalAttendanceController personalAttendanceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	private OvertimeRecords overtimeRecords;

	private String id;

	public Integer publishType;

	private String pageNo;

	private Date updateTime;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否

	/** 跳转至用户修改页面 */
	public String goSaveOrOvertimeRecords() {
		try {

			isAllowAudit = isAllowAudit(BillType.OA_OVERTIME_RECORDS);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				overtimeRecords = personalAttendanceController.doListEntityById3(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrOvertimeRecords";
	}

	public String postData(MultiValueMap<String, Object> formData, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Charset", "utf-8");
		requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, formData);
		return response.getBody();
	}

	/** 处理新增修改操作 */
	public String saveOrOvertimeRecords() {
		boolean isSave = true;
		try {
			if (null != overtimeRecords.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(overtimeRecords);
			overtimeRecords = personalAttendanceController.doSaveSalesOrder3(overtimeRecords);

			String billId = overtimeRecords.getId();
			MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
			formData.add("billId", billId);
			Object objUser = getSession().getAttribute("userInfo");
			UserAccount user = null;
			if (null != objUser && objUser instanceof UserAccount) {
				user = (UserAccount) objUser;
				formData.add("userId", user.getId());
			}
			String url = "http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode";
			String response = postData(formData, url);
			JSONObject json = JSONObject.fromObject(response);
			if (json.has("status")) {
				if ("1".equals(json.getString("status"))) {
					overtimeRecords.setStatus("1");
					overtimeRecords = personalAttendanceController.doSaveSalesOrder3(overtimeRecords);
				}
			}

			this.overtimeRecords.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.overtimeRecords.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			overtimeRecords.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.overtimeRecords);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public OvertimeRecords getOvertimeRecords() {
		return overtimeRecords;
	}

	public void setOvertimeRecords(OvertimeRecords overtimeRecords) {
		this.overtimeRecords = overtimeRecords;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

}

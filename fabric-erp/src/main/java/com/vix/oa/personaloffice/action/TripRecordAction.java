
package com.vix.oa.personaloffice.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.oa.personaloffice.controller.PersonalAttendanceController;
import com.vix.oa.personaloffice.entity.TripRecord;

/**
 * 
 * @ClassName: TripRecordAction
 * @Description: 出差记录 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-8 下午3:04:46
 */
@Controller
@Scope("prototype")
public class TripRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalAttendanceController.class);
	
	@Autowired
	private PersonalAttendanceController personalAttendanceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	
	
	private TripRecord tripRecord;
	
	private String id;
	
	public Integer publishType;
	
	private String pageNo;
	
	private Date updateTime;
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrTripRecord(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				tripRecord = personalAttendanceController.doListEntityById2(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrTripRecord";
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrTripRecord() {
		boolean isSave = true;
		try {
			if (null != tripRecord.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(tripRecord);
			tripRecord = personalAttendanceController.doSaveSalesOrder2(tripRecord);
			this.tripRecord.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.tripRecord.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			tripRecord.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.tripRecord);
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


	public TripRecord getTripRecord() {
		return tripRecord;
	}


	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}

}

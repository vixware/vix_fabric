
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
import com.vix.oa.personaloffice.entity.TLeaveRecords;

/**
 * 
 * @ClassName: TleaveRecordsAction
 * @Description: 请假记录  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-8 下午2:22:32
 */
@Controller
@Scope("prototype")
public class TleaveRecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalAttendanceController.class);
	@Autowired
	private PersonalAttendanceController personalAttendanceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private TLeaveRecords tleaveRecords;
	private String id;
	public Integer publishType;
	private String pageNo;
	private Date updateTime;
	
	/** 跳转至请假记录页面 */
	public String goSaveOrTleaveRecords(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				tleaveRecords = personalAttendanceController.doListEntityById1(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrTleaveRecords";
	}
	
	/** 处理新增修改操作 */
	public String saveOrTleaveRecords() {
		boolean isSave = true;
		try {
			if (null != tleaveRecords.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(tleaveRecords);
			tleaveRecords = personalAttendanceController.doSaveSalesOrder1(tleaveRecords);
			this.tleaveRecords.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.tleaveRecords.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			tleaveRecords.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.tleaveRecords);
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


	public TLeaveRecords getTleaveRecords() {
		return tleaveRecords;
	}


	public void setTleaveRecords(TLeaveRecords tleaveRecords) {
		this.tleaveRecords = tleaveRecords;
	}



}

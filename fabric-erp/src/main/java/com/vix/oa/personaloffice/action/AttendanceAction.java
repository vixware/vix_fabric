
package com.vix.oa.personaloffice.action;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.oa.personaloffice.controller.PersonalAttendanceController;
import com.vix.oa.personaloffice.entity.Attendance;

/**
 * 
 * @ClassName: AttendanceAction
 * @Description: 个人考勤记录  
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-7-8 下午3:23:37
 */
@Controller
@Scope("prototype")
public class AttendanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalAttendanceController.class);
	@Autowired
	private PersonalAttendanceController personalAttendanceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private Attendance attendance;
	private String id;
	public Integer publishType;
	private String pageNo;
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrAttendance(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				attendance = personalAttendanceController.doListEntityById4(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrAttendance";
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrAttendance() {
		boolean isSave = true;
		try {
			if (null != attendance.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(attendance);
			attendance = personalAttendanceController.doSaveAttendance(attendance);
			this.attendance.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.attendance.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			attendance.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.attendance);
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


	public Attendance getAttendance() {
		return attendance;
	}


	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

}

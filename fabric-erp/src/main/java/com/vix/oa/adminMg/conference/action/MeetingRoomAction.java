package com.vix.oa.adminMg.conference.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;
import com.vix.oa.adminMg.conference.entity.MeetingRoom;
import com.vix.oa.adminMg.conference.service.IMeetingRoomService;

@Controller
@Scope("prototype")
public class MeetingRoomAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 注入service */
	@Autowired
	private IMeetingRoomService meetingRoomService;
	private String id;
	private MeetingRoom meetingRoom;
	private MeetingRequest meetingRequest;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 会议申请
	 * @return
	 */
	public String goMeetingRoom() {
		return "goMeetingRoom";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String start = getRequestParameter("start");
			String end = getRequestParameter("end");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				meetingRoom = meetingRoomService.findEntityById(MeetingRoom.class, id);
				this.saveBaseEntity(this.meetingRequest);
			} else {
				meetingRoom = new MeetingRoom();
				meetingRoom.setStartTime(dateFormat.parse(start));
				meetingRoom.setEndTime(dateFormat.parse(end));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String showCanlendar() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				meetingRoom = meetingRoomService.findEntityById(MeetingRoom.class, id);
				this.saveBaseEntity(this.meetingRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showCanlendar";
	}

	/** 处理删除操作 */
	public String deleteCanlendarById() {
		try {
			MeetingRoom meetingRoom = meetingRoomService.findEntityById(MeetingRoom.class, id);
			if (null != meetingRoom) {
				meetingRoomService.deleteByEntity(meetingRoom);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != meetingRoom.getId()) {
				isSave = false;
			}
			/**索引 */
			String meetingName = meetingRoom.getMeetingName();
			String py = ChnToPinYin.getPYString(meetingName);
			meetingRoom.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(meetingRoom);
			this.meetingRoom.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.meetingRoom.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			meetingRoom.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			meetingRoomService.merge(meetingRoom);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理修改操作 */
	public String updateCanlendar() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			if (null != id) {
				isSave = false;
			}
			String startTime = getRequestParameter("startTime");
			String endTime = getRequestParameter("endTime");
			meetingRoom = meetingRoomService.findEntityById(MeetingRoom.class, id);
			meetingRoom.setStartTime(dateFormat.parse(startTime));
			meetingRoom.setEndTime(dateFormat.parse(endTime));
			meetingRoomService.merge(meetingRoom);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public void calendarEvents() {
		List<MeetingRoom> meetingRoom = new ArrayList<MeetingRoom>();
		String json = "";
		try {
			meetingRoom = meetingRoomService.findAllByEntityClass(MeetingRoom.class);
			if (meetingRoom != null && meetingRoom.size() > 0) {
				json = convertListToJson(meetingRoom, meetingRoom.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"".equals(json)) {
			renderJson(json);
		} else {
			renderJson("{\"total\":0,\"rows\":[]}");
		}
	}
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	

}

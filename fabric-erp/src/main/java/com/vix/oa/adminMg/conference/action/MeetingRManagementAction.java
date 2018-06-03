package com.vix.oa.adminMg.conference.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.controller.ApplicationMeetingController;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 会议室申请管理
 * 
 * @author Thinkpad
 *
 */
@Controller
@Scope("prototype")
public class MeetingRManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(ApplicationMeetingController.class);

	@Autowired
	private ApplicationMeetingController applicationMeetingController;
	private List<ApplicationMg> applicationMgList;
	private MeetingRequest meetingRequest;
	private String id;
	private String parentId;
	private String pageNo;
	public Integer bookingSituation;
	private Date updateTime;
	private ApplicationMg applicationMg;
	/** 会议室管理 */
	private List<MeetingRequest> meetingRequestList;

	/** 加载顶端工具栏 */
	public String goTopDynamicMenuContent() {

		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			applicationMgList = applicationMeetingController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemps," + SearchCondition.NOEQUAL, 1);

			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 状态
			String bookingSituation = getRequestParameter("bookingSituation");
			if (null != bookingSituation && !"".equals(bookingSituation)) {
				params.put("bookingSituation," + SearchCondition.EQUAL, Integer.parseInt(bookingSituation));
			}
			// 按最近使用
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			// uploadPersonId包含当前登录人id
			/*
			 * Long employeeId = SecurityUtil.getCurrentUserId();
			 * params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			 */
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = applicationMeetingController.doSubSingleList(params, getPager());
			logger.info("获取申请会议室列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取申请会议室搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 申请人
			String proposer = getRequestParameter("proposer");
			if (null != proposer && !"".equals(proposer)) {
				proposer = URLDecoder.decode(proposer, "utf-8");
			}
			// 参与人
			String participants = getRequestParameter("participants");
			if (null != participants && !"".equals(participants)) {
				participants = URLDecoder.decode(participants, "utf-8");
			}
			// 会议主题
			String meetingTheme = getRequestParameter("meetingTheme");
			if (null != meetingTheme && !"".equals(meetingTheme)) {
				meetingTheme = URLDecoder.decode(meetingTheme, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("proposer," + SearchCondition.ANYLIKE, proposer);
				pager = applicationMeetingController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != proposer && !"".equals(proposer)) {
					params.put("proposer," + SearchCondition.ANYLIKE, proposer);
				}
				if (null != meetingTheme && !"".equals(meetingTheme)) {
					params.put("meetingTheme," + SearchCondition.ANYLIKE, meetingTheme);
				}
				if (null != participants && !"".equals(participants)) {
					params.put("participants," + SearchCondition.ANYLIKE, participants);
				}
				pager = applicationMeetingController.goSingleList(params, getPager());
			}
			logger.info("获取申请会议室搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public void updateBookingSituation() {
		try {
			ApplicationMg pb = applicationMeetingController.findEntityById(id);
			/** 根据会议获取会议室ID */
			MeetingRequest meetingRequest = pb.getMeetingRequest();
			if (pb.getBookingSituation() == 1) {
				pb.setBookingSituation(3);
				/** 获取会议室ID */
				meetingRequest.setMeetingRoomStatus(0);
				baseHibernateService.merge(meetingRequest);
			} else
				pb.setBookingSituation(bookingSituation);
			applicationMg = applicationMeetingController.doSaveSalesOrder(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public Integer getBookingSituation() {
		return bookingSituation;
	}

	public void setBookingSituation(Integer bookingSituation) {
		this.bookingSituation = bookingSituation;
	}

	public List<ApplicationMg> getApplicationMgList() {
		return applicationMgList;
	}

	public void setApplicationMgList(List<ApplicationMg> applicationMgList) {
		this.applicationMgList = applicationMgList;
	}

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public MeetingRequest getMeetingRequest() {
		return meetingRequest;
	}

	public void setMeetingRequest(MeetingRequest meetingRequest) {
		this.meetingRequest = meetingRequest;
	}

	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
	}

}

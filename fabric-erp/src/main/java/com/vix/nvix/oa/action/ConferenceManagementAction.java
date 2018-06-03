package com.vix.nvix.oa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.adminMg.conference.controller.ApplicationMeetingController;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;
import com.vix.oa.adminMg.conference.entity.MeetingSummary;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.entity.WxpTrendsAndEmployee;
import com.vix.wechat.base.util.WxQyUtil;

/**
 * 
 * @ClassName: ConferenceManagementAction
 * @Description: 会议管理
 * @author bobchen
 * @date 2016年1月12日 上午10:39:47
 *
 */
@Controller
@Scope("prototype")
public class ConferenceManagementAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ApplicationMeetingController applicationMeetingController;

	private String id;
	private MeetingRequest meetingRequest;
	private ApplicationMg applicationMg;
	private MeetingSummary meetingSummary;
	private List<MeetingRequest> meetingRequestList;
	private List<ApplicationMg> applicationMgList;
	private String syncTag;
	private String employeeIds;
	private String userIdStr = "";
	private List<Employee> empList;
	private String applicationMgId;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			meetingRequestList = vixntBaseService.findAllByConditions(MeetingRequest.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goAffiliatedApplicationMg() {
		return "goAffiliatedApplicationMg";
	}

	/* 查询创建会议室数据 */
	public void goMeetingRequest() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/** 根据时间进行倒序排序 */
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("createTime");
				pager.setOrderBy("desc");
			}
			// 会议室名称
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("meetingName," + SearchCondition.ANYLIKE, searchCode);
			}
			// 会议室编码
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("code," + SearchCondition.ANYLIKE, searchCodeA);
			}
			// 管理员
			String searchCodeB = getDecodeRequestParameter("searchCodeB");
			if (StringUtils.isNotEmpty(searchCodeB)) {
				params.put("creator," + SearchCondition.ANYLIKE, searchCodeB);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, MeetingRequest.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取参加人员
	public void goAffiliatedApplicationMgList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("trendsId," + SearchCondition.EQUAL, id);
			p.put("status," + SearchCondition.NOEQUAL, "Q");
			String employeeIds = "";
			List<WxpTrendsAndEmployee> wxpTrendsAndEmployeeList = baseHibernateService.findAllByConditions(WxpTrendsAndEmployee.class, p);
			if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
				for (WxpTrendsAndEmployee wxpTrendsAndEmployee : wxpTrendsAndEmployeeList) {
					employeeIds += "," + wxpTrendsAndEmployee.getEmployeeId();
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(employeeIds)) {
				params.put("id," + SearchCondition.IN, employeeIds);
				pager = baseHibernateService.findPagerByHqlConditions(pager, Employee.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goLeaveApplicationMg() {
		return "goLeaveApplicationMg";
	}

	public void goLeaveApplicationMgList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("trendsId," + SearchCondition.EQUAL, id);
			p.put("status," + SearchCondition.EQUAL, "Q");
			String employeeIds = "";
			List<WxpTrendsAndEmployee> wxpTrendsAndEmployeeList = baseHibernateService.findAllByConditions(WxpTrendsAndEmployee.class, p);
			if (wxpTrendsAndEmployeeList != null && wxpTrendsAndEmployeeList.size() > 0) {
				for (WxpTrendsAndEmployee wxpTrendsAndEmployee : wxpTrendsAndEmployeeList) {
					employeeIds += "," + wxpTrendsAndEmployee.getEmployeeId();
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(employeeIds)) {
				params.put("id," + SearchCondition.IN, employeeIds);
				pager = baseHibernateService.findPagerByHqlConditions(pager, Employee.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 查询申请会议室数据 */
	public void goApplicationMg() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("meetingStartTime");
				getPager().setOrderBy("desc");
			}
			// 会议主题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("meetingTheme," + SearchCondition.ANYLIKE, searchCode);
			}
			// 申请人
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("proposer," + SearchCondition.ANYLIKE, searchCodeA);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), ApplicationMg.class, params);
			String[] s = {"*.sex"};
			renderDataTable(pager, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 会议室管理 */
	public void goApplicationMgs() {
		syncTag = getRequestParameter("syncTag");
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ApplicationMg.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 申请会议室 */
	public String goApplyApplicationMg() {
		try {
			Map<String, Object> params = getParams();
			applicationMgList = vixntBaseService.findAllByConditions(ApplicationMg.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goApplyApplicationMg";
	}

	/**
	 * 跳转申请会议室页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			/* 现在不需要下拉选择先去掉 */
			/*
			 * meetingRequestList =
			 * baseHibernateService.findAllByEntityClassAndAttribute(
			 * MeetingRequest.class, "meetingRoomStatus", 0);
			 */
			Map<String, Object> params = getParams();
			applicationMgList = vixntBaseService.findAllByConditions(ApplicationMg.class, params);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				applicationMg = baseHibernateService.findEntityById(ApplicationMg.class, id);
			} else {
				applicationMg = new ApplicationMg();
				applicationMg.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				applicationMg.setIsTemps(1);
				applicationMg.setUploadPerson(SecurityUtil.getCurrentUserName());
				applicationMg = applicationMeetingController.doSaveSalesOrder(applicationMg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 新增会议纪要
	 * 
	 * @return
	 */
	public String goSaveOrUpdateMeetingSummary() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				meetingSummary = baseHibernateService.findEntityByAttribute(MeetingSummary.class, "applicationMg.id", id);
				if (meetingSummary != null) {
				} else {
					meetingSummary = new MeetingSummary();
					ApplicationMg applicationMg = vixntBaseService.findEntityById(ApplicationMg.class, id);
					if (applicationMg != null) {
						meetingSummary.setApplicationMg(applicationMg);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMeetingSummary";
	}

	/** 处理修改申请会议室操作 */
	public String saveOrUpdate() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(applicationMg.getId())) {
				isSave = false;
			}
			/** 根据meetingRequest的id对meetingRequest里的MeetingRoomStatus进行set */
			/*
			 * meetingRequest =
			 * baseHibernateService.findEntityById(MeetingRequest.class,
			 * applicationMg.getMeetingRequest().getId());
			 * meetingRequest.setMeetingRoomStatus(1);
			 * baseHibernateService.merge(meetingRequest);
			 */

			/** 拿到当前用户的姓名，保存 */
			this.applicationMg.setUploadPersonId(SecurityUtil.getCurrentEmpId());
			Employee employee = getEmployee();
			if (employee != null) {
				applicationMg.setUploadPersonName(employee.getName());
				applicationMg.setUploadPerson(employee.getName());
			}
			applicationMg.setIsTemps(0);
			WxpQyWeixinSite site = baseHibernateService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", applicationMg.getTenantId());

			// 处理消息推送
			Map<String, Object> p = getParams();
			p.put("empType," + SearchCondition.EQUAL, "WE");
			empList = vixntBaseService.findAllByConditions(Employee.class, p);
			if (empList != null && empList.size() > 0) {
				for (Employee e : empList) {
					userIdStr += "|" + e.getUserId();
				}
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				if (!"1".equals(applicationMg.getIsTemps())) {
					// 更新企业号token
					updateWxpWeixinSite();
					WxQyUtil.messageSendNews("7", userIdStr.substring(1), applicationMg.getMeetingTheme(), site.getQiyeAccessToken(), applicationMg.getMeetingDescription(), "http://www.vixware.cn/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + applicationMg.getId(), "https://qy.weixin.qq.com/cgi-bin/getmediadata?type=image&media_id=2ESke_34-KrJK9UQsJgKWbVnc0f7GGtxaCIEGW1xiDJoBZtlN7Ve3q1ilmqp62zYp&uin=1806062044");
					applicationMg.setIsTemps(1);
				}
			}
			applicationMg = baseHibernateService.merge(applicationMg);
			// 处理消息推送

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
			e.printStackTrace();
		}
		return UPDATE;
	}

	// 保存会议纪要
	public String saveOrUpdateMeetingSummary() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(meetingSummary.getId())) {
				isSave = false;
			}
			Employee employee = getEmployee();
			if (employee != null) {
				meetingSummary.setCreator(employee.getName());
			}
			initEntityBaseController.initEntityBaseAttribute(meetingSummary);
			meetingSummary = baseHibernateService.merge(meetingSummary);
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
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 查看会议室的使用情况并申请会议
	 * 
	 * @return
	 */
	public String goApplyRoom() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, id);
				// 业务修改暂时不需要
				/*
				 * if (meetingRequest.getApplicationMg() != null &&
				 * meetingRequest.getApplicationMg().size() > 0) {
				 * applicationMgList = new ArrayList<ApplicationMg>();
				 * applicationMgList.addAll(meetingRequest.getApplicationMg());
				 * }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goApplyRoom";
	}

	/* 查询申请的会议室数据 */
	public void goApplyRooms() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("meetingStartTime");
				getPager().setOrderBy("desc");
			}
			// 会议主题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("meetingTheme," + SearchCondition.ANYLIKE, searchCode);
			}
			// 根据时间过滤会议申请
			params.put("meetingStartTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), ApplicationMg.class, params);
			String[] s = {"*.sex"};
			renderDataTable(pager, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看会议室的使用情况
	 * 
	 * @return
	 */
	public String goViewRoom() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, id);
				if (meetingRequest.getApplicationMg() != null && meetingRequest.getApplicationMg().size() > 0) {
					applicationMgList = new ArrayList<ApplicationMg>();
					applicationMgList.addAll(meetingRequest.getApplicationMg());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewRoom";
	}

	/**
	 * 查看禁用状态下的会议室
	 * 
	 * @return
	 */
	public String goViewRooms() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewRooms";
	}

	/** 处理修改申请会议室操作 */
	public String saveOrUpdateApplyRoom() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(applicationMg.getId())) {
				isSave = false;
			}

			// 获取参会人
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = getParams();
				p.put("id," + SearchCondition.IN, employeeIds);
				empList = baseHibernateService.findAllByConditions(Employee.class, p);
				Set<Employee> subEmployees = new HashSet<Employee>();
				if (empList != null && empList.size() > 0) {
					for (Employee employee : empList) {
						subEmployees.add(employee);
					}
				}
				applicationMg.setAffiliatedEmployees(subEmployees);
			}
			/** 拿到当前用户的姓名，保存 */
			Employee employee = getEmployee();
			if (employee != null) {
				applicationMg.setUploadPersonId(SecurityUtil.getCurrentEmpId());
				applicationMg.setUploadPersonName(employee.getName());
				applicationMg.setUploadPerson(employee.getName());
				applicationMg.setEmployee(employee);
				Set<Employee> subEmployees = new HashSet<Employee>();
				subEmployees.add(employee);
				applicationMg.setIssuerEmployees(subEmployees);
			}
			applicationMg.setIsTemps(0);
			applicationMg.setStatus("0");
			applicationMg.setMeetingRequest(meetingRequest);
			applicationMg = baseHibernateService.merge(applicationMg);

			if (applicationMg.getAffiliatedEmployees() != null && applicationMg.getAffiliatedEmployees().size() > 0) {
				for (Employee e : applicationMg.getAffiliatedEmployees()) {
					userIdStr += "|" + e.getUserId();
				}
			}
			if (StringUtils.isNotEmpty(userIdStr)) {
				// 更新企业号token
				updateWxpWeixinSite();
				sendMessage("7", userIdStr.substring(1), applicationMg.getMeetingTheme(), applicationMg.getMeetingDescription(), applicationMg.getId(), "", applicationMg.getTenantId());
			}
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
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * @return the employeeIds
	 */
	public String getEmployeeIds() {
		return employeeIds;
	}

	/**
	 * @param employeeIds
	 *            the employeeIds to set
	 */
	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	/** 处理会议室申请删除操作 */
	public void deletesById() {
		try {
			ApplicationMg pb = baseHibernateService.findEntityById(ApplicationMg.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 跳转新增会议室页面
	 * 
	 * @return
	 */
	public String goSaveMeetingRD() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveMeetingRD";
	}

	/**
	 * 跳转修改会议室状态页面
	 * 
	 * @return
	 */
	public String goSaveState() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveState";
	}

	/** 处理修改会议室基础数据操作 */
	public String saveMeetingRD() {

		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(meetingRequest.getId())) {
				isSave = false;
			}
			/** 拿到当前用户的姓名，保存 */
			this.meetingRequest.setUploadPersonId(SecurityUtil.getCurrentEmpId());
			this.meetingRequest.setUploadPerson(SecurityUtil.getCurrentUserName());
			meetingRequest.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			Employee employee = getEmployee();
			if (employee != null) {
				meetingRequest.setUploadPersonName(employee.getName());
				meetingRequest.setUploadPerson(employee.getName());
			}
			meetingRequest.setCreateTime(new Date());
			meetingRequest = baseHibernateService.merge(meetingRequest);
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
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理会议室基础数据删除操作 */
	public void deletessById() {
		try {
			MeetingRequest pb = baseHibernateService.findEntityById(MeetingRequest.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}

	/* 会议室管理 */
	public String goConferenceRoomManagement() {
		try {
			Map<String, Object> params = getParams();
			meetingRequestList = vixntBaseService.findAllByConditions(MeetingRequest.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goConferenceRoomManagement";
	}

	/** 处理会议室管理删除操作 */
	public void deleteById() {
		try {
			MeetingRequest pb = baseHibernateService.findEntityById(MeetingRequest.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public MeetingRequest getMeetingRequest() {
		return meetingRequest;
	}

	public void setMeetingRequest(MeetingRequest meetingRequest) {
		this.meetingRequest = meetingRequest;
	}

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	/**
	 * @return the userIdStr
	 */
	public String getUserIdStr() {
		return userIdStr;
	}

	/**
	 * @param userIdStr
	 *            the userIdStr to set
	 */
	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public MeetingSummary getMeetingSummary() {
		return meetingSummary;
	}

	public void setMeetingSummary(MeetingSummary meetingSummary) {
		this.meetingSummary = meetingSummary;
	}

	public String getApplicationMgId() {
		return applicationMgId;
	}

	public void setApplicationMgId(String applicationMgId) {
		this.applicationMgId = applicationMgId;
	}

	/**
	 * @return the empList
	 */
	public List<Employee> getEmpList() {
		return empList;
	}

	/**
	 * @param empList
	 *            the empList to set
	 */
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<ApplicationMg> getApplicationMgList() {
		return applicationMgList;
	}

	public void setApplicationMgList(List<ApplicationMg> applicationMgList) {
		this.applicationMgList = applicationMgList;
	}
}

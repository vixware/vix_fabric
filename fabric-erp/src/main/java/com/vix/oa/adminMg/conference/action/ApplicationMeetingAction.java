package com.vix.oa.adminMg.conference.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.billtype.BillType;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.controller.ApplicationMeetingController;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.EquipmentDetails;
import com.vix.oa.adminMg.conference.entity.MeetingDevice;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 
 * @ClassName: ApplicationMeetingAction
 * @Description: 申请会议室
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-3-19 上午11:10:14
 */
@Controller
@Scope("prototype")
public class ApplicationMeetingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(ApplicationMeetingController.class);

	@Autowired
	private ApplicationMeetingController applicationMeetingController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private ApplicationMg applicationMg;
	private EquipmentDetails equipmentDetails;
	private String id;
	private String meetingRequestId;
	private String pageNo;
	private Date updateTime;
	/** 申请会议室 */
	private List<ApplicationMg> applicationMgList;
	/** 会议室管理 */
	private List<MeetingRequest> meetingRequestList;
	/** 会议室设备管理 */
	private List<MeetingDevice> meetingDeviceList;
	private List<EquipmentDetails> equipmentDetailsList;

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
			 * String employeeId = SecurityUtil.getCurrentUserId();
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

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("meetingRoomStatus," + SearchCondition.NOEQUAL, 1);
			meetingRequestList = baseHibernateService.findAllByConditions(MeetingRequest.class, params);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				applicationMg = applicationMeetingController.doListEntityById(id);
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

	/** 屏蔽，业务更改，暂时不符合业务需求 */
	/** 根据会议室ID查出当前会议室当天申请使用的时间 */
	public String goMeetingRequest() {
		try {
			if (StringUtils.isNotEmpty(meetingRequestId) && !meetingRequestId.equals("0")) {
				applicationMgList = baseHibernateService.findAllByEntityClassAndAttribute(ApplicationMg.class, "meetingRequest.id", meetingRequestId);
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			StringBuffer strBuffer = new StringBuffer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strTime = "[";
			int index = 0;
			for (int i = 0; i < applicationMgList.size(); i++) {
				if (sdf.format(applicationMgList.get(i).getMeetingEndTime()).equals(sdf.format(new Date()))) {
					if (index > 0) {
						strTime += ",";
					}
					strTime += "{\"meetingStartTime\":\"";
					strTime += fmat.format(applicationMgList.get(i).getMeetingStartTime()) + "\",";
					strTime += "\"meetingEndTime\":\"";
					strTime += fmat.format(applicationMgList.get(i).getMeetingEndTime()) + "\"";
					strTime += "}";
					index++;
				}
			}
			strTime += "]";
			strBuffer.append("{\"success\":true,\"useTime\":" + strTime + "}");
			response.getWriter().print(strBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(applicationMg.getId()) && !"0".equals(applicationMg.getId())) {
				isSave = false;
			}
			/** 索引 */
			String meetingTheme = applicationMg.getMeetingTheme();
			String py = ChnToPinYin.getPYString(meetingTheme);

			/** 根据meetingRequest的id对meetingRequest里的MeetingRoomStatus进行set */
			MeetingRequest meetingRequest = baseHibernateService.findEntityById(MeetingRequest.class, applicationMg.getMeetingRequest().getId());
			meetingRequest.setMeetingRoomStatus(1);
			baseHibernateService.merge(meetingRequest);

			applicationMg.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(applicationMg);
			applicationMg = applicationMeetingController.doSaveSalesOrder(applicationMg);
			this.applicationMg.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.applicationMg.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			applicationMg.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			applicationMg.setIsTemps(0);
			this.saveBaseEntity(this.applicationMg);
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ApplicationMg pb = applicationMeetingController.findEntityById(id);
			if (null != pb) {
				applicationMeetingController.doDeleteByEntity(pb);
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

	/** 跳转到设备明细新增修改页面 */
	public String goAddEquipmentDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				equipmentDetails = applicationMeetingController.doListEquipmentDetailsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddEquipmentDetails";
	}

	/** 保存设备明细Tab页弹框页面 */
	public String saveOrUpdateEquipmentDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				applicationMg = applicationMeetingController.doListEntityById(id);
				equipmentDetails.setApplicationMg(applicationMg);
				applicationMeetingController.doSaveEquipmentDetails(equipmentDetails);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 获取设备明细json数据 */
	public void getEquipmentDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ApplicationMg applicationMg = applicationMeetingController.doListEntityById(id);
				json = convertListToJson(new ArrayList<EquipmentDetails>(applicationMg.getEquipmentDetails()), applicationMg.getEquipmentDetails().size(), "applicationMg");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除tab明细操作 */
	public String deleteEquipmentDetailsById() {
		try {
			EquipmentDetails equipmentDetails = applicationMeetingController.doListEquipmentDetailsById(id);
			if (null != equipmentDetails) {
				applicationMeetingController.deleteEquipmentDetailsEntity(equipmentDetails);
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

	// 跳转到选择设备页面
	public String goChooseEquipment() {
		return "goChooseEquipment";
	}

	/** 获取设备列表数据 */
	public String goEquipmentList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			Pager pager = applicationMeetingController.goSingleList1(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goEquipmentList";
	}

	public String getId() {
		return id;
	}

	public List<ApplicationMg> getApplicationMgList() {
		return applicationMgList;
	}

	public void setApplicationMgList(List<ApplicationMg> applicationMgList) {
		this.applicationMgList = applicationMgList;
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

	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
	}

	public List<MeetingDevice> getMeetingDeviceList() {
		return meetingDeviceList;
	}

	public void setMeetingDeviceList(List<MeetingDevice> meetingDeviceList) {
		this.meetingDeviceList = meetingDeviceList;
	}

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

	public EquipmentDetails getEquipmentDetails() {
		return equipmentDetails;
	}

	public void setEquipmentDetails(EquipmentDetails equipmentDetails) {
		this.equipmentDetails = equipmentDetails;
	}

	public List<EquipmentDetails> getEquipmentDetailsList() {
		return equipmentDetailsList;
	}

	public void setEquipmentDetailsList(List<EquipmentDetails> equipmentDetailsList) {
		this.equipmentDetailsList = equipmentDetailsList;
	}

	public String getMeetingRequestId() {
		return meetingRequestId;
	}

	public void setMeetingRequestId(String meetingRequestId) {
		this.meetingRequestId = meetingRequestId;
	}
}

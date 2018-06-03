package com.vix.oa.personaloffice.action;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.controller.PersonalAttendanceController;
import com.vix.oa.personaloffice.entity.Attendance;
import com.vix.oa.personaloffice.entity.OvertimeRecords;
import com.vix.oa.personaloffice.entity.PersonalAttendance;
import com.vix.oa.personaloffice.entity.TLeaveRecords;
import com.vix.oa.personaloffice.entity.TripRecord;
/**
 * 
 * @ClassName: PersonalAttendanceAction
 * @Description: 考勤管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-4-16 下午1:19:40
 */
@Controller
@Scope("prototype")
public class PersonalAttendanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalAttendanceController.class);
	@Autowired
	private PersonalAttendanceController personalAttendanceController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private PersonalAttendance personalAttendance;
	private PersonalAttendance entity;
	private TLeaveRecords tleaveRecords;
	private TripRecord tripRecord;
	private OvertimeRecords overtimeRecords;
	private TLeaveRecords tLeaveRecordsentity;
	private Attendance attendance;
	private String id;
	public Integer publishType;
	private String pageNo;
	private Date updateTime;
	
	/** 个人办公-外出记录*/
	private List<PersonalAttendance> personalAttendanceList;
	
	/** 个人办公-请假记录*/
	private List<TLeaveRecords> tLeaveRecordsList;	
	
	/** 个人办公-出差记录*/
	private List<TripRecord> tripRecordList;	
	
	/** 个人办公-加班记录 */
	private List<OvertimeRecords> overtimeRecordsList;	
	
	/** 个人办公-加班记录 */
	private List<Attendance> attendanceList;	
	
	/** 跳转到外出记录列表页面 */
	@Override
	public String goList() {
		try {
			personalAttendanceList = personalAttendanceController.doListSalesOrderIndex();
			tLeaveRecordsList = personalAttendanceController.doListSalesOrderIndex1();
			tripRecordList = personalAttendanceController.doListSalesOrderIndex2();
			overtimeRecordsList = personalAttendanceController.doListSalesOrderIndex3();
			attendanceList = personalAttendanceController.doListAttendanceIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取外出记录列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 1);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("outstartDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personalAttendanceController.doSubSingleList(params,getPager());
			logger.info("获取外出记录列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 外出记录搜索功能 */
	public String goSearchPager() {
		
		return "goSearchPager";
	}
	
	/** 获取外出记录搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 外出原因
			String outReason = getRequestParameter("outReason");
			if (null != outReason && !"".equals(outReason)) {
				outReason = URLDecoder.decode(outReason, "utf-8");
			}
			// 外出人员
			String approver = getRequestParameter("approver");
			if (null != approver && !"".equals(approver)) {
				approver = URLDecoder.decode(approver, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("outReason," + SearchCondition.ANYLIKE, outReason);
				pager = personalAttendanceController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != approver && !"".equals(approver)) {
					params.put("approver," + SearchCondition.ANYLIKE, approver);
				}
				if (null != outReason && !"".equals(outReason)) {
					params.put("outReason," + SearchCondition.ANYLIKE, outReason);
				}
				pager = personalAttendanceController.goSingleList(params, getPager());
			}
			logger.info("获取外出记录搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 获取请假记录列表数据 */
	public String goTleaveRecordsList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 0);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("vacateendDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personalAttendanceController.doSubSingleList1(params,getPager());
			logger.info("获取请假记录列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTleaveRecordsList";
	}
	
	/** 请假记录搜索功能  */
	public String goTleaveList() {
			
		return "goTleaveList";
	}
	
	/** 获取请假记录搜索列表数据 */
	public String goSearchList1() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 请假原因
			String vacateReason = getRequestParameter("vacateReason");
			if (null != vacateReason && !"".equals(vacateReason)) {
				vacateReason = URLDecoder.decode(vacateReason, "utf-8");
			}
			// 请假人员
			String approver = getRequestParameter("approver");
			if (null != approver && !"".equals(approver)) {
				approver = URLDecoder.decode(approver, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("vacateReason," + SearchCondition.ANYLIKE, vacateReason);
				pager = personalAttendanceController.goSearchList1(params, getPager());
			}
			// 高级搜索
			else {
				if (null != approver && !"".equals(approver)) {
					params.put("approver," + SearchCondition.ANYLIKE, approver);
				}
				if (null != vacateReason && !"".equals(vacateReason)) {
					params.put("vacateReason," + SearchCondition.ANYLIKE, vacateReason);
				}
				pager = personalAttendanceController.goTleaveRecordsList(params, getPager());
			}
			logger.info("获取请假记录搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTleaveRecordsList";
	}
	
	
	
	/** 获取出差记录列表数据 */
	public String goTripRecordList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 2);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("businessstartDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personalAttendanceController.doSubSingleList2(params,getPager());
			logger.info("获取出差记录列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTripRecordList";
	}
	
	/**出差记录搜索功能*/
	public String goTripList() {
		
		return "goTripList";
	}
	
	/** 获取出差记录搜索列表数据 */
	public String goSearchList2() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 出差原因
			String reason = getRequestParameter("reason");
			if (null != reason && !"".equals(reason)) {
				reason = URLDecoder.decode(reason, "utf-8");
			}
			// 出差地区
			String businessLocation = getRequestParameter("businessLocation");
			if (null != businessLocation && !"".equals(businessLocation)) {
				businessLocation = URLDecoder.decode(businessLocation, "utf-8");
			}
			// 出差人
			String approver = getRequestParameter("approver");
			if (null != approver && !"".equals(approver)) {
				approver = URLDecoder.decode(approver, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("reason," + SearchCondition.ANYLIKE, reason);
				pager = personalAttendanceController.goSearchList2(params, getPager());
			}
			// 高级搜索
			else {
				if (null != approver && !"".equals(approver)) {
					params.put("approver," + SearchCondition.ANYLIKE, approver);
				}
				if (null != businessLocation && !"".equals(businessLocation)) {
					params.put("businessLocation," + SearchCondition.ANYLIKE, businessLocation);
				}
				if (null != reason && !"".equals(reason)) {
					params.put("reason," + SearchCondition.ANYLIKE, reason);
				}
				pager = personalAttendanceController.goTripRecordList(params, getPager());
			}
			logger.info("获取出差记录搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTripRecordList";
	}
	
	
	/** 获取加班记录列表数据 */
	public String goOvertimeRecordsList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 2);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("applicationovertimeDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personalAttendanceController.doSubSingleList3(params,getPager());
			logger.info("获取加班记录列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOvertimeRecordsList";
	}
	
	/**加班登记搜索功能*/
	public String goOvertimeList() {
		
		return "goOvertimeList";
	}
	
	/** 获取加班登记搜索列表数据 */
	public String goSearchList3() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 加班内容
			String overtimeContent = getRequestParameter("overtimeContent");
			if (null != overtimeContent && !"".equals(overtimeContent)) {
				overtimeContent = URLDecoder.decode(overtimeContent, "utf-8");
			}
			// 申请人
			String approver = getRequestParameter("approver");
			if (null != approver && !"".equals(approver)) {
				approver = URLDecoder.decode(approver, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("overtimeContent," + SearchCondition.ANYLIKE, overtimeContent);
				pager = personalAttendanceController.goSearchList3(params, getPager());
			}
			// 高级搜索
			else {
				if (null != approver && !"".equals(approver)) {
					params.put("approver," + SearchCondition.ANYLIKE, approver);
				}
				if (null != overtimeContent && !"".equals(overtimeContent)) {
					params.put("overtimeContent," + SearchCondition.ANYLIKE, overtimeContent);
				}
				pager = personalAttendanceController.goOvertimeRecordsList(params, getPager());
			}
			logger.info("获取加班登记搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOvertimeRecordsList";
	}
	
	/** 获取考勤记录列表数据 */
	public String goAttendanceList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 2);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("provisionDate");
				getPager().setOrderBy("desc");
			}
			Pager pager = personalAttendanceController.doSubSingleList4(params,getPager());
			logger.info("获取考勤列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAttendanceList";
	}
	
	
	/** 考勤记录搜索功能*/
	public String goAttendancePager() {
		
		return "goAttendancePager";
	}
	
	/** 获取考勤记录搜索列表数据 */
	public String goSearchList4() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 录入人
			String loginPerson = getRequestParameter("loginPerson");
			if (null != loginPerson && !"".equals(loginPerson)) {
				loginPerson = URLDecoder.decode(loginPerson, "utf-8");
			}
			// 登录编号
			String loginOrder = getRequestParameter("loginOrder");
			if (null != loginOrder && !"".equals(loginOrder)) {
				loginOrder = URLDecoder.decode(loginOrder, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("loginPerson," + SearchCondition.ANYLIKE, loginPerson);
				pager = personalAttendanceController.goSearchList4(params, getPager());
			}
			// 高级搜索
			else {
				if (null != loginOrder && !"".equals(loginOrder)) {
					params.put("loginOrder," + SearchCondition.ANYLIKE, loginOrder);
				}
				if (null != loginPerson && !"".equals(loginPerson)) {
					params.put("loginPerson," + SearchCondition.ANYLIKE, loginPerson);
				}
				pager = personalAttendanceController.goAttendanceList(params, getPager());
			}
			logger.info("获取考勤记录搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAttendanceList";
	}
	
	/** 获取列表数据 */
	public String goSingleList5() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("publishType," + SearchCondition.EQUAL, 2);*/
			Pager pager = personalAttendanceController.doSubSingleList(params,getPager());
			logger.info("我的值班");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	
	public String goSingle() {
		
		return "goSingle";
	}
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				personalAttendance = personalAttendanceController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(personalAttendance.getId()) && !"".equals(personalAttendance.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(personalAttendance);
			personalAttendance = personalAttendanceController.doSaveSalesOrder(personalAttendance);
			this.personalAttendance.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.personalAttendance.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			personalAttendance.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.personalAttendance);
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
	
	/** 处理外出记录删除操作 */
	public String deleteById() {
		try {
			PersonalAttendance pb = personalAttendanceController.findEntityById(id);
			if (null != pb) {
				personalAttendanceController.doDeleteByEntity(pb);
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
	
	
	/** 处理删除请假记录操作 */
	public String deleteLeave() {
		try {
			TLeaveRecords pb = personalAttendanceController.findLeaveById(id);
			if (null != pb) {
				personalAttendanceController.doDeleteByLeave(pb);
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
	
	/** 处理删除出差记录操作 */
	public String deleteTripRecord() {
		try {
			TripRecord pb = personalAttendanceController.findTripRecordById(id);
			if (null != pb) {
				personalAttendanceController.doDeleteByTripRecord(pb);
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
	
	/** 处理删除加班登记操作 */
	public String deleteOvertimeRecords() {
		try {
			OvertimeRecords pb = personalAttendanceController.findOvertimeRecordsById(id);
			if (null != pb) {
				personalAttendanceController.doDeleteByOvertimeRecords(pb);
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
	
	/** 处理删除考勤记录操作 */
	public String deleteAttendance() {
		try {
			Attendance pb = personalAttendanceController.findAttendanceById(id);
			if (null != pb) {
				personalAttendanceController.doDeleteByAttendance(pb);
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


	public PersonalAttendance getPersonalAttendance() {
		return personalAttendance;
	}


	public void setPersonalAttendance(PersonalAttendance personalAttendance) {
		this.personalAttendance = personalAttendance;
	}


	public PersonalAttendance getEntity() {
		return entity;
	}


	public void setEntity(PersonalAttendance entity) {
		this.entity = entity;
	}


	public List<PersonalAttendance> getPersonalAttendanceList() {
		return personalAttendanceList;
	}


	public void setPersonalAttendanceList(
			List<PersonalAttendance> personalAttendanceList) {
		this.personalAttendanceList = personalAttendanceList;
	}


	public TLeaveRecords getTleaveRecords() {
		return tleaveRecords;
	}

	public void setTleaveRecords(TLeaveRecords tleaveRecords) {
		this.tleaveRecords = tleaveRecords;
	}

	public TLeaveRecords gettLeaveRecordsentity() {
		return tLeaveRecordsentity;
	}


	public void settLeaveRecordsentity(TLeaveRecords tLeaveRecordsentity) {
		this.tLeaveRecordsentity = tLeaveRecordsentity;
	}


	public List<TLeaveRecords> gettLeaveRecordsList() {
		return tLeaveRecordsList;
	}


	public void settLeaveRecordsList(List<TLeaveRecords> tLeaveRecordsList) {
		this.tLeaveRecordsList = tLeaveRecordsList;
	}


	public TripRecord getTripRecord() {
		return tripRecord;
	}


	public void setTripRecord(TripRecord tripRecord) {
		this.tripRecord = tripRecord;
	}


	public List<TripRecord> getTripRecordList() {
		return tripRecordList;
	}


	public void setTripRecordList(List<TripRecord> tripRecordList) {
		this.tripRecordList = tripRecordList;
	}


	public OvertimeRecords getOvertimeRecords() {
		return overtimeRecords;
	}


	public void setOvertimeRecords(OvertimeRecords overtimeRecords) {
		this.overtimeRecords = overtimeRecords;
	}


	public List<OvertimeRecords> getOvertimeRecordsList() {
		return overtimeRecordsList;
	}


	public void setOvertimeRecordsList(List<OvertimeRecords> overtimeRecordsList) {
		this.overtimeRecordsList = overtimeRecordsList;
	}


	public Attendance getAttendance() {
		return attendance;
	}


	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}


	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}


	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

}

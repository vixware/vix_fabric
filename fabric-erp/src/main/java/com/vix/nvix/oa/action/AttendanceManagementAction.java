package com.vix.nvix.oa.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.MonthlyStatistics;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.nvix.oa.constant.PunchCardRecordConstant;
import com.vix.oa.personaloffice.entity.PersonalAttendance;
import com.vix.oa.personaloffice.entity.TLeaveRecords;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AttendanceManagementAction
 * @Description: 考勤管理
 * @author bobchen
 * @date 2016年1月12日 上午9:40:23
 *
 */
@Controller
@Scope("prototype")
public class AttendanceManagementAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IPersonalAttendanceService personalAttendanceService;
	private PersonalAttendance personalAttendance;
	private TLeaveRecords tleaveRecords;
	private String syncTag;
	private String weekday;
	private String nowTime;
	/** 签到时间 */
	private String doAttendance;
	/** 签退时间 */
	private String outAttendance;
	// 考勤次数
	private Integer times;
	// 迟到
	private Integer lates;
	// 早退
	private Integer earlys;
	// 缺卡
	private Integer lacks;
	// 旷工
	private Integer absenteeisms;
	// 月统计考勤
	private MonthlyStatistics monthly;
	private List<MonthlyStatistics> monthlyList;
	private PunchCardRecord punchCard;
	private List<PunchCardRecord> punchCardList;
	private Map<String, List<PunchCardRecord>> pMap;
	private List<String> pcrKeyList;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sevenDay = new SimpleDateFormat("yyyy-MM-dd");
	private List<PunchCardRecord> punchCardRecordList;
	@Override
	public String goList() {
		try {
			/** 是否签到 */
			Object isDoAttendanceObj = getSession().getAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE);
			if (StrUtils.objectIsNull(isDoAttendanceObj)) {
				punchCardList = loadPunchCardRecord();
				if (null != punchCardList && punchCardList.size() >= 1) {
					getSession().setAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE, "1");
				} else {
					getSession().setAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE, "0");
				}
			}
			/** 是否签退 */
			Object isOutAttendanceObj = getSession().getAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE);
			if (StrUtils.objectIsNull(isOutAttendanceObj)) {
				if (null == punchCardList) {
					punchCardList = loadPunchCardRecord();
				}
				if (null != punchCardList && punchCardList.size() >= 2) {
					getSession().setAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE, "1");
				} else {
					getSession().setAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE, "0");
				}
			}
			/** 签到时间 */
			Object doAttendanceTimeObj = getSession().getAttribute(PunchCardRecordConstant.PCR_DOATTENDANCE_TIME);
			if (StrUtils.objectIsNull(doAttendanceTimeObj)) {
				if (null == punchCardList) {
					punchCardList = loadPunchCardRecord();
				}
				if (null != punchCardList && punchCardList.size() >= 1) {
					PunchCardRecord pcr = punchCardList.get(0);
					if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
						getSession().setAttribute(PunchCardRecordConstant.PCR_DOATTENDANCE_TIME, pcr.getPunchCardDate().substring(11, pcr.getPunchCardDate().length()));
					}
				}
			}
			/** 签退时间 */
			Object outAttendanceTimeObj = getSession().getAttribute(PunchCardRecordConstant.PCR_OUTATTENDANCE_TIME);
			if (StrUtils.objectIsNull(outAttendanceTimeObj)) {
				if (null == punchCardList) {
					punchCardList = loadPunchCardRecord();
				}
				if (null != punchCardList && punchCardList.size() >= 2) {
					PunchCardRecord pcr = punchCardList.get(1);
					if (null != pcr && pcr.getPunchCardDate().length() >= 11) {
						getSession().setAttribute(PunchCardRecordConstant.PCR_OUTATTENDANCE_TIME, pcr.getPunchCardDate().substring(11, pcr.getPunchCardDate().length()));
					}
				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
			Employee emp = getEmployee();

			// 获取周记录
			Map<String, Object> params = getParams();
			if (null != emp && StrUtils.isNotEmpty(emp.getCode())) {
				params.put("userCode", emp.getCode());
			}
			params.put("recordDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(sevenDay.parse(DaysUtils.getSevenDays())) + "!" + DaysUtils.getEndDay(new Date()));
			punchCardList = personalAttendanceService.findAllTopEntityByCountAndConditions(PunchCardRecord.class, "recordDate", "asc", 14, params);
			if (null != punchCardList && punchCardList.size() > 0) {
				pMap = new HashMap<String, List<PunchCardRecord>>();
				for (PunchCardRecord pcr : punchCardList) {
					if (null != pcr && null != pcr.getRecordDate()) {
						String key = (sevenDay.format(pcr.getRecordDate()).split("-"))[1] + "月" + (sevenDay.format(pcr.getRecordDate()).split("-"))[2] + "日";
						if (pMap.keySet().contains(key)) {
							pMap.get(key).add(pcr);
						} else {
							pMap.put(key, new ArrayList<PunchCardRecord>());
							pMap.get(key).add(pcr);
						}
					}
				}
				pcrKeyList = new ArrayList<String>(pMap.keySet());
				if (pMap.keySet().size() > 0) {
					Collections.sort(pcrKeyList, Collections.reverseOrder());
				}
			}

			String yearsAndMonth = String.valueOf(DateUtil.getYear()) + " " + String.valueOf(DateUtil.getMonth());
			Map<String, Object> param = getParams();
			if (emp != null && StrUtils.isNotEmpty(emp.getName())) {
				param.put("empName", emp.getName());
				param.put("dayAndMonth", yearsAndMonth);
			}

			// 获取月明细
			monthlyList = personalAttendanceService.findAllByConditions(MonthlyStatistics.class, param);
			if (null != monthlyList && monthlyList.size() > 0) {
				for (MonthlyStatistics ms : monthlyList) {
					times = ms.getCardNum().intValue();
					lates = ms.getLateTimes().intValue();
					earlys = ms.getEarlyTimes().intValue();
					absenteeisms = ms.getAbsenteeismNum().intValue();
					// lacks = ms.get
				}
			} else {
				times = 0;
				lates = 0;
				earlys = 0;
				lacks = 0;
				absenteeisms = 0;
			}
			weekday = sdf.format(new Date());
			nowTime = DateUtil.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GO_LIST;
	}
	public void calendarEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			//params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
			punchCardRecordList = vixntBaseService.findAllByConditions(PunchCardRecord.class, params);
			if (punchCardRecordList != null && punchCardRecordList.size() > 0) {
				JSONArray array = new JSONArray();
				for (PunchCardRecord punchCardRecord : punchCardRecordList) {
					JSONObject object = new JSONObject();
					object.put("id", punchCardRecord.getId());
					object.put("title", punchCardRecord.getName());
					if (punchCardRecord.getPunchCardDate() != null) {
						object.put("start", punchCardRecord.getPunchCardDate());
					}
					if (punchCardRecord.getPunchCardDate() != null) {
						object.put("end", punchCardRecord.getPunchCardDate());
					}
					object.put("allDay", false);
					array.add(object);
				}
				json = array.toString();
			}
			if (!"".equals(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<PunchCardRecord> loadPunchCardRecord() throws Exception {
		Map<String, Object> params = getParams();
		Employee emp = getEmployee();
		if (null != emp) {
			params.put("userCode," + SearchCondition.EQUAL, emp.getCode());
		}
		params.put("recordDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
		return personalAttendanceService.findAllTopEntityByCountAndConditions(PunchCardRecord.class, "recordDate", "asc", 2, params);
	}

	/* 查询外出记录数据 */
	public void goPersonalAttendance() {
		syncTag = getRequestParameter("syncTag");
		try {
			Map<String, Object> params = getParams();
			Pager pager = personalAttendanceService.findPagerByHqlConditions(getPager(), PersonalAttendance.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 查询请假记录数据 */
	public void goTleaveRecords() {
		syncTag = getRequestParameter("syncTag");
		try {
			Map<String, Object> params = getParams();
			Pager pager = personalAttendanceService.findPagerByHqlConditions(getPager(), TLeaveRecords.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		return "goSaveOrUpdate";
	}

	public void doAttendance() {
		try {
			PunchCardRecord pcr = new PunchCardRecord();
			Employee emp = getEmployee();
			if (null != emp) {
				pcr.setSource("0");
				pcr.setUserCode(emp.getCode());
				pcr.setRecordDate(new Date());
				pcr.setPunchCardDate(sdf.format(new Date()));
				pcr.setIpAddress(getRequest().getRemoteAddr());
				loadCommonData(pcr);
				punchCard = personalAttendanceService.merge(pcr);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			doAttendance = sdf.format(new Date());
			getSession().setAttribute(PunchCardRecordConstant.PCR_IS_DOATTENDANCE, "1");
			getSession().setAttribute(PunchCardRecordConstant.PCR_DOIPADDRESS, SecurityUtil.getCurrentUserIp());
			getSession().setAttribute(PunchCardRecordConstant.PCR_DOATTENDANCE_TIME, sdf.format(new Date()));
			if (emp != null && StrUtils.isNotEmpty(emp.getName())) {
				getSession().setAttribute(PunchCardRecordConstant.PCR_USERNAME, emp.getName());
			}
		} catch (Exception e) {
			renderText("0:" + "签到失败!请联系管理员!");
			e.printStackTrace();
		}
		if (punchCard != null) {
			renderText(doAttendance + "," + punchCard.getId() + "," + "签到成功！");
		} else {
			renderText("1," + "签到失败!请联系管理员!");
		}
	}

	public void outAttendance() {
		try {
			PunchCardRecord pcr = new PunchCardRecord();
			Employee emp = getEmployee();
			if (null != emp) {
				pcr.setSource("0");
				pcr.setUserCode(emp.getCode());
				pcr.setRecordDate(new Date());
				pcr.setPunchCardDate(sdf.format(new Date()));
				pcr.setIpAddress(getRequest().getRemoteAddr());
				loadCommonData(pcr);
				punchCard = personalAttendanceService.merge(pcr);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			outAttendance = sdf.format(new Date());
			getSession().setAttribute(PunchCardRecordConstant.PCR_IS_OUTATTENDANCE, "1");
			getSession().setAttribute(PunchCardRecordConstant.PCR_OUTIPADDRESS, SecurityUtil.getCurrentUserIp());
			getSession().setAttribute(PunchCardRecordConstant.PCR_OUTATTENDANCE_TIME, sdf.format(new Date()));
			if (emp != null && StrUtils.isNotEmpty(emp.getName())) {
				getSession().setAttribute(PunchCardRecordConstant.PCR_USERNAME, emp.getName());
			}
		} catch (Exception e) {
			renderText("0:" + "签退失败!请联系管理员!");
			e.printStackTrace();
		}
		if (punchCard != null) {
			renderText(outAttendance + "," + punchCard.getId() + "," + "签退成功！");
		} else {
			renderText("1," + "签退失败!请联系管理员!");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersonalAttendance getPersonalAttendance() {
		return personalAttendance;
	}

	public void setPersonalAttendance(PersonalAttendance personalAttendance) {
		this.personalAttendance = personalAttendance;
	}

	public TLeaveRecords getTleaveRecords() {
		return tleaveRecords;
	}

	public void setTleaveRecords(TLeaveRecords tleaveRecords) {
		this.tleaveRecords = tleaveRecords;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	public IPersonalAttendanceService getPersonalAttendanceService() {
		return personalAttendanceService;
	}

	public void setPersonalAttendanceService(IPersonalAttendanceService personalAttendanceService) {
		this.personalAttendanceService = personalAttendanceService;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getDoAttendance() {
		if (StrUtils.isEmpty(doAttendance)) {
			return "未签到";
		}
		return doAttendance;
	}

	public void setDoAttendance(String doAttendance) {
		this.doAttendance = doAttendance;
	}

	public String getOutAttendance() {
		if (StrUtils.isEmpty(outAttendance)) {
			return "未签退";
		}
		return outAttendance;
	}

	public void setOutAttendance(String outAttendance) {
		this.outAttendance = outAttendance;
	}

	public PunchCardRecord getPunchCard() {
		return punchCard;
	}

	public void setPunchCard(PunchCardRecord punchCard) {
		this.punchCard = punchCard;
	}

	public List<PunchCardRecord> getPunchCardList() {
		return punchCardList;
	}

	public void setPunchCardList(List<PunchCardRecord> punchCardList) {
		this.punchCardList = punchCardList;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getLates() {
		return lates;
	}

	public void setLates(Integer lates) {
		this.lates = lates;
	}

	public Integer getEarlys() {
		return earlys;
	}

	public void setEarlys(Integer earlys) {
		this.earlys = earlys;
	}

	public Integer getLacks() {
		return lacks;
	}

	public void setLacks(Integer lacks) {
		this.lacks = lacks;
	}

	public Integer getAbsenteeisms() {
		return absenteeisms;
	}

	public void setAbsenteeisms(Integer absenteeisms) {
		this.absenteeisms = absenteeisms;
	}

	public MonthlyStatistics getMonthly() {
		return monthly;
	}

	public void setMonthly(MonthlyStatistics monthly) {
		this.monthly = monthly;
	}

	public List<MonthlyStatistics> getMonthlyList() {
		return monthlyList;
	}

	public void setMonthlyList(List<MonthlyStatistics> monthlyList) {
		this.monthlyList = monthlyList;
	}

	public Map<String, List<PunchCardRecord>> getpMap() {
		return pMap;
	}

	public void setpMap(Map<String, List<PunchCardRecord>> pMap) {
		this.pMap = pMap;
	}

	public List<String> getPcrKeyList() {
		return pcrKeyList;
	}

	public void setPcrKeyList(List<String> pcrKeyList) {
		this.pcrKeyList = pcrKeyList;
	}
}
package com.vix.nvix.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.vix.common.properties.util.MyTool;
import com.vix.common.properties.util.TableBeanE;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.AlertNotice;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.HomeTemplateDetail;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;
import com.vix.nvix.oa.constant.PunchCardRecordConstant;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.oa.personaloffice.service.IPersonalAttendanceService;
import com.vix.oa.personaloffice.service.IQueryDataService;
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassFullName com.vix.nvix.task.action.VixNtIndexAction
 *
 * @author bjitzhang
 *
 * @date 2016年4月10日
 *
 */
@Controller
@Scope("prototype")
public class VixNtIndexAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private List<VixTask> unVixTaskList;
	private List<VixTask> onVixTaskList;
	private List<VixTask> endVixTaskList;
	@Autowired
	private IPersonalAttendanceService personalAttendanceService;
	@Autowired
	private IQueryDataService queryDataService;
	private List<VixTask> vixTaskList;// 我的任务
	private List<Calendars> calendarsList;
	private List<AlertNotice> alertNoticeList;// 提醒
	private List<PunchCardRecord> punchCardList;
	private Integer taskNum = 0;
	private String queryTime;
	private String homeTemplateCode = null;

	private List<HomeTemplateDetail> homeTemplateDetailList = null;
	public String goIndex() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName", "%" + searchtaskname + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());

			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
				if (pager != null) {
					taskNum = pager.getTotalCount();
				}
			}
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndex";
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

	/**
	 * 会员工作台
	 * 
	 * @return
	 */
	public String goListContent() {

		if (SecurityUtil.getBindHomeTemplateCode() != null) {
			homeTemplateCode = SecurityUtil.getBindHomeTemplateCode();
		} else {
			// 设置默认的工作台
			homeTemplateCode = "NVIXNT_OA";
		}

		if (SecurityUtil.getBindHomeTemplateId() != null) {
			Map<String, Object> params = getParams();
			params.put("homeTemplate.id," + SearchCondition.EQUAL, SecurityUtil.getBindHomeTemplateId());
			try {
				homeTemplateDetailList = baseHibernateService.findAllByConditions(HomeTemplateDetail.class, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "goListContent";
	}
	/**
	 * 协同工作台
	 * 
	 * @return
	 */
	public String goDefaultContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String vixTaskName = getDecodeRequestParameter("vixTaskName");
			if (StringUtils.isNotEmpty(vixTaskName)) {
				params.put("questName", "%" + vixTaskName + "%");
			}
			String searchtaskname = getDecodeRequestParameter("searchtaskname");
			if (StringUtils.isNotEmpty(searchtaskname)) {
				params.put("questName", "%" + searchtaskname + "%");
			}
			String complete = getRequestParameter("complete");
			if (StringUtils.isNotEmpty(complete)) {
				params.put("complete", Integer.parseInt(complete));
			}
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());

			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
				if (pager != null) {
					taskNum = pager.getTotalCount();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDefaultContent";
	}

	/**
	 * 跳转到搜索页
	 * 
	 * @return
	 */
	public String goSearch() {
		return "goSearch";
	}

	// 我当天的代办
	public void goMyToDoTaskList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());
			params.put("notcomplete", 2);
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 提醒
	public void goAlertNoticeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, AlertNotice.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 动态新闻
	 */
	public void goTrendsList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, Trends.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goMyUnVixTaskList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("complete", 0);
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());
			Pager pager = getPager();
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goMyOnVixTaskList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("complete", 1);
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());
			Employee e = getEmployee();
			Pager pager = getPager();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goMyEndVixTaskList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("complete", 2);
			params.put("status", "0");
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			params.put("taskStartTime", new Date());
			params.put("taskEndTime", new Date());
			Pager pager = getPager();
			Employee e = getEmployee();
			if (e != null) {
				params.put("employeeId", e.getId());
				pager = vixntBaseService.findTaskPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 我的任务列表
	public String goTask() {
		try {
			vixTaskList = vixntBaseService.findVixTaskList("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTask";
	}

	// 我的提醒列表
	public String goNotify() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskType," + SearchCondition.EQUAL, "4");
			calendarsList = vixntBaseService.findAllByConditions(Calendars.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goNotify";
	}
	
	//我的征集通知
	public void goCollect() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			/*params.put("taskType," + SearchCondition.EQUAL, "9");*/
			pager = vixntBaseService.findPagerByHqlConditions(pager, Calendars.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goCollectDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				 Calendars calendars = vixntBaseService.findEntityById(Calendars.class, id);
				 getRequest().setAttribute("calendars", calendars);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCollectDetail";
	}
	/**
	 * 公告通知
	 */
	public void goNoticeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
			if (pager.getResultList().size() < 5) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 5 - listSize; i++) {
					pager.getResultList().add(new AnnouncementNotification());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 我的消息列表
	public String goMsgs() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("taskType," + SearchCondition.EQUAL, "8");
			calendarsList = vixntBaseService.findAllByConditions(Calendars.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMsgs";
	}

	/** 获得’工作台 ’相关视图 返回json */
	public void nvixContentJsonA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			hsMap.put("controlSQL", controlSQL);
			List<String> timeArr = new ArrayList<String>();
			timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
			ArrayList<String> qTimeStateTemp = new ArrayList<String>();
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
					String yesterdayCur = MyTool.dateReduceOrAddByInt(timeArr.get(0), -1);// 当前时间的昨天，减少一天
					qTimeStateTemp.add(yesterdayCur);
				}
			}
			hsMap.put("timeArr", timeArr);
			Double totalDoubleA = (Double) queryDataService.findnvixContentJsonA(hsMap).get("totalDouble");

			timeArr = new ArrayList<String>();
			timeArr.add(MyTool.getYesterdayBy_yyyyMMdd());// 查询昨天
			if (qTimeStateTemp != null) {
				if (qTimeStateTemp.size() == 1) {
					timeArr = new ArrayList<String>();
					timeArr.add(qTimeStateTemp.get(0));
				}
			}
			hsMap.put("timeArr", timeArr);
			Double totalDoubleB = (Double) queryDataService.findnvixContentJsonA(hsMap).get("totalDouble");
			Double compareAB = totalDoubleA - totalDoubleB;
			String compareABStr = "增加";
			if (compareAB < 0.0) {
				compareABStr = "减少";
			}
			// 说明：后缀Cnum代表compare number 就是要在页面显示的比较后的数字
			// 说明：后缀Cstr代表compare String 就是要在页面显示的比较后的字符串
			// 说明：后缀Tnum代表Today number 就是要在页面显示的今天的数据，数字；
			String strA = "{\"" + (hsMap.get("controlSQL").toString() + "Cnum") + "\":" + MyTool.getplusDoubleByDouble(compareAB) + "}";
			String strB = "{\"" + (hsMap.get("controlSQL").toString() + "Cstr") + "\":" + MyTool.StringJsonReturn(compareABStr) + "}";
			String strC = "{\"" + (hsMap.get("controlSQL").toString() + "Tnum") + "\":" + totalDoubleA + "}";
			String ABC = MyTool.mergeJsonStringTwo(strA, strB, strC);
			renderJson(ABC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 获得’工作台 ’相关视图 返回json B (只查询今日的数据) */
	public void nvixContentJsonB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			hsMap.put("controlSQL", controlSQL);
			List<String> timeArr = new ArrayList<String>();
			timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
				}
			}
			hsMap.put("timeArr", timeArr);
			Double totalDoubleA = (Double) queryDataService.findnvixContentJsonB(hsMap).get("totalDouble");
			// 说明：后缀Tnum代表Today number 就是要在页面显示的今天的数据，数字；
			String strC = "{\"" + (hsMap.get("controlSQL").toString() + "Tnum") + "\":" + totalDoubleA + "}";
			renderJson(strC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 查询 工作台 '今日销售情况' */
	@SuppressWarnings("unchecked")
	public void nvixContentJsonC() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			// JVtodaySalesMoneyTop 先查‘销售金额Top’
			hsMap.put("controlSQL", "JVtodaySalesMoneyTopSdwv");// 按小时查询今日销售金额|JVtodaySalesMoneyTopSdwv
			List<String> timeArr = MyTool.getNewlyDateArrayByInt_ZX(-0);// 又改需求：改为今日
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
				}
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("myDATE_FORMAT", "'%H'");// 格式成24小时

			Map<String, Object> findOne = queryDataService.findnvixContentJsonC(hsMap);
			ArrayList<String> stringXaxis = (ArrayList<String>) findOne.get("stringXaxis");
			ArrayList<String> numberYaxisMoney = (ArrayList<String>) findOne.get("numberYaxis");

			hsMap.put("controlSQL", "JVtodaySalesOrdernumTopSdwv"); // 查询相关销售订单数|JVtodaySalesOrdernumTopSdwv
			Map<String, Object> findTwo = queryDataService.findnvixContentJsonC(hsMap);
			ArrayList<String> numberYaxisOrdernum = (ArrayList<String>) findTwo.get("numberYaxis");

			String isNull = "";
			isNull = new String("\"" + isNull + "\"");
			Gson gson = new Gson();
			String onlyFourJsonString = "{\"numberYaxisMoney\":" + numberYaxisMoney.toString() + ",\"stringXaxis\":" + gson.toJson(stringXaxis) + ",\"numberYaxisOrdernum\":" + numberYaxisOrdernum.toString() + ",\"isNull\":" + isNull + "}";
			renderJson(onlyFourJsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 获得’客户分析 ’客流量视图 **/
	@SuppressWarnings("unchecked")
	public void nvixContentJsonCSylb() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			List<String> timeArr = new ArrayList<String>();
			if (StringUtils.isNotEmpty(endTime)) {
				if (endTime.equals("Today")) {
					timeArr.add(MyTool.getTodayBy_yyyyMMdd());// 查询今天
				} else if (endTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(endTime);
				}
				hsMap.put("timeArr", timeArr);
				hsMap.put("myDATE_FORMAT", "'%H'");// 格式成24小时
				hsMap.put("controlSQL", "WorkbenchPassengerFlowSylb");// WorkbenchPassengerFlowSylb|工作台页面客流量
			}
			Map<String, Object> findOne = queryDataService.findnvixContentJsonC(hsMap);
			ArrayList<String> stringXaxis = (ArrayList<String>) findOne.get("stringXaxis");
			ArrayList<String> numberYaxisMoney = (ArrayList<String>) findOne.get("numberYaxis");
			Gson gosn = new Gson();
			String jsonOnlyTwo = "{\"memberOrderPassengersArr\":" + numberYaxisMoney.toString() + ",\"timeStr\":" + gosn.toJson(stringXaxis) + "}";
			renderJson(jsonOnlyTwo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 客户消费排行TOP10 返回html */
	@SuppressWarnings("unchecked")
	public String nvixContentHtmlA() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			List<String> timeArr = MyTool.getNewlyDateArrayByInt_ZX(-0);// 又改需求：改为今日
			String titleDate = "今日";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
					titleDate = "当前";
				}
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			tBeanEArr = (ArrayList<TableBeanE>) queryDataService.findnvixContentHtmlA(hsMap).get("tBeanEArr");
			getRequest().setAttribute("titleDate", titleDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cusConsumptionTOP";// 客户消费排行TOP10
									// ：CustomerConsumptionRankingTOP10
									// (cusConsumptionTOP)
	}

	/** 商品销量排行 TOP10 返回html */
	@SuppressWarnings("unchecked")
	public String nvixContentHtmlB() {
		try {
			Map<String, Object> hsMap = new HashMap<String, Object>();
			hsMap.put("tenantId", SecurityUtil.getCurrentUserTenantId());
			hsMap.put("companyInnerCode", SecurityUtil.getCurrentEmpOrgInnerCode());
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				hsMap.put("channelDistributorId", employee.getChannelDistributor().getId());
			}
			List<String> timeArr = MyTool.getNewlyDateArrayByInt_ZX(-0);// 又改需求：改为今日
			String titleDate = "今日";
			String yesterdayCur = "0";
			if (StringUtils.isNotEmpty(queryTime)) {
				if (queryTime.contains("rN-pE{")) {
					timeArr = new ArrayList<String>();
					timeArr = MyTool.getTimeArrByHtmlParameterString(queryTime);
					yesterdayCur = MyTool.dateReduceOrAddByInt(timeArr.get(0), -1);// 当前时间的昨天，减少一天
					titleDate = "当前";
				}
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("topNum", topNum);
			hsMap.put("controlSQL", "one");
			tBeanEArr = (ArrayList<TableBeanE>) queryDataService.findnvixContentHtmlB(hsMap).get("tBeanEArr");
			timeArr = new ArrayList<String>(); // 更改需求：不做环比了
			timeArr.add(MyTool.getYesterdayBy_yyyyMMdd());// 查询昨天
			if (yesterdayCur.length() > 2) {
				timeArr = new ArrayList<String>();
				timeArr.add(yesterdayCur);
			}
			hsMap.put("timeArr", timeArr);
			hsMap.put("controlSQL", "two");// 查询较昨天的 环比值
			if (MyTool.isNotEmpty(tBeanEArr)) {
				for (int x = 0; x < tBeanEArr.size(); x++) {
					TableBeanE tableBeanE = tBeanEArr.get(x);
					String spName = tableBeanE.getColA();
					Double todayJe = Double.parseDouble(tableBeanE.getColC());
					hsMap.put("spName", "\'" + spName + "\'");
					Double yesterdayJe = (Double) queryDataService.findnvixContentHtmlB(hsMap).get("yesterdayJe");
					String mom = MyTool.getMomOne(todayJe, yesterdayJe);
					tableBeanE.setColD(mom);
					tBeanEArr.set(x, tableBeanE);
				}
			}
			getRequest().setAttribute("titleDate", titleDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sinProductSalesTOP";// 商品(单品)销量排行TOP10 Single product sales
									// ranking TOP10 (sinProductSalesTOP)
	}
	private String startTime;
	private String endTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private ArrayList<TableBeanE> tBeanEArr;
	public ArrayList<TableBeanE> gettBeanEArr() {
		return tBeanEArr;
	}
	public void settBeanEArr(ArrayList<TableBeanE> tBeanEArr) {
		this.tBeanEArr = tBeanEArr;
	}
	private String topNum;
	public String getTopNum() {
		return topNum;
	}
	public void setTopNum(String topNum) {
		this.topNum = topNum;
	}
	private String controlSQL;
	public String getControlSQL() {
		return controlSQL;
	}
	public void setControlSQL(String controlSQL) {
		this.controlSQL = controlSQL;
	}

	public Integer getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(Integer taskNum) {
		this.taskNum = taskNum;
	}

	public List<VixTask> getVixTaskList() {
		return vixTaskList;
	}

	public void setVixTaskList(List<VixTask> vixTaskList) {
		this.vixTaskList = vixTaskList;
	}

	/**
	 * @return the unVixTaskList
	 */
	public List<VixTask> getUnVixTaskList() {
		return unVixTaskList;
	}

	/**
	 * @param unVixTaskList
	 *            the unVixTaskList to set
	 */
	public void setUnVixTaskList(List<VixTask> unVixTaskList) {
		this.unVixTaskList = unVixTaskList;
	}

	public List<PunchCardRecord> getPunchCardList() {
		return punchCardList;
	}

	public void setPunchCardList(List<PunchCardRecord> punchCardList) {
		this.punchCardList = punchCardList;
	}

	public List<Calendars> getCalendarsList() {
		return calendarsList;
	}

	public void setCalendarsList(List<Calendars> calendarsList) {
		this.calendarsList = calendarsList;
	}

	/**
	 * @return the onVixTaskList
	 */
	public List<VixTask> getOnVixTaskList() {
		return onVixTaskList;
	}

	/**
	 * @param onVixTaskList
	 *            the onVixTaskList to set
	 */
	public void setOnVixTaskList(List<VixTask> onVixTaskList) {
		this.onVixTaskList = onVixTaskList;
	}

	/**
	 * @return the endVixTaskList
	 */
	public List<VixTask> getEndVixTaskList() {
		return endVixTaskList;
	}

	/**
	 * @param endVixTaskList
	 *            the endVixTaskList to set
	 */
	public void setEndVixTaskList(List<VixTask> endVixTaskList) {
		this.endVixTaskList = endVixTaskList;
	}

	public List<AlertNotice> getAlertNoticeList() {
		return alertNoticeList;
	}

	public void setAlertNoticeList(List<AlertNotice> alertNoticeList) {
		this.alertNoticeList = alertNoticeList;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	/**
	 * @return the homeTemplateCode
	 */
	public String getHomeTemplateCode() {
		return homeTemplateCode;
	}

	/**
	 * @param homeTemplateCode
	 *            the homeTemplateCode to set
	 */
	public void setHomeTemplateCode(String homeTemplateCode) {
		this.homeTemplateCode = homeTemplateCode;
	}

	/**
	 * @return the homeTemplateDetailList
	 */
	public List<HomeTemplateDetail> getHomeTemplateDetailList() {
		return homeTemplateDetailList;
	}

	/**
	 * @param homeTemplateDetailList
	 *            the homeTemplateDetailList to set
	 */
	public void setHomeTemplateDetailList(List<HomeTemplateDetail> homeTemplateDetailList) {
		this.homeTemplateDetailList = homeTemplateDetailList;
	}

}

package com.vix.nvix.common.base.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.drools.util.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.utils.kit.HttpKit;
import com.vix.crm.business.entity.CrmMember;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.hql.AttendanceHqlProvider;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.common.base.vo.EmpDayCardRecords;
import com.vix.nvix.common.base.vo.EmpMonthCardRecords;
import com.vix.nvix.oa.attendance.entity.AttendanceRuleSet;
import com.vix.nvix.oa.attendance.entity.BasicRule;
import com.vix.nvix.oa.attendance.entity.CalculationRule;
import com.vix.nvix.oa.attendance.entity.DailyStatistics;
import com.vix.nvix.oa.attendance.entity.LeaveRecord;
import com.vix.nvix.oa.attendance.entity.MonthlyStatistics;
import com.vix.nvix.oa.attendance.entity.OverTimeRule;
import com.vix.nvix.oa.attendance.entity.PeriodRule;
import com.vix.nvix.oa.attendance.entity.Scheduling;

/**
 * 
 * com.vix.wechat.department.controller.WechatDepartmentController
 *
 * @author bjitzhang
 *
 * @date 2015年12月29日
 */
@Controller("vixntBaseController")
@Scope("prototype")
public class VixntBaseController implements DealCustomerAccount {
	@Autowired
	public IVixntBaseService vixntBaseService;
	@Resource(name = "attendanceHqlProvider")
	private AttendanceHqlProvider attendanceHqlProvider;
	/**
	 * 初始化基础数据
	 */
	@Autowired
	public InitEntityBaseController initEntityBaseController;

	/**
	 * 获取普通规则 通过人员 获取当前工作时间的对应班次,通过班次获取对应的规则
	 */
	public PeriodRule getPeriodRule(String employeeId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("employeeId", employeeId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet.getPeriodRule();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 基本规则
	public BasicRule getBasicRule(String employeeId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("employeeId", employeeId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet.getBasicRule();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AttendanceRuleSet getAttendanceRuleSet(String employeeId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("employeeId", employeeId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AttendanceRuleSet getAttendanceRuleSetByUnit(String unitId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("unitId", unitId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingByUnitHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public OverTimeRule getOverTimeRule(String employeeId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("employeeId", employeeId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet.getOverTimeRule();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CalculationRule getCalculationRule(String employeeId) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("employeeId", employeeId);
			StringBuilder hql = attendanceHqlProvider.findSchedulingHql(params);
			Scheduling scheduling = vixntBaseService.findObjectByHql(hql.toString(), params);
			if (scheduling != null) {
				AttendanceRuleSet attendanceRuleSet = scheduling.getAttRuleSet();
				if (attendanceRuleSet != null) {
					return attendanceRuleSet.getCalculationRule();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分析数据(基本规则处理)
	 * 
	 * @param dayAndMonth
	 * @param employee
	 * @param periodRule
	 * @throws Exception
	 */
	public void dealData(String dayAndMonth, Employee employee, PeriodRule periodRule, Map<String, Object> p) throws Exception {

		// 上下班都不需要刷卡
		if (periodRule.getWorkOnSwipingCard() != null && periodRule.getWorkOnSwipingCard() != 1 && periodRule.getWorkOffSwipingCard() != null && periodRule.getWorkOffSwipingCard() != 1) {
			dealNoCard(employee, periodRule, dayAndMonth);
		} else {
			if (employee != null && StringUtils.isNotEmpty(employee.getCode())) {
				p.put("userCode", employee.getCode());
			}
			List<EmpDayCardRecords> empDayCardRecordsList = vixntBaseService.getEmpDayCardRecordsList(p);
			if (empDayCardRecordsList != null && empDayCardRecordsList.size() > 0) {
				dealDailyStatistics(dayAndMonth, empDayCardRecordsList, employee);
			} else {
				String empdateCode = "";
				// 没有考勤记录怎么办
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("employeeId", employee.getId());
				//
				// 上班时间
				String workOnTime = jointTimeData(dayAndMonth, periodRule.getWorkOnTime());
				// 下班时间
				String workOffTime = jointTimeData(dayAndMonth, periodRule.getWorkOffTime());
				params.put("startTime", workOnTime);
				params.put("endTime", workOffTime);
				// 需要增加日期条件
				StringBuilder hql = attendanceHqlProvider.findLeaveRecordHql(params);
				LeaveRecord leaveRecord = vixntBaseService.findObjectByHql(hql.toString(), params);
				empdateCode = employee.getCode() + dayAndMonth;
				// 创建考勤结果明细
				DailyStatistics dailyStatistics = vixntBaseService.findEntityByAttribute(DailyStatistics.class, "empdateCode", empdateCode);
				// 有请假记录
				if (dailyStatistics == null) {
					if (leaveRecord != null) {
						dailyStatistics = new DailyStatistics();
						dailyStatistics.setEmpdateCode(empdateCode);
						dailyStatistics.setDayAndMonth(dayAndMonth);
						dailyStatistics.setRecordNum(employee.getCode());
						dailyStatistics.setEmp(employee);
						dailyStatistics.setEmployeeName(employee.getName());
						dailyStatistics.setStartWorkStatus("2");
						dailyStatistics.setEndWorkStatus("2");
						dailyStatistics.setAttendanceStatus("2");
						dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
						dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
						if (StringUtils.isNotEmpty(leaveRecord.getLeaveType())) {
							dailyStatistics.setVacationType(leaveRecord.getLeaveType());
						}
						dailyStatistics = vixntBaseService.merge(dailyStatistics);
					} else {
						// 没有请假记录,可视为旷工
						dailyStatistics = new DailyStatistics();
						dailyStatistics.setEmpdateCode(empdateCode);
						dailyStatistics.setDayAndMonth(dayAndMonth);
						dailyStatistics.setRecordNum(employee.getCode());
						dailyStatistics.setEmp(employee);
						dailyStatistics.setEmployeeName(employee.getName());
						dailyStatistics.setStartWorkStatus("2");
						dailyStatistics.setEndWorkStatus("2");
						dailyStatistics.setAttendanceStatus("1");
						dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
						dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
						dailyStatistics.setAccumulativeWorkHours(0D);
						dailyStatistics.setDiscountDay(0D);
						dailyStatistics = vixntBaseService.merge(dailyStatistics);
						System.out.println("旷工");
					}
				}
			}
		}
	}

	// 按照时段规则处理
	public void dealDailyStatistics(String dayAndMonth, List<EmpDayCardRecords> empDayCardRecordsList, Employee employee) throws Exception {
		for (EmpDayCardRecords empDayCardRecords : empDayCardRecordsList) {
			if (empDayCardRecords != null) {
				// 通过考勤编码获取员工信息
				// 刷卡时段设置
				PeriodRule periodRule = getPeriodRule(employee.getId());
				// 加班规则
				OverTimeRule overTimeRule = getOverTimeRule(employee.getId());
				BasicRule basicRule = getBasicRule(employee.getId());
				if (periodRule != null) {
					// 考勤记录唯一标示码
					String empdateCode = empDayCardRecords.getUserCode() + empDayCardRecords.getDatetemp();
					// 考勤日期
					String datetemp = dayAndMonth;
					if (StringUtils.isEmpty(datetemp))
						datetemp = empDayCardRecords.getDatetemp();
					// 签到时间
					String startcard = empDayCardRecords.getStartcard();
					// 签退时间
					String endcard = empDayCardRecords.getEndcard();
					// 上班时间
					String startWorkTime = periodRule.getWorkOnTime();
					// 下班时间
					String endWorkTime = periodRule.getWorkOffTime();

					// 创建考勤结果明细
					DailyStatistics dailyStatistics = vixntBaseService.findEntityByAttribute(DailyStatistics.class, "empdateCode", empdateCode);
					if (dailyStatistics == null) {
						//
						dailyStatistics = initDailyStatistics(employee, empDayCardRecords, empdateCode, datetemp);
						// 刷卡2次以上
						if (StringUtils.isNotEmpty(startcard) && StringUtils.isNotEmpty(endcard)) {
							// 处理上下班都需要刷卡的情况
							if (periodRule.getWorkOnSwipingCard() != null && periodRule.getWorkOnSwipingCard() == 1 && periodRule.getWorkOffSwipingCard() != null && periodRule.getWorkOffSwipingCard() == 1) {
								if (basicRule.getInterval() != null && basicRule.getInterval() < countMinuteTime(startcard, endcard)) {
									// 处理签到
									dealSignedOn(datetemp, periodRule, empDayCardRecords, dailyStatistics, overTimeRule);
									// 处理签退
									dealSignOut(datetemp, periodRule, empDayCardRecords, dailyStatistics, overTimeRule);
									// 上下班都正常打卡
									dailyStatistics = dealAllNormalCard(periodRule, startWorkTime, endWorkTime, dailyStatistics, basicRule);
									continue;
								}
								if (basicRule.getInterval() != null && 0 < countMinuteTime(startcard, endcard) && countMinuteTime(startcard, endcard) < basicRule.getInterval()) {
									// 刷卡时间小于设定时间的情况
									dailyStatistics = dealCardShort(startWorkTime, endWorkTime, dailyStatistics);
									continue;
								}
							}
						} else {
							// 处理单次刷卡的情况
							dealOneCard(periodRule, startcard, dailyStatistics);
						}
					}
				}
			}
		}
	}

	/**
	 * 初始化 DailyStatistics
	 * 
	 * @param employee
	 * @param empDayCardRecords
	 * @param empdateCode
	 * @param datetemp
	 * @return
	 */
	private DailyStatistics initDailyStatistics(Employee employee, EmpDayCardRecords empDayCardRecords, String empdateCode, String datetemp) {
		DailyStatistics dailyStatistics = new DailyStatistics();
		dailyStatistics.setEmpdateCode(empdateCode);
		dailyStatistics.setDayAndMonth(datetemp);
		dailyStatistics.setRecordNum(empDayCardRecords.getUserCode());
		dailyStatistics.setEmp(employee);
		dailyStatistics.setEmployeeName(employee.getName());
		return dailyStatistics;
	}

	/**
	 * 处理2次刷卡时间小于设定时间的情况
	 * 
	 * @param startWorkTime
	 * @param endWorkTime
	 * @param dailyStatistics
	 * @return
	 * @throws Exception
	 */
	private DailyStatistics dealCardShort(String startWorkTime, String endWorkTime, DailyStatistics dailyStatistics) throws Exception {
		// 刷卡间隔小于刷卡间隔时长设定值
		dailyStatistics.setAttendanceStatus("1");
		dailyStatistics.setAccumulativeWorkHours(0D);
		dailyStatistics.setDiscountDay(0D);
		dailyStatistics.setStartWorkTime(startWorkTime);
		dailyStatistics.setEndWorkTime(endWorkTime);
		dailyStatistics.setStartWorkStatus("2");
		dailyStatistics.setEndWorkStatus("2");
		// 上班时间
		String workOnTime = jointTimeData(dailyStatistics.getDayAndMonth(), startWorkTime);
		dailyStatistics.setStartcard(workOnTime);
		// 下班时间
		String workOffTime = jointTimeData(dailyStatistics.getDayAndMonth(), endWorkTime);
		dailyStatistics.setEndcard(workOffTime);
		dailyStatistics = vixntBaseService.merge(dailyStatistics);
		return dailyStatistics;
	}

	/**
	 * 上下班都正常打卡的情况
	 * 
	 * @param periodRule
	 * @param startWorkTime
	 * @param endWorkTime
	 * @param dailyStatistics
	 * @return
	 * @throws Exception
	 */
	private DailyStatistics dealAllNormalCard(PeriodRule periodRule, String startWorkTime, String endWorkTime, DailyStatistics dailyStatistics, BasicRule basicRule) throws Exception {
		dailyStatistics.setAccumulativeWorkHours(periodRule.getManhour());
		dailyStatistics.setDiscountDay(periodRule.getDiscountDay());
		dailyStatistics.setStartWorkTime(startWorkTime);
		dailyStatistics.setEndWorkTime(endWorkTime);
		if (basicRule != null) {
			// 工时中扣除迟到时间
			if (basicRule.getDeductionLateTime() == 1 && dailyStatistics != null && dailyStatistics.getAccumulativeWorkHours() != null && dailyStatistics.getLateTime() != null) {
				dailyStatistics.setAccumulativeWorkHours(dailyStatistics.getAccumulativeWorkHours() - dailyStatistics.getLateTime());
			}
			// 工时中扣除早退时间
			if (basicRule.getDeductionEarlyTime() == 1 && dailyStatistics != null && dailyStatistics.getAccumulativeWorkHours() != null && dailyStatistics.getEarlyTime() != null) {
				dailyStatistics.setAccumulativeWorkHours(dailyStatistics.getAccumulativeWorkHours() - dailyStatistics.getEarlyTime());
			}
			// 迟到早退不计入旷工
			if (basicRule.getDoNotIncludedAbsenteeism() != 1) {
				dailyStatistics.setAttendanceStatus("1");
			} else {
				dailyStatistics.setAttendanceStatus("0");
			}
		} else {
			dailyStatistics.setAttendanceStatus("0");
		}
		dailyStatistics = vixntBaseService.merge(dailyStatistics);
		return dailyStatistics;
	}

	/**
	 * 上下班都不需要刷卡的情况
	 * 
	 * @param periodRule
	 * @param startWorkTime
	 * @param endWorkTime
	 * @param dailyStatistics
	 * @return
	 * @throws Exception
	 */
	private void dealNoCard(Employee employee, PeriodRule periodRule, String dayAndMonth) throws Exception {
		// 创建考勤结果明细
		String empdateCode = employee.getCode() + dayAndMonth;
		DailyStatistics dailyStatistics = vixntBaseService.findEntityByAttribute(DailyStatistics.class, "empdateCode", empdateCode);
		if (dailyStatistics == null) {
			dailyStatistics = new DailyStatistics();
			// 考勤记录唯一标示码
			dailyStatistics.setEmpdateCode(empdateCode);
			dailyStatistics.setDayAndMonth(dayAndMonth);
			dailyStatistics.setRecordNum(employee.getCode());
			dailyStatistics.setEmp(employee);
			dailyStatistics.setEmployeeName(employee.getName());
			// 处理签到签退都不刷卡的情况
			dailyStatistics.setAttendanceStatus("0");
			dailyStatistics.setAccumulativeWorkHours(periodRule.getManhour());
			dailyStatistics.setDiscountDay(periodRule.getDiscountDay());
			dailyStatistics.setStartWorkStatus("0");
			dailyStatistics.setEndWorkStatus("0");
			// 上班时间
			String workOnTime = jointTimeData(dayAndMonth, periodRule.getWorkOnTime());
			dailyStatistics.setStartcard(workOnTime);
			// 下班时间
			String workOffTime = jointTimeData(dayAndMonth, periodRule.getWorkOffTime());
			dailyStatistics.setEndcard(workOffTime);
			dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
			dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
			vixntBaseService.merge(dailyStatistics);
		}
	}

	/**
	 * 处理单次刷卡
	 * 
	 * @param periodRule
	 * @param startcard
	 * @param endcard
	 * @param dailyStatistics
	 * @throws Exception
	 */
	private void dealOneCard(PeriodRule periodRule, String startcard, DailyStatistics dailyStatistics) throws Exception {
		String startcardstr = null;
		if (StringUtils.isNotEmpty(startcard))
			startcardstr = startcard.substring(11, 19);
		// 签到正常,下班不需要打卡的情况
		if (isInTime(periodRule.getEarliestTime() + "-" + periodRule.getLateAbsencesTime(), startcardstr) && periodRule.getWorkOffSwipingCard() != null && periodRule.getWorkOffSwipingCard() != 1) {
			// 签到成功
			dailyStatistics.setStartWorkStatus("0");
			dailyStatistics.setEndWorkStatus("0");
			dailyStatistics.setStartcard(startcard);
			// 下班时间
			String workOffTime = jointTimeData(dailyStatistics.getDayAndMonth(), periodRule.getWorkOffTime());
			dailyStatistics.setEndcard(workOffTime);
			dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
			dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
			dailyStatistics.setDiscountDay(periodRule.getDiscountDay());
			dailyStatistics.setAccumulativeWorkHours(periodRule.getManhour());
			dailyStatistics.setAttendanceStatus("0");
			dailyStatistics = vixntBaseService.merge(dailyStatistics);
			return;
		}
		// 签退正常,上班免刷卡
		if (isInTime(periodRule.getEarlyAbsenceTime() + "-" + periodRule.getLastWorkOffTime(), startcardstr) && periodRule.getWorkOnSwipingCard() != null && periodRule.getWorkOnSwipingCard() != 1) {
			dailyStatistics.setStartWorkStatus("0");
			dailyStatistics.setEndWorkStatus("0");
			String workOnTime = jointTimeData(dailyStatistics.getDayAndMonth(), periodRule.getWorkOnTime());
			dailyStatistics.setStartcard(workOnTime);
			dailyStatistics.setEndcard(startcard);
			dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
			dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
			dailyStatistics.setDiscountDay(periodRule.getDiscountDay());
			dailyStatistics.setAccumulativeWorkHours(periodRule.getManhour());
			dailyStatistics.setAttendanceStatus("0");
			dailyStatistics = vixntBaseService.merge(dailyStatistics);
			return;
		}
		// 单次刷卡 算旷工
		if (periodRule.getIsAbsenteeism() != null && periodRule.getIsAbsenteeism() == 1) {
			dailyStatistics.setStartWorkStatus("2");
			dailyStatistics.setEndWorkStatus("2");
			dailyStatistics.setAccumulativeWorkHours(0D);
			dailyStatistics.setDiscountDay(0D);
			dailyStatistics.setAttendanceStatus("1");
			dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
			dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
			dailyStatistics = vixntBaseService.merge(dailyStatistics);
			return;
		}
		// 刷卡时间不在签到签退范围内
		if (!isInTime(periodRule.getEarliestTime() + "-" + periodRule.getLateAbsencesTime(), startcardstr) && !isInTime(periodRule.getEarlyAbsenceTime() + "-" + periodRule.getLastWorkOffTime(), startcardstr)) {
			dailyStatistics.setStartcard(startcard);
			dailyStatistics.setStartWorkStatus("2");
			dailyStatistics.setEndWorkStatus("2");
			dailyStatistics.setAccumulativeWorkHours(0D);
			dailyStatistics.setDiscountDay(0D);
			dailyStatistics.setAttendanceStatus("3");
			dailyStatistics.setStartWorkTime(periodRule.getWorkOnTime());
			dailyStatistics.setEndWorkTime(periodRule.getWorkOffTime());
			dailyStatistics = vixntBaseService.merge(dailyStatistics);
			return;
		}
	}

	/**
	 * 处理上班前加班
	 * 
	 * @param datetemp
	 * @param periodRule
	 * @param empDayCardRecords
	 * @param dailyStatistics
	 * @throws ParseException
	 */
	public void dealStartWorkOverTime(String datetemp, PeriodRule periodRule, EmpDayCardRecords empDayCardRecords, DailyStatistics dailyStatistics, OverTimeRule overTimeRule) throws ParseException {
		// 上班时间
		String workOnTime = jointTimeData(datetemp, periodRule.getWorkOnTime());
		// 签到时间
		String startcard = empDayCardRecords.getStartcard();
		Integer oTHs = overTimeRule.getoTHs();
		if (countMinuteTime(startcard, workOnTime) >= oTHs) {
			if (dailyStatistics.getAccumulativeOverTimeHours() != null) {
				dailyStatistics.setAccumulativeOverTimeHours(dailyStatistics.getAccumulativeOverTimeHours() + countMinuteTime(startcard, workOnTime));
			} else {
				dailyStatistics.setAccumulativeOverTimeHours(countMinuteTime(startcard, workOnTime));
			}
		}
	}

	// 处理下班后加班
	public void dealEndWorkOverTime(String datetemp, PeriodRule periodRule, EmpDayCardRecords empDayCardRecords, DailyStatistics dailyStatistics, OverTimeRule overTimeRule) throws ParseException {
		String endcard = empDayCardRecords.getEndcard();
		// 下班时间
		String workOffTime = jointTimeData(datetemp, periodRule.getWorkOffTime());
		Integer offDutyHours = overTimeRule.getOffDutyHours();
		if (countMinuteTime(workOffTime, endcard) >= offDutyHours) {
			if (dailyStatistics.getAccumulativeOverTimeHours() != null) {
				dailyStatistics.setAccumulativeOverTimeHours(dailyStatistics.getAccumulativeOverTimeHours() + countMinuteTime(workOffTime, endcard));
			} else {
				dailyStatistics.setAccumulativeOverTimeHours(countMinuteTime(workOffTime, endcard));
			}
		}
	}

	// 处理签到
	public void dealSignedOn(String datetemp, PeriodRule periodRule, EmpDayCardRecords empDayCardRecords, DailyStatistics dailyStatistics, OverTimeRule overTimeRule) throws ParseException {
		// 上班时间
		String workOnTime = jointTimeData(datetemp, periodRule.getWorkOnTime());
		// 签到时间
		String startcard = empDayCardRecords.getStartcard();
		// 容许迟到的时间
		Integer allowTheLateTime = periodRule.getAllowTheLateTime();
		// 迟到记缺勤时间
		String lateAbsencesTime = jointTimeData(datetemp, periodRule.getLateAbsencesTime());
		// 最早上班考勤时间
		// String earliestTime = jointTimeData(datetemp,
		// periodRule.getEarliestTime());
		// 打卡时间在上班时间之后
		if (countMinuteTime(workOnTime, startcard) > 0) {
			if (countMinuteTime(workOnTime, startcard) <= allowTheLateTime) {
				// 迟到的时间在容许迟到的时间范围内,不计入迟到范围.
				dailyStatistics.setStartWorkStatus("0");
				dailyStatistics.setLateTime(countMinuteTime(workOnTime, startcard));
			} else if (countMinuteTime(workOnTime, startcard) > allowTheLateTime) {
				if (countMinuteTime(startcard, lateAbsencesTime) >= 0) {
					// 迟到
					dailyStatistics.setStartWorkStatus("1");
					dailyStatistics.setLateTime(countMinuteTime(workOnTime, startcard));
				} else {
					dailyStatistics.setStartWorkStatus("1");
				}
			}
		} else {
			// 正常
			dailyStatistics.setStartWorkStatus("0");
		}
		// 判断是否开启上班前加班
		if (periodRule.getAcoo() != null && periodRule.getAcoo() == 1) {
			dealStartWorkOverTime(datetemp, periodRule, empDayCardRecords, dailyStatistics, overTimeRule);
		}
		dailyStatistics.setStartcard(startcard);
	}

	// 处理签退
	public void dealSignOut(String datetemp, PeriodRule periodRule, EmpDayCardRecords empDayCardRecords, DailyStatistics dailyStatistics, OverTimeRule overTimeRule) throws ParseException {
		String endcard = empDayCardRecords.getEndcard();
		// 下班时间
		String workOffTime = jointTimeData(datetemp, periodRule.getWorkOffTime());
		// 早退记缺勤时间
		String earlyAbsenceTime = jointTimeData(datetemp, periodRule.getEarlyAbsenceTime());
		// 容许早退的时间
		Integer allowedEarlyTime = periodRule.getAllowedEarlyTime();
		// 最晚下班时间
		// String lastWorkOffTime = jointTimeData(datetemp,
		// periodRule.getLastWorkOffTime());
		// 下班之前签到
		if (countMinuteTime(endcard, workOffTime) > 0) {
			if (countMinuteTime(endcard, workOffTime) <= allowedEarlyTime) {
				// 早退了,但没超过容许早退的最大时间");
				dailyStatistics.setEndWorkStatus("0");
			} else if (countMinuteTime(endcard, workOffTime) > allowedEarlyTime) {
				if (countMinuteTime(endcard, earlyAbsenceTime) > 0) {
					dailyStatistics.setEndWorkStatus("1");
				} else {
					// 早退了
					dailyStatistics.setEndWorkStatus("1");
					dailyStatistics.setEarlyTime(countMinuteTime(endcard, workOffTime));
				}
			}
		} else {
			// 正常签退
			dailyStatistics.setEndWorkStatus("0");
		}
		// 处理下班后加班
		if (periodRule.getAcwo() != null && periodRule.getAcwo() == 1) {
			dealEndWorkOverTime(datetemp, periodRule, empDayCardRecords, dailyStatistics, overTimeRule);
		}
		dailyStatistics.setEndcard(endcard);
	}

	/**
	 * 考勤汇总
	 */
	public void dealEmpMonthCardRecords(Map<String, Object> p) {
		try {
			List<EmpMonthCardRecords> empMonthCardRecordsList = vixntBaseService.getEmpMonthCardRecordsList(p);
			if (empMonthCardRecordsList != null && empMonthCardRecordsList.size() > 0) {
				for (EmpMonthCardRecords empMonthCardRecords : empMonthCardRecordsList) {
					if (empMonthCardRecords != null) {
						String empdateCode = empMonthCardRecords.getUserCode() + empMonthCardRecords.getDatetemp();
						MonthlyStatistics monthlyStatistics = vixntBaseService.findEntityByAttribute(MonthlyStatistics.class, "empdateCode", empdateCode);
						if (monthlyStatistics == null) {
							monthlyStatistics = new MonthlyStatistics();
							// 通过考勤编码获取员工信息
							Employee employee = vixntBaseService.findEntityByAttribute(Employee.class, "code", empMonthCardRecords.getUserCode());
							if (employee != null) {
								monthlyStatistics.setEmp(employee);
								monthlyStatistics.setEmpName(employee.getName());
								// 获取班次
								AttendanceRuleSet attendanceRuleSet = null;
								// 先获取部门上绑定的班次
								if (employee.getOrganizationUnit() != null && StringUtils.isNotEmpty(employee.getOrganizationUnit().getId())) {
									attendanceRuleSet = getAttendanceRuleSetByUnit(employee.getOrganizationUnit().getId());
								}
								// 如果部门上未绑定,获取人员上边绑定班次
								if (attendanceRuleSet == null) {
									attendanceRuleSet = getAttendanceRuleSet(employee.getId());
								}
								if (attendanceRuleSet != null && attendanceRuleSet.getExerciseDate() != null) {
									Double workdays = getAllTheDateOftheMonthWork(attendanceRuleSet.getExerciseDate());
									monthlyStatistics.setWorkDays(workdays);
								}
							}
							monthlyStatistics.setRecordNum(empMonthCardRecords.getUserCode());
							monthlyStatistics.setEmpdateCode(empdateCode);
							monthlyStatistics.setCardNum(empMonthCardRecords.getCardNum());
							monthlyStatistics.setDayAndMonth(empMonthCardRecords.getDatetemp());
							monthlyStatistics.setAccumulativeWorkHours(empMonthCardRecords.getAccumulativeWorkHours());
							monthlyStatistics.setAccumulativeLeaveHours(empMonthCardRecords.getAccumulativeLeaveHours());
							monthlyStatistics.setAccumulativeOverTimeHours(empMonthCardRecords.getAccumulativeOverTimeHours());
							monthlyStatistics.setAccumulativeEvectionHours(empMonthCardRecords.getAccumulativeEvectionHours());
							monthlyStatistics.setLateTimes(empMonthCardRecords.getLateTime());
							monthlyStatistics.setEarlyTimes(empMonthCardRecords.getEarlyTime());
							monthlyStatistics.setAffairAbsence(empMonthCardRecords.getAffairAbsence());
							monthlyStatistics.setSickLeave(empMonthCardRecords.getSickLeave());
							monthlyStatistics.setAbsenteeismNum(empMonthCardRecords.getAbsenteeismNum());
							monthlyStatistics = vixntBaseService.merge(monthlyStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算时间差(分钟)
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public Long countMinuteTime(String begin, String end) {
		Long total_minute = 0L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date begin_date = df.parse(begin);
			Date end_date = df.parse(end);
			total_minute = (end_date.getTime() - begin_date.getTime());
			// long day = total_minute / (24 * 60 * 60 * 1000);
			// long hour = total_minute / (60 * 60 * 1000);
			Long min = total_minute / (60 * 1000);
			return min;
		} catch (ParseException e) {
			System.out.println("传入的时间格式不符合规定");
		}
		return total_minute;
	}

	// 计算实际上班天数
	private static Double getAllTheDateOftheMonthWork(String exerciseDate) {
		Double workdays = 0D;
		List<Date> dateList = getAllTheDateOftheMonth(getLastMonthDay(new Date()));
		if (dateList != null && dateList.size() > 0) {
			for (Date d : dateList) {
				Integer exerciseDateDay = getWeek(d);
				if (exerciseDate.contains(String.valueOf(exerciseDateDay))) {
					workdays++;
				}
			}
		}
		return workdays;
	}

	private static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	public static Integer getWeek(Date date) {
		// String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}

	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00) 全闭合,,如[10:00-20:00]
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();

			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00:00")) {
				args[1] = "24:00:00";
			}
			if (end < start) {
				if (now > end && now < start) {
					return false;
				} else {
					return true;
				}
			} else {
				if (now >= start && now <= end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}

	}

	public static Date getLastMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date strDateTo = calendar.getTime();
		return strDateTo;
	}

	public static void main(String args[]) {
		System.out.println(isInTime("08:30-18:30", "08:30"));
		System.out.println(getAllTheDateOftheMonthWork("1,2,3,4,5"));
	}

	// 拼接时间字符串
	public String jointTimeData(String datetemp, String hourandminutestr) {
		return datetemp + " " + hourandminutestr;
	}

	@Override
	public CrmMember saveOrUpdateCrmMember(CustomerAccount customerAccount, String channelDistributorId) {
		CrmMember crmMember = null;

		try {
			if (customerAccount != null) {
				crmMember = vixntBaseService.findEntityByAttribute(CrmMember.class, "customerAccount.id", customerAccount.getId());
				if (crmMember != null) {

				} else {
					crmMember = new CrmMember();
					crmMember.setLastTradeTime(customerAccount.getUpdateTime());
					crmMember.setFirstTradeTime(customerAccount.getCreateTime());
					crmMember.setCustomerAccount(customerAccount);
					crmMember.setChannelDistributorId(channelDistributorId);
					initEntityBaseController.initEntityBaseAttribute(crmMember);
					crmMember = vixntBaseService.merge(crmMember);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return crmMember;
	}

	@Override
	public CrmMember updateCrmMember(CrmMember crmMember, RequireGoodsOrder requireGoodsOrder) throws Exception {
		if (crmMember != null && requireGoodsOrder != null) {
			if (crmMember.getTradeCount() != null) {
				crmMember.setTradeCount(crmMember.getTradeCount() + 1);
			} else {
				crmMember.setTradeCount(1L);
			}
			if (requireGoodsOrder.getAmount() != null) {
				if (crmMember.getTradeAmount() != null) {
					crmMember.setTradeAmount(crmMember.getTradeAmount() + requireGoodsOrder.getAmount());
				} else {
					crmMember.setTradeAmount(requireGoodsOrder.getAmount());
				}
			}
			if (crmMember.getItemNum() != null) {
				crmMember.setItemNum(crmMember.getItemNum() + requireGoodsOrder.getNum());
			} else {
				crmMember.setItemNum(requireGoodsOrder.getNum());
			}
			crmMember.setLastTradeTime(requireGoodsOrder.getCreateTime());
			crmMember = vixntBaseService.merge(crmMember);
		}
		return crmMember;
	}

	/**
	 * 调用POS接口
	 * 
	 * @param json
	 * @return
	 */
	public String postToPos(String url, String json, String userAccount, String password) {
		Map<String, String> map = new HashMap<String, String>();
		String auth = userAccount + ":" + password;
		String authHeader = "Basic " + Base64.encodeBase64String(auth.getBytes()).replaceAll("[\\s*\t\n\r]", "");
		map.put("Authorization", authHeader);
		String resp = HttpKit.postJson(url, null, json, map);
		return resp;
	}
}

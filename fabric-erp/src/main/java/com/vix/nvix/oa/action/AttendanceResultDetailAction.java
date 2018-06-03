/**
 * 
 */
package com.vix.nvix.oa.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.controller.VixntBaseController;
import com.vix.nvix.oa.attendance.entity.AttendanceRuleSet;
import com.vix.nvix.oa.attendance.entity.DailyStatistics;
import com.vix.nvix.oa.attendance.entity.PeriodRule;

/**
 * 
 * @类全名 com.vix.nvix.oa.action.AttendanceResultDetailAction
 *
 * @author zhanghaibing
 *
 * @date 2016年7月15日
 */
@Controller
@Scope("prototype")
public class AttendanceResultDetailAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource(name = "vixntBaseController")
	private VixntBaseController vixntBaseController;
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 只显示昨天的数据
	public void goDailyStatisticsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 显示昨天的考勤数据
			Date date = getYesterDay(new Date());
			if (date != null) {
				// params.put("dayAndMonth," + SearchCondition.EQUAL,
				// df.format(date));
			}
			String dayAndMonth = getRequestParameter("dayAndMonth");
			if (StringUtils.isNotEmpty(dayAndMonth)) {
				params.put("dayAndMonth," + SearchCondition.EQUAL, dayAndMonth);
			}
			Employee emp = getEmployee();
			if (null != emp) {
				params.put("recordNum," + SearchCondition.EQUAL, emp.getCode());
			}
			String empName = getDecodeRequestParameter("empName");
			if (StringUtils.isNotEmpty(empName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, empName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, DailyStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 处理考勤
	public void dealAttendance() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			List<Employee> employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					if (employee != null) {
						// 获取班次
						AttendanceRuleSet attendanceRuleSet = null;
						// 先获取部门上绑定的班次
						if (employee.getOrganizationUnit() != null && StringUtils.isNotEmpty(employee.getOrganizationUnit().getId())) {
							attendanceRuleSet = vixntBaseController.getAttendanceRuleSetByUnit(employee.getOrganizationUnit().getId());
						}
						// 如果部门上未绑定,获取人员上边绑定班次
						if (attendanceRuleSet == null) {
							attendanceRuleSet = vixntBaseController.getAttendanceRuleSet(employee.getId());
						}
						if (attendanceRuleSet != null) {
							String exerciseDate = attendanceRuleSet.getExerciseDate();
							// 刷卡时段设置
							PeriodRule periodRule = attendanceRuleSet.getPeriodRule();
							Map<String, Object> p = new HashMap<String, Object>();
							List<Date> dateList = getAllTheDateOftheMonth(getLastMonthDay(new Date()));
							if (dateList != null && dateList.size() > 0) {
								for (Date d : dateList) {
									Integer exerciseDateDay = getWeek(d);
									String dayAndMonth = df.format(d);
									if (exerciseDate.contains(String.valueOf(exerciseDateDay))) {
										String workOnTime = jointTimeData(dayAndMonth, periodRule.getEarliestTime());
										String workOffTime = jointTimeData(dayAndMonth, periodRule.getLastWorkOffTime());
										if (sdf.parse(workOffTime).getTime() > sdf.parse(workOnTime).getTime()) {
											// 最早上班考勤时间
											if (workOnTime != null) {
												p.put("starttime", workOnTime);
											} else {
												p.put("starttime", dayAndMonth + " " + "00:00:00");
											}
											// 最晚下班时间
											if (workOffTime != null) {
												p.put("endtime", workOffTime);
											} else {
												p.put("endtime", dayAndMonth + " " + "23:59:59");
											}
										}
										Date date = new Date();
										if (date.getTime() > d.getTime()) {
											vixntBaseController.dealData(df.format(d), employee, periodRule, p);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(getLastMonthDay(new Date()));
	}
}
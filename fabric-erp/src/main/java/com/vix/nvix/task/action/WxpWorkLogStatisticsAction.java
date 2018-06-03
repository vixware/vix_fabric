package com.vix.nvix.task.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.AllWorkLogStatistics;
import com.vix.nvix.oa.attendance.entity.WorkLogStatistics;
import com.vix.nvix.oa.bo.AllWorkLogStatisticsBo;
import com.vix.nvix.oa.bo.WorkLogStatisticsBo;

/**
 * 日报统计
 * 
 * @类全名 com.vix.nvix.task.action.WxpWorkLogStatisticsAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月11日
 */
@Controller
@Scope("prototype")
public class WxpWorkLogStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/** 获取列表数据 */
	public void goWorkLogStatisticsList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee emp = getEmployee();
			if (emp != null) {
				params.put("empId," + SearchCondition.EQUAL, emp.getId());
			}
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("datetemp");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, AllWorkLogStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 跳转到日报统计页面
	public String goAllStatisticsList() {
		return "goAllStatisticsList";
	}

	public void dealWorkLogStatistics() {
		try {

			Map<String, Object> p = new HashMap<String, Object>();
			Employee emp = getEmployee();
			if (emp != null) {
				p.put("TENANTID", emp.getTenantId());
			}
			List<WorkLogStatisticsBo> workLogStatisticsBoList = vixntBaseService.getWorkLogStatisticsBoList(p);
			// 处理员工
			Map<String, Object> params = new HashMap<String, Object>();
			List<Employee> employeeList = vixntBaseService.findAllByConditions(Employee.class, params);
			for (Employee employee : employeeList) {
				if (employee != null) {
					List<Date> dateList = getAllTheDateOftheMonth(getLastMonthDay(new Date()));
					if (dateList != null && dateList.size() > 0) {
						// 处理日期
						for (Date d : dateList) {
							Integer exerciseDateDay = getWeek(d);
							if ("1,2,3,4,5".contains(String.valueOf(exerciseDateDay))) {
								WorkLogStatistics workLogStatistics = vixntBaseService.findEntityByAttribute(WorkLogStatistics.class, "empcodeAndDate", employee.getId() + df.format(d));
								if (workLogStatistics == null) {
									if (workLogStatisticsBoList != null && workLogStatisticsBoList.size() > 0) {
										for (WorkLogStatisticsBo workLogStatisticsBo : workLogStatisticsBoList) {
											if (workLogStatisticsBo.getEmpId().equals(employee.getId())) {
												WorkLogStatistics w = vixntBaseService.findEntityByAttribute(WorkLogStatistics.class, "empcodeAndDate", workLogStatisticsBo.getEmpId() + workLogStatisticsBo.getDatetemp());
												if (w == null) {
													workLogStatistics = new WorkLogStatistics();
													workLogStatistics.setEmpId(workLogStatisticsBo.getEmpId());
													workLogStatistics.setEmpName(workLogStatisticsBo.getEmpName());
													workLogStatistics.setDatetemp(workLogStatisticsBo.getDatetemp());
													workLogStatistics.setEmpcodeAndDate(workLogStatisticsBo.getEmpId() + workLogStatisticsBo.getDatetemp());
													workLogStatistics.setIsCreate("1");
													workLogStatistics.setWeekDate(getWeekOfDate(df.parse(workLogStatisticsBo.getDatetemp())));
													initEntityBaseController.initEntityBaseAttribute(workLogStatistics);
													workLogStatistics = vixntBaseService.merge(workLogStatistics);
												}
											}
										}
									}
									workLogStatistics = vixntBaseService.findEntityByAttribute(WorkLogStatistics.class, "empcodeAndDate", employee.getId() + df.format(d));
									if (workLogStatistics == null) {
										workLogStatistics = new WorkLogStatistics();
										workLogStatistics.setEmpId(employee.getId());
										workLogStatistics.setEmpName(employee.getName());
										workLogStatistics.setDatetemp(df.format(d));
										workLogStatistics.setEmpcodeAndDate(employee.getId() + df.format(d));
										workLogStatistics.setIsCreate("0");
										workLogStatistics.setWeekDate(getWeekOfDate(d));
										initEntityBaseController.initEntityBaseAttribute(workLogStatistics);
										workLogStatistics = vixntBaseService.merge(workLogStatistics);
									}
								}
							}
						}
					}
				}
			}

			List<AllWorkLogStatisticsBo> allWorkLogStatisticsBoList = vixntBaseService.getAllWorkLogStatisticsBoList(null);
			if (allWorkLogStatisticsBoList != null && allWorkLogStatisticsBoList.size() > 0) {
				for (AllWorkLogStatisticsBo allWorkLogStatisticsBo : allWorkLogStatisticsBoList) {
					if (allWorkLogStatisticsBo != null) {
						AllWorkLogStatistics allWorkLogStatistics = vixntBaseService.findEntityByAttribute(AllWorkLogStatistics.class, "empcodeAndDate", allWorkLogStatisticsBo.getEmpcodeAndDate());
						if (allWorkLogStatistics == null) {
							allWorkLogStatistics = new AllWorkLogStatistics();
							allWorkLogStatistics.setEmpcodeAndDate(allWorkLogStatisticsBo.getEmpcodeAndDate());
							allWorkLogStatistics.setEmpId(allWorkLogStatisticsBo.getEmpId());
							allWorkLogStatistics.setEmpName(allWorkLogStatisticsBo.getEmpName());
							allWorkLogStatistics.setDatetemp(allWorkLogStatisticsBo.getDatetemp());
							allWorkLogStatistics.setNum(allWorkLogStatisticsBo.getNum());
							allWorkLogStatistics = vixntBaseService.merge(allWorkLogStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

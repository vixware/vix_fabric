package com.vix.nvix.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.TaskStatistics;
import com.vix.nvix.oa.bo.TaskStatisticsBo;

/**
 * 任务统计
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class VixntProjectStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public void goTaskStatisticsList() {
		try {
			dealTaskStatistics(null);
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			Employee emp = getEmployee();
			if (emp != null) {
				params.put("empId," + SearchCondition.EQUAL, emp.getId());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaskStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dealTaskStatistics(Map<String, Object> p) {
		try {
			List<TaskStatisticsBo> taskStatisticsBoList = vixntBaseService.getTaskStatisticsBoList(p,null);
			if (taskStatisticsBoList != null && taskStatisticsBoList.size() > 0) {
				for (TaskStatisticsBo taskStatisticsBo : taskStatisticsBoList) {
					if (taskStatisticsBo != null) {
						TaskStatistics taskStatistics = vixntBaseService.findEntityByAttribute(TaskStatistics.class, "empId", taskStatisticsBo.getEmpId());
						if (taskStatistics == null) {
							taskStatistics = new TaskStatistics();
							taskStatistics.setEmpId(taskStatisticsBo.getEmpId());
							taskStatistics.setEmpName(taskStatisticsBo.getEmpName());
							taskStatistics.setTaskNum(taskStatisticsBo.getTaskNum());
							taskStatistics.setFinishTaskNum(taskStatisticsBo.getFinishTaskNum());
							taskStatistics.setNoStartTaskNum(taskStatisticsBo.getNoStartTaskNum());
							taskStatistics.setProgressTaskNum(taskStatisticsBo.getProgressTaskNum());
							initEntityBaseController.initEntityBaseAttribute(taskStatistics);
							taskStatistics = vixntBaseService.merge(taskStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

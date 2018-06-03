package com.vix.nvix.task.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.pm.common.entity.Project;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.oa.attendance.entity.TaskStatistics;
import com.vix.nvix.oa.bo.TaskStatisticsBo;
import com.vix.nvix.task.vo.TaskStatisticsVo;

/**
 * 任务统计
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class WxpTaskStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String syncTag;
	private String completeTaskName;
	private String completeTaskValue;
	private String timeOutTaskName;
	private String timeOutTaskValue;
	
	@Override
	public String goList() {
		dealCompleteTaskStatistics();
		dealTimeOutTaskStatistics();
		return GO_LIST;
	}

	/** 获取列表数据 */
	public void goTaskStatisticsList() {
		try {
			syncTag = getRequestParameter("syncTag");
			if("week".equals(syncTag)){
				dealTaskStatistics(null, "week");
			}else if("month".equals(syncTag)){
				dealTaskStatistics(null, "month");
			}else if("reason".equals(syncTag)){
				dealTaskStatistics(null, "reason");
			}else if("year".equals(syncTag)){
				dealTaskStatistics(null, "year");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/*Employee emp = getEmployee();
			if (emp != null) {
				params.put("empId," + SearchCondition.EQUAL, emp.getId());
			}*/
			String empName = getDecodeRequestParameter("empName");
			if(StrUtils.isNotEmpty(empName)){
				params.put("empName," + SearchCondition.ANYLIKE, empName.trim());
			}
			params.put("syncTag," + SearchCondition.EQUAL, syncTag.trim());
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaskStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 获取列表数据 */
	public void goProjectTaskStatisticsList() {
		try {
			//dealTaskStatistics(null, "projectTask");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp", "1");
			params.put("isDeleted", "0");
			List<Project> projectList = vixntBaseService.findProjectByHql(params);
			if(projectList != null && projectList.size() > 0){
				for (Project project : projectList) {
					if(project != null){
						//所有任务 
						StringBuilder vixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
								+ " and hentity.isDeleted = '0' and hentity.flag = '2' and type = '0' and hentity.parentVixTask != null and "); 
						vixtaskhql.append(" (hentity.parentVixTask.project.id = '" + project.getId() + "')"); 
						Long vixtaskCount = vixntBaseService.findDataCountByHql(vixtaskhql, "hentity", params); 
						if(vixtaskCount != null){
							project.setTaskNum(Integer.parseInt(vixtaskCount.toString()));
						}
						// 未开始任务 
						StringBuilder noStartVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
								+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '0' and type = '0' and hentity.parentVixTask != null and "); 
						noStartVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
						Long noStartVixtaskCount = vixntBaseService.findDataCountByHql(noStartVixtaskhql, "hentity", params); 
						if(noStartVixtaskCount != null){
							project.setNostartTaskNum(Integer.parseInt(noStartVixtaskCount.toString()));
						}
						// 进行中任务
						StringBuilder progressVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
								+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '1' and type = '0' and hentity.parentVixTask != null and "); 
						progressVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
						Long progressVixtaskCount = vixntBaseService.findDataCountByHql(progressVixtaskhql, "hentity", params); 
						if(progressVixtaskCount != null){
							project.setProgressTaskNum(Integer.parseInt(progressVixtaskCount.toString()));
						}
						// 已完成任务
						StringBuilder completeVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
								+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '2' and type = '0' and hentity.parentVixTask != null and "); 
						completeVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
						Long completeVixtaskCount = vixntBaseService.findDataCountByHql(completeVixtaskhql, "hentity", params); 
						if(completeVixtaskCount != null){
							project.setFinishTaskNum(Integer.parseInt(completeVixtaskCount.toString()));
						}
						// 超时完成任务
						StringBuilder timeoutVixtaskhql = new StringBuilder("select count(hentity.id) from VixTask hentity where 1=1 and hentity.status = '0' and hentity.isTemp = '1' "
								+ " and hentity.isDeleted = '0' and hentity.flag = '2' and hentity.complete = '3' and type = '0' and hentity.parentVixTask != null and "); 
						timeoutVixtaskhql.append(" hentity.parentVixTask.project.id = '" + project.getId() + "' "); 
						Long timeoutVixtaskCount = vixntBaseService.findDataCountByHql(timeoutVixtaskhql, "hentity", params); 
						if(timeoutVixtaskCount != null){
							project.setTimeoutTaskNum(Integer.parseInt(timeoutVixtaskCount.toString()));
						}
						project = vixntBaseService.merge(project);
					}
				}
			}
			Pager pager = getPager();
			String projectName = getDecodeRequestParameter("projectName");
			if (StringUtils.isNotEmpty(projectName)) {
				params.put("projectName", projectName);
			}
			pager = vixntBaseService.findProjectPager(pager, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 获取列表数据 */
	public void goOrgTaskStatisticsList() {
		try {
			dealTaskStatistics(null, "orgTask");
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			String orgName = getDecodeRequestParameter("orgName");
			if(StrUtils.isNotEmpty(orgName)){
				params.put("empName," + SearchCondition.ANYLIKE, orgName.trim());
			}
			params.put("syncTag," + SearchCondition.EQUAL, "orgTask");
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaskStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dealTaskStatistics(Map<String, Object> p, String syncTag) {
		try {
			List<TaskStatisticsBo> taskStatisticsBoList = vixntBaseService.getTaskStatisticsBoList(p, syncTag);
			if (taskStatisticsBoList != null && taskStatisticsBoList.size() > 0) {
				for (TaskStatisticsBo taskStatisticsBo : taskStatisticsBoList) {
					if (taskStatisticsBo != null) {
						TaskStatistics taskStatistics = vixntBaseService.findEntityByAttribute(TaskStatistics.class, "empcodeAndDate", taskStatisticsBo.getEmpId() + taskStatisticsBo.getDatetemp());
						if (taskStatistics == null) {
							taskStatistics = new TaskStatistics();
							taskStatistics.setEmpcodeAndDate(taskStatisticsBo.getEmpId() + taskStatisticsBo.getDatetemp());
							taskStatistics.setEmpId(taskStatisticsBo.getEmpId());
							taskStatistics.setSyncTag(syncTag);
							taskStatistics.setEmpName(taskStatisticsBo.getEmpName());
							taskStatistics.setTaskNum(taskStatisticsBo.getTaskNum());
							taskStatistics.setFinishTaskNum(taskStatisticsBo.getFinishTaskNum());
							taskStatistics.setNoStartTaskNum(taskStatisticsBo.getNoStartTaskNum());
							taskStatistics.setProgressTaskNum(taskStatisticsBo.getProgressTaskNum());
							taskStatistics.setOvertimeTaskNum(taskStatisticsBo.getOvertimeTaskNum());
							taskStatistics.setDatetemp(taskStatisticsBo.getDatetemp());
							initEntityBaseController.initEntityBaseAttribute(taskStatistics);
							taskStatistics = vixntBaseService.merge(taskStatistics);
						} else {
							taskStatistics.setTaskNum(taskStatisticsBo.getTaskNum());
							taskStatistics.setFinishTaskNum(taskStatisticsBo.getFinishTaskNum());
							taskStatistics.setNoStartTaskNum(taskStatisticsBo.getNoStartTaskNum());
							taskStatistics.setProgressTaskNum(taskStatisticsBo.getProgressTaskNum());
							taskStatistics.setOvertimeTaskNum(taskStatisticsBo.getOvertimeTaskNum());
							taskStatistics = vixntBaseService.merge(taskStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dealCompleteTaskStatistics() {
		try {
			List<TaskStatisticsVo> taskStatisticsVoList = vixntBaseService.getCompleteTaskStatisticsVoList();
			if (taskStatisticsVoList != null && taskStatisticsVoList.size() > 0) {
				completeTaskName = "";
				completeTaskValue = "";
				for (TaskStatisticsVo taskStatisticsVo : taskStatisticsVoList) {
					if (taskStatisticsVo != null) {
						completeTaskName += "\"" + taskStatisticsVo.getEmpName() + "\"" + ",";
						completeTaskValue += taskStatisticsVo.getRegnum()+",";
					}
				}
				completeTaskName = completeTaskName.substring(0, completeTaskName.length()-1);
				completeTaskValue = completeTaskValue.substring(0, completeTaskValue.length()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dealTimeOutTaskStatistics() {
		try {
			List<TaskStatisticsVo> taskStatisticsVoList = vixntBaseService.getTimeOutTaskStatisticsVoList();
			if (taskStatisticsVoList != null && taskStatisticsVoList.size() > 0) {
				timeOutTaskName = "";
				timeOutTaskValue = "";
				for (TaskStatisticsVo taskStatisticsVo : taskStatisticsVoList) {
					if (taskStatisticsVo != null) {
						timeOutTaskName += "\"" + taskStatisticsVo.getEmpName() + "\"" + ",";
						timeOutTaskValue += taskStatisticsVo.getRegnum()+",";
					}
				}
				timeOutTaskName = timeOutTaskName.substring(0, timeOutTaskName.length()-1);
				timeOutTaskValue = timeOutTaskValue.substring(0, timeOutTaskValue.length()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportTaskStatisticsExcel(){
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "任务统计.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = new HashMap<String, Object>();
			/*Employee emp = getEmployee();
			if (emp != null) {
				params.put("empId," + SearchCondition.EQUAL, emp.getId());
			}*/
			params.put("syncTag," + SearchCondition.EQUAL, syncTag.trim());
			List<TaskStatistics> taskStatisticsList = vixntBaseService.findAllByConditions(TaskStatistics.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("taskStatistics_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("taskStatistics_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("taskStatisticsList", taskStatisticsList);
					xlsArea.applyAt(new CellRef("taskStatistics!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSyncTag() {
		if (null != syncTag && !"".equals(syncTag)) {
			return syncTag;
		} else {
			syncTag = "week";
			return syncTag;
		}
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	public String getCompleteTaskName() {
		return completeTaskName;
	}

	public void setCompleteTaskName(String completeTaskName) {
		this.completeTaskName = completeTaskName;
	}

	public String getCompleteTaskValue() {
		return completeTaskValue;
	}

	public void setCompleteTaskValue(String completeTaskValue) {
		this.completeTaskValue = completeTaskValue;
	}

	public String getTimeOutTaskName() {
		return timeOutTaskName;
	}

	public void setTimeOutTaskName(String timeOutTaskName) {
		this.timeOutTaskName = timeOutTaskName;
	}

	public String getTimeOutTaskValue() {
		return timeOutTaskValue;
	}

	public void setTimeOutTaskValue(String timeOutTaskValue) {
		this.timeOutTaskValue = timeOutTaskValue;
	}

}
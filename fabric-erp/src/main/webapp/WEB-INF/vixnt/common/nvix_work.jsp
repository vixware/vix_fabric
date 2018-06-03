<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li>
	<a id="mid_task" href="#" onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');">
		<i class="fa fa-lg fa-fw fa-tasks">
			<c:if test="${noStartCount != null && noStartCount > 0}"><em>${noStartCount}</em></c:if>
		</i> 
		<span class="menu-item-parent">任务与计划</span>
	</a>
	<ul>
		<li>
			<a href="#"><span class="menu-item-parent">任务设置</span></a>
			<ul>
				<li><a id="mid_taskType" href="#" onclick="loadContent('mid_taskType','${nvix}/nvixnt/wxpTaskTypeAction!goList.action');">任务类型</a></li>
			</ul>
		</li>
		<li><a id="mid_mytask" href="#" onclick="loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTask.action');">我的任务</a></li>
		<li><a id="mid_mytasklist" href="#" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goMyTask.action');">任务总览</a></li>
		<li><a id="mid_mytask" href="#" onclick="loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goProjectTask.action');">项目任务</a></li>
		<li><a id="mid_mytask" href="#" onclick="loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goTeamTask.action');">团队任务</a></li>
		<%-- <li><a id="mid_mytask" href="#" onclick="loadContent('mid_mytask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskGtt.action');">项目甘特图</a></li>
		<li><a id="mid_mytasklist" href="#" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goMyTask.action');">任务管理</a></li>
		<li><a id="mid_projectstask" href="#" onclick="loadContent('mid_projectstask','${nvix}/nvixnt/taskAndPlanAction!goProjectTaskManagement.action');">项目任务管理</a></li>
		<li><a id="mid_teamtask" href="#" onclick="loadContent('mid_teamtask','${nvix}/nvixnt/taskAndPlanAction!goListTeamTask.action');">团队任务管理</a></li> --%>
		<li><a id="mid_wxpTaskStatistics" href="#" onclick="loadContent('mid_wxpTaskStatistics','${nvix}/nvixnt/wxpTaskStatisticsAction!goList.action');">统计分析</a></li>
	</ul>
</li>
<li><a id="mid_schedule" href="#" onclick="loadContent('mid_schedule','${nvix}/nvixnt/nvixScheduleAction!goList.action');"><i class="fa fa-lg fa-fw fa-calendar"></i><span class="menu-item-parent">日程安排</span></a></li>
<li><a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i><span class="menu-item-parent">协同办公</span></a>
	<ul>
		<li><a id="oa_dailyJournal" href="#" onclick="loadContent('oa_dailyJournal','${nvix}/nvixnt/dailyJournalAction!goList.action');">日报/工作日志</a></li>
		<li><a id="oa_conference" href="#" onclick="loadContent('oa_conference','${nvix}/nvixnt/conferenceManagementAction!goList.action');">会议管理</a></li>
		<li><a id="oa_notice" href="#" onclick="loadContent('oa_notice','${nvix}/nvixnt/noticeAction!goList.action');">公告通知</a></li>
		<li><a id="oa_newsAdministration" href="#" onclick="loadContent('oa_newsAdministration','${nvix}/nvixnt/newsAdministrationAction!goList.action');">新闻</a></li>
		<li><a id="oa_workPlans" href="#" onclick="loadContent('oa_workPlans','${nvix}/nvixnt/workPlansAction!goList.action');">工作计划</a></li>
		<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/nvixJobTodoAction!goList.action');">待办审批</a></li>
		<li><a id="oa_addresses" href="#" onclick="loadContent('oa_addresses','${nvix}/nvixnt/addressesAction!goList.action');">通讯录</a></li>
		<li><a href="#"><span class="menu-item-parent">报销管理</span></a>
			<ul>
				<li><a href="#"><span class="menu-item-parent">设置</span></a>
					<ul>
						<li><a id="oa_nvixarealevel" href="#" onclick="loadContent('oa_nvixarealevel','${nvix}/nvixnt/nvixAreaLevelAction!goList.action');">地区设置</a></li>
						<li><a id="oa_nvixvehicle" href="#" onclick="loadContent('oa_nvixvehicle','${nvix}/nvixnt/nvixVehicleAction!goList.action');">交通工具</a></li>
						<li><a id="oa_nvixareaexpensesstandards" href="#" onclick="loadContent('oa_nvixareaexpensesstandards','${nvix}/nvixnt/nvixAreaExpensesStandardsAction!goList.action');">交通报销标准</a></li>
						<li><a id="oa_nvixDailyExpensesStandards" href="#" onclick="loadContent('oa_nvixDailyExpensesStandards','${nvix}/nvixnt/nvixDailyExpensesStandardsAction!goList.action');">日常报销标准</a></li>
						<li><a id="oa_nvixAccomodationsExpensesStandards" href="#" onclick="loadContent('oa_nvixAccomodationsExpensesStandards','${nvix}/nvixnt/nvixAccomodationsExpensesStandardsAction!goList.action');">住宿报销标准</a></li>
					</ul></li>
				<li><a id="oa_vixntreimburse" href="#" onclick="loadContent('oa_vixntreimburse','${nvix}/nvixnt/vixntReimburseAction!goList.action');">我的报销</a></li>
			</ul></li>
	</ul></li>
	<!-- 考勤管理 -->
	<li><a id="oa_attendance" href="#" onclick="loadContent('oa_attendance','${nvix}/nvixnt/attendanceManagementAction!goList.action');"><i class="fa fa-lg fa-fw fa-calendar-o"></i> <span class="menu-item-parent">考勤管理</span></a>
	<ul>
		<li><a href="#" id="" onclick=""><span class="menu-item-parent">规则设置</span></a>
			<ul>
				<li><a href="#" id="oa_period" onclick="loadContent('oa_period','${nvix}/nvixnt/periodAction!goList.action');">工作时段设置</a></li>
				<li><a href="#" id="oa_ruleSet" onclick="loadContent('oa_ruleSet','${nvix}/nvixnt/attendance/attendanceRuleSetAction!goRuleSet.action');">班次设置</a></li>
				<li><a href="#" id="oa_ruleDetail" onclick="loadContent('oa_ruleDetail','${nvix}/nvixnt/attendance/attendanceRuleSetAction!goRuleDetail.action');">基本规则</a></li>
				<li><a href="#" id="oa_overTime" onclick="loadContent('oa_overTime','${nvix}/nvixnt/attendance/overTimeAction!goList.action');">加班规则</a></li>
				<li><a href="#" id="oa_automatic" onclick="loadContent('oa_automatic','${nvix}/nvixnt/attendance/automaticAction!goList.action');">自动套班</a></li>
				<li><a href="#" id="oa_calculation" onclick="loadContent('oa_calculation','${nvix}/nvixnt/attendance/attendanceRuleSetAction!goCalculation.action');">计算方式</a></li>
				<li><a href="#" id="oa_other" onclick="loadContent('oa_other','${nvix}/nvixnt/attendance/otherRuleAction!goList.action');">其它规则</a></li>
			</ul></li>
		<li><a href="#">日常操作</a>
			<ul>
				<li><a href="#" id="oa_schedul" onclick="loadContent('oa_schedul','${nvix}/nvixnt/attendance/schedulingAction!goList.action');">排班管理</a></li>
				<li><a href="#" id="oa_vixntLeave" onclick="loadContent('oa_vixntLeave','${nvix}/nvixnt/vixntLeaveAction!goList.action');">请假管理</a></li>
				<li><a href="#" id="oa_holidayRule" onclick="loadContent('oa_holidayRule','${nvix}/nvixnt/attendance/holiDayRuleAction!goList.action');">假日管理</a></li>
			</ul></li>
		<li><a href="#"><span class="menu-item-parent">报表管理</span></a>
			<ul>
				<li><a href="#" id="oa_attendanceDetail" onclick="loadContent('oa_attendanceDetail','${nvix}/nvixnt/attendanceDetailAction!goList.action');">原始记录查询</a></li>
				<li><a href="#" id="oa_attendanceResultDetail" onclick="loadContent('oa_attendanceResultDetail','${nvix}/nvixnt/attendanceResultDetailAction!goList.action');">考勤结果明细表</a></li>
				<li><a href="#" id="oa_attendanceResultCollect" onclick="loadContent('oa_attendanceResultCollect','${nvix}/nvixnt/attendanceResultCollectAction!goList.action');">考勤结果汇总</a></li>
			</ul></li>
	</ul></li>
<s:include value="nvix_document.jsp"></s:include>
<li><a href="#"><i class="fa fa-lg fa-fw fa-file"></i> <span class="menu-item-parent">文件管理</span></a>
<ul>
	<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/nvixFileAction!goFile.action');">我的文件</a></li>
	<li><a id="mid_file" href="#" onclick="loadContent('mid_file','${nvix}/nvixnt/nvixFileAction!goList.action');">文件</a></li>
</ul></li>
<li><a id="" href="#" ><i class="fa fa-lg fa-fw fa-puzzle-piece"></i><span class="menu-item-parent">邮件管理</span></a>
<ul>
	<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/system/nvixMailAction!goList.action');"> 收件箱</a></li>
</ul></li>
<s:include value="nvix_vreport.jsp"></s:include>
<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/system/vixntRemindsCenterAction!goList.action');"><i class="fa fa-lg fa-fw fa-comment-o"></i> <span class="menu-item-parent">消息中心</span></a></li>
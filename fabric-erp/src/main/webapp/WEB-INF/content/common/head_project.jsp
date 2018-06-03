<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">项目管理</a>
	<ul>
		<li class="fly"><a href="#">仪表盘</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectDashboardAction!goList.action','bg_02');">项目仪表盘</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectAction!goSaveOrUpdate.action','bg_02');">新增项目</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectAction!goList.action','bg_02');">项目列表</a></li>
				<li><a href="#">项目设置</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/pm/projectCanlendarAction!goCanlendar.action','bg_02');">项目日历</a></li>
		<li><a href="#" onclick="loadContent('${vix}/pm/demandManagementAction!goList.action','bg_02');">需求管理</a></li>
		<li class="fly"><a href="#">任务管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/taskManagementAction!mgrTaskList.action','bg_02');">任务列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/taskManagementAction!goList.action','bg_02');">任务视图</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/pmMilestoneAction!goList.action','bg_02');">里程碑</a></li>
				<li><a href="#">依赖视图</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/ganttChartAction!goList.action','bg_02');">甘特图</a></li>
				<li><a href="#">任务报告</a></li>
			</ul></li>
		<li class="fly"><a href="#">资源管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/pmViewAction!goList.action','bg_02');">项目组织管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectFileAction!goList.action','bg_02');">项目文件管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/equipmentResourceAction!goList.action','bg_02');">设备资源管理</a></li>
			</ul></li>
		<li class="fly"><a href="#">追踪管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/changeManageAction!goList.action','bg_02');">变更管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/problemReportAction!goList.action','bg_02');">问题报告</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/milestoneReportAction!goList.action','bg_02');">里程碑报告</a></li>
				<li><a href="#">问题设置</a></li>
				<li><a href="#">报表</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/pm/documentationAction!goList.action','bg_02');">文档管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/documentationAction!goList.action','bg_02');">文档列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/newFolderAction!goList.action','bg_02');">文件夹列表</a></li>
			</ul></li>
		<li class="fly"><a href="#">沟通管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/meetingManagementAction!goList.action','bg_02');">会议管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/communicationFeedbackAction!timeline.action','bg_02');">沟通与反馈</a></li>
				<li><a href="#">论坛 </a></li>
				<li><a href="#">项目Wiki</a></li>
				<li><a href="#">聊天</a></li>
			</ul></li>
		<li class="fly"><a href="#">时间追踪与结算</a>
			<ul>
				<li><a href="#">列表视图</a></li>
				<li><a href="#">日历视图</a></li>
				<li><a href="#">时间追踪报表</a></li>
			</ul></li>
		<li class="fly"><a href="#">项目用户</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/pmOrgAction!goList.action','bg_02');">项目成员</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/projectUsersAction!goList.action','bg_02');">客户成员</a></li>
			</ul></li>
		<li class="fly"><a href="#">项目一览</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/pm/capitalListAction!goList.action','bg_02');">资金一览</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/progressListAction!goList.action','bg_02');">进度一览</a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/pmStaffListAction!goList.action','bg_02');">人员一览</a></li>
			</ul></li>
		<li><a href="#">项目风险评估</a></li>
		<li><a href="#">项目预警</a></li>
		<li class="fly_end"><a href="#">项目统计</a></li>
	</ul></li>
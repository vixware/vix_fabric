<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<ul class="notification-body">
	<s:if test="vixTaskList.size > 0">
		<s:iterator value="vixTaskList" var="entity" status="s">
			<li><span>
					<div class="bar-holder no-padding">
						<p class="margin-bottom-5">
							<strong><a onclick="loadContent('mid_task','${nvix}/nvixnt/taskAndPlanAction!goList.action');">任务名称:</a></strong> <i>${entity.questName }</i> <span class="pull-right semi-bold text-muted">${entity.taskSchedule }%</span>
						</p>
						<div class="progress progress-md progress-striped">
							<div class="progress-bar bg-color-teal" style="width: ${entity.taskSchedule }%;"></div>
						</div>
						<em class="note no-margin">${entity.createTimeTimeStr }</em>
					</div>
			</span></li>
		</s:iterator>
	</s:if>
</ul>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:if test="projectList.size > 0">
	<s:iterator value="projectList" var="entity" status="s">
		<div class="task-item">
			<div class="task-wrap">
				<a href="#"><div class="task-pic">
						<img src="${nvix}${entity.pictureurl}" height="286px" width="286px">
						<div class="task-mask">
							<div class="air air-top-righ">
								<div class="easy-pie-chart txt-color-white easyPieChart" data-percent="${entity.projectSchedule }" data-pie-size="100" data-pie-track-color="rgba(119, 119, 127,1)">
									<span class="percent percent-sign txt-color-white"></span>
								</div>
							</div>
						</div>
					</div></a>
				<div class="task-title clearfix txt-color-grayDark">
					<a data-toggle="modal" href="#appviews" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id='+'${entity.id}');">${entity.projectName}</a>
					<div class="pull-right">
						<a href="#">开始时间：${entity.estimateStartTimeStr }</a>
					</div>
				</div>
			</div>
		</div>
	</s:iterator>
</s:if>
<s:else>
	<div class="task-item">
		<div class="task-wrap">
			<a href="#"><div class="task-pic">
					<img src="${nvix}/vixntcommon/base/images/project.png">
					<div class="task-mask">
						<div class="air air-top-righ">
							<div class="easy-pie-chart txt-color-white easyPieChart" data-percent="${entity.projectSchedule }" data-pie-size="100" data-pie-track-color="rgba(119, 119, 127,1)">
								<span class="percent percent-sign txt-color-white"></span>
							</div>
						</div>
					</div>
				</div></a>
			<div class="task-title clearfix txt-color-grayDark">
				<a data-toggle="modal" href="#appviews" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id='+'${entity.id}');">${entity.projectName}</a>
				<div class="pull-right">
					<a href="#">开始时间：${entity.estimateStartTimeStr }</a>
				</div>
			</div>
		</div>
	</div>
	<div class="task-item">
		<div class="task-wrap">
			<a href="#"><div class="task-pic">
					<img src="${nvix}/vixntcommon/base/images/project.png">
					<div class="task-mask">
						<div class="air air-top-righ">
							<div class="easy-pie-chart txt-color-white easyPieChart" data-percent="${entity.projectSchedule }" data-pie-size="100" data-pie-track-color="rgba(119, 119, 127,1)">
								<span class="percent percent-sign txt-color-white"></span>
							</div>
						</div>
					</div>
				</div></a>
			<div class="task-title clearfix txt-color-grayDark">
				<a data-toggle="modal" href="#appviews" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id='+'${entity.id}');">${entity.projectName}</a>
				<div class="pull-right">
					<a href="#">总任务：${entity.taskNum }</a>
				</div>
			</div>
		</div>
	</div>
	<div class="task-item">
		<div class="task-wrap">
			<a href="#"><div class="task-pic">
					<img src="${nvix}/vixntcommon/base/images/project.png">
					<div class="task-mask">
						<div class="air air-top-righ">
							<div class="easy-pie-chart txt-color-white easyPieChart" data-percent="${entity.projectSchedule }" data-pie-size="100" data-pie-track-color="rgba(119, 119, 127,1)">
								<span class="percent percent-sign txt-color-white"></span>
							</div>
						</div>
					</div>
				</div></a>
			<div class="task-title clearfix txt-color-grayDark">
				<a data-toggle="modal" href="#appviews" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id='+'${entity.id}');">${entity.projectName}</a>
				<div class="pull-right">
					<a href="#">总任务：${entity.taskNum }</a>
				</div>
			</div>
		</div>
	</div>
	<div class="task-item">
		<div class="task-wrap">
			<a href="#"><div class="task-pic">
					<img src="${nvix}/vixntcommon/base/images/project.png">
					<div class="task-mask">
						<div class="air air-top-righ">
							<div class="easy-pie-chart txt-color-white easyPieChart" data-percent="${entity.projectSchedule }" data-pie-size="100" data-pie-track-color="rgba(119, 119, 127,1)">
								<span class="percent percent-sign txt-color-white"></span>
							</div>
						</div>
					</div>
				</div></a>
			<div class="task-title clearfix txt-color-grayDark">
				<a data-toggle="modal" href="#appviews" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id='+'${entity.id}');">${entity.projectName}</a>
				<div class="pull-right">
					<a href="#">总任务：${entity.taskNum }</a>
				</div>
			</div>
		</div>
	</div>
</s:else>
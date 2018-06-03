<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<c:if test="${projectList != null && fn:length(projectList) > 0}">
	<c:forEach items="${projectList}" var="project" varStatus="s">
		<div class="task-list <c:if test="${s.index == 0}">select</c:if>" style="cursor: pointer;" id="${project.id}">
			<h6>${project.projectName}</h6>
			<p class="txt-color-gray">${project.note}</p>
			<c:forEach items="${taskMap}" var="map"> 
				<c:if test="${map.key == project.id}">
					未开始
					<div class="progress progress-xs">
						<div class="progress-bar bg-color-orange" style="width: ${fn:split(map.value,':')[0]}"></div>
					</div>
					进行中
					<div class="progress progress-xs">
						<div class="progress-bar bg-color-blue1" style="width: ${fn:split(map.value,':')[1]}"></div>
					</div>
					已完成
					<div class="progress progress-xs">
						<div class="progress-bar bg-color-green1" style="width: ${fn:split(map.value,':')[2]}"></div>
					</div>
					超时完成
					<div class="progress progress-xs">
						<div class="progress-bar bg-color-red1" style="width: ${fn:split(map.value,':')[3]}"></div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<hr/>
	</c:forEach>
</c:if>
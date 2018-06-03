<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="col-sm-12 col-md-12 col-lg-12">
	<div class="pull-right">&nbsp;${vixTask.taskSchedule}%</div>
	<div class="progress progress-sm">
		<div class="progress-bar" style="width: ${vixTask.taskSchedule}%;"></div>
	</div>
</div>
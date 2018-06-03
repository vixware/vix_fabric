<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="executionFeedbackForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdateFeedback.action">
	<input type="hidden" id="executionFeedbackId" name="executionFeedback.id" value="${executionFeedback.id}" /> <input type="hidden" id="projectId" name="executionFeedback.project.id" value="${executionFeedback.project.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务进度:</label>
			<div class="col-md-10">
				<input id="schedule" name="schedule" value="${executionFeedback.taskDefinition.taskSchedule}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 反馈内容:</label>
			<div class="col-md-10">
				<input id="executionFeedback" name="executionFeedback.executionFeedback" value="${executionFeedback.executionFeedback}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#executionFeedbackForm").validationEngine();
</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="executionFeedbackForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdateFeedback.action">
	<input type="hidden" id="executionFeedbackId" name="executionFeedback.id" value="${executionFeedback.id}" /> <input type="hidden" id="projectId" name="executionFeedback.project.id" value="${executionFeedback.project.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 反馈时间:</label>
			<div class="col-md-9">
				<input id="feedbackTime" name="executionFeedback.feedbackTime" value="${executionFeedback.feedbackTimeTimeStr}" class="form-control validate[required]" type="text" readonly="readonly" onClick="" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 项目进度:</label>
			<div class="col-md-9">
				<%-- <input id="schedule" name="executionFeedback.schedule" value="${executionFeedback.taskDefinition.taskSchedule}" class="form-control validate[required,custom[integer],min[0],max[100]]" type="text" /> --%>
				<input type="text" data-slider-id='schedule' id="schedule" name="executionFeedback.schedule" value="${executionFeedback.project.projectSchedule}" class="slider slider-primary" data-slider-step="1" data-slider-min="0" data-slider-max="100"
					data-slider-value="${executionFeedback.project.projectSchedule}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 反馈内容:</label>
			<div class="col-md-10">
				<textarea name="executionFeedback.executionFeedback" class="form-control validate[required]">${executionFeedback.executionFeedback } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#executionFeedbackForm").validationEngine();
	$('#schedule').slider({
		formatter : function(value) {
			return 'Current value: ' + value;
		}
	});
</script>
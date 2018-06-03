<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="executionFeedbackForm" class="form-horizontal" style="padding: 20px 15px; margin-top: 20px;" action="${nvix}/nvixnt/taskAndPlanAction!saveOrUpdateExecutionFeedback.action">
	<input type="hidden" id="id" name="executionFeedback.id" value="${executionFeedback.id}" /> <input type="hidden" id="code" name="executionFeedback.code" value="${executionFeedback.code}" /> <input type="hidden" id="taskDefinitionId" name="executionFeedback.taskDefinition.id"
		value="${executionFeedback.taskDefinition.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 反馈时间:</label>
			<div class="col-md-9">
				<input id="feedbackTime" name="executionFeedback.feedbackTime" value="${executionFeedback.feedbackTimeTimeStr}" class="form-control validate[required]" type="text" readonly="readonly" onClick="" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 任务进度:</label>
			<div class="col-md-9">
				<%-- <input id="schedule" name="executionFeedback.schedule" value="${executionFeedback.taskDefinition.taskSchedule}" class="form-control validate[required,custom[integer],min[0],max[100]]" type="text" /> --%>
				<input type="text" data-slider-id='schedule' id="schedule" name="executionFeedback.schedule" value="${executionFeedback.taskDefinition.taskSchedule}" class="slider slider-primary" data-slider-step="1" data-slider-min="0" data-slider-max="100"
					data-slider-value="${executionFeedback.taskDefinition.taskSchedule}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 反馈内容:</label>
			<div class="col-md-9">
				<textarea id="executionFeedback" name="executionFeedback.executionFeedback" class="form-control validate[required]">${executionFeedback.executionFeedback} </textarea>
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
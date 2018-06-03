<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="feedbackForm" class="form-horizontal" style="padding: 20px 15px; margin-top: 20px;" action="${nvix}/nvixnt/nvixCrmProjectAction!saveOrUpdateFeedback.action">
	<input type="hidden" id="id" name="id" value="${crmProject.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 项目进度:</label>
			<div class="col-md-9">
				<input type="text" data-slider-id='projectSchedule' id="projectSchedule" name="projectSchedule" value="${crmProject.projectSchedule}" class="slider slider-primary" data-slider-step="1" data-slider-min="0" data-slider-max="100"
					data-slider-value="${crmProject.projectSchedule}" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#feedbackForm").validationEngine();
	$('#projectSchedule').slider({
		formatter : function(value) {
			return 'Current value: ' + value;
		}
	});
</script>
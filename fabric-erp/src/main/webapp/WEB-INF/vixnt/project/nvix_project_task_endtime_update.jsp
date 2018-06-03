<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="projectEndTimeForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!updateTaskEndTime.action">
	<input type="hidden" id="id" name="vixTask.id" value="${vixTask.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-4 control-label"><span class="text-danger">*</span> 截止时间:</label>
			<div class="col-md-8">
				<input id="newEndTime" name="newEndTime" value="${fn:substring(vixTask.taskEndTime, 0,19)}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 200px;" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#projectEndTimeForm").validationEngine();
</script>
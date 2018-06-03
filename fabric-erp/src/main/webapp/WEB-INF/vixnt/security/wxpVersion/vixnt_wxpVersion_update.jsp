<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="taskTypeForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/wxpTaskTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="taskType.id" value="${taskType.id}" /> <input type="hidden" id="code" name="taskType.code" value="${taskType.code}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-10">
				<input id="name" name="taskType.name" value="${taskType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="taskType.memo" class="form-control">${taskType.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#taskTypeForm").validationEngine();
</script>
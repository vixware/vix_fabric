<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="transportForm" action="${nvix}/nvixnt/nvixVehicleAction!saveOrUpdate.action" class="form-horizontal" style="padding: 20px 15px;">
	<input type="hidden" id="id" name="transport.id" value="${transport.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="transport.name" value="${transport.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="transport.memo" class="form-control">${transport.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#transportForm").validationEngine();
</script>
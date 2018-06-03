<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerAccountClipTypeForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/membershipCardTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="customerAccountClipType.id" value="${customerAccountClipType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-10">
				<input id="name" name="customerAccountClipType.name" value="${customerAccountClipType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="customerAccountClipType.memo" class="form-control">${customerAccountClipType.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#customerAccountClipTypeForm").validationEngine();
</script>
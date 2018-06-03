<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="itemTagForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixItemTagAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="itemTag.id" value="${itemTag.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="itemTag.name" value="${itemTag.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="itemTag.memo" class="form-control">${itemTag.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#itemTagForm").validationEngine();
</script>
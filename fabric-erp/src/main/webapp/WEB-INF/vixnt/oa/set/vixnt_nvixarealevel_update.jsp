<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="areaLevelForm" action="${nvix}/nvixnt/nvixAreaLevelAction!saveOrUpdate.action" class="form-horizontal" style="padding: 20px 15px;">
	<input type="hidden" id="id" name="areaLevel.id" value="${areaLevel.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="areaLevel.name" value="${areaLevel.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="areaLevel.memo" class="form-control">${areaLevel.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#areaLevelForm").validationEngine();
</script>
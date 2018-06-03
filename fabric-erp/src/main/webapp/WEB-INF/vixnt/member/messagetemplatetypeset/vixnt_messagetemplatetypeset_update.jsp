<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="memberTagForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntMessageTemplateTypeSetAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="memberTag.id" value="${memberTag.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-3">
				<input id="code" name="memberTag.code" value="${memberTag.code}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="memberTag.name" value="${memberTag.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="memberTag.memo" class="form-control">${memberTag.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#memberTagForm").validationEngine();
</script>
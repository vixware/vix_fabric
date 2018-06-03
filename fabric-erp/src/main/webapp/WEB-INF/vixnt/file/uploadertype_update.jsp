<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="uploaderTypeForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixFileAction!saveOrUpdateUploaderType.action">
	<input type="hidden" id="uploaderTypeId" name="uploaderType.id" value="${uploaderType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 分类编码:</label>
			<div class="col-md-4">
				<input id="uploaderTypeCode" name="uploaderType.code" value="${uploaderType.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 分类名称:</label>
			<div class="col-md-4">
				<input id="uploaderTypeName" name="uploaderType.name" value="${uploaderType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
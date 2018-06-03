<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="uploaderAttLogsForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/attendanceDetailAction!updateUploader.action">
	<input type="hidden" id="uploaderId" name="uploader.id" value="${uploader.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span>考勤原始记录:
			</label>
			<div class="col-md-6">
				<input type="file" id="docToUpload" name="docToUpload" class="validate[required]">
			</div>
		</div>
	</fieldset>
</form>
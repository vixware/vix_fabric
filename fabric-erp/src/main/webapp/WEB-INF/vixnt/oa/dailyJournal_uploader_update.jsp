<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="uploaderForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/dailyJournalAction!saveOrUpdateUploader.action">
	<input type="hidden" id="workLogId" name="id" value="${workLog.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span>附件:
			</label>
			<div class="col-md-6">
				<input type="file" id="docToUpload" name="docToUpload" class="validate[required]">
			</div>
		</div>
	</fieldset>
</form>